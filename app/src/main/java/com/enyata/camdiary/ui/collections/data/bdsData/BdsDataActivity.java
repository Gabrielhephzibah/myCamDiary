package com.enyata.camdiary.ui.collections.data.bdsData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityBdsDataBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;

import javax.inject.Inject;

public class BdsDataActivity extends BaseActivity<ActivityBdsDataBinding,BdsViewModel>implements BdsDataNavigator  {

    @Inject
    ViewModelProviderFactory factory;
    BdsViewModel bdsViewModel;
    ActivityBdsDataBinding activityBdsDataBinding;
    LinearLayout bioDataFragment, locationInfo, incomeSourceFragment, coperativeInfo, farmInfo;
    ImageView  bioDataToggle, locationToggle, incomeToggle, coperativeToggle, farmInfoToggle;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bds_data;
    }

    @Override
    public BdsViewModel getViewModel() {
        bdsViewModel = ViewModelProviders.of(this,factory).get(BdsViewModel.class);
        return bdsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bdsViewModel.setNavigator(this);
        activityBdsDataBinding = getViewDataBinding();
        bioDataFragment = activityBdsDataBinding.bioDataFragment;
        incomeSourceFragment = activityBdsDataBinding.incomeFragment;
        locationInfo = activityBdsDataBinding.locationFragment;
        coperativeInfo = activityBdsDataBinding.coperativeFragment;
        farmInfo = activityBdsDataBinding.farmInfoFragment;
        bioDataToggle = activityBdsDataBinding.bioDataToggle;
        incomeToggle = activityBdsDataBinding.incomeToggle;
        locationToggle = activityBdsDataBinding.locationToggle;
        coperativeToggle = activityBdsDataBinding.coprerativeToggle;
        farmInfoToggle = activityBdsDataBinding.farmInfoToggle;
    }

    @Override
    public void onBioData() {
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
        switch (bdsViewModel.getCurrentUserType()){
            case "collectors": {
                Intent intent = new Intent( getApplicationContext(), DataCollectionActivity.class);
                startActivity(intent);
                break;
            }

            case "data_collectors": {
                Intent intent = new Intent(getApplicationContext(), DataCollectorDashboardActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
