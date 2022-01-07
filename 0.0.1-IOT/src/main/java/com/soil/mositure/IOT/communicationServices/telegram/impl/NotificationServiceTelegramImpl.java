package com.soil.mositure.IOT.communicationServices.telegram.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.soil.mositure.IOT.communicationServices.telegram.NotificationServiceTelegram;
import com.soil.mositure.IOT.model.PropertyDataModel;
import com.soil.mositure.IOT.model.UserNotificationModel;
import com.soil.mositure.IOT.repository.PropertyDataRepository;
import com.soil.mositure.IOT.repository.UserNotificationRepository;
import com.soil.mositure.IOT.services.UserPhoneService;
import org.apache.catalina.User;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceTelegramImpl implements NotificationServiceTelegram {

    @Autowired
    UserPhoneService userPhoneService;
    @Autowired
    PropertyDataRepository propertyDataRepository;
    @Autowired
    UserNotificationRepository notifRepo;

    @Override
    public void sendTelegramAlertMessage(float moistureValue,int moistureLimit) throws IOException {
        HashMap<String,String> enabled_user_chatIds=userPhoneService.getEnabledUsersForNotificationServices();
        String sendMessageURL="https://api.telegram.org/bot5027203227:AAEEYS5DwRTSTVVKzOv9A8dU2dAYIOcknys/sendMessage";
        String actual_message = "!!!ALERT SERVICE!!!\n Moisture Level is " + moistureValue + " % \n Should not be below "+moistureLimit+" percent";
        for(Map.Entry<String,String> user_data:enabled_user_chatIds.entrySet()) {
            AsyncHttpClient client = new DefaultAsyncHttpClient();
             String body = "{\n" +
                    "    \"chat_id\":"+user_data.getValue()+",\n" +
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
    public void sendTelegramSilentMessage(float moistureValue,int moistureLimit) throws IOException {
        HashMap<String,String> enabled_user_chatIds=userPhoneService.getEnabledUsersForNotificationServices();
        String sendMessageURL="https://api.telegram.org/bot5027203227:AAEEYS5DwRTSTVVKzOv9A8dU2dAYIOcknys/sendMessage";
        String actual_message="!NOTIFICATION SERVICE! \n Moisture Level is "+moistureValue+" % \n Minimum moisture level is maintained";
        for(Map.Entry<String,String> user_data:enabled_user_chatIds.entrySet()) {
            AsyncHttpClient client = new DefaultAsyncHttpClient();
             String body = "{\n" +
                     "    \"chat_id\":"+user_data.getValue()+",\n" +
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

    @Override
    public void sendTelegramDeviceConnectedMessage() {
        List<UserNotificationModel> user_data=notifRepo.findAll();
        PropertyDataModel prop=propertyDataRepository.getById(1);
        TelegramBot bot=new TelegramBot.Builder("5027203227:AAEEYS5DwRTSTVVKzOv9A8dU2dAYIOcknys").build();
        String message="!!!SERVICE MESSAGE!!!\n\nDevice Connected\n\nMeasurement Time interval: "+prop.getMoisture_measure_frequency()+" minutes";
        String property_table="\n\n Application properties \n";
        property_table+=" Alert Service Status :" +prop.isAlert_service_status()+"\n"
                +" Data Insert Service Status :"+prop.isData_insertion_status()+"\n"
                +" Moisture Limit :"+prop.getMoisture_alert_limit()+"\n"
                +" Data Measure Frequency :"+prop.getMoisture_measure_frequency();
        for(UserNotificationModel user:user_data) {
            SendMessage ack_message_req = new SendMessage(user.getTelegram_id(), message);
            bot.execute(ack_message_req);
        }
    }
}
