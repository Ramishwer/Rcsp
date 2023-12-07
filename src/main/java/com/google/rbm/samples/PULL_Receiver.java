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
import com.google.rbm.samples.lib.cards.CardOrientation;
import com.google.rbm.samples.lib.cards.MediaHeight;
import db.dbcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

               JSONObject jobj=new JSONObject(jsonResponse); 
               JSONObject jsonMap=new JSONObject();
               if(jobj.has("suggestionResponse")){
                   jsonMap=jobj.getJSONObject("suggestionResponse");
               }else if(jobj.has("status")){
                   jsonMap=jobj.getJSONObject("status");
               }else{
                   jsonMap=jobj;
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
              String fileUrl="http://reports.sms24hours.com:8021/RPR/img/images.jpg";
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
