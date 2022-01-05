package com.soil.mositure.IOT.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_notification_service_table")
public class UserNotificationModel {

    @Id
    private int user_id;
    private String phone_num;
    private String telegram_id;

    public UserNotificationModel(String phone_num, String telegram_id) {
        this.phone_num = phone_num;
        this.telegram_id = telegram_id;
    }

    public UserNotificationModel(int user_id, String phone_num, String telegram_id) {
        this.user_id = user_id;
        this.phone_num = phone_num;
        this.telegram_id = telegram_id;
    }

    public UserNotificationModel() {
    }

    public UserNotificationModel(String phone_num) {
        this.phone_num = phone_num;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getTelegram_id() {
        return telegram_id;
    }

    public void setTelegram_id(String telegram_id) {
        this.telegram_id = telegram_id;
    }
}
