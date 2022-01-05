package com.soil.mositure.IOT.communicationServices.telegram.impl;

import com.soil.mositure.IOT.communicationServices.telegram.NotificationServiceTelegram;
import com.soil.mositure.IOT.services.UserPhoneService;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NotificationServiceTelegramImpl implements NotificationServiceTelegram {

    @Autowired
    UserPhoneService userPhoneService;
    @Override
    public void sendTelegramAlertMessage(float moistureValue) throws IOException {

        List<String> user_chatIds=userPhoneService.getAllTelegramChatIds();
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        String sendMessageURL="https://api.telegram.org/bot5027203227:AAEEYS5DwRTSTVVKzOv9A8dU2dAYIOcknys/sendMessage";
        String actual_message = "ALERT!\n Moisture Level is " + moistureValue + " % \n Should not be below 20 percent";
        for(String chat_id:user_chatIds) {
             String body = "{\n" +
                    "    \"chat_id\":"+chat_id+",\n" +
                    "    \"text\":\""+actual_message+"\"\n" +
                    "}";
            client.prepare("POST", sendMessageURL)
                    .setHeader("Accept", "application/json")
                    .setHeader("User-Agent", "Moisture Notifier System-Telegram Bot")
                    .setHeader("Content-Type", "application/json")
                    .setBody(body)
                    .execute()
                    .toCompletableFuture()
                    .join();

            client.close();
        }
    }

    @Override
    public void sendTelegramSilentMessage(float moistureValue) throws IOException {
        List<String> user_chatIds=userPhoneService.getAllTelegramChatIds();
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        String sendMessageURL="https://api.telegram.org/bot5027203227:AAEEYS5DwRTSTVVKzOv9A8dU2dAYIOcknys/sendMessage";
        String actual_message="Moisture Level is "+moistureValue+" % \n Minimum moisture level is maintained";
        for(String chat_id:user_chatIds) {
             String body = "{\n" +
                     "    \"chat_id\":"+chat_id+",\n" +
                     "    \"text\":\""+actual_message+"\",\n" +
                     "    \"disable_notification\":true\n" +
                     "}";
            client.prepare("POST", sendMessageURL)
                    .setHeader("Accept", "application/json")
                    .setHeader("User-Agent", "Moisture Notifier System-Telegram Bot")
                    .setHeader("Content-Type", "application/json")
                    .setBody(body)
                    .execute()
                    .toCompletableFuture()
                    .join();

            client.close();
        }
    }
}
