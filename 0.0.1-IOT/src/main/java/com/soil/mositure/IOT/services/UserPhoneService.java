package com.soil.mositure.IOT.services;

import java.util.HashMap;
import java.util.List;

public interface UserPhoneService {

    List<String> getAllPhoneNumbers();
    List<String> getAllTelegramChatIds();
    boolean deleteUserByPhoneNumber(String phone_num);
    boolean disableUserByPhoneNumber(String phone_num);
    boolean enableUserByPhoneNumber(String phone_num);
    HashMap<String,String> getEnabledUsersForNotificationServices();
}
