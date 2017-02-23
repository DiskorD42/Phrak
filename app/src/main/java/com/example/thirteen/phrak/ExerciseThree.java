package com.example.thirteen.phrak;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marc on 06.02.2017.
 */

public class ExerciseThree extends AppCompatActivity {
    TextView exercise;
    TextView weight;
    EditText amrap;

    Map<String, String> workouts = new HashMap<>();
    DBHandler db = new DBHandler(this, null, null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise_one);
        initializeMap();
        Bundle bundle = getIntent().getExtras();
        String workout = bundle.getString("nworkout");
        String firstLetter = workout.substring(2, 3);

        exercise = (TextView) findViewById(R.id.fullscreen_content);
        weight = (TextView) findViewById(R.id.weight);
        amrap = (EditText) findViewById(R.id.amrap);

        Entry eData = getExerciseData(firstLetter);

        exercise.setText(eData.name);
        weight.setText(String.valueOf(eData.weight));
        amrap.setText(String.valueOf(eData.amrap));



    }

    private void initializeMap() {
        workouts.put("R", "Rows");
        workouts.put("B", "BenchPress");
        workouts.put("C", "Chinups");
        workouts.put("S", "Squats");
        workouts.put("O", "OverheadPress");
        workouts.put("C", "Chinups");
        workouts.put("D", "Deadlifts");
    }

    private Entry getExerciseData(String firstLetter) {

        return db.getLastEntry(workouts.get(firstLetter));

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

    }





    //maybe bool?D
    public void saveExercise(View view) {

        Bundle bundle = getIntent().getExtras();
        int workout_id = bundle.getInt("workout_id");

        int amrapnr = Integer.valueOf(amrap.getText().toString());
        double weightnr = Double.valueOf(weight.getText().toString());

        Entry e = new Entry(exercise.getText().toString(), new Date(),
                weightnr, amrapnr, workout_id).calculateIncrement(db);

        db.addEntry(e);
        Intent intent = new Intent(this, FinishActivity.class);
        startActivity(intent);
    }
}