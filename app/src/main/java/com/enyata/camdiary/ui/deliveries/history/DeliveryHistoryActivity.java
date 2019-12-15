package com.enyata.camdiary.ui.deliveries.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDeliveryHistoryBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;

import javax.inject.Inject;

public class DeliveryHistoryActivity extends BaseActivity<ActivityDeliveryHistoryBinding,DeliveryHistoryViewModel>implements DeliveryHistoryNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private DeliveryHistoryViewModel deliveryHistoryViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryHistoryActivity.class);
    }



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery_history;
    }

    @Override
    public DeliveryHistoryViewModel getViewModel() {
        deliveryHistoryViewModel = ViewModelProviders.of(this,factory).get(DeliveryHistoryViewModel.class);
        return deliveryHistoryViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryHistoryViewModel.setNavigator(this);
    }

    @Override
    public void signup() {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);

    }

    @Override
    public void delivery() {
        Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
        startActivity(intent);

    }
}
