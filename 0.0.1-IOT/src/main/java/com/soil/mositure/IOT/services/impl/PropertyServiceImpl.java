package com.soil.mositure.IOT.services.impl;

import com.soil.mositure.IOT.model.PropertyDataModel;
import com.soil.mositure.IOT.repository.PropertyDataRepository;
import com.soil.mositure.IOT.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyDataRepository propertyDataRepository;

    @Override
    public PropertyDataModel getProperty() {
        return propertyDataRepository.getById(1);
    }
}
