package com.enyata.camdiary.ui.aggregations.history;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.AggregationHistory;
import com.enyata.camdiary.data.model.api.response.AggregationHistoryResponse;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
import com.enyata.camdiary.data.model.api.response.FarmerIdResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityAggregatorHistoryBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AggregatorHIstoryActivity extends BaseActivity<ActivityAggregatorHistoryBinding,AggregatorHistoryViewModel>implements AggregatorHistoryNavigator {

    AggregatorHistoryAdapter aggregatorHistoryAdapter;
    ListView listView;
    ArrayList<AggregatorHistoryList> aggregatorHistoryLists = new ArrayList<>();
    ImageView image;
    CFAlertDialog alert;


    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private AggregatorHistoryViewModel aggregatorHistoryViewModel;
    ActivityAggregatorHistoryBinding activityAggregatorHistoryBinding;
    ArrayList<AggregationItemInterface> aggregationList = new ArrayList<AggregationItemInterface>();

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
        TextView aggregatorName =  activityAggregatorHistoryBinding.aggregatorName;
        aggregatorName.setText("Hey"+ "," + aggregatorHistoryViewModel.getCurrentUser());
        image = findViewById(R.id.aggregatorImage);
        String aggregatorImage = aggregatorHistoryViewModel.getAggregatorImage();
        Picasso.get().load(aggregatorImage).into(image);


        if (InternetConnection.getInstance(this).isOnline()){
            aggregatorHistoryViewModel.getAggregationHistory();
        }else {
            Alert.showFailed(getApplicationContext(),"Please Check your Internet Connection and try again");
        }



    }

    @Override
    public void handleError(Throwable throwable) {
        try {
        if (throwable != null ) {
            ANError error = (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            }else {
                Alert.showFailed(getApplicationContext(),"Unable to connect to the internet");
            }

        }
        }catch (IllegalStateException | JsonSyntaxException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
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
        CFAlertDialog.Builder alertDialog = new CFAlertDialog.Builder(this);
        LayoutInflater inflater = AggregatorHIstoryActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout_notification_sheet,null);
        alertDialog .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)
                .setCancelable(false)
                .setHeaderView(dialogView);
        Button yes = dialogView.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Button no = dialogView.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        alert = alertDialog.create();
        alert.show();

    }

    @Override
    public void getAggregationHistory(AggregationHistoryResponse response) {
        try {


            for (AggregationHistory history : response.getAggregationHistory()) {
                String[] formatted = history.getDate().split(" ");
                String[] formattedDate = formatted[0].split("-");
                String date = formattedDate[2] + "/" + formattedDate[1] + "/" + formattedDate[0];

                aggregationList.add(new AggregationHistoryHeader(date));
                List<AggregatorCollections> aggregatorCollections = history.getAggregatorCollections();
                for (int i = 0; i < aggregatorCollections.size(); i++) {
                    AggregatorCollections collections = aggregatorCollections.get(i);
                    String firstName = collections.getCollectorDetails().getFirstName();
                    String lastName = collections.getCollectorDetails().getLastName();
                    String verificationId = collections.getCollectorDetails().getVerificationId();
                    String volume = collections.getAggregationTotalVolume();
                    String image = collections.getCollectorDetails().getImageUrl();

                    aggregationList.add(new AggregatorHistory(firstName + " " + lastName, verificationId, volume + " litres", image));
                    AggregationCustomAdapter customAdapter = new AggregationCustomAdapter(AggregatorHIstoryActivity.this, aggregationList);
                    listView.setAdapter(customAdapter);


                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.i("unknown error", e.getMessage());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        aggregatorHistoryViewModel.dispose();
    }

}
