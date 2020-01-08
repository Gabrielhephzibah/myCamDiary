package com.enyata.camdiary.ui.collections.farmer.farmerId;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.Details;
import com.enyata.camdiary.databinding.ActivityFarmerIdBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;

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
        String id = activityFarmerIdBinding.farmerId.getText().toString();
        if(TextUtils.isEmpty(id)){
            Alert.showFailed(getApplicationContext(),"Please enter farmer id");
            return;
        }

        farmerIdViewModel.getFarmerDetails(id);


    }

    @Override
    public void onResponse(DetailsResponse data) {
        Intent intent = new Intent(getApplicationContext(), FarmerDetailsActivity.class);
        Details response = data.getData();

        intent.putExtra("first_name",response.getFirstName());
        intent.putExtra("last_name",response.getLastName());
        intent.putExtra("phone_no", response.getContactNo());
        intent.putExtra("coperate_name", response.getCooperativeName());
        intent.putExtra("farmer_id",response.getVerificationId());
        intent.putExtra("farmer_identity",response.getId());
        startActivity(intent);
    }


    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            Details response = gson.fromJson(error.getErrorBody(), Details.class);
            Alert.showFailed(getApplicationContext(), response.getError());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        farmerIdViewModel.setNavigator(this);
        activityFarmerIdBinding = getViewDataBinding();
        farmerId = activityFarmerIdBinding.farmerId;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        farmerIdViewModel.dispose();
    }
}
