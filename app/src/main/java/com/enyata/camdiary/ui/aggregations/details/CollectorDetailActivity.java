package com.enyata.camdiary.ui.aggregations.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectorDetailBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardViewModel;
import com.enyata.camdiary.ui.aggregations.product.ProductActivity;
import com.enyata.camdiary.ui.base.BaseActivity;

import javax.inject.Inject;

public class CollectorDetailActivity extends BaseActivity<ActivityCollectorDetailBinding,CollectorDetailViewModel> implements CollectorDetailNavigator{

    String firstName;
    String lastName;
    String verificationNumber;
    String phoneNumber;
    String email;
    String coperateName;
    String id;

    @Inject
    ViewModelProviderFactory factory;
    private CollectorDetailViewModel collectorDetailViewModel;
    ActivityCollectorDetailBinding activityCollectorDetailBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collector_detail;
    }

    @Override
    public CollectorDetailViewModel getViewModel() {
        collectorDetailViewModel = ViewModelProviders.of(this,factory).get(CollectorDetailViewModel.class);
        return collectorDetailViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectorDetailViewModel.setNavigator(this);
        activityCollectorDetailBinding = getViewDataBinding();


        firstName = getIntent().getStringExtra("first_name");
        lastName = getIntent().getStringExtra("last_name");
        verificationNumber = getIntent().getStringExtra("verification_id");
        phoneNumber = getIntent().getStringExtra("phone_number");
        email = getIntent().getStringExtra("email");
        id = getIntent().getStringExtra("id");



        TextView name = activityCollectorDetailBinding.name;
        TextView Email = activityCollectorDetailBinding.email;
        TextView verification_number = activityCollectorDetailBinding.verificationNumber;
        TextView number = activityCollectorDetailBinding.number;


        name.setText(firstName+ " " + lastName);
        Email.setText(email);
        verification_number.setText(verificationNumber);
        number.setText(phoneNumber);


    }


    @Override
    public void proceed() {
        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
        intent .putExtra("id",id);

        startActivity(intent);
    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);
    }
}
