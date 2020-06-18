package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.cdsoffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCdsOfflineBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;

import javax.inject.Inject;

public class CdsOfflineActivity extends BaseActivity<ActivityCdsOfflineBinding,CdsOfflineViewModel>implements CdsOfflineNavigator {
   @Inject
    ViewModelProviderFactory  factory;
   CdsOfflineViewModel cdsOfflineViewModel;
   ActivityCdsOfflineBinding activityCdsOfflineBinding;
    LinearLayout bioData,locationInfo, incomeSource, farmInfo;
    ImageView biodataToggle,incomeToggle, locationToggle,farmInfoToggle;




    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cds_offline;
    }

    @Override
    public CdsOfflineViewModel getViewModel() {
        cdsOfflineViewModel = ViewModelProviders.of(this,factory).get(CdsOfflineViewModel.class);
        return cdsOfflineViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cdsOfflineViewModel.setNavigator(this);
        activityCdsOfflineBinding = getViewDataBinding();
        bioData = activityCdsOfflineBinding.bioDataFragment;
        locationInfo = activityCdsOfflineBinding.locationFragment;
        incomeSource = activityCdsOfflineBinding.incomeFragment;
        farmInfo = activityCdsOfflineBinding.farmInfoFragment;
        biodataToggle = activityCdsOfflineBinding.bioDataToggle;
        farmInfoToggle = activityCdsOfflineBinding.farmInfoToggle;
        locationToggle = activityCdsOfflineBinding.locationToggle;
        incomeToggle = activityCdsOfflineBinding.incomeToggle;
    }

    @Override
    public void onBioData() {
        hideKeyboard();
        if (bioData.getVisibility()== View.GONE){
            bioData.setVisibility(View.VISIBLE);
            biodataToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            bioData.setVisibility(View.GONE);
            biodataToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onLocationInfo() {
        hideKeyboard();
        if (locationInfo.getVisibility()==View.GONE){
            locationInfo.setVisibility(View.VISIBLE);
            locationToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            locationInfo.setVisibility(View.GONE);
            locationToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onIncomeSources() {
        hideKeyboard();
        if (incomeSource.getVisibility()==View.GONE){
            incomeSource.setVisibility(View.VISIBLE);
            incomeToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            incomeSource.setVisibility(View.GONE);
            incomeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onFarmInfo() {
        if (farmInfo.getVisibility()==View.GONE){
            farmInfo.setVisibility(View.VISIBLE);
            farmInfoToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            farmInfo.setVisibility(View.GONE);
            farmInfoToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);

    }
}
