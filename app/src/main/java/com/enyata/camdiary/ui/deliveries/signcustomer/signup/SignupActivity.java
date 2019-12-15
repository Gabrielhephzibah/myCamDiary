package com.enyata.camdiary.ui.deliveries.signcustomer.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivitySignupBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanViewModel;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmation.ConfirmationActivity;

import javax.inject.Inject;

public class SignupActivity extends BaseActivity<ActivitySignupBinding,SignupViewModel>implements SignupNavigator {

    ImageView delivery;
    ImageView history;

    @Inject
    ViewModelProviderFactory factory;
    private SignupViewModel signupViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, BottlesActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
    }

    @Override
    public SignupViewModel getViewModel() {
        signupViewModel = ViewModelProviders.of(this,factory).get(SignupViewModel.class);
        return signupViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupViewModel.setNavigator(this);

        delivery = findViewById(R.id.delivery);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
                startActivity(intent);
            }
        });

        history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeliveryHistoryActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void submit() {
        Intent intent = new Intent(getApplicationContext(), ConfirmationActivity.class);
        startActivity(intent);

    }
}
