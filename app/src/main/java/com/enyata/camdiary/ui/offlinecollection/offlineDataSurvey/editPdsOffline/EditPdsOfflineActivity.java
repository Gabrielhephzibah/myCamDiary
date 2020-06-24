package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editPdsOffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Entity;

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
import com.enyata.camdiary.databinding.ActivityEditPdsOfflineBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editCdsOffline.EditCdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataActivity;
import com.enyata.camdiary.utils.Alert;

import java.util.List;

import javax.inject.Inject;

public class EditPdsOfflineActivity extends BaseActivity<ActivityEditPdsOfflineBinding, EditPdsOfflineViewModel> implements  EditPdsOfflineNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityEditPdsOfflineBinding activityEditPdsOfflineBinding;
    EditPdsOfflineViewModel editPdsOfflineViewModel;
    LinearLayout incomeFragment, farmInfoFragment;
    LinearLayout bioData;
    ImageView incomeToggle, farmInfoToggle, biodataToggle;
    String[] genderOption = {"", "Male", "Female"};
    String firstNameText, lastNameText, phoneNoText, genderText, farmerIdText, sourcesIncomeText, mainIncomeText, monthEarningText, weekEarningText, marketText, milkPerdayText, houseHoldConsumeText, milkForSaleText, challengesText, abujaCowText, totalCowText, milkingCowText, recommendText, feedbackText;
    EditText sourcesIncome, mainIncome, weekEarning, monthEarning, market, milkPerDay, houseHoldConsume, milkForsale, challenges, abujaCow, totalCow, milkingCow, recommend, feedback;
    EditText firstName, lastName, phoneNo, farmerId;
    Spinner gender;
    ArrayAdapter<String> genderAdapter;
    PdsDataCollection pdsDataCollection;



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_pds_offline;
    }

    @Override
    public EditPdsOfflineViewModel getViewModel() {
        editPdsOfflineViewModel = ViewModelProviders.of(this,factory).get(EditPdsOfflineViewModel.class);
        return editPdsOfflineViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editPdsOfflineViewModel.setNavigator(this);
        activityEditPdsOfflineBinding = getViewDataBinding();
        bioData = activityEditPdsOfflineBinding.bioDataFragment;
        incomeFragment = activityEditPdsOfflineBinding.incomeFragment;
        farmInfoFragment = activityEditPdsOfflineBinding.farmInfoFragment;
        incomeToggle = activityEditPdsOfflineBinding.incomeToggle;
        farmInfoToggle = activityEditPdsOfflineBinding.farmInfoToggle;
        biodataToggle = activityEditPdsOfflineBinding.bioDataToggle;
        sourcesIncome = activityEditPdsOfflineBinding.sourcesOfIncome;
        firstName = activityEditPdsOfflineBinding.firstName;
        lastName = activityEditPdsOfflineBinding.lastName;
        phoneNo = activityEditPdsOfflineBinding.phoneNoEdit;
        mainIncome = activityEditPdsOfflineBinding.mainIncome;
        weekEarning = activityEditPdsOfflineBinding.weekEarning;
        monthEarning = activityEditPdsOfflineBinding.monthlyEarning;
        market = activityEditPdsOfflineBinding.marketTime;
        milkPerDay = activityEditPdsOfflineBinding.milkPerDay;
        houseHoldConsume = activityEditPdsOfflineBinding.houseHoldConsume;
        milkForsale = activityEditPdsOfflineBinding.milkForSale;
        challenges = activityEditPdsOfflineBinding.milkChallenges;
        abujaCow = activityEditPdsOfflineBinding.abujaCow;
        totalCow = activityEditPdsOfflineBinding.totalCow;
        milkingCow = activityEditPdsOfflineBinding.milkingCow;
        recommend = activityEditPdsOfflineBinding.recommendation;
        feedback = activityEditPdsOfflineBinding.feedback;
        gender = activityEditPdsOfflineBinding.genderSpinner;
        farmerId = activityEditPdsOfflineBinding.farmerId;
        pdsDataCollection = (PdsDataCollection)getIntent().getSerializableExtra("pds_data");
        Log.i("DATA_PASSED", "Data that was passed"+pdsDataCollection);
        setAllEditText();


        genderAdapter = new ArrayAdapter<>(EditPdsOfflineActivity.this, android.R.layout.simple_spinner_item,genderOption);
        gender.setAdapter(genderAdapter);
        if (pdsDataCollection.getGender()!=null) {
            String genderString = pdsDataCollection.getGender();
            ArrayAdapter<String> myAdap = (ArrayAdapter<String>) gender.getAdapter();
            int spinnerPosition = myAdap.getPosition(genderString);
            Log.i("GENDER", String.valueOf(spinnerPosition));
            gender.setSelection(spinnerPosition);
        }




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
        sourcesIncomeText = sourcesIncome.getText().toString().trim();
        mainIncomeText = mainIncome.getText().toString().trim();
        weekEarningText = weekEarning.getText().toString().trim();
        monthEarningText = monthEarning.getText().toString().trim();
        marketText = market.getText().toString().trim();
        milkPerdayText = milkPerDay.getText().toString().trim();
        houseHoldConsumeText = houseHoldConsume.getText().toString().trim();
        milkForSaleText = milkForsale.getText().toString().trim();
        challengesText = challenges.getText().toString().trim();
        abujaCowText = abujaCow.getText().toString().trim();
        totalCowText = totalCow.getText().toString().trim();
        milkingCowText = milkingCow.getText().toString().trim();
        recommendText = recommend.getText().toString().trim();
        feedbackText = feedback.getText().toString().trim();
        farmerIdText = farmerId.getText().toString().trim();
        if (gender!=null && gender.getSelectedItem()!=null) {
            genderText = (String) gender.getSelectedItem();
        }

        Log.i("FIRSTName",firstNameText);
        Log.i("LASTName",lastNameText);

        Log.i("PhoneNumber",phoneNoText);

        Log.i("farmerId",farmerIdText);
        Log.i("sourcesIncome",sourcesIncomeText);
        Log.i("mainincome",mainIncomeText);
        Log.i("weekearn",weekEarningText);
        Log.i("monthearn",monthEarningText);
        Log.i("milkPerDay",milkPerdayText);
        Log.i("milkForsale",milkForSaleText);
        Log.i("challengesText",challengesText);
        Log.i("abujaCow",abujaCowText);
        Log.i("totalCow",totalCowText);
        Log.i("milkingCow",milkingCowText);
        Log.i("feedback",feedbackText);
        Log.i("gender",genderText);
        Log.i("consumption",houseHoldConsumeText);
        Log.i("reccommendation",recommendText);
        Log.i("market",marketText);
        isEditTextValueEmpty(firstNameText,lastNameText,phoneNoText,sourcesIncomeText,mainIncomeText,weekEarningText,monthEarningText,milkPerdayText,milkForSaleText,challengesText,abujaCowText,totalCowText,milkingCowText,feedbackText,genderText,recommendText,houseHoldConsumeText,marketText,farmerIdText);



    }

    @Override
    public void onBack() {
        Intent intent  = new Intent(getApplicationContext(), OfflineSavedDataActivity.class);
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
        Alert.showSuccess(getApplicationContext(),"Data Updated successfully");
        Intent intent  = new Intent(getApplicationContext(), OfflineSavedDataActivity.class);
        startActivity(intent);


    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable!= null){
            Alert.showFailed(getApplicationContext(), "Error updating data");
        }

    }

    public void setAllEditText(){
        if (pdsDataCollection!= null){
            firstName.setText(pdsDataCollection.getFirstName());
            lastName.setText(pdsDataCollection.getLastName());
            phoneNo.setText(pdsDataCollection.getPhone_no());
            mainIncome.setText(pdsDataCollection.getMainIncome());
            sourcesIncome.setText(pdsDataCollection.getSourcesOfIncome());
            weekEarning.setText(pdsDataCollection.getWeekEarning());
            monthEarning.setText(pdsDataCollection.getMonthlyEarning());
            market.setText(pdsDataCollection.getMarketDays());
            milkPerDay.setText(pdsDataCollection.getLitresOfMilkPerDay());
            challenges.setText(pdsDataCollection.getMilkChallenges());
            abujaCow.setText(pdsDataCollection.getCowInAbuja());
            totalCow.setText(pdsDataCollection.getTotalCow());
            milkingCow.setText(pdsDataCollection.getMilkingCow());
            recommend.setText(pdsDataCollection.getRecommendations());
            feedback.setText(pdsDataCollection.getFeedback());
            farmerId.setText(pdsDataCollection.getFarmerId());
            houseHoldConsume.setText(pdsDataCollection.getHouseHoldConsumption());
            milkForsale.setText(pdsDataCollection.getMilkForSale());


        }

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
            editPdsOfflineViewModel.updatePdsData(pdsDataCollection);

        }
    }
}
