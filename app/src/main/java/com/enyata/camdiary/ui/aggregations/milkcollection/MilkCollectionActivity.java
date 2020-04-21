package com.enyata.camdiary.ui.aggregations.milkcollection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.CreateAggregationRequest;
import com.enyata.camdiary.data.model.api.response.MilkCollectionDataResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityMilkCollectionBinding;
import com.enyata.camdiary.ui.aggregations.collection.success.CollectionSuccessActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import java.util.zip.Inflater;

import javax.inject.Inject;

public class MilkCollectionActivity extends BaseActivity<ActivityMilkCollectionBinding,MilkCollectionViewModel>implements MilkCollectionNavigator {

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    Gson gson;
    MilkCollectionViewModel milkCollectionViewModel;
    ActivityMilkCollectionBinding activityMilkCollectionBinding;
    AlertDialog.Builder dialog;
    AlertDialog alert1, alert2, alert3;
    TextView collectorName, email, volume, numberOfChurn,verificationId;
    View view1, view2, view3;
    Button milkProceed;
    EditText aggregatorVolume;
    String aggregatorVolumeText;
    String collectorId;
    String collectorsName;



    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_milk_collection;
    }

    @Override
    public MilkCollectionViewModel getViewModel() {

        milkCollectionViewModel = ViewModelProviders.of(this,factory).get(MilkCollectionViewModel.class);
        return milkCollectionViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        milkCollectionViewModel.setNavigator(this);
        activityMilkCollectionBinding = getViewDataBinding();

        collectorName = activityMilkCollectionBinding.collectorName;
        email = activityMilkCollectionBinding.collectorEmail;
        volume = activityMilkCollectionBinding.collectorVolume;
        numberOfChurn = activityMilkCollectionBinding.numberOfChurn;
        verificationId = activityMilkCollectionBinding.verificationId;
        milkProceed = activityMilkCollectionBinding.milkproceed;

        collectorId = milkCollectionViewModel.getCollectorId();

        if (InternetConnection.getInstance(this).isOnline()) {
            milkCollectionViewModel.getMilkCollectionData(collectorId);
        }else {
            Alert.showFailed(getApplicationContext(),"Please check your Internet Connection and try again");
        }
    }



    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
        startActivity(intent);

    }

    @Override
    public void onProceed() {
         collectorsName = milkCollectionViewModel.getCollectorName();
        dialog = new AlertDialog.Builder(MilkCollectionActivity.this);
        LayoutInflater inflater =  MilkCollectionActivity.this.getLayoutInflater();
        view1 = inflater.inflate(R.layout.aggregator_volume_layout,null);
        dialog.setView(view1);
        aggregatorVolume = view1.findViewById(R.id.aggregatorVolume);
        aggregatorVolumeText = aggregatorVolume.getText().toString();
        dialog.setCancelable(false);
        alert1 = dialog.create();
        alert1.show();

        TextView back = view1.findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert1.dismiss();
            }
        });
        TextView accept = view1.findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aggregatorVolumeText = aggregatorVolume.getText().toString();
                if (TextUtils.isEmpty(aggregatorVolumeText)){
                    Alert.showFailed(getApplicationContext(),"Please Enter Volume");
                    return;
                }else {
                    Log.i("AggregatorVolume", aggregatorVolumeText);
                view2 = inflater.inflate(R.layout.confirm_entry_layout, null);
                dialog.setView(view2);
                TextView message = view2.findViewById(R.id.message);
                message.setText("You have collected " + aggregatorVolumeText + " litres of product \nfrom " + collectorsName + " .\nPlease tap continue to confirm \nCollection");;
                TextView cancel = view2.findViewById(R.id.cancel);
                TextView continuee = view2.findViewById(R.id.continuee);
                alert2 = dialog.create();
                alert2.show();
                    cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                    }
                });

                continuee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismissAllmodal();
                        if (InternetConnection.getInstance(MilkCollectionActivity.this).isOnline()) {
                            CreateAggregationRequest.Request request = new CreateAggregationRequest.Request(collectorId, aggregatorVolume.getText().toString());
                            milkCollectionViewModel.createAggregation(request);
                        }else {
                            Alert.showFailed(getApplicationContext(),"Please Check your Internet and try again");
                        }

                    }
                });



                }
            }
        });

    }

    @Override
    public void handleError(Throwable throwable) {
       Log.i("THROWABLE", throwable.getMessage());

    }

    @Override
    public void getMilkCollectionData(MilkCollectionDataResponse response) {
        Log.i("Success", String.valueOf(response));
        milkProceed.setVisibility(View.VISIBLE);
        milkCollectionViewModel.setCollectorName(response.getCollector().getCollector().getFirstName() + " " +response.getCollector().getCollector().getLastName());
        collectorName.setText(String.format("%s %s", response.getCollector().getCollector().getFirstName(), response.getCollector().getCollector().getLastName()));
        email.setText(response.getCollector().getCollector().getEmail());
        volume.setText(response.getCollector().getTotalVolume());
        numberOfChurn.setText(response.getCollector().getNumberOfChurns());
        verificationId.setText(response.getCollector().getCollector().getVerificationId());


    }

    @Override
    public void onAggregationResponse(NewCollectionResponse response) {
        Log.i("Success", String.valueOf(response));
        Alert.showSuccess(getApplicationContext(), response.getResponseMessage());
        Intent intent = new Intent(getApplicationContext(), CollectionSuccessActivity.class);
        intent.putExtra("aggregationVolume", aggregatorVolumeText);
        startActivity(intent);


    }

    @Override
    public void aggregationHandleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            }
        }else{
            Alert.showFailed(getApplicationContext(), " Unable to connect to the internet");
        }

    }

    public void dismissAllmodal(){
        alert1.dismiss();
        alert2.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        milkCollectionViewModel.onDispose();
    }
}
