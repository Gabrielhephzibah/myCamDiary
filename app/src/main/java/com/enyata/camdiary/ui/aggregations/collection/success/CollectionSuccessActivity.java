package com.enyata.camdiary.ui.aggregations.collection.success;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectionSuccessBinding;
import com.enyata.camdiary.ui.aggregations.collection.last.LastCollectionActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeViewModel;
import com.enyata.camdiary.ui.aggregations.product.ProductActivity;
import com.enyata.camdiary.ui.base.BaseActivity;

import javax.inject.Inject;

public class CollectionSuccessActivity extends BaseActivity<ActivityCollectionSuccessBinding,CollectionSuccessViewModel>implements CollectionSuccessNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private CollectionSuccessViewModel collectionSuccessViewModel;
    String collectorName;

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
        collectorName = collectionSuccessViewModel.getCollectorName();


        String volume = getIntent().getStringExtra("aggregationVolume");

        TextView volumeTextView = findViewById(R.id.aggregationVolume);
        volumeTextView.setText(String.format("%s Litres ", volume));
        TextView collectorTextView = findViewById(R.id.collectorName);
        collectorTextView.setText(collectorName);
    }


    @Override
    public void onHome() {
        Intent intent = new Intent( getApplicationContext(), AggregatorDashboardActivity.class);
        startActivity(intent);
    }
}
