package com.soil.mositure.IOT.services.impl;

import com.soil.mositure.IOT.communicationServices.telegram.NotificationServiceTelegram;
import com.soil.mositure.IOT.constants.EnumValues;
import com.soil.mositure.IOT.model.PropertyDataModel;
import com.soil.mositure.IOT.services.MoistureService;
import com.soil.mositure.IOT.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoistureServiceImpl implements MoistureService {

    @Autowired
    PropertyService propertyService;
    @Override
    public EnumValues.alertStatus alertService(float moistureValue) {
        PropertyDataModel property=propertyService.getProperty();
        int moisture_limit=property.getMoisture_alert_limit();
        if(moistureValue>=moisture_limit)
            return EnumValues.alertStatus.ALERT_NOT_NEEDED;
        else if(moistureValue<0)
            return EnumValues.alertStatus.MOISTURE_VALUE_ERROR;
        else if(moistureValue>=0&&moistureValue<moisture_limit)
        return EnumValues.alertStatus.ALERT_NEEDED;
            else
        return EnumValues.alertStatus.ALERT_CANT_BE_PROCESSED;
    }
}
