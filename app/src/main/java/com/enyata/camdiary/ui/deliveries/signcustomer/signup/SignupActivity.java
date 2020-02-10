package com.enyata.camdiary.ui.deliveries.signcustomer.signup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.DispatcherSignUpRequest;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.databinding.ActivitySignupBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanViewModel;
import com.enyata.camdiary.ui.aggregations.product.ProductActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmation.ConfirmationActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmsuccess.SignsuccessActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import javax.inject.Inject;

public class SignupActivity extends BaseActivity<ActivitySignupBinding, SignupViewModel> implements SignupNavigator {

    ImageView delivery;
    ImageView history;
    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private SignupViewModel signupViewModel;
    AlertDialog dialog1;
    ActivitySignupBinding activitySignupBinding;
    TextInputEditText firstName, lastName, email, phoneNumber, address;

    String first,last, myEmail,myAddress,PhoneNo;

    public static Intent newIntent(Context context) {
        return new Intent(context, BottlesActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
    }

    @Override
    public SignupViewModel getViewModel() {
        signupViewModel = ViewModelProviders.of(this, factory).get(SignupViewModel.class);
        return signupViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupViewModel.setNavigator(this);
        activitySignupBinding = getViewDataBinding();
         firstName= activitySignupBinding.firstName;
         lastName = activitySignupBinding.lastName;
         email = activitySignupBinding.email;
         phoneNumber = activitySignupBinding.phoneNumber;
         address = activitySignupBinding.address;
        history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeliveryHistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void submit() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(SignupActivity.this);
        LayoutInflater inflater = SignupActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dispatcher_signup_confirmation, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);

        TextView firstName = dialogView.findViewById(R.id.firstName);
        TextView lastName = dialogView.findViewById(R.id.lastName);
        TextView email = dialogView.findViewById(R.id.email);
        TextView phoneNumber = dialogView.findViewById(R.id.phoneNumber);
        Button confirm = dialogView.findViewById(R.id.confirm);
        Button back = dialogView.findViewById(R.id.back);
        String firstNamee = activitySignupBinding.firstName.getText().toString();
        String lastNamee = activitySignupBinding.lastName.getText().toString();
        String emaill = activitySignupBinding.email.getText().toString();
        String phoneNumberr = activitySignupBinding.phoneNumber.getText().toString();
        String addresss = activitySignupBinding.address.getText().toString();
        if (!signupViewModel.isLengthEqualsTothree(firstNamee)){
            Alert.showFailed(getApplicationContext(), "First-name length must be three or more");
            return;
        }if (!signupViewModel.isLengthuptoTothree(lastNamee)) {
            Alert.showFailed(getApplicationContext(), "Last-name length must be three or more");
            return;
        }if (!signupViewModel.isEmailValid(emaill)){
            Alert.showFailed(getApplicationContext(),"Please enter a valid mail address");
            return;
        }if (!signupViewModel.isPhoneNumberValid(phoneNumberr)){
            Alert.showFailed(getApplicationContext(),"Please enter a valid phone number");
            return;
        }if (!signupViewModel.isAddressEmpty(addresss)){
            Alert.showFailed(getApplicationContext(),"Please enter a valid Address");
        }
        else {
            firstName.setText(activitySignupBinding.firstName.getText().toString());
            lastName.setText(activitySignupBinding.lastName.getText().toString());
            email.setText(activitySignupBinding.email.getText().toString());
            phoneNumber.setText(activitySignupBinding.phoneNumber.getText().toString());


            dialog1 = dialog.create();
            dialog1.show();

        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
                DispatcherSignUpRequest.Request request = new DispatcherSignUpRequest.Request(activitySignupBinding.firstName.getText().toString(), activitySignupBinding.lastName.getText().toString(),activitySignupBinding.phoneNumber.getText().toString(),activitySignupBinding.email.getText().toString(),activitySignupBinding.address.getText().toString());

                if (InternetConnection.getInstance(SignupActivity.this).isOnline()) {
                    signupViewModel.dispatcherSignup(request);
                }
            }
        });

    }

    @Override
    public void logout() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), DeliveryDashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void dispatcherSignup(DispatcherSignUpResponse response) {
        Alert.showSuccess(getApplicationContext(), "Sign-up Successful, A message has been sent to your mail, Please check your inbox ");
        Intent intent = new Intent(getApplicationContext(), SignsuccessActivity.class);
        startActivity(intent);

    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable!=null){
            ANError error  = (ANError) throwable;
            DispatcherSignUpResponse response = gson.fromJson(error.getErrorBody(), DispatcherSignUpResponse.class);
            if (error.getErrorBody()!=null){
                Alert.showFailed(getApplicationContext(), response.getMessage());

            }else {
                Alert.showFailed(getApplicationContext(),"Unable to Connect to the Internet");
            }
        }

    }
}
