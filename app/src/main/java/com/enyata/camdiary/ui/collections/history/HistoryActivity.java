package com.enyata.camdiary.ui.collections.history;

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
import com.enyata.camdiary.databinding.ActivityHistoryBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorAdapter;
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorList;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryActivity;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryViewModel;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class HistoryActivity extends BaseActivity<ActivityHistoryBinding,HistoryViewModel>implements HistoryNavigator {

    CollectorHistoryAdapter collectorHistoryAdapter;
    ListView listView;
    ArrayList<CollectorHistoryList> collectorHistoryLists = new ArrayList<>();



    @Inject
    ViewModelProviderFactory factory;
    private HistoryViewModel historyViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, HistoryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public HistoryViewModel getViewModel() {
        historyViewModel = ViewModelProviders.of(this,factory).get(HistoryViewModel.class);
        return historyViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyViewModel.setNavigator(this);
        listView = findViewById(R.id.listView);



        JSONObject collector1 = new JSONObject();
        try {
            collector1.put("fullName", "Akin, Solomon");
            collector1.put("companyName", "Xamsatde");
            collector1.put("companyId", "X3478JND8992");
            collector1.put("status", "Rejected");
            collector1.put("myLitres", "40 litres");
            collector1.put("date", "23/08/2020");

        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject collector2 = new JSONObject();
        try {
            collector2.put("fullName", "Akin, Solomon");
            collector2.put("companyName", "Xamsatde");
            collector2.put("companyId", "X3478JND8992");
            collector2.put("status", "Rejected");
            collector2.put("myLitres", "40 litres");
            collector2.put("date", "23/08/2020");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject collector3 = new JSONObject();
        try {
            collector3.put("fullName", "Akin, Solomon");
            collector3.put("companyName", "Xamsatde");
            collector3.put("companyId", "X3478JND8992");
            collector3.put("status", "Rejected");
            collector3.put("myLitres", "40 litres");
            collector3.put("date", "23/08/2020");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray array = new JSONArray();
        array.put(collector1);
        array.put(collector2);
        array.put(collector3);
        array.put(collector2);
        array.put(collector1);
        array.put(collector3);


        for (int i = 0; i < array.length(); i++) {

            try {
                Log.i("message", array.toString());

                JSONObject object = array.getJSONObject(i);
                String fullName = object.getString("fullName");
                String companyName = object.getString("companyName");
                String companyId= object.getString("companyId");
                String status= object.getString("status");
                String myLitres = object.getString("myLitres");
                String date = object.getString("date");



                collectorHistoryLists.add(new CollectorHistoryList(fullName,companyName,companyId,status,myLitres,date));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        collectorHistoryAdapter = new CollectorHistoryAdapter(HistoryActivity.this, collectorHistoryLists);
        listView.setAdapter(collectorHistoryAdapter);



    }

    @Override
    public void dataCollection() {
        Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
        startActivity(intent);

    }

    @Override
    public void scan() {

        Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
        startActivity(intent);

    }

    @Override
    public void back() {
        Intent back = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(back);

    }
}
