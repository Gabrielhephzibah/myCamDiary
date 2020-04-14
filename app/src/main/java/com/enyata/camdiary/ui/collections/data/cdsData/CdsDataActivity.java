package com.enyata.camdiary.ui.collections.data.cdsData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityCdsDataBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import javax.inject.Inject;

public class CdsDataActivity extends BaseActivity<ActivityCdsDataBinding,CdsDataViewModel>implements CdsNavigator {
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    Gson gson;
    CdsDataViewModel cdsDataViewModel;
    ActivityCdsDataBinding activityCdsDataBinding;
    LinearLayout bioData,locationInfo, incomeSource, farmInfo;
    EditText firstName,lastName,age,phoneNo,adult18Avove,communityName,sourcesIncome,mainIncome,weekEarn,monthEarn, milkPerDay,milkForSale,challenges,abujaCow,
    totalCow,milkingCow,feedback;
    Spinner gender, maritalStatus,electoralWard,areaCouncil;
    Button submitCds;
    String firstNameText, lastNameText, ageText,phoneNoText, adult18Text, communityNameText,sourcesIncomeText, mainIncomeText,weekEarnText, monthEarnText, milkPerDayText,milkForSaleText,challengesText,abujaCowText,totalCowText,
    milkingCowText,feedbackText ,selectedAreaCouncil,selectedWard,selectedGender, selectedMaritalStatus;
    String[] genderOption = {"","Male","Female"};
    String[] maritalStatusOption = {"","Yes","No"};
    String[] areaCouncilOption = {"","Abaji","Bwari","Gwagalada","Kuje", "Kwali","Abuja Municipal"};
    String[] abajiOption = {"","Abaji Central","Abaji North East","Abaji South East","Agyana/Pandagi", "Rimba Ebagi","Nuku","Alu Mamagi","Yaba","Gurdi","Gawu"};
    String[] bwariOption = {"","Bwari Central","Kuduru","Igu","Shere", "Kawu","Ushafa","Dutse Alhaji","Byazhin","Kubwa","Usuma"};
    String[] gwagaladaOption = {"","Gwagalada Centre","Kutunku","Staff Quarters","Ibwa", "Dobi","Paiko","Tungan Maje","Zuba","Ikwa","Gwako"};
    String[] kujeOption = {"","Kuje","Chibiri","Guabe","Kwaku", "Kabi","Rubochi","Gwargwada","Gudun Karya","Kujekwa","Yenche"};
    String[] kwaliOption = {"","Kwali Road","Yangoji","Pai","Kilankwa", "Dafa","Kundu","Ashara","Gumbo","Wako","Yebu"};
    String[] abujaMunicpalOption = {"","City Centre","Garki","Kabusa","Wuse", "Gwarinpa","Jiwa","Gui","Karshi","Orozo","Karu","Nyanya","Gwagwa"};



    ImageView biodataToggle,incomeToggle, locationToggle,farmInfoToggle;



    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cds_data;
    }

    @Override
    public CdsDataViewModel getViewModel() {
        cdsDataViewModel = ViewModelProviders.of(this,factory).get(CdsDataViewModel.class);
        return cdsDataViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cdsDataViewModel.setNavigator(this);
        activityCdsDataBinding = getViewDataBinding();
        bioData = activityCdsDataBinding.bioDataFragment;
        locationInfo = activityCdsDataBinding.locationFragment;
        incomeSource = activityCdsDataBinding.incomeFragment;
        farmInfo = activityCdsDataBinding.farmInfoFragment;
        biodataToggle = activityCdsDataBinding.bioDataToggle;
        farmInfoToggle = activityCdsDataBinding.farmInfoToggle;
        locationToggle = activityCdsDataBinding.locationToggle;
        incomeToggle = activityCdsDataBinding.incomeToggle;
        firstName = activityCdsDataBinding.firstName;
        lastName = activityCdsDataBinding.lastName;
        age = activityCdsDataBinding.age;
        phoneNo = activityCdsDataBinding.phoneNoEdit;
        adult18Avove = activityCdsDataBinding.adultYear;
        communityName = activityCdsDataBinding.communityName;
        sourcesIncome = activityCdsDataBinding.sourcesOfIncome;
        mainIncome = activityCdsDataBinding.mainIncome;
        weekEarn = activityCdsDataBinding.weekEarning;
        monthEarn = activityCdsDataBinding.monthlyEarning;
        milkPerDay = activityCdsDataBinding.milkPerDay;
        milkForSale = activityCdsDataBinding.milkForSale;
        challenges = activityCdsDataBinding.milkChallenges;
        abujaCow = activityCdsDataBinding.cowInAbuja;
        totalCow = activityCdsDataBinding.totalCow;
        milkingCow = activityCdsDataBinding.noOfMilkingCow;
        feedback = activityCdsDataBinding.feedback;
        gender = activityCdsDataBinding.genderSpinner;
        maritalStatus = activityCdsDataBinding.marriedSpinner;
        electoralWard = activityCdsDataBinding.electoralWardSpinner;
        areaCouncil = activityCdsDataBinding.areaCouncilSpinner;
        submitCds = activityCdsDataBinding.submitCds;




        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item,genderOption);
        gender.setAdapter(genderAdapter);

        ArrayAdapter<String>maritalStatusAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item, maritalStatusOption);
        maritalStatus.setAdapter(maritalStatusAdapter);

        ArrayAdapter<String>councilOptionAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item, areaCouncilOption);
        areaCouncil.setAdapter(councilOptionAdapter);

        areaCouncil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1: {
                        ArrayAdapter<String> abajiWardAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item, abajiOption);
                        electoralWard.setAdapter(abajiWardAdapter);
                        break;
                    }
                    case 2: {
                        ArrayAdapter<String> bwariWardAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item, bwariOption);
                        electoralWard.setAdapter(bwariWardAdapter);
                        break;
                    }
                    case 3: {
                        ArrayAdapter<String> gwagaladaWardAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item, gwagaladaOption);
                        electoralWard.setAdapter(gwagaladaWardAdapter);
                        break;
                    }
                    case 4: {
                        ArrayAdapter<String> kujeWardAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item, kujeOption);
                        electoralWard.setAdapter(kujeWardAdapter);
                        break;
                    }
                    case 5: {
                        ArrayAdapter<String> kwaliWardAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item, kwaliOption);
                        electoralWard.setAdapter(kwaliWardAdapter);
                        break;
                    }

                    case 6: {
                        ArrayAdapter<String> abujaWardAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item, abujaMunicpalOption);
                        electoralWard.setAdapter(abujaWardAdapter);
                        break;
                    }

                    default: {
                        ArrayAdapter<String> defaultWardAdapter = new ArrayAdapter<>(CdsDataActivity.this, android.R.layout.simple_spinner_item);
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


       submitCds.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               firstNameText = firstName.getText().toString();
               lastNameText = lastName.getText().toString();
               ageText = age.getText().toString();
               phoneNoText = phoneNo.getText().toString();
               adult18Text = adult18Avove.getText().toString();
               communityNameText = communityName.getText().toString();
               sourcesIncomeText = sourcesIncome.getText().toString();
               mainIncomeText = mainIncome.getText().toString();
               weekEarnText = weekEarn.getText().toString();
               monthEarnText = monthEarn.getText().toString();
               milkPerDayText = milkPerDay.getText().toString();
               milkForSaleText = milkForSale.getText().toString();
               challengesText = challenges.getText().toString();
               abujaCowText = abujaCow.getText().toString();
               totalCowText = totalCow.getText().toString();
               milkingCowText = milkingCow.getText().toString();
               feedbackText = feedback.getText().toString();
               selectedAreaCouncil = areaCouncil.getSelectedItem().toString();

               if (electoralWard!=null && electoralWard.getSelectedItem()!=null){
                   selectedWard = electoralWard.getSelectedItem().toString();
               }else {
                   Alert.showFailed(getApplicationContext(),"Area Council and electoral ward are required");
                   return;
               }
               selectedGender = ( String) gender.getSelectedItem();
               selectedMaritalStatus = (String) maritalStatus.getSelectedItem();
                if(InternetConnection.getInstance(CdsDataActivity.this).isOnline()) {
                   CdsDataRequest.Request request = new CdsDataRequest.Request(firstNameText,lastNameText,selectedGender,ageText,selectedMaritalStatus,phoneNoText,selectedWard,selectedAreaCouncil,communityNameText,sourcesIncomeText,mainIncomeText,weekEarnText,monthEarnText,adult18Text,milkPerDayText,milkForSaleText,challengesText,abujaCowText,totalCowText,milkingCowText,feedbackText);
                   cdsDataViewModel.submitCdsDataCollection(request);
               }else {
                   Alert.showFailed(getApplicationContext(),"Please check your internet connection and try again");
               }


           }
       });


//        selectedFinalAssess  = finalAssessSpinner.getSelectedItem().toString();





    }

    @Override
    public void onBioData() {
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
        switch (cdsDataViewModel.getCurrentUserType()){
            case "collectors": {
                Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
                startActivity(intent);
                break;
            }

            case "data_collectors": {
                Intent intent = new Intent(getApplicationContext(), DataCollectorDashboardActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onResponse(NewCollectionResponse response) {
        switch (cdsDataViewModel.getCurrentUserType()){
            case "collectors":{
                Alert.showSuccess(getApplicationContext(), response.getResponseMessage());
                Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
                startActivity(intent);
                break;
            }
            case  "data_collectors":{
                Alert.showSuccess(getApplicationContext(),response.getResponseMessage());
                Intent intent = new Intent(getApplicationContext(),DataCollectorDashboardActivity.class);
                startActivity(intent);
                break;
            }
        }
//        Alert.showSuccess(getApplicationContext(), response.getResponseMessage());


    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            }
        }else{
            Alert.showFailed(getApplicationContext(), " Unable to connect to the internet");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cdsDataViewModel.onDispose();
    }
}

