package com.enyata.camdiary.ui.aggregations.history;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enyata.camdiary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AggregationCustomAdapter extends BaseAdapter {
    private Context context;
    private List<AggregationItemInterface>aggregation = new ArrayList<AggregationItemInterface>();
    
    
    public  AggregationCustomAdapter(){
        super();
    }
    
    public  AggregationCustomAdapter(Context context, ArrayList<AggregationItemInterface> aggregation){
        this.context = context;
        this.aggregation = aggregation;
    }
    
    

    @Override
    public int getCount() {
        return aggregation.size();
    }

    @Override
    public Object getItem(int position) {
        return aggregation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (aggregation.get(position).isSection()){
            convertView = inflater.inflate(R.layout.history_date_header_layout, parent,false);
            TextView date = convertView.findViewById(R.id.date);
            date.setText(aggregation.get(position).getDate());
        }else {
            convertView = inflater.inflate(R.layout.aggregator_history_layout, parent, false);
            TextView name = convertView.findViewById(R.id.name);
            name.setText(aggregation.get(position).getFullName());

            TextView litres = convertView.findViewById(R.id.litres);
            litres.setText(aggregation.get(position).getMyLitres());

            TextView verificationId = convertView.findViewById(R.id.collectorId);
            verificationId.setText(aggregation.get(position).getCompanyId());

            ImageView image = convertView.findViewById(R.id.historyImage);
            Glide.with(context).load(aggregation.get(position).historyImage()).into(image);
//            Picasso.get().load(aggregation.get(position).historyImage()).fit().into(image);

        }
        
        
        return convertView;
    }
}
