package com.soil.mositure.IOT.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="moisture_data")
public class MoistureDataModel {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private int entry_id;
    private Timestamp creation_timestamp=Timestamp.valueOf(LocalDateTime.now());
    private float moisture_value;
    private String alert_status;

    public MoistureDataModel(float moisture_value, String alert_status) {
        this.moisture_value = moisture_value;
        this.alert_status = alert_status;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public Timestamp getCreation_timestamp() {
        return creation_timestamp;
    }

    public void setCreation_timestamp(Timestamp creation_timestamp) {
        this.creation_timestamp = creation_timestamp;
    }

    public float getMoisture_value() {
        return moisture_value;
    }

    public void setMoisture_value(float moisture_value) {
        this.moisture_value = moisture_value;
    }

    public String getAlert_status() {
        return alert_status;
    }

    public void setAlert_status(String alert_status) {
        this.alert_status = alert_status;
    }
}
