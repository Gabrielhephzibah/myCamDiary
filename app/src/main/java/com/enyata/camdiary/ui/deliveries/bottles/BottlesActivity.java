package com.enyata.camdiary.ui.deliveries.bottles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityBottlesBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;

import javax.inject.Inject;

public class BottlesActivity extends BaseActivity<ActivityBottlesBinding,BottlesViewModel>implements BottlesNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private BottlesViewModel bottlesViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, BottlesActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bottles;
    }

    @Override
    public BottlesViewModel getViewModel() {
        bottlesViewModel = ViewModelProviders.of(this,factory).get(BottlesViewModel.class);
        return bottlesViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottlesViewModel.setNavigator(this);
    }

    @Override
    public void finish() {
        Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
        startActivity(intent);
    }
}
