package com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveySavedData;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
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
import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.camdiary.BuildConfig;
import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.request.BdsDataRequest;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.request.PdsDataRequest;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;
import com.enyata.camdiary.ui.collections.data.bdsData.BdsDataActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editBdsOffline.EditBdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editCdsOffline.EditCdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editPdsOffline.EditPdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataNavigator;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataViewModel;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveyAdapters.BdsDataSurveyAdapter;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveyAdapters.CdsDataSurveyAdapter;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveyAdapters.PdsDataSurveyAdapter;
import com.enyata.camdiary.ui.password.ResetPasswordActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.CommonUtils;
import com.enyata.camdiary.utils.FragmentUtils;
import com.enyata.camdiary.utils.InternetConnection;
import com.enyata.camdiary.utils.NetworkUtils;
import com.enyata.camdiary.utils.rx.SchedulerProvider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


public class DataSurveySavedDataFragement extends Fragment implements OfflineSavedDataNavigator,CdsDataSurveyAdapter.onDataListener,PdsDataSurveyAdapter.onPdsDataListener,BdsDataSurveyAdapter.onBdsDataListener {
    @Inject
    Gson gson = new Gson();
    RelativeLayout openCds, openBds, openPds;
    LinearLayout cdsFragment, bdsFragment, pdsFragment;
    ImageView cdsToggleIcon, bdsToggleIcon, pdsToggleIcon;
    RecyclerView cdsRecyclerView, pdsRecyclerView, bdsRecyclerview;
    private OfflineSavedDataViewModel offlineSavedDataViewModel;
    private CdsDataCollection cdsDataCollection;
    private Button uploadcds, uploadBds, uploadPds;
    private CdsDataSurveyAdapter cdsDataSurveyAdapter;
    AlertDialog alertDialog, cdsDialog, bdsDialog, pdsDialog;
    private PdsDataCollection pdsDataCollection;
    private PdsDataSurveyAdapter pdsDataSurveyAdapter;
    private BdsDataSurveyAdapter bdsDataSurveyAdapter;
    private BdsDataCollections bdsDataCollection;
    TextInputEditText password, email;
    Uri uri;
    Bitmap myBitmap;
    TextView cdsDate, bdsDate, pdsDate;
    RelativeLayout forgotPassword;
    String cloudImageUrl, cloudinaryId, cloudinaryImage;
    private CompositeDisposable disposables = new CompositeDisposable();
    String passWord, eMail;


    public DataSurveySavedDataFragement() {
        //leave
    }

    public static DataSurveySavedDataFragement newInstance() {
        return new DataSurveySavedDataFragement();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offlineSavedDataViewModel = ViewModelProviders.of(requireActivity()).get(OfflineSavedDataViewModel.class);
        offlineSavedDataViewModel.setNavigator(this);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_survey_saved_data_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openCds = view.findViewById(R.id.onCdsOpen);
        openBds = view.findViewById(R.id.onBdsOpen);
        openPds = view.findViewById(R.id.onPdsOpen);
        cdsFragment = view.findViewById(R.id.cdsFragment);
        bdsFragment = view.findViewById(R.id.bdsFragment);
        pdsFragment = view.findViewById(R.id.pdsFragment);
        cdsToggleIcon = view.findViewById(R.id.cdsToggleIcon);
        bdsToggleIcon = view.findViewById(R.id.bdsToggleIcon);
        pdsToggleIcon = view.findViewById(R.id.pdsToggleIcon);
        cdsRecyclerView = view.findViewById(R.id.cdsRecyclerView);
        pdsRecyclerView = view.findViewById(R.id.pdsRecyclerView);
        bdsRecyclerview = view.findViewById(R.id.bdsRecyclerView);
        cdsDate = view.findViewById(R.id.cdsDate);
        bdsDate = view.findViewById(R.id.bdsDate);
        pdsDate = view.findViewById(R.id.pdsDate);
        bdsDate.setText(offlineSavedDataViewModel.getCurrentDate());
        pdsDate.setText(offlineSavedDataViewModel.getCurrentDate());
        cdsDate.setText(offlineSavedDataViewModel.getCurrentDate());


        uploadcds = view.findViewById(R.id.uploadCds);
        uploadBds = view.findViewById(R.id.uploadBds);
        uploadPds = view.findViewById(R.id.uploadPds);

        uploadcds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = DataSurveySavedDataFragement.this.getLayoutInflater();
                View viewItem = inflater.inflate(R.layout.offline_upload_modal, null);
                alertBuilder.setView(viewItem);
                alertBuilder.setCancelable(false);
                Button signInUpload = viewItem.findViewById(R.id.signInToUpload);
//                forgotPassword = viewItem.findViewById(R.id.forgotPasswordLayout);
//                forgotPassword.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(getActivity(), ResetPasswordActivity.class);
//                        startActivity(intent);
//                    }
//                });
                TextView close = viewItem.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cdsDialog.cancel();
                    }
                });
                password = viewItem.findViewById(R.id.passwordTextView);
                email = viewItem.findViewById(R.id.emailTextView);


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
                                    offlineSavedDataViewModel.loginToUpload(eMail, passWord);
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


                        Log.i("Email", eMail);
                        Log.i("Password", passWord);

                        Log.i("UPLOAD", "Upload");
                    }
                });
                cdsDialog = alertBuilder.create();
                cdsDialog.show();

            }
        });

        uploadPds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = DataSurveySavedDataFragement.this.getLayoutInflater();
                View viewItem = inflater.inflate(R.layout.offline_upload_modal, null);
                alertBuilder.setView(viewItem);
                alertBuilder.setCancelable(false);
                Button signInUpload = viewItem.findViewById(R.id.signInToUpload);
                password = viewItem.findViewById(R.id.passwordTextView);
                email = viewItem.findViewById(R.id.emailTextView);
//                forgotPassword = viewItem.findViewById(R.id.forgotPasswordLayout);
//                forgotPassword.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(getActivity(), ResetPasswordActivity.class);
//                        startActivity(intent);
//                    }
//                });
                TextView close = viewItem.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pdsDialog.cancel();
                    }
                });
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
                                    offlineSavedDataViewModel.loginToUploadPDS(eMail, passWord);
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

                pdsDialog = alertBuilder.create();
                pdsDialog.show();


            }
        });

        uploadBds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = DataSurveySavedDataFragement.this.getLayoutInflater();
                View viewItem = inflater.inflate(R.layout.offline_upload_modal, null);
                alertBuilder.setView(viewItem);
                alertBuilder.setCancelable(false);
                Button signInUpload = viewItem.findViewById(R.id.signInToUpload);
                password = viewItem.findViewById(R.id.passwordTextView);
                email = viewItem.findViewById(R.id.emailTextView);
//                forgotPassword = viewItem.findViewById(R.id.forgotPasswordLayout);
//                forgotPassword.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(getActivity(), ResetPasswordActivity.class);
//                        startActivity(intent);
//                    }
//                });
                TextView close = viewItem.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bdsDialog.cancel();
                    }
                });
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
                                    offlineSavedDataViewModel.loginToUploadBDS(eMail, passWord);
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

                bdsDialog = alertBuilder.create();
                bdsDialog.show();


            }
        });


        cdsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pdsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bdsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        addRecyclerViewDivider(cdsRecyclerView);
        addRecyclerViewDivider(pdsRecyclerView);
        addRecyclerViewDivider(bdsRecyclerview);

        offlineSavedDataViewModel.getAllCdsData();
        offlineSavedDataViewModel.getAllPdsData();
        offlineSavedDataViewModel.getAllBdsData();
        openCds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cdsFragment.getVisibility() == View.GONE) {
                    cdsFragment.setVisibility(View.VISIBLE);
                    cdsToggleIcon.setImageResource(R.drawable.ic_expand_less_icon);
                } else {
                    cdsFragment.setVisibility(View.GONE);
                    cdsToggleIcon.setImageResource(R.drawable.ic_expand_more_icon);
                }


            }
        });

        openBds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bdsFragment.getVisibility() == View.GONE) {
                    bdsFragment.setVisibility(View.VISIBLE);
                    bdsToggleIcon.setImageResource(R.drawable.ic_expand_less_icon);
                } else {
                    bdsFragment.setVisibility(View.GONE);
                    bdsToggleIcon.setImageResource(R.drawable.ic_expand_more_icon);
                }

            }
        });

        openPds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdsFragment.getVisibility() == View.GONE) {
                    pdsFragment.setVisibility(View.VISIBLE);
                    pdsToggleIcon.setImageResource(R.drawable.ic_expand_less_icon);
                } else {
                    pdsFragment.setVisibility(View.GONE);
                    pdsToggleIcon.setImageResource(R.drawable.ic_expand_more_icon);
                }

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
        cdsDataSurveyAdapter = new CdsDataSurveyAdapter(getActivity(), cdsDataCollection, this);
        cdsRecyclerView.setAdapter(cdsDataSurveyAdapter);
        if (cdsDataCollection.isEmpty()) {
            uploadcds.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDeleteResponse() {
        Log.i("DELETESUCCESSFUL", "DELETESucessful");
        Alert.showSuccess(getActivity(), " Data deleted successfully!");
    }

    @Override
    public void onGetPdsResponse(List<PdsDataCollection> pdsDataCollections) {
        pdsDataSurveyAdapter = new PdsDataSurveyAdapter(getActivity(), pdsDataCollections, this);
        pdsRecyclerView.setAdapter(pdsDataSurveyAdapter);
        if (pdsDataCollections.isEmpty()) {
            uploadPds.setVisibility(View.GONE);
        }

    }

    @Override
    public void onGetBdsResponse(List<BdsDataCollections> bdsDataCollections) {
        Log.d("SAVED BDS FIRST", "GET AND SHOW ON RECYCLERVIEW");
        bdsDataSurveyAdapter = new BdsDataSurveyAdapter(getActivity(), bdsDataCollections, this);
        bdsRecyclerview.setAdapter(bdsDataSurveyAdapter);
        if (bdsDataCollections.isEmpty()) {
            uploadBds.setVisibility(View.GONE);
        }
        Log.i("Success", "SUCESSFULL" + bdsDataCollections);
    }

    @Override
    public void onLoginResponse(CamLoginResponse loginResponse) {
        FragmentUtils.hideLoading();
        cdsDialog.cancel();
        AlertDialog alertSuccess = new AlertDialog.Builder(getActivity()).create();
        alertSuccess.setMessage(loginResponse.getMessage() + "!." + "Continue with upload");
        alertSuccess.setCancelable(false);
        alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "UPLOAD",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        offlineSavedDataViewModel.getAllCdsDataAndUpload();
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
    public void onLoginError(Throwable throwable) {
        FragmentUtils.hideLoading();
        if (cdsDialog != null) {
            cdsDialog.cancel();
        }
        if (bdsDialog != null) {
            bdsDialog.cancel();
        }
        if (pdsDialog != null) {
            pdsDialog.cancel();
        }
//        cdsDialog.cancel();

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
    public void onLoginCollectionError(Throwable throwable) {

    }

    @Override
    public void onGetCdsUploadResponse(List<CdsDataCollection> cdsDataCollections) {
        try {
            for (int i = 0; i < cdsDataCollections.size(); i++) {
                CdsDataCollection cdsDataNew = cdsDataCollections.get(i);
                cdsDataCollection = cdsDataSurveyAdapter.getData().get(i);
                String firstName = cdsDataNew.getFirstName();
                String lastName = cdsDataNew.getLastName();
                String gender = cdsDataNew.getGender();
                String age = cdsDataNew.getAge();
                String PhoneNumber = cdsDataNew.getPhone_no();
                String maritalStatus = cdsDataNew.getMarital_status();
                String adult18 = cdsDataNew.getAdult18Above();
                String electoralWard = cdsDataNew.getElectoralWard();
                String areaCouncil = cdsDataNew.getAreaCouncil();
                String communityName = cdsDataNew.getCommunityName();
                String Income = cdsDataNew.getIncome();
                String sourceOfcome = cdsDataNew.getSourcesOfIncome();
                String weeklyEarn = cdsDataNew.getWeekEarning();
                String monthlyEarn = cdsDataNew.getMonthlyEarning();
                String milkPerDay = cdsDataNew.getLitresOfMilkPerDay();
                String milkForSale = cdsDataNew.getMilkForSale();
                String challenges = cdsDataNew.getMilkChallenges();
                String cowInAbuja = cdsDataNew.getCowInAbuja();
                String totalCow = cdsDataNew.getTotalCow();
                String milkingCow = cdsDataNew.getMilkingCow();
                String feedback = cdsDataNew.getFeedback();
                if (InternetConnection.getInstance(getActivity()).isOnline()) {
                    CdsDataRequest.Request request = new CdsDataRequest.Request(firstName, lastName, gender, age, maritalStatus, PhoneNumber, electoralWard, areaCouncil, communityName, Income, sourceOfcome, weeklyEarn, monthlyEarn, adult18, milkPerDay, milkForSale, challenges, cowInAbuja, totalCow, milkingCow, feedback);
                    offlineSavedDataViewModel.submitCdsDataCollection(request,cdsDataCollection);
                    FragmentUtils.showLoading(getActivity());
                } else {
                    Alert.showFailed(getActivity(), "Unable to connect to the internet");
                }


            }

        } catch (Exception e) {

        }

    }

    @Override
    public void onGetPdsUploadResponse(List<PdsDataCollection> pdsDataCollections) {
        Log.i("ALL PDS DATA", String.valueOf(pdsDataCollections));
        try {
            for (int i = 0; i < pdsDataCollections.size(); i++) {
                PdsDataCollection pdsData = pdsDataCollections.get(i);
                pdsDataCollection = pdsDataSurveyAdapter.getData().get(i);
                String farmerVerificationId = pdsData.getFarmerId();
                String sourcesIncome = pdsData.getSourcesOfIncome();
                String mainIncome = pdsData.getMainIncome();
                String weekEarning = pdsData.getWeekEarning();
                String monthEarning = pdsData.getMonthlyEarning();
                String market = pdsData.getMarketDays();
                String milkPerday = pdsData.getLitresOfMilkPerDay();
                String houseHoldConsume = pdsData.getHouseHoldConsumption();
                String milkForSale = pdsData.getMilkForSale();
                String challenges = pdsData.getMilkChallenges();
                String abujaCow = pdsData.getCowInAbuja();
                String totalCow = pdsData.getTotalCow();
                String milkingCow = pdsData.getMilkingCow();
                String recommend = pdsData.getRecommendations();
                String feedback = pdsData.getFeedback();
                if (InternetConnection.getInstance(getActivity()).isOnline()) {
                    FragmentUtils.showLoading(getActivity());
                    PdsDataRequest.Request request = new PdsDataRequest.Request(farmerVerificationId, sourcesIncome, mainIncome, weekEarning, monthEarning, market, milkPerday, houseHoldConsume, milkForSale, challenges, abujaCow, totalCow, milkingCow, recommend, feedback);
                    offlineSavedDataViewModel.submitPdsDataCollection(request, pdsDataCollection);
                    FragmentUtils.showLoading(getActivity());
                } else {
                    Alert.showFailed(getActivity(), "Unable to connect to the internet");
                }
            }

        } catch (Exception e) {

        }
    }

    @Override
    public void onGetBdsUploadResponse(List<BdsDataCollections> bdsDataCollections) {
        Log.d("BDS DATA ON SECOND", "GET FOR UPLOAD");
        try {
            for (int i = 0; i < bdsDataCollections.size(); i++) {
                BdsDataCollections bdsData = bdsDataCollections.get(i);

                bdsDataCollection = bdsDataSurveyAdapter.getData().get(i);
                String firstName = bdsData.getFirstName();
                String lastName = bdsData.getLastName();
                String age = bdsData.getAge();
                String familyName = bdsData.getFamilyName();
                String phoneNo = bdsData.getPhoneNo();
                String childrenUnder18 = bdsData.getChildrenUnder18();
                String below16 = bdsData.getChildrenBelow16();
                String adult18Above = bdsData.getAdult18Above();
                String below16InSch = bdsData.getChildrenBelow16InSch();
                String communityName = bdsData.getCommunityName();
                String sourcesIncome = bdsData.getSourcesOfIncome();
                String mainIncome = bdsData.getMainIncome();
                String weekEarning = bdsData.getWeekEarning();
                String monthlyEarning = bdsData.getMonthlyEarning();
                String marketDay = bdsData.getMarketDays();
                String milkPerDay = bdsData.getLitresOfMilkPerDay();
                String milkConsume = bdsData.getHouseHoldConsumption();
                String milkForSale = bdsData.getMilkForSale();
                String challenges = bdsData.getMilkChallenges();
                String cowInAbuja = bdsData.getCowInAbuja();
                String totalCow = bdsData.getTotalCow();
                String milkingCow = bdsData.getMilkingCow();
                String animalFeedQuatity = bdsData.getBagsOfAnimalFeed();
                String recommendation = bdsData.getRecommendations();
                String copereativeName = bdsData.getCooperativeName();
                String feedback = bdsData.getFeedback();
                String electoralWard = bdsData.getElectoralWard();
                String areaCouncilWard = bdsData.getAreaCouncil();
                String gender = bdsData.getGender();
                String maritalStatus = bdsData.getMarital_status();
                String animalFeedInterest = bdsData.getAnimalFeedInterest();
                String farmerImageBitmap = bdsData.getImageUrl();


                if (bdsData.getImageUrl() != null) {
                    String farmerImageUri = bdsData.getImageUrl();
                    uri = Uri.parse(farmerImageUri);
                    try {
                        myBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                        Log.i("Bitmappp", String.valueOf(myBitmap));

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        myBitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                        byte[] partImage = byteArrayOutputStream.toByteArray();
                        FragmentUtils.showLoading(getActivity());
                        MediaManager.get().upload(partImage)
                                .option("resource_type", "image")
                                .unsigned(BuildConfig.CLOUDINARY_UPLOAD_PRESET)
                                .callback(new UploadCallback() {

                                    @Override
                                    public void onStart(String requestId) {
                                        Log.i("START", "STARTTTTT");
                                        FragmentUtils.showLoading(getActivity());


                                    }

                                    @Override
                                    public void onProgress(String requestId, long bytes, long totalBytes) {
                                        Double progress = (double) bytes / totalBytes;
                                        FragmentUtils.showLoading(getActivity());
                                        Log.i("PROGRESS", "PROGRESS");

                                    }

                                    @Override
                                    public void onSuccess(String requestId, Map resultData) {
                                        if (resultData != null) {
                                            Log.i("SUCCESS", "SUCCESS");
                                            FragmentUtils.hideLoading();
                                            cloudImageUrl = (String) resultData.get("url");
                                            cloudinaryId = (String) resultData.get("public_id").toString();
                                            cloudinaryImage = MediaManager.get().url().transformation(new Transformation()).resourceType("image").generate(cloudinaryId + ".jpg");
                                            Alert.showSuccess(getActivity(), "Image uploaded successfully");
                                            Log.i("imageURL", cloudImageUrl);
                                            Log.i("cloudinaryID", cloudinaryId);

                                            if (InternetConnection.getInstance(getActivity()).isOnline()) {
                                                FragmentUtils.showLoading(getActivity());
                                                BdsDataRequest.Request request = new BdsDataRequest.Request(cloudImageUrl, firstName, lastName, gender, age, maritalStatus, familyName, phoneNo, electoralWard, areaCouncilWard, communityName, copereativeName, sourcesIncome, mainIncome, weekEarning, monthlyEarning, marketDay, childrenUnder18, below16, below16InSch, adult18Above, milkPerDay, milkConsume, milkForSale, challenges, cowInAbuja, totalCow, milkingCow, animalFeedInterest, animalFeedQuatity, recommendation, feedback);
                                                   offlineSavedDataViewModel.onSubmitBdsDataCollection(request,bdsDataCollection);
                                                   FragmentUtils.showLoading(getActivity());

//                                                disposables.add(offlineSavedDataViewModel
//                                                        .submitBDSData(request)
//                                                        .subscribeOn(Schedulers.io())
//                                                        .observeOn(AndroidSchedulers.mainThread())
//                                                        .subscribe(response -> {
//                                                            FragmentUtils.hideLoading();
//                                                            Alert.showSuccess(getActivity(), response.getResponseMessage());
//                                                            offlineSavedDataViewModel.deleteBdsOnUpload(bdsDataCollection);
//                                                            if (bdsDataCollections.isEmpty()) {
//                                                                uploadBds.setVisibility(View.GONE);
//                                                            } else {
//                                                                uploadBds.setVisibility(View.VISIBLE);
//                                                            }
//
//                                                        }, throwable -> {
//                                                            FragmentUtils.hideLoading();
//                                                            onSubmitCdsDataError(throwable);
//
//
//                                                        }));

                                            } else {
                                                Alert.showFailed(getActivity(), "Please check your internet connection and try again");
                                            }


                                        }

                                    }

                                    @Override
                                    public void onError(String requestId, ErrorInfo error) {
                                        Log.i("ERROR", "ERROR");
                                        FragmentUtils.hideLoading();
                                        Alert.showFailed(getActivity(), "Error Uploading Result, Please try again later ");
                                    }

                                    @Override
                                    public void onReschedule(String requestId, ErrorInfo error) {
                                        Log.i("SCHEDULE", "SCHEDULE");
                                        FragmentUtils.hideLoading();
                                        Alert.showFailed(getActivity(), "Uploading is taking time,please take picture again");

                                    }
                                })
                                .dispatch();


                    } catch (IOException e) {
                        e.printStackTrace();
                        Alert.showFailed(getActivity(), "Image cannot be found in gallery");
                    }

                }




            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onGetCollectionUploadResponse(List<MilkCollection> milkCollections) {

    }

    @Override
    public void onSubmitCdsData(NewCollectionResponse cdsDataRequest) {
        Alert.showSuccess(getActivity(), cdsDataRequest.getResponseMessage());


    }

    @Override
    public void onSubmitCdsDataError(Throwable throwable) {
        Log.d("ERROR", "ERROR");
        FragmentUtils.hideLoading();
        try {
            if (throwable != null) {
                Log.d("NEW ERROR", "NEWERROR");
                ANError error = (ANError) throwable;
                NewCollectionResponse response = gson.fromJson(error.getErrorBody(), NewCollectionResponse.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getActivity(), response.getResponseMessage());
//                    Alert.showFailed(getActivity(), response.getMessage());
                }
            } else {
                Alert.showFailed(getActivity(), " Unable to connect to the internet");
            }
        } catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception) {
            Alert.showFailed(getActivity(), "An unknown error occurred");
        }

    }

    @Override
    public void onSubmitCollectionError(Throwable throwable) {

    }

    @Override
    public void onLoginPDSResponse(CamLoginResponse response) {
        FragmentUtils.hideLoading();
        pdsDialog.cancel();
        AlertDialog alertSuccess = new AlertDialog.Builder(getActivity()).create();
        alertSuccess.setMessage(response.getMessage() + "!." + "Continue with upload");
        alertSuccess.setCancelable(false);
        alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "UPLOAD",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        offlineSavedDataViewModel.getAllPdsDataAndUpload();
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
    public void onLoginBDSResponse(CamLoginResponse response) {
        FragmentUtils.hideLoading();
        bdsDialog.cancel();
        AlertDialog alertSuccess = new AlertDialog.Builder(getActivity()).create();
        alertSuccess.setMessage(response.getMessage() + "!." + "Continue with upload");
        alertSuccess.setCancelable(false);
        alertSuccess.setButton(AlertDialog.BUTTON_POSITIVE, "UPLOAD",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        offlineSavedDataViewModel.getAllBdsDataAndUpload();
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
    public void onLoginCollectionResponse(CamLoginResponse response) {

    }

    @Override
    public void onMilkCollectionResponse(List<MilkCollection> milkCollections) {

    }

    @Override
    public void onCdsUploadResponse(NewCollectionResponse response, CdsDataCollection cdsDataCollection) {
        FragmentUtils.hideLoading();
        Alert.showSuccess(getActivity(),response.getResponseMessage());
        offlineSavedDataViewModel.deleteDataOnUpload(cdsDataCollection);
        if (offlineSavedDataViewModel.checkifCdsIsEmpty()) {
            uploadcds.setVisibility(View.GONE);
        } else {
            uploadcds.setVisibility(View.VISIBLE);
        }




    }

    @Override
    public void onBdsUploadResponse(NewCollectionResponse response, BdsDataCollections bdsDataCollections) {
        FragmentUtils.hideLoading();
        Alert.showSuccess(getActivity(),response.getResponseMessage());
        offlineSavedDataViewModel.deleteBdsOnUpload(bdsDataCollections);
        if (offlineSavedDataViewModel.checkIfBdsIsEmpty()) {
            uploadBds.setVisibility(View.GONE);
        } else {
            uploadBds.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPdsUploadResponse(NewCollectionResponse response, PdsDataCollection pdsDataCollections) {
        FragmentUtils.hideLoading();
        Alert.showSuccess(getActivity(),response.getResponseMessage());
        offlineSavedDataViewModel.deletePdsOnUpload(pdsDataCollections);
        if (offlineSavedDataViewModel.checkIfPdsIsEmpty()) {
            uploadPds.setVisibility(View.GONE);
        } else {
            uploadPds.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMilkCollectionUploadResponse(NewCollectionResponse response, MilkCollection milkCollection) {

    }

    @Override
    public void onDataClick(int position) {
        cdsDataCollection = cdsDataSurveyAdapter.getData().get(position);
        String name = cdsDataCollection.getFirstName() + "  " + cdsDataCollection.getLastName();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = DataSurveySavedDataFragement.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.offline_delete_confirmation_modal, null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView deleteMessage = view.findViewById(R.id.deleteMessage);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        String message = "Are you sure you want to delete\n" + name + " from the  CDS data collection\n" + " list";
        Spannable spannable = new SpannableString(message);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#21523C")), message.indexOf(name), message.indexOf(name) + name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), message.indexOf(name), message.indexOf(name) + name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        deleteMessage.setText(spannable);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offlineSavedDataViewModel.deleteCdsData(cdsDataCollection);
                cdsDataSurveyAdapter.deleteMyNote(position);
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


        Log.i("Clicked", "position" + position);
        Log.i("Clicked", "position" + cdsDataCollection);


    }


    @Override
    public void onEditClick(int position) {
        cdsDataCollection = cdsDataSurveyAdapter.getData().get(position);
        Intent intent = new Intent(getActivity(), EditCdsOfflineActivity.class);
        intent.putExtra("cds_data", cdsDataCollection);
        startActivity(intent);
        Log.i("ONEditButton", "EditButton");
        Log.i("POSITION", "Click" + position);
        Log.i("OBJECTCLICKED", "ObjectClick" + cdsDataCollection);

    }

    public void addRecyclerViewDivider(RecyclerView recyclerView) {
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public void onPdsDeleteClick(int position) {
        pdsDataCollection = pdsDataSurveyAdapter.getData().get(position);
        String name = pdsDataCollection.getFirstName() + " " + pdsDataCollection.getLastName();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = DataSurveySavedDataFragement.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.offline_delete_confirmation_modal, null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView deleteMessage = view.findViewById(R.id.deleteMessage);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        String message = "Are you sure you want to delete\n" + name + " from the  PDS data collection\n" + " list";
        Spannable spannable = new SpannableString(message);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#21523C")), message.indexOf(name), message.indexOf(name) + name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), message.indexOf(name), message.indexOf(name) + name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        deleteMessage.setText(spannable);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offlineSavedDataViewModel.deletePdsData(pdsDataCollection);
                pdsDataSurveyAdapter.deletePdsData(position);
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
    public void onPdsEditClick(int position) {
        pdsDataCollection = pdsDataSurveyAdapter.getData().get(position);
        Intent intent = new Intent(getActivity(), EditPdsOfflineActivity.class);
        intent.putExtra("pds_data", pdsDataCollection);
        startActivity(intent);


    }

    @Override
    public void onBdsDeleteClick(int position) {
        bdsDataCollection = bdsDataSurveyAdapter.getData().get(position);
        String name = bdsDataCollection.getFirstName() + " " + bdsDataCollection.getLastName();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = DataSurveySavedDataFragement.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.offline_delete_confirmation_modal, null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView deleteMessage = view.findViewById(R.id.deleteMessage);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        String message = "Are you sure you want to delete\n" + name + " from then BDS data collection\n" + " list";
        Spannable spannable = new SpannableString(message);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#21523C")), message.indexOf(name), message.indexOf(name) + name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), message.indexOf(name), message.indexOf(name) + name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        deleteMessage.setText(spannable);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offlineSavedDataViewModel.deleteBdsData(bdsDataCollection);
                bdsDataSurveyAdapter.deleteBdsData(position);
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
    public void onBdsEditClick(int position) {
        bdsDataCollection = bdsDataSurveyAdapter.getData().get(position);
        Intent intent = new Intent(getActivity(), EditBdsOfflineActivity.class);
        intent.putExtra("bds_data", bdsDataCollection);
        startActivity(intent);

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
//        Log.d("ONDESTROY","DATA SURVEY FRAGMENT UNDESTROY CALLED");
//        super.onDestroy();
//            offlineSavedDataViewModel.dispose();
//
//    }


}
