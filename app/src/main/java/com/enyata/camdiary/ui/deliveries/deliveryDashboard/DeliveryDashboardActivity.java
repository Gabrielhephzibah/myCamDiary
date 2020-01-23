package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DeliveryCompletedResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.PendingData;
import com.enyata.camdiary.data.model.api.response.PendingDeliveryResponse;
import com.enyata.camdiary.data.model.api.response.Product;
import com.enyata.camdiary.databinding.ActivityDeliveryDashboardBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

public class DeliveryDashboardActivity extends BaseActivity<ActivityDeliveryDashboardBinding,DeliveryDashboardViewModel>implements DeliveryDashboardNavigator {

    @Inject
    Gson gson;

    DeliveryListAdapter deliveryListAdapter;
    ListView listView;
    ArrayList<DeliveryList> deliveryLists = new ArrayList<>();
    String address;
    String phoneNo;
    String firstName;
    String lastName;
    String name;
    String quantity;


    @Inject
    ViewModelProviderFactory factory;
    private DeliveryDashboardViewModel deliveryDashboardViewModel;
    int[]layouts = {R.layout.delivery_first_slide, R.layout.delivery_second_slide};
    private DeliveryDashboardAdapter deliveryDashboardAdapterr;
    ImageView[] slider_dash;
    LinearLayout slideLayout;
    ViewPager pager;
    ActivityDeliveryDashboardBinding activityDeliveryDashboardBinding;



    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryDashboardActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery_dashboard;
    }

    @Override
    public DeliveryDashboardViewModel getViewModel() {
        deliveryDashboardViewModel = ViewModelProviders.of(this,factory).get(DeliveryDashboardViewModel.class);
        return deliveryDashboardViewModel;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryDashboardViewModel.setNavigator(this);
        pager = findViewById(R.id.pager);
        slideLayout = findViewById(R.id.slideLayout);
        listView = findViewById(R.id.listView);
        activityDeliveryDashboardBinding = getViewDataBinding();
        ImageView logout = activityDeliveryDashboardBinding.logout;

        TextView dispatcherName = activityDeliveryDashboardBinding.dispatcherName;
        dispatcherName.setText( "Hey," + " " + deliveryDashboardViewModel.getCurrentFirstName());

        TextView date = activityDeliveryDashboardBinding.date;
        date.setText(deliveryDashboardViewModel.getCurrentDate());


        if (!isNetworkConnected()) {
            Alert.showInfo(getApplicationContext(),"No internet connection, please check internet settings and try again");
            return;
        }else{
            deliveryDashboardViewModel.getDeliveriesCompleted();
            deliveryDashboardViewModel.getPendingDelivery();
        }




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                Log.i("MESSAGE", address);
                Log.i("MESSAGE", phoneNo);
                intent.putExtra("address", address);
                intent.putExtra("firstName",firstName);
                intent.putExtra("lastName",lastName);
                intent.putExtra("phoneNo",phoneNo);
                intent.putExtra("name", name);
                intent.putExtra("quantity", quantity);
                startActivity(intent);
            }
        });


        deliveryDashboardAdapterr = new DeliveryDashboardAdapter(this, getSupportFragmentManager());
        pager.setAdapter(deliveryDashboardAdapterr);
        createSliderDash(0);


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            Alert.showFailed(getApplicationContext(), "Please check your internet connection");
        }
    }

    @Override
    public void createSliderDash(int current_position) {
        if (slideLayout != null)
            slideLayout.removeAllViews();

        slider_dash = new ImageView[layouts.length];
        for (int i = 0; i < layouts.length; i++){
            slider_dash[i] = new ImageView(this);
            if (i == current_position){
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_slider_dash));
            }else{
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            slideLayout.setLayoutParams(params);


            slideLayout.addView(slider_dash[i], params);
        }
    }

    @Override
    public void signup() {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);


    }

    @Override
    public void history() {
        Intent intent = new Intent(getApplicationContext(), DeliveryHistoryActivity.class);
        startActivity(intent);

    }

    @Override
    public void delivery() {
        Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
        startActivity(intent);

    }

    @Override
    public void logout() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void getDeliveryCompleted(DeliveryCompletedResponse response) {
        deliveryDashboardViewModel.setdeliveryCompleted(response.getData());
    }

    @Override
    public void getPendingDelivery(PendingDeliveryResponse response) {

        for (PendingData pendingData : response.getData()){
            String items;

            for (int i = 0; i <pendingData.getOrder().getProducts().size(); i++){
                Product obj = pendingData.getOrder().getProducts().get(i);
                name = obj.getName();
                quantity = obj.getQuantity();

            }
            address = pendingData.getOrder().getAddress();
            firstName = pendingData.getOrder().getUsers().getFirstName();
            lastName = pendingData.getOrder().getUsers().getLastName();
            phoneNo = pendingData.getOrder().getUsers().getContactNo();


            if (pendingData.getOrder().getProductCount().equals("1")){
                items= pendingData.getOrder().getProductCount() + " item";
            }else {
                 items = pendingData.getOrder().getProductCount()+ " items";
            }

            deliveryLists.add(new DeliveryList(pendingData.getOrder().getUsers().getFirstName()+ ","+" "+pendingData.getOrder().getUsers().getLastName(),items,pendingData.getOrder().getUsers().getContactNo(),pendingData.getOrder().getUsers().getVerificationId()));
          deliveryListAdapter = new DeliveryListAdapter(DeliveryDashboardActivity.this,deliveryLists);
          listView.setAdapter(deliveryListAdapter);
        }


    }



}
