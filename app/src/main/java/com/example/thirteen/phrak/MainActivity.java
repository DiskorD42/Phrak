package com.example.thirteen.phrak;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.thirteen.phrak.DBHandler;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initialSetup(View view){
        Intent intent = new Intent(this, CreateWorkoutActivity.class);

        //get all the values from the number picker
        EditText bench = (EditText) findViewById(R.id.benchPress);
        EditText barbell = (EditText) findViewById(R.id.row);
        EditText squat = (EditText) findViewById(R.id.squat);
        EditText press = (EditText) findViewById(R.id.overheadPress);
        EditText chinups = (EditText) findViewById(R.id.chinup);
        EditText deadlift = (EditText) findViewById(R.id.deadlift);
        EditText lowerinc = (EditText) findViewById(R.id.lowerInc);
        EditText higherinc = (EditText) findViewById(R.id.higherInc);

        dbHandler = new DBHandler(this, null, null, 1);
        dbHandler.addSetup(Float.valueOf(bench.getText().toString()),Float.valueOf(barbell.getText().toString()),
                Float.valueOf(squat.getText().toString()),Float.valueOf(press.getText().toString()),
                Float.valueOf(chinups.getText().toString()), Float.valueOf(deadlift.getText().toString()),
                Float.valueOf(higherinc.getText().toString()), Float.valueOf(lowerinc.getText().toString()));

        startActivity(intent);

        }
    public void setEntry(String name, Date date, EditText numb){
        dbHandler = new DBHandler(this, null,null,1);
        Entry entry = new Entry(name,date,Float.valueOf(numb.getText().toString()),-1);
        dbHandler.addEntry(entry);



    }


    }
