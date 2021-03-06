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
    public static final String COLUMN_BENCH_PRESS = "BenchPress";
    public static final String COLUMN_BARBELL_ROWS = "Rows";
    public static final String COLUMN_SQUAT = "Squats";
    public static final String COLUMN_OVERHEAD_PRESS = "OverheadPress";
    public static final String COLUMN_CHINUP = "Chinups";
    public static final String COLUMN_DEADLIFT = "Deadlifts";
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


        String query = "CREATE TABLE " + TABLE_ENTRY + "(" +
                COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " DATE, " +
                COLUMN_WORKOUTNAME + " TEXT, " +
                COLUMN_WEIGHT + " FLOAT, " +
                COLUMN_AMRAP + " INT, " +
                COLUMN_WORKOUT_ID_FK + " INT " +
                ");";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_SETUP + "(" +
                COLUMN_SETUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BENCH_PRESS + " FLOAT, " +
                COLUMN_BARBELL_ROWS + " FLOAT, " +
                COLUMN_SQUAT + " FLOAT, " +
                COLUMN_OVERHEAD_PRESS + " FLOAT, " +
                COLUMN_CHINUP + " FLOAT, "  +
                COLUMN_DEADLIFT + " FLOAT, " +
                COLUMN_INCH + " FLOAT, " +
                COLUMN_INCL + " FLOAT " +
                ");";
        db.execSQL(query2);

        String query3 = "CREATE TABLE " + TABLE_WORKOUT + "(" +
                COLUMN_WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WORKOUT1 + " TEXT, " +
                COLUMN_WORKOUT2 + " TEXT, " +
                COLUMN_WORKOUT3 + " TEXT " +
                ");";
        db.execSQL(query3);

        initiate_workout_table(db);

    }

    private void initiate_workout_table(SQLiteDatabase db) {
        ContentValues values = new ContentValues();


        values.put(COLUMN_WORKOUT1, "OverheadPress");
        values.put(COLUMN_WORKOUT2, "Chinups");
        values.put(COLUMN_WORKOUT3, "Squats");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "BenchPress");
        values.put(COLUMN_WORKOUT2, "Rows");
        values.put(COLUMN_WORKOUT3, "Deadlifts");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "OverheadPress");
        values.put(COLUMN_WORKOUT2, "Chinups");
        values.put(COLUMN_WORKOUT3, "Squats");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "BenchPress");
        values.put(COLUMN_WORKOUT2, "Rows");
        values.put(COLUMN_WORKOUT3, "Squats");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "OverheadPress");
        values.put(COLUMN_WORKOUT2, "Chinups");
        values.put(COLUMN_WORKOUT3, "Deadlifts");
        db.insert(TABLE_WORKOUT, null, values);
        values.put(COLUMN_WORKOUT1, "Bench Press");
        values.put(COLUMN_WORKOUT2, "Rows");
        values.put(COLUMN_WORKOUT3, "Squats");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_ENTRY);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SETUP);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_WORKOUT);
        onCreate(db);

    }

    public void addEntry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, String.valueOf(entry.date));
        values.put(COLUMN_WORKOUTNAME, String.valueOf(entry.name));
        values.put(COLUMN_WEIGHT, entry.weight);
        values.put(COLUMN_AMRAP, entry.amrap);
        values.put(COLUMN_WORKOUT_ID_FK, entry.workout_id);
        db.insert(TABLE_ENTRY, null, values);
        db.close();

    }

    public void addSetup(double bench, double barbell, double squat, double press, double chinup,
                         double deadlift, double inch, double incl) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BENCH_PRESS, bench);
        values.put(COLUMN_BARBELL_ROWS, barbell);
        values.put(COLUMN_SQUAT, squat);
        values.put(COLUMN_OVERHEAD_PRESS, press);
        values.put(COLUMN_CHINUP, chinup);
        values.put(COLUMN_DEADLIFT, deadlift);
        values.put(COLUMN_INCH, inch);
        values.put(COLUMN_INCL, incl);
        db.insert(TABLE_SETUP, null, values);
        db.close();

    }

    public void updateSetup(double bench, double barbell, double squat, double press, double chinup,
    double deadlift, double inch, double incl) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_SETUP + " SET " +
                COLUMN_BENCH_PRESS + "=" + bench + ", " +
                COLUMN_BARBELL_ROWS + "=" + barbell + ", " +
                COLUMN_SQUAT + "="+ squat + ", " +
                COLUMN_CHINUP + "="+ chinup +", " +
                COLUMN_OVERHEAD_PRESS + "="+ press +", " +
                COLUMN_DEADLIFT + "="+ deadlift +", " +
                COLUMN_INCH + "="+ inch +", " +
                COLUMN_INCL + "="+ incl +";");

    }

    public double getLowerinc() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SETUP + " ;";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        return c.getDouble(c.getColumnIndex(COLUMN_INCL));

    }

    public double getHigherinc() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SETUP + " ;";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        return c.getDouble(c.getColumnIndex(COLUMN_INCH));

    }

    public void deleteEntry(String date, String workoutname) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ENTRY + " WHERE " + COLUMN_DATE + "=" + date + "AND " +
                COLUMN_WORKOUTNAME + "=" + workoutname + ";");

    }

    public Entry getLastEntry(String workoutname) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ENTRY + " WHERE " + COLUMN_WORKOUTNAME + " = '" + workoutname +
                "' ORDER BY "+ COLUMN_ENTRY_ID + " DESC LIMIT 1;";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        //cursorindexoutofboundsexception index 0
        if (c.moveToFirst() && c.getCount() >=1) {

            return new Entry(c.getString(c.getColumnIndex(COLUMN_WORKOUTNAME)), new Date(c.getLong(c.getColumnIndex(COLUMN_DATE))),
                    c.getFloat(c.getColumnIndex(COLUMN_WEIGHT)), c.getInt(c.getColumnIndex(COLUMN_AMRAP)), c.getInt(c.getColumnIndex(COLUMN_WORKOUT_ID_FK)));

        } else {
            //get entry from setup table
            String query2 = "SELECT \"" + workoutname+"\" FROM " + TABLE_SETUP + ";";
            Cursor d = db.rawQuery(query2, null);
            if(d.moveToFirst()){
                return new Entry(workoutname, new Date(), d.getDouble(d.getColumnIndex(workoutname)),0,1);
            }

        }
        return null;
    }

    public String[] getLastAndNextWorkout() {
        int workout_id;
        String last_workout_name = null;
        String next_workout_name = null;
        String[] workouts = new String[3];
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_ENTRY + "  ORDER BY " + COLUMN_ENTRY_ID + " DESC LIMIT 1;";

        Cursor c = db.rawQuery(query, null);
        //workout lowest workout id = 1
        if (c.moveToFirst()) {
            workout_id = c.getInt(c.getColumnIndex(COLUMN_WORKOUT_ID_FK));
            String query2 = "SELECT * FROM " + TABLE_WORKOUT + " WHERE " +
                    COLUMN_WORKOUT_ID + "=" + workout_id + ";";

            Cursor l = db.rawQuery(query2, null);
            if (l.moveToFirst()) {
                last_workout_name = String.valueOf(l.getString(l.getColumnIndex(COLUMN_WORKOUT1)).charAt(0));
                last_workout_name += String.valueOf(l.getString(l.getColumnIndex(COLUMN_WORKOUT2)).charAt(0));
                last_workout_name += String.valueOf(l.getString(l.getColumnIndex(COLUMN_WORKOUT3)).charAt(0));
                workouts[0] = last_workout_name;

            }
            String query3 = "SELECT * FROM " + TABLE_WORKOUT + " WHERE " +
                    COLUMN_WORKOUT_ID + "=" + (workout_id + 1) + ";";
            Cursor n = db.rawQuery(query3, null);
            if (n.moveToFirst()) {
                next_workout_name = String.valueOf(n.getString(n.getColumnIndex(COLUMN_WORKOUT1)).charAt(0));
                next_workout_name += String.valueOf(n.getString(n.getColumnIndex(COLUMN_WORKOUT2)).charAt(0));
                next_workout_name += String.valueOf(n.getString(n.getColumnIndex(COLUMN_WORKOUT3)).charAt(0));
                workouts[1] = next_workout_name;
                //resets the id after 5
                if(workout_id+1==5){
                    workout_id = 0;
                }
                workouts[2] = "" + (workout_id+1);

            }


        } else {
            workouts[0] = "...";
            workouts[1] = "OCS";
            workouts[2] = "1";



        }
        return workouts;
    }
}
