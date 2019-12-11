package com.enyata.camdiary.ui.aggregations.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectorDetailBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardViewModel;
import com.enyata.camdiary.ui.aggregations.product.ProductActivity;
import com.enyata.camdiary.ui.base.BaseActivity;

import javax.inject.Inject;

public class CollectorDetailActivity extends BaseActivity<ActivityCollectorDetailBinding,CollectorDetailViewModel> implements CollectorDetailNavigator{

    @Inject
    ViewModelProviderFactory factory;
    private CollectorDetailViewModel collectorDetailViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collector_detail;
    }

    @Override
    public CollectorDetailViewModel getViewModel() {
        collectorDetailViewModel = ViewModelProviders.of(this,factory).get(CollectorDetailViewModel.class);
        return collectorDetailViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectorDetailViewModel.setNavigator(this);
    }


    @Override
    public void proceed() {
        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
        startActivity(intent);
    }
}
