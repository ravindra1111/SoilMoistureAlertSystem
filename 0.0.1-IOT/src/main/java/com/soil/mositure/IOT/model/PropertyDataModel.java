package com.soil.mositure.IOT.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="property_table")
public class PropertyDataModel {

    @Id
    private int sl_num;
    private boolean data_insertion_status;
    private boolean alert_service_status;
    private int moisture_measure_frequency;
    private int moisture_alert_limit;

    public PropertyDataModel(boolean data_insertion_status, boolean alert_service_status, int moisture_measure_frequency, int moisture_alert_limit) {
        this.data_insertion_status = data_insertion_status;
        this.alert_service_status = alert_service_status;
        this.moisture_measure_frequency = moisture_measure_frequency;
        this.moisture_alert_limit = moisture_alert_limit;
    }

    public PropertyDataModel(int sl_num, boolean data_insertion_status, boolean alert_service_status, int moisture_measure_frequency, int moisture_alert_limit) {
        this.sl_num = sl_num;
        this.data_insertion_status = data_insertion_status;
        this.alert_service_status = alert_service_status;
        this.moisture_measure_frequency = moisture_measure_frequency;
        this.moisture_alert_limit = moisture_alert_limit;
    }

    public PropertyDataModel() {
    }

    public int getSl_num() {
        return sl_num;
    }

    public void setSl_num(int sl_num) {
        this.sl_num = sl_num;
    }

    public boolean isData_insertion_status() {
        return data_insertion_status;
    }

    public void setData_insertion_status(boolean data_insertion_status) {
        this.data_insertion_status = data_insertion_status;
    }

    public boolean isAlert_service_status() {
        return alert_service_status;
    }

    public void setAlert_service_status(boolean alert_service_status) {
        this.alert_service_status = alert_service_status;
    }

    public int getMoisture_measure_frequency() {
        return moisture_measure_frequency;
    }

    public void setMoisture_measure_frequency(int moisture_measure_frequency) {
        this.moisture_measure_frequency = moisture_measure_frequency;
    }

    public int getMoisture_alert_limit() {
        return moisture_alert_limit;
    }

    public void setMoisture_alert_limit(int moisture_alert_limit) {
        this.moisture_alert_limit = moisture_alert_limit;
    }
}
