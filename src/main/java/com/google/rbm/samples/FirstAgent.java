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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.rbm.samples.lib.RbmApiHelper;
import com.google.rbm.samples.lib.SuggestionHelper;
import com.google.rbm.samples.lib.cards.CardOrientation;
import com.google.rbm.samples.lib.cards.MediaHeight;
import db.dbcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
public class FirstAgent {

    private static final Logger logger = Logger.getLogger(FirstAgent.class.getName());

    private static final String EXCEPTION_WAS_THROWN = "an exception was thrown";

    private static final String PUB_SUB_NAME = "rbm-agent-subscription";

    private Subscriber subscriber;

    private RCSBusinessMessaging.Builder builder;

    private RbmApiHelper rbmApiHelper;
    private String msisdn;

  

  


    private void sendTesterInvite() {
        try {
            rbmApiHelper.registerTester(msisdn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendGreeting(String msisdn) {
        try {
            this.msisdn=msisdn;
           // rbmApiHelper.sendTextMessage("What is your favorite color?",
                 //   msisdn);

            List<Suggestion> suggestions = new ArrayList<Suggestion>();
            suggestions.add(
                    new SuggestionHelper("1.Product List", "suggestion_1").getSuggestedReply());

            OpenUrlAction openUrlAction = new OpenUrlAction();
            openUrlAction.setUrl("https://virtuosonetsoft.com/career");

            // creating a suggested action based on an open url action
            SuggestedAction suggestedAction = new SuggestedAction();
            suggestedAction.setText("2.Vacancies");
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
    public static void main(String[] args) {

        try {
            // String msisdn = args[0];
            String msisdn = "+919779072900";
            String mode = "chat";
            String fileUrl = "http://www.google.com/logos/doodles/2015/googles-new-logo-5078286822539264.3-hp2x.gif";
            if (args.length > 1) {
                mode = args[1];
            }
            if (args.length > 0) {
                msisdn = args[0];
            }
    
            // create agent
            FirstAgent firstAgent = new FirstAgent();

            if (mode.equals("chat")) {
                
                firstAgent.sendfile(fileUrl,msisdn);

            } else {
                // send tester invite to user
                firstAgent.sendTesterInvite();

                logger.info("Tester invite sent to " + msisdn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // [END run_application]

    private void sendfile(String fileUrl,String msisdn) {
        try {
    this .msisdn=msisdn;
              List<Suggestion> suggestions = new ArrayList<Suggestion>();
            suggestions.add(
                    new SuggestionHelper("1.Product List", "suggestion_1").getSuggestedReply());

            OpenUrlAction openUrlAction = new OpenUrlAction();
            openUrlAction.setUrl("https://virtuosonetsoft.com/career");

            // creating a suggested action based on an open url action
            SuggestedAction suggestedAction = new SuggestedAction();
            suggestedAction.setText("2.Vacancies");
            suggestedAction.setPostbackData("suggestion_2");
            suggestedAction.setOpenUrlAction(openUrlAction);
            if(rbmApiHelper==null){
                this.rbmApiHelper=new RbmApiHelper();
            }
 
            // attaching action to a suggestion
            Suggestion suggestion = new Suggestion();
            suggestion.setAction(suggestedAction);
            suggestions.add(suggestion);
            

            StandaloneCard standaloneCard = rbmApiHelper.createStandaloneCard(
                    "Virtuoso Netsoft",
                    "Welcome To Virtuoso Netsoft!!",
                    fileUrl,
                    MediaHeight.MEDIUM,
                    CardOrientation.VERTICAL,
                    suggestions
            );
            
            rbmApiHelper.uploadFile(fileUrl);
            rbmApiHelper.sendStandaloneCard(standaloneCard, msisdn);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
