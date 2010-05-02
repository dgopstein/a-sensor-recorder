package com.cheind.sensorrecorder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import android.content.Context;
import android.hardware.SensorEvent;
import android.os.Environment;

/**
 * Writes sensor data formatted as simple CSV to the external storage.
 * Might fail if no external storage is present or external storage is
 * shared with PC - only one the pc or the phone can have access to the external storage
 * at any time. This should become the helping error message.
 * 
 *
 */
public class FileSensorEventListener extends GenericSensorEventListener {
  static final String FILE_DELIMITER = ";"; 
  
  private BufferedWriter _out;
  private String _line_separator;
  
  public FileSensorEventListener(Context c, int sensor_type) throws IOException {
   super(new DecimalFormat("0.0000"), FILE_DELIMITER);

   // Exception handling should be done in UI related context
   File root = Environment.getExternalStorageDirectory();
   if (!root.canWrite())
     throw new IOException("Cannot write-access external storage");
   
   String sensor_name = c.getString(SensorType.mapToStringID(sensor_type));
   String filename = sensor_name.replace(' ', '_').toLowerCase() + ".csv";
   
   File directory = new File(root, c.getString(R.string.sd_directory));
   directory.mkdir();
   File record = new File(directory, filename);
   
   FileWriter fw = new FileWriter(record);
   // Use an explicit buffer size to avoid the discouraged constructor
   // see http://www.anddev.org/viewtopic.php?p=32126
   _out = new BufferedWriter(fw, 1024);
   
   _line_separator = System.getProperty("line.separator");
  }
  
  public void onSensorListenerUnregistered() {
    try {
      _out.close();
    } catch (IOException e) {}
  }
  
  @Override
  public void onSensorChanged(SensorEvent ev) {
    try {
      _out.write(this.formatValues(ev.values) + _line_separator);
      _out.flush();
    } catch (IOException e) {}  
  }
}
