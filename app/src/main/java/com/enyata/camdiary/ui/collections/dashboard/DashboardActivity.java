package com.enyata.camdiary.ui.collections.dashboard;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amitshekhar.utils.Constants;
import com.androidnetworking.error.ANError;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.FarmerIdResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.databinding.ActivityCollectionDashboardBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdViewModel;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;
import com.enyata.camdiary.ui.editProfile.EditProfileActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;


public class DashboardActivity extends BaseActivity<ActivityCollectionDashboardBinding, DashboardViewModel> implements DashboardNavigator {

    String firstName;
    String lastName;
    String coperateName;
    String verification_number;
    String fullName;
    ImageView collectorImage;
    RelativeLayout history,scanBarcode;
    CFAlertDialog alert;


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
   private RelativeLayout data;
   int backButtonPressed = 0;

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

        if (throwable != null ) {
            try {
                ANError error = (ANError) throwable;
                FarmerIdResponse response = gson.fromJson(error.getErrorBody(),FarmerIdResponse.class);
                if (error.getErrorBody()!= null){
                    Alert.showFailed(getApplicationContext(), response.getResponseMessage());
                }else {
                    Alert.showFailed(getApplicationContext(),"Unable to connect to the internet");
                }
            }catch (IllegalStateException | JsonSyntaxException exception){
                exception.printStackTrace();
                Log.i("ERROR", exception.getMessage());
                Alert.showFailed(getApplicationContext(),"An unknown error has occurred");
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardViewModel.setNavigator(this);
        activityCollectionDashboardBinding = getViewDataBinding();
        data = activityCollectionDashboardBinding.data;
        history = activityCollectionDashboardBinding.history;
        scanBarcode = activityCollectionDashboardBinding.scanbarcode;
        Log.i("MYYYYYYYYYYYYYY", dashboardViewModel.getUserType());
        collectorImage = findViewById(R.id.collectorImage);
        String imageUrl = dashboardViewModel.getCollectorImage();
        Picasso.get().load(imageUrl).into(collectorImage);
        Log.i("USERTYPE", dashboardViewModel.getUserType());

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

            fullName = firstName + " " + lastName;

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


            if (InternetConnection.getInstance(this).isOnline()) {
                dashboardViewModel.getVolumeOfAcceptedCollection();
                dashboardViewModel.getVolumeOfRejectedCollection();
                dashboardViewModel.getTodaysCollection();
                dashboardViewModel.getAllEntries();
                return;
            } else {
                Alert.showFailed(getApplicationContext(), "Please check Internet Connection and try again");
            }


        }


        @Override
        public void createSliderDash ( int current_position){
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
        public void scancode () {
            Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
            startActivity(intent);
        }

        @Override
        public void history () {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
        }

        @Override
        public void dataCollection () {
            Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
            startActivity(intent);
        }

        @Override
        public void logout () {
            CFAlertDialog.Builder alertDialog = new CFAlertDialog.Builder(this);
            LayoutInflater inflater = DashboardActivity.this.getLayoutInflater();
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
        public void displayAcceptedVolume (VolumeResponse volume){
            dashboardViewModel.setAcceptedVolume(volume.getData());
        }

        @Override
        public void displayRejectedVolume (VolumeResponse volume){
            dashboardViewModel.setRejectedVolume(volume.getData());
        }

        @Override
        public void getAllEntries (AllEntries entries){
            dashboardViewModel.setEntries(entries.getData());
        }

        @Override
        public void getTodayCollection (CollectionResponse todayCollectionResponse){
//        Log.i("RESPONSE", todayCollectionResponse.getData().toString());
        try {
            for (Collection response : todayCollectionResponse.getData()) {
                dashboardCollectorLists.add(new DashboardCollectorList(response.getFarmer().getFirstName() + "  " + response.getFarmer().getLastName(), response.getFarmer().getCooperativeName(), response.getFarmer().getVerificationId(), response.getStatusOfCollection(), response.getVolume() + " litres"));
                DashboardCollectorAdapter dashboardCollectorAdapter = new DashboardCollectorAdapter(DashboardActivity.this, dashboardCollectorLists);
                listView.setAdapter(dashboardCollectorAdapter);
            }
        }catch (NullPointerException e){
            e.printStackTrace();

            Log.i("An Unknown error ", e.getMessage());
        }

        }

    @Override
    public void onProfilePicture() {
        Intent intent = new Intent(getApplicationContext(), CollectorEditProfileActivity.class);
        startActivity(intent);
    }

    @Override
        protected void onDestroy () {
            super.onDestroy();
            dashboardViewModel.dispose();
        }

    @Override
    public void onBackPressed() {
         if (backButtonPressed >= 2){
             Intent intent = new Intent(Intent.ACTION_MAIN);
             intent.addCategory(Intent.CATEGORY_HOME);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);
         }else {
             Toast.makeText(this, "Press the back button twice to close the application.", Toast.LENGTH_SHORT).show();
             backButtonPressed++;
         }


    }


}
