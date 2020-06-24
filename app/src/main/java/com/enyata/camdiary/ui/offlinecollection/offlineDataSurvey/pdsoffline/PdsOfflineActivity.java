package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.pdsoffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;
import com.enyata.camdiary.databinding.ActivityPdsOfflineBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.cdsoffline.CdsOfflineActivity;
import com.enyata.camdiary.utils.Alert;

import java.util.List;

import javax.inject.Inject;

public class PdsOfflineActivity extends BaseActivity<ActivityPdsOfflineBinding,PdsOfflineViewModel> implements PdsOfflineNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityPdsOfflineBinding activityPdsOfflineBinding;
    PdsOfflineViewModel pdsOfflineViewModel;
    LinearLayout incomeFragment, farmInfoFragment;
    LinearLayout bioData;
    ImageView incomeToggle, farmInfoToggle, biodataToggle;
    String[] genderOption = {"", "Male", "Female"};
    String firstNameText, lastNameText, phoneNoText, genderText, farmerIdText, sourcesIncomeText, mainIncomeText, monthEarningText, weekEarningText, marketText, milkPerdayText, houseHoldConsumeText, milkForSaleText, challengesText, abujaCowText, totalCowText, milkingCowText, recommendText, feedbackText;
    EditText sourcesIncome, mainIncome, weekEarning, monthEarning, market, milkPerDay, houseHoldConsume, milkForsale, challenges, abujaCow, totalCow, milkingCow, recommend, feedback;
    EditText firstName, lastName, phoneNo, farmerId;
    Spinner gender;


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pds_offline;
    }

    @Override
    public PdsOfflineViewModel getViewModel() {
        pdsOfflineViewModel = ViewModelProviders.of(this, factory).get(PdsOfflineViewModel.class);
        return pdsOfflineViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pdsOfflineViewModel.setNavigator(this);
        activityPdsOfflineBinding = getViewDataBinding();
        bioData = activityPdsOfflineBinding.bioDataFragment;
        incomeFragment = activityPdsOfflineBinding.incomeFragment;
        farmInfoFragment = activityPdsOfflineBinding.farmInfoFragment;
        incomeToggle = activityPdsOfflineBinding.incomeToggle;
        farmInfoToggle = activityPdsOfflineBinding.farmInfoToggle;
        biodataToggle = activityPdsOfflineBinding.bioDataToggle;
        sourcesIncome = activityPdsOfflineBinding.sourcesOfIncome;
        firstName = activityPdsOfflineBinding.firstName;
        lastName = activityPdsOfflineBinding.lastName;
        phoneNo = activityPdsOfflineBinding.phoneNoEdit;
        mainIncome = activityPdsOfflineBinding.mainIncome;
        weekEarning = activityPdsOfflineBinding.weekEarning;
        monthEarning = activityPdsOfflineBinding.monthlyEarning;
        market = activityPdsOfflineBinding.marketTime;
        milkPerDay = activityPdsOfflineBinding.milkPerDay;
        houseHoldConsume = activityPdsOfflineBinding.houseHoldConsume;
        milkForsale = activityPdsOfflineBinding.milkForSale;
        challenges = activityPdsOfflineBinding.milkChallenges;
        abujaCow = activityPdsOfflineBinding.abujaCow;
        totalCow = activityPdsOfflineBinding.totalCow;
        milkingCow = activityPdsOfflineBinding.milkingCow;
        recommend = activityPdsOfflineBinding.recommendation;
        feedback = activityPdsOfflineBinding.feedback;
        gender = activityPdsOfflineBinding.genderSpinner;
        farmerId = activityPdsOfflineBinding.farmerId;

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(PdsOfflineActivity.this, android.R.layout.simple_spinner_item, genderOption);
        gender.setAdapter(genderAdapter);

    }

    @Override
    public void onIncomeSource() {
        hideKeyboard();
        if (incomeFragment.getVisibility() == View.GONE) {
            incomeFragment.setVisibility(View.VISIBLE);
            incomeToggle.setImageResource(R.drawable.ic_icon_open);
        } else {
            incomeFragment.setVisibility(View.GONE);
            incomeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onFarmInfo() {
        hideKeyboard();
        if (farmInfoFragment.getVisibility() == View.GONE) {
            farmInfoFragment.setVisibility(View.VISIBLE);
            farmInfoToggle.setImageResource(R.drawable.ic_icon_open);
        } else {
            farmInfoFragment.setVisibility(View.GONE);
            farmInfoToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onSubmitPds() {
        firstNameText = firstName.getText().toString().trim();
        lastNameText = lastName.getText().toString().trim();
        phoneNoText = phoneNo.getText().toString().trim();
        if (gender != null && gender.getSelectedItem() != null) {
            genderText = (String) gender.getSelectedItem();
        }
        sourcesIncomeText = sourcesIncome.getText().toString().trim();
        mainIncomeText = mainIncome.getText().toString().trim();
        weekEarningText = weekEarning.getText().toString().trim();
        monthEarningText = monthEarning.getText().toString().trim();
        marketText = market.getText().toString().trim();
        milkPerdayText = milkPerDay.getText().toString().trim();
        houseHoldConsumeText = houseHoldConsume.getText().toString().trim();
        milkForSaleText = milkForsale.getText().toString();
        challengesText = challenges.getText().toString().trim();
        abujaCowText = abujaCow.getText().toString().trim();
        totalCowText = totalCow.getText().toString().trim();
        milkingCowText = milkingCow.getText().toString().trim();
        recommendText = recommend.getText().toString().trim();
        feedbackText = feedback.getText().toString().trim();
        farmerIdText = farmerId.getText().toString().trim();

        Log.i("FIRSTName", firstNameText);
        Log.i("LASTName", lastNameText);
        Log.i("PhoneNumber", phoneNoText);
        Log.i("sourcesIncome", sourcesIncomeText);
        Log.i("mainincome", mainIncomeText);
        Log.i("weekearn", weekEarningText);
        Log.i("monthearn", monthEarningText);
        Log.i("market", marketText);
        Log.i("milkperday", milkPerdayText);
        Log.i("household", houseHoldConsumeText);
        Log.i("milkForsale", milkForSaleText);
        Log.i("challengesText", challengesText);
        Log.i("abujaCow", abujaCowText);
        Log.i("totalCow", totalCowText);
        Log.i("milkingCow", milkingCowText);
        Log.i("feedback", feedbackText);
        Log.i("recommendation", recommendText);
        Log.i("gender", genderText);
        isEditTextValueEmpty(firstNameText,lastNameText,phoneNoText,sourcesIncomeText,mainIncomeText,weekEarningText,monthEarningText,milkPerdayText,milkForSaleText,challengesText,abujaCowText,totalCowText,milkingCowText,feedbackText,genderText,recommendText,houseHoldConsumeText,marketText,farmerIdText);

    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBioData() {
        hideKeyboard();
        if (bioData.getVisibility() == View.GONE) {
            bioData.setVisibility(View.VISIBLE);
            biodataToggle.setImageResource(R.drawable.ic_icon_open);
        } else {
            bioData.setVisibility(View.GONE);
            biodataToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }
    }

    @Override
    public void onResponse() {
        Alert.showSuccess(getApplicationContext(),"PDS data saved successfully");
        Log.i("Successful","It was successful");
        Intent intent  = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void handleError(Throwable throwable) {
        Log.i("Error",throwable.getMessage());
        Alert.showFailed(getApplicationContext(),"Error while saving data");


    }


    public void isEditTextValueEmpty(String first_name, String last_name, String phoneNo, String sourcesOfIncome, String mainIncome, String weekEarn, String monthEarn, String milkPerDay, String milkForSale, String challenges, String abujaCow, String totalCow, String milkingCow, String feedback, String gender, String recommendation, String householdConsumption, String marketDay, String farmerId) {
        if (first_name.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "first name is required");
        } else if (last_name.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "last name is required");
        } else if (recommendation.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "recommendation is required");
        } else if (phoneNo.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "phone number is required");
        } else if (marketDay.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "market days is required");
        } else if (householdConsumption.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "household consumption is required");
        } else if (sourcesOfIncome.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "sources of income is required");
        } else if (mainIncome.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "main income is required");
        } else if (weekEarn.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "weekly earning is required");
        } else if (monthEarn.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "monthly earning is required");
        } else if (milkPerDay.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "volume of milk per day is required");
        } else if (milkForSale.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "volume of milk for sale is required");
        } else if (challenges.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "challenges is required");
        } else if (abujaCow.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "numbers of cow in Abuja is required");
        } else if (totalCow.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "total numbers of cow is required");
        } else if (milkingCow.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "numbers of milking cow is required");
        } else if (feedback.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "feedback is required");
        }else if (gender.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "gender is required");
        }else if (farmerId.isEmpty()) {
            Alert.showFailed(getApplicationContext(), "farmerId is required");
        }
        else {
            PdsDataCollection pdsDataCollection = new PdsDataCollection();
            pdsDataCollection.setFirstName(firstNameText);
            pdsDataCollection.setLastName(lastNameText);
            pdsDataCollection.setRecommendations(recommendText);
            pdsDataCollection.setPhone_no(phoneNoText);
            pdsDataCollection.setMarketDays(marketText);
            pdsDataCollection.setHouseHoldConsumption(houseHoldConsumeText);
            pdsDataCollection.setSourcesOfIncome(sourcesIncomeText);
            pdsDataCollection.setMainIncome(mainIncomeText);
            pdsDataCollection.setWeekEarning(weekEarningText);
            pdsDataCollection.setMonthlyEarning(monthEarningText);
            pdsDataCollection.setLitresOfMilkPerDay(milkPerdayText);
            pdsDataCollection.setMilkForSale(milkForSaleText);
            pdsDataCollection.setMilkChallenges(challengesText);
            pdsDataCollection.setCowInAbuja(abujaCowText);
            pdsDataCollection.setTotalCow(totalCowText);
            pdsDataCollection.setMilkingCow(milkingCowText);
            pdsDataCollection.setFeedback(feedbackText);
            pdsDataCollection.setGender(genderText);
            pdsDataCollection.setFarmerId(farmerIdText);
            pdsOfflineViewModel.addNewPdsData(pdsDataCollection);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pdsOfflineViewModel.onDispose();
    }
}
