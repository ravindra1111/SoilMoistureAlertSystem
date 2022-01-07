package com.soil.mositure.IOT.DataServices;

import com.soil.mositure.IOT.model.MoistureDataModel;

import java.io.IOException;
import java.util.List;

public interface DataService {

    void telegram_data_service() throws IOException;
    void deleteData(List<MoistureDataModel> moisture_data);
}
