package com.enyata.camdiary.ui.offlinecollection.savedData.milkCollectionSavedData;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.request.NewCreateCollectionRequest;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.data.remote.ApiUtils;
import com.enyata.camdiary.data.remote.RetrofitClient;
import com.enyata.camdiary.ui.offlinecollection.savedData.MilkCollectionAdapter.MilkCollectionAdapter;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataNavigator;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataViewModel;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveySavedData.DataSurveySavedDataFragement;
import com.enyata.camdiary.ui.password.ResetPasswordActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.FragmentUtils;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class MilkCollectionSavedDataFragment extends Fragment implements OfflineSavedDataNavigator, MilkCollectionAdapter.onMilkListener {
    @Inject
    Gson gson = new Gson();
    OfflineSavedDataViewModel offlineSavedDataViewModel;
    private MilkCollectionAdapter milkCollectionAdapter;
    RecyclerView milkCollectionRecyclerView;
    MilkCollection milkCollection;
    AlertDialog alertDialog, milkCollectionDialog;
    Button uploadMilkCollection;
    TextInputEditText password, email;
    String passWord, eMail;
    TextView date;
    RelativeLayout forgotPassword;
    RetrofitClient retrofitClient = new RetrofitClient();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offlineSavedDataViewModel = ViewModelProviders.of(requireActivity()).get(OfflineSavedDataViewModel.class);
        offlineSavedDataViewModel.setNavigator(this);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.milk_collection_saved_data_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        milkCollectionRecyclerView = view.findViewById(R.id.milkCollectionRecyclerView);
        uploadMilkCollection = view.findViewById(R.id.uploadMilkCollection);
        date = view.findViewById(R.id.date);
        milkCollectionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addRecyclerViewDivider(milkCollectionRecyclerView);
        offlineSavedDataViewModel.getAllMilkCollection();
        date.setText(offlineSavedDataViewModel.getCurrentDate());

        uploadMilkCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = MilkCollectionSavedDataFragment.this.getLayoutInflater();
                View viewItem = inflater.inflate(R.layout.offline_upload_modal, null);
                alertBuilder.setView(viewItem);
                alertBuilder.setCancelable(false);
                Button signInUpload = viewItem.findViewById(R.id.signInToUpload);
                password = viewItem.findViewById(R.id.passwordTextView);
                email = viewItem.findViewById(R.id.emailTextView);
//                forgotPassword = viewItem.findViewById(R.id.forgotPasswordLayout);
                TextView close = viewItem.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        milkCollectionDialog.cancel();
                    }
                });

//                forgotPassword.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(getActivity(), ResetPasswordActivity.class);
//                        startActivity(intent);
//                    }
//                });
                signInUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (InternetConnection.getInstance(getActivity()).isOnline()) {
                            passWord = password.getText().toString();
                            eMail = email.getText().toString();
                            if (offlineSavedDataViewModel.isEmailAndPasswordValid(eMail, passWord)) {
                                if (offlineSavedDataViewModel.isLengthEqualsToSeven(passWord)) {
                                    if (!FragmentUtils.isNetworkConnected(getActivity())) {
                                        Alert.showInfo(getActivity(), "No internet connection, please check internet settings and try again");
                                        return;
                                    }
                                    hideKeyboard();
                                    offlineSavedDataViewModel.loginToUploadCollection(eMail, passWord);
                                    FragmentUtils.showLoading(getActivity());
                                } else {
                                    Alert.showInfo(getActivity(), "Password length must be seven or more");

                                }
                            } else {
                                Alert.showFailed(getActivity(), "Please fill all fields");

                            }

                        } else {
                            Alert.showFailed(getActivity(), "Please check your internet connection and try again");

                        }


                    }
                });

                milkCollectionDialog = alertBuilder.create();
                milkCollectionDialog.show();
            }
        });

    }

    @Override
    public void onBackToDashboard() {

    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            Alert.showFailed(getActivity(), "Error while processing request");
        }

    }

    @Override
    public void onGetResponse(List<CdsDataCollection> cdsDataCollection) {

    }

    @Override
    public void onDeleteResponse() {
        Alert.showSuccess(getActivity(), "Collection deleted Successfully");
    }

    @Override
    public void onGetPdsResponse(List<PdsDataCollection> pdsDataCollections) {

    }

    @Override
    public void onGetBdsResponse(List<BdsDataCollections> bdsDataCollections) {

    }

    @Override
    public void onLoginResponse(CamLoginResponse response) {

    }

    @Override
    public void onLoginError(Throwable throwable) {

    }

    @Override
    public void onLoginCollectionError(Throwable throwable) {
        FragmentUtils.hideLoading();
        milkCollectionDialog.cancel();
        try {
            if (throwable != null) {
                ANError error = (ANError) throwable;
                ResetPasswordResponse response = gson.fromJson(error.getErrorBody(), ResetPasswordResponse.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getActivity(), response.getMessage());
                } else {

                    Alert.showFailed(getActivity(), "Unable to connect to the Internet");
                }

            }
        } catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception) {
            Alert.showFailed(getActivity(), "An unknown error occurred");
        }


    }

    @Override
    public void onGetCdsUploadResponse(List<CdsDataCollection> cdsDataCollections) {

    }

    @Override
    public void onGetPdsUploadResponse(List<PdsDataCollection> pdsDataCollections) {

    }

    @Override
    public void onGetBdsUploadResponse(List<BdsDataCollections> bdsDataCollections) {

    }

    @Override
    public void onGetCollectionUploadResponse(List<MilkCollection> milkCollections) {
        Log.i("MILKCollectionaSSSS", String.valueOf(milkCollections));

        try {
            for (int i = 0; i < milkCollections.size(); i++) {
                MilkCollection milkData = milkCollections.get(i);
                milkCollection = milkCollectionAdapter.getData().get(i);
                String farmerId = milkData.getFarmerId();
                String status = milkData.getStatusOfCollection();
                String testOne = milkData.getTestOne();
                String testTwo = milkData.getTestTwo();
                String testThree = milkData.getTestThree();
                String message = milkData.getMessage();
                boolean approvedContainer = milkData.isApprovedContainer();
                List<ChurnDetailsData> churnDetailsData = milkData.getChurnDetails();
//                Log.i("FarmerId",farmerId);
//                Log.i("Status",status);
//                Log.i("testOne",testOne);
//                Log.i("TestTwo",testTwo);
//                Log.i("TestThree",testThree);
//                Log.i("message",message);
//                Log.i("approvedContainer",String.valueOf(approvedContainer));
//                Log.i("ChurnDetails",String.valueOf(churnDetailsData));

                if (InternetConnection.getInstance(getActivity()).isOnline()) {

                    NewCreateCollectionRequest request = new NewCreateCollectionRequest(farmerId, status, testOne, testTwo, testThree, approvedContainer, message, churnDetailsData);
                    offlineSavedDataViewModel.createMilkCollection(request,milkCollection);
                    FragmentUtils.showLoading(getActivity());

//                    mAPIService = ApiUtils.getAPIService();
//                    disposables.add(mAPIService.createNewCollection(offlineSavedDataViewModel.getAccesstoken(), request)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(response -> {
//                                FragmentUtils.hideLoading();
//                                Alert.showSuccess(getActivity(), response.getResponseMessage());
//                                offlineSavedDataViewModel.deleteCollectionOnUpload(milkCollection);
//                                if (milkCollections.isEmpty()) {
//                                    uploadMilkCollection.setVisibility(View.GONE);
//                                } else {
//                                    uploadMilkCollection.setVisibility(View.VISIBLE);
//                                }
//
//                                Log.i("RESPONSE", "RESPONSE IS SUCESSFULK");
//                            }, throwable -> {
//                                FragmentUtils.hideLoading();
//                                onSubmitCollectionError(throwable);
//
//                            }));

                } else {
                    Alert.showFailed(getActivity(), "Unable to connect to the internet");
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onSubmitCdsData(NewCollectionResponse cdsDataRequest) {

    }

    @Override
    public void onSubmitCdsDataError(Throwable throwable) {
    }

    @Override
    public void onSubmitCollectionError(Throwable throwable) {
        FragmentUtils.hideLoading();
        throwable.printStackTrace();
        try {
            if (throwable instanceof HttpException) {
                Log.i("HTTP", "HTTP");
                Converter<ResponseBody, NewCollectionResponse> errorConverter = retrofitClient.getRetrofit().responseBodyConverter(NewCollectionResponse.class, new Annotation[0]);
                try {
                    NewCollectionResponse error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    Alert.showFailed(getActivity(), error.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Alert.showFailed(getActivity(), "Unable to connect to internet");
            }
        } catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception) {
            Alert.showFailed(getActivity(), "An unknown error occurred");
        }
    }

    @Override
    public void onLoginPDSResponse(CamLoginResponse response) {

    }

    @Override
    public void onLoginBDSResponse(CamLoginResponse response) {

    }

    @Override
    public void onLoginCollectionResponse(CamLoginResponse response) {
        FragmentUtils.hideLoading();
        milkCollectionDialog.cancel();
        AlertDialog alertSuccess = new AlertDialog.Builder(getActivity()).create();
        alertSuccess.setMessage(response.getMessage() + "!" + "Continue with upload");
        alertSuccess.setCancelable(false);
        alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "UPLOAD",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        offlineSavedDataViewModel.getAllCollectionDataAndUpload();
                        dialog.dismiss();
                    }
                });
        alertSuccess.setButton(AlertDialog.BUTTON_NEGATIVE, "BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertSuccess.show();

    }

    @Override
    public void onMilkCollectionResponse(List<MilkCollection> milkCollections) {
        Log.i("MilkCollection", String.valueOf(milkCollections));
        milkCollectionAdapter = new MilkCollectionAdapter(getActivity(), milkCollections, this);
        milkCollectionRecyclerView.setAdapter(milkCollectionAdapter);
        if (milkCollections.isEmpty()) {
            uploadMilkCollection.setVisibility(View.GONE);
        }


    }

    @Override
    public void onCdsUploadResponse(NewCollectionResponse response, CdsDataCollection cdsDataCollection) {

    }

    @Override
    public void onBdsUploadResponse(NewCollectionResponse response, BdsDataCollections bdsDataCollections) {

    }

    @Override
    public void onPdsUploadResponse(NewCollectionResponse response, PdsDataCollection pdsDataCollections) {

    }

    @Override
    public void onMilkCollectionUploadResponse(NewCollectionResponse response, MilkCollection milkCollection) {
        FragmentUtils.hideLoading();
        Alert.showSuccess(getActivity(), response.getResponseMessage());
        offlineSavedDataViewModel.deleteCollectionOnUpload(milkCollection);
        if (offlineSavedDataViewModel.checkIfMilkCollectionIsEmpty()) {
            uploadMilkCollection.setVisibility(View.GONE);
        } else {
            uploadMilkCollection.setVisibility(View.VISIBLE);
        }

    }

    public void addRecyclerViewDivider(RecyclerView recyclerView) {
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public void deleteMilk(int position) {
        milkCollection = milkCollectionAdapter.getData().get(position);
        String farmerId = milkCollection.getFarmerId();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = MilkCollectionSavedDataFragment.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.offline_delete_confirmation_modal, null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView deleteMessage = view.findViewById(R.id.deleteMessage);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        String message = "Are you sure you want to delete\n" + farmerId + " from the collection list";
        Spannable spannable = new SpannableString(message);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#21523C")), message.indexOf(farmerId), message.indexOf(farmerId) + farmerId.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), message.indexOf(farmerId), message.indexOf(farmerId) + farmerId.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        deleteMessage.setText(spannable);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offlineSavedDataViewModel.deleteMilkCollection(milkCollection);
                milkCollectionAdapter.deleteMilkData(position);
                alertDialog.cancel();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();


            }
        });

        alertDialog = builder.create();
        alertDialog.show();


    }

    @Override
    public void detailMilk(int position) {
        milkCollection = milkCollectionAdapter.getData().get(position);
        List<ChurnDetailsData> churnDetails = milkCollection.getChurnDetails();
        String prefix = " ";
        String volumePrefix = " ";
        StringBuilder stringVolume = new StringBuilder();
        StringBuilder stringChurn = new StringBuilder();
        Log.d("CHURNDETAIL", String.valueOf(churnDetails));
        for (int i = 0; i < churnDetails.size(); i++) {
            ChurnDetailsData data = churnDetails.get(i);
            String volume = data.getVolume();
            String churnId = data.getChurnId();


            stringChurn.append(churnId);
            stringChurn.append("\n");


            stringVolume.append(volume).append(" Litres");
            stringVolume.append("\n");
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = MilkCollectionSavedDataFragment.this.getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.offline_collection_details_layout, null);
        alertBuilder.setView(viewItem);
        alertBuilder.setCancelable(false);
        Button button = viewItem.findViewById(R.id.close);
        TextView volumeText = viewItem.findViewById(R.id.volumeText);
        TextView churnIdText = viewItem.findViewById(R.id.churnIdText);
        volumeText.setText(stringVolume);
        churnIdText.setText(stringChurn);
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.d("ON DESTROY","MILK COLLECTION FRAGMENT UNDESTROY CALLED");
//        offlineSavedDataViewModel.dispose();
//
//    }


}
