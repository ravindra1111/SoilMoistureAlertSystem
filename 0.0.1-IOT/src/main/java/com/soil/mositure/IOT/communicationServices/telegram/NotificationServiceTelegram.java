package com.soil.mositure.IOT.communicationServices.telegram;

import java.io.IOException;

public interface NotificationServiceTelegram {

    void sendTelegramAlertMessage(float moistureValue) throws IOException;
    void sendTelegramSilentMessage(float moistureValue) throws IOException;
}
