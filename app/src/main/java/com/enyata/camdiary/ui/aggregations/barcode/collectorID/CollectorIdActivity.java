package com.enyata.camdiary.ui.aggregations.barcode.collectorID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectorIdBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardViewModel;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.base.BaseActivity;

import javax.inject.Inject;

public class CollectorIdActivity extends BaseActivity<ActivityCollectorIdBinding,ColectorIdViewModel>implements CollectorIdNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private ColectorIdViewModel colectorIdViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collector_id;
    }

    @Override
    public ColectorIdViewModel getViewModel() {
        colectorIdViewModel = ViewModelProviders.of(this,factory).get(ColectorIdViewModel.class);
        return colectorIdViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colectorIdViewModel.setNavigator(this);

    }

    @Override
    public void accept() {
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
        startActivity(intent);

    }
}
