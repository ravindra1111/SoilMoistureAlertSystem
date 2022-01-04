package com.soil.mositure.IOT.services.impl;

import com.soil.mositure.IOT.constants.EnumValues;
import com.soil.mositure.IOT.services.MoistureService;
import org.springframework.stereotype.Service;

@Service
public class MoistureServiceImpl implements MoistureService {
    @Override
    public EnumValues.alertStatus alertService(float moistureValue) {
        if(moistureValue>=20)
            return EnumValues.alertStatus.ALERT_NOT_NEEDED;
        else if(moistureValue<0)
            return EnumValues.alertStatus.MOISTURE_VALUE_ERROR;
        else if(moistureValue<20&&moistureValue>=0)
            return EnumValues.alertStatus.ALERT_PROCESSING;
        else
        return EnumValues.alertStatus.ALERT_NEEDED;
    }
}
