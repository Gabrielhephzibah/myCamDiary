package com.enyata.camdiary.ui.collections.rejection.rejectsuccess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityRejectsuccessBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionViewModel;

import javax.inject.Inject;

public class RejectsuccessActivity extends BaseActivity<ActivityRejectsuccessBinding,RejectsuccessViewModel>implements RejectsuccessNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private RejectsuccessViewModel rejectsuccessViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, RejectsuccessActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rejectsuccess;
    }

    @Override
    public RejectsuccessViewModel getViewModel() {
        rejectsuccessViewModel = ViewModelProviders.of(this,factory).get(RejectsuccessViewModel.class);
        return rejectsuccessViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rejectsuccessViewModel.setNavigator(this);
    }

    @Override
    public void home() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }
}