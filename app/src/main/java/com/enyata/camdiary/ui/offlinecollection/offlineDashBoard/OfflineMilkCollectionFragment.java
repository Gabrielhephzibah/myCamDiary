package com.enyata.camdiary.ui.offlinecollection.offlineDashBoard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.ui.offlinecollection.offlineMilkCollection.milkRejectionOffline.MilkRejectionOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataViewModel;
import com.enyata.camdiary.utils.Alert;
import com.google.android.material.textfield.TextInputEditText;

public class OfflineMilkCollectionFragment extends Fragment {
    TextInputEditText churnId, volume,farmerId;
    LinearLayout addMore;
    Button saveCollection;
    RadioGroup radioGroup;
    RadioButton acceptBtn, rejectBtn;
    String milkVolume;
    String milkChurn;
    String farmerIdText;
    String status;
    OfflineDashboardViewModel offlineDashboardViewModel;
    ChurnDetailsData churnDetails;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offlineDashboardViewModel = ViewModelProviders.of(requireActivity()).get(OfflineDashboardViewModel.class);
        if (!offlineDashboardViewModel.checkIfChurnDetailsIsEmpty()){
            AlertDialog alertSuccess = new AlertDialog.Builder(getActivity()).create();
            alertSuccess.setMessage("Do you want to continue with unsaved volume and churn id?");
            alertSuccess.setCancelable(false);
            alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertSuccess.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    offlineDashboardViewModel.deleteChurnDetails(offlineDashboardViewModel.getChurnDetails());
                    dialogInterface.cancel();
                }
            });
            alertSuccess.show();

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offline_milk_collection_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        churnId = view.findViewById(R.id.churnId);
        volume = view.findViewById(R.id.milkVolume);
        farmerId =  view.findViewById(R.id.farmerId);
//        farmerId.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        saveCollection = view.findViewById(R.id.saveCollection);
        addMore = view.findViewById(R.id.addMore);
        acceptBtn = view.findViewById(R.id.acceptRadioBtn);
        rejectBtn = view.findViewById(R.id.rejectRadioButton);
        radioGroup = view.findViewById(R.id.radioGroup);

        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milkVolume = volume.getText().toString().trim();
                milkChurn = churnId.getText().toString().trim();
                if (TextUtils.isEmpty(milkVolume)){
                    Alert.showFailed(getActivity(),"volume cannot be empty");
                    return;
                }else if (TextUtils.isEmpty(milkChurn)){
                    Alert.showFailed(getActivity(),"churn id cannot be empty");
                    return;
                }else {
                    AlertDialog confirmationMessage = new AlertDialog.Builder(getActivity()).create();
                    confirmationMessage.setMessage("Are you sure you want to add more volume and churn ID");
                    confirmationMessage.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    churnDetails = new ChurnDetailsData(milkChurn,milkVolume);
                                    offlineDashboardViewModel.saveChurnDetailsToLocalStorage(churnDetails);
                                    dialog.dismiss();
                                    volume.setText(null);
                                    churnId.setText(null);
                                }
                            });
                    confirmationMessage.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    confirmationMessage.show();
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                hideKeyboard();
                switch (checkedId){
                    case R.id.acceptRadioBtn : {
                        Log.i("Radio","Accept");
                        status = "accepted";
                        break;
                    }
                    case R.id.rejectRadioButton : {
                        Log.i("RADIO BUTTON", "REJECT");
                        milkVolume =volume.getText().toString().trim();
                        milkChurn = churnId.getText().toString().trim();
                        farmerIdText = farmerId.getText().toString().trim();
                        offlineDashboardViewModel.setOfflineFarmerId(farmerIdText);
                        if (TextUtils.isEmpty(milkVolume)){
                            Alert.showFailed(getActivity(),"Volume cannot be empty");
                            radioGroup.clearCheck();
                            return;
                        }else if (!TextUtils.isEmpty(milkChurn)) {
                            Alert.showFailed(getActivity(), "Churn ID not allowed for rejection,churn must be empty");
                            radioGroup.clearCheck();
                            return;
                        }else if (TextUtils.isEmpty(farmerIdText)){
                            Alert.showFailed(getActivity(),"Farmer ID cannot be empty");
                            radioGroup.clearCheck();
                            return;
                        }else if (offlineDashboardViewModel.isChurnIdInArray()){
                            Alert.showFailed(getActivity(),"Unsaved data contains churn id, please delete unsaved data and try again");
                            radioGroup.clearCheck();
                            return;
                        }
                        else {
                           churnDetails = new ChurnDetailsData("0",milkVolume);
                            offlineDashboardViewModel.saveChurnDetailsToLocalStorage(churnDetails);
                            Intent intent = new Intent(getActivity(), MilkRejectionOfflineActivity.class);
                            startActivity(intent);
                            break;
                        }
                    }
                }
            }
        });


        saveCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                farmerIdText = farmerId.getText().toString().trim();
                milkVolume =volume.getText().toString().trim();
                milkChurn = churnId.getText().toString().trim();
                if (TextUtils.isEmpty(farmerIdText)){
                    Alert.showFailed(getActivity(),"Farmer id is required");
                    return;
                }
                else if (TextUtils.isEmpty(milkVolume)){
                    Alert.showFailed(getActivity(),"Volume is required");
                    return;
                }
                else if (TextUtils.isEmpty(milkChurn)){
                    Alert.showFailed(getActivity(),"Churn id is required");
                    return;
                }
                else if (radioGroup.getCheckedRadioButtonId() == -1){
                    Alert.showFailed(getActivity(),"Status cannot be empty");
                    return;
                }else {
                    AlertDialog alertSuccess = new AlertDialog.Builder(getActivity()).create();
                    alertSuccess.setMessage("Are you sure you want to save collection?");
                    alertSuccess.setCancelable(false);
                    alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    churnDetails = new ChurnDetailsData(milkChurn, milkVolume);
                                    offlineDashboardViewModel.saveChurnDetailsToLocalStorage(churnDetails);
                                    MilkCollection milkCollection = new MilkCollection();
                                    milkCollection.setFarmerId(farmerIdText);
                                    milkCollection.setStatusOfCollection(status);
                                    milkCollection.setTestOne("passed");
                                    milkCollection.setTestTwo("passed");
                                    milkCollection.setTestThree("passed");
                                    milkCollection.setApprovedContainer(false);
                                    milkCollection.setMessage("nil");
                                    milkCollection.setChurnDetails(offlineDashboardViewModel.getChurnDetails());
                                    offlineDashboardViewModel.addNewMilkCollection(milkCollection);
                                    dialog.dismiss();
                                }
                            });
                    alertSuccess.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            volume.setText(null);
                            churnId.setText(null);
                            farmerId.setText(null);
                            radioGroup.clearCheck();
                            offlineDashboardViewModel.deleteChurnDetails(offlineDashboardViewModel.getChurnDetails());
                            dialogInterface.cancel();

                        }
                    });
                    alertSuccess.show();

                }
            }
        });

    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }




}

