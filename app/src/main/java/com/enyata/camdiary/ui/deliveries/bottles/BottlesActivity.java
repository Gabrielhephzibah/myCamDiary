package com.enyata.camdiary.ui.deliveries.bottles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.DeliveryCollection;
import com.enyata.camdiary.data.model.api.response.DeliveryDetailResponse;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityBottlesBinding;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import javax.inject.Inject;

public class BottlesActivity extends BaseActivity<ActivityBottlesBinding,BottlesViewModel>implements BottlesNavigator {

    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private BottlesViewModel bottlesViewModel;
    ActivityBottlesBinding activityBottlesBinding;
    String customerName;

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
       Log.i("ORDERID", bottlesViewModel.getOrderId());
       customerName = bottlesViewModel.getCustomerName();
       Log.d("CUSTOMER PHONE NO", bottlesViewModel.getCustomerPhoneNo());

    }

    @Override
    public void finish() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(BottlesActivity.this);
        LayoutInflater inflater = BottlesActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        TextView cancel = dialogView.findViewById(R.id.cancelCollection);
        TextView continuee = dialogView.findViewById(R.id.continuee);
        TextView message = dialogView.findViewById(R.id.message);
        message.setText(String.format("You have just delivered CamDairy Product to \n%s.\nPlease tap continue to confirm \nDelivery", customerName));
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        cancel.setOnClickListener(v -> alertDialog.dismiss());
        continuee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

        if (InternetConnection.getInstance(BottlesActivity.this).isOnline()) {
            String bottle = activityBottlesBinding.editText.getText().toString();
            String newBottle;
            if (bottle.isEmpty()){
                newBottle = "0";
            }else {
                newBottle = activityBottlesBinding.editText.getText().toString();
            }

            DeliveryCollection.Request request = new DeliveryCollection.Request(newBottle, bottlesViewModel.getOrderId());
            bottlesViewModel.addNewDelivery(request);

        } else {
            Alert.showFailed(getApplicationContext(), "Please check your network connection and try again");
        }
            }
        });


    }

    @Override
    public void onResponse(DispatcherSignUpResponse response) {
        Log.i("REQUESSST SUCCESSS", "YOUR REQUEST IS SUCCESSFULL");
        Alert.showSuccess(getApplicationContext(), response.getMessage());
        Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
        intent.putExtra("Bottles", activityBottlesBinding.editText.getText().toString());
        startActivity(intent);

    }

    @Override
    public void handleError(Throwable throwable) {
        try {
        if (throwable!=null){
            ANError error =  (ANError) throwable;
            DispatcherSignUpResponse response = gson.fromJson(error.getErrorBody(), DispatcherSignUpResponse.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(),response.getMessage());
            }else {
                Alert.showFailed(getApplicationContext(),"Unable to Connect to  the internet");
            }
        }

        }catch (IllegalStateException | JsonSyntaxException |ClassCastException |NullPointerException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
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
