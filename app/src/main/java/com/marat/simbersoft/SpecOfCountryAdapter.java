package com.marat.simbersoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SpecOfCountryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelListView> dataModelArrayList;




    public SpecOfCountryAdapter(Context context,
                                ArrayList<ModelListView> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
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
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflator = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            viewHolder = new ViewHolder();

            convertView = inflator
                    .inflate(R.layout.spec_country_lv, null, true);



            viewHolder.tvname = (TextView) convertView.findViewById(R.id.country_name);
            viewHolder.mTVcapital = (TextView) convertView.findViewById(R.id.capital_text_view);
            viewHolder.mTVpopulationDemonym = (TextView) convertView.findViewById(R.id.population_demonym_text_view);
            viewHolder.mTVarea = (TextView) convertView.findViewById(R.id.area_text_view);
            viewHolder.mTVregionSubregion = (TextView) convertView.findViewById(R.id.region_subregion_text_view);
            viewHolder.mTVcorrency = (TextView) convertView.findViewById(R.id.corren_code_name_symbol_text_view);
            viewHolder.mIVflag = convertView.findViewById(R.id.flag);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tvname.setText(dataModelArrayList.get(position).getName());
        Picasso.get().load(dataModelArrayList.get(position).getFlag()).into(viewHolder.mIVflag);
        Toast.makeText(context , dataModelArrayList.get(position).getFlag(),Toast.LENGTH_LONG).show();
        viewHolder.mTVcapital.setText(("Capital :  " + dataModelArrayList.get(position).getCapital()));
        viewHolder.mTVpopulationDemonym.setText("Population :  " +
                dataModelArrayList.get(position).getPopulation() +
                " " + dataModelArrayList.get(position).getDemonym());
        viewHolder.mTVarea.setText("Area :  " + dataModelArrayList.get(position).getArea() +
                " km\u00B2");
        viewHolder.mTVregionSubregion.setText("Region :  " +
                dataModelArrayList.get(position).getRegion() +
                ", " + dataModelArrayList.get(position).getSubregion());
        //todo обработка null значений
        viewHolder.mTVcorrency.setText("Corrensies :  \n     "+
                dataModelArrayList.get(position).getCorrencies().getCode() +
                "\n     " + dataModelArrayList.get(position).getCorrencies().getName() +
                "\n     " + dataModelArrayList.get(position).getCorrencies().getSymbol());




        return  convertView;

    }

    private class ViewHolder {
        protected TextView tvname;

        protected ImageView mIVflag;
        protected TextView mTVcapital, mTVpopulationDemonym, mTVarea, mTVregionSubregion;
        protected  TextView mTVcorrency;

    }
}
