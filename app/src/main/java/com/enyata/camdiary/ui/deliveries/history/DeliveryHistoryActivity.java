package com.enyata.camdiary.ui.deliveries.history;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DeliveryHistory;
import com.enyata.camdiary.data.model.api.response.DeliveryHistoryResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryHistoryResponseData;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityDeliveryHistoryBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DeliveryHistoryActivity extends BaseActivity<ActivityDeliveryHistoryBinding, DeliveryHistoryViewModel> implements DeliveryHistoryNavigator {

    @Inject
    Gson gson;

    ListView listView;
    ActivityDeliveryHistoryBinding activityDeliveryHistoryBinding;
    ArrayList<DeliveryItemInterface> deliveryHistoryLists = new ArrayList<DeliveryItemInterface>();
    ImageView image;
    CFAlertDialog alert;


    @Inject
    ViewModelProviderFactory factory;
    private DeliveryHistoryViewModel deliveryHistoryViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryHistoryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery_history;
    }

    @Override
    public DeliveryHistoryViewModel getViewModel() {
        deliveryHistoryViewModel = ViewModelProviders.of(this, factory).get(DeliveryHistoryViewModel.class);
        return deliveryHistoryViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryHistoryViewModel.setNavigator(this);
        listView = findViewById(R.id.listView);
        activityDeliveryHistoryBinding = getViewDataBinding();
        image = findViewById(R.id.dispatcherImage);
        String dispatcherImage = deliveryHistoryViewModel.getDispatcherImage();
        Picasso.get().load(dispatcherImage).into(image);
        ImageView logout = activityDeliveryHistoryBinding.logout;
         TextView dispatcherName = activityDeliveryHistoryBinding.dispatcherName;
         dispatcherName.setText("Hey" +", " +deliveryHistoryViewModel.getCurrentUser());
        if (InternetConnection.getInstance(this).isOnline()) {
            deliveryHistoryViewModel.getDeliveryHistory();
        } else {
            Alert.showFailed(getApplicationContext(), "Please check your internet setting and try again");
        }

    }

    @Override
    public void signup() {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);

    }

    @Override
    public void delivery() {
        Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
        startActivity(intent);

    }

    @Override
    public void logout() {
        CFAlertDialog.Builder alertDialog = new CFAlertDialog.Builder(this);
        LayoutInflater inflater = DeliveryHistoryActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout_notification_sheet,null);
        alertDialog .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)
                .setCancelable(false)
                .setHeaderView(dialogView);
        Button yes = dialogView.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Button no = dialogView.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        alert = alertDialog.create();
        alert.show();
    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void deliveryHistory(DeliveryHistoryResponseData response) {
        try {



        for (DeliveryHistoryResponse data : response.getData()) {
            String[] formatted = data.getDate().split(" ");
            String[] formattedDate = formatted[0].split("-");
            String date = formattedDate[2] + "/" + formattedDate[1] + "/" + formattedDate[0];
            deliveryHistoryLists.add(new DeliveryHistoryHeader(date));
            List<DeliveryHistory> deliveryHistory = data.getDeliveryHistory();
            for (int i = 0; i < deliveryHistory.size(); i++) {
                String items;
                DeliveryHistory history = deliveryHistory.get(i);
                String firstName = history.getCustomerDetails().getFirstName();
                String lastName = history.getCustomerDetails().getLastName();
                String contactNo = history.getCustomerDetails().getPhone();
                String shopifyOrderReference = history.getShopifyOrderReference();
                String productCount = history.getProductsCount();
                if (productCount.equals("1")) {
                    items = history.getProductsCount() + " item";
                } else {
                    items = history.getProductsCount() + " items";
                }

                deliveryHistoryLists.add(new DispatcherHistory(firstName + " " + lastName, items, contactNo, shopifyOrderReference, items));
                DeliveryCustomAdapter customAdapter = new DeliveryCustomAdapter(DeliveryHistoryActivity.this, deliveryHistoryLists);
                listView.setAdapter(customAdapter);
            }
        }
    }catch (NullPointerException e){
            e.printStackTrace();
            Log.i("Unknown Error", e.getMessage());
        }


    }

    @Override
    public void handleError(Throwable throwable) {

        Log.i("ERROR", throwable.getMessage());
        try {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            if (error.getErrorBody() != null) {
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            } else {
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
        deliveryHistoryViewModel.onDispose();
    }
}
