package com.soil.mositure.IOT.model;

import javax.persistence.*;

@Entity
@Table(name="user_notification_service_table")
public class UserNotificationModel {

    @Id
    @GeneratedValue
    private int user_id;
    @Column(unique = true)
    private String phone_num;
    @Column(unique = true)
    private String telegram_id;
    private boolean user_enabled=true;

    public UserNotificationModel(String phone_num, String telegram_id) {
        this.phone_num = phone_num;
        this.telegram_id = telegram_id;
        this.user_enabled=true;
    }


    public UserNotificationModel(int user_id, String phone_num, String telegram_id, boolean user_enabled) {
        this.user_id = user_id;
        this.phone_num = phone_num;
        this.telegram_id = telegram_id;
        this.user_enabled = user_enabled;
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

    public boolean isUser_enabled() {
        return user_enabled;
    }

    public void setUser_enabled(boolean user_enabled) {
        this.user_enabled = user_enabled;
    }
}
