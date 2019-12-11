package com.enyata.camdiary.ui.collections.constant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityConstantLayoutBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeViewModel;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;

import javax.inject.Inject;

public class ConstantLayoutActivity extends BaseActivity<ActivityConstantLayoutBinding,ConstantLayoutViewModel>implements ConstantLayoutNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private ConstantLayoutViewModel constantLayoutViewModel;



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_constant_layout;
    }

    @Override
    public ConstantLayoutViewModel getViewModel() {
        constantLayoutViewModel = ViewModelProviders.of(this,factory).get(ConstantLayoutViewModel.class);
        return constantLayoutViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constantLayoutViewModel.setNavigator(this);
    }

    @Override
    public void history() {
        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void dataCollection() {
        Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
        startActivity(intent);


    }

    @Override
    public void scan() {
        Intent intent = new Intent(getApplicationContext(),BarcodeActivity.class);
        startActivity(intent);

    }
}
