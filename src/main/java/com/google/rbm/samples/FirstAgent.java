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
import com.google.api.services.rcsbusinessmessaging.v1.RCSBusinessMessaging;
import com.google.api.services.rcsbusinessmessaging.v1.model.CreateCalendarEventAction;
import com.google.api.services.rcsbusinessmessaging.v1.model.OpenUrlAction;
import com.google.api.services.rcsbusinessmessaging.v1.model.StandaloneCard;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.rbm.samples.lib.StandaloneCardHelper;
import com.google.rbm.samples.lib.SuggestionHelper;
import com.google.rbm.samples.lib.cards.CardOrientation;
import com.google.rbm.samples.lib.cards.CardWidth;
import com.google.rbm.samples.lib.cards.MediaHeight;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.api.services.rcsbusinessmessaging.v1.model.SuggestedAction;
import com.google.api.services.rcsbusinessmessaging.v1.model.Suggestion;
import com.google.rbm.samples.lib.RbmApiHelper;
import static java.lang.System.out;
// [END import_libraries]

/**
 * RCS Business Messaging sample first agent.
 * <p>
 * Sends the following message to a user: "What is your favorite color?" Parses
 * the user's response and echos it in a new message.
 */
public class FirstAgent {

    private static final Logger logger = Logger.getLogger(FirstAgent.class.getName());

    private static final String EXCEPTION_WAS_THROWN = "an exception was thrown";

    private static final String PUB_SUB_NAME = "rbm-agent-subscription";

    private Subscriber subscriber;

    private RCSBusinessMessaging.Builder builder;

    private RbmApiHelper rbmApiHelper;
    private String msisdn;

    public FirstAgent(String msisdn) {
        this.msisdn = msisdn;

        this.rbmApiHelper = new RbmApiHelper();
    }

    public void sendTesterInvite() {
        try {
            rbmApiHelper.registerTester(msisdn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendGreeting(String url, String s1, String s2) {
        try {
            this.msisdn = msisdn;
            // rbmApiHelper.sendTextMessage("What is your favorite color?",
            //   msisdn);

            List<Suggestion> suggestions = new ArrayList<Suggestion>();
            suggestions.add(
                    new SuggestionHelper(s1, "suggestion_1").getSuggestedReply());

            OpenUrlAction openUrlAction = new OpenUrlAction();
            openUrlAction.setUrl(url);

            // creating a suggested action based on an open url action
            SuggestedAction suggestedAction = new SuggestedAction();
            suggestedAction.setText(s2);
            suggestedAction.setPostbackData("suggestion_2");
            suggestedAction.setOpenUrlAction(openUrlAction);

            // attaching action to a suggestion
            Suggestion suggestion = new Suggestion();
            suggestion.setAction(suggestedAction);
            suggestions.add(suggestion);
            rbmApiHelper.sendTextMessage("Welcome To Virtuoso Netsoft \n How do we help", msisdn, suggestions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // [START run_application]
//    public static void main(String[] args) {
//
//        try {
//            // String msisdn = args[0];
//            String msisdn = "+919779072900";
//            String mode = "chat";
//            String fileUrl = "http://reports.sms24hours.com:8021/RPR/img/images.jpg";
//            if (args.length > 1) {
//                mode = args[1];
//            }
//            if (args.length > 0) {
//                msisdn = args[0];
//            }
//
//            // create agent
//            FirstAgent firstAgent = new FirstAgent(msisdn);
//
//            if (mode.equals("chat")) {
//
//              
//
//            } else {
//                // send tester invite to user
//                firstAgent.sendTesterInvite();
//
//                logger.info("Tester invite sent to " + msisdn);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    // [END run_application]
    public void richCard(String fileUrl, String s1, String s2, String title, String description) {
        try {
            // Create an instance of the RBM API helper
            RbmApiHelper rbmApiHelper = new RbmApiHelper();

            // Create suggestions for chip list
            List<Suggestion> suggestions = new ArrayList<Suggestion>();
            suggestions.add(
                    new SuggestionHelper(s1, "suggestion_1").getSuggestedReply());

            suggestions.add(
                    new SuggestionHelper(s2, "suggestion_2").getSuggestedReply());

          

            // Create a standalone rich card to send to the user
            StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                   title,
                    description,
                    fileUrl,
                    MediaHeight.MEDIUM,
                    CardOrientation.VERTICAL,
                    suggestions
            );

            rbmApiHelper.sendStandaloneCard(standaloneCard, msisdn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RichCardCcarousels(String c1, String ctitle1, String d1, String card1Image1, String c2, String ctitle2, String d2, String card2Image2, String c3, String ctitle3, String d3, String card3Image3) {

        try {
            // Create an instance of the RBM API helper
            RbmApiHelper rbmApiHelper = new RbmApiHelper();

            List cardContents = new ArrayList();

            // Images for the carousel cards
            // Create suggestions for first carousel card
            List card1Suggestions = new ArrayList();
            card1Suggestions.add(
                    new SuggestionHelper(c1, "card_1"));

            cardContents.add(
                    new StandaloneCardHelper(
                            ctitle1,
                            d1,
                            card1Image1,
                            card1Suggestions)
                            .getCardContent(MediaHeight.SHORT)
            );

            // Images for the carousel cards
            // Create suggestions for second carousel card
            List card2Suggestions = new ArrayList();
            card2Suggestions.add(
                    new SuggestionHelper(c2, "card_2"));

            cardContents.add(
                    new StandaloneCardHelper(
                            ctitle2,
                            d2,
                            card2Image2,
                            card2Suggestions)
                            .getCardContent(MediaHeight.SHORT)
            );

            // Create suggestions for second carousel card
            List card3Suggestions = new ArrayList();
            card3Suggestions.add(
                    new SuggestionHelper(c3, "card_3"));

            cardContents.add(
                    new StandaloneCardHelper(
                            ctitle3,
                            d3,
                            card3Image3,
                            card3Suggestions)
                            .getCardContent(MediaHeight.SHORT)
            );

            // Send the carousel to the user
            rbmApiHelper.sendCarouselCards(cardContents, CardWidth.MEDIUM, msisdn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int textMessage(String text) {

        try {
            // Create an instance of the RBM API helper
            RbmApiHelper rbmApiHelper = new RbmApiHelper();

            // Send simple text message to user
            rbmApiHelper.sendTextMessage(
                    text,
                    msisdn
            );
            
            System.out.println("rcs msg "+rbmApiHelper);
        } catch (Exception e) {
            e.printStackTrace();
        }
return 1;
    }
    
    
  public void createCalendarEvent(String title,String description,String startTime,String endTime,String text,String MessageText) {
   try {
   // Create an instance of the RBM API helper
   RbmApiHelper rbmApiHelper = new RbmApiHelper();

   // Create suggestions for chip list
   List<Suggestion> suggestions = new ArrayList<Suggestion>();

   // creating a create calendar event suggested action
  CreateCalendarEventAction createCalendarEventAction = new CreateCalendarEventAction();
   createCalendarEventAction.setTitle(title);
   createCalendarEventAction.setDescription(description);
   createCalendarEventAction.setStartTime(startTime);
   createCalendarEventAction.setEndTime(endTime);

   // creating a suggested action based on a create calendar event action
   SuggestedAction suggestedAction = new SuggestedAction();
   suggestedAction.setText(text);
   suggestedAction.setPostbackData("postback_data_1234");
   suggestedAction.setCreateCalendarEventAction(createCalendarEventAction);

   // attaching action to a suggestion
   Suggestion suggestion = new Suggestion();
   suggestion.setAction(suggestedAction);

   suggestions.add(suggestion);

   // Send simple text message with the suggestion action
   rbmApiHelper.sendTextMessage(
      MessageText,
      msisdn,
      suggestions
   );
} catch(Exception e) {
   e.printStackTrace();
}

   
  
}
  
   public static void main(String[] args) {
        FirstAgent fa=new FirstAgent("+916392100079");
        fa.textMessage("gdfgg");
        String title="sdfdsf"; 
       String MessageText="ftjtfhdggdggfdhfhf";
       String description="hfdhgfdghf";
       String startTime="2020-06-30T19:00:00Z";
       String endTime="2020-06-30T20:00:00Z";
       String text="sdatetetetetegfg";
       fa.createCalendarEvent(title, description, startTime, endTime, text, MessageText);
            
     
    }

}
