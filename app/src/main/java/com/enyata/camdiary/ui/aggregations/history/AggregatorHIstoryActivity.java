package com.enyata.camdiary.ui.aggregations.history;

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
import com.enyata.camdiary.databinding.ActivityAggregatorHistoryBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.product.ProductViewModel;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.history.CollectorHistoryAdapter;
import com.enyata.camdiary.ui.collections.history.CollectorHistoryList;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class AggregatorHIstoryActivity extends BaseActivity<ActivityAggregatorHistoryBinding,AggregatorHistoryViewModel>implements AggregatorHistoryNavigator {

    AggregatorHistoryAdapter aggregatorHistoryAdapter;
    ListView listView;
    ArrayList<AggregatorHistoryList> aggregatorHistoryLists = new ArrayList<>();


    @Inject
    ViewModelProviderFactory factory;
    private AggregatorHistoryViewModel aggregatorHistoryViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorHIstoryActivity.class);
    }



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_aggregator_history;
    }

    @Override
    public AggregatorHistoryViewModel getViewModel() {
        aggregatorHistoryViewModel= ViewModelProviders.of(this,factory).get(AggregatorHistoryViewModel.class);
        return aggregatorHistoryViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aggregatorHistoryViewModel.setNavigator(this);


        listView = findViewById(R.id.listView);



        JSONObject collector1 = new JSONObject();
        try {
            collector1.put("fullName", "Akin, Solomon");

            collector1.put("companyId", "64ERT234KI89");

            collector1.put("myLitres", "40 litres");
            collector1.put("date", "23/08/2020");

        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject collector2 = new JSONObject();
        try {
            collector2.put("fullName", "Akin, Solomon");

            collector2.put("companyId", "64ERT234KI89");

            collector2.put("myLitres", "40 litres");
            collector2.put("date", "23/08/2020");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject collector3 = new JSONObject();
        try {
            collector3.put("fullName", "Akin, Solomon");

            collector3.put("companyId", "64ERT234KI89");

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
                String companyId= object.getString("companyId");

                String myLitres = object.getString("myLitres");
                String date = object.getString("date");



                aggregatorHistoryLists.add(new AggregatorHistoryList(fullName,companyId,myLitres,date));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        aggregatorHistoryAdapter = new AggregatorHistoryAdapter(AggregatorHIstoryActivity.this, aggregatorHistoryLists);
        listView.setAdapter(aggregatorHistoryAdapter);



    }

    @Override
    public void scan() {
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(),AggregatorDashboardActivity.class);
        startActivity(intent);

    }
}
