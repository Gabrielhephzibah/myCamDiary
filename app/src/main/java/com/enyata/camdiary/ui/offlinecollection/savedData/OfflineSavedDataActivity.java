package com.enyata.camdiary.ui.offlinecollection.savedData;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;
import com.enyata.camdiary.databinding.ActivityOfflineSavedDataBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveySavedData.DataSurveySavedDataFragement;
import com.enyata.camdiary.ui.offlinecollection.savedData.milkCollectionSavedData.MilkCollectionSavedDataFragment;
import com.enyata.camdiary.utils.Alert;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

import javax.inject.Inject;

public class OfflineSavedDataActivity extends BaseActivity<ActivityOfflineSavedDataBinding, OfflineSavedDataViewModel> implements OfflineSavedDataNavigator {
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    Gson gson;
    OfflineSavedDataViewModel offlineSavedDataViewModel;
    ActivityOfflineSavedDataBinding activityOfflineSavedDataBinding;
    TabLayout tabLayout;
    LinearLayout toggleLayout;
    Fragment milkCollection ;
    Fragment dataCollection;
    ImageView home,savedData,logout,backToDashboard;
    MilkCollectionSavedDataFragment milkCollectionSavedDataFragment;


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_offline_saved_data;
    }

    @Override
    public OfflineSavedDataViewModel getViewModel() {
        offlineSavedDataViewModel = ViewModelProviders.of(this,factory).get(OfflineSavedDataViewModel.class);
        return offlineSavedDataViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offlineSavedDataViewModel.setNavigator(this);
        activityOfflineSavedDataBinding = getViewDataBinding();
        tabLayout = activityOfflineSavedDataBinding.offlineSavedDataTabLayout;
        toggleLayout = activityOfflineSavedDataBinding.savedDataToggleLayout;
        home = activityOfflineSavedDataBinding.included.offlineHome;
        savedData = activityOfflineSavedDataBinding.included.offlineSavedData;
        logout = activityOfflineSavedDataBinding.included.offlineLogout;
        backToDashboard = activityOfflineSavedDataBinding.backToDashboard;
        milkCollection = new MilkCollectionSavedDataFragment();
        dataCollection = new DataSurveySavedDataFragement();

        tabLayout.addTab(tabLayout.newTab().setText("Data Collection"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Milk Collection"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#EBEBEB"));


        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmenttrans = fragment.beginTransaction();
        fragmenttrans.replace(R.id.savedDataToggleLayout, dataCollection);
        fragmenttrans.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 1: {
                        FragmentManager fragmentMa = getSupportFragmentManager();
                        FragmentTransaction fragment = fragmentMa.beginTransaction();
                        fragment.replace(R.id.savedDataToggleLayout, milkCollection);
                        fragment.commit();
                        break;
                    }
                    default: {
                        FragmentManager fragment = getSupportFragmentManager();
                        FragmentTransaction fragmenttrans = fragment.beginTransaction();
                        fragmenttrans.replace(R.id.savedDataToggleLayout, dataCollection);
                        fragmenttrans.commit();
                        break;

                    }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


//        MilkCollectionSavedDataFragment fragmente =  (MilkCollectionSavedDataFragment) getSupportFragmentManager().findFragmentById(R.id.bdsRecyclerView);
    }


    @Override
    public void onBackToDashboard() {
        Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);

        Log.i("CLICKED","CLICKED");

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onGetResponse(List<CdsDataCollection> cdsDataCollection) {


    }

    @Override
    public void onDeleteResponse() {

    }

    @Override
    public void onGetPdsResponse(List<PdsDataCollection> pdsDataCollections) {

    }

    @Override
    public void onGetBdsResponse(List<BdsDataCollections> bdsDataCollections) {

    }

    @Override
    public void onLoginResponse(CamLoginResponse response) {

    }

    @Override
    public void onLoginError(Throwable throwable) {
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
        }
        catch (IllegalStateException | JsonSyntaxException  exception){
            Alert.showFailed(getApplicationContext(), "An unknown error occurred");
        }

    }

    @Override
    public void onLoginCollectionError(Throwable throwable) {

    }

    @Override
    public void onGetCdsUploadResponse(List<CdsDataCollection> cdsDataCollections) {

    }

    @Override
    public void onGetPdsUploadResponse(List<PdsDataCollection> cdsDataCollections) {

    }

    @Override
    public void onGetBdsUploadResponse(List<BdsDataCollections> bdsDataCollections) {

    }

    @Override
    public void onGetCollectionUploadResponse(List<MilkCollection> milkCollections) {

    }

    @Override
    public void onSubmitCdsData(NewCollectionResponse cdsDataRequest) {

    }

    @Override
    public void onSubmitCdsDataError(Throwable throwable) {

    }

    @Override
    public void onSubmitCollectionError(Throwable throwable) {

    }

    @Override
    public void onLoginPDSResponse(CamLoginResponse response) {

    }

    @Override
    public void onLoginBDSResponse(CamLoginResponse response) {

    }

    @Override
    public void onLoginCollectionResponse(CamLoginResponse response) {

    }

    @Override
    public void onMilkCollectionResponse(List<MilkCollection> milkCollections) {

    }

    @Override
    public void onCdsUploadResponse(NewCollectionResponse response, CdsDataCollection cdsDataCollection) {

    }

    @Override
    public void onBdsUploadResponse(NewCollectionResponse response, BdsDataCollections bdsDataCollections) {

    }

    @Override
    public void onPdsUploadResponse(NewCollectionResponse response, PdsDataCollection pdsDataCollections) {

    }

    @Override
    public void onMilkCollectionUploadResponse(NewCollectionResponse response, MilkCollection milkCollection) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ON DESTROY","ACTIVITY UNDESTROY CALLED");
        offlineSavedDataViewModel.dispose();
    }
}
