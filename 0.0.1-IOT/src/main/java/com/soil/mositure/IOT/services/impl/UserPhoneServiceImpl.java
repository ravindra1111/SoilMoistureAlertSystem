package com.soil.mositure.IOT.services.impl;

import com.soil.mositure.IOT.model.UserNotificationModel;
import com.soil.mositure.IOT.repository.UserNotificationRepository;
import com.soil.mositure.IOT.services.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPhoneServiceImpl implements UserPhoneService {

    @Autowired
    UserNotificationRepository userNotificationRepository;
    @Override
    public List<String> getAllPhoneNumbers() {
        List<String> phoneNumbers=new ArrayList<String>();
        List<UserNotificationModel> table_data=userNotificationRepository.findAll();
        for(UserNotificationModel user_data:table_data){
            phoneNumbers.add(user_data.getPhone_num());
        }
        return phoneNumbers;
    }

    @Override
    public List<String> getAllTelegramChatIds() {
        List<String> telegramChatIds=new ArrayList<String>();
        List<UserNotificationModel> table_data=userNotificationRepository.findAll();
        for(UserNotificationModel user_data:table_data){
            telegramChatIds.add(user_data.getTelegram_id());
        }
        return telegramChatIds;
    }

    @Override
    public boolean deleteUserByPhoneNumber(String phone_num) {
        List<UserNotificationModel> users_data=userNotificationRepository.findAll();
        for(UserNotificationModel user_data:users_data){
            userNotificationRepository.deleteById(user_data.getUser_id());
            return true;
        }
        return false;
    }
}
