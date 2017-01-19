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

    public static final String TABLE_SETUP = "setup";
    public static final String COLUMN_SETUP_ID = "id";
    public static final String COLUMN_BENCH_PRESS = "bench_press";
    public static final String COLUMN_BARBELL_ROWS = "barbell_row";
    public static final String COLUMN_SQUAT = "squat";
    public static final String COLUMN_OVERHEAD_PRESS = "overhead_press";
    public static final String COLUMN_CHINUP = "chinup";
    public static final String COLUMN_DEADLIFT = "deadlift";
    public static final String COLUMN_INCH = "increment_high";
    public static final String COLUMN_INCL = "increment_high";


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
                COLUMN_AMRAP + " amrap" +
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_ENTRY);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SETUP);
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
}
