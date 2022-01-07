package com.soil.mositure.IOT.communicationServices.impl;

import com.soil.mositure.IOT.communicationServices.NotificationServices;
import com.soil.mositure.IOT.communicationServices.telegram.NotificationServiceTelegram;
import com.soil.mositure.IOT.constants.EnumValues;
import com.soil.mositure.IOT.model.PropertyDataModel;
import com.soil.mositure.IOT.repository.PropertyDataRepository;
import com.soil.mositure.IOT.services.MoistureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotificationServiceImpl implements NotificationServices {

    @Autowired
    NotificationServiceTelegram telegramNotifService;
    @Autowired
    MoistureService moistureService;
    @Autowired
    PropertyDataRepository propertyDataRepository;

    @Override
    public boolean sendNotifications(float moistureValue,int moistureLimit) throws IOException {
        PropertyDataModel property = propertyDataRepository.getById(1);
        if (property.isAlert_service_status()) {
            EnumValues.alertStatus val = moistureService.alertService(moistureValue);
            if (val.equals(EnumValues.alertStatus.ALERT_NEEDED) || (val.equals(EnumValues.alertStatus.ALERT_PROCESSING)))
                telegramNotifService.sendTelegramAlertMessage(moistureValue,moistureLimit);
            else
                telegramNotifService.sendTelegramSilentMessage(moistureValue,moistureLimit);
            return true;
        }
        else
            return false;
    }

    @Override
    public void sendAckNotifications() {
        telegramNotifService.sendTelegramDeviceConnectedMessage();
    }
}
