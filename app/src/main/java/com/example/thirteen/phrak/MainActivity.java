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
import android.widget.NumberPicker;

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
        NumberPicker bench = (NumberPicker) findViewById(R.id.benchPress);
        NumberPicker barbell = (NumberPicker) findViewById(R.id.row);
        NumberPicker squat = (NumberPicker) findViewById(R.id.squat);
        NumberPicker press = (NumberPicker) findViewById(R.id.overheadPress);
        NumberPicker chinups = (NumberPicker) findViewById(R.id.chinup);
        NumberPicker deadlift = (NumberPicker) findViewById(R.id.deadlift);
        NumberPicker lowerinc = (NumberPicker) findViewById(R.id.lowerInc);
        NumberPicker higherinc = (NumberPicker) findViewById(R.id.higherInc);

        dbHandler = new DBHandler(this, null, null, 2);
        //dbHandler.addSetup(bench.getValue(),barbell.getValue(), squat.getValue(),press.getValue(),
         //       chinups.getValue(), deadlift.getValue(), higherinc.getValue(), lowerinc.getValue());
        dbHandler.addSetup(5.5,5.5,5.5,5.5,55.5,5.5,5.5,5.5);

        startActivity(intent);

        }
    public void setEntry(String name, Date date, NumberPicker numb){
        dbHandler = new DBHandler(this, null,null,1);
        Entry entry = new Entry(name,date,numb.getValue(),-1);
        dbHandler.addEntry(entry);



    }


    }
