package com.enyata.camdiary.ui.collections.history;

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
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorList;

import java.util.ArrayList;

public class CollectorHistoryAdapter extends ArrayAdapter {
    private Context mcontext;


    public ArrayList<CollectorHistoryList> collectorHistoryLists;

    public CollectorHistoryAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<CollectorHistoryList>list) {
        super(context, 0 , list);
        mcontext = context;
        collectorHistoryLists = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.collector_history_layout,parent,false);

        CollectorHistoryList collector = collectorHistoryLists.get(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(collector.getFullName());

        TextView litres = listItem.findViewById(R.id.litres);
        litres.setText(collector.getMyLitres());

        TextView companyName  = listItem.findViewById(R.id.companyname);
        companyName.setText(collector.getCompanyName());

        TextView farmerId  = listItem.findViewById(R.id.farmerId);
        farmerId.setText(collector.getCompanyId());

        TextView status = listItem.findViewById(R.id.status);
        status.setText(collector.getStatus());

        TextView date = listItem.findViewById(R.id.date);
        date.setText(collector.getDate());


        return listItem;
    }

    @Override
    public int getCount() {
        return collectorHistoryLists.size();

    }

}
