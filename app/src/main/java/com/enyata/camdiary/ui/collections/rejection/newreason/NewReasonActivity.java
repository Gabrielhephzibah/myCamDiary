package com.enyata.camdiary.ui.collections.rejection.newreason;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.request.NewCreateCollectionRequest;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.databinding.ActivityNewReasonBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.newentervolume.NewEnterVolumeActivity;
import com.enyata.camdiary.ui.collections.rejection.reason.ReasonActivity;
import com.enyata.camdiary.ui.collections.rejection.rejectsuccess.RejectsuccessActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

public class NewReasonActivity extends BaseActivity<ActivityNewReasonBinding,NewReasonViewModel>implements NewReasonNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityNewReasonBinding activityNewReasonBinding;
    NewReasonViewModel newReasonViewModel;
    private String test_one;
    private String test_two;
    private String test_three;
    private boolean unapproved_container;
    private String statusOfCollection;
    String alertMessage;
    String farmerName;
    String rejectionVolume;
    List<ChurnDetailsData>churnRejection;

    EditText textarea;
    private String message;
    String farmerVerificationId;


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_reason;
    }

    @Override
    public NewReasonViewModel getViewModel() {
        newReasonViewModel = ViewModelProviders.of(this,factory).get(NewReasonViewModel.class);
        return newReasonViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newReasonViewModel.setNavigator(this);
        activityNewReasonBinding = getViewDataBinding();
        textarea = activityNewReasonBinding.textarea;
        farmerName = newReasonViewModel.getFarmerFullName();
        farmerVerificationId = newReasonViewModel.getFarmerVerificationNo();
        Log.d("ChurnDetails",String.valueOf(newReasonViewModel.getChurnDetails()));
        churnRejection = newReasonViewModel.getChurnDetails();
        rejectionVolume = newReasonViewModel.getRejectedVolume();
        message = "nil";
        test_one = "passed";
        test_two = "passed";
        test_three = "passed";
        statusOfCollection = "rejected";
        unapproved_container = false;
    }

    @Override
    public void submit(){
        message = textarea.getText().toString().trim();
        Log.d("text_one", test_one);
        Log.d("text_second", test_two);
        Log.d("test_three", test_three);
        Log.d("approved_container",Boolean.toString(unapproved_container));
        Log.d("Message",message);

//        List<ChurnDetailsData> churnData = newReasonViewModel.getChurnDetails();
//        for (int i = 0 ; i< churnData.size(); i++){
//            ChurnDetailsData data = churnData.get(i);
//            String volume = data.getVolume();
//
//           alertMessage =  "You have rejected "+ volume + " litres of product \nfrom " +farmerName + ".\nPlease tap continue to confirm \nrejection";
//        }

        final AlertDialog.Builder dialog = new AlertDialog.Builder(NewReasonActivity.this);
        LayoutInflater inflater = NewReasonActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        TextView entry = (TextView) dialogView.findViewById(R.id.entry);
        TextView dialogMessage = (TextView) dialogView.findViewById(R.id.message);
        entry.setText("Rejection Accepted");
        dialogMessage.setText("You have rejected "+ rejectionVolume + " litres of product \nfrom " +farmerName + ".\nPlease tap continue to confirm \nrejection");
        TextView cancel =(TextView) dialogView.findViewById(R.id.cancelCollection);
        TextView continuee = (TextView) dialogView.findViewById(R.id.continuee);
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        continuee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (newReasonViewModel.checkIfChurnDetailsIsEmpty()){
                    Alert.showFailed(getApplicationContext(),"Churn ID and volume cannot be empty");
                    return;
                }
                if (InternetConnection.getInstance(NewReasonActivity.this).isOnline()){
                    NewCreateCollectionRequest request = new NewCreateCollectionRequest(farmerVerificationId, "Rejected", test_one, test_two, test_three, unapproved_container, message, churnRejection);
                    newReasonViewModel.createCollection(request);

                }else {
                    Alert.showFailed(getApplicationContext(),"Please Check Your Internet Connection and try again");
                }
            }
        });


    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), NewEnterVolumeActivity.class);
        startActivity(intent);

    }

    @Override
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
                if (checked)unapproved_container = true;
                else unapproved_container = false;
                Log.d("approved_container",Boolean.toString(unapproved_container));
                break;
        }


    }

    @Override
    public void handleError(Throwable throwable) {
        hideLoading();
        Log.d("ERROR","ERROR");


    }

    @Override
    public void onResponse(NewCollectionResponse response) {
        hideLoading();
        Alert.showSuccess(getApplicationContext(),"Rejection Successful");
        Intent intent = new Intent(getApplicationContext(), RejectsuccessActivity.class);
        startActivity(intent);

    }

    @Override
    public void onStarting() {
        showLoading();
    }
}
