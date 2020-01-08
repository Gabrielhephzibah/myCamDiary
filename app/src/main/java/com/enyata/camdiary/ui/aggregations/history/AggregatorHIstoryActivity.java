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
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
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
    ActivityAggregatorHistoryBinding activityAggregatorHistoryBinding;

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


        activityAggregatorHistoryBinding = getViewDataBinding();
        listView = activityAggregatorHistoryBinding.listView;

        aggregatorHistoryViewModel.getAggretionHistory();



    }

    @Override
    public void handleError(Throwable throwable) {

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

    @Override
    public void getAggregatorHistory(AggregationCollectionResponse response) {
        for (AggregatorCollections history : response.getData()){
            String[] formatted = history.getCreatedAt().split(" ");
            String[] formattedDate = formatted[0].split("-");
            String date = formattedDate[2] +"/"+formattedDate[1]+"/"+formattedDate[0];
            aggregatorHistoryLists .add(new AggregatorHistoryList(history.getCollectorDetails().getFirstName() + " "+ history.getCollectorDetails().getLastName(),history.getCollectorDetails().getVerificationId(), history.getVolume()+ " litres", date));
            aggregatorHistoryAdapter = new AggregatorHistoryAdapter(AggregatorHIstoryActivity.this,aggregatorHistoryLists);
            listView.setAdapter(aggregatorHistoryAdapter);
        }

    }
}
