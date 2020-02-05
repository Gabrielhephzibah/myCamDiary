package com.enyata.camdiary.ui.deliveries.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.enyata.camdiary.R;

import java.util.ArrayList;

public class DeliveryCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DeliveryItemInterface> item;

    public DeliveryCustomAdapter() {
        super();
    }

    public DeliveryCustomAdapter(Context context, ArrayList<DeliveryItemInterface> item) {
        this.context = context;
        this.item = item;
    }


    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (item.get(position).isSection()) {
                // if section header
                convertView = inflater.inflate(R.layout.history_date_header_layout, parent, false);
                TextView date = (TextView) convertView.findViewById(R.id.date);
                date.setText(((DeliveryHistoryHeader) item.get(position)).getDate());
            } else {
                // if item
                convertView = inflater.inflate(R.layout.delivery_history_layout, parent, false);
                TextView name = (TextView) convertView.findViewById(R.id.name);
                name.setText(((DispatcherHistory) item.get(position)).getMyName());

                TextView number = (TextView) convertView.findViewById(R.id.number);
                number.setText(((DispatcherHistory) item.get(position)).getNumber());

                TextView itemId = (TextView) convertView.findViewById(R.id.itemsId);
                itemId.setText(((DispatcherHistory) item.get(position)).getItemId());

                TextView items = (TextView) convertView.findViewById(R.id.items);
                items.setText(((DispatcherHistory) item.get(position)).getItems());
            }


            return convertView;

    }
}
