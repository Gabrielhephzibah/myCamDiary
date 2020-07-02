package com.enyata.camdiary.ui.aggregations.collectorCollection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectorColletionBinding;
import com.enyata.camdiary.ui.base.BaseActivity;

import java.util.zip.Inflater;

import javax.inject.Inject;

public class CollectorCollectionActivity extends BaseActivity<ActivityCollectorColletionBinding, CollectorCollectionViewModel>implements CollectorCollectionNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityCollectorColletionBinding activityCollectorColletionBinding;
    CollectorCollectionViewModel collectorCollectionViewModel;



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collector_colletion;
    }

    @Override
    public CollectorCollectionViewModel getViewModel() {
        collectorCollectionViewModel = ViewModelProviders.of(this,factory).get(CollectorCollectionViewModel.class);
        return collectorCollectionViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectorCollectionViewModel.setNavigator(this);

    }

    @Override
    public void onViewMore() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this,R.style.DialogStyle);
       LayoutInflater inflater = CollectorCollectionActivity.this.getLayoutInflater();
        View view =  inflater.inflate(R.layout.view_more_layout,null);
        alertBuilder.setView(view);
        alertBuilder.setCancelable(false);
        Button button = view.findViewById(R.id.close);
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });





    }

    @Override
    public void onCollection() {
        AlertDialog.Builder alertBuilderVolume = new AlertDialog.Builder(this);
        LayoutInflater inflater = CollectorCollectionActivity.this.getLayoutInflater();
        View mview =  inflater.inflate(R.layout.aggregator_volume_layout,null);
        alertBuilderVolume.setView(mview);
        alertBuilderVolume.setCancelable(false);
        TextView back = mview.findViewById(R.id.back);
        AlertDialog dialog = alertBuilderVolume.create();
        dialog.show();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });

    }
}
