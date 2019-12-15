package com.enyata.camdiary.ui.deliveries.signcustomer.confirmsuccess;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivitySignSuccessBinding;

import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;

import javax.inject.Inject;

public class SignsuccessActivity extends BaseActivity<ActivitySignSuccessBinding, SignsuccessViewModel>implements SignsuccessNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private SignsuccessViewModel successViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SignsuccessViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_success;
    }

    @Override
    public SignsuccessViewModel getViewModel() {
        successViewModel = ViewModelProviders.of(this,factory).get(SignsuccessViewModel.class);
        return successViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        successViewModel.setNavigator(this);
    }

    @Override
    public void home() {
        Intent intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        startActivity(intent);

    }
}
