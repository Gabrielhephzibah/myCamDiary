package com.enyata.camdiary.ui.collections.data.dataScanBarcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDataScanCodeBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.data.dataFarmerId.DataFarmerIdActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.ui.scanbarcode.dataCollectorScanBarcode.DataCollectorBarcodeActivity;

import javax.inject.Inject;

public class DataScanCodeActivity extends  BaseActivity<ActivityDataScanCodeBinding, DataScanCodeViewModel>implements DataScanCodeNavigator {
    @Inject
    ViewModelProviderFactory factory;
    DataScanCodeViewModel dataScanCodeViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_scan_code;
    }

    @Override
    public DataScanCodeViewModel getViewModel() {
        dataScanCodeViewModel = ViewModelProviders.of(this, factory).get(DataScanCodeViewModel.class);
        return dataScanCodeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       dataScanCodeViewModel.setNavigator(this);
    }

    @Override
    public void onFarmerId() {
        Intent intent = new Intent(getApplicationContext(), DataFarmerIdActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBack() {
        switch (dataScanCodeViewModel.getCurrentUserType()){
            case "collector": {
                Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
                startActivity(intent);
                break;
            }
            case "data collector":{
                Intent intent = new Intent(getApplicationContext(), DataCollectorDashboardActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onScanBarCode() {
        Intent intent = new Intent(getApplicationContext(), DataCollectorBarcodeActivity.class);
        startActivity(intent);
    }
}
