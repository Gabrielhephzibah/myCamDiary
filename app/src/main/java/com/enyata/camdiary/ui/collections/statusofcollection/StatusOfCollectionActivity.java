package com.enyata.camdiary.ui.collections.statusofcollection;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectionDashboardBinding;
import com.enyata.camdiary.databinding.ActivitySuccessfulBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;

import javax.inject.Inject;

public class StatusOfCollectionActivity extends BaseActivity<ActivitySuccessfulBinding, StatusOfCollectionViewModel>implements StatusOfCollectionNavigator {

 String volume;
 String respondCode;
    private ActivitySuccessfulBinding activitySuccessfulBinding;
    @Inject
    ViewModelProviderFactory factory;
    private StatusOfCollectionViewModel successfulViewModel;

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
    public StatusOfCollectionViewModel getViewModel() {
        successfulViewModel = ViewModelProviders.of(this,factory).get(StatusOfCollectionViewModel.class);
        return successfulViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        successfulViewModel.setNavigator(this);
        volume = getIntent().getStringExtra("volume");
        respondCode = getIntent().getStringExtra("responseCode");
        activitySuccessfulBinding = getViewDataBinding();
        TextView litresCollected  = activitySuccessfulBinding.litresCollected;
        litresCollected.setText(volume+" Litres");

    }

    @Override
    public void home() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);

    }
}
