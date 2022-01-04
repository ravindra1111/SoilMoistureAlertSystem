package com.soil.mositure.IOT.services;

import com.soil.mositure.IOT.constants.EnumValues;

public interface MoistureService {

    EnumValues.alertStatus alertService(float moistureValue);
}
