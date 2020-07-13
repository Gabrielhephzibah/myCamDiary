package com.enyata.camdiary.ui.aggregations.aggregatorEditProfile;

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
import com.enyata.camdiary.databinding.ActivityAggregatorEditProfileBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.editProfile.EditProfileFragment.ChangePassword;
import com.enyata.camdiary.ui.editProfile.EditProfileFragment.EditProfileFragment;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;

public class AggregatorEditProfileActivity extends BaseActivity<ActivityAggregatorEditProfileBinding, AggregatorEditProfileViewModel>implements  AggregatorEditProfileNavigator {
   @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    AggregatorEditProfileViewModel aggregatorEditProfileViewModel;
    TabLayout tabLayout;
    LinearLayout toggleFragment;
    Fragment changePassword = new AggregatorChangePasswordFragment();
    Fragment editProfile = new AggregatorEditProfileFragment();
    ActivityAggregatorEditProfileBinding activityAggregatorEditProfileBinding;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_aggregator_edit_profile;
    }

    @Override
    public AggregatorEditProfileViewModel getViewModel() {
        aggregatorEditProfileViewModel = ViewModelProviders.of(this,factory).get(AggregatorEditProfileViewModel.class);
        return aggregatorEditProfileViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aggregatorEditProfileViewModel.setNavigator(this);
        activityAggregatorEditProfileBinding = getViewDataBinding();

        tabLayout = activityAggregatorEditProfileBinding.tabLayout;
        toggleFragment = activityAggregatorEditProfileBinding.toggleFragment;
        tabLayout.addTab(tabLayout.newTab().setText("Edit Profile"),true);
        tabLayout.addTab(tabLayout.newTab().setText("Change Password"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#21523C"));
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmenttrans =fragment.beginTransaction();
        fragmenttrans.replace(R.id.toggleFragment,editProfile);
        fragmenttrans.commit();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {

                    case 1: {
                        FragmentManager fragmentMa = getSupportFragmentManager();
                        FragmentTransaction fragment =fragmentMa.beginTransaction();
                        fragment.replace(R.id.toggleFragment,changePassword);
                        fragment.commit();
                        break;
                    }
                    default:{
                        FragmentManager fragment = getSupportFragmentManager();
                        FragmentTransaction fragmenttrans =fragment.beginTransaction();
                        fragmenttrans.replace(R.id.toggleFragment,editProfile);
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
        Intent intent  = new Intent( getApplicationContext(), AggregatorDashboardActivity.class);
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
        }catch (IllegalStateException | JsonSyntaxException|NullPointerException|ClassCastException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
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
        aggregatorEditProfileViewModel.onDispose();
    }

}
