package com.enyata.camdiary.ui.aggregations.dashboard;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
import com.enyata.camdiary.data.model.api.response.FarmerIdResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.databinding.ActivityAggregatorDashboardBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

public class AggregatorDashboardActivity extends BaseActivity<ActivityAggregatorDashboardBinding, AggregatorDashboardViewModel> implements AggregatorDashboardNavigator {
    @Inject
    Gson gson;


    AggregatorListAdapter aggregatorListAdapter;
    ListView listView;
    ArrayList<AggregatorList> aggregatorLists = new ArrayList<>();
    AggregatorHomepageAdapter aggregatorHomepageAdapter;
    TextView aggregator_name;
    TextView date;
    ActivityAggregatorDashboardBinding activityAggregatorDashboardBinding;
    ImageView aggregatorUrl;
    CFAlertDialog alertDialog1;

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
        activityAggregatorDashboardBinding = getViewDataBinding();
        String aggregatorImage = aggregatorDashboardViewModel.getAggregatorImage();
        aggregatorUrl = findViewById(R.id.aggregatorImage);
        Picasso.get().load(aggregatorImage).into(aggregatorUrl);

        pager = activityAggregatorDashboardBinding.pager;

        slideLayout = activityAggregatorDashboardBinding.slideLayout;

       listView = activityAggregatorDashboardBinding.listView;

       aggregator_name = activityAggregatorDashboardBinding.aggregatorName;

       date = activityAggregatorDashboardBinding.date;

        date.setText(aggregatorDashboardViewModel.getCurrentDate());
        aggregator_name.setText(String.format("Hey,%s", aggregatorDashboardViewModel.getFirstName()));

        if (InternetConnection.getInstance(this).isOnline()) {
            aggregatorDashboardViewModel.getTotalVolumeCollectedByAggregator();
            aggregatorDashboardViewModel.getTotalNumberOfCollectors();
            aggregatorDashboardViewModel.getAggregatorTodayCollection();
        }else{
            Alert.showFailed(getApplicationContext(), "Please Check Your Internet Connection and try again");

        }

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
        if (throwable != null ) {
            ANError error = (ANError) throwable;
            FarmerIdResponse response = gson.fromJson(error.getErrorBody(), FarmerIdResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            }else {
                Alert.showFailed(getApplicationContext(),"Unable to connect to the internet");
            }

        }

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
            aggregatorLists.add(new AggregatorList(response.getCollectorDetails().getFirstName() + " "+ response.getCollectorDetails().getLastName(), response.getCollectorDetails().getVerificationId(), response.getAggregationTotalVolume() + " litres" ));
            aggregatorListAdapter = new AggregatorListAdapter(AggregatorDashboardActivity.this,aggregatorLists);
            listView.setAdapter(aggregatorListAdapter);

        }


    }

    @Override
    public void onLogOut() {
        CFAlertDialog.Builder alertDialog = new CFAlertDialog.Builder(this);
        LayoutInflater inflater = AggregatorDashboardActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout_bottom_sheet,null);
               alertDialog .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)

//                .setTitle("LOG-OUT")
//                .setMessage("Are you sure you want  to Log-out")
                .setCancelable(true)
//                .setBackgroundColor(Color.parseColor("#21523C"))

                .setHeaderView(dialogView);
        Button yes = dialogView.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });

       alertDialog1 = alertDialog.create();
        alertDialog1.show();





//        alertDialog.show();


//        BottomSheetMaterialDialog.Builder bottomSheet = new BottomSheetMaterialDialog.Builder(AggregatorDashboardActivity.this);
//        LayoutInflater inflater = AggregatorDashboardActivity.this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.logout_bottom_sheet,null);
////        bottomSheet.setTitle("Are you Sure You want to exit this App?");
//        bottomSheet.setPositiveButton("Yes", new BottomSheetMaterialDialog.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });

//        bottomSheet.setNegativeButton("NO", new BottomSheetMaterialDialog.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                dialogInterface.dismiss();
//            }
//        });
//        BottomSheetMaterialDialog alert = bottomSheet.build();
//        alert.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        aggregatorDashboardViewModel.dispose();
    }


}
