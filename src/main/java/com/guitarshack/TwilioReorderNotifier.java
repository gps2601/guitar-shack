package com.guitarshack;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioReorderNotifier implements ReorderNotifier {
    public static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    @Override
    public void send(String notification) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(System.getenv("TO_PHONE_NUMBER")),
                new com.twilio.type.PhoneNumber(System.getenv("FROM_PHONE_NUMBER")),
                notification)
                .create();

        System.out.println("Twilio Message SID: " + message.getSid());
    }
}
