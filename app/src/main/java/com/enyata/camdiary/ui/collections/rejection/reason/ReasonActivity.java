package com.enyata.camdiary.ui.collections.rejection.reason;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityReasonBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.rejection.rejectsuccess.RejectsuccessActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;

import org.json.JSONObject;

import javax.inject.Inject;

public class ReasonActivity extends BaseActivity<ActivityReasonBinding,ReasonViewModel>implements ReasonNavigator {

    @Inject
    Gson gson;
    String volume;

    String infoMessage;

    ActivityReasonBinding activityReasonBinding;

    private String test_one;
    private String test_two;
    private String test_three;
    private boolean approved_container;
    EditText textarea;

    @Inject
    ViewModelProviderFactory factory;
    private ReasonViewModel reasonViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, ReasonActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reason;
    }

    @Override
    public ReasonViewModel getViewModel() {
        reasonViewModel = ViewModelProviders.of(this,factory).get(ReasonViewModel.class);
        return reasonViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reasonViewModel.setNavigator(this);
        activityReasonBinding = getViewDataBinding();
        textarea = activityReasonBinding.textarea;
        volume = getIntent().getStringExtra("volume");
        infoMessage = "nil";
        Log.d("volume ", volume);


        test_one = "failed";
        test_two = "failed";
        test_three = "failed";
        approved_container = true;


    }

    @Override
    public void submit() {

        Log.d("text_one", test_one);
        Log.d("text_second", test_two);
        Log.d("test_three", test_three);
        Log.d("approved_container",Boolean.toString(approved_container));


        final AlertDialog.Builder dialog = new AlertDialog.Builder(ReasonActivity.this);
        LayoutInflater inflater = ReasonActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        TextView entry = (TextView) dialogView.findViewById(R.id.entry);
        TextView message = (TextView) dialogView.findViewById(R.id.message);
        entry.setText("Rejection Accepted");
        message.setText("You have rejected "+ volume + " litres of product \nfrom Akin Solomon.\nPlease tap continue to confirm \nrejection");

        TextView cancel =(TextView) dialogView.findViewById(R.id.cancel);
        TextView continuee = (TextView) dialogView.findViewById(R.id.continuee);
        final AlertDialog alertDialog =dialog.create();
        alertDialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        continuee.setOnClickListener(v -> {
            alertDialog.dismiss();




            if(!TextUtils.isEmpty(textarea.getText().toString())){
                infoMessage = textarea.getText().toString();
            }

            try {
                JSONObject params = new JSONObject();
                params.put("farmer_id", reasonViewModel.getFarmerId());
                params.put("status_of_collection", "rejected");
                params.put("volume",volume);
                params.put("test_one",test_one);
                params.put("test_two", test_two);
                params.put("test_three", test_three);
                params.put("approved_container",approved_container);
                params.put("message",infoMessage );
                reasonViewModel.createRejectionCollection(params);

            }catch (Exception error){
                error.printStackTrace();
            }

//
//            Intent intent = new Intent(getApplicationContext(), RejectsuccessActivity.class);
//            startActivity(intent);

        });

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), EnterVolumeActivity.class);
        startActivity(intent);
    }


    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox1:
                if (checked) test_one = "failed";
                else test_one = "passed";
                Log.d("TEST_ONE",test_one);
                break;

            case R.id.checkbox2:
                if (checked) test_two = "failed";
                else test_two = "passed";
                Log.d("TEST_TWO",test_two);
                break;
            case R.id.checkbox3:
                if (checked) test_three = "failed";
                else test_three = "passed";
                Log.d("TEST_THREE",test_three);
                break;
            case R.id.checkbox4:
                if (checked)approved_container = true;
                else approved_container = false;
                Log.d("approved_container",Boolean.toString(approved_container));
                break;
        }

    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
            Alert.showFailed(getApplicationContext(), response.getResponseMessage());
        }
    }

    @Override
    public void onResponse(NewCollectionResponse response) {
        Intent intent = new Intent(getApplicationContext(), RejectsuccessActivity.class);
        intent.putExtra("volume",volume);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reasonViewModel.dispose();
    }

}
