package com.enyata.camdiary.ui.collections.farmer.farmerDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
    @Inject
    ViewModelProviderFactory factory;
    private FarmerDetailsViewModel farmerDetailsViewModel;

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
}
