package com.enyata.camdiary.ui.collections.entervolume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityEnterVolumeBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.rejection.reason.ReasonActivity;
import com.enyata.camdiary.ui.collections.statusofcollection.StatusOfCollectionActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import org.json.JSONObject;

import javax.inject.Inject;

public class EnterVolumeActivity extends BaseActivity<ActivityEnterVolumeBinding, EnterVolumeViewModel> implements EnterVolumeNavigator {
    String volume;
    String first_name;
    String last_name;
    String fullName;
    String coperateName;
    String verificationNumber;
    String farmer_id;

    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private EnterVolumeViewModel enterVolumeViewModel;
    private ActivityEnterVolumeBinding enterVolumeBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, FarmerDetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_volume;
    }

    @Override
    public EnterVolumeViewModel getViewModel() {
        enterVolumeViewModel = ViewModelProviders.of(this, factory).get(EnterVolumeViewModel.class);
        return enterVolumeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enterVolumeBinding = getViewDataBinding();
        enterVolumeViewModel.setNavigator(this);
        first_name = getIntent().getStringExtra("first_name");
        last_name = getIntent().getStringExtra("last_name");
        coperateName = getIntent().getStringExtra("coperate_name");
        verificationNumber = getIntent().getStringExtra("farmer_id");
        farmer_id= getIntent().getStringExtra("farmer_identity");
        fullName = first_name + " " + last_name;
    }

    @Override
    public void accept() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(EnterVolumeActivity.this);
        LayoutInflater inflater = EnterVolumeActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        TextView message = (TextView) dialogView.findViewById(R.id.message);
        volume = enterVolumeBinding.volumeEditText.getText().toString();
        message.setText("You have collected "+volume +" litres of product \nfrom "+ fullName+ " .\nPlease tap continue to confirm \nCollection");

        TextView cancel = dialogView.findViewById(R.id.cancel);
        TextView continuee = dialogView.findViewById(R.id.continuee);
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        cancel.setOnClickListener(v -> alertDialog.dismiss());
        continuee.setOnClickListener(v -> {
            alertDialog.dismiss();

            if (TextUtils.isEmpty(volume)) {
                Alert.showInfo(getApplicationContext(), "Please enter volume");
                return;
            } else  if (InternetConnection.getInstance(this).isOnline()){
                try {
                    JSONObject params = new JSONObject();
                    params.put("farmer_id",enterVolumeViewModel.getFarmerId());
                    params.put("status_of_collection", "accepted");
                    params.put("volume", volume);
                    params.put("test_one", "passed");
                    params.put("test_two", "passed");
                    params.put("test_three", "passed");
                    params.put("approved_container", "true");

                    params.put("message","nil");

                    enterVolumeViewModel.createCollection(params);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Alert.showFailed(getApplicationContext(),"Please check your Internet Connection and try again");
            }
        });
    }

    @Override
    public void onResponseError() {
        Alert.showFailed(getApplicationContext(), "Unable to connect");
    }

    @Override
    public void reject() {
         volume = enterVolumeBinding.volumeEditText.getText().toString();

        if (TextUtils.isEmpty(volume)){
            Alert.showInfo(getApplicationContext(),"Please enter volume");
            return;
        } else {
            Intent intent = new Intent(getApplicationContext(), ReasonActivity.class);
            intent.putExtra("volume",volume);
            intent.putExtra("first_name",first_name);
            intent.putExtra("last_name", last_name);
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
    public void displayResponse(NewCollectionResponse response) {
        Alert.showSuccess(getApplicationContext(),"Collection Successful");
        Intent status = new Intent(getApplicationContext(), StatusOfCollectionActivity.class);
        status.putExtra("responseCode", response.getResponseCode());
        status.putExtra("volume",volume);
        status.putExtra("first_name",first_name);
        status.putExtra("last_name", last_name);
        status.putExtra("coperate_name",coperateName);
        status.putExtra("farmer_id",verificationNumber);
        startActivity(status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        enterVolumeViewModel.dispose();
    }

}
