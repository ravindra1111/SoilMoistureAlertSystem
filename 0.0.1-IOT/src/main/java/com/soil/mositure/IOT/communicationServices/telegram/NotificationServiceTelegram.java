package com.soil.mositure.IOT.communicationServices.telegram;

import java.io.IOException;

public interface NotificationServiceTelegram {

    void sendTelegramAlertMessage(float moistureValue,int moistureLimit) throws IOException;
    void sendTelegramSilentMessage(float moistureValue,int moistureLimit) throws IOException;
    void sendTelegramDeviceConnectedMessage();
}
