package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.bdsoffline;

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
import com.enyata.camdiary.databinding.ActivityBdsOfflineBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;

import javax.inject.Inject;

public class BdsOfflineActivity extends BaseActivity<ActivityBdsOfflineBinding, BdsOfflineViewModel>implements BdsOfflineNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityBdsOfflineBinding activityBdsOfflineBinding;
    BdsOfflineViewModel bdsOfflineViewModel;
    LinearLayout bioDataFragment, locationInfo, incomeSourceFragment, coperativeInfo, farmInfo;
    ImageView bioDataToggle, locationToggle, incomeToggle, coperativeToggle, farmInfoToggle;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bds_offline;
    }

    @Override
    public BdsOfflineViewModel getViewModel() {
        bdsOfflineViewModel = ViewModelProviders.of(this,factory).get(BdsOfflineViewModel.class);
        return bdsOfflineViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bdsOfflineViewModel.setNavigator(this);
        activityBdsOfflineBinding = getViewDataBinding();

        bioDataFragment = activityBdsOfflineBinding.bioDataFragment;
        incomeSourceFragment = activityBdsOfflineBinding.incomeFragment;
        locationInfo = activityBdsOfflineBinding.locationFragment;
        coperativeInfo = activityBdsOfflineBinding.coperativeFragment;
        farmInfo = activityBdsOfflineBinding.farmInfoFragment;
        bioDataToggle = activityBdsOfflineBinding.bioDataToggle;
        incomeToggle = activityBdsOfflineBinding.incomeToggle;
        locationToggle = activityBdsOfflineBinding.locationToggle;
        coperativeToggle = activityBdsOfflineBinding.coprerativeToggle;
        farmInfoToggle = activityBdsOfflineBinding.farmInfoToggle;

    }

    @Override
    public void onBioData() {
        hideKeyboard();
        if (bioDataFragment.getVisibility()== View.GONE){
            bioDataFragment.setVisibility(View.VISIBLE);
            bioDataToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            bioDataFragment.setVisibility(View.GONE);
            bioDataToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onLocationInfo() {
        hideKeyboard();
        if (locationInfo.getVisibility()== View.GONE){
            locationInfo.setVisibility(View.VISIBLE);
            locationToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            locationInfo.setVisibility(View.GONE);
            locationToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onIncomeSource() {
        hideKeyboard();
        if (incomeSourceFragment.getVisibility()== View.GONE){
            incomeSourceFragment.setVisibility(View.VISIBLE);
            incomeToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            incomeSourceFragment.setVisibility(View.GONE);
            incomeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onCoperativeInfo() {
        hideKeyboard();
        if (coperativeInfo.getVisibility()== View.GONE){
            coperativeInfo.setVisibility(View.VISIBLE);
            coperativeToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            coperativeInfo.setVisibility(View.GONE);
            coperativeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onFarmInfo() {
        hideKeyboard();
        if (farmInfo.getVisibility()== View.GONE){
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

    @Override
    public void onUploadPicture() {

    }

    @Override
    public void onSubmitBds() {

    }
}
