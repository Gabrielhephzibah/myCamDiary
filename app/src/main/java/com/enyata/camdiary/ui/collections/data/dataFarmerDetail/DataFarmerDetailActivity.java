package com.enyata.camdiary.ui.collections.data.dataFarmerDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDataFarmerDetailBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.data.dataScanBarcode.DataScanCodeActivity;
import com.enyata.camdiary.ui.collections.data.pdsData.PdsDataActivity;

import javax.inject.Inject;

public class DataFarmerDetailActivity extends BaseActivity<ActivityDataFarmerDetailBinding,DataFarmerDetailViewModel>implements DataFarmerDetailNavigator {
    @Inject
    ViewModelProviderFactory factory;
    DataFarmerDetailViewModel dataFarmerDetailViewModel;
    TextView farmerName, farmerNumber, farmerCoperateName, farmerVerifationId;
    ActivityDataFarmerDetailBinding activityDataFarmerDetailBinding;
    String firstName;
    String phonenumber;
    String coperateName;
    String verificationNumber;



    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_farmer_detail;
    }

    @Override
    public DataFarmerDetailViewModel getViewModel() {
        dataFarmerDetailViewModel = ViewModelProviders.of(this,factory).get(DataFarmerDetailViewModel.class);
        return dataFarmerDetailViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataFarmerDetailViewModel.setNavigator(this);
        activityDataFarmerDetailBinding = getViewDataBinding();
        farmerName = activityDataFarmerDetailBinding.farmerName;
        farmerNumber = activityDataFarmerDetailBinding.farmerNumber;
        farmerCoperateName = activityDataFarmerDetailBinding.coperateName;
        farmerVerifationId = activityDataFarmerDetailBinding.verificationNumber;

        firstName = dataFarmerDetailViewModel.getFarmerFullName();
        phonenumber = dataFarmerDetailViewModel.getFramerPhoneNo();
        coperateName = dataFarmerDetailViewModel.getFarmerCoperative();
        verificationNumber = dataFarmerDetailViewModel.getFarmerVerificationNo();

        farmerName.setText(firstName);
        farmerNumber.setText(phonenumber);
        farmerCoperateName.setText(coperateName);
        farmerVerifationId.setText(verificationNumber);




    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), DataScanCodeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onProceed() {
        Intent intent = new Intent(getApplicationContext(), PdsDataActivity.class);
        startActivity(intent);
    }
}
