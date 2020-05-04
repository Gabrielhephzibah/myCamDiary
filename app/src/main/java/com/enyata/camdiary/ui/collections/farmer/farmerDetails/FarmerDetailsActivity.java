package com.enyata.camdiary.ui.collections.farmer.farmerDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityFarmerDetailsBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;

import javax.inject.Inject;

public class FarmerDetailsActivity extends BaseActivity<ActivityFarmerDetailsBinding, FarmerDetailsViewModel>implements FarmerDetailsNavigator {

    String firstName;
   String phonenumber;
   String coperateName;
   String verificationNumber;
   String lastname;
   String farmer_id;

    @Inject
    ViewModelProviderFactory factory;
    private FarmerDetailsViewModel farmerDetailsViewModel;
    ActivityFarmerDetailsBinding activityFarmerDetailsBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, FarmerDetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_farmer_details;
    }

    @Override
    public FarmerDetailsViewModel getViewModel() {
        farmerDetailsViewModel = ViewModelProviders.of(this,factory).get(FarmerDetailsViewModel.class);
        return farmerDetailsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        farmerDetailsViewModel.setNavigator(this);
        activityFarmerDetailsBinding = getViewDataBinding();

        firstName = farmerDetailsViewModel.getFarmerFullName();
        phonenumber = farmerDetailsViewModel.getFramerPhoneNo();
        coperateName = farmerDetailsViewModel.getFarmerCoperative();
        verificationNumber = farmerDetailsViewModel.getFarmerVerificationNo();

        TextView farmerName = activityFarmerDetailsBinding.farmerName;
        TextView farmerNumber = activityFarmerDetailsBinding.farmerNumber;
        TextView coperativeName = activityFarmerDetailsBinding.coperateName;
        TextView verificationNo = activityFarmerDetailsBinding.verificationNumber;

        farmerName.setText(firstName);
        farmerNumber.setText(phonenumber);
        coperativeName.setText(coperateName);
        verificationNo.setText(verificationNumber);


    }

    @Override
    public void proceed() {
        Intent intent =  new Intent(getApplicationContext(), EnterVolumeActivity.class);
        startActivity(intent);
    }

    @Override
    public void goscan() {
        Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        farmerDetailsViewModel.dispose();
    }
}
