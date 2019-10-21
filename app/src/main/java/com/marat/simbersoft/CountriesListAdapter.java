package com.marat.simbersoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marat.simbersoft.ModelListView;
import com.marat.simbersoft.R;

import java.util.ArrayList;

public class CountriesListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelListView> dataModelArrayList;

    public CountriesListAdapter(Context context,
                                ArrayList<ModelListView> dataModelArrayList){
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }


    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            //viewHolder.position = position;

            convertView = inflater.inflate(R.layout.retro_lv, null, true);


            viewHolder.mTVname = (TextView) convertView.findViewById(R.id.country_name);


            convertView.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.mTVname.setText(dataModelArrayList.get(position).getName());
      //  viewHolder.position = position;


        return convertView;
    }

    private class ViewHolder {

        protected TextView mTVname;

      //  protected int position;

    }

}
