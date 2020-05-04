package com.enyata.camdiary.ui.datacollector.dataCollectorEditProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.databinding.ActivityDataCollectorEditProfileBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorChangePasswordFragment;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileFragment;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;

public class DataCollectorEditProfileActivity extends BaseActivity<ActivityDataCollectorEditProfileBinding, DataCollectorEditProfileViewModel>implements DataCollectorEditProfileNavigator {


    @Inject
    Gson gson;
    @Inject
    ViewModelProviderFactory factory;
    ActivityDataCollectorEditProfileBinding activityDataCollectorEditProfileBinding;
    DataCollectorEditProfileViewModel dataCollectorEditProfileViewModel;
    TabLayout tabLayout;
    LinearLayout toggleFragment;
    Fragment changePassword = new DataCollectorChangePasswordFragment();
    Fragment editProfile = new DataCollectorEditProfileFragment();


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_collector_edit_profile ;
    }

    @Override
    public DataCollectorEditProfileViewModel getViewModel() {
        dataCollectorEditProfileViewModel = ViewModelProviders.of(this,factory).get(DataCollectorEditProfileViewModel.class);
        return dataCollectorEditProfileViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataCollectorEditProfileViewModel.setNavigator(this);
       activityDataCollectorEditProfileBinding = getViewDataBinding();

        tabLayout = activityDataCollectorEditProfileBinding.tabLayout;
        toggleFragment = activityDataCollectorEditProfileBinding.toggleFragment;
        tabLayout.addTab(tabLayout.newTab().setText("Edit Profile"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Change Password"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#21523C"));
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmenttrans = fragment.beginTransaction();
        fragmenttrans.replace(R.id.toggleFragment, editProfile);
        fragmenttrans.commit();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {

                    case 1: {
                        FragmentManager fragmentMa = getSupportFragmentManager();
                        FragmentTransaction fragment = fragmentMa.beginTransaction();
                        fragment.replace(R.id.toggleFragment, changePassword);
                        fragment.commit();
                        break;
                    }
                    default: {
                        FragmentManager fragment = getSupportFragmentManager();
                        FragmentTransaction fragmenttrans = fragment.beginTransaction();
                        fragmenttrans.replace(R.id.toggleFragment, editProfile);
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


    }


    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), DataCollectorDashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleError(Throwable throwable) {
        try {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            ResetPasswordResponse response = gson.fromJson(error.getErrorBody(), ResetPasswordResponse.class);
            if (error.getErrorBody() != null) {
                Alert.showFailed(getApplicationContext(), response.getMessage());

            } else {
                Alert.showFailed(getApplicationContext(), "Unable to Connect to the Internet");
            }
        }
        }catch (IllegalStateException | JsonSyntaxException exception){
            Alert.showFailed(getApplicationContext(),"An  unknown error occurred");
        }
    }

    @Override
    public void onResponse(ResetPasswordResponse response) {
        Alert.showSuccess(getApplicationContext(), response.getMessage());

    }

    @Override
    public void onEditProfile(CollectorDetailsResponse response) {
        Alert.showSuccess(getApplicationContext(),response.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataCollectorEditProfileViewModel.onDispose();
    }
}
