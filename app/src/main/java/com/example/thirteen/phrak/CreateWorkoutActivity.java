package com.example.thirteen.phrak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CreateWorkoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        String[] lastWorkout = getLastWorkout();
        TextView lworkout = (TextView)findViewById(R.id.last_workout);
        lworkout.setText(lastWorkout[0]);
        TextView nworkout = (TextView)findViewById(R.id.next_workout);
        nworkout.setText(lastWorkout[1]);


    }

    public String[] getLastWorkout(){

        DBHandler dbHandler = new DBHandler(this,null,null,1);
        return dbHandler.getLastAndNextWorkout();

    }
}
