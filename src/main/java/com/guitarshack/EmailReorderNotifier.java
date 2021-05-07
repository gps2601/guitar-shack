package com.guitarshack;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

public class EmailReorderNotifier implements ReorderNotifier {

    private MailjetClient client;

    public EmailReorderNotifier(MailjetClient client) {
        this.client = client;
    }

    @Override
    public void send(String notification) {
        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", System.getenv("EMAIL_ADDRESS"))
                                        .put("Name", "Warehouse"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", System.getenv("EMAIL_ADDRESS"))
                                                .put("Name", "Guitar Shack Manager")))
                                .put(Emailv31.Message.SUBJECT, "Guitar Shack Restock Notification.")
                                .put(Emailv31.Message.TEXTPART, notification)
                                .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));

        try {
            client.post(request);
        } catch (MailjetException e) {
            e.printStackTrace();
        } catch (MailjetSocketTimeoutException e) {
            e.printStackTrace();
        }
    }
}
