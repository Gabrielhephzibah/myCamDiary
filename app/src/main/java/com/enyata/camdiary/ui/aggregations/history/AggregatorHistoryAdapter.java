package com.enyata.camdiary.ui.aggregations.history;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.collections.history.CollectorHistoryList;

import java.util.ArrayList;

public class AggregatorHistoryAdapter extends ArrayAdapter {
    private Context mcontext;


    public ArrayList<AggregatorHistoryList> aggregatorHistoryLists;

    public AggregatorHistoryAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<AggregatorHistoryList>list) {
        super(context, 0 , list);
        mcontext = context;
        aggregatorHistoryLists = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.aggregator_history_layout,parent,false);

        AggregatorHistoryList aggregator = aggregatorHistoryLists.get(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(aggregator.getFullName());

        TextView litres = listItem.findViewById(R.id.litres);
        litres.setText(aggregator.getMyLitres());


        TextView farmerId  = listItem.findViewById(R.id.farmerId);
        farmerId.setText(aggregator.getCompanyId());


        TextView date = listItem.findViewById(R.id.date);
        date.setText(aggregator.getDate());


        return listItem;
    }

    @Override
    public int getCount() {
        if(aggregatorHistoryLists.size() > 10){
            return 5;
        }else{
            return aggregatorHistoryLists.size();
        }
    }
}
