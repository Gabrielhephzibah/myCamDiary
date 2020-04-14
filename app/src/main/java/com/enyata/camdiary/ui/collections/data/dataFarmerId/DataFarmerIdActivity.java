package com.enyata.camdiary.ui.collections.data.dataFarmerId;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.Details;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.ResponseMessage;
import com.enyata.camdiary.databinding.ActivityDataFarmerIdBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.data.dataFarmerDetail.DataFarmerDetailActivity;
import com.enyata.camdiary.ui.collections.data.dataScanBarcode.DataScanCodeActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DataFarmerIdActivity extends BaseActivity<ActivityDataFarmerIdBinding,DataFarmerIdViewModel>implements DataFarmerIdNavigator {
    @Inject
    Gson gson;
    @Inject
    ViewModelProviderFactory factory;
    DataFarmerIdViewModel dataFarmerIdViewModel;
    EditText farmerId;
    ActivityDataFarmerIdBinding activityDataFarmerIdBinding;
    String verificationId;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_farmer_id;
    }

    @Override
    public DataFarmerIdViewModel getViewModel() {
        dataFarmerIdViewModel = ViewModelProviders.of(this,factory).get(DataFarmerIdViewModel.class);
        return  dataFarmerIdViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataFarmerIdViewModel.setNavigator(this);
        activityDataFarmerIdBinding = getViewDataBinding();
        farmerId = activityDataFarmerIdBinding.dataFarmerId;


    }

    @Override
    public void onAccept() {
        verificationId = activityDataFarmerIdBinding.dataFarmerId.getText().toString();
        if (TextUtils.isEmpty(verificationId)){
            Alert.showFailed(getApplicationContext(),"Please enter farmer id");
            return;
        }else if (InternetConnection.getInstance(this).isOnline()){
                dataFarmerIdViewModel.getFarmerDetails(verificationId);
            }else {
                Alert.showFailed(getApplicationContext(),"Please Check your Internet connection and try again");
            }




    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), DataScanCodeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(DetailsResponse details) {
        Intent intent = new Intent(getApplicationContext(), DataFarmerDetailActivity.class);
        Details response = details.getData();
        dataFarmerIdViewModel.setFarmerFullName(response.getFirstName() + " " +response.getLastName());
        dataFarmerIdViewModel.setFarmerCoperative(response.getCooperativeName());
        dataFarmerIdViewModel.setFarmerPhoneNo(response.getContactNo());
        dataFarmerIdViewModel.setFarmerVerificationId(response.getVerificationId());
        startActivity(intent);

    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            ResponseMessage response = gson.fromJson(error.getErrorBody(), ResponseMessage.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getMessage()+ ", " + "Please enter a valid Id");
            }
        }else{
            Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
        }

    }
}
