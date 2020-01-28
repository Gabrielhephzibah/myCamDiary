package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

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

public class DeliveryItemAdapter extends ArrayAdapter {

    private  Context mcontext;

    ArrayList<DeliveryItemList> deliveryItemList;


    public DeliveryItemAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<DeliveryItemList>lists) {
        super(context,0,lists);
        this.mcontext = context;
        this.deliveryItemList = lists;

    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItems = convertView;
        if (listItems == null)
           listItems = LayoutInflater.from(mcontext).inflate(R.layout.items_details_layout, parent,false);

            DeliveryItemList delivery = deliveryItemList.get(position);

            TextView itemName = listItems.findViewById(R.id.itemName);
            itemName.setText(delivery.getItemName());

            TextView quantity = listItems.findViewById(R.id.quantity);
            quantity.setText(delivery.getQuantity());

            return listItems;

        }

        @Override
                public int getCount(){

            return deliveryItemList.size();

    }
}
