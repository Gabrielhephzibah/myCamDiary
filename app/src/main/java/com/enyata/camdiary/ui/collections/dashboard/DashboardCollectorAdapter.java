package com.enyata.camdiary.ui.collections.dashboard;

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

import java.util.ArrayList;
import java.util.List;

public class DashboardCollectorAdapter extends ArrayAdapter<DashboardCollectorList> {

    private Context mcontext;


    public ArrayList<DashboardCollectorList> dashboardCollectorLists;

    public DashboardCollectorAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<DashboardCollectorList>list) {
        super(context, 0 , list);
        mcontext = context;
        dashboardCollectorLists = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.collector_list_layout,parent,false);

        DashboardCollectorList collector = dashboardCollectorLists.get(position);
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

        return listItem;
    }

    @Override
    public int getCount() {
        if(dashboardCollectorLists.size() > 10){
            return 5;
        }else{
            return dashboardCollectorLists.size();
        }
    }





}
