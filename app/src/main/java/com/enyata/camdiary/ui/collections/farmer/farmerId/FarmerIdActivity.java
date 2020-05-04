package com.enyata.camdiary.ui.collections.farmer.farmerId;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.Details;
import com.enyata.camdiary.data.model.api.response.EnterIdErrorResponse;
import com.enyata.camdiary.data.model.api.response.ResponseMessage;
import com.enyata.camdiary.databinding.ActivityFarmerIdBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;

public class  FarmerIdActivity extends BaseActivity<ActivityFarmerIdBinding,FarmerIdViewModel>implements FarmerIdNavigator {

    @Inject
    Gson gson;

    EditText farmerId;

    @Inject
    ViewModelProviderFactory factory;
    private FarmerIdViewModel farmerIdViewModel;
    ActivityFarmerIdBinding activityFarmerIdBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, FarmerIdActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_farmer_id;
    }

    @Override
    public FarmerIdViewModel getViewModel() {
        farmerIdViewModel = ViewModelProviders.of(this,factory).get(FarmerIdViewModel.class);
        return farmerIdViewModel;
    }

    @Override
    public void accept() {
        String verificationId = activityFarmerIdBinding.farmerId.getText().toString();


        if(TextUtils.isEmpty(verificationId)){
            Alert.showFailed(getApplicationContext()," Please enter farmer id");
            return;

        }else if (InternetConnection.getInstance(this).isOnline()){
            farmerIdViewModel.getFarmerDetails(verificationId);
        } else{
            Alert.showFailed(getApplicationContext(),"Please Check your Internet Connection and try again");
        }

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(DetailsResponse data) {
        Intent intent = new Intent(getApplicationContext(), FarmerDetailsActivity.class);
        Details response = data.getData();
        farmerIdViewModel.setFarmerId(String.valueOf(response.getId()));
        farmerIdViewModel.setFarmerFullName(response.getFirstName() + " " +response.getLastName());
        farmerIdViewModel.setFarmerCoperative(response.getCooperativeName());
        farmerIdViewModel.setFarmerPhoneNo(response.getContactNo());
        farmerIdViewModel.setFarmerVerificationId(response.getVerificationId());
        startActivity(intent);
    }


    @Override
    public void handleError(Throwable throwable) {
        try {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            ResponseMessage response = gson.fromJson(error.getErrorBody(), ResponseMessage.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getMessage()+ ", " + "Please enter a valid Id");
            }
        }else{
            Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
        }

        }catch (IllegalStateException | JsonSyntaxException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        farmerIdViewModel.setNavigator(this);
        activityFarmerIdBinding = getViewDataBinding();
       farmerId =activityFarmerIdBinding.farmerId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        farmerIdViewModel.dispose();
    }

}
