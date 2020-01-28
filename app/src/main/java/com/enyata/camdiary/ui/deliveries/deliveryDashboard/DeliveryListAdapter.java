package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

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
import com.enyata.camdiary.ui.aggregations.history.AggregatorHistoryList;

import java.util.ArrayList;

public class DeliveryListAdapter extends ArrayAdapter {

    private Context mcontext;


    public ArrayList<DeliveryList> deliveryLists;

    public DeliveryListAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<DeliveryList>list) {
        super(context, 0 , list);
        mcontext = context;
        deliveryLists = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.delivery_list_layout,parent,false);

        DeliveryList delivery = deliveryLists.get(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(delivery.getMyName());

        TextView items = listItem.findViewById(R.id.items);
        items.setText(delivery.getItems());


        TextView number  = listItem.findViewById(R.id.number);
        number.setText(delivery.getNumber());


        TextView itemId = listItem.findViewById(R.id.itemsId);
        itemId.setText(delivery.getItemsId());


        return listItem;
    }

    @Override
    public int getCount() {

        return deliveryLists.size();

    }


}
