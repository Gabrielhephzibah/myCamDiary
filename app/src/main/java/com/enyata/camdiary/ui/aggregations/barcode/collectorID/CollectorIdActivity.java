package com.enyata.camdiary.ui.aggregations.barcode.collectorID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.FarmerDetails;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.databinding.ActivityCollectorIdBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardViewModel;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;

import javax.inject.Inject;

public class CollectorIdActivity extends BaseActivity<ActivityCollectorIdBinding,ColectorIdViewModel>implements CollectorIdNavigator {
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
        colectorIdViewModel = ViewModelProviders.of(this,factory).get(ColectorIdViewModel.class);
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
//        if (throwable != null) {
//            ANError error = (ANError) throwable;
//            FarmerDetails response = gson.fromJson(error.getErrorBody(), FarmerDetails.class);
//            Alert.showFailed(getApplicationContext(), response.getError());
//        }

    }

    @Override
    public void accept() {
        String id = collectorId.getText().toString();
        if(TextUtils.isEmpty(id)){
            Alert.showFailed(getApplicationContext(),"Please enter farmer id");
            return;
        }

//        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
//        startActivity(intent);

        colectorIdViewModel.getCollectorDetails(id);

    }

    @Override
    public void back() {
        Intent intent =  new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);
    }

    @Override
    public void getCollectorDetails(DetailsResponse response) {
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);

        FarmerDetails data = response.getData();
        Log.d("message",data.getFirstName());
        Log.d("message",data.getLastName());
        Log.d("message",data.getContactNo());
        Log.d("message",data.getVerificationId());

//        Log.d("message",data.getCooperativeName());

        intent.putExtra("first_name",data.getFirstName());
        intent.putExtra("last_name",data.getLastName());
        intent.putExtra("phone_number", data.getContactNo());
        intent.putExtra("verification_id",data.getVerificationId());
        intent.putExtra("email",data.getEmail());
        intent.putExtra("coperate_name", data.getCooperativeName());



        startActivity(intent);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        colectorIdViewModel.dispose();

    }
}
