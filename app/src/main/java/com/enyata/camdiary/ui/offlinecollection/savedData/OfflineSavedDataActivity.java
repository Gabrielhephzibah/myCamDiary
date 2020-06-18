package com.enyata.camdiary.ui.offlinecollection.savedData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityOfflineSavedDataBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDataCollectionFragment;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineMilkCollectionFragment;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveySavedData.DataSurveySavedDataFragement;
import com.enyata.camdiary.ui.offlinecollection.savedData.milkCollectionSavedData.MilkCollectionSavedDataFragment;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

public class OfflineSavedDataActivity extends BaseActivity<ActivityOfflineSavedDataBinding, OfflineSavedDataViewModel>implements OfflineSavedDataNavigator {

    @Inject
    ViewModelProviderFactory factory;
    OfflineSavedDataViewModel offlineSavedDataViewModel;
    ActivityOfflineSavedDataBinding activityOfflineSavedDataBinding;
    TabLayout tabLayout;
    LinearLayout toggleLayout;
    Fragment milkCollection = new MilkCollectionSavedDataFragment();
    Fragment dataCollection = new DataSurveySavedDataFragement();
    ImageView home,savedData,logout;


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
        tabLayout.addTab(tabLayout.newTab().setText("Data Collection"),true);
        tabLayout.addTab(tabLayout.newTab().setText("Milk Collection"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#EBEBEB"));

        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmenttrans =fragment.beginTransaction();
        fragmenttrans.replace(R.id.savedDataToggleLayout,dataCollection);
        fragmenttrans.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 1: {
                        FragmentManager fragmentMa = getSupportFragmentManager();
                        FragmentTransaction fragment =fragmentMa.beginTransaction();
                        fragment.replace(R.id.savedDataToggleLayout,milkCollection);
                        fragment.commit();
                        break;
                    }
                    default:{
                        FragmentManager fragment = getSupportFragmentManager();
                        FragmentTransaction fragmenttrans =fragment.beginTransaction();
                        fragmenttrans.replace(R.id.savedDataToggleLayout,dataCollection);
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
        Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);
    }
}
