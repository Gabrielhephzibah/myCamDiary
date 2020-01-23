package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDetailsBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.feedback.FeedbackActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding,DetailsViewModel>implements DetailsNavigator {
    String address;
    String firstName;
    String lastName;
    String phoneNo;
    String productName;
    String  quantity;
    @Inject
    ViewModelProviderFactory factory;
    private DetailsViewModel detailsViewModel;
    ActivityDetailsBinding activityDetailsBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public DetailsViewModel getViewModel() {
        detailsViewModel = ViewModelProviders.of(this,factory).get(DetailsViewModel.class);
        return detailsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsViewModel.setNavigator(this);
        activityDetailsBinding = getViewDataBinding();
        TextView addresss = activityDetailsBinding.address;
        TextView name = activityDetailsBinding.userName;
        TextView phoneNoo = activityDetailsBinding.phoneNo;
        TextView item1 = activityDetailsBinding.items1;
        TextView quantity1 = activityDetailsBinding.quantity1;

        address = getIntent().getStringExtra("address");
        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        phoneNo = getIntent().getStringExtra("phoneNo");
        productName = getIntent().getStringExtra("name");
        quantity = getIntent().getStringExtra("quantity");


        addresss.setText(address);
        name.setText(firstName + " "+ lastName);
        phoneNoo.setText(phoneNo);
        item1.setText(productName + ".....................");
        quantity1.setText(quantity);

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void deliver() {
        Intent intent = new Intent(getApplicationContext(), BottlesActivity.class);
        startActivity(intent);

    }
}
