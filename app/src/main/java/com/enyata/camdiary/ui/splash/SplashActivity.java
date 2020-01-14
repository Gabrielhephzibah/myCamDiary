

package com.enyata.camdiary.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivitySplashBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.login.LoginActivity;

import javax.inject.Inject;

/**
 * Created by Sanni Michael Tomiwa 3/12/2019.
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    ViewModelProviderFactory factory;
    
    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this,factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openDashboardActivity(String type) {
        Intent intent;
        if(type.equals("collector")){
            intent = new Intent(getApplicationContext(),DashboardActivity.class);
        }else if(type.equals("aggregator")){
            intent = new Intent(getApplicationContext(), AggregatorDashboardActivity.class);
        }else{
            intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.decideNextActivity();
    }
}
