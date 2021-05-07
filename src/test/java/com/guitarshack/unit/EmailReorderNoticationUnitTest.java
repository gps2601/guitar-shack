package com.guitarshack.unit;

import com.guitarshack.EmailReorderNotifier;
import com.guitarshack.ReorderNotifier;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmailReorderNoticationUnitTest {
    @Test
    void emailReorderNotifcationSent() throws MailjetSocketTimeoutException, MailjetException {
        MailjetClient client = mock(MailjetClient.class);
        ReorderNotifier reoderNotifier = new EmailReorderNotifier(client);


        String notification = "Test Notification";
        reoderNotifier.send(notification);

        verify(client).post(any());
    }
}
