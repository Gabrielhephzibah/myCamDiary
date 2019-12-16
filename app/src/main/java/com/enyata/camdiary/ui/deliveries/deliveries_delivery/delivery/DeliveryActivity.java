package com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDeliveryBinding;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHistoryAdapter;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHistoryList;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardNavigator;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryList;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class DeliveryActivity extends BaseActivity<ActivityDeliveryBinding,DeliveryViewModel>implements DeliveryNavigator {




    DeliveryItemAdapter deliveryItemAdapter;
    ListView listView;
    ArrayList<DeliveryItemList> deliveryItemLists = new ArrayList<>();


    @Inject
    ViewModelProviderFactory factory;
    private DeliveryViewModel deliveryViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery;
    }

    @Override
    public DeliveryViewModel getViewModel() {
        deliveryViewModel = ViewModelProviders.of(this,factory).get(DeliveryViewModel.class);
        return deliveryViewModel;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryViewModel.setNavigator(this);
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

                deliveryItemLists.add(new DeliveryItemList(myName,items,number,itemId,date));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        deliveryItemAdapter= new DeliveryItemAdapter(DeliveryActivity.this, deliveryItemLists);
        listView.setAdapter(deliveryItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                startActivity(intent);
            }
        });




    }


    @Override
    public void click() {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        startActivity(intent);

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        startActivity(intent);

    }
}
