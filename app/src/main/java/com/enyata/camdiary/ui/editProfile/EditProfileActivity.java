package com.enyata.camdiary.ui.editProfile;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityEditProfileBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.editProfile.EditProfileFragment.ChangePassword;
import com.enyata.camdiary.ui.editProfile.EditProfileFragment.EditProfileFragment;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding,EditProfileViewModel>implements EditProfileNavigator {

    @Inject
    ViewModelProviderFactory factory;
    EditProfileViewModel editProfileViewModel;
    ActivityEditProfileBinding activityEditProfileBinding;
    TabLayout tabLayout;
    LinearLayout toggleFragment;
    Fragment changePassword = new ChangePassword();
    Fragment editProfile = new EditProfileFragment();


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    public EditProfileViewModel getViewModel() {
        editProfileViewModel = ViewModelProviders.of(this,factory).get(EditProfileViewModel.class);
        return editProfileViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editProfileViewModel.setNavigator(this);
        activityEditProfileBinding = getViewDataBinding();
        tabLayout = activityEditProfileBinding.tabLayout;
        toggleFragment = activityEditProfileBinding.toggleFragment;
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
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }
}
