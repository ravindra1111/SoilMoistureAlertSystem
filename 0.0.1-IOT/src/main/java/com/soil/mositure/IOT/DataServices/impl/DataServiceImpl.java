package com.soil.mositure.IOT.DataServices.impl;

import com.opencsv.CSVWriter;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendDocument;
import com.soil.mositure.IOT.DataServices.DataService;
import com.soil.mositure.IOT.model.MoistureDataModel;
import com.soil.mositure.IOT.model.PropertyDataModel;
import com.soil.mositure.IOT.repository.MoistureDataRepository;
import com.soil.mositure.IOT.repository.PropertyDataRepository;
import com.soil.mositure.IOT.repository.UserNotificationRepository;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    MoistureDataRepository moistureData;
    @Autowired
    PropertyDataRepository propertyData;
    @Autowired
    UserNotificationRepository user_admin;

    @Override
    public void telegram_data_service() throws IOException {
        List<MoistureDataModel> moisture_data=moistureData.findAll();
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        //send message
        String sendMessageURL="https://api.telegram.org/bot5027203227:AAEEYS5DwRTSTVVKzOv9A8dU2dAYIOcknys/sendMessage";
        String actual_message=prepareMessage(moisture_data);
        String admin_chat_id=user_admin.getById(0).getTelegram_id();
        String body = "{\n" +
                "    \"chat_id\":"+admin_chat_id+",\n" +
                "    \"text\":\""+actual_message+"\"\n" +
                "}";
        client.prepare("POST", sendMessageURL)
                .setHeader("Accept", "application/json")
                .setHeader("User-Agent", "Moisture Notifier System-Telegram Bot")
                .setHeader("Content-Type", "application/json")
                .setBody(body)
                .execute()
                .toCompletableFuture()
                .join();

        client.close();
        //send file
        sendTelegramFile(prepareCSVFile(moisture_data),admin_chat_id);
        deleteData(moisture_data);
    }

    @Override
    public void deleteData(List<MoistureDataModel> moisture_data) {
        moistureData.deleteAll();
    }

    String prepareMessage(List<MoistureDataModel> moisture_data){
        //preparing message
        //Moisture table
        String moisture_table="MoistureData as on "+ LocalDateTime.now() +"\n"
                +"EntNum\tMoistureData\tTOE\n";
        DateTimeFormatter format=DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        for(MoistureDataModel data:moisture_data){
            String row=" "+data.getEntry_id()+" \t\t\t "+data.getMoisture_value()+ " \t\t\t\t "
                    +data.getCreation_timestamp().format(format)+"\n";
            moisture_table+=row;
        }
        //PropertyTable
        String property_table="\n\n Application properties \n";
        PropertyDataModel prop=propertyData.getById(1);
        property_table+=" Alert Service Status :" +prop.isAlert_service_status()+"\n"
                        +" Data Insert Service Status :"+prop.isData_insertion_status()+"\n"
                        +" Moisture Limit :"+prop.getMoisture_alert_limit()+"\n"
                        +" Data Measure Frequency :"+prop.getMoisture_measure_frequency();

        return moisture_table+property_table;
    }


    File prepareCSVFile(List<MoistureDataModel> moisture_data) throws IOException {

        String file_name=LocalDateTime.now().toLocalDate()+"_data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(file_name));
        boolean header=true;
        for(MoistureDataModel data:moisture_data){
            if(header){
                String row_header[]= {"entry_id", "alert_status", "creation_timestamp", "moisture_value"};
                writer.writeNext(row_header);
                header=false;
            }
            String row_data[]={String.valueOf(data.getEntry_id()),
                               String.valueOf(data.getAlert_status()),
                               String.valueOf(data.getCreation_timestamp()),
                               String.valueOf(data.getMoisture_value())};
            writer.writeNext(row_data);
            writer.flush();
        }
        File file=new File(file_name);
        return file;
    }

    void sendTelegramFile(File file,String chat_id){
        TelegramBot bot=new TelegramBot.Builder("5027203227:AAEEYS5DwRTSTVVKzOv9A8dU2dAYIOcknys").build();
        SendDocument doc=new SendDocument(chat_id,file);
        doc.caption(LocalDateTime.now().toLocalDate()+" data csv file");
        bot.execute(doc);
    }
}
