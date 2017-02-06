package com.example.thirteen.phrak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateWorkoutActivity extends AppCompatActivity {
TextView lworkout;
TextView nworkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        String[] lastWorkout = getLastWorkout();
        lworkout = (TextView) findViewById(R.id.last_workout);
        lworkout.setText(lastWorkout[0]);
        nworkout = (TextView) findViewById(R.id.next_workout);
        nworkout.setText(lastWorkout[1]);


    }

    public String[] getLastWorkout() {

        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        return dbHandler.getLastAndNextWorkout();

    }

    public void createWorkout(View view) {
        Intent intent = new Intent(this, ExerciseOne.class);
        Bundle bundle  = new Bundle();
        bundle.putString("nworkout", nworkout.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
