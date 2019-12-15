package com.enyata.camdiary.ui.deliveries.deliveries_delivery.feedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityFeedbackBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsViewModel;

import javax.inject.Inject;

public class FeedbackActivity extends BaseActivity<ActivityFeedbackBinding,FeedbackViewModel>implements FeedbackNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private FeedbackViewModel feedbackViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public FeedbackViewModel getViewModel() {
        feedbackViewModel = ViewModelProviders.of(this,factory).get(FeedbackViewModel.class);
        return feedbackViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedbackViewModel.setNavigator(this);
    }

    @Override
    public void bottles() {
        Intent intent = new Intent(getApplicationContext(), BottlesActivity.class);
        startActivity(intent);

    }

    @Override
    public void finish() {
        Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
        startActivity(intent);

    }
}
