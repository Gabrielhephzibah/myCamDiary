package com.enyata.camdiary.ui.collections.collectorEditProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.databinding.ActivityCollectorEditProfileBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.editProfile.EditProfileFragment.ChangePassword;
import com.enyata.camdiary.ui.editProfile.EditProfileFragment.EditProfileFragment;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import javax.inject.Inject;

import static android.Manifest.permission.CAMERA;

public class CollectorEditProfileActivity extends BaseActivity<ActivityCollectorEditProfileBinding, CollectorEditProfileViewModel>implements  CollectorEditProfileNavigator {
    @Inject
    Gson gson;
    @Inject
    ViewModelProviderFactory factory;
    CollectorEditProfileViewModel collectorEditProfileViewModel;
    ActivityCollectorEditProfileBinding activityCollectorEditProfileBinding;
    TabLayout tabLayout;
    LinearLayout toggleFragment;
    Fragment changePassword = new CollectorChangePasswordFragment();
    Fragment editProfile = new CollectorEditProfileFragment();



    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collector_edit_profile;
    }

    @Override
    public CollectorEditProfileViewModel getViewModel() {
        collectorEditProfileViewModel = ViewModelProviders.of(this, factory).get(CollectorEditProfileViewModel.class);
        return collectorEditProfileViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectorEditProfileViewModel.setNavigator(this);
        activityCollectorEditProfileBinding = getViewDataBinding();

        tabLayout = activityCollectorEditProfileBinding.tabLayout;
        toggleFragment = activityCollectorEditProfileBinding.toggleFragment;
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
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            ResetPasswordResponse response = gson.fromJson(error.getErrorBody(), ResetPasswordResponse.class);
            if (error.getErrorBody() != null) {
                Alert.showFailed(getApplicationContext(), response.getMessage());

            } else {
                Alert.showFailed(getApplicationContext(), "Unable to Connect to the Internet");
            }
        }
    }

    @Override
    public void onResponse(ResetPasswordResponse response) {
        Alert.showSuccess(getApplicationContext(), response.getMessage());

    }

    @Override
    public void onEditProfile(ResetPasswordResponse response) {
        Alert.showSuccess(getApplicationContext(),response.getMessage());
        Intent intent =  new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        collectorEditProfileViewModel.onDispose();
    }
}
