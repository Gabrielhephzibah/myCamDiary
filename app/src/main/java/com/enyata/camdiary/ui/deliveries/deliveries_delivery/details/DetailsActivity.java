package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DeliveryDetailResponse;
import com.enyata.camdiary.data.model.api.response.Product;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.databinding.ActivityDetailsBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.feedback.FeedbackActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryList;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding,DetailsViewModel>implements DetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    Gson gson;
    private DetailsViewModel detailsViewModel;
    ActivityDetailsBinding activityDetailsBinding;
    ArrayList<DeliveryItemList>deliveryItemLists = new ArrayList<>();
    DeliveryItemAdapter deliveryItemAdapter;
    TextView name;
    String customerName;
    TextView addresss;
    TextView phoneNoo;
    ListView listView;
    String shopifyId;
    List<Product> list;
    Button deliverButton;

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
         addresss = activityDetailsBinding.address;
        name = activityDetailsBinding.userName;
         phoneNoo = activityDetailsBinding.phoneNo;
         listView = activityDetailsBinding.listView;
       shopifyId =  detailsViewModel.getShopifyId();
        Log.i("SHOPIIIIFYYYY", detailsViewModel.getShopifyId());
        detailsViewModel.getOrderDetails(shopifyId);
        deliverButton = activityDetailsBinding.deliverButton;


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

    @Override
    public void onResponse(DeliveryDetailResponse response) {
        deliverButton.setVisibility(View.VISIBLE);
        detailsViewModel.setOrderId(String.valueOf(response.getData().getId()));
        customerName = response.getData().getDeliveryDetails().getFirstName()+" "+ response.getData().getDeliveryDetails().getLastName();
        detailsViewModel.setCustomerName(customerName);
        name.setText(String.format("%s %s", response.getData().getDeliveryDetails().getFirstName(), response.getData().getDeliveryDetails().getLastName()));
        addresss.setText(String.format("%s %s %s", response.getData().getDeliveryDetails().getAddress(), response.getData().getDeliveryDetails().getCity(), response.getData().getDeliveryDetails().getCountry()));
        phoneNoo.setText(response.getData().getDeliveryDetails().getPhone());
        list = response.getData().getOrdered_products();
        try {
            for (int i = 0; i < list.size(); i++){
            Product obj = list.get(i);
            String productNamee = obj.getTitle();
            String productQuantityy = obj.getQuantity();
            deliveryItemLists.add(new DeliveryItemList(productNamee,"x "+ productQuantityy));
            deliveryItemAdapter = new DeliveryItemAdapter(DetailsActivity.this,deliveryItemLists);
            listView.setAdapter(deliveryItemAdapter);

        }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @Override
    public void handleError(Throwable throwable) {
        try {
            if (throwable != null) {
                ANError error = (ANError) throwable;
                ResetPasswordResponse response = gson.fromJson(error.getErrorBody(), ResetPasswordResponse.class);
                if (error.getErrorBody()!= null){
                    Alert.showFailed(getApplicationContext(),response.getMessage());
                }else {

                    Alert.showFailed(getApplicationContext(), "Unable to connect to the Internet");
                }

            }
        }catch (IllegalStateException | JsonSyntaxException exception){
            Alert.showFailed(getApplicationContext(), "An unknown error occurred");
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailsViewModel.onDispose();
    }
}
