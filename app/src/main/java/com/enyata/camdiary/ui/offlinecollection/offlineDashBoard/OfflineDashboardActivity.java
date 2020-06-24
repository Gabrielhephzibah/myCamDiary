package com.enyata.camdiary.ui.offlinecollection.offlineDashBoard;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.TableLayout;
import android.widget.Toast;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.databinding.ActivityOfflineDashboardBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveryEditProfile.DeliveryChangePasswordFragment;
import com.enyata.camdiary.ui.deliveries.deliveryEditProfile.DeliveryEditProfileFragment;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import javax.inject.Inject;

public class OfflineDashboardActivity extends BaseActivity<ActivityOfflineDashboardBinding, OfflineDashboardViewModel>implements OfflineDashboardNavigator {

    @Inject
    ViewModelProviderFactory factory;
    OfflineDashboardViewModel offlineDashboardViewModel;
    ActivityOfflineDashboardBinding activityOfflineDashboardBinding;
    TabLayout tabLayout;
    LinearLayout toggleLayout;
    Fragment milkCollection = new OfflineMilkCollectionFragment();
    Fragment dataCollection = new OfflineDataCollectionFragment();
    ImageView home,savedData,logout;
    int backButtonPressed = 0;


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_offline_dashboard;
    }

    @Override
    public OfflineDashboardViewModel getViewModel() {
        offlineDashboardViewModel = ViewModelProviders.of(this,factory).get(OfflineDashboardViewModel.class);
        return offlineDashboardViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offlineDashboardViewModel.setNavigator(this);
        activityOfflineDashboardBinding = getViewDataBinding();
        tabLayout = activityOfflineDashboardBinding.offlineTabLayout;
        toggleLayout = activityOfflineDashboardBinding.toggleLayout;
        home = activityOfflineDashboardBinding.included.offlineHome;
        savedData = activityOfflineDashboardBinding.included.offlineSavedData;
        logout = activityOfflineDashboardBinding.included.offlineLogout;
        tabLayout.addTab(tabLayout.newTab().setText("Data Collection"),true);
        tabLayout.addTab(tabLayout.newTab().setText("Milk Collection"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#EBEBEB"));

        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmenttrans =fragment.beginTransaction();
        fragmenttrans.replace(R.id.toggleLayout,dataCollection);
        fragmenttrans.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {

                    case 1: {
                        FragmentManager fragmentMa = getSupportFragmentManager();
                        FragmentTransaction fragment =fragmentMa.beginTransaction();
                        fragment.replace(R.id.toggleLayout,milkCollection);
                        fragment.commit();
                        break;
                    }
                    default:{
                        FragmentManager fragment = getSupportFragmentManager();
                        FragmentTransaction fragmenttrans =fragment.beginTransaction();
                        fragmenttrans.replace(R.id.toggleLayout,dataCollection);
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

        savedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OfflineSavedDataActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (backButtonPressed >= 2) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button twice to close the application.", Toast.LENGTH_SHORT).show();
            backButtonPressed++;

        }
    }
}
