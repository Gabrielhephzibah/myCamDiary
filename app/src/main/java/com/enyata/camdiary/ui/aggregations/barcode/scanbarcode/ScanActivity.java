package com.enyata.camdiary.ui.aggregations.barcode.scanbarcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityScanBinding;
import com.enyata.camdiary.ui.aggregations.barcode.collectorID.CollectorIdActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardViewModel;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;

import javax.inject.Inject;

public class ScanActivity extends BaseActivity<ActivityScanBinding,ScanViewModel>implements ScanNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private ScanViewModel scanViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    public ScanViewModel getViewModel() {
        scanViewModel = ViewModelProviders.of(this,factory).get(ScanViewModel.class);
        return scanViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanViewModel.setNavigator(this);

    }

    @Override
    public void scan() {
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void enterId() {
        Intent intent = new Intent(getApplicationContext(), CollectorIdActivity.class);
        startActivity(intent);

    }
}
