package com.enyata.camdiary.ui.collections.data.pdsData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.PdsDataRequest;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityPdsDataBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.data.dataFarmerDetail.DataFarmerDetailActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;

public class PdsDataActivity extends BaseActivity<ActivityPdsDataBinding, PdsDataViewModel>implements PdsDataNavigator {

    LinearLayout incomeFragment, farmInfoFragment;
    ImageView incomeToggle, farmInfoToggle;
    @Inject
    ViewModelProviderFactory factory;

    @Inject
    Gson gson;
    String farmerVerificationId;
    String sourcesIncomeText, mainIncomeText, monthEarningText, weekEarningText, marketText, milkPerdayText, houseHoldConsumeText,milkForSaleText,challengesText,abujaCowText,totalCowText, milkingCowText,recommendText, feedbackText;
    EditText sourcesIncome, mainIncome,weekEarning, monthEarning,market,milkPerDay,houseHoldConsume,milkForsale,challenges,abujaCow,totalCow,milkingCow,recommend,feedback;

    PdsDataViewModel pdsDataViewModel;
    ActivityPdsDataBinding activityPdsDataBinding;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pds_data;
    }

    @Override
    public PdsDataViewModel getViewModel() {
        pdsDataViewModel = ViewModelProviders.of(this,factory).get(PdsDataViewModel.class);
        return pdsDataViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pdsDataViewModel.setNavigator(this);
        activityPdsDataBinding = getViewDataBinding();
        incomeFragment = activityPdsDataBinding.incomeFragment;
        farmInfoFragment = activityPdsDataBinding.farmInfoFragment;
        incomeToggle = activityPdsDataBinding.incomeToggle;
        farmInfoToggle = activityPdsDataBinding.farmInfoToggle;
        sourcesIncome = activityPdsDataBinding.sourcesOfIncome;
        mainIncome = activityPdsDataBinding.mainIncome;
        weekEarning = activityPdsDataBinding.weekEarning;
        monthEarning = activityPdsDataBinding.monthlyEarning;
        market = activityPdsDataBinding.marketTime;
        milkPerDay = activityPdsDataBinding.milkPerDay;
        houseHoldConsume = activityPdsDataBinding.houseHoldConsume;
        milkForsale = activityPdsDataBinding.milkForSale;
        challenges = activityPdsDataBinding.milkChallenges;
        abujaCow = activityPdsDataBinding.abujaCow;
        totalCow = activityPdsDataBinding.totalCow;
        milkingCow = activityPdsDataBinding.milkingCow;
        recommend = activityPdsDataBinding.recommendation;
        feedback = activityPdsDataBinding.feedback;
        farmerVerificationId = pdsDataViewModel.getFarmerVerificationNo();

    }

    @Override
    public void onIncomeSource() {
        hideKeyboard();
        if (incomeFragment.getVisibility()== View.GONE){
            incomeFragment.setVisibility(View.VISIBLE);
            incomeToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            incomeFragment.setVisibility(View.GONE);
            incomeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }



    }

    @Override
    public void onFarmInfo() {
        hideKeyboard();
        if (farmInfoFragment.getVisibility()== View.GONE){
            farmInfoFragment.setVisibility(View.VISIBLE);
            farmInfoToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            farmInfoFragment.setVisibility(View.GONE);
            farmInfoToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onSubmitPds() {
        hideKeyboard();
        Log.i("FarmerVID", farmerVerificationId);
        sourcesIncomeText = sourcesIncome.getText().toString();
        mainIncomeText = mainIncome.getText().toString();
        weekEarningText = weekEarning.getText().toString();
        monthEarningText = monthEarning.getText().toString();
        marketText = market.getText().toString();
        milkPerdayText =  milkPerDay.getText().toString();
        houseHoldConsumeText = houseHoldConsume.getText().toString();
        milkForSaleText = milkForsale.getText().toString();
        challengesText = challenges.getText().toString();
        abujaCowText = abujaCow.getText().toString();
        totalCowText = totalCow.getText().toString();
        milkingCowText = milkingCow.getText().toString();
        recommendText = recommend.getText().toString();
        feedbackText = feedback.getText().toString();

        if (InternetConnection.getInstance(PdsDataActivity.this).isOnline()){
            PdsDataRequest.Request request = new PdsDataRequest.Request(farmerVerificationId,sourcesIncomeText,mainIncomeText,weekEarningText,monthEarningText,marketText,milkPerdayText,houseHoldConsumeText,milkForSaleText,challengesText,abujaCowText,totalCowText,milkingCowText,recommendText,feedbackText);
            pdsDataViewModel.submitPdsDataCollection(request);

        }else {
            Alert.showFailed(getApplicationContext(),"Please check your internet connection and try again");
        }

    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), DataFarmerDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(NewCollectionResponse response) {
        switch (pdsDataViewModel.getCurrentUserType()){
            case "collector":{
                Alert.showSuccess(getApplicationContext(), response.getResponseMessage());
                Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
                startActivity(intent);
                break;
            }
            case  "data collector":{
                Alert.showSuccess(getApplicationContext(),response.getResponseMessage());
                Intent intent = new Intent(getApplicationContext(), DataCollectorDashboardActivity.class);
                startActivity(intent);
                break;
            }
        }
        Log.i("RESPONSE","SUCCESSFUL");
    }

    @Override
    public void handleError(Throwable throwable) {
        try {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            }
        }else{
            Alert.showFailed(getApplicationContext(), " Unable to connect to the internet");
        }

        }catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pdsDataViewModel.onDispose();
    }
}
