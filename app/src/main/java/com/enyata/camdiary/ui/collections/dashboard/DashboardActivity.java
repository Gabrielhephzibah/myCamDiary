package com.enyata.camdiary.ui.collections.dashboard;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.TodayCollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.databinding.ActivityCollectionDashboardBinding;
import com.enyata.camdiary.databinding.ActivityLoginBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity<ActivityCollectionDashboardBinding,DashboardViewModel>implements DashboardNavigator {
    DashboardCollectorAdapter dashboardCollectorAdapter;
    ListView listView;
    ArrayList<DashboardCollectorList> dashboardCollectorLists = new ArrayList<>();

    DashboardAdapter dashboardAdapter;
    private ActivityCollectionDashboardBinding mActivityDashboardBinding;
    int[] layouts = {R.layout.collection_first_slide, R.layout.collection_second_slide, R.layout.collection_third_slide};
    LinearLayout slideLayout;
    ImageView[] slider_dash;
    ViewPager pager;

    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private DashboardViewModel dashboardViewModel;

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
        dashboardViewModel = ViewModelProviders.of(this,factory).get(DashboardViewModel.class);
        return dashboardViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            VolumeResponse response = gson.fromJson(error.getErrorBody(), VolumeResponse.class);
            Alert.showFailed(getApplicationContext(),response.getResponseMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardViewModel.setNavigator(this);
        pager = findViewById(R.id.pager);
        slideLayout = findViewById(R.id.slideLayout);
        listView = findViewById(R.id.listView);
        TextView username = findViewById(R.id.username);
        TextView today = findViewById(R.id.today);
        today.setText(dashboardViewModel.getCurrentDate());
        username.setText(String.format("Hey,%s", dashboardViewModel.getFirstName()));

        if (!isNetworkConnected()) {
            Alert.showInfo(getApplicationContext(),"No internet connection, please check internet settings and try again");
            return;
        }else{
            dashboardViewModel.getVolumeOfAcceptedCollection();
            dashboardViewModel.getVolumeOfRejectedCollection();
            dashboardViewModel.getTodaysCollection();
            dashboardViewModel.getAllEntries();
        }

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
        dashboardViewModel.setEntries(entries.getCount());
    }

    @Override
    public void getTodayCollection(TodayCollectionResponse todayCollectionResponse) {
        for (CollectionResponse response : todayCollectionResponse.getData()) {
            dashboardCollectorLists.add(new DashboardCollectorList("Mike", "Enyata", "XXXXX", response.getStatusOfCollection(), String.valueOf(response.getVolume())));
            dashboardCollectorAdapter = new DashboardCollectorAdapter(DashboardActivity.this, dashboardCollectorLists);
            listView.setAdapter(dashboardCollectorAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dashboardViewModel.dispose();
    }
}
