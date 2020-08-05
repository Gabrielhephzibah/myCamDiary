package com.enyata.camdiary.ui.aggregations.collectorCollection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.request.NewAggregationRequest;
import com.enyata.camdiary.data.model.api.response.ChurnData;
import com.enyata.camdiary.data.model.api.response.ChurnDetails;
import com.enyata.camdiary.data.model.api.response.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.response.CollectorCollectionResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.ResponseMessage;
import com.enyata.camdiary.databinding.ActivityCollectorColletionBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveySavedData.DataSurveySavedDataFragement;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import javax.inject.Inject;

public class CollectorCollectionActivity extends BaseActivity<ActivityCollectorColletionBinding, CollectorCollectionViewModel>implements CollectorCollectionNavigator, CollectorCollectionAdapter.onListItem {
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    Gson gson;
   private ActivityCollectorColletionBinding activityCollectorColletionBinding;
    private CollectorCollectionViewModel collectorCollectionViewModel;
    private RecyclerView mRecyclerView;
    String collectorVerificationId;
    private CollectorCollectionAdapter collectorCollectionAdapter;
    private List<CollectorCollectionListItem>collectionListItems = new ArrayList<>();
     CollectorCollectionListItem listItem;
    AlertDialog alertOne, alertTwo,alertThree,alertFour;
    AlertDialog.Builder alertBuilder;
    String aggregatorVolume;
    String collectorName;
    String churnId;
    TextView noCollectionText;
//     ChurnDetailsAdapter churnDetailsAdapter;

    RecyclerView churnRecyclerView;



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collector_colletion;
    }

    @Override
    public CollectorCollectionViewModel getViewModel() {
        collectorCollectionViewModel = ViewModelProviders.of(this,factory).get(CollectorCollectionViewModel.class);
        return collectorCollectionViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectorCollectionViewModel.setNavigator(this);
        activityCollectorColletionBinding = getViewDataBinding();
        mRecyclerView = activityCollectorColletionBinding.collectionRecyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        addRecyclerViewDivider(mRecyclerView);
        collectorVerificationId = collectorCollectionViewModel.getCollectorVerificationId();
        collectorCollectionViewModel.getCollectorCollection(collectorVerificationId);
        collectorName = collectorCollectionViewModel.getCollectorName();
        noCollectionText = activityCollectorColletionBinding.noCollectionText;
        Log.d("NAME", collectorName);


    }

    @Override
    public void onViewMore() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this,R.style.DialogStyle);
       LayoutInflater inflater = CollectorCollectionActivity.this.getLayoutInflater();
        View view =  inflater.inflate(R.layout.view_more_layout,null);
        alertBuilder.setView(view);
        alertBuilder.setCancelable(false);
        Button button = view.findViewById(R.id.close);
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });





    }

    @Override
    public void onCollection() {
        AlertDialog.Builder alertBuilderVolume = new AlertDialog.Builder(this);
        LayoutInflater inflater = CollectorCollectionActivity.this.getLayoutInflater();
        View mview =  inflater.inflate(R.layout.aggregator_volume_layout,null);
        alertBuilderVolume.setView(mview);
        alertBuilderVolume.setCancelable(false);
        TextView back = mview.findViewById(R.id.back);
        AlertDialog dialog = alertBuilderVolume.create();
        dialog.show();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });

    }

    @Override
    public void onResponse(CollectorCollectionResponse response) {
        Log.d("RESPOSEEE","This is the reaponse"+response);
        if (response.getData().isEmpty()) {
            noCollectionText.setVisibility(View.VISIBLE);
        }else {
            try {
                for (ChurnData data : response.getData()) {
                    String churnId = data.getChurnId();
                    String volume = data.getVolume();
                    collectionListItems.add(new CollectorCollectionListItem(churnId, volume));
                    collectorCollectionAdapter = new CollectorCollectionAdapter(this, collectionListItems, this);
                    mRecyclerView.setAdapter(collectorCollectionAdapter);


                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Error", e.getMessage());
            }
        }

    }

    @Override
    public void handleError(Throwable throwable) {

        try {
            if (throwable != null) {
                ANError error = (ANError) throwable;
                ResponseMessage response = gson.fromJson(error.getErrorBody(), ResponseMessage.class);
                if (error.getErrorBody()!= null){
                    Alert.showFailed(getApplicationContext(), response.getMessage());
                }else {
                    Alert.showFailed(getApplicationContext(),"Unable to Connect to the Internet");
                }

            }
        }catch (IllegalStateException | JsonSyntaxException |NullPointerException|ClassCastException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
        }

    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onChurnDetailsResponse(ChurnDetails response) {
        List<ChurnDetailsItem>churnDetailsItems = new ArrayList<>();
        List<ChurnDetailsData> data = response.getData();
        for (int i = 0; i <data.size(); i++){
            ChurnDetailsData detailsData = data.get(i);
            String firstName = detailsData.getFarmer().getFirstName();
            String lastName = detailsData.getFarmer().getLastName();
            String verificationId = detailsData.getFarmer().getVerificationId();
            String volume = detailsData.getVolume();
            churnDetailsItems.add(new ChurnDetailsItem(firstName,lastName,verificationId,volume));
        }


        Log.d("CHURNDETAILRESPONSE", "RESPONSE"+ response);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = CollectorCollectionActivity.this.getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.view_more_layout,null);
        alertBuilder.setView(viewItem);
        alertBuilder.setCancelable(false);
        Button close = viewItem.findViewById(R.id.close);
        RecyclerView churnRecyclerView = viewItem.findViewById(R.id.churnDetailRecyclerView);
        churnRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChurnDetailsAdapter churnDetailsAdapter = new ChurnDetailsAdapter(this,churnDetailsItems);
        churnRecyclerView.setAdapter(churnDetailsAdapter);
        churnRecyclerView.setHasFixedSize(true);



        AlertDialog alert = alertBuilder.create();
        alert.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.cancel();
            }
        });

    }

    @Override
    public void onAggregationResponse(NewCollectionResponse response, int position) {
        hideLoading();
        collectorCollectionAdapter.removeCollectionData(position);
        Alert.showSuccess(getApplicationContext(),response.getMessage());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = CollectorCollectionActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.aggregator_confirm_successful_layout,null);
        TextView collectorsName = view.findViewById(R.id.collectorName);
        TextView volume = view.findViewById(R.id.volume);
        TextView next = view.findViewById(R.id.next);
        TextView back = view.findViewById(R.id.back);
        builder.setView(view);
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        collectorsName.setText(collectorName);
        volume.setText(String.format("%s Litres", aggregatorVolume));
        if (collectionListItems.size() < 1){
            next.setText("FINISH");
        }else {
            next.setText("NEXT");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (next.getText()=="FINISH"){
                    Intent intent = new Intent(getApplicationContext(), AggregatorDashboardActivity.class);
                    startActivity(intent);
                }else {
                    alertDialog.cancel();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
                Intent intent  = new Intent(getApplicationContext(), AggregatorDashboardActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onStarting() {
        showLoading();

    }

    @Override
    public void AggregationError(Throwable throwable) {
        hideLoading();
        try {
            if (throwable != null) {
                ANError error = (ANError) throwable;
                ResponseMessage response = gson.fromJson(error.getErrorBody(), ResponseMessage.class);
                if (error.getErrorBody()!= null){
                    Alert.showFailed(getApplicationContext(), response.getMessage());
                }else {
                    Alert.showFailed(getApplicationContext(),"Unable to Connect to the Internet");
                }

            }
        }catch (IllegalStateException | JsonSyntaxException |NullPointerException|ClassCastException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        collectorCollectionViewModel.dispose();
    }

    @Override
    public void onViewMore(int position) {
        listItem = collectorCollectionAdapter.getData().get(position);
        String churnId = listItem.getChurnId();
        String volume =  listItem.getVolume();
        if(InternetConnection.getInstance(this).isOnline()) {
            collectorCollectionViewModel.getChurnDetails(collectorVerificationId, churnId);
        }else {
            Alert.showFailed(getApplicationContext(),"No internet connection");
        }

        Log.d("VIEWMORECLICKED","Viewmoreposition was clicked at"+position );
        Log.d("LISTITEM", String.valueOf(listItem));



    }

    @Override
    public void onItemClick(int position) {
        listItem = collectorCollectionAdapter.getData().get(position);
        Log.d("POSITION",String.valueOf(position));
        Log.d("LIST_ITEM",String.valueOf(listItem));
        churnId = listItem.getChurnId();
        alertBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = CollectorCollectionActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.aggregator_volume_layout,null);
        alertBuilder.setView(view);
        alertBuilder.setCancelable(false);
        TextView accept = view.findViewById(R.id.accept);
        TextView reject = view.findViewById(R.id.reject);
        TextView back = view.findViewById(R.id.back);
        TextInputEditText editText = view.findViewById(R.id.aggregatorVolume);


        alertOne = alertBuilder.create();
        alertOne.show();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertOne.cancel();
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aggregatorVolume = editText.getText().toString().trim();
                if (TextUtils.isEmpty(aggregatorVolume)) {
                    Alert.showFailed(getApplicationContext(),"Enter volume");
                    return;

                } else {
                    alertOne.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(CollectorCollectionActivity.this, R.style.DialogTheme);
                    View itemView = inflater.inflate(R.layout.aggregation_rejection_layout, null);
                    alertDialog.setView(itemView);
                    EditText textArea = itemView.findViewById(R.id.textarea);
                    CheckBox checkBox = itemView.findViewById(R.id.checkbox1);

                    Button submit = itemView.findViewById(R.id.submit);
                    ImageView back = itemView.findViewById(R.id.back);
                    alertTwo = alertDialog.create();
                    alertTwo.show();

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertOne.dismiss();
                            String textarea = textArea.getText().toString();
                            if (!(checkBox.isChecked() || !textarea.isEmpty())){
                                Alert.showFailed(getApplicationContext(),"Reason for rejection cannot be empty");
                                Log.d("textarea", textarea);
                                return;

                            }else {
                                Log.d("newTextArea", textarea);
                            View rejectView = inflater.inflate(R.layout.confirm_entry_layout, null);
                            alertBuilder.setView(rejectView);
                            TextView message = rejectView.findViewById(R.id.message);
                            TextView cancel = rejectView.findViewById(R.id.cancelCollection);
                            TextView continuee = rejectView.findViewById(R.id.continuee);
                            message.setText("You have rejected " + aggregatorVolume + " litres of product \nfrom " + collectorName + ". \nPlease tap continue to confirm \nrejection");

                            alertFour = alertBuilder.create();
                            alertFour.show();

                            continuee.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String textAreaText = textArea.getText().toString();
                                    String collectorId = collectorVerificationId;
                                    String aggregationVolume = aggregatorVolume;
                                    String statusOfAggregation = "rejected";
                                    String statusOfTest;
                                    String churn = churnId;
                                    String reason;
                                    if (checkBox.isChecked()) {
                                        statusOfTest = "failed";
                                    } else {
                                        statusOfTest = "passed";
                                    }

                                    if (!textAreaText.isEmpty()) {
                                        reason = textArea.getText().toString();
                                    } else {
                                        reason = "nil";
                                    }
                                    Log.d("collectorID", collectorId);
                                    Log.d("aggregationVolume", aggregationVolume);
                                    Log.d("statusOfAggregation", statusOfAggregation);
                                    Log.d("statusOfText", statusOfTest);
                                    Log.d("reasonForRejection", reason);

                                    if (InternetConnection.getInstance(CollectorCollectionActivity.this).isOnline()) {
                                        alertFour.cancel();
                                        alertTwo.cancel();
                                        NewAggregationRequest.Request request = new NewAggregationRequest.Request(collectorId, aggregationVolume, statusOfAggregation, statusOfTest, reason, churn);
                                        collectorCollectionViewModel.createAggregation(request, position);
                                    } else {
                                        Alert.showFailed(getApplicationContext(), "No internet connection");
                                    }

                                }
                            });

                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alertFour.cancel();
                                }
                            });

                        }
                        }
                    });

                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertTwo.cancel();
                            alertOne.cancel();
                        }
                    });
                }
            }

        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aggregatorVolume = editText.getText().toString().trim();
                if (TextUtils.isEmpty(aggregatorVolume)){
                    Alert.showFailed(getApplicationContext(),"Enter volume");
                    return;
                }else {
                    alertOne.dismiss();
                    View thirdView = inflater.inflate(R.layout.confirm_entry_layout, null);
                    alertBuilder.setView(thirdView);
                    TextView message = thirdView.findViewById(R.id.message);
                    TextView cancel = thirdView.findViewById(R.id.cancelCollection);
                    TextView continuee = thirdView.findViewById(R.id.continuee);

                    message.setText("You have collected " + aggregatorVolume +  " litres of product \nfrom "+ collectorName + ". \nPlease tap continue to confirm \ncollection");

                    alertThree = alertBuilder.create();
                    alertThree.show();

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertThree.dismiss();
                        }
                    });

                    continuee.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                          String collectorId = collectorVerificationId;
                          String aggregationVolume = aggregatorVolume;
                          String statusOfAggregation = "accepted";
                          String statusOfTest = "passed";
                          String reasonForRejection = "nil";
                          String churn = churnId;
                          if (InternetConnection.getInstance(CollectorCollectionActivity.this).isOnline()){
                              alertThree.cancel();
                              NewAggregationRequest.Request request = new NewAggregationRequest.Request(collectorId,aggregationVolume,statusOfAggregation,statusOfTest,reasonForRejection,churn);
                              collectorCollectionViewModel.createAggregation(request,position);
                          }else {
                              Alert.showFailed(getApplicationContext(),"No internet connection");
                          }
                        }
                    });

                }

            }
        });


    }

    public void addRecyclerViewDivider(RecyclerView recyclerView) {
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(divider);
    }
}
