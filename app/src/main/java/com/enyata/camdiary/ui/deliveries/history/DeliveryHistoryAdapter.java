package com.enyata.camdiary.ui.deliveries.history;

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
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryItemList;

import java.util.ArrayList;

public class DeliveryHistoryAdapter extends ArrayAdapter {

    private Context mcontext;


    public ArrayList<DeliveryHistoryList> deliveryHistoryLists;

    public DeliveryHistoryAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<DeliveryHistoryList>list) {
        super(context, 0 , list);
        mcontext = context;
        deliveryHistoryLists = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.delivery_history_layout,parent,false);

        DeliveryHistoryList delivery = deliveryHistoryLists.get(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(delivery.getMyName());

        TextView items = listItem.findViewById(R.id.items);
        items.setText(delivery.getItems());



        TextView date = listItem.findViewById(R.id.date);
        date.setText(delivery.getDate());


        TextView number  = listItem.findViewById(R.id.number);
        number.setText(delivery.getNumber());


        TextView itemId = listItem.findViewById(R.id.itemsId);
        itemId.setText(delivery.getItemId());


        return listItem;
    }

    @Override
    public int getCount() {
        if(deliveryHistoryLists.size() > 10){
            return 5;
        }else{
            return deliveryHistoryLists.size();
        }
    }

}
