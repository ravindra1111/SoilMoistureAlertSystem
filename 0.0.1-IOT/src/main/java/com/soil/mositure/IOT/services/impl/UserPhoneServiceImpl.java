package com.soil.mositure.IOT.services.impl;

import com.soil.mositure.IOT.model.UserNotificationModel;
import com.soil.mositure.IOT.repository.UserNotificationRepository;
import com.soil.mositure.IOT.services.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
            if(String.valueOf(user_data.getPhone_num()).equalsIgnoreCase(phone_num))
            {
                userNotificationRepository.deleteById(user_data.getUser_id());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean disableUserByPhoneNumber(String phone_num) {
        List<UserNotificationModel> users_data=userNotificationRepository.findAll();
        for(UserNotificationModel user_data:users_data){
            if(String.valueOf(user_data.getPhone_num()).equalsIgnoreCase(phone_num))
            {
                user_data.setUser_enabled(false);
                userNotificationRepository.save(user_data);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean enableUserByPhoneNumber(String phone_num) {
        List<UserNotificationModel> users_data=userNotificationRepository.findAll();
        for(UserNotificationModel user_data:users_data){
            if(String.valueOf(user_data.getPhone_num()).equalsIgnoreCase(phone_num))
            {
                user_data.setUser_enabled(true);
                userNotificationRepository.save(user_data);
                return true;
            }
        }
        return false;
    }

    @Override
    public HashMap<String, String> getEnabledUsersForNotificationServices() {
        List<UserNotificationModel> users_data=userNotificationRepository.findAll();
        HashMap<String,String> notificationServiceEnabledUsers=new HashMap<String,String>();
        for(UserNotificationModel user_data:users_data){
            if(user_data.isUser_enabled()){
                notificationServiceEnabledUsers.put(user_data.getPhone_num(),user_data.getTelegram_id());
            }
        }
        return notificationServiceEnabledUsers;
    }
}
