package com.enyata.camdiary.ui.deliveries.signcustomer.confirmation;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityConfirmationBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmsuccess.SignsuccessActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;

import javax.inject.Inject;

public class ConfirmationActivity extends BaseActivity<ActivityConfirmationBinding, ConfirmationViewModel>implements ConfirmationNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private ConfirmationViewModel confirmationViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, BottlesActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirmation;
    }

    @Override
    public ConfirmationViewModel getViewModel() {
        confirmationViewModel = ViewModelProviders.of(this,factory).get(ConfirmationViewModel.class);
        return confirmationViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       confirmationViewModel.setNavigator(this);
    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);

    }

    @Override
    public void confirm() {
        Intent intent = new Intent(getApplicationContext(), SignsuccessActivity.class);
        startActivity(intent);

    }
}
