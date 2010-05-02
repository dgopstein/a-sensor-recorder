package com.cheind.sensorrecorder;

import java.text.DecimalFormat;

import android.content.Context;
import android.hardware.SensorEvent;
import android.widget.TextView;

public class UISensorEventListener extends GenericSensorEventListener {
  
  static final String VISUAL_DELIMITER = "| ";
  
  private TextView _tv;
  
  public UISensorEventListener(Context c, TextView tv) {
    super(new DecimalFormat("0.00"), VISUAL_DELIMITER);
    _tv = tv;
  }
  
  @Override
  public void onSensorChanged(SensorEvent ev) {
    _tv.setText(this.formatValues(ev.values));
  }
}
