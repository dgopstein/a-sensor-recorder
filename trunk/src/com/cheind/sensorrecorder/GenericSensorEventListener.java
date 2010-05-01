package com.cheind.sensorrecorder;

import java.util.Arrays;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class GenericSensorEventListener implements SensorEventListener {
  
  private TextView _tv;

  public GenericSensorEventListener(TextView tv) {
    _tv = tv;
  }

  @Override
  public void onAccuracyChanged(Sensor arg0, int arg1) {  
  }

  @Override
  public void onSensorChanged(SensorEvent ev) {
   _tv.setText(Arrays.toString(ev.values));
  }
}
