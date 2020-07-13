package com.enyata.camdiary.ui.aggregations.barcode.collectorID;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.CollectorDetails;
import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.Details;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.ResponseMessage;
import com.enyata.camdiary.databinding.ActivityCollectorIdBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;

public class CollectorIdActivity extends BaseActivity<ActivityCollectorIdBinding, ColectorIdViewModel> implements CollectorIdNavigator {
    @Inject
    Gson gson;

    EditText collectorId;

    @Inject
    ViewModelProviderFactory factory;
    private ColectorIdViewModel colectorIdViewModel;
    ActivityCollectorIdBinding activityCollectorIdBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collector_id;
    }

    @Override
    public ColectorIdViewModel getViewModel() {
        colectorIdViewModel = ViewModelProviders.of(this, factory).get(ColectorIdViewModel.class);
        return colectorIdViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colectorIdViewModel.setNavigator(this);
        collectorId = findViewById(R.id.collectorId);

    }

    @Override
    public void handleError(Throwable throwable) {
        try {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            ResponseMessage response = gson.fromJson(error.getErrorBody(), ResponseMessage.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getMessage()+ ", " + "Please enter a valid Id");
            }else {
                Alert.showFailed(getApplicationContext(),"Unable to Connect to the Internet");
            }

        }
        }catch (IllegalStateException | JsonSyntaxException|NullPointerException|ClassCastException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
        }
    }

    @Override
    public void accept() {
        String id = collectorId.getText().toString();
        if (TextUtils.isEmpty(id)) {
            Alert.showFailed(getApplicationContext(), "Please enter collector's verification_id");
            return;
        }else  if (InternetConnection.getInstance(this).isOnline()){
            colectorIdViewModel.getCollectorDetails(id);
        } else {
            Alert.showFailed(getApplicationContext(), "Please Check you Internet Connection and try again");
        }

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);
    }

    @Override
    public void getCollectorDetails(CollectorDetailsResponse response) {
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
        CollectorDetails data = response.getData();
        colectorIdViewModel.setCollectorName(data.getFirstName() + " " + data.getLastName());
        colectorIdViewModel.setCollectorId(String.valueOf(data.getId()));
        colectorIdViewModel.setCollectorEmail(data.getEmail());
        colectorIdViewModel.setCollectorPhoneNumber(data.getContactNo());
        colectorIdViewModel.setCollectorVerificationId(data.getVerificationId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        colectorIdViewModel.dispose();

    }
}
