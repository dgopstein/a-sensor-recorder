package com.cheind.sensorrecorder;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class SensorListAdapter extends BaseAdapter {
  
  private List<Sensor> _sensors;
  private LayoutInflater _inflater;
  
  public SensorListAdapter(Context c, List<Sensor> sensors) {
    _inflater = LayoutInflater.from(c);
    _sensors = sensors;
  }

  @Override
  public int getCount() {
    return _sensors.size(); 
  }

  @Override
  public Object getItem(int pos) {
    return _sensors.get(pos);
  }

  @Override
  public long getItemId(int arg0) {
    return arg0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup group) {
    // Note that android will not instance a list-item-view for each and 
    // every item to show. Instead android instances as many views as fit 
    // on the screen.
    //
    // Therefore android requests either: 
    // - create a new few : convertView == null
    // - adapt an existing view to new list position : convertView != null
    
    ViewHolder holder;
    
    if (convertView == null) {
      convertView = _inflater.inflate(R.layout.sensor_list_item, null);
      
      holder = new ViewHolder();
      holder.check = (CheckBox) convertView.findViewById(R.id.sensor_check);
      holder.type = (TextView) convertView.findViewById(R.id.sensor_type);
      holder.details = (TextView) convertView.findViewById(R.id.sensor_details);
      
      
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    
    ListView lv = (ListView) group;
    holder.sensor = _sensors.get(position);
    holder.type.setText(SensorType.mapToStringID(holder.sensor.getType()));
    holder.details.setText(holder.sensor.getName());
    holder.check.setChecked(lv.getCheckedItemPositions().get(position));
    
    return convertView;
  }
  
  static class ViewHolder {
    CheckBox check;
    TextView type;
    TextView details;
    Sensor sensor;
  } 

}
