package com.enyata.camdiary.ui.deliveries.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDeliveryHistoryBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryItemAdapter;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryItemList;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class DeliveryHistoryActivity extends BaseActivity<ActivityDeliveryHistoryBinding,DeliveryHistoryViewModel>implements DeliveryHistoryNavigator {


    DeliveryHistoryAdapter deliveryHistoryAdapter;
    ListView listView;
    ArrayList<DeliveryHistoryList> deliveryHistoryLists= new ArrayList<>();


    @Inject
    ViewModelProviderFactory factory;
    private DeliveryHistoryViewModel deliveryHistoryViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryHistoryActivity.class);
    }



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery_history;
    }

    @Override
    public DeliveryHistoryViewModel getViewModel() {
        deliveryHistoryViewModel = ViewModelProviders.of(this,factory).get(DeliveryHistoryViewModel.class);
        return deliveryHistoryViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryHistoryViewModel.setNavigator(this);
        listView = findViewById(R.id.listView);


        JSONObject delivery1 = new JSONObject();
        try {
            delivery1.put("myName", "Devon, Samuel");

            delivery1.put("items", "4 items");

            delivery1.put("number", "+2348033237685");

            delivery1.put("itemId", "64ERT234KI89");

            delivery1.put("date","23/08/2020");

        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject delivery2 = new JSONObject();
        try {
            delivery2.put("myName", "Devon, Samuel");

            delivery2.put("items", "4 items");

            delivery2.put("number", "+2348033237685");

            delivery2.put("itemId", "64ERT234KI89");

            delivery2.put("date","23/08/2020");

        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject delivery3 = new JSONObject();
        try {
            delivery3.put("myName", "Devon, Samuel");

            delivery3.put("items", "4 items");

            delivery3.put("number", "+2348033237685");

            delivery3.put("itemId", "64ERT234KI89");

            delivery3.put("date","23/08/2020");

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

                String date = object.getString("date");

                deliveryHistoryLists.add(new DeliveryHistoryList(myName,items,number,itemId,date));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        deliveryHistoryAdapter= new DeliveryHistoryAdapter(DeliveryHistoryActivity.this, deliveryHistoryLists);
        listView.setAdapter(deliveryHistoryAdapter);





    }

    @Override
    public void signup() {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);

    }

    @Override
    public void delivery() {
        Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
        startActivity(intent);

    }
}
