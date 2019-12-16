package com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery;

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
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryList;

import java.util.ArrayList;

public class DeliveryItemAdapter extends ArrayAdapter {

    private Context mcontext;


    public ArrayList<DeliveryItemList> deliveryItemLists;

    public DeliveryItemAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<DeliveryItemList>list) {
        super(context, 0 , list);
        mcontext = context;
        deliveryItemLists = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.delivery_item_list,parent,false);

        DeliveryItemList delivery = deliveryItemLists.get(position);
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
        if(deliveryItemLists.size() > 10){
            return 5;
        }else{
            return deliveryItemLists.size();
        }
    }

}
