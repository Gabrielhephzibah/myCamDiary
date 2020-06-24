package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editCdsOffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.databinding.ActivityEditCdsOfflineBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.cdsoffline.CdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataActivity;
import com.enyata.camdiary.utils.Alert;

import java.util.Arrays;

import javax.inject.Inject;

public class EditCdsOfflineActivity extends BaseActivity<ActivityEditCdsOfflineBinding,EditCdsOfflineViewModel>implements EditCdsOfflineNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityEditCdsOfflineBinding activityEditCdsOfflineBinding;
    EditCdsOfflineViewModel editCdsOfflineViewModel;
    LinearLayout bioData,locationInfo, incomeSource, farmInfo;
    ImageView biodataToggle,incomeToggle, locationToggle,farmInfoToggle;
    EditText firstName,lastName,age,phoneNo,adult18Avove,communityName,sourcesIncome,mainIncome,weekEarn,monthEarn, milkPerDay,milkForSale,challenges,abujaCow,
            totalCow,milkingCow,feedback;
    Spinner gender, maritalStatus,electoralWard,areaCouncil;
    Button submitCds;
    String firstNameText, lastNameText, ageText,phoneNoText, adult18Text, communityNameText,sourcesIncomeText, mainIncomeText,weekEarnText, monthEarnText, milkPerDayText,milkForSaleText,challengesText,abujaCowText,totalCowText,
            milkingCowText,feedbackText ,selectedAreaCouncil,selectedWard,selectedGender, selectedMaritalStatus;
    String[] genderOption = {"","Male","Female"};
    ArrayAdapter<String> genderAdapter;
    String[] maritalStatusOption = {"","Yes","No"};
    String[] areaCouncilOption = {"","Abaji","Bwari","Gwagalada","Kuje", "Kwali","Abuja Municipal"};
    String[] abajiOption = {"","Abaji Central","Abaji North East","Abaji South East","Agyana/Pandagi", "Rimba Ebagi","Nuku","Alu Mamagi","Yaba","Gurdi","Gawu"};
    String[] bwariOption = {"","Bwari Central","Kuduru","Igu","Shere", "Kawu","Ushafa","Dutse Alhaji","Byazhin","Kubwa","Usuma"};
    String[] gwagaladaOption = {"","Gwagalada Centre","Kutunku","Staff Quarters","Ibwa", "Dobi","Paiko","Tungan Maje","Zuba","Ikwa","Gwako"};
    String[] kujeOption = {"","Kuje","Chibiri","Guabe","Kwaku", "Kabi","Rubochi","Gwargwada","Gudun Karya","Kujekwa","Yenche"};
    String[] kwaliOption = {"","Kwali Road","Yangoji","Pai","Kilankwa", "Dafa","Kundu","Ashara","Gumbo","Wako","Yebu"};
    String[] abujaMunicpalOption = {"","City Centre","Garki","Kabusa","Wuse", "Gwarinpa","Jiwa","Gui","Karshi","Orozo","Karu","Nyanya","Gwagwa"};
    CdsDataCollection cdsDataCollection;




    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_cds_offline;
    }

    @Override
    public EditCdsOfflineViewModel getViewModel() {
        editCdsOfflineViewModel = ViewModelProviders.of(this,factory).get(EditCdsOfflineViewModel.class);
        return editCdsOfflineViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editCdsOfflineViewModel.setNavigator(this);
        activityEditCdsOfflineBinding = getViewDataBinding();
        bioData = activityEditCdsOfflineBinding.bioDataFragment;
        locationInfo = activityEditCdsOfflineBinding.locationFragment;
        incomeSource = activityEditCdsOfflineBinding.incomeFragment;
        farmInfo = activityEditCdsOfflineBinding.farmInfoFragment;
        biodataToggle = activityEditCdsOfflineBinding.bioDataToggle;
        farmInfoToggle = activityEditCdsOfflineBinding.farmInfoToggle;
        locationToggle = activityEditCdsOfflineBinding.locationToggle;
        incomeToggle = activityEditCdsOfflineBinding.incomeToggle;
        firstName = activityEditCdsOfflineBinding.firstName;
        lastName = activityEditCdsOfflineBinding.lastName;
        age = activityEditCdsOfflineBinding.age;
        phoneNo = activityEditCdsOfflineBinding.phoneNoEdit;
        adult18Avove = activityEditCdsOfflineBinding.adultYear;
        communityName = activityEditCdsOfflineBinding.communityName;
        sourcesIncome = activityEditCdsOfflineBinding.sourcesOfIncome;
        mainIncome = activityEditCdsOfflineBinding.mainIncome;
        weekEarn = activityEditCdsOfflineBinding.weekEarning;
        monthEarn = activityEditCdsOfflineBinding.monthlyEarning;
        milkPerDay = activityEditCdsOfflineBinding.milkPerDay;
        milkForSale = activityEditCdsOfflineBinding.milkForSale;
        challenges = activityEditCdsOfflineBinding.milkChallenges;
        abujaCow = activityEditCdsOfflineBinding.cowInAbuja;
        totalCow = activityEditCdsOfflineBinding.totalCow;
        milkingCow = activityEditCdsOfflineBinding.noOfMilkingCow;
        feedback = activityEditCdsOfflineBinding.feedback;
        gender = activityEditCdsOfflineBinding.genderSpinner;
        maritalStatus = activityEditCdsOfflineBinding.marriedSpinner;
        electoralWard = activityEditCdsOfflineBinding.electoralWardSpinner;
        areaCouncil = activityEditCdsOfflineBinding.areaCouncilSpinner;
        submitCds = activityEditCdsOfflineBinding.submitCds;

         cdsDataCollection = (CdsDataCollection)getIntent().getSerializableExtra("cds_data");
        if (cdsDataCollection!=null) {
            Log.i("CDS COllectionPassed", "WHAT WAS PASSED" + cdsDataCollection);
            firstName.setText(cdsDataCollection.getFirstName());
            lastName.setText(cdsDataCollection.getLastName());
            age.setText(cdsDataCollection.getAge());
            phoneNo.setText(cdsDataCollection.getPhone_no());
            adult18Avove.setText(cdsDataCollection.getAdult18Above());
            communityName.setText(cdsDataCollection.getCommunityName());
            sourcesIncome.setText(cdsDataCollection.getSourcesOfIncome());
            mainIncome.setText(cdsDataCollection.getIncome());
            weekEarn.setText(cdsDataCollection.getWeekEarning());
            monthEarn.setText(cdsDataCollection.getMonthlyEarning());
            milkPerDay.setText(cdsDataCollection.getLitresOfMilkPerDay());
            milkForSale.setText(cdsDataCollection.getMilkForSale());
            challenges.setText(cdsDataCollection.getMilkChallenges());
            abujaCow.setText(cdsDataCollection.getCowInAbuja());
            totalCow.setText(cdsDataCollection.getTotalCow());
            milkingCow.setText(cdsDataCollection.getMilkingCow());
            feedback.setText(cdsDataCollection.getFeedback());



        }else {
            Alert.showFailed(getApplicationContext()," unknown error ");
        }

        genderAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item,genderOption);
        gender.setAdapter(genderAdapter);
        if (cdsDataCollection.getGender()!=null) {
            String genderString = cdsDataCollection.getGender();
            ArrayAdapter<String> myAdap = (ArrayAdapter<String>) gender.getAdapter();
            int spinnerPosition = myAdap.getPosition(genderString);
            Log.i("GENDER", String.valueOf(spinnerPosition));
            gender.setSelection(spinnerPosition);
        }


        ArrayAdapter<String>maritalStatusAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item, maritalStatusOption);
        maritalStatus.setAdapter(maritalStatusAdapter);

        String maritalStatusString = cdsDataCollection.getMarital_status();
        ArrayAdapter<String > myAdap1 = (ArrayAdapter<String>)maritalStatus.getAdapter();
        int spinnerPosition2 = myAdap1.getPosition(maritalStatusString);
        Log.i("GENDER", String.valueOf(spinnerPosition2));
        maritalStatus.setSelection(spinnerPosition2);

        ArrayAdapter<String>councilOptionAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item, areaCouncilOption);
        areaCouncil.setAdapter(councilOptionAdapter);
        String areaCouncilString = cdsDataCollection.getAreaCouncil();
        ArrayAdapter<String > myAdap2 = (ArrayAdapter<String>) areaCouncil.getAdapter();
        int spinnerPosition3 = myAdap2.getPosition(areaCouncilString);
        Log.i("GENDER", String.valueOf(spinnerPosition3));
        areaCouncil.setSelection(spinnerPosition3);


        areaCouncil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1: {
                        ArrayAdapter<String> abajiWardAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item, abajiOption);
                        electoralWard.setAdapter(abajiWardAdapter);

                        String electoralWardString = cdsDataCollection.getElectoralWard();
                        ArrayAdapter<String > myAdap3 = (ArrayAdapter<String>) electoralWard.getAdapter();
                        int spinnerPosition3 = myAdap3.getPosition(electoralWardString);
                        Log.i("GENDER", String.valueOf(spinnerPosition3));
                        electoralWard.setSelection(spinnerPosition3);
                        break;
                    }
                    case 2: {
                        ArrayAdapter<String> bwariWardAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item, bwariOption);
                        electoralWard.setAdapter(bwariWardAdapter);
                        String electoralWardString = cdsDataCollection.getElectoralWard();
                        ArrayAdapter<String > myAdap3 = (ArrayAdapter<String>) electoralWard.getAdapter();
                        int spinnerPosition3 = myAdap3.getPosition(electoralWardString);
                        Log.i("GENDER", String.valueOf(spinnerPosition3));
                        electoralWard.setSelection(spinnerPosition3);
                        break;
                    }
                    case 3: {
                        ArrayAdapter<String> gwagaladaWardAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item, gwagaladaOption);
                        electoralWard.setAdapter(gwagaladaWardAdapter);
                        String electoralWardString = cdsDataCollection.getElectoralWard();
                        ArrayAdapter<String > myAdap3 = (ArrayAdapter<String>) electoralWard.getAdapter();
                        int spinnerPosition3 = myAdap3.getPosition(electoralWardString);
                        Log.i("GENDER", String.valueOf(spinnerPosition3));
                        electoralWard.setSelection(spinnerPosition3);
                        break;
                    }
                    case 4: {
                        ArrayAdapter<String> kujeWardAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item, kujeOption);
                        electoralWard.setAdapter(kujeWardAdapter);
                        String electoralWardString = cdsDataCollection.getElectoralWard();
                        ArrayAdapter<String > myAdap3 = (ArrayAdapter<String>) electoralWard.getAdapter();
                        int spinnerPosition3 = myAdap3.getPosition(electoralWardString);
                        Log.i("GENDER", String.valueOf(spinnerPosition3));
                        electoralWard.setSelection(spinnerPosition3);
                        break;
                    }
                    case 5: {
                        ArrayAdapter<String> kwaliWardAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item, kwaliOption);
                        electoralWard.setAdapter(kwaliWardAdapter);
                        String electoralWardString = cdsDataCollection.getElectoralWard();
                        ArrayAdapter<String > myAdap3 = (ArrayAdapter<String>) electoralWard.getAdapter();
                        int spinnerPosition3 = myAdap3.getPosition(electoralWardString);
                        Log.i("GENDER", String.valueOf(spinnerPosition3));
                        electoralWard.setSelection(spinnerPosition3);

                        break;
                    }

                    case 6: {
                        ArrayAdapter<String> abujaWardAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item, abujaMunicpalOption);
                        electoralWard.setAdapter(abujaWardAdapter);
                        String electoralWardString = cdsDataCollection.getElectoralWard();
                        ArrayAdapter<String > myAdap3 = (ArrayAdapter<String>) electoralWard.getAdapter();
                        int spinnerPosition3 = myAdap3.getPosition(electoralWardString);
                        Log.i("GENDER", String.valueOf(spinnerPosition3));
                        electoralWard.setSelection(spinnerPosition3);
                        break;
                    }

                    default: {
                        ArrayAdapter<String> defaultWardAdapter = new ArrayAdapter<>(EditCdsOfflineActivity.this, android.R.layout.simple_spinner_item);
                        electoralWard.setAdapter(defaultWardAdapter);
                        break;
                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Alert.showFailed(getApplicationContext(), "Area council and Ward are required");

            }
        });


    }

    @Override
    public void onBioData() {
        hideKeyboard();
        if (bioData.getVisibility()== View.GONE){
            bioData.setVisibility(View.VISIBLE);
            biodataToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            bioData.setVisibility(View.GONE);
            biodataToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onLocationInfo() {
        hideKeyboard();
        if (locationInfo.getVisibility()==View.GONE){
            locationInfo.setVisibility(View.VISIBLE);
            locationToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            locationInfo.setVisibility(View.GONE);
            locationToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onIncomeSources() {
        hideKeyboard();
        if (incomeSource.getVisibility()==View.GONE){
            incomeSource.setVisibility(View.VISIBLE);
            incomeToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            incomeSource.setVisibility(View.GONE);
            incomeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onFarmInfo() {
        if (farmInfo.getVisibility()==View.GONE){
            farmInfo.setVisibility(View.VISIBLE);
            farmInfoToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            farmInfo.setVisibility(View.GONE);
            farmInfoToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }


    }

    @Override
    public void onBack() {
        Intent intent  = new Intent(getApplicationContext(), OfflineSavedDataActivity.class);
        startActivity(intent);

    }

    @Override
    public void onSubmitCDS() {
        firstNameText = firstName.getText().toString().trim();
        lastNameText = lastName.getText().toString().trim();
        ageText = age.getText().toString().trim();
        phoneNoText = phoneNo.getText().toString().trim();
        adult18Text = adult18Avove.getText().toString().trim();
        communityNameText = communityName.getText().toString().trim();
        sourcesIncomeText = sourcesIncome.getText().toString().trim();
        mainIncomeText = mainIncome.getText().toString().trim();
        weekEarnText = weekEarn.getText().toString().trim();
        monthEarnText = monthEarn.getText().toString().trim();
        milkPerDayText = milkPerDay.getText().toString().trim();
        milkForSaleText = milkForSale.getText().toString().trim();
        challengesText = challenges.getText().toString().trim();
        abujaCowText = abujaCow.getText().toString().trim();
        totalCowText = totalCow.getText().toString().trim();
        milkingCowText = milkingCow.getText().toString().trim();
        feedbackText = feedback.getText().toString();

        if (electoralWard!=null && electoralWard.getSelectedItem()!=null){
            selectedWard = electoralWard.getSelectedItem().toString();
        }else {
            selectedWard = cdsDataCollection.getElectoralWard();
        }
        if (areaCouncil!=null && areaCouncil.getSelectedItem()!=null) {
            selectedAreaCouncil = areaCouncil.getSelectedItem().toString();
        }

        if (gender!=null && gender.getSelectedItem()!=null) {
            selectedGender = (String) gender.getSelectedItem();
        }if (maritalStatus!=null && maritalStatus.getSelectedItem()!=null) {

            selectedMaritalStatus = (String) maritalStatus.getSelectedItem();
        }
        Log.i("FIRSTName",firstNameText);
        Log.i("LASTName",lastNameText);
        Log.i("Age",ageText);
        Log.i("PhoneNumber",phoneNoText);
        Log.i("adult18",adult18Text);
        Log.i("CummunityText",communityNameText);
        Log.i("sourcesIncome",sourcesIncomeText);
        Log.i("mainincome",mainIncomeText);
        Log.i("weekearn",weekEarnText);
        Log.i("monthearn",monthEarnText);
        Log.i("milkPerDay",milkPerDayText);
        Log.i("milkForsale",milkForSaleText);
        Log.i("challengesText",challengesText);
        Log.i("abujaCow",abujaCowText);
        Log.i("totalCow",totalCowText);
        Log.i("milkingCow",milkingCowText);
        Log.i("feedback",feedbackText);
        Log.i("AreaCouncil",selectedAreaCouncil);
        Log.i("electoralward",selectedWard);
        Log.i("gender",selectedGender);
        Log.i("maritalStatus",selectedMaritalStatus);

        isEditTextValueEmpty(firstNameText,lastNameText,ageText,phoneNoText,adult18Text,communityNameText,sourcesIncomeText,mainIncomeText,weekEarnText,monthEarnText,milkPerDayText,milkForSaleText,challengesText,abujaCowText,totalCowText,milkingCowText,feedbackText,selectedAreaCouncil,selectedGender,selectedMaritalStatus,selectedWard);



    }

    @Override
    public void onResponse() {
        Log.i("UPDATE","UPDATE SUCCESSFUL");
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



    public void isEditTextValueEmpty(String first_name, String last_name, String age, String phoneNo, String adult18, String communityName,String sourcesOfIncome, String mainIncome, String weekEarn, String monthEarn, String milkPerDay, String milkForSale, String challenges,String abujaCow, String totalCow, String milkingCow,String feedback, String areaCouncil,String gender, String maritalStatus, String ward ){
        if (first_name.isEmpty()){
            Alert.showFailed(getApplicationContext(),"first name is required");
        }else if (last_name.isEmpty()){
            Alert.showFailed(getApplicationContext(),"last name is required");
        }else if (age.isEmpty()){
            Alert.showFailed(getApplicationContext(),"age is required");
        }else if (phoneNo.isEmpty()){
            Alert.showFailed(getApplicationContext(),"phone number is required");
        }else if (adult18.isEmpty()){
            Alert.showFailed(getApplicationContext(),"adult_above_18 is required");
        }else if (communityName.isEmpty()){
            Alert.showFailed(getApplicationContext(),"community name is required");
        }else if (sourcesOfIncome.isEmpty()){
            Alert.showFailed(getApplicationContext(),"sources of income is required");
        }else if (mainIncome.isEmpty()){
            Alert.showFailed(getApplicationContext(),"main income is required");
        }else if (weekEarn.isEmpty()){
            Alert.showFailed(getApplicationContext(),"weekly earning is required");
        }else if (monthEarn.isEmpty()){
            Alert.showFailed(getApplicationContext(),"monthly earning is required");
        }else if (milkPerDay.isEmpty()){
            Alert.showFailed(getApplicationContext(),"volume of milk per day is required");
        }else if (milkForSale.isEmpty()){
            Alert.showFailed(getApplicationContext(),"volume of milk for sale is required");
        }else if (challenges.isEmpty()){
            Alert.showFailed(getApplicationContext(),"challenges is required");
        }else if (abujaCow.isEmpty()){
            Alert.showFailed(getApplicationContext(),"numbers of cow in Abuja is required");
        }else if (totalCow.isEmpty()){
            Alert.showFailed(getApplicationContext(),"total numbers of cow is required");
        }else if (milkingCow.isEmpty()){
            Alert.showFailed(getApplicationContext(),"numbers of milking cow is required");
        }else if (feedback.isEmpty()){
            Alert.showFailed(getApplicationContext(),"feedback is required");
        }else if (areaCouncil.isEmpty()){
            Alert.showFailed(getApplicationContext(),"area council is required");
        }else if (gender.isEmpty()){
            Alert.showFailed(getApplicationContext(),"gender is required");
        }else if (maritalStatus.isEmpty()){
            Alert.showFailed(getApplicationContext(),"marital status is required");
        }else if (ward.isEmpty()){
            Alert.showFailed(getApplicationContext()," electoral ward is required");
        }else {
//            CdsDataCollection cdsDataCollection = new CdsDataCollection(firstNameText,lastNameText,selectedGender,ageText,selectedMaritalStatus,phoneNoText,adult18Text,selectedWard,selectedAreaCouncil,communityNameText,mainIncomeText,sourcesIncomeText,weekEarnText,monthEarnText,milkPerDayText,milkForSaleText,challengesText,abujaCowText,totalCowText,milkingCowText,feedbackText);
            cdsDataCollection.setFirstName(firstNameText);
            cdsDataCollection.setLastName(lastNameText);
            cdsDataCollection.setAge(ageText);
            cdsDataCollection.setPhone_no(phoneNoText);
            cdsDataCollection.setAdult18Above(adult18Text);
            cdsDataCollection.setCommunityName(communityNameText);
            cdsDataCollection.setSourcesOfIncome(sourcesIncomeText);
            cdsDataCollection.setIncome(mainIncomeText);
            cdsDataCollection.setWeekEarning(weekEarnText);
            cdsDataCollection.setMonthlyEarning(monthEarnText);
            cdsDataCollection.setLitresOfMilkPerDay(milkPerDayText);
            cdsDataCollection.setMilkForSale(milkForSaleText);
            cdsDataCollection.setMilkChallenges(challengesText);
            cdsDataCollection.setCowInAbuja(abujaCowText);
            cdsDataCollection.setTotalCow(totalCowText);
            cdsDataCollection.setMilkingCow(milkingCowText);
            cdsDataCollection.setFeedback(feedbackText);
            cdsDataCollection.setAreaCouncil(selectedAreaCouncil);
            cdsDataCollection.setGender(selectedGender);
            cdsDataCollection.setMarital_status(selectedMaritalStatus);
            cdsDataCollection.setElectoralWard(selectedWard);
            editCdsOfflineViewModel.updateCdsData(cdsDataCollection);


        }
    }
}