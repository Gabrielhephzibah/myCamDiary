package com.enyata.camdiary.ui.collections.farmer.farmerId;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityFarmerIdBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;

import javax.inject.Inject;

public class FarmerIdActivity extends BaseActivity<ActivityFarmerIdBinding,FarmerIdViewModel>implements FarmerIdNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private FarmerIdViewModel farmerIdViewModel;

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
        Intent intent = new Intent(getApplicationContext(), FarmerDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        farmerIdViewModel.setNavigator(this);
    }
}
