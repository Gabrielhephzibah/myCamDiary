package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.bdsoffline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.databinding.ActivityBdsOfflineBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;
import com.enyata.camdiary.utils.Alert;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class BdsOfflineActivity extends BaseActivity<ActivityBdsOfflineBinding, BdsOfflineViewModel>implements BdsOfflineNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityBdsOfflineBinding activityBdsOfflineBinding;
    BdsOfflineViewModel bdsOfflineViewModel;
    LinearLayout bioDataFragment, locationInfo, incomeSourceFragment, coperativeInfo, farmInfo;
    ImageView bioDataToggle, locationToggle, incomeToggle, coperativeToggle, farmInfoToggle;
    EditText firstName, lastName, age,familyName,phoneNo,childernUnder18,below16, below16InSch,adult18Above,communityName,soucesIncome, mainIncome, weekEarning, monthEarning,markeyDay,milkPerDay, milkConsume, milkForSale,challenges, cowInAbuja,totalCow,milkingCow,
            animalFeedQuantity,recommendation,feedback,coperativeName;
    ImageView farmerImage;
    int currentapiVersion;
    boolean spinnerSelected;
    ProgressDialog dialog;
    Uri uri;
    List<String> electoralWards;
    String cloudinaryImage, imageURL, cloudinaryID;
    private static final int REQUEST_PERMISSION = 1;
    private static int PICK_FROM_GALLERY = 1;
    Spinner gender, maritalStatus,electoralWard,areaCouncil, animalFeedInterest;
    String firstNameText, lastNameText, ageText, familyNameText,phoneNoText,childrenUnder18Text, below16Text, below16InSchText, adult18AboveText, communityNameText,sourcesIncomeText,mainIncomeText,weekEarningText, monthlyEarningText, marketDayText,milkPerDayText,milkConsumeText,milkForSaleText,challengesText,cowInAbujaText, totalCowText,milkingCowText,
            animalFeedQuatityText, recommendationText, feedbackText, genderSelected, maritalStatusSelected,electoralWardSelected,areaCouncilWardSelected,copereativeNameText, animalFeedInterestSelected,farmerImageBitmap;
    String [] cooperativeNameOption = {"","Paiko","Zuba","falaku","lumo"};
    List<String>getCoperativeName;
    String [] animalFieldInterestOption = {"","Yes","No"};
    Bitmap bitmap;
    String[] genderOption = {"","Male","Female"};
    String[] maritalStatusOption = {"","Yes","No"};
    String[] areaCouncilOption = {"","Abaji","Bwari","Gwagalada","Kuje", "Kwali","Abuja Municipal"};
    String[] abajiOption = {"","Abaji Central","Abaji North East","Abaji South East","Agyana/Pandagi", "Rimba Ebagi","Nuku","Alu Mamagi","Yaba","Gurdi","Gawu"};
    String[] bwariOption = {"","Bwari Central","Kuduru","Igu","Shere", "Kawu","Ushafa","Dutse Alhaji","Byazhin","Kubwa","Usuma"};
    String[] gwagaladaOption = {"","Gwagalada Centre","Kutunku","Staff Quarters","Ibwa", "Dobi","Paiko","Tungan Maje","Zuba","Ikwa","Gwako"};
    String[] kujeOption = {"","Kuje","Chibiri","Guabe","Kwaku", "Kabi","Rubochi","Gwargwada","Gudun Karya","Kujekwa","Yenche"};
    String[] kwaliOption = {"","Kwali Road","Yangoji","Pai","Kilankwa", "Dafa","Kundu","Ashara","Gumbo","Wako","Yebu"};
    String[] abujaMunicpalOption = {"","City Centre","Garki","Kabusa","Wuse", "Gwarinpa","Jiwa","Gui","Karshi","Orozo","Karu","Nyanya","Gwagwa"};




    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bds_offline;
    }

    @Override
    public BdsOfflineViewModel getViewModel() {
        bdsOfflineViewModel = ViewModelProviders.of(this,factory).get(BdsOfflineViewModel.class);
        return bdsOfflineViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bdsOfflineViewModel.setNavigator(this);
        activityBdsOfflineBinding = getViewDataBinding();

        currentapiVersion = Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission() && checkExternalPermission()) {
            } else {
                requestPermission();
            }
        }

        bioDataFragment = activityBdsOfflineBinding.bioDataFragment;
        incomeSourceFragment = activityBdsOfflineBinding.incomeFragment;
        locationInfo = activityBdsOfflineBinding.locationFragment;
        coperativeInfo = activityBdsOfflineBinding.coperativeFragment;
        farmInfo = activityBdsOfflineBinding.farmInfoFragment;
        bioDataToggle = activityBdsOfflineBinding.bioDataToggle;
        incomeToggle = activityBdsOfflineBinding.incomeToggle;
        locationToggle = activityBdsOfflineBinding.locationToggle;
        coperativeToggle = activityBdsOfflineBinding.coprerativeToggle;
        farmInfoToggle = activityBdsOfflineBinding.farmInfoToggle;

        firstName = activityBdsOfflineBinding.firstName;
        lastName = activityBdsOfflineBinding.lastName;
        age = activityBdsOfflineBinding.age;
        familyName = activityBdsOfflineBinding.familyName;
        phoneNo = activityBdsOfflineBinding.phoneNumber;
        childernUnder18 = activityBdsOfflineBinding.below18;
        below16 = activityBdsOfflineBinding.below16;
        below16InSch = activityBdsOfflineBinding.below16InSch;
        adult18Above = activityBdsOfflineBinding.adult18Above;
        communityName = activityBdsOfflineBinding.communityName;
        soucesIncome = activityBdsOfflineBinding.sourcesOfIncome;
        mainIncome = activityBdsOfflineBinding.mainIncome;
        weekEarning = activityBdsOfflineBinding.weekEarning;
        monthEarning = activityBdsOfflineBinding.monthlyEarning;
        markeyDay = activityBdsOfflineBinding.marketTime;
        milkPerDay = activityBdsOfflineBinding.milkPerDay;
        milkConsume  = activityBdsOfflineBinding.houseHoldConsume;
        milkForSale = activityBdsOfflineBinding.milkForSale;
        challenges = activityBdsOfflineBinding.milkChallenges;
        cowInAbuja = activityBdsOfflineBinding.cowInAbuja;
        totalCow = activityBdsOfflineBinding.totalCow;
        milkingCow = activityBdsOfflineBinding.milkingCow;
        animalFeedQuantity = activityBdsOfflineBinding.animalFieldQuantity;
        recommendation = activityBdsOfflineBinding.recommendation;
        feedback = activityBdsOfflineBinding.feedback;
        farmerImage = activityBdsOfflineBinding.farmerImage;
        gender = activityBdsOfflineBinding.genderSpinner;
        maritalStatus = activityBdsOfflineBinding.maritalSpinner;
        electoralWard = activityBdsOfflineBinding.electoralWardSpinner;
        areaCouncil = activityBdsOfflineBinding.areaCouncilSpinner;
        coperativeName = activityBdsOfflineBinding.cooperativeName;
        animalFeedInterest = activityBdsOfflineBinding.animalFeedInterestSpinner;
//        bdsOfflineViewModel.getBdsData();

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item,genderOption);
        gender.setAdapter(genderAdapter);

        ArrayAdapter<String> animalFeedInterestAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item,animalFieldInterestOption);
        animalFeedInterest.setAdapter(animalFeedInterestAdapter);

        ArrayAdapter<String>maritalStatusAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item, maritalStatusOption);
        maritalStatus.setAdapter(maritalStatusAdapter);

        ArrayAdapter<String>councilOptionAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item, areaCouncilOption);
        areaCouncil.setAdapter(councilOptionAdapter);

        areaCouncil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1: {
                        ArrayAdapter<String> abajiWardAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item, abajiOption);
                        electoralWard.setAdapter(abajiWardAdapter);
                        break;
                    }
                    case 2: {
                        ArrayAdapter<String> bwariWardAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item, bwariOption);
                        electoralWard.setAdapter(bwariWardAdapter);
                        break;
                    }
                    case 3: {
                        ArrayAdapter<String> gwagaladaWardAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item, gwagaladaOption);
                        electoralWard.setAdapter(gwagaladaWardAdapter);
                        break;
                    }
                    case 4: {
                        ArrayAdapter<String> kujeWardAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item, kujeOption);
                        electoralWard.setAdapter(kujeWardAdapter);
                        break;
                    }
                    case 5: {
                        ArrayAdapter<String> kwaliWardAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item, kwaliOption);
                        electoralWard.setAdapter(kwaliWardAdapter);
                        break;
                    }

                    case 6: {
                        ArrayAdapter<String> abujaWardAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item, abujaMunicpalOption);
                        electoralWard.setAdapter(abujaWardAdapter);
                        break;
                    }

                    default: {
                        ArrayAdapter<String> defaultWardAdapter = new ArrayAdapter<>(BdsOfflineActivity.this, android.R.layout.simple_spinner_item);
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
        if (bioDataFragment.getVisibility()== View.GONE){
            bioDataFragment.setVisibility(View.VISIBLE);
            bioDataToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            bioDataFragment.setVisibility(View.GONE);
            bioDataToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onLocationInfo() {
        hideKeyboard();
        if (locationInfo.getVisibility()== View.GONE){
            locationInfo.setVisibility(View.VISIBLE);
            locationToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            locationInfo.setVisibility(View.GONE);
            locationToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onIncomeSource() {
        hideKeyboard();
        if (incomeSourceFragment.getVisibility()== View.GONE){
            incomeSourceFragment.setVisibility(View.VISIBLE);
            incomeToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            incomeSourceFragment.setVisibility(View.GONE);
            incomeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onCoperativeInfo() {
        hideKeyboard();
        if (coperativeInfo.getVisibility()== View.GONE){
            coperativeInfo.setVisibility(View.VISIBLE);
            coperativeToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            coperativeInfo.setVisibility(View.GONE);
            coperativeToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }

    }

    @Override
    public void onFarmInfo() {
        hideKeyboard();
        if (farmInfo.getVisibility()== View.GONE){
            farmInfo.setVisibility(View.VISIBLE);
            farmInfoToggle.setImageResource(R.drawable.ic_icon_open);
        }else {
            farmInfo.setVisibility(View.GONE);
            farmInfoToggle.setImageResource(R.drawable.ic_keyboard_arrow_right_white);
        }


    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void onUploadPicture() {
        try{
            Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
        }catch(Exception exp){
            Log.i("Error",exp.toString());
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
        }


    }

    @Override
    public void onSubmitBds() {
        firstNameText = firstName.getText().toString().trim();
        lastNameText = lastName.getText().toString().trim();
        ageText = age.getText().toString().trim();
        familyNameText = familyName.getText().toString().trim();
        phoneNoText = phoneNo.getText().toString().trim();
        childrenUnder18Text = childernUnder18.getText().toString().trim();
        below16Text = below16.getText().toString().trim();
        adult18AboveText = adult18Above.getText().toString().trim();
        below16InSchText = below16InSch.getText().toString().trim();
        communityNameText = communityName.getText().toString().trim();
        sourcesIncomeText = soucesIncome.getText().toString().trim();
        mainIncomeText = mainIncome.getText().toString().trim();
        weekEarningText = weekEarning.getText().toString().trim();
        monthlyEarningText = monthEarning.getText().toString().trim();
        marketDayText = markeyDay.getText().toString().trim();
        milkPerDayText = milkPerDay.getText().toString().trim();
        milkConsumeText = milkConsume.getText().toString().trim();
        milkForSaleText = milkForSale.getText().toString().trim();
        challengesText = challenges.getText().toString().trim();
        cowInAbujaText = cowInAbuja.getText().toString().trim();
        totalCowText = totalCow.getText().toString().trim();
        milkingCowText = milkingCow.getText().toString().trim();
        animalFeedQuatityText = animalFeedQuantity.getText().toString().trim();
        recommendationText = recommendation.getText().toString().trim();
        feedbackText = feedback.getText().toString().trim();
        copereativeNameText = coperativeName.getText().toString().trim();

        if (uri!=null) {
            farmerImageBitmap = uri.toString();
            Log.i("Image uri", farmerImageBitmap);
        }else {
            Alert.showFailed(getApplicationContext(),"image is required");
        }
        if (farmerImageBitmap == null){
            Alert.showFailed(getApplicationContext(),"farmer image is required");
            return;
        }

        if (electoralWard!=null && electoralWard.getSelectedItem()!=null){
            electoralWardSelected = electoralWard.getSelectedItem().toString();
        }
        if (areaCouncil!=null && areaCouncil.getSelectedItem()!=null) {
            areaCouncilWardSelected = areaCouncil.getSelectedItem().toString();
        }
        if (gender!=null && gender.getSelectedItem()!=null) {
            genderSelected = (String) gender.getSelectedItem();
        }if (maritalStatus!=null && maritalStatus.getSelectedItem()!=null) {
            maritalStatusSelected = (String) maritalStatus.getSelectedItem();
        }if (animalFeedInterest!=null && animalFeedInterest.getSelectedItem()!=null) {
            animalFeedInterestSelected = (String) animalFeedInterest.getSelectedItem();
        }
        isEditTextValueEmpty(firstNameText,lastNameText,ageText,phoneNoText,adult18AboveText,communityNameText,sourcesIncomeText,mainIncomeText,weekEarningText,monthlyEarningText,milkPerDayText,milkForSaleText,challengesText,cowInAbujaText,totalCowText,milkingCowText,feedbackText,areaCouncilWardSelected,genderSelected,maritalStatusSelected,electoralWardSelected,marketDayText,milkConsumeText,recommendationText,below16Text,below16InSchText,childrenUnder18Text,animalFeedInterestSelected,farmerImageBitmap,familyNameText,copereativeNameText);

        Log.i("FIRSTName",firstNameText);
        Log.i("LASTName",lastNameText);
        Log.i("Age",ageText);
        Log.i("familyname",familyNameText);
        Log.i("PhoneNumber",phoneNoText);
        Log.i("childrenUnder18",childrenUnder18Text);
        Log.i("below16",below16Text);
        Log.i("adult18",adult18AboveText);
        Log.i("below16insch",below16InSchText);
        Log.i("CummunityText",communityNameText);
        Log.i("sourcesIncome",sourcesIncomeText);
        Log.i("mainincome",mainIncomeText);
        Log.i("weekearn",weekEarningText);
        Log.i("monthearn",monthlyEarningText);
        Log.i("marketday",marketDayText);
        Log.i("milkPerDay",milkPerDayText);
        Log.i("milkConsume",milkConsumeText);
        Log.i("milkForsale",milkForSaleText);
        Log.i("challengesText",challengesText);
        Log.i("abujaCow",cowInAbujaText);
        Log.i("totalCow",totalCowText);
        Log.i("milkingCow",milkingCowText);
        Log.i("animalfeedquantity",animalFeedQuatityText);
        Log.i("recommendation",recommendationText);
        Log.i("cooperativename",copereativeNameText);
        Log.i("feedback",feedbackText);

//        Log.i("ward",electoralWardSelected);
//        Log.i("areacouncil",areaCouncilWardSelected);
//        Log.i("gender",genderSelected);
//        Log.i("maritalstatus",maritalStatusSelected);
//        Log.i("animalfeedselected",animalFeedInterestSelected);
//        Log.i("image",farmerImageBitmap);







    }

    @Override
    public void onResponse() {
        Log.i("SUCCESSFUL", "SUCCESSFULL");
        Alert.showSuccess(getApplicationContext(),"Data Saved Successfully");
        Intent intent  = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleError(Throwable throwable) {
        Log.i("ERROR","ERROR");
        if (throwable!=null){
            Alert.showFailed(getApplicationContext(),"Data not successfully submitted");

        }

    }
//
//    @Override
//    public void onGetResponse(List<BdsDataCollections> bdsDataCollection) {
//        Log.i("Success","Data" + bdsDataCollection);
//    }

    private boolean checkExternalPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(BdsOfflineActivity.this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    boolean readExternalAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (readExternalAccepted && writeExternalStorage) {
                        Alert.showSuccess(getApplicationContext(), "Permission Granted, Now you can access the gallery");
                    } else {
                        Alert.showSuccess(getApplicationContext(), "Permission Denied, You cannot access gallery");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA) && shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE) && shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                                showMessageOKCancel("You need to allow access to both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                                            REQUEST_PERMISSION);
                                                }
                                            }
                                        });
                                return;
                            }
                        }


                    }

                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getApplicationContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK){
            uri = data.getData();

            try {
                 bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);

                farmerImage.setImageBitmap(bitmap);
                Log.i("uri", String.valueOf(uri));
            } catch (IOException e) {
                e.printStackTrace();
                Alert.showFailed(getApplicationContext(),"An unknown error occurred");
            }
        }
    }


    public void isEditTextValueEmpty(String first_name, String last_name, String age, String phoneNo, String adult18, String communityName,String sourcesOfIncome, String mainIncome, String weekEarn, String monthEarn, String milkPerDay, String milkForSale, String challenges,String abujaCow, String totalCow, String milkingCow,String feedback, String areaCouncil,String gender, String maritalStatus, String ward, String marketday, String householdConsp, String recommendation, String below16, String below16InSch, String childrenUnder18, String animalFeedInterest, String imageBitmap, String familname, String cooperativeName){
        if (first_name.isEmpty()){
            Alert.showFailed(getApplicationContext(),"first name is required");
        }else if (first_name.length() < 3) {
            Alert.showFailed(getApplicationContext(), "first name must be greater than three");
        } else if (last_name.isEmpty()){
            Alert.showFailed(getApplicationContext(),"last name is required");
        }else if (last_name.length() < 3) {
            Alert.showFailed(getApplicationContext(), "last name must be grater than three");
        }
        else if (age.isEmpty()){
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
        }
        else if (marketday.isEmpty()){
            Alert.showFailed(getApplicationContext()," market visit  is required");
        }else if (householdConsp.isEmpty()){
            Alert.showFailed(getApplicationContext()," household consumption ward is required");
        }else if (recommendation.isEmpty()){
            Alert.showFailed(getApplicationContext()," recommendation ward is required");
        }else if (below16.isEmpty()){
            Alert.showFailed(getApplicationContext()," children below 16 is required");
        }else if (below16InSch.isEmpty()){
            Alert.showFailed(getApplicationContext()," children below 16 in school is required");
        }else if (childrenUnder18.isEmpty()){
            Alert.showFailed(getApplicationContext()," children under 18 is required");
        }else if (animalFeedInterest.isEmpty()){
            Alert.showFailed(getApplicationContext()," animal feed interest is required");
        }else if (imageBitmap == null){
            Alert.showFailed(getApplicationContext()," image  is required");
        }else if (familname.isEmpty()){
            Alert.showFailed(getApplicationContext()," family is required");
        } else if (cooperativeName.isEmpty()){
            Alert.showFailed(getApplicationContext()," cooperative name is required");
        }else {
            BdsDataCollections bdsDataCollection = new BdsDataCollections();
            bdsDataCollection.setFirstName(firstNameText);
            bdsDataCollection.setLastName(lastNameText);
            bdsDataCollection.setAge(ageText);
            bdsDataCollection.setPhoneNo(phoneNoText);
            bdsDataCollection.setAdult18Above(adult18AboveText);
            bdsDataCollection.setCommunityName(communityNameText);
            bdsDataCollection.setSourcesOfIncome(sourcesIncomeText);
            bdsDataCollection.setMainIncome(mainIncomeText);
            bdsDataCollection.setWeekEarning(weekEarningText);
            bdsDataCollection.setMonthlyEarning(monthlyEarningText);
            bdsDataCollection.setLitresOfMilkPerDay(milkPerDayText);
            bdsDataCollection.setMilkForSale(milkForSaleText);
            bdsDataCollection.setMilkChallenges(challengesText);
            bdsDataCollection.setCowInAbuja(cowInAbujaText);
            bdsDataCollection.setTotalCow(totalCowText);
            bdsDataCollection.setMilkingCow(milkingCowText);
            bdsDataCollection.setFeedback(feedbackText);
            bdsDataCollection.setAreaCouncil(areaCouncilWardSelected);
            bdsDataCollection.setGender(genderSelected);
            bdsDataCollection.setMarital_status(maritalStatusSelected);
            bdsDataCollection.setElectoralWard(electoralWardSelected);
            bdsDataCollection.setMarketDays(marketDayText);
            bdsDataCollection.setHouseHoldConsumption(milkConsumeText);
            bdsDataCollection.setRecommendations(recommendationText);
            bdsDataCollection.setChildrenBelow16(below16Text);
            bdsDataCollection.setChildrenBelow16InSch(below16InSchText);
            bdsDataCollection.setChildrenUnder18(childrenUnder18Text);
            if (animalFeedQuatityText.isEmpty()){
                bdsDataCollection.setBagsOfAnimalFeed("0");

            }else {
                bdsDataCollection.setBagsOfAnimalFeed(animalFeedQuatityText);
            }


            bdsDataCollection.setAnimalFeedInterest(animalFeedInterestSelected);
            bdsDataCollection.setCooperativeName(copereativeNameText);
            bdsDataCollection.setFamilyName(familyNameText);
            bdsDataCollection.setImageUrl(farmerImageBitmap);
            bdsOfflineViewModel.addNewBds(bdsDataCollection);

        }


    }
}
