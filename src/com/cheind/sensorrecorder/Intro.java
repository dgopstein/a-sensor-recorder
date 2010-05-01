package com.cheind.sensorrecorder;

import java.util.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Intro extends Activity {
    
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.intro);
    
    final ListView sensor_list = (ListView) findViewById(R.id.sensor_list);
    sensor_list.setItemsCanFocus(false);
    SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
    List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
    sensor_list.setAdapter(new SensorListAdapter(this, sensors));
    
    Button record = (Button) findViewById(R.id.record);
    record.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        SparseBooleanArray ids = sensor_list.getCheckedItemPositions();
        ArrayList<Integer> checked_ids = this.convertCheckedItemPositions(ids);

        if (checked_ids.size() == 0) {
          showSelectOneAlert(arg0);
        } else {
          Intent i = new Intent();
          i.setClass(Intro.this.getApplicationContext(), Record.class);
          i.putExtra("selected.sensors", checked_ids);
          Intro.this.startActivity(i);
        }
        
      }

      private ArrayList<Integer> convertCheckedItemPositions(SparseBooleanArray ids) {
        ArrayList<Integer> checked = new ArrayList<Integer>();
        for (int i = 0; i < ids.size(); ++i) {
          if (ids.valueAt(i)) 
            checked.add(ids.keyAt(i));
        }
        Collections.sort(checked);
        return checked;
      }
      
      private void showSelectOneAlert(View v) {
        new AlertDialog.Builder(v.getContext())
        .setTitle("Error")
        .setMessage("Please select at least one sensor")
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
          
          @Override
          public void onClick(DialogInterface arg0, int arg1) {
            // TODO Auto-generated method stub
            
          }
        })
        .show();
      }
    });
  }
}