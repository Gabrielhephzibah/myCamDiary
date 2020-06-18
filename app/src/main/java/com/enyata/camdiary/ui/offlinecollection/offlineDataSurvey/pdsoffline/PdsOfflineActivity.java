package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.pdsoffline;

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
import com.enyata.camdiary.databinding.ActivityPdsOfflineBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;

import javax.inject.Inject;

public class PdsOfflineActivity extends BaseActivity<ActivityPdsOfflineBinding,PdsOfflineViewModel>implements PdsOfflineNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityPdsOfflineBinding activityPdsOfflineBinding;
    PdsOfflineViewModel pdsOfflineViewModel;
    LinearLayout incomeFragment, farmInfoFragment;
    ImageView incomeToggle, farmInfoToggle;


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pds_offline;
    }

    @Override
    public PdsOfflineViewModel getViewModel() {
        pdsOfflineViewModel = ViewModelProviders.of(this,factory).get(PdsOfflineViewModel.class);
        return pdsOfflineViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pdsOfflineViewModel.setNavigator(this);
        activityPdsOfflineBinding = getViewDataBinding();
        incomeFragment = activityPdsOfflineBinding.incomeFragment;
        farmInfoFragment = activityPdsOfflineBinding.farmInfoFragment;
        incomeToggle = activityPdsOfflineBinding.incomeToggle;
        farmInfoToggle = activityPdsOfflineBinding.farmInfoToggle;

    }

    @Override
    public void onIncomeSource() {
        hideKeyboard();
        if (incomeFragment.getVisibility()== View.GONE){
            incomeFragment.setVisibility(View.VISIBLE);
            incomeToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            incomeFragment.setVisibility(View.GONE);
            incomeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onFarmInfo() {
        hideKeyboard();
        if (farmInfoFragment.getVisibility()== View.GONE){
            farmInfoFragment.setVisibility(View.VISIBLE);
            farmInfoToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            farmInfoFragment.setVisibility(View.GONE);
            farmInfoToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onSubmitPds() {

    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);

    }
}
