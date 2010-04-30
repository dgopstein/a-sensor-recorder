package com.cheind.sensorrecorder;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.cheind.sensorrecorder.SensorListAdapter.ViewHolder;

public class Intro extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        
        ListView sensor_list = (ListView) findViewById(R.id.sensor_list);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_list.setAdapter(new SensorListAdapter(this, sm.getSensorList(Sensor.TYPE_ALL)));
        sensor_list.setOnItemClickListener(new OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
            ListView lv = (ListView) arg0;
            SensorListAdapter.ViewHolder vh = (ViewHolder) arg1.getTag();
            boolean check = !vh.check.isChecked();
            vh.check.setChecked(check);
            lv.setItemChecked(position, check);
            
            String s ="";
            for(long cid : lv.getCheckItemIds()) {
              s += cid +  ",";
            }
            Log.i("juhu", s);
            
          }
        });
    }
}