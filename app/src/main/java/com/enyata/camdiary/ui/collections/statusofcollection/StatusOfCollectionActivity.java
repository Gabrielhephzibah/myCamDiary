package com.enyata.camdiary.ui.collections.statusofcollection;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.databinding.ActivityCollectionDashboardBinding;
import com.enyata.camdiary.databinding.ActivitySuccessfulBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;

import java.util.List;

import javax.inject.Inject;

public class StatusOfCollectionActivity extends BaseActivity<ActivitySuccessfulBinding, StatusOfCollectionViewModel>implements StatusOfCollectionNavigator {

 String volume;
 String respondCode;
 String firstName;
 String lastName;
 String fullName;
 String coperateName;
 TextView churnId, volumeText;
    String prefix;
    String volumePrefix;
    StringBuilder stringVolume;
    StringBuilder stringChurn;

    String verificationNumber;
 List<ChurnDetailsData>churnDetailsData;

    private ActivitySuccessfulBinding activitySuccessfulBinding;
    @Inject
    ViewModelProviderFactory factory;
    private StatusOfCollectionViewModel statusOfCollectionViewModel;

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
        statusOfCollectionViewModel = ViewModelProviders.of(this,factory).get(StatusOfCollectionViewModel.class);
        return statusOfCollectionViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusOfCollectionViewModel.setNavigator(this);
        fullName = statusOfCollectionViewModel.getFarmerFullName();
        activitySuccessfulBinding = getViewDataBinding();
        churnId = activitySuccessfulBinding.churnId;
        volumeText = activitySuccessfulBinding.volumeText;

        TextView farmerName = activitySuccessfulBinding.farmerName;
        churnDetailsData = statusOfCollectionViewModel.getChurnDetails();
        prefix = " ";
        volumePrefix = " ";
         stringVolume = new StringBuilder();
         stringChurn = new StringBuilder();

        for (int i = 0; i <churnDetailsData.size(); i ++) {
            ChurnDetailsData data = churnDetailsData.get(i);
            String textChurn = data.getChurnId();
            String textVolume = data.getVolume();


                stringChurn.append(textChurn);
                 stringChurn.append("\n");



                stringVolume.append(textVolume).append(" Litres");
                stringVolume.append("\n");
        }
        churnId.setText(stringChurn);
        volumeText.setText(stringVolume);
        farmerName.setText(fullName);



    }

    @Override
    public void home() {
        churnDetailsData = statusOfCollectionViewModel.getChurnDetails();
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
        statusOfCollectionViewModel.deleteChurnDetails(churnDetailsData);

    }
}
