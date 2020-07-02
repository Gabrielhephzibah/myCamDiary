package com.enyata.camdiary.ui.offlinecollection.offlineMilkCollection.milkRejectionOffline;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;
import com.enyata.camdiary.databinding.ActivityMilkRejectionOfflineBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveyAdapters.PdsDataSurveyAdapter;
import com.enyata.camdiary.utils.Alert;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MilkRejectionOfflineActivity extends BaseActivity<ActivityMilkRejectionOfflineBinding,MilkRejectionOfflineViewModel>implements MilkRejectionOfflineNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ActivityMilkRejectionOfflineBinding activityMilkRejectionOfflineBinding;
    MilkRejectionOfflineViewModel milkRejectionOfflineViewModel;
    CheckBox checkboxOne, checkboxTwo, checkBoxThree,approvedContainer;
    private String test_one;
    private String test_two;
    private String test_three;
    private boolean unapproved_container;
    private String message;
    private  String statusOfCollection;
    EditText textArea;
    private List<PdsDataCollection>pdsDataCollections = new ArrayList<>();
    String offlineFarmerId;
    PdsDataSurveyAdapter pdsDataSurveyAdapter;
    List<ChurnDetailsData>milkData;
    private List<PdsDataCollection> pdsDataCollection;


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_milk_rejection_offline;
    }

    @Override
    public MilkRejectionOfflineViewModel getViewModel() {
        milkRejectionOfflineViewModel = ViewModelProviders.of(this,factory).get(MilkRejectionOfflineViewModel.class);
        return milkRejectionOfflineViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        milkRejectionOfflineViewModel.setNavigator(this);
        activityMilkRejectionOfflineBinding = getViewDataBinding();
        checkboxOne = activityMilkRejectionOfflineBinding.checkbox1;
        checkboxTwo = activityMilkRejectionOfflineBinding.checkbox2;
        checkBoxThree = activityMilkRejectionOfflineBinding.checkbox3;
        approvedContainer = activityMilkRejectionOfflineBinding.checkbox4;
        textArea = activityMilkRejectionOfflineBinding.textarea;
        message = "nil";
        test_one = "passed";
        test_two = "passed";
        test_three = "passed";
        statusOfCollection = "rejected";
        unapproved_container = false;




    }

    @Override
    public void submit() {
        offlineFarmerId = milkRejectionOfflineViewModel.getOfflineFarmerId();
        milkData = milkRejectionOfflineViewModel.getChurnDetails();
        Log.i("TestOne",test_one);
        Log.i("TestTwo",test_two);
        Log.i("TestThree",test_three);
        Log.i("ApprovedContainer", String.valueOf(unapproved_container));
        Log.i("statusofcollection", statusOfCollection);
        Log.i("FarmerId",offlineFarmerId);
        Log.i("milkdate", String.valueOf(milkData));
        message = textArea.getText().toString().trim();
        AlertDialog alertSuccess = new AlertDialog.Builder(MilkRejectionOfflineActivity.this).create();
        alertSuccess.setMessage("Are you want to save collection?");
        alertSuccess.setCancelable(false);
        alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MilkCollection milkCollection = new MilkCollection();
                        milkCollection.setFarmerId(offlineFarmerId);
                        milkCollection.setStatusOfCollection(statusOfCollection);
                        milkCollection.setTestOne(test_one);
                        milkCollection.setTestTwo(test_two);
                        milkCollection.setTestThree(test_three);
                        milkCollection.setApprovedContainer(unapproved_container);
                        milkCollection.setMessage(message);
                        milkCollection.setChurnDetails(milkData);
                        milkRejectionOfflineViewModel.addNewMilkCollection(milkCollection);
                        dialog.dismiss();
                    }
                });
        alertSuccess.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                milkRejectionOfflineViewModel.deleteChurnDetails(milkData);
            }
        });
        alertSuccess.show();

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), OfflineDashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox1:
                if (checked) test_one = "failed";
                else test_one = "passed";
                Log.d("TEST_ONE", test_one);
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
        if (throwable!=null){
            Alert.showFailed(getApplicationContext(),"Collection not successfully submitted");
            milkRejectionOfflineViewModel.deleteChurnDetails(milkRejectionOfflineViewModel.getChurnDetails());
        }


    }

    @Override
    public void onResponse() {
        Alert.showSuccess(getApplicationContext(),"Collection created successfully");
        Intent intent = new Intent(getApplicationContext(),OfflineDashboardActivity.class);
        startActivity(intent);
        milkRejectionOfflineViewModel.deleteChurnDetails(milkRejectionOfflineViewModel.getChurnDetails());

    }

}
