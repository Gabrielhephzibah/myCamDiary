package com.enyata.camdiary.ui.collections.data.bdsData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.library.baseAdapters.BR;
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

import com.androidnetworking.error.ANError;
import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.BdsDataRequest;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.response.ElectoralWardResponse;
import com.enyata.camdiary.data.model.api.response.GetCoperativeNameResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityBdsDataBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.ui.collections.data.cdsData.CdsDataActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class BdsDataActivity extends BaseActivity<ActivityBdsDataBinding,BdsViewModel>implements BdsDataNavigator  {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    Gson gson;



    BdsViewModel bdsViewModel;
    ActivityBdsDataBinding activityBdsDataBinding;
    LinearLayout bioDataFragment, locationInfo, incomeSourceFragment, coperativeInfo, farmInfo;
    ImageView  bioDataToggle, locationToggle, incomeToggle, coperativeToggle, farmInfoToggle;
    EditText firstName, lastName, age,familyName,phoneNo,childernUnder18,below16, below16InSch,adult18Above,communityName,soucesIncome, mainIncome, weekEarning, monthEarning,markeyDay,milkPerDay, milkConsume, milkForSale,challenges, cowInAbuja,totalCow,milkingCow,
    animalFeedQuantity,recommendation,feedback;
    ImageView farmerImage;
    int currentapiVersion;
    boolean spinnerSelected;
    ProgressDialog dialog;
    String animalFeedRequirement;
    List<String> electoralWards;
    String cloudinaryImage, imageURL, cloudinaryID;
    private static final int REQUEST_PERMISSION = 1;
    private static int PICK_FROM_GALLERY = 1;
    Spinner gender, maritalStatus,electoralWard,areaCouncil,coperativeName, animalFeedInterest;
    String firstNameText, lastNameText, ageText, familyNameText,phoneNoText,childrenUnder18Text, below16Text, below16InSchText, adult18AboveText, communityNameText,sourcesIncomeText,mainIncomeText,weekEarningText, monthlyEarningText, marketDayText,milkPerDayText,milkConsumeText,milkForSaleText,challengesText,cowInAbujaText, totalCowText,milkingCowText,
    animalFeedQuatityText, recommendationText, feedbackText, genderSelected, maritalStatusSelected,electoralWardSelected,areaCouncilWardSelected,copereativeNameSelected, animalFeedInterestSelected;
    String [] cooperativeNameOption = {"","Paiko","Zuba","falaku","lumo"};
    List<String>getCoperativeName;
    String [] animalFieldInterestOption = {"","Yes","No"};
    String[] genderOption = {"","Male","Female"};
    String[] maritalStatusOption = {"","Yes","No"};
    String[] areaCouncilOption = {"","Abaji","Bwari","Gwagalada","Kuje", "Kwali","Abuja Municipal"};
//    String[] abajiOption = {"","Abaji Central","Abaji North East","Abaji South East","Agyana/Pandagi", "Rimba Ebagi","Nuku","Alu Mamagi","Yaba","Gurdi","Gawu"};
//    String[] bwariOption = {"","Bwari Central","Kuduru","Igu","Shere", "Kawu","Ushafa","Dutse Alhaji","Byazhin","Kubwa","Usuma"};
//    String[] gwagaladaOption = {"","Gwagalada Centre","Kutunku","Staff Quarters","Ibwa", "Dobi","Paiko","Tungan Maje","Zuba","Ikwa","Gwako"};
//    String[] kujeOption = {"","Kuje","Chibiri","Guabe","Kwaku", "Kabi","Rubochi","Gwargwada","Gudun Karya","Kujekwa","Yenche"};
//    String[] kwaliOption = {"","Kwali Road","Yangoji","Pai","Kilankwa", "Dafa","Kundu","Ashara","Gumbo","Wako","Yebu"};
//    String[] abujaMunicpalOption = {"","City Centre","Garki","Kabusa","Wuse", "Gwarinpa","Jiwa","Gui","Karshi","Orozo","Karu","Nyanya","Gwagwa"};




    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bds_data;
    }

    @Override
    public BdsViewModel getViewModel() {
        bdsViewModel = ViewModelProviders.of(this,factory).get(BdsViewModel.class);
        return bdsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bdsViewModel.setNavigator(this);
        activityBdsDataBinding = getViewDataBinding();

         currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission() && checkExternalPermission()) {
            } else {
                requestPermission();
            }
        }
        bioDataFragment = activityBdsDataBinding.bioDataFragment;
        incomeSourceFragment = activityBdsDataBinding.incomeFragment;
        locationInfo = activityBdsDataBinding.locationFragment;
        coperativeInfo = activityBdsDataBinding.coperativeFragment;
        farmInfo = activityBdsDataBinding.farmInfoFragment;
        bioDataToggle = activityBdsDataBinding.bioDataToggle;
        incomeToggle = activityBdsDataBinding.incomeToggle;
        locationToggle = activityBdsDataBinding.locationToggle;
        coperativeToggle = activityBdsDataBinding.coprerativeToggle;
        farmInfoToggle = activityBdsDataBinding.farmInfoToggle;
        firstName = activityBdsDataBinding.firstName;
        lastName = activityBdsDataBinding.lastName;
        age = activityBdsDataBinding.age;
        familyName = activityBdsDataBinding.familyName;
        phoneNo = activityBdsDataBinding.phoneNumber;
        childernUnder18 = activityBdsDataBinding.below18;
        below16 = activityBdsDataBinding.below16;
        below16InSch = activityBdsDataBinding.below16InSch;
        adult18Above = activityBdsDataBinding.adult18Above;
        communityName = activityBdsDataBinding.communityName;
        soucesIncome = activityBdsDataBinding.sourcesOfIncome;
        mainIncome = activityBdsDataBinding.mainIncome;
        weekEarning = activityBdsDataBinding.weekEarning;
        monthEarning = activityBdsDataBinding.monthlyEarning;
        markeyDay = activityBdsDataBinding.marketTime;
        milkPerDay = activityBdsDataBinding.milkPerDay;
        milkConsume  = activityBdsDataBinding.houseHoldConsume;
        milkForSale = activityBdsDataBinding.milkForSale;
        challenges = activityBdsDataBinding.milkChallenges;
        cowInAbuja = activityBdsDataBinding.cowInAbuja;
        totalCow = activityBdsDataBinding.totalCow;
        milkingCow = activityBdsDataBinding.milkingCow;
        animalFeedQuantity = activityBdsDataBinding.animalFieldQuantity;
        recommendation = activityBdsDataBinding.recommendation;
        feedback = activityBdsDataBinding.feedback;
        farmerImage = activityBdsDataBinding.farmerImage;
        gender = activityBdsDataBinding.genderSpinner;
        maritalStatus = activityBdsDataBinding.maritalSpinner;
        electoralWard = activityBdsDataBinding.electoralWardSpinner;
        areaCouncil = activityBdsDataBinding.areaCouncilSpinner;
        coperativeName = activityBdsDataBinding.coperativeNameSpinner;
        animalFeedInterest = activityBdsDataBinding.animalFeedInterestSpinner;
        dialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("please wait");
        bdsViewModel.getCoperativeName();


        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(BdsDataActivity.this, android.R.layout.simple_spinner_item,genderOption);
        gender.setAdapter(genderAdapter);




        ArrayAdapter<String> animalFeedInterestAdapter = new ArrayAdapter<>(BdsDataActivity.this, android.R.layout.simple_spinner_item,animalFieldInterestOption);
        animalFeedInterest.setAdapter(animalFeedInterestAdapter);

        ArrayAdapter<String>maritalStatusAdapter = new ArrayAdapter<>(BdsDataActivity.this, android.R.layout.simple_spinner_item, maritalStatusOption);
        maritalStatus.setAdapter(maritalStatusAdapter);

        ArrayAdapter<String>councilOptionAdapter = new ArrayAdapter<>(BdsDataActivity.this, android.R.layout.simple_spinner_item, areaCouncilOption);
        areaCouncil.setAdapter(councilOptionAdapter);

        areaCouncil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spinnerSelected) {
                    spinnerSelected = true;
                    return;
                }else {
                    bdsViewModel.getElectoralWard(areaCouncil.getSelectedItem().toString());
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
        switch (bdsViewModel.getCurrentUserType()){
            case "collector": {
                Intent intent = new Intent( getApplicationContext(), DataCollectionActivity.class);
                startActivity(intent);
                break;
            }

            case "data collector": {
                Intent intent = new Intent(getApplicationContext(), DataCollectorDashboardActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onSubmitBds() {
        hideKeyboard();
        firstNameText = firstName.getText().toString();
        lastNameText = lastName.getText().toString();
        ageText = age.getText().toString();
        familyNameText = familyName.getText().toString();
        phoneNoText = phoneNo.getText().toString();
        childrenUnder18Text = childernUnder18.getText().toString();
        below16Text = below16.getText().toString();
        adult18AboveText = adult18Above.getText().toString();
        below16InSchText = below16InSch.getText().toString();
        communityNameText = communityName.getText().toString();
        sourcesIncomeText = soucesIncome.getText().toString();
        mainIncomeText = mainIncome.getText().toString();
        weekEarningText = weekEarning.getText().toString();
        monthlyEarningText = monthEarning.getText().toString();
        marketDayText = markeyDay.getText().toString();
        milkPerDayText = milkPerDay.getText().toString();
        milkConsumeText = milkConsume.getText().toString();
        milkForSaleText = milkForSale.getText().toString();
        challengesText = challenges.getText().toString();
        cowInAbujaText = cowInAbuja.getText().toString();
        totalCowText = totalCow.getText().toString();
        milkingCowText = milkingCow.getText().toString();
        animalFeedQuatityText = animalFeedQuantity.getText().toString();
        if (animalFeedQuatityText.isEmpty()){
            animalFeedRequirement = "0";

        }else {
            animalFeedRequirement = animalFeedQuatityText;
        }
        recommendationText = recommendation.getText().toString();
        feedbackText = feedback.getText().toString();
        animalFeedInterestSelected = animalFeedInterest.getSelectedItem().toString();
        copereativeNameSelected = coperativeName.getSelectedItem().toString();
        areaCouncilWardSelected = (String) areaCouncil.getSelectedItem();
        if (electoralWard!=null && electoralWard.getSelectedItem()!=null){
            electoralWardSelected = electoralWard.getSelectedItem().toString();
        }else {
            Alert.showFailed(getApplicationContext(),"Area Council and electoral ward are required");
            return;
        }
        genderSelected = ( String) gender.getSelectedItem();
        maritalStatusSelected = (String) maritalStatus.getSelectedItem();
        Log.i("Electoral ward", electoralWardSelected);
        Log.i("Area Council", areaCouncilWardSelected);
        Log.i("COPERATIVENAME",copereativeNameSelected);
        if (electoralWardSelected.equals("select electoral ward")){
            Alert.showFailed(getApplicationContext(),"select electoral ward");
            return;
        }else if (copereativeNameSelected.equals("select cooperative name")){
            Alert.showFailed(getApplicationContext(),"select cooperative name");
            return;
        }else {
            if (InternetConnection.getInstance(BdsDataActivity.this).isOnline()) {
                BdsDataRequest.Request request = new BdsDataRequest.Request(imageURL, firstNameText, lastNameText, genderSelected, ageText, maritalStatusSelected, familyNameText, phoneNoText, electoralWardSelected, areaCouncilWardSelected, communityNameText, copereativeNameSelected, sourcesIncomeText, mainIncomeText, weekEarningText, monthlyEarningText, marketDayText, childrenUnder18Text, below16Text, below16InSchText, adult18AboveText, milkPerDayText, milkConsumeText, milkForSaleText, challengesText, cowInAbujaText, totalCowText, milkingCowText, animalFeedInterestSelected, animalFeedRequirement, recommendationText, feedbackText);
                bdsViewModel.onSubmitBdsDataQuestion(request);

            } else {
                Alert.showFailed(getApplicationContext(), "Please check your internet connection and try again");
            }


        }


    }

    @Override
    public void onUploadPicture() {
        try{

            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
        }catch(Exception exp){
            Log.i("Error",exp.toString());
        }



    }

    @Override
    public void onResponse(NewCollectionResponse response) {
        switch (bdsViewModel.getCurrentUserType()){
            case "collector":{
                Alert.showSuccess(getApplicationContext(), response.getResponseMessage());
                Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
                startActivity(intent);
                break;
            }
            case  "data collector":{
                Alert.showSuccess(getApplicationContext(),response.getResponseMessage());
                Intent intent = new Intent(getApplicationContext(),DataCollectorDashboardActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void handleError(Throwable throwable) {
        try {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getMessage());
            }
        }else{
            Alert.showFailed(getApplicationContext(), " Unable to connect to the internet");
        }
        }catch (IllegalStateException | JsonSyntaxException | ClassCastException | NullPointerException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
        }
    }



    private boolean checkExternalPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(BdsDataActivity.this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    boolean readExternalAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (readExternalAccepted && writeExternalStorage) {
                        Alert.showSuccess(getApplicationContext(), "Permission Granted, Now you can access access the gallery");
                    } else {
                        Alert.showSuccess(getApplicationContext(), "Permission Denied, You cannot access gallery");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA) && shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
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
            Uri uri = data.getData();

            try {
                dialog.show();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                uploadImagetoCloudinary(bitmap);

                farmerImage.setImageBitmap(bitmap);
                Log.i("BITMAP", String.valueOf(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public void uploadImagetoCloudinary(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG , 20, byteArrayOutputStream);
        byte[] partImage = byteArrayOutputStream.toByteArray();
        MediaManager.get().upload(partImage)
                .option("resource_type", "image")
                .unsigned("ht7lodiw")
                .callback(new UploadCallback() {

                    @Override
                    public void onStart(String requestId) {
                        Log.i("START", "STARTTTTT");
                        dialog.show();


                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Double progress = (double) bytes / totalBytes;

                        dialog.show();
                        Log.i("PROGRESS", "PROGRESS");

                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        if (resultData != null) {
                            Log.i("SUCCESS", "SUCCESS");
                            dialog.dismiss();

                            imageURL = (String) resultData.get("url");
                            cloudinaryID = (String) resultData.get("public_id").toString();
                            cloudinaryImage = MediaManager.get().url().transformation(new Transformation()).resourceType("image").generate(cloudinaryID + ".jpg");
                            Alert.showSuccess(getApplicationContext(),"Image uploaded successfully");
                            Log.i("imageURL", imageURL);
                            Log.i("cloudinaryID", cloudinaryID);

                        }

                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.i("ERROR", "ERROR");
                        dialog.dismiss();
                        Alert.showFailed(getApplicationContext(), "Error Uploading Result, Please try again later ");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        Log.i("SCHEDULE", "SCHEDULE");
                        dialog.dismiss();

                        Alert.showFailed(getApplicationContext(), "Uploading is taking time,please take picture again");

                    }
                })
                .dispatch();


    }

    @Override
    public void onElectoralWardResponse(ElectoralWardResponse response) {
        electoralWards = response.getData();
        electoralWards.add(0, "select electoral ward");
        ArrayAdapter<String>electoralWardAdapter = new ArrayAdapter<>(BdsDataActivity.this,android.R.layout.simple_spinner_item, electoralWards);
        electoralWard.setAdapter(electoralWardAdapter);

    }

    @Override
    public void onGetCoperativeResponse(GetCoperativeNameResponse response) {
        Log.i("CoperativeResponse ","Response Responsible" + String.valueOf(response.getData()));
        getCoperativeName = response.getData();
        getCoperativeName.add(0, "select cooperative name");
        ArrayAdapter<String> cooperativeNameAdapter= new ArrayAdapter<>(BdsDataActivity.this, android.R.layout.simple_spinner_item,getCoperativeName);
        coperativeName.setAdapter(cooperativeNameAdapter);

    }

    @Override
    public void onGetCoperativeError(Throwable throwable) {
        Log.i("ERROR", "Error");
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
            Alert.showFailed(getApplicationContext(),"An  unknown error occurred");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bdsViewModel.onDispose();
    }


}
