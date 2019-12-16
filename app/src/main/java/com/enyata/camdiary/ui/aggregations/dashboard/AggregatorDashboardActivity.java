package com.enyata.camdiary.ui.aggregations.dashboard;

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
import com.enyata.camdiary.databinding.ActivityAggregatorDashboardBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeViewModel;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorAdapter;
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class AggregatorDashboardActivity extends BaseActivity<ActivityAggregatorDashboardBinding, AggregatorDashboardViewModel> implements AggregatorDashboardNavigator {

    AggregatorListAdapter aggregatorListAdapter;
    ListView listView;
    ArrayList<AggregatorList> aggregatorLists = new ArrayList<>();

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


        JSONObject collector1 = new JSONObject();
        try {
            collector1.put("fullName", "Akin, Solomon");

            collector1.put("companyId", "X3478JND8992");

            collector1.put("myLitres", "40 litres");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject collector2 = new JSONObject();
        try {
            collector2.put("fullName", "Akin, Solomon");

            collector2.put("companyId", "X3478JND8992");

            collector2.put("myLitres", "40 litres");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject collector3 = new JSONObject();
        try {
            collector3.put("fullName", "Akin, Solomon");

            collector3.put("companyId", "X3478JND8992");

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

                String companyId = object.getString("companyId");

                String myLitres = object.getString("myLitres");

                aggregatorLists.add(new AggregatorList(fullName, companyId, myLitres));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }


        aggregatorListAdapter = new AggregatorListAdapter(AggregatorDashboardActivity.this, aggregatorLists);
        listView.setAdapter(aggregatorListAdapter);


        aggregatorDashboardAdapter = new AggregatorDashboardAdapter(layouts, AggregatorDashboardActivity.this);
        pager.setAdapter(aggregatorDashboardAdapter);
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


}
