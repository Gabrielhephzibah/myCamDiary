package com.enyata.camdiary.ui.collections.history;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.enyata.camdiary.R;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CollectionHistoryCustomAdapter extends BaseAdapter {
   private Context context;
   private ArrayList<CollectionItemInterface> collection = new ArrayList<CollectionItemInterface>();

   public  CollectionHistoryCustomAdapter(){
       super();
   }

    public CollectionHistoryCustomAdapter(Context context, ArrayList<CollectionItemInterface>collections){
      this.context = context;
      this.collection = collections;
    }

    @Override
    public int getCount() {
        return collection.size();
    }

    @Override
    public Object getItem(int position) {
        return collection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       if (collection.get(position).isSection()){
           convertView = inflater.inflate(R.layout.history_date_header_layout,parent,false);
           TextView date = convertView.findViewById(R.id.date);
           date.setText(collection.get(position).getDate());
       }else {
           convertView = inflater.inflate(R.layout.collector_history_layout, parent, false);
           TextView name = convertView.findViewById(R.id.name);
           name.setText(collection.get(position).getFullName());

           TextView litres = convertView.findViewById(R.id.collectorLitres);
           litres.setText(collection.get(position).getMyLitres());

           TextView companyName = convertView.findViewById(R.id.companyname);
           companyName.setText(collection.get(position).getCompanyName());

           TextView farmerId = convertView.findViewById(R.id.farmerId);
           farmerId.setText(collection.get(position).getCompanyId());

           TextView status = convertView.findViewById(R.id.status);
           status.setText(collection.get(position).getStatus());
       }


        return convertView;
    }
}
