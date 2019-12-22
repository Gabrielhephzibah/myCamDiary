package com.enyata.camdiary.ui.aggregations.dashboard;

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
import android.widget.TextView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.databinding.ActivityAggregatorDashboardBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class AggregatorDashboardActivity extends BaseActivity<ActivityAggregatorDashboardBinding, AggregatorDashboardViewModel> implements AggregatorDashboardNavigator {

    AggregatorListAdapter aggregatorListAdapter;
    ListView listView;
    ArrayList<AggregatorList> aggregatorLists = new ArrayList<>();
    AggregatorHomepageAdapter aggregatorHomepageAdapter;
    TextView aggregatorName;
    TextView date;

    @Inject
    ViewModelProviderFactory factory;
    private AggregatorDashboardViewModel aggregatorDashboardViewModel;
    ViewPager pager;
    int[] layouts = {R.layout.aggregator_first_slide, R.layout.aggregator_second_slide};
    private AggregatorDashboardAdapter aggregatorDashboardAdapter;
    LinearLayout slideLayout;
    ImageView[] slider_dash;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_aggregator_dashboard;
    }

    @Override
    public AggregatorDashboardViewModel getViewModel() {
        aggregatorDashboardViewModel = ViewModelProviders.of(this, factory).get(AggregatorDashboardViewModel.class);
        return aggregatorDashboardViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aggregatorDashboardViewModel.setNavigator(this);
        pager = findViewById(R.id.pager);
        slideLayout = findViewById(R.id.slideLayout);
        listView = findViewById(R.id.listView);
        aggregatorName = findViewById(R.id.aggregator_name);
        date = findViewById(R.id.date);
        date.setText(aggregatorDashboardViewModel.getCurrentDate());
        aggregatorName.setText(String.format("Hey,%s", aggregatorDashboardViewModel.getFirstName()));



        if (!isNetworkConnected()) {
            Alert.showInfo(getApplicationContext(),"No internet connection, please check internet settings and try again");
            return;
        }else{
            aggregatorDashboardViewModel.getTotalVolumeCollectedByAggregator();
            aggregatorDashboardViewModel.getTotalNumberOfCollectors();
            aggregatorDashboardViewModel.getAggregatorTodayCollection();
        }


//        JSONObject collector1 = new JSONObject();
//        try {
//            collector1.put("fullName", "Akin, Solomon");
//
//            collector1.put("companyId", "64ERT234KI89");
//
//            collector1.put("myLitres", "40 litres");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//
//        JSONObject collector2 = new JSONObject();
//        try {
//            collector2.put("fullName", "Akin, Solomon");
//
//            collector2.put("companyId", "64ERT234KI89");
//
//            collector2.put("myLitres", "40 litres");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


//        JSONObject collector3 = new JSONObject();
//        try {
//            collector3.put("fullName", "Akin, Solomon");
//
//            collector3.put("companyId", "64ERT234KI89");
//
//            collector3.put("myLitres", "40 litres");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        JSONArray array = new JSONArray();
//        array.put(collector1);
//        array.put(collector2);
//        array.put(collector3);
//        array.put(collector2);
//        array.put(collector1);
//        array.put(collector3);


//        for (int i = 0; i < array.length(); i++) {
//
//            try {
//                Log.i("message", array.toString());
//
//                JSONObject object = array.getJSONObject(i);
//                String fullName = object.getString("fullName");
//
//                String companyId = object.getString("companyId");
//
//                String myLitres = object.getString("myLitres");
//
//                aggregatorLists.add(new AggregatorList(fullName, companyId, myLitres));
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//        }


//        aggregatorListAdapter = new AggregatorListAdapter(AggregatorDashboardActivity.this, aggregatorLists);
//        listView.setAdapter(aggregatorListAdapter);


        aggregatorHomepageAdapter = new AggregatorHomepageAdapter(this, getSupportFragmentManager());
        pager.setAdapter(aggregatorHomepageAdapter);
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
    public void handleError(Throwable throwable) {

    }

    @Override
    public void history() {
        Intent intent = new Intent(getApplicationContext(), AggregatorHIstoryActivity.class);
        startActivity(intent);


    }

    @Override
    public void scan() {
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);

    }

    @Override
    public void out() {

    }

    @Override
    public void createSliderDash(int current_position) {
        if (slideLayout != null)
            slideLayout.removeAllViews();

        slider_dash = new ImageView[layouts.length];
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
    public void displayAggregatorVolume(AggregationVolume volume) {
        aggregatorDashboardViewModel.setAggregatorVolume(volume.getData());
    }

    @Override
    public void numberOfCollectors(NumberOfCollectors aggregation) {
        aggregatorDashboardViewModel.setTotalAggregation(aggregation.getData());

    }

    @Override
    public void getAggregatorTodayCollection(AggregationCollectionResponse todayCollection) {
        for (AggregatorCollections response : todayCollection.getData() ) {
            aggregatorLists.add(new AggregatorList("Tolu Ajibade", "XXXX", response.getVolume()+ "litres" ));
            aggregatorListAdapter = new AggregatorListAdapter(AggregatorDashboardActivity.this,aggregatorLists);
            listView.setAdapter(aggregatorListAdapter);

        }


    }

    @Override
    public void onLogOut() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }


}
