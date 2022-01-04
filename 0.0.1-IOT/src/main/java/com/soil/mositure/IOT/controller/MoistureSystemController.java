package com.soil.mositure.IOT.controller;

import com.soil.mositure.IOT.model.MoistureDataModel;
import com.soil.mositure.IOT.repository.MoistureDataRepository;
import com.soil.mositure.IOT.services.MoistureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class MoistureSystemController {

    @Autowired
    MoistureDataRepository moisture_repository;

    @Autowired
    MoistureService moistureService;

    @GetMapping("/insertmoisturedata/{moisturevalue}")
    public MoistureDataModel insertMoistureData(@PathVariable("moisturevalue") float moistureValue){
       /* wamon47665@ehstock.com
        Qwerty@123!!@@##*/
        String alert_status= String.valueOf(moistureService.alertService(moistureValue));
        MoistureDataModel data=new MoistureDataModel(moistureValue,alert_status);
        return moisture_repository.save(data);
    }

}
