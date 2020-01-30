package com.enyata.camdiary.ui.collections.history;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.databinding.ActivityHistoryBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

public class HistoryActivity extends BaseActivity<ActivityHistoryBinding,HistoryViewModel>implements HistoryNavigator {

    @Inject
    Gson gson;

    CollectorHistoryAdapter collectorHistoryAdapter;
    ListView listView;
    ArrayList<CollectorHistoryList> collectorHistoryLists = new ArrayList<>();
    ActivityHistoryBinding activityHistoryBinding;

    RelativeLayout data;



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
        activityHistoryBinding =getViewDataBinding();
        data = activityHistoryBinding.data;

        if (historyViewModel.getUserType().equals("data_collectors")){
            data.setVisibility(View.VISIBLE);
        }

        listView = activityHistoryBinding.listView;
        
       if (InternetConnection.getInstance(this).isOnline()){
           historyViewModel.getAllCollection();
       }else {
           Alert.showFailed(getApplicationContext(), "Please Check Your Internet Connection and try again later");
       }




    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            VolumeResponse response = gson.fromJson(error.getErrorBody(), VolumeResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            }else {
                Alert.showFailed(getApplicationContext(), "Unable to connect to the Internet");
            }

        }

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

    @Override
    public void getAllCollections(CollectionResponse allCollections) {
        for (Collection response : allCollections.getData()) {
            String[] formatted = response.getCreatedAt().split(" ");
            String[] formattedDate = formatted[0].split("-");
            String date = formattedDate[2] +"/"+formattedDate[1]+"/"+formattedDate[0];
            collectorHistoryLists.add(new CollectorHistoryList(response.getFarmer().getFirstName()+  "  " + response.getFarmer().getLastName(),response.getFarmer().getCooperativeName(),response.getFarmer().getVerificationId(), response.getStatusOfCollection(), response.getVolume()+ " litres",  date));
            collectorHistoryAdapter = new CollectorHistoryAdapter(HistoryActivity.this, collectorHistoryLists);
            listView.setAdapter(collectorHistoryAdapter);
        }

    }

    @Override
    public void logout() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        historyViewModel.dispose();
    }
}
