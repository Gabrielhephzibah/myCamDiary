package com.enyata.camdiary.ui.collections.newentervolume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.request.NewCreateCollectionRequest;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.remote.RetrofitClient;
import com.enyata.camdiary.databinding.ActivityNewEnterVolumeBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.rejection.newreason.NewReasonActivity;
import com.enyata.camdiary.ui.collections.statusofcollection.StatusOfCollectionActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class NewEnterVolumeActivity extends BaseActivity<ActivityNewEnterVolumeBinding,NewEnterVolumeViewModel>implements NewEnterVolumeNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityNewEnterVolumeBinding activityNewEnterVolumeBinding;
    NewEnterVolumeViewModel newEnterVolumeViewModel;
    TextInputEditText volume, churnId;
    LinearLayout addMore;
    String volumeText;
    String churnIdText;
    String farmerName;
    String alertMessage;
    List<ChurnDetailsData>churn;
    AlertDialog alert;
    RetrofitClient retrofitClient = new RetrofitClient();

    String farmerVerificationId;



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_enter_volume;
    }

    @Override
    public NewEnterVolumeViewModel getViewModel() {
        newEnterVolumeViewModel = ViewModelProviders.of(this,factory).get(NewEnterVolumeViewModel.class);
        return newEnterVolumeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newEnterVolumeViewModel.setNavigator(this);
        activityNewEnterVolumeBinding = getViewDataBinding();
        volume = activityNewEnterVolumeBinding.milkVolume;
        churnId = activityNewEnterVolumeBinding.churnId;
        addMore = activityNewEnterVolumeBinding.addMore;
        farmerName = newEnterVolumeViewModel.getFarmerFullName();
        Log.i("FramerId",newEnterVolumeViewModel.getFarmerVerificationNo());

        if (!newEnterVolumeViewModel.checkIfChurnDetailsIsEmpty()){
            AlertDialog alertSuccess = new AlertDialog.Builder(NewEnterVolumeActivity.this).create();
            alertSuccess.setMessage("Do you want to continue with unsaved volume and churn id?");
            alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertSuccess.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    newEnterVolumeViewModel.deleteChurnDetails(newEnterVolumeViewModel.getChurnDetails());
                    dialogInterface.cancel();
                }
            });
            alertSuccess.show();
        }


        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volumeText = volume.getText().toString().trim();
                churnIdText = churnId.getText().toString().trim();
                if (TextUtils.isEmpty(volumeText)){
                    Alert.showFailed(getApplicationContext(),"Volume cannot be empty");
                    return;
                }else if (TextUtils.isEmpty(churnIdText)){
                    Alert.showFailed(getApplicationContext(),"Churn Id cannot be empty");
                    return;
                }else {
                AlertDialog alertSuccess = new AlertDialog.Builder(NewEnterVolumeActivity.this).create();
                alertSuccess.setMessage("Are you sure you want to add more volume and churn ID");
                alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ChurnDetailsData data = new ChurnDetailsData(churnIdText,volumeText);
                                newEnterVolumeViewModel.saveChurnDetailsToLocalStorage(data);
                                volume.setText(null);
                                churnId.setText(null);
                                dialog.cancel();
                            }
                        });
                alertSuccess.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertSuccess.show();
                }
            }
        });




    }

    @Override
    public void accept() {
        hideKeyboard();
        volumeText = volume.getText().toString().trim();
        churnIdText = churnId.getText().toString().trim();
        if (TextUtils.isEmpty(volumeText)){
            Alert.showFailed(getApplicationContext(),"Enter volume");
            return;
        }else if (TextUtils.isEmpty(churnIdText)){
            Alert.showFailed(getApplicationContext(),"Enter Churn ID");
            return;
        }
        else {
            ChurnDetailsData churnDetailsData = new ChurnDetailsData(churnIdText,volumeText);
             newEnterVolumeViewModel.saveChurnDetailsToLocalStorage(churnDetailsData);
            List<ChurnDetailsData>churnData = newEnterVolumeViewModel.getChurnDetails();
            String prefix = " ";
            String volumePrefix = " ";
            StringBuilder stringVolume = new StringBuilder();
            StringBuilder stringChurn = new StringBuilder();
            for (int i = 0; i< churnData.size(); i++ ){
                ChurnDetailsData churnDetails = churnData.get(i);
                String alertChurn= churnDetails.getChurnId() ;
                String alertVolume = churnDetails.getVolume() ;
                Log.i("ChurnId", alertChurn);
                Log.i("Volume",alertVolume);
                if (churnData.size() >1 && churnData.indexOf(churnDetails) == (churnData.size() -1)){
                    stringChurn.append(" and ").append(alertChurn);
                }else {
                    stringChurn.append(prefix);
                    prefix = " , ";
                    stringChurn.append(alertChurn);
                }

                if (churnData.size() > 1 && churnData.indexOf(churnDetails) == (churnData.size() -1)){
                    stringVolume.append(" and ").append(alertVolume);
                }else {
                    stringVolume.append(volumePrefix);
                    volumePrefix = " , ";
                    stringVolume.append(alertVolume);
                }
                alertMessage = "You have collected " + stringVolume + " litres of product with Churn_id of " + stringChurn + " respectively from " + farmerName + ".\nPlease tap continue to confirm Collection";

            }
             AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            LayoutInflater inflater = NewEnterVolumeActivity.this.getLayoutInflater();
            View view = inflater.inflate(R.layout.confirm_entry_layout,null);
            alertDialog.setView(view);
            alertDialog.setCancelable(false);
            TextView message = (TextView) view.findViewById(R.id.message);
            TextView continuee = view.findViewById(R.id.continuee);
            TextView cancelCollection = view.findViewById(R.id.cancelCollection);
            message.setText(alertMessage);
            churn = newEnterVolumeViewModel.getChurnDetails();


            continuee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    farmerVerificationId = newEnterVolumeViewModel.getFarmerVerificationNo();
                    churn = newEnterVolumeViewModel.getChurnDetails();
                    alert.cancel();

                    if (newEnterVolumeViewModel.checkIfChurnDetailsIsEmpty()){
                        Alert.showFailed(getApplicationContext(),"Churn ID and volume cannot be empty");
                        return;
                    }
                    if (InternetConnection.getInstance(NewEnterVolumeActivity.this).isOnline()) {
                        NewCreateCollectionRequest request = new NewCreateCollectionRequest(farmerVerificationId, "Accepted", "passed", "passed", "passed", false, "nil", churn);
                        newEnterVolumeViewModel.createCollection(request);
                    }else {
                        Alert.showFailed(getApplicationContext(),"Unable to connect to the internet");
                    }
                }
            });




            alert = alertDialog.create();
            alert.show();

            cancelCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newEnterVolumeViewModel.deleteChurnDetails(churn);
                    volume.setText(null);
                    churnId.setText(null);
                    alert.cancel();

                }
            });




        }



    }

    @Override
    public void reject() {
        hideKeyboard();
        volumeText = volume.getText().toString().trim();
        churnIdText = churnId.getText().toString().trim();
        if (TextUtils.isEmpty(volumeText)){
            Alert.showFailed(getApplicationContext(),"Enter volume");
            return;
        }else if (!TextUtils.isEmpty(churnIdText)){
            Alert.showFailed(getApplicationContext(),"Churn id not allowed for rejection, churn id must be empty");
            return;
        }else if (newEnterVolumeViewModel.isChurnIdInArray()){
            Alert.showFailed(getApplicationContext(),"Unsaved data contains churn id, please delete unsaved data and try again");
            return;
        }
        else {
            newEnterVolumeViewModel.setRejectedVolume(volumeText);
            ChurnDetailsData rejected = new ChurnDetailsData("0",volumeText);
            newEnterVolumeViewModel.saveChurnDetailsToLocalStorage(rejected);

            Intent intent = new Intent(getApplicationContext(), NewReasonActivity.class);
            startActivity(intent);

        }


    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), FarmerDetailsActivity.class);
        startActivity(intent);

    }

    @Override
    public void handleError(Throwable throwable) {
        hideLoading();
        Log.i("ERROR","ERROR");
        if (throwable!=null){
            try {
                if (throwable instanceof HttpException) {
                    Log.i("HTTP", "HTTP");
                    Converter<ResponseBody, NewCollectionResponse> errorConverter = retrofitClient.getRetrofit().responseBodyConverter(NewCollectionResponse.class, new Annotation[0]);
                    try {
                        NewCollectionResponse error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                        Alert.showFailed(getApplicationContext(), error.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Alert.showFailed(getApplicationContext(), "Unable to connect to internet");
                }
            } catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }
        }

    }

    @Override
    public void displayResponse(NewCollectionResponse response) {

    }

    @Override
    public void dismissAllModal() {

    }

    @Override
    public void onStarting() {
        showLoading();
    }

    @Override
    public void onResponse(NewCollectionResponse response) {
        hideLoading();
        Alert.showSuccess(getApplicationContext(),response.getResponseMessage());
        Intent intent = new Intent(getApplicationContext(), StatusOfCollectionActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newEnterVolumeViewModel.dispose();
    }
}
