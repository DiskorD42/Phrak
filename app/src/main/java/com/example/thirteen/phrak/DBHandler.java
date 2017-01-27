package com.example.thirteen.phrak;

/**
 * Created by Thirteen on 26.12.2016.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "phrak.db";
    public static final String TABLE_ENTRY = "entry";
    public static final String COLUMN_ENTRY_ID = "entry_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_WORKOUTNAME = "name";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_AMRAP = "amrap";
    public static final String COLUMN_WORKOUT_ID_FK = "workout_nr";

    public static final String TABLE_SETUP = "setup";
    public static final String COLUMN_SETUP_ID = "id";
    public static final String COLUMN_BENCH_PRESS = "bench_press";
    public static final String COLUMN_BARBELL_ROWS = "barbell_row";
    public static final String COLUMN_SQUAT = "squat";
    public static final String COLUMN_OVERHEAD_PRESS = "overhead_press";
    public static final String COLUMN_CHINUP = "chinup";
    public static final String COLUMN_DEADLIFT = "deadlift";
    public static final String COLUMN_INCH = "increment_high";
    public static final String COLUMN_INCL = "increment_low";

    public static final String TABLE_WORKOUT = "workout";
    public static final String COLUMN_WORKOUT_ID = "workout_id";
    public static final String COLUMN_WORKOUT1 = "workout_one";
    public static final String COLUMN_WORKOUT2 = "workout_two";
    public static final String COLUMN_WORKOUT3 = "workout_three";



    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE" + TABLE_ENTRY + "(" +
                COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                COLUMN_DATE + " DATE" +
                COLUMN_WORKOUTNAME + " TEXT" +
                COLUMN_WEIGHT + " FLOAT" +
                COLUMN_AMRAP + " INT" +
                COLUMN_WORKOUT_ID_FK + " INT" +
                ");";
        db.execSQL(query);

        String query2 = "CREATE TABLE" + TABLE_SETUP + "(" +
                COLUMN_SETUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                COLUMN_BENCH_PRESS + " FLOAT" +
                COLUMN_BARBELL_ROWS + " FLOAT" +
                COLUMN_SQUAT + " FLOAT" +
                COLUMN_OVERHEAD_PRESS + " FLOAT" +
                COLUMN_CHINUP + " FLOAT" +
                COLUMN_DEADLIFT + " FLOAT" +
                COLUMN_INCH + " FLOAT" +
                COLUMN_INCL + " FLOAT" +
                ");";
        db.execSQL(query2);

        String query3 = "CREATE TABLE" + TABLE_WORKOUT + "(" +
                COLUMN_WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                COLUMN_WORKOUT1 + " TEXT" +
                COLUMN_WORKOUT2 + " TEXT" +
                COLUMN_WORKOUT3 + " TEXT" +
                ");";
        db.execSQL(query3);

        initiate_workout_table();

    }

    private void initiate_workout_table() {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();

        values.put(COLUMN_WORKOUT1, "Overhead Press");
        values.put(COLUMN_WORKOUT2, "Chinups");
        values.put(COLUMN_WORKOUT3, "Squats");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "Bench Press");
        values.put(COLUMN_WORKOUT2, "Rows");
        values.put(COLUMN_WORKOUT3, "Deadlifts");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "Overhead Press");
        values.put(COLUMN_WORKOUT2, "Chinups");
        values.put(COLUMN_WORKOUT3, "Squats");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "Bench Press");
        values.put(COLUMN_WORKOUT2, "Rows");
        values.put(COLUMN_WORKOUT3, "Squats");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "Overhead Press");
        values.put(COLUMN_WORKOUT2, "Chinups");
        values.put(COLUMN_WORKOUT3, "Deadlifts");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "Bench Press");
        values.put(COLUMN_WORKOUT2, "Rows");
        values.put(COLUMN_WORKOUT3, "Squats");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_ENTRY);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SETUP);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_WORKOUT);
        onCreate(db);

    }

    public void addEntry(Entry entry) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, String.valueOf(entry.date));
        values.put(COLUMN_WORKOUTNAME, String.valueOf(entry.name));
        values.put(COLUMN_WEIGHT, entry.weight);
        values.put(COLUMN_AMRAP, entry.amrap);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ENTRY, null, values);
        db.close();

    }

    public void addSetup(float bench, float barbell, float squat, float press, float chinup,
                         float deadlift, float inch, float incl) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_BENCH_PRESS, bench);
        values.put(COLUMN_BARBELL_ROWS, barbell);
        values.put(COLUMN_SQUAT, squat);
        values.put(COLUMN_OVERHEAD_PRESS, press);
        values.put(COLUMN_CHINUP, chinup);
        values.put(COLUMN_DEADLIFT, deadlift);
        values.put(COLUMN_INCH, inch);
        values.put(COLUMN_INCL, incl);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SETUP, null, values);
        db.close();

    }

    public void updateSetup(float bench, float barbell, float squat, float press, float chinup,
                            float deadlift, float inch, float incl){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_SETUP + " SET" +
                COLUMN_BENCH_PRESS + "=" + bench + ", " +
                COLUMN_BARBELL_ROWS + "=" + barbell + ", " +
                COLUMN_SQUAT + "="+ squat + ", " +
                COLUMN_CHINUP + "="+ chinup +", " +
                COLUMN_OVERHEAD_PRESS + "="+ press +", " +
                COLUMN_DEADLIFT + "="+ deadlift +", " +
                COLUMN_INCH + "="+ inch +", " +
                COLUMN_INCL + "="+ incl +";");

    }

    public void deleteEntry(String date, String workoutname) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ENTRY + " WHERE" + COLUMN_DATE + "=" + date + "AND" +
                COLUMN_WORKOUTNAME + "=" + workoutname + ";");

    }

    public Entry getLastEntry(String workoutname) {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT FROM " + TABLE_ENTRY + " WHERE" + COLUMN_WORKOUTNAME + "=" + workoutname +
                " AND WHERE" + COLUMN_ENTRY_ID + "=(SELECT MAX(" + COLUMN_ENTRY_ID + ") FROM+" + TABLE_ENTRY + ");";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if (c.getString(c.getColumnIndex(COLUMN_WORKOUTNAME)) != null) {

            return new Entry(c.getString(c.getColumnIndex(COLUMN_WORKOUTNAME)), new Date(c.getLong(c.getColumnIndex(COLUMN_DATE))),
                    c.getInt(c.getColumnIndex(COLUMN_WEIGHT)), c.getInt(c.getColumnIndex(COLUMN_AMRAP)));

        } else {
            return null;
        }
    }

    public String[] getLastAndNextWorkout() {
        int workout_id;
        String last_workout_name = null;
        String next_workout_name = null;
        String[] workouts = new String[2];
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT FROM " + TABLE_ENTRY + "WHERE" + COLUMN_ENTRY_ID + "=(SELECT MAX(" +
                COLUMN_ENTRY_ID + ") FROM+" + TABLE_ENTRY + ");";

        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            workout_id = c.getInt(c.getColumnIndex(COLUMN_WORKOUT_ID_FK));

            String query2 = "SELECT * FROM " + TABLE_WORKOUT + " WHERE" +
                    COLUMN_WORKOUT_ID + "="+workout_id+ ");";

            Cursor l = db.rawQuery(query2, null);
            if(l.moveToFirst()){
                last_workout_name = String.valueOf(l.getString(l.getColumnIndex(COLUMN_WORKOUT1)).charAt(0));
                last_workout_name += String.valueOf(l.getString(l.getColumnIndex(COLUMN_WORKOUT2)).charAt(0));
                last_workout_name += String.valueOf(l.getString(l.getColumnIndex(COLUMN_WORKOUT3)).charAt(0));
                workouts[0] = last_workout_name;

            }
            String query3 = "SELECT * FROM " + TABLE_WORKOUT + " WHERE" +
                    COLUMN_WORKOUT_ID + "="+workout_id+1 + ");";
            Cursor n = db.rawQuery(query2, null);
            if(n.moveToFirst()){
                next_workout_name = String.valueOf(n.getString(n.getColumnIndex(COLUMN_WORKOUT1)).charAt(0));
                next_workout_name += String.valueOf(n.getString(n.getColumnIndex(COLUMN_WORKOUT2)).charAt(0));
                next_workout_name += String.valueOf(n.getString(n.getColumnIndex(COLUMN_WORKOUT3)).charAt(0));
                workouts[1] = next_workout_name;

            }


        }else{
            workouts[0] = "...";
            workouts[1] = "OCS";
        }

        return workouts;

    }
}
