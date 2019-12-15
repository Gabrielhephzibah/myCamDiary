package com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityFinishBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;

import javax.inject.Inject;

public class FinishActivity extends BaseActivity<ActivityFinishBinding,FinishViewModel>implements FinishNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private FinishViewModel finishViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_finish;
    }

    @Override
    public FinishViewModel getViewModel() {
        finishViewModel = ViewModelProviders.of(this,factory).get(FinishViewModel.class);
        return finishViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishViewModel.setNavigator(this);
    }

    @Override
    public void finish() {
        Intent intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        startActivity(intent);
    }
}
