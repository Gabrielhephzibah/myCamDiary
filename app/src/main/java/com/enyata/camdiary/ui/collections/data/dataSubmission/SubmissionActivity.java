package com.enyata.camdiary.ui.collections.data.dataSubmission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityLoginBinding;
import com.enyata.camdiary.databinding.ActivitySubmissionBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;
import com.enyata.camdiary.ui.collections.successfulcollection.SuccessfulViewModel;
import com.enyata.camdiary.ui.login.LoginViewModel;

import javax.inject.Inject;

public class SubmissionActivity extends BaseActivity<ActivitySubmissionBinding, SubmissionViewModel>implements SubmissionNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private SubmissionViewModel submissionViewModel;




    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_submission;
    }

    @Override
    public SubmissionViewModel getViewModel() {
        submissionViewModel = ViewModelProviders.of(this,factory).get(SubmissionViewModel.class);
        return submissionViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       submissionViewModel.setNavigator(this);
    }

    @Override
    public void home() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void history() {
        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(intent);

    }

    @Override
    public void scan() {
        Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
        startActivity(intent);

    }

    @Override
    public void dataCollection() {
        Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
        startActivity(intent);

    }
}
