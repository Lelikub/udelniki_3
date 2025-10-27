package com.udel.dataMiner;

import java.util.List;

import com.udel.dataMiner.dataModel.ObjectEntity;
import com.udel.dataMiner.dataModel.ObjectParameters;

public class DataTakerClass {

    public List<ObjectEntity> Objects;
    public List<ObjectParameters> ObjectParameters;


    public DataTakerClass(){
        /// Заполнить базу данных
        /// Получить все данные из бд
        DataTakerFromSQlite();
    }

    private void DataTakerFromSQlite(){
        Objects = SQLiteMiner.getAllObjects();
        ObjectParameters = SQLiteMiner.getAllObjectParameters();
    }
}
