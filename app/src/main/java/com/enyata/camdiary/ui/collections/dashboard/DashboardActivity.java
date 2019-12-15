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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityCollectionDashboardBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity<ActivityCollectionDashboardBinding,DashboardViewModel>implements DashboardNavigator {
    DashboardCollectorAdapter dashboardCollectorAdapter;
    ListView listView;
    ArrayList<DashboardCollectorList> dashboardCollectorLists = new ArrayList<>();

    DashboardAdapter dashboardAdapter;
    int[] layouts = {R.layout.collection_first_slide, R.layout.collection_second_slide, R.layout.collection_third_slide};
    LinearLayout slideLayout;
    ImageView[] slider_dash;
    ViewPager pager;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardViewModel.setNavigator(this);
        pager =findViewById(R.id.pager);
        slideLayout = findViewById(R.id.slideLayout);
        listView = findViewById(R.id.listView);


        JSONObject collector1 = new JSONObject();
        try {
            collector1.put("fullName", "Akin, Solomon");
            collector1.put("companyName", "Xamsatde");
            collector1.put("companyId", "X3478JND8992");
            collector1.put("status", "Rejected");
            collector1.put("myLitres", "40 litres");

        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject collector2 = new JSONObject();
        try {
            collector2.put("fullName", "Akin, Solomon");
            collector2.put("companyName", "Xamsatde");
            collector2.put("companyId", "X3478JND8992");
            collector2.put("status", "Rejected");
            collector2.put("myLitres", "40 litres");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject collector3 = new JSONObject();
        try {
            collector3.put("fullName", "Akin, Solomon");
            collector3.put("companyName", "Xamsatde");
            collector3.put("companyId", "X3478JND8992");
            collector3.put("status", "Rejected");
            collector3.put("myLitres", "40 litres");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray array = new JSONArray();
        array.put(collector1);
        array.put(collector2);
        array.put(collector3);
        array.put(collector2);
        array.put(collector1);
        array.put(collector3);


        for (int i = 0; i < array.length(); i++) {

            try {
                Log.i("message", array.toString());

                JSONObject object = array.getJSONObject(i);
                String fullName = object.getString("fullName");
                String companyName = object.getString("companyName");
                String companyId= object.getString("companyId");
                String status= object.getString("status");
                String myLitres = object.getString("myLitres");



                dashboardCollectorLists.add(new DashboardCollectorList(fullName,companyName,companyId,status,myLitres));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }


        dashboardCollectorAdapter = new DashboardCollectorAdapter(DashboardActivity.this, dashboardCollectorLists);
        listView.setAdapter(dashboardCollectorAdapter);




        dashboardAdapter = new DashboardAdapter(layouts, DashboardActivity.this);
        pager.setAdapter(dashboardAdapter);
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
}
