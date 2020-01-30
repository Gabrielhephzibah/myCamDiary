package com.enyata.camdiary.ui.password;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.ResetPasswordRequest;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.databinding.ActivityResetPasswordBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;

import javax.inject.Inject;

public class ResetPasswordActivity extends BaseActivity<ActivityResetPasswordBinding, ResetPasswordViewModel>implements ResetPasswordNavigator {

    @Inject
    Gson  gson;
    @Inject
    ViewModelProviderFactory factory;
    ActivityResetPasswordBinding activityResetPasswordBinding;
    ResetPasswordViewModel resetPasswordViewModel;
    EditText editText;
    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    public ResetPasswordViewModel getViewModel() {
         resetPasswordViewModel = ViewModelProviders.of(this,factory).get(ResetPasswordViewModel.class);
         return resetPasswordViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resetPasswordViewModel.setNavigator(this);
        activityResetPasswordBinding = getViewDataBinding();
         String editText = activityResetPasswordBinding.editText.getText().toString();



    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable!= null){
            ANError error = (ANError) throwable;
            ResetPasswordResponse response = gson.fromJson(error.getErrorBody(), ResetPasswordResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getMessage());
            }else {
                Alert.showFailed(getApplicationContext(),"Unable to Connect to the Internet");
            }
        }
    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSubmit() {
        Log.i("EDITTEXTVALUE", activityResetPasswordBinding.editText.getText().toString());

        ResetPasswordRequest.Request request = new ResetPasswordRequest.Request(activityResetPasswordBinding.editText.getText().toString());

        resetPasswordViewModel.resetPassword(request);


    }

    @Override
    public void onResetResponse(ResetPasswordResponse response) {
        Log.i("MESSAGEEEEE", response.getMessage());
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
               alertDialog .setTitle("Reset Password");
               alertDialog.setMessage("A message has been sent to your mail, Please check your inbox to reset password");
               alertDialog.setCancelable(false);
               alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                       startActivity(intent);

                   }

               });
               alertDialog.setNegativeButton("Resend", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                   }
               });
               AlertDialog alert = alertDialog.create();
               alert.show();












    }
}
