package com.cheind.sensorrecorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
  
  public static final String TAG_CHECKED_SENSOR_IDS ="selected.sensors";
  
  private ListView _sensor_list;
  private List<Sensor> _sensors;
    
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.intro);
    
    _sensor_list = (ListView) findViewById(R.id.sensor_list);
    _sensor_list.setItemsCanFocus(false);
    SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
    _sensors = sm.getSensorList(Sensor.TYPE_ALL);
    _sensor_list.setAdapter(new SensorListAdapter(this, _sensors));
    
    Button record = (Button) findViewById(R.id.record);
    record.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        SparseBooleanArray ids = _sensor_list.getCheckedItemPositions();
        ArrayList<Integer> checked_ids = Intro.convertCheckedItemPositions(ids);

        if (checked_ids.size() == 0) {
          showSelectOneAlert(arg0);
        } else {
          Intent i = new Intent();
          i.setClass(Intro.this.getApplicationContext(), Record.class);
          i.putExtra(TAG_CHECKED_SENSOR_IDS, checked_ids);
          Intro.this.startActivity(i);
        }
        
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
  
  private static ArrayList<Integer> convertCheckedItemPositions(SparseBooleanArray ids) {
    ArrayList<Integer> checked = new ArrayList<Integer>();
    for (int i = 0; i < ids.size(); ++i) {
      if (ids.valueAt(i)) 
        checked.add(ids.keyAt(i));
    }
    Collections.sort(checked);
    return checked;
  }
}