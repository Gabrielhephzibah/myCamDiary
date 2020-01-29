package com.enyata.camdiary.ui.aggregations.history;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
import com.enyata.camdiary.data.model.api.response.FarmerIdResponse;
import com.enyata.camdiary.databinding.ActivityAggregatorHistoryBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

public class AggregatorHIstoryActivity extends BaseActivity<ActivityAggregatorHistoryBinding,AggregatorHistoryViewModel>implements AggregatorHistoryNavigator {

    AggregatorHistoryAdapter aggregatorHistoryAdapter;
    ListView listView;
    ArrayList<AggregatorHistoryList> aggregatorHistoryLists = new ArrayList<>();


    @Inject
    Gson gson;

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
        aggregatorHistoryViewModel = ViewModelProviders.of(this, factory).get(AggregatorHistoryViewModel.class);
        return aggregatorHistoryViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aggregatorHistoryViewModel.setNavigator(this);
        activityAggregatorHistoryBinding = getViewDataBinding();
        listView = activityAggregatorHistoryBinding.listView;
        if (InternetConnection.getInstance(this).isOnline()){
            aggregatorHistoryViewModel.getAggretionHistory();
        }else {
            Alert.showFailed(getApplicationContext(),"Please Check your Internet Connection and try again");
        }



    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null ) {
            ANError error = (ANError) throwable;
            FarmerIdResponse response = gson.fromJson(error.getErrorBody(), FarmerIdResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            }else {
                Alert.showFailed(getApplicationContext(),"Unable to connect to the internet");
            }

        }

    }

    @Override
    public void scan() {
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), AggregatorDashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void onLogOut() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void getAggregatorHistory(AggregationCollectionResponse response) {
        for (AggregatorCollections history : response.getData()) {
            String[] formatted = history.getCreatedAt().split(" ");
            String[] formattedDate = formatted[0].split("-");
            String date = formattedDate[2] + "/" + formattedDate[1] + "/" + formattedDate[0];
            aggregatorHistoryLists.add(new AggregatorHistoryList(history.getCollectorDetails().getFirstName() + " " + history.getCollectorDetails().getLastName(), history.getCollectorDetails().getVerificationId(), history.getVolume() + " litres", date));
            aggregatorHistoryAdapter = new AggregatorHistoryAdapter(AggregatorHIstoryActivity.this, aggregatorHistoryLists);
            listView.setAdapter(aggregatorHistoryAdapter);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        aggregatorHistoryViewModel.dispose();
    }

}
