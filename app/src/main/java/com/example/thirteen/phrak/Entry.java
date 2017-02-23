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

    public Entry(String name, Date date, double weight, int amrap, int workout_id) {

        this.name = name;
        this.date = date;
        this.weight = weight;
        this.amrap = amrap;
        this.workout_id = workout_id;
    }

    public long GetDateToLong() {
        //Date c = new Date(System.currentTimeMillis());
        return date.getTime();
        //To get current time
    }

    public Entry calculateIncrement(DBHandler db) {

        //if amrap bigger then 10 increment double

        if (this.amrap > 10) {
            if (this.name.equals("Deadlift") || this.name.equals("Squat")) {
                double up = db.getHigherinc();
                this.weight += 2 * up;
            } else {
                double down = db.getLowerinc();
                this.weight += 2 * down;
            }
        //else increment single
        } if (this.amrap >5 && this.amrap < 10){
            if (this.name.equals("Deadlift") || this.name.equals("Squat")) {
                double up = db.getHigherinc();
                this.weight += up;
            } else {
                double down = db.getLowerinc();
                this.weight += down;
            }
        }
        //if amrap lower then 5 deload by 10%
        if (this.amrap < 5) {
                //TODO make this deload by 10% closest to actual increments aka weights;
                this.weight = this.weight - (this.weight * 0.1);
            }
        this.weight = Math.round(this.weight*2)/2.0;
        return this;
    }
}
