package com.enyata.camdiary.ui.collections.dashboard;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.FarmerId;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.databinding.ActivityCollectionDashboardBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdViewModel;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;

import java.util.ArrayList;
import javax.inject.Inject;

public class DashboardActivity extends BaseActivity<ActivityCollectionDashboardBinding, DashboardViewModel> implements DashboardNavigator {

    String firstName;
    String lastName;
    String coperateName;
    String verification_number;
    String fullName;

    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    FarmerIdViewModel farmerIdViewModel;
    private DashboardViewModel dashboardViewModel;
    private ActivityCollectionDashboardBinding activityCollectionDashboardBinding;
    private ListView listView;
    private ArrayList<DashboardCollectorList> dashboardCollectorLists = new ArrayList<>();
    private LinearLayout slideLayout;

    int[] layouts = {R.layout.collection_first_slide, R.layout.collection_second_slide, R.layout.collection_third_slide};

    public static Intent newIntent(Context context) {
        return new Intent(context, FarmerDetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection_dashboard;
    }

    @Override
    public DashboardViewModel getViewModel() {
        dashboardViewModel = ViewModelProviders.of(this, factory).get(DashboardViewModel.class);
        return dashboardViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            VolumeResponse response = gson.fromJson(error.getErrorBody(), VolumeResponse.class);
            Alert.showFailed(getApplicationContext(), response.getResponseMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardViewModel.setNavigator(this);
        activityCollectionDashboardBinding = getViewDataBinding();
        ViewPager pager = activityCollectionDashboardBinding.pager;
        slideLayout = activityCollectionDashboardBinding.slideLayout;
        listView = activityCollectionDashboardBinding.listView;
        TextView username = activityCollectionDashboardBinding.username;
        TextView today = activityCollectionDashboardBinding.today;
        today.setText(dashboardViewModel.getCurrentDate());
        firstName = getIntent().getStringExtra("first_name");
        lastName = getIntent().getStringExtra("last_name");
        coperateName = getIntent().getStringExtra("coperate_name");
        verification_number = getIntent().getStringExtra("farmer_id");

        fullName = firstName + " "+ lastName;


//        farmerIdViewModel.getFarmerDetails();



        username.setText(String.format("Hey,%s", dashboardViewModel.getFirstName()));

        DashboardAdapter viewPagerAdapter = new DashboardAdapter(this, getSupportFragmentManager());
        pager.setAdapter(viewPagerAdapter);
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

        if (!isNetworkConnected()) {
            Alert.showInfo(getApplicationContext(), "No internet connection, please check internet settings and try again");
            return;
        }

        dashboardViewModel.getVolumeOfAcceptedCollection();
        dashboardViewModel.getVolumeOfRejectedCollection();
        dashboardViewModel.getTodaysCollection();
        dashboardViewModel.getAllEntries();


    }


    @Override
    public void createSliderDash(int current_position) {
        if (slideLayout != null)
            slideLayout.removeAllViews();

        ImageView[] slider_dash = new ImageView[layouts.length];
        for (int i = 0; i < layouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            slideLayout.setLayoutParams(params);


            slideLayout.addView(slider_dash[i], params);
        }

    }

    @Override
    public void scancode() {
        Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
        startActivity(intent);
    }

    @Override
    public void history() {
        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void dataCollection() {
        Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
        startActivity(intent);
    }

    @Override
    public void logout() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayAcceptedVolume(VolumeResponse volume) {
        dashboardViewModel.setAcceptedVolume(volume.getData());
    }

    @Override
    public void displayRejectedVolume(VolumeResponse volume) {
        dashboardViewModel.setRejectedVolume(volume.getData());
    }

    @Override
    public void getAllEntries(AllEntries entries) {
        dashboardViewModel.setEntries(entries.getData());
    }

    @Override
    public void getTodayCollection(CollectionResponse todayCollectionResponse) {
        for (Collection response : todayCollectionResponse.getData())  {
            dashboardCollectorLists.add(new DashboardCollectorList(response.getFarmer().getFirstName()+ "  " + response.getFarmer().getLastName(),response.getFarmer().getCooperativeName(), response.getFarmer().getVerificationId(),response.getStatusOfCollection(), String.valueOf(response.getVolume()+ " litres")));
            DashboardCollectorAdapter dashboardCollectorAdapter = new DashboardCollectorAdapter(DashboardActivity.this, dashboardCollectorLists);
            listView.setAdapter(dashboardCollectorAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dashboardViewModel.dispose();
    }
}
