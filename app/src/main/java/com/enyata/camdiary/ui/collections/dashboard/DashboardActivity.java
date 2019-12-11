package com.enyata.camdiary.ui.collections.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectionDashboardBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity<ActivityCollectionDashboardBinding,DashboardViewModel>implements DashboardNavigator {

    DashboardAdapter dashboardAdapter;

    @Inject
    ViewModelProviderFactory factory;
    private DashboardViewModel dashboardViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, FarmerDetailsActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection_dashboard;
    }

    @Override
    public DashboardViewModel getViewModel() {
        dashboardViewModel = ViewModelProviders.of(this,factory).get(DashboardViewModel.class);
        return dashboardViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardViewModel.setNavigator(this);

    }
}
