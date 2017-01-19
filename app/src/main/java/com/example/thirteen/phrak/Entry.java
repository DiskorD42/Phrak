package com.example.thirteen.phrak;

import java.util.Date;

/**
 * Created by Thirteen on 26.12.2016.
 */

public class Entry {

    String name;
    Date date;
    int weight;
    int amrap;

    public Entry(String name, Date date, int weight, int amrap){

        this.name = name;
        this.date = date;
        this.weight = weight;
        this.amrap = amrap;
    }

    public long GetDateToLong(){
        //Date c = new Date(System.currentTimeMillis());
        return date.getTime();
    //To get current time
    }
}
