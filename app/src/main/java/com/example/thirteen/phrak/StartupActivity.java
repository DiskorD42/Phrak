package com.example.thirteen.phrak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        DBHandler db = new DBHandler(this,null,null,1);
        String[] workouts = db.getLastAndNextWorkout();
        if (workouts[0].equals("...")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, CreateWorkoutActivity.class);
            startActivity(intent);

        }
    }
}
