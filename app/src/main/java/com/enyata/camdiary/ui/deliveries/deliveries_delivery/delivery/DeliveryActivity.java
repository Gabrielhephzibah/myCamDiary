package com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDeliveryBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardNavigator;

import javax.inject.Inject;

public class DeliveryActivity extends BaseActivity<ActivityDeliveryBinding,DeliveryViewModel>implements DeliveryNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private DeliveryViewModel deliveryViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery;
    }

    @Override
    public DeliveryViewModel getViewModel() {
        deliveryViewModel = ViewModelProviders.of(this,factory).get(DeliveryViewModel.class);
        return deliveryViewModel;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryViewModel.setNavigator(this);
    }


    @Override
    public void click() {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        startActivity(intent);

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        startActivity(intent);

    }
}
