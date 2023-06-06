package com.davidvan.myprograms.packyourbag.Data;

import android.app.Application;
import android.content.Context;

import com.davidvan.myprograms.packyourbag.Constants.MyConstants;
import com.davidvan.myprograms.packyourbag.Database.RoomDB;
import com.davidvan.myprograms.packyourbag.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {

    RoomDB database;
    String category;

    Context context;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 3;

    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }

    public List<Items> getBasicData(){
        category = "Basic Needs";
        List<Items> basicitem = new ArrayList<>();
        basicitem.add(new Items("Visa", category, false));
        basicitem.add(new Items("Passport", category, false));
        return basicitem;
    }

    public List<Items> getClothingData() {
        String [] data = {"Underwear", "Shorts", "Jeans", "Cargo"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }


    public List<Items> getPersonalCareData() {
        String [] data = {"Tooth-brush", "Tooth-paste", "Floss", "Mouthwash"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }

    public List<Items> getBabyNeeds() {
        String [] data = {"Diaper", "Tooth-paste", "Pacifier", "Bath-Thing"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }

    public List<Items> getHealthNeedsData() {
        String [] data = {"Medicine", "Life Jacket"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }



    public List<Items> getTechnologyData() {
        String[] data = {"Mobile Phone", "Phone Cover", "E-Book Reader", "Camera", "Camera Charger",
                "Portable Speaker", "IPad (Tab)", "Headphone"
                , "Laptop", "Laptop Charger", "Mouse", "Extension Cable", "Data Transfer Cable",
                "Battery", "Power Bank", "DVD Player", "Flash-Light", "MP3 Player", "MP3 Player Charger"
                , "Voltage Adapter", "SD Card"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE, data);
    }

        public List<Items> getFoodData() {
            String[] data = {"Snack", "Sandwich", "Juice", "Tea Bags", "Coffee", "Water", "Thermos", "Chips", "Baby Food"};
            return prepareItemsList(MyConstants.FOOD_CAMEL_CASE,data);
        }
        public List<Items> getBeachSuppliesData() {
            String[] data = {"Sea Glasses", "Sea Bed", "Suntan Cream", "Beach Umbrella", "Swim Fins",
                    "Beach Bag", "Beach Towel", "Beach Slippers",
                    "Sunbed", "Snorkel", "Waterproof Clock"};
            return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE, data);
    }


    public List<Items> getCarSuppliesData() {
        String[] data = {"Pump", "Car Jack", "Spare Car Key", "Accident Record Set", "Auto Refrigerator",
                "Car Cover", "Car Charger", "Window Sun Shades"};
        return prepareItemsList(MyConstants.CAR_SUPPLIES_CAMEL_CASE,data);
    }
    public List<Items> getNeedsData () {
        String []data = {"Backpack", "Daily Bags", "Laundry Bag", "Sewing Kit",
                "Travel Lock", "Luggage Tag", "Magazine",
                "Sports Equipment", "Important Numbers"};
        return prepareItemsList (MyConstants. NEEDS_CAMEL_CASE, data);
    }

    public List<Items> prepareItemsList(String category, String []data){
        List<String> list = Arrays.asList(data);
        List<Items> dataList = new ArrayList<>();
        dataList.clear();;

        for(int i=0; i<list.size(); i++) {
            dataList.add(new Items(list.get(i), category, false));
        }
        return dataList;
    }

    public List<List<Items>> getAllData(){
        List<List<Items>> listOfAllItems = new ArrayList<>();
        listOfAllItems.clear();
        listOfAllItems.add(getBasicData());
        listOfAllItems.add(getClothingData());
        listOfAllItems.add(getTechnologyData());
        listOfAllItems.add(getFoodData());
        listOfAllItems.add(getBeachSuppliesData());
        listOfAllItems.add(getCarSuppliesData());
        listOfAllItems.add(getNeedsData());
        listOfAllItems.add(getPersonalCareData());
        return listOfAllItems;
    }

    public void persistAllData(){
        List<List<Items>> listOfAllItems = getAllData();
        for(List<Items> list:listOfAllItems) {
            for(Items items:list){
                database.mainDao().saveItem(items);
            }
        }
        System.out.println("Data added");
    }
}
