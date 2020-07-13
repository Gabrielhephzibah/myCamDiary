package com.enyata.camdiary.ui.collections.entervolume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import javax.inject.Inject;

public class EnterVolumeActivity extends BaseActivity<ActivityEnterVolumeBinding, EnterVolumeViewModel> implements EnterVolumeNavigator {
    String volume;
    String first_name;
    String last_name;
    String fullName;
    TextInputEditText churnNo;
    AlertDialog.Builder dialog;
    String churnNumber;

    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private EnterVolumeViewModel enterVolumeViewModel;
    private ActivityEnterVolumeBinding enterVolumeBinding;
    private  AlertDialog firstModal,secondModal;

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
        fullName = enterVolumeViewModel.getFarmerFullName();
    }

    @Override
    public void accept() {
        volume = enterVolumeBinding.volumeEditText.getText().toString();
        if (TextUtils.isEmpty(volume)) {
            Alert.showInfo(getApplicationContext(), "Please enter Volume");
            return;
        }else {
            dialog = new AlertDialog.Builder(EnterVolumeActivity.this);
        LayoutInflater inflater = EnterVolumeActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.collector_churn_layout, null);
        dialog.setView(view);
        dialog.setCancelable(false);
        TextView back = view.findViewById(R.id.back);
        churnNo= view.findViewById(R.id.churnNo);
        TextView accept = view.findViewById(R.id.acceptChurn);
        firstModal = dialog.create();
        firstModal.show();
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firstModal.dismiss();

                }
            });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                churnNumber = churnNo.getText().toString();
                if (TextUtils.isEmpty(churnNumber)) {
                    Log.i("ChurnNo", churnNumber);
                    Alert.showFailed(getApplicationContext(), "Please Enter Churn Number");
                    return;
                } else {

                    final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
                    dialog.setView(dialogView);
                    dialog.setCancelable(false);
                    TextView message = (TextView) dialogView.findViewById(R.id.message);
                    volume = enterVolumeBinding.volumeEditText.getText().toString();
                    message.setText("You have collected " + volume + " litres of product \nfrom " + fullName + " .\nPlease tap continue to confirm \nCollection");

                    TextView cancel = dialogView.findViewById(R.id.cancel);
                    TextView continuee = dialogView.findViewById(R.id.continuee);
                    secondModal = dialog.create();
                    secondModal.show();

                    cancel.setOnClickListener(v -> secondModal.dismiss());
                    continuee.setOnClickListener(v -> {
                        dismissAllModal();

                         if (InternetConnection.getInstance(EnterVolumeActivity.this).isOnline()) {
                            try {
                                JSONObject params = new JSONObject();
                                params.put("farmer_id", enterVolumeViewModel.getFarmerId());
                                params.put("status_of_collection", "accepted");
                                params.put("volume", volume);
                                params.put("test_one", "passed");
                                params.put("churn_no",churnNumber );
                                params.put("test_two", "passed");
                                params.put("test_three", "passed");
                                params.put("approved_container", "true");
                                params.put("message", "nil");

                                enterVolumeViewModel.createCollection(params);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            Alert.showFailed(getApplicationContext(), "Please check your Internet Connection and try again");
                        }


                    });


                }
            }
        });

    }


    }


    @Override
    public void reject() {
        hideKeyboard();
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
        }catch (IllegalStateException | JsonSyntaxException| NullPointerException |ClassCastException exception){
            Alert.showFailed(getApplicationContext(), "An unknown error occurred");
        }
    }

    @Override
    public void displayResponse(NewCollectionResponse response) {
        Alert.showSuccess(getApplicationContext(),"Collection Successful");
        Intent status = new Intent(getApplicationContext(), StatusOfCollectionActivity.class);
        status.putExtra("volume",volume);
        startActivity(status);
    }

    @Override
    public void dismissAllModal() {
        firstModal.dismiss();
        secondModal.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        enterVolumeViewModel.dispose();
    }

}
