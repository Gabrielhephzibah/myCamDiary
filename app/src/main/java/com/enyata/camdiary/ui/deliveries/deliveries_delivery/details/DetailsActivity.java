package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDetailsBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.feedback.FeedbackActivity;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding,DetailsViewModel>implements DetailsNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private DetailsViewModel detailsViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public DetailsViewModel getViewModel() {
        detailsViewModel = ViewModelProviders.of(this,factory).get(DetailsViewModel.class);
        return detailsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsViewModel.setNavigator(this);

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(),DeliveryActivity.class);
        startActivity(intent);

    }

    @Override
    public void deliver() {
        Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
        startActivity(intent);

    }
}
