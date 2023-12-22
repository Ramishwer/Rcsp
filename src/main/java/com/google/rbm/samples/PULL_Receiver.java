/*
Copyright 2018 Google Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.google.rbm.samples;

// [START import_libraries]
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.services.rcsbusinessmessaging.v1.RCSBusinessMessaging;
import com.google.api.services.rcsbusinessmessaging.v1.model.AgentContentMessage;
import com.google.api.services.rcsbusinessmessaging.v1.model.AgentMessage;
import com.google.api.services.rcsbusinessmessaging.v1.model.ContentInfo;
import com.google.api.services.rcsbusinessmessaging.v1.model.DialAction;
import com.google.api.services.rcsbusinessmessaging.v1.model.OpenUrlAction;
import com.google.api.services.rcsbusinessmessaging.v1.model.StandaloneCard;
import com.google.api.services.rcsbusinessmessaging.v1.model.SuggestedAction;
import com.google.api.services.rcsbusinessmessaging.v1.model.Suggestion;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.rbm.samples.lib.RbmApiHelper;
import com.google.rbm.samples.lib.StandaloneCardHelper;
import com.google.rbm.samples.lib.SuggestionHelper;
import com.google.rbm.samples.lib.cards.CardOrientation;
import com.google.rbm.samples.lib.cards.CardWidth;
import com.google.rbm.samples.lib.cards.MediaHeight;
import db.dbcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
// [END import_libraries]

/**
 * RCS Business Messaging sample first agent.
 * <p>
 * Sends the following message to a user: "What is your favorite color?" Parses
 * the user's response and echos it in a new message.
 */
public class PULL_Receiver {

    private static final Logger logger = Logger.getLogger(PULL_Receiver.class.getName());

    private static final String EXCEPTION_WAS_THROWN = "an exception was thrown";

    // the name of the pub/sub pull subscription
    private static final String PUB_SUB_NAME = "rbm-agent-subscription";

    // pubsub subscription service for our pull requests
    private Subscriber subscriber;

    // reference to the RBM api builder
    private RCSBusinessMessaging.Builder builder;

    private RbmApiHelper rbmApiHelper;
    private String msisdn;

    // the phone number, in E.164 format, to start a conversation with
    /**
     * Constructor of the PULL_Receiver class.
     */
    public PULL_Receiver() {
        logger.info("Initializing the agent.");

        // initialize pub/sub for pull monitoring
        initPubSub("rbm-agent-service-account-credentials.json");

        // initialize the API helper
        this.rbmApiHelper = new RbmApiHelper();
    }

    /**
     * Creates a MessageReceiver handler for pulling new messages from the
     * pubsub subscription.
     *
     * @return The MessageReceiver listener.
     */
    private MessageReceiver getMessageReceiver() {
        return new MessageReceiver() {
            /**
             * Handle incoming message, then ack/nack the received message.
             *
             * @param message The message sent by the user.
             * @param consumer Consumer for accepting a reply.
             */

            public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
                String jsonResponse = message.getData().toStringUtf8();

                logger.info("Id : " + message.getMessageId());
                logger.info(jsonResponse);

                JSONObject jobj = new JSONObject(jsonResponse);
                JSONObject jsonMap = new JSONObject();
                if (jobj.has("suggestionResponse")) {
                    jsonMap = jobj.getJSONObject("suggestionResponse");
                } else if (jobj.has("status")) {
                    jsonMap = jobj.getJSONObject("status");
                } else {
                    jsonMap = jobj;
                }

                dbcon db = new dbcon();
                db.getCon("RCS");

                String sql = "insert into PULLS(msgId,Data) values('" + message.getMessageId() + "','" + jsonResponse + "')";
                logger.info("Executing:" + sql);
                logger.info("Rows Inserted:" + db.setUpdate(sql));
                db.closeConection();
                // make sure the map contains response text
                if (jsonMap.has("text")) {
                    String userResponseText = jsonMap.getString("text");
                    String senderPhoneNumber = jobj.getString("senderPhoneNumber");
                    String messageId = jobj.getString("messageId");
                    logger.info("Response:" + userResponseText);
                    logger.info("senderPhoneNumber:" + senderPhoneNumber);
                    logger.info("messageId:" + messageId);

                    // let the user know we received and read the message
                    rbmApiHelper.sendReadMessage(messageId, senderPhoneNumber);

                    // forward the response to our handler
                    handleUserResponse(userResponseText, senderPhoneNumber);
                }

                // let the service know we successfully processed the response
                consumer.ack();
            }
        };
    }

    /**
     * Initializes a pull subscription to receive user responses.
     */
    private void initPubSub(String credentialsFileLocation) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(credentialsFileLocation).getFile());

            CredentialsProvider credentialsProvider
                    = FixedCredentialsProvider.create(ServiceAccountCredentials
                            .fromStream(
                                    new FileInputStream(file)
                            )
                    );

            GoogleCredentials credentials
                    = GoogleCredentials.fromStream(new FileInputStream(file))
                            .createScoped(
                                    Collections.singletonList("https://www.googleapis.com/auth/pubsub"));
            credentials.refreshIfExpired();

            String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

            ProjectSubscriptionName subscriptionName
                    = ProjectSubscriptionName.of(projectId, PUB_SUB_NAME);

            // Instantiate an asynchronous message receiver
            MessageReceiver receiver = this.getMessageReceiver();

            // create PubSub subscription
            subscriber = Subscriber.newBuilder(subscriptionName, receiver)
                    .setCredentialsProvider(credentialsProvider)
                    .build();

            logger.info("Starting Pub/Sub listener");
            subscriber.startAsync();
        } catch (Exception e) {
            logger.log(Level.SEVERE, EXCEPTION_WAS_THROWN, e);
        }
    }

    public void subscribeAsyncExample(String projectId, String subscriptionId, String credentialsFileLocation) {
        ProjectSubscriptionName subscriptionName
                = ProjectSubscriptionName.of(projectId, subscriptionId);

        // Instantiate an asynchronous message receiver.
        MessageReceiver receiver
                = (PubsubMessage message, AckReplyConsumer consumer) -> {
                    // Handle incoming message, then ack the received message.
                    System.out.println("Id: " + message.getMessageId());
                    String jsonResponse = message.getData().toStringUtf8();
                    dbcon db = new dbcon();
                    db.getCon("RCS");

                    String sql = "insert into PULLS(msgId,Data) values('" + message.getMessageId() + "','" + jsonResponse + "')";
                    logger.info("Executing:" + sql);
                    logger.info("Rows Inserted:" + db.setUpdate(sql));
                    db.closeConection();
                    System.out.println("Data: " + jsonResponse);

                    consumer.ack();
                };

        Subscriber subscriber = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(credentialsFileLocation).getFile());

            CredentialsProvider credentialsProvider
                    = FixedCredentialsProvider.create(ServiceAccountCredentials
                            .fromStream(
                                    new FileInputStream(file)
                            )
                    );

            subscriber = Subscriber.newBuilder(subscriptionName, receiver).setCredentialsProvider(credentialsProvider).build();
            // Start the subscriber.
            subscriber.startAsync().awaitRunning();
            System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
            // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
            subscriber.awaitTerminated(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            // Shut down the subscriber after 30s. Stop receiving messages.
            subscriber.stopAsync();
        }
    }

    /**
     * Takes the user's response and creates an appropriate response.
     * <p>
     * In this sample, the RBM agent responds with "I like USER_RESPONSE too!"
     *
     * @param responseText The response the user sent to the agent.
     * @param senderPhoneNumber The phone number that send the response.
     */
    private void handleUserResponse(String responseText, String senderPhoneNumber) {

        responseText = responseText.toLowerCase();

        if (responseText.equals("stop")) {
            // Any real agent must support this command
            // TODO: Client typed stop, agent should no longer send messages to this msisdn
            logger.info(senderPhoneNumber + " asked to stop agent messaging");
        } else if (responseText.equalsIgnoreCase("1.Product List")) {

            List<Suggestion> suggestions = new ArrayList<Suggestion>();

            OpenUrlAction openUrlAction = new OpenUrlAction();
            openUrlAction.setUrl("https://virtuosonetsoft.com/sms24hours");

            // creating a suggested action based on an open url action
            SuggestedAction suggestedAction = new SuggestedAction();
            suggestedAction.setText("1.SMS");
            suggestedAction.setPostbackData("suggestion_sms");
            suggestedAction.setOpenUrlAction(openUrlAction);

            SuggestedAction suggestedAction2 = new SuggestedAction();
            suggestedAction2.setText("2.Whatsapp");
            suggestedAction2.setPostbackData("suggestion_whatsapp");
            suggestedAction2.setOpenUrlAction(openUrlAction);

            SuggestedAction suggestedAction3 = new SuggestedAction();
            suggestedAction3.setText("3.RCS");
            suggestedAction3.setPostbackData("suggestion_rcs");
            suggestedAction3.setOpenUrlAction(openUrlAction);

            // attaching action to a suggestion
            Suggestion suggestion = new Suggestion();
            suggestion.setAction(suggestedAction);

            Suggestion suggestion2 = new Suggestion();
            suggestion2.setAction(suggestedAction2);

            Suggestion suggestion3 = new Suggestion();
            suggestion3.setAction(suggestedAction3);

            suggestions.add(suggestion);
            suggestions.add(suggestion2);
            suggestions.add(suggestion3);
            String fileUrl = "http://reports.sms24hours.com:8021/RPR/img/images.jpg";
            try {
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Product list",
                        "Please choose From Below:",
                        fileUrl,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.uploadFile(fileUrl);
                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);

                // rbmApiHelper.sendTextMessage("Please choose From Below:", senderPhoneNumber, suggestions);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            String arr[] = {};

        } else if (responseText.equalsIgnoreCase("hello") || responseText.equalsIgnoreCase("hi")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Grettings !!! welcome to Vns Sms24HourServices",
                        senderPhoneNumber
                );
                List cardContents = new ArrayList();

                String card1Image = "https://thumbs.dreamstime.com/z/business-woman-sending-sms-email-marketing-business-woman-sending-sms-email-marketing-using-mobile-phone-118223999.jpg";

                // Create suggestions for first carousel card
                List card1Suggestions = new ArrayList();
                card1Suggestions.add(
                        new SuggestionHelper("Interested", "card_1"));

                cardContents.add(
                        new StandaloneCardHelper(
                                "BULK SMS",
                                "Being our primary and best-selling product, Bulk SMS is one of the best options to go for to reach more and more people & grow your reach. Target more people with promotional or transactional SMS with Virtuoso, SMS 24Hours today & Play as a ruler in the market.",
                                card1Image,
                                card1Suggestions)
                                .getCardContent(MediaHeight.SHORT)
                );

                // Images for the carousel cards
                String card2Image = "https://media.istockphoto.com/id/1424406254/photo/business-man-hand-working-on-laptop-with-email-illustration-email-marketing-concept-company.jpg?s=1024x1024&w=is&k=20&c=PqDvqeKGrsv_4b_JrU--yORNCb5UwGYZSjw6kTX6ZUI=";

                // Create suggestions for second carousel card
                List card2Suggestions = new ArrayList();
                card2Suggestions.add(
                        new SuggestionHelper("Interested", "card_2"));

                cardContents.add(
                        new StandaloneCardHelper(
                                "EMAIL MARKETING",
                                "Looking for bulk email services? We are one of the most relied upon Email marketing companies across the globe with the huge target based customized database of emails. We shoot emails to promote your business across the globe.",
                                card2Image,
                                card2Suggestions)
                                .getCardContent(MediaHeight.SHORT)
                );
                String card3Image = "https://wpsocialninja.com/wp-content/uploads/2022/07/Whatsapp-Marketing-feature-2-1536x804.png";
                List card3Suggestions = new ArrayList();
                card3Suggestions.add(
                        new SuggestionHelper("Interested", "card_3"));

                cardContents.add(
                        new StandaloneCardHelper(
                                "WHATSAPP MARKETING",
                                "Whatsapp is one of the most used IM applications today. With millions of users, it is smart to plan for Whatsapp marketing for your business. We at Virtuoso are known for ur 85-95% of delivery rate.",
                                card3Image,
                                card3Suggestions)
                                .getCardContent(MediaHeight.SHORT)
                );

                // Send the carousel to the user
                rbmApiHelper.sendCarouselCards(cardContents, CardWidth.MEDIUM, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("Interested")) {

            try {
                // Create an instance of the RBM API helper
                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Inquire", "suggestion_1").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Inquire",
                        "Inquire about our bulk sms service",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Create an instance of the RBM API helper
                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Demo", "suggestion_2").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Demo",
                        "Booking demo for Pannel/UI",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Create an instance of the RBM API helper
                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Purchase", "suggestion_3").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Purchase",
                        "Purchasing our bulk SMS services",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("Inquire")) {

            try {
                // Create an instance of the RBM API helper
                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Bulk SMS Service", "suggestion_1").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Bulk SMS Service",
                        "Inquire about our bulk sms service",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("Demo")) {

            try {
                rbmApiHelper.sendTextMessage(
                        "Please enter the date and time (below mentioned format) you want the demo to be scheduled. DD-MM-YYYY&HH:MM",
                        senderPhoneNumber
                );

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("14-12-2023&11:12")) {
            try {
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                SuggestedAction suggestedAction = new SuggestedAction();
                DialAction dialAction = new DialAction();
                dialAction.setPhoneNumber("+15556667777");
                OpenUrlAction openUrlAction = new OpenUrlAction();
                openUrlAction.setUrl("https://virtuosonetsoft.com/sms24hours");
                SuggestedAction suggestedAction2 = new SuggestedAction();
                suggestedAction2.setText("Contact Us");
                suggestedAction2.setPostbackData("Contactus");
                suggestedAction2.setOpenUrlAction(openUrlAction);

                suggestedAction.setText("Call Us");
                suggestedAction.setPostbackData("Contact");
                suggestedAction.setDialAction(dialAction);
                Suggestion suggestion = new Suggestion();
                suggestion.setAction(suggestedAction);
                Suggestion suggestion2 = new Suggestion();
                suggestion2.setAction(suggestedAction2);
                suggestions.add(suggestion);
                suggestions.add(suggestion2);

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard5 = rbmApiHelper.createStandaloneCard(
                        "Contact Us",
                        "you can click 'Call Us' if you want to know your credits via call and you can click 'Contact Us if you want to know credit on our website.'",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard5, senderPhoneNumber);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (responseText.equalsIgnoreCase("Purchase")) {

            try {
                rbmApiHelper.sendTextMessage(
                        "Please tell which type of messages pack you want to opt??",
                        senderPhoneNumber
                );
                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Buy Trans SMS Pack", "Trans").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Transactional Messages",
                        "Transactional Messages",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Create an instance of the RBM API helper
                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Buy OTP SMS Pack", "OTP").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "OTP Messages",
                        "One Time Password Messages",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Create an instance of the RBM API helper
                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Buy Promo SMS Pack", "Promo").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Promotional Messages",
                        "Promotional Messages",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Create an instance of the RBM API helper
                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Buy All Pack", "All").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "All of three Promotionam,Transactional,Otp Messages",
                        "Promotionam,Transactional,Otp Messages for user with all needs",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("Buy Trans SMS Pack")) {

            try {
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                SuggestedAction suggestedAction = new SuggestedAction();
                DialAction dialAction = new DialAction();
                dialAction.setPhoneNumber("+15556667777");
                OpenUrlAction openUrlAction = new OpenUrlAction();
                openUrlAction.setUrl("https://virtuosonetsoft.com/sms24hours");
                SuggestedAction suggestedAction2 = new SuggestedAction();
                suggestedAction2.setText("Contact Us");
                suggestedAction2.setPostbackData("Contact_us");
                suggestedAction2.setOpenUrlAction(openUrlAction);

                suggestedAction.setText("Call Us");
                suggestedAction.setPostbackData("Call_Us");
                suggestedAction.setDialAction(dialAction);
                Suggestion suggestion = new Suggestion();
                suggestion.setAction(suggestedAction);
                Suggestion suggestion2 = new Suggestion();
                suggestion2.setAction(suggestedAction2);
                suggestions.add(suggestion);
                suggestions.add(suggestion2);

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard5 = rbmApiHelper.createStandaloneCard(
                        "Contact Us",
                        "You can press below,if you want to 'purchase Transactional pack by going through our website'.You can press below,if you want to 'purchase Transactional pack by calling us'",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard5, senderPhoneNumber);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("Bulk SMS Service")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Are You Existing User?",
                        senderPhoneNumber
                );

                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("YES", "yes").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "YES",
                        "Choose Yes if you are existing user",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
                List<Suggestion> suggestions1 = new ArrayList<Suggestion>();
                suggestions1.add(
                        new SuggestionHelper("NO", "no").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard1 = rbmApiHelper.createStandaloneCard(
                        "NO",
                        "Choose No if you are New user",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions1
                );

                rbmApiHelper.sendStandaloneCard(standaloneCard1, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if (responseText.equalsIgnoreCase("NO")) {
            try {
                 List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Create New Account", "New_Account").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Create New Account",
                        "Press below for creating new account",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);
           
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
             else if (responseText.equalsIgnoreCase("YES")) {
            try {
            rbmApiHelper.sendTextMessage(
                        "Please provide your account username and email 'in the following format:1111 & xyz@gmail.com' so that we can take you to your profile.",
                        senderPhoneNumber
                );
           
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
           else if (responseText.equalsIgnoreCase("Create New Account")) {
            try {
                    List<Suggestion> suggestions = new ArrayList<Suggestion>();
                SuggestedAction suggestedAction = new SuggestedAction();
                DialAction dialAction = new DialAction();
                dialAction.setPhoneNumber("+15556667777");
                OpenUrlAction openUrlAction = new OpenUrlAction();
                openUrlAction.setUrl("https://virtuosonetsoft.com/sms24hours");
                SuggestedAction suggestedAction2 = new SuggestedAction();
                suggestedAction2.setText("Contact Us");
                suggestedAction2.setPostbackData("Contact_us");
                suggestedAction2.setOpenUrlAction(openUrlAction);

                suggestedAction.setText("Call Us");
                suggestedAction.setPostbackData("Call_Us");
                suggestedAction.setDialAction(dialAction);
                Suggestion suggestion = new Suggestion();
                suggestion.setAction(suggestedAction);
                Suggestion suggestion2 = new Suggestion();
                suggestion2.setAction(suggestedAction2);
                suggestions.add(suggestion);
                suggestions.add(suggestion2);

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard5 = rbmApiHelper.createStandaloneCard(
                        "Contact Us",
                        "You can Call us by clicking on 'Call Us'for creating new account and can also contact us on our website for the same",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard5, senderPhoneNumber);
                
                 rbmApiHelper.sendTextMessage(
                        "After getting the call/Recevied details from your side, you'll an email from us with username & password in a sort time and after that you'll be able to login.",
                        senderPhoneNumber
                );
           
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        
        else if (responseText.equalsIgnoreCase("Create New Account")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Please provide your account username and email 'in the format:abc11 & xyz@gmail.com' so that we can take your profile",
                        senderPhoneNumber
                );

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("vns123 & vns@gmail.com")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Hello Chatbot welcome to your account what would you like to do now??",
                        senderPhoneNumber
                );

                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("Account Credits", "Credits").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Account Credits Information",
                        "You can click below to know your credit",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);

                List<Suggestion> suggestions1 = new ArrayList<Suggestion>();
                suggestions1.add(
                        new SuggestionHelper("Report Extraction", "Extraction").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard1 = rbmApiHelper.createStandaloneCard(
                        "Report Extraction",
                        "Press below to extract the report you need",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions1
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard1, senderPhoneNumber);

                List<Suggestion> suggestions2 = new ArrayList<Suggestion>();
                suggestions2.add(
                        new SuggestionHelper("Panel/UI Issues", "Extraction").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard2 = rbmApiHelper.createStandaloneCard(
                        "Issues with the Panel/UI",
                        "Press below to raise the issue with panel/UI",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions2
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard2, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("Panel/UI Issues")) {
            try {

                RbmApiHelper rbmApiHelper = new RbmApiHelper();

                // Create suggestions for chip list
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(
                        new SuggestionHelper("DLR Issue", "dlr").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                        "Issue In DLR",
                        "Press below if you have issue in DLR",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard, senderPhoneNumber);

                List<Suggestion> suggestions1 = new ArrayList<Suggestion>();
                suggestions1.add(
                        new SuggestionHelper("Template Issue", "Template").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard1 = rbmApiHelper.createStandaloneCard(
                        "Issue in  Template",
                        "Press below if you have issue in Template",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions1
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard1, senderPhoneNumber);

                List<Suggestion> suggestions2 = new ArrayList<Suggestion>();
                suggestions2.add(
                        new SuggestionHelper("Other Issue", "Other").getSuggestedReply());

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard2 = rbmApiHelper.createStandaloneCard(
                        "Other Issue",
                        "Press below if you have any other issue",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions2
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard2, senderPhoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (responseText.equalsIgnoreCase("Template Issue")) {

            try {

                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                SuggestedAction suggestedAction = new SuggestedAction();
                DialAction dialAction = new DialAction();
                dialAction.setPhoneNumber("+15556667777");
                OpenUrlAction openUrlAction = new OpenUrlAction();
                openUrlAction.setUrl("https://virtuosonetsoft.com/sms24hours");
                SuggestedAction suggestedAction2 = new SuggestedAction();
                suggestedAction2.setText("Contact Us");
                suggestedAction2.setPostbackData("Contactus");
                suggestedAction2.setOpenUrlAction(openUrlAction);

                suggestedAction.setText("Call Us");
                suggestedAction.setPostbackData("Contact");
                suggestedAction.setDialAction(dialAction);
                Suggestion suggestion = new Suggestion();
                suggestion.setAction(suggestedAction);
                Suggestion suggestion2 = new Suggestion();
                suggestion2.setAction(suggestedAction2);
                suggestions.add(suggestion);
                suggestions.add(suggestion2);

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard5 = rbmApiHelper.createStandaloneCard(
                        "Contact Us",
                        "Press below if you have any other issue",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard5, senderPhoneNumber);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (responseText.equalsIgnoreCase("Account Credits")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Please enter account name for information",
                        senderPhoneNumber
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if (responseText.equalsIgnoreCase("vns123")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Great,Our team will contact you in sort time or if you want contact us you can follow below mentioned details",
                        senderPhoneNumber
                );

                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                SuggestedAction suggestedAction = new SuggestedAction();
                DialAction dialAction = new DialAction();
                dialAction.setPhoneNumber("+15556667777");
                OpenUrlAction openUrlAction = new OpenUrlAction();
                openUrlAction.setUrl("https://virtuosonetsoft.com/sms24hours");
                SuggestedAction suggestedAction2 = new SuggestedAction();
                suggestedAction2.setText("Contact Us");
                suggestedAction2.setPostbackData("Contactus");
                suggestedAction2.setOpenUrlAction(openUrlAction);

                suggestedAction.setText("Call Us");
                suggestedAction.setPostbackData("Contact");
                suggestedAction.setDialAction(dialAction);
                Suggestion suggestion = new Suggestion();
                suggestion.setAction(suggestedAction);
                Suggestion suggestion2 = new Suggestion();
                suggestion2.setAction(suggestedAction2);
                suggestions.add(suggestion);
                suggestions.add(suggestion2);

                // Create a standalone rich card to send to the user
                StandaloneCard standaloneCard5 = rbmApiHelper.createStandaloneCard(
                        "Contact Us",
                        "you can click 'Call Us' if you want to know your credits via call and you can click 'Contact Us if you want to know credit on our website.'",
                        null,
                        MediaHeight.MEDIUM,
                        CardOrientation.VERTICAL,
                        suggestions
                );
                rbmApiHelper.sendStandaloneCard(standaloneCard5, senderPhoneNumber);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (responseText.equalsIgnoreCase("Report Extraction")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Please enter the date (in below format) of what you want to extract the report. DD-MM-YYYY",
                        senderPhoneNumber
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (responseText.equalsIgnoreCase("14-12-2023")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Please enter the your email-id so that we can send you the report.",
                        senderPhoneNumber
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (responseText.equalsIgnoreCase("test@gmail.com")) {
            try {
                rbmApiHelper.sendTextMessage(
                        "Great Our team will shere report as soon as possible at your email-id",
                        senderPhoneNumber
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            rbmApiHelper.sendIsTypingMessage(senderPhoneNumber);

        }

    }

    /**
     * Sends a user an invite to test this agent.
     */
    /**
     * Sends the initial greeting of "What is your favorite color?" to the user.
     */
    // [START run_application]
    public static void main(String[] args) {
//        if (args.length != 2 && args.length != 3) {
//            logger.info("Usage: mvn exec:java " +
//                    "-Dexec.args=\"<PHONE E.164> <MODE>\"");
//
//            System.exit(-1);
//        }

        

        try {

            // create agent
            PULL_Receiver firstAgent = new PULL_Receiver();
          
            while (true) {
                Thread.sleep(Long.MAX_VALUE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // [END run_application]

}
