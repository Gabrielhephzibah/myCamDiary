

package com.enyata.camdiary.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;

import com.enyata.camdiary.databinding.ActivityLoginBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.password.ResetPasswordActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by hephzibah on 10/12/19.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    Gson gson;
    private String decoded;

    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            ResetPasswordResponse response = gson.fromJson(error.getErrorBody(), ResetPasswordResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(),response.getMessage());
            }else {

                Alert.showFailed(getApplicationContext(), "Unable to connect to the Internet");
            }

        }
    }


    @Override
    public void loginClick() {
        if (InternetConnection.getInstance(this).isOnline()) {
            String email = mActivityLoginBinding.emailTextView.getText().toString();
            String password = mActivityLoginBinding.passwordTextView.getText().toString();
            if (mLoginViewModel.isEmailAndPasswordValid(email, password)) {
                if (mLoginViewModel.isLengthEqualsToSeven(password)) {
                    if (!isNetworkConnected()) {
                        Alert.showInfo(getApplicationContext(), "No internet connection, please check internet settings and try again");
                        return;
                    }
                    hideKeyboard();
                    mLoginViewModel.login(email, password);
                } else {
                    Alert.showInfo(getApplicationContext(), "Password length must be seven or more");
                }
            } else {
                Alert.showFailed(getApplicationContext(), "Please fill all fields");
            }

        }else {
            Alert.showFailed(getApplicationContext(),"Please check your internet connection and try again");
        }
    }

    @Override
    public void goToDashBoard(String type) {
        Intent intent = null;
        if(type.equals("collector")){
            intent = new Intent(getApplicationContext(), DashboardActivity.class);
        }else if(type.equals("aggregator")){
            intent = new Intent(getApplicationContext(), AggregatorDashboardActivity.class);
        }else if(type.equals("dispatcher")){
            intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        }else if(type.equals("data collector")){
            intent = new Intent(getApplicationContext(), DataCollectorDashboardActivity.class);
        }

        startActivity(intent);
    }

    @Override
    public void onForgotPassword() {
        Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(CamLoginResponse response) {

//        String token = response.getData().getToken();
//        try {
//            decoded = DecodeToken.decoded(token);
//            Log.i("DECODED",decoded);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);

    }
}
