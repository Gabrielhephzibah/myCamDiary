package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDeliveryDashboardBinding;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHistoryAdapter;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHistoryList;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryItemAdapter;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryItemList;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class DeliveryDashboardActivity extends BaseActivity<ActivityDeliveryDashboardBinding,DeliveryDashboardViewModel>implements DeliveryDashboardNavigator {



    DeliveryListAdapter deliveryListAdapter;
    ListView listView;
    ArrayList<DeliveryList> deliveryLists = new ArrayList<>();


    @Inject
    ViewModelProviderFactory factory;
    private DeliveryDashboardViewModel deliveryDashboardViewModel;
    int[]layouts = {R.layout.delivery_first_slide, R.layout.delivery_second_slide};
    private DeliveryDashboardAdapter deliveryDashboardAdapterr;
    ImageView[] slider_dash;
    LinearLayout slideLayout;
    ViewPager pager;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryDashboardActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery_dashboard;
    }

    @Override
    public DeliveryDashboardViewModel getViewModel() {
        deliveryDashboardViewModel = ViewModelProviders.of(this,factory).get(DeliveryDashboardViewModel.class);
        return deliveryDashboardViewModel;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryDashboardViewModel.setNavigator(this);
        pager = findViewById(R.id.pager);
        slideLayout = findViewById(R.id.slideLayout);
        listView = findViewById(R.id.listView);




        JSONObject delivery1 = new JSONObject();
        try {
            delivery1.put("myName", "Devon, Samuel");

            delivery1.put("items", "4 items");

            delivery1.put("number", "+2348033237685");

            delivery1.put("itemId", "64ERT234KI89");



        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject delivery2 = new JSONObject();
        try {
            delivery2.put("myName", "Devon, Samuel");

            delivery2.put("items", "4 items");

            delivery2.put("number", "+2348033237685");

            delivery2.put("itemId", "64ERT234KI89");



        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject delivery3 = new JSONObject();
        try {
            delivery3.put("myName", "Devon, Samuel");

            delivery3.put("items", "4 items");

            delivery3.put("number", "+2348033237685");

            delivery3.put("itemId", "64ERT234KI89");



        } catch (JSONException e) {
            e.printStackTrace();
        }





        JSONArray array = new JSONArray();
        array.put(delivery1);
        array.put(delivery2);
        array.put(delivery3);
        array.put(delivery2);
        array.put(delivery1);
        array.put(delivery3);


        for (int i = 0; i < array.length(); i++) {

            try {
                Log.i("message", array.toString());

                JSONObject object = array.getJSONObject(i);
                String myName = object.getString("myName");
                String items= object.getString("items");

                String number = object.getString("number");
                String itemId = object.getString("itemId");



                deliveryLists.add(new DeliveryList(myName,items,number,itemId));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        deliveryListAdapter= new DeliveryListAdapter(DeliveryDashboardActivity.this, deliveryLists);
        listView.setAdapter(deliveryListAdapter);




        deliveryDashboardAdapterr = new DeliveryDashboardAdapter(layouts, DeliveryDashboardActivity.this);
        pager.setAdapter(deliveryDashboardAdapterr);
        createSliderDash(0);


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void createSliderDash(int current_position) {
        if (slideLayout != null)
            slideLayout.removeAllViews();

        slider_dash = new ImageView[layouts.length];
        for (int i = 0; i < layouts.length; i++){
            slider_dash[i] = new ImageView(this);
            if (i == current_position){
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_slider_dash));
            }else{
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            slideLayout.setLayoutParams(params);


            slideLayout.addView(slider_dash[i], params);
        }
    }

    @Override
    public void signup() {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);


    }

    @Override
    public void history() {
        Intent intent = new Intent(getApplicationContext(), DeliveryHistoryActivity.class);
        startActivity(intent);

    }

    @Override
    public void delivery() {
        Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
        startActivity(intent);

    }
}
