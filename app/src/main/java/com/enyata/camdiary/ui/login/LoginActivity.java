/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

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
import com.enyata.camdiary.databinding.ActivityLoginBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.main.MainActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by hephzibah on 10/12/19.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    Gson gson;

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
            CamLoginResponse response = gson.fromJson(error.getErrorBody(), CamLoginResponse.class);
            Alert.showFailed(getApplicationContext(),response.getError());
        }
    }



    @Override
    public void loginClick() {
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
    }

    @Override
    public void goToDashBoard(String type) {
        Intent intent = null;
        if(type.equals("collectors")){
            intent = new Intent(getApplicationContext(), DashboardActivity.class);
        }else if(type.equals("aggregator")){
            intent = new Intent(getApplicationContext(), AggregatorDashboardActivity.class);
        }else if(type.equals("delivery")){
            intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);

    }
}
