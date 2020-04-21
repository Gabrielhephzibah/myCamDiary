package com.enyata.camdiary.ui.aggregations.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AggregatorListAdapter extends ArrayAdapter {
    private Context mcontext;


    public ArrayList<AggregatorList> aggregatorLists;

    public AggregatorListAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<AggregatorList>list) {
        super(context, 0 , list);
        mcontext = context;
        aggregatorLists = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.aggregator_list_layout,parent,false);

        AggregatorList aggregator = aggregatorLists.get(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(aggregator.getFullName());

        TextView litres = listItem.findViewById(R.id.litres);
        litres.setText(aggregator.getMyLitres());


        TextView collectorId  = listItem.findViewById(R.id.collectorId);
        collectorId.setText(aggregator.getCompanyId());

        ImageView image = listItem.findViewById(R.id.image);
        Picasso.get().load(aggregator.getImage()).fit().into(image);



        return listItem;
    }

    @Override
    public int getCount() {
            return aggregatorLists.size();
    }

}
