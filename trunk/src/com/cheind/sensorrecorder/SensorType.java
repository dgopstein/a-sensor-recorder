package com.cheind.sensorrecorder;

import android.hardware.Sensor;

public class SensorType {
  
  public static int mapToStringID(int sensor_type) {
    switch(sensor_type) {
    case Sensor.TYPE_ACCELEROMETER:
      return R.string.sensor_type_accelerometer;
    case Sensor.TYPE_GYROSCOPE:
      return R.string.sensor_type_gyroscope;
    case Sensor.TYPE_LIGHT:
      return R.string.sensor_type_light;
    case Sensor.TYPE_MAGNETIC_FIELD:
      return R.string.sensor_type_magnetic_field;
    case Sensor.TYPE_ORIENTATION:
      return R.string.sensor_type_orientation;
    case Sensor.TYPE_PRESSURE:
      return R.string.sensor_type_pressure;
    case Sensor.TYPE_PROXIMITY:
      return R.string.sensor_type_proximity;
    case Sensor.TYPE_TEMPERATURE:
      return R.string.sensor_type_temperature;
    }
    return 0;
    
  }
  

}
