package com.cheind.sensorrecorder;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Record extends Activity {
  
  SensorManager _sm;
  List<Sensor> _sensors;
  List<GenericSensorEventListener> _listeners;
  LayoutInflater _inflater;
  TableLayout _table;
  
  public void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
    _sm = (SensorManager) getSystemService(SENSOR_SERVICE);
    _sensors = _sm.getSensorList(Sensor.TYPE_ALL);
    _inflater = this.getLayoutInflater();
    _listeners = new ArrayList<GenericSensorEventListener>();
    
    this.setContentView(R.layout.record);
    _table = (TableLayout) findViewById(R.id.table_of_values);
  }
  
  @Override
  public void onResume() {
    super.onResume();
    
    // Make sure to clean up layout from a previous on-resume iteration
    _table.removeAllViews();
    _listeners.clear();
    
    // Intent sent by Intro containing the selected
    // sensors as a list of ids
    Intent intent = this.getIntent();
    ArrayList<Integer> sensor_ids = intent.getIntegerArrayListExtra(Intro.TAG_CHECKED_SENSOR_IDS);
    
    // Incrementally build layout and register sensor event listeners
    for(int si : sensor_ids) {
      Sensor s = _sensors.get(si);
      TableRow row = (TableRow)_inflater.inflate(R.layout.sensor_row, _table, false);
      TextView sensor_type = (TextView)row.findViewById(R.id.sensor_type);
      TextView sensor_values = (TextView)row.findViewById(R.id.sensor_column_values);
      
      sensor_type.setText(this.getString(SensorType.mapToStringID(s.getType())));
      sensor_values.setText(this.getString(R.string.no_values));
      
      GenericSensorEventListener gsel = new GenericSensorEventListener(sensor_values);
      _sm.registerListener(gsel, s, SensorManager.SENSOR_DELAY_UI);
      _table.addView(row);
      _listeners.add(gsel);
    }
  }
  
  @Override
  public void onStop() {
    super.onStop();
    // Clean-up event listeners
    for(GenericSensorEventListener l : _listeners) {
      _sm.unregisterListener(l);
    }
  }

}
