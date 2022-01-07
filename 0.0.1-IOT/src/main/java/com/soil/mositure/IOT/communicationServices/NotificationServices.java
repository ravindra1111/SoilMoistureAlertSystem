package com.soil.mositure.IOT.communicationServices;

import java.io.IOException;

public interface NotificationServices {

    boolean sendNotifications(float moistureValue,int moistureLimit) throws IOException;
    void sendAckNotifications();
}
