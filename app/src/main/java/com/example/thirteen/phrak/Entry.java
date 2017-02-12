package com.example.thirteen.phrak;

import java.util.Date;

/**
 * Created by Thirteen on 26.12.2016.
 */

public class Entry {

    String name;
    Date date;
    double weight;
    int amrap;
    int workout_id;

    public Entry(String name, Date date, double weight, int amrap, int workout_id){

        this.name = name;
        this.date = date;
        this.weight = weight;
        this.amrap = amrap;
        this.workout_id = workout_id;
    }

    public long GetDateToLong(){
        //Date c = new Date(System.currentTimeMillis());
        return date.getTime();
    //To get current time
    }
}
