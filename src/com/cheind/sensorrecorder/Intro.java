package com.cheind.sensorrecorder;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;

public class Intro extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        
        ListView sensor_list = (ListView) findViewById(R.id.sensor_list);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_list.setAdapter(new SensorListAdapter(this, sm.getSensorList(Sensor.TYPE_ALL)));
    }
}