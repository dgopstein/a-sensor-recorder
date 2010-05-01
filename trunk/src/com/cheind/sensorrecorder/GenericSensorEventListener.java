package com.cheind.sensorrecorder;

import java.text.DecimalFormat;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class GenericSensorEventListener implements SensorEventListener {
  
  private TextView _tv;
  private DecimalFormat _format_two;
  static final String VISUAL_DELIMITER = ", ";

  public GenericSensorEventListener(TextView tv) {
    _tv = tv;
    _format_two = new DecimalFormat("0.00");
  }

  @Override
  public void onAccuracyChanged(Sensor arg0, int arg1) {  
  }

  @Override
  public void onSensorChanged(SensorEvent ev) {
    StringBuilder sb = new StringBuilder();
    float values[] = ev.values;
      
    for (int i = 0; i < values.length; ++i) {
      float v = values[i];
      sb.append(_format_two.format(v));
      if (i < (values.length - 1))
        sb.append(VISUAL_DELIMITER);
    }
    
   _tv.setText(sb.toString());
  }
}
