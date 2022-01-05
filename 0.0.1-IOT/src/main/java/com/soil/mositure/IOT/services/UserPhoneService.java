package com.soil.mositure.IOT.services;

import java.util.List;

public interface UserPhoneService {

    List<String> getAllPhoneNumbers();
    List<String> getAllTelegramChatIds();
    boolean deleteUserByPhoneNumber(String phone_num);
}
