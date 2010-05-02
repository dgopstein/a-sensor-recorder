package com.cheind.sensorrecorder;

import java.text.DecimalFormat;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class GenericSensorEventListener implements SensorEventListener {
  
  private DecimalFormat _fmt;
  private String _delimiter;

  public GenericSensorEventListener(DecimalFormat fmt, String delimiter) {
    _fmt = fmt;
    _delimiter = delimiter;  
  }
  
  protected String formatValues(float[] values) {
    StringBuilder sb = new StringBuilder();
      
    for (int i = 0; i < values.length; ++i) {
      float v = values[i];
      sb.append(_fmt.format(v));
      if (i < (values.length - 1))
        sb.append(_delimiter);
    }
    
    return sb.toString();
  }
    
  public void onSensorListenerUnregistered() {}
  
  @Override
  public void onAccuracyChanged(Sensor arg0, int arg1) {  
  }

  @Override
  public void onSensorChanged(SensorEvent ev) {
  }
}
