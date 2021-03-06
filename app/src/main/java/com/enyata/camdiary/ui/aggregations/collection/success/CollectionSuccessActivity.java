package com.enyata.camdiary.ui.aggregations.collection.success;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectionSuccessBinding;
import com.enyata.camdiary.ui.aggregations.collection.last.LastCollectionActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeViewModel;
import com.enyata.camdiary.ui.base.BaseActivity;

import javax.inject.Inject;

public class CollectionSuccessActivity extends BaseActivity<ActivityCollectionSuccessBinding,CollectionSuccessViewModel>implements CollectionSuccessNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private CollectionSuccessViewModel collectionSuccessViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection_success;
    }

    @Override
    public CollectionSuccessViewModel getViewModel() {
        collectionSuccessViewModel = ViewModelProviders.of(this,factory).get(CollectionSuccessViewModel.class);
        return collectionSuccessViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectionSuccessViewModel.setNavigator(this);
    }

    @Override
    public void next() {
        Intent intent = new Intent(getApplicationContext(), LastCollectionActivity.class);
        startActivity(intent);
    }
}
