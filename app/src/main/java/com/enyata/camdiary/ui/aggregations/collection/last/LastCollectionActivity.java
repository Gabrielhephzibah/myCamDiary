package com.enyata.camdiary.ui.aggregations.collection.last;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityLastCollectionBinding;
import com.enyata.camdiary.ui.aggregations.collection.success.CollectionSuccessViewModel;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeViewModel;
import com.enyata.camdiary.ui.base.BaseActivity;

import javax.inject.Inject;

public class LastCollectionActivity extends BaseActivity<ActivityLastCollectionBinding,LastCollectionViewModel>implements LastCollectionNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private LastCollectionViewModel lastCollectionViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, LastCollectionActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_last_collection;
    }

    @Override
    public LastCollectionViewModel getViewModel() {
        lastCollectionViewModel = ViewModelProviders.of(this,factory).get(LastCollectionViewModel.class);
        return lastCollectionViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lastCollectionViewModel.setNavigator(this);

    }

    @Override
    public void finish() {
        Intent intent = new Intent(getApplicationContext(),AggregatorDashboardActivity.class);
        startActivity(intent);
    }
}
