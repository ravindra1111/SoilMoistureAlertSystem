package com.soil.mositure.IOT.controller;

import com.soil.mositure.IOT.communicationServices.NotificationServices;
import com.soil.mositure.IOT.communicationServices.telegram.NotificationServiceTelegram;
import com.soil.mositure.IOT.constants.EnumValues;
import com.soil.mositure.IOT.model.MoistureDataModel;
import com.soil.mositure.IOT.model.PropertyDataModel;
import com.soil.mositure.IOT.model.UserNotificationModel;
import com.soil.mositure.IOT.repository.MoistureDataRepository;
import com.soil.mositure.IOT.repository.PropertyDataRepository;
import com.soil.mositure.IOT.repository.UserNotificationRepository;
import com.soil.mositure.IOT.services.MoistureService;
import com.soil.mositure.IOT.services.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Controller
@RestController
public class MoistureSystemController {

    @Autowired
    MoistureDataRepository moisture_repository;
    @Autowired
    MoistureService moistureService;
    @Autowired
    NotificationServices notificationServices;
    @Autowired
    PropertyDataRepository propertyDataRepository;
    @Autowired
    UserNotificationRepository userNotificationRepository;
    @Autowired
    UserPhoneService userPhoneService;

    @GetMapping("/insertmoisturedata/{moisturevalue}")
    public MoistureDataModel insertMoistureData(@PathVariable("moisturevalue") float moistureValue) throws IOException {
        String alert_status= String.valueOf(moistureService.alertService(moistureValue));
        boolean messageSent=notificationServices.sendNotifications(moistureValue);

        PropertyDataModel property=propertyDataRepository.getById(1);
        MoistureDataModel data = new MoistureDataModel(moistureValue, alert_status);

        if(messageSent)
            data.setAlert_status(String.valueOf(EnumValues.alertStatus.ALERT_SENT));

        if(property.isData_insertion_status()) {
            return moisture_repository.save(data);
        }
        else
            return data;
    }

    @GetMapping("/adduser/{phonenumber}/{telegramchat_id}")
    public String addUserForNotifications(@PathVariable("phonenumber") String phone_num,
                                           @PathVariable("telegramchat_id") String telegramChat_id)
    {
        UserNotificationModel user_data=userNotificationRepository.save(new UserNotificationModel(phone_num,telegramChat_id));
        if(user_data.getPhone_num()==phone_num||user_data.getTelegram_id()==telegramChat_id)
            return "User added for notification services";
        else
            return "User not added for notification services";
    }

    @DeleteMapping("/deleteuser/{phonenumber}")
    public String deleteUserFromNotificationService(@PathVariable("phonenumber") String phone_num){
        if(userPhoneService.deleteUserByPhoneNumber(phone_num))
            return "User deleted from notification services";
        else
            return "Looks like user do not exists";
    }

    @GetMapping("/enablealertservice")
    public String enableAlertServices(){
        PropertyDataModel property=propertyDataRepository.getById(1);
        property.setAlert_service_status(true);
        propertyDataRepository.save(property);
        if(propertyDataRepository.getById(1).isAlert_service_status())
            return "Alert Service Started";
        else
            return "Alert Service Not Started";
    }

    @GetMapping("/disablealertservice")
    public String disableAlertServices(){
        PropertyDataModel property=propertyDataRepository.getById(1);
        property.setAlert_service_status(false);
        propertyDataRepository.save(property);
        if(propertyDataRepository.getById(1).isAlert_service_status())
            return "Alert Service Started";
        else
            return "Alert Service Not Started";
    }

}
