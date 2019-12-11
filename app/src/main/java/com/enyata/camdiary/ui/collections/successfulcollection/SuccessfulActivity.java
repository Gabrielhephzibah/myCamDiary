package com.enyata.camdiary.ui.collections.successfulcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivitySuccessfulBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;

import javax.inject.Inject;

public class SuccessfulActivity extends BaseActivity<ActivitySuccessfulBinding,SuccessfulViewModel>implements SuccessfulNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private SuccessfulViewModel successfulViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, FarmerDetailsActivity.class);
    }
    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_successful;
    }

    @Override
    public SuccessfulViewModel getViewModel() {
        successfulViewModel = ViewModelProviders.of(this,factory).get(SuccessfulViewModel.class);
        return successfulViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        successfulViewModel.setNavigator(this);
    }

    @Override
    public void home() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);

    }
}
