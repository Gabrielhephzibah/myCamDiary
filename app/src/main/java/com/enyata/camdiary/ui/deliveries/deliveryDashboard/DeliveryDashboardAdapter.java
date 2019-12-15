package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class DeliveryDashboardAdapter extends PagerAdapter {
    private int[] layouts;
    Context context;
    LayoutInflater layoutInflater;


    public  DeliveryDashboardAdapter(int[] layouts, Context context){
        this.layouts = layouts;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);




    }
    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = layoutInflater.inflate(layouts[position],container,false);

        container.addView(view);


        return view;
    }
}
