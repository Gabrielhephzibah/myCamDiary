package com.enyata.camdiary.ui.collections.barcode;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityBarcodeBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdActivity;
import com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode.CollectorScanBarCode;

import javax.inject.Inject;


public class BarcodeActivity  extends BaseActivity<ActivityBarcodeBinding, BarcodeViewModel> implements BarcodeNavigator  {

    @Inject
    ViewModelProviderFactory factory;
    private BarcodeViewModel barcodeViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, BarcodeActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_barcode;
    }

    @Override
    public BarcodeViewModel getViewModel() {
        barcodeViewModel = ViewModelProviders.of(this,factory).get(BarcodeViewModel.class);
        return barcodeViewModel;
    }

    @Override
    public void barcodeClick() {

    }


    @Override
    public void scan() {
        Intent intent = new Intent(getApplicationContext(), CollectorScanBarCode.class);
        startActivity(intent);
    }

    @Override
    public void enterid() {
        Intent intent = new Intent(getApplicationContext(), FarmerIdActivity.class);
        startActivity(intent);

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barcodeViewModel.setNavigator(this);
    }
}
