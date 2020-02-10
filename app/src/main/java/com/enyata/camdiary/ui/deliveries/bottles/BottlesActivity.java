package com.enyata.camdiary.ui.deliveries.bottles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.DeliveryCollection;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityBottlesBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import org.json.JSONObject;

import javax.inject.Inject;

public class BottlesActivity extends BaseActivity<ActivityBottlesBinding,BottlesViewModel>implements BottlesNavigator {

    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private BottlesViewModel bottlesViewModel;
    ActivityBottlesBinding activityBottlesBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, BottlesActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bottles;
    }

    @Override
    public BottlesViewModel getViewModel() {
        bottlesViewModel = ViewModelProviders.of(this,factory).get(BottlesViewModel.class);
        return bottlesViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottlesViewModel.setNavigator(this);
        activityBottlesBinding = getViewDataBinding();
       EditText editText = activityBottlesBinding.editText;


    }

    @Override
    public void finish() {
        Log.i("ORDERID", bottlesViewModel.getOrderId());
        if (InternetConnection.getInstance(this).isOnline()) {

            DeliveryCollection.Request request = new DeliveryCollection.Request(activityBottlesBinding.editText.getText().toString(), bottlesViewModel.getOrderId());
            bottlesViewModel.addNewDelivery(request);

            Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
            intent.putExtra("Bottles", activityBottlesBinding.editText.getText().toString());
            startActivity(intent);
        } else {
            Alert.showFailed(getApplicationContext(), "Please check your network connection and try again");
        }
    }

    @Override
    public void onResponse(NewCollectionResponse response) {
        Log.i("REQUESSST SUCCESSS", "YOUR REQUEST IS SUCCESSFULL");
        Alert.showSuccess(getApplicationContext(),"ORDER COMPLETED");

    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable!=null){
            ANError error =  (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(),response.getResponseMessage());
            }else {
                Alert.showFailed(getApplicationContext(),"Unable to Connect to  the internet");
            }
        }


    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bottlesViewModel.onDispose();
    }
}
