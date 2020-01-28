package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.Product;
import com.enyata.camdiary.databinding.ActivityDetailsBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.feedback.FeedbackActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryList;

import java.util.ArrayList;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding,DetailsViewModel>implements DetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private DetailsViewModel detailsViewModel;
    ActivityDetailsBinding activityDetailsBinding;
    ArrayList<DeliveryItemList>deliveryItemLists = new ArrayList<>();
    DeliveryItemAdapter deliveryItemAdapter;
    String customerName;
    String address;
    String phoneNo;

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
        ListView listView = activityDetailsBinding.listView;



        customerName = getIntent().getStringExtra("customerName");
        address = getIntent().getStringExtra("customerAddress");
        phoneNo = getIntent().getStringExtra("contactNo");
        ArrayList<Product> list = ((ArrayList<Product>) getIntent().getSerializableExtra("list"));
        Log.i("ARRAYLIST",list.toString());
        for (int i = 0; i < list.size(); i++){
            Product obj = list.get(i);
            String productNamee = obj.getName();
            String productQuantityy = obj.getQuantity();

            deliveryItemLists.add(new DeliveryItemList(productNamee,"x "+ productQuantityy));
            deliveryItemAdapter = new DeliveryItemAdapter(DetailsActivity.this,deliveryItemLists);
            listView.setAdapter(deliveryItemAdapter);


        }
        addresss.setText(address);
        name.setText(customerName);
        phoneNoo.setText(phoneNo);

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
