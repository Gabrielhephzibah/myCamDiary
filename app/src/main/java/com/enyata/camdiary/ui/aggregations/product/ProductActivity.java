package com.enyata.camdiary.ui.aggregations.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;

import com.enyata.camdiary.data.model.AggregationSavedCollection;
import com.enyata.camdiary.data.model.Post;

import com.enyata.camdiary.data.model.api.response.Collection;


import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.data.remote.ApiUtils;
import com.enyata.camdiary.databinding.ActivityProductBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class ProductActivity extends BaseActivity<ActivityProductBinding, ProductViewModel> implements ProductNavigator, AdapterView.OnItemSelectedListener {
    String str;

    private APIService mAPIService;


    @Inject
    Gson gson;


    @Inject
    ViewModelProviderFactory factory;
    ActivityProductBinding activityProductBinding;

    private ProductViewModel productViewModel;
    private AlertDialog firstModal, secondModal, thirdModal;
    private ProductAdapter productAdapter;
    private ListView listView;
    private ArrayList<ProductList> productLists = new ArrayList<>();
    private String churno;
    OkHttpClient client;


    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    public ProductViewModel getViewModel() {
        productViewModel = ViewModelProviders.of(this, factory).get(ProductViewModel.class);
        return productViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productViewModel.setNavigator(this);

        mAPIService = ApiUtils.getAPIService();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Log.i("COLLECTORID", productViewModel.getCollectorId());

        String id = productViewModel.getCollectorId();

        productViewModel.getCollectorCollection(id);
        activityProductBinding = getViewDataBinding();
        listView = activityProductBinding.listView;

        if (!productViewModel.checkIfAggregationCollectionIsEmpty()){
            productViewModel.setAggregationCollectionList(null);
        }


        listView.setOnItemClickListener((adapterView, view, position, l) -> {

            ProductList product = productLists.get(position);
            String farmerId = product.getFarmerId();
            String collectionStatus = product.getCollectionStatus();
            String collectionVolume = product.getCollectionVolume();
            String testOne = product.getTestOne();
            String testTwo = product.getTestTwo();
            String testThree = product.getTestThree();
            String farmerName = product.getFullName();
            String approvedContainer = product.getApprovedContainer();
            String collectionMessage = product.getMessage();
            String collectionId = product.getCollectionId();
            String collectorId = product.getCollectorId();

            final AlertDialog.Builder dialog = new AlertDialog.Builder(ProductActivity.this);
            LayoutInflater inflater = ProductActivity.this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.aggregator_enter_volume_layout, null);
            dialog.setView(dialogView);
            dialog.setCancelable(false);

            TextView back = dialogView.findViewById(R.id.back);
            TextView accept = dialogView.findViewById(R.id.accept);
            Spinner spinner = dialogView.findViewById(R.id.spinner);

            String[] number = {"0", "1", "2", "3", "4", "5", "6"};

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ProductActivity.this, android.R.layout.simple_spinner_item, number);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(this);
            EditText volume = dialogView.findViewById(R.id.aggregatorVolume);

            firstModal = dialog.create();
            firstModal.show();

            back.setOnClickListener(view1 -> firstModal.dismiss());

            accept.setOnClickListener(view12 -> {

                if (productViewModel.isVolumeEmpty(volume.getText().toString())) {
                    Alert.showWarning(getApplicationContext(), "Please enter volume");
                    return;
                }
                AggregationSavedCollection collection = new AggregationSavedCollection(collectionId,farmerId,collectionVolume,collectionStatus,testOne,testTwo,testThree,approvedContainer,collectionMessage,volume.getText().toString(),churno);



                LayoutInflater nextInflater = ProductActivity.this.getLayoutInflater();
                View nextDialogView = nextInflater.inflate(R.layout.confirm_entry_layout, null);
                TextView message = nextDialogView.findViewById(R.id.message);
                message.setText(String.format("You have collected %s litres of product \nfrom %s.\nPlease tap continue to confirm \nCollection", volume.getText().toString(), productViewModel.getCollectorName()));
                dialog.setView(nextDialogView);
                dialog.setCancelable(false);

                secondModal = dialog.create();
                secondModal.show();

                TextView cancel = nextDialogView.findViewById(R.id.cancel);
                TextView continuee = nextDialogView.findViewById(R.id.continuee);

                cancel.setOnClickListener(view121 -> secondModal.dismiss());

                continuee.setOnClickListener(view1212 -> {
                    LayoutInflater thirdInflater = ProductActivity.this.getLayoutInflater();
                    View thirdDialogView = thirdInflater.inflate(R.layout.aggregator_confirm_successful_layout, null);
                    dialog.setView(thirdDialogView);
                    dialog.setCancelable(false);
                    thirdModal = dialog.create();
                    thirdModal.show();

                    TextView back1 = thirdDialogView.findViewById(R.id.back);
                    TextView text = thirdDialogView.findViewById(R.id.next);
                    TextView collectorName = thirdDialogView.findViewById(R.id.collectorName);
                    TextView farmerNamee = thirdDialogView.findViewById(R.id.farmerName);
                    TextView volumee = thirdDialogView.findViewById(R.id.volume);

                    collectorName.setText(productViewModel.getCollectorName());
                    farmerNamee.setText(farmerName);
                    volumee.setText(volume.getText().toString());

                    if (productLists.size() == 1) {
                        text.setText(getString(R.string.finishText));
                    } else {
                        text.setText(getString(R.string.nextText));
                    }
                    back1.setOnClickListener(view12121 -> thirdModal.dismiss());
                    text.setOnClickListener(view121212 -> {

                        productLists.remove(position);
                        productAdapter.notifyDataSetChanged();
                        dismissAllModal();
                        if (productViewModel.checkIfAggregationCollectionIsEmpty()) {
                            List<AggregationSavedCollection> newList = new ArrayList<>();
                            newList.add(collection);
                            productViewModel.setAggregationCollectionList(newList);
                        } else {
                            List<AggregationSavedCollection> arrayList = new ArrayList<>(productViewModel.getAggregationCollectionList());
                            arrayList.add(collection);
                            productViewModel.setAggregationCollectionList(arrayList);
                        }
                        if (text.getText().toString().equals(getString(R.string.finishText))) {
                        Post newAggregation = new Post(collectorId,productViewModel.getAggregationCollectionList());
                        if (InternetConnection.getInstance(this).isOnline()){
                            productViewModel.sendPost(newAggregation);
                        } else {
                            Alert.showFailed(getApplicationContext(),"Please Check your Internet Connection and try again");
                        }


                        }

                    });

                });

            });

        });

        productViewModel.getCollections().observe(this, response -> {
            for (Collection data : response.getData()) {
                productLists.add(new ProductList(data.getFarmer().getFirstName() + "  " + data.getFarmer().getLastName(), data.getFarmer().getCooperativeName(), data.getFarmer().getVerificationId(), data.getVolume() + " litres", String.valueOf(data.getId()), String.valueOf(data.getFarmerId()), data.getStatusOfCollection(), String.valueOf(data.getVolume()), data.getTestOne(), data.getTestTwo(), data.getTestThree(), Boolean.toString(data.getApprovedContainer()), data.getMessage(), String.valueOf(data.getCollectorId())));
                productAdapter = new ProductAdapter(ProductActivity.this, productLists);
                listView.setAdapter(productAdapter);
            }
        });

    }

    public void dismissAllModal() {
        firstModal.dismiss();
        secondModal.dismiss();
        thirdModal.dismiss();
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            VolumeResponse response = gson.fromJson(error.getErrorBody(), VolumeResponse.class);
            Alert.showFailed(getApplicationContext(), response.getResponseMessage());
        }
    }

    @Override
    public void product() {
        Intent intent = new Intent(getApplicationContext(), VolumeActivity.class);
        startActivity(intent);
    }

    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    public void getCollectorCollection(CollectionResponse response) {
        productViewModel.setCollections(response);
    }

    @Override
    public void onResponse() {
        Alert.showSuccess(getApplicationContext(), "Aggregation Successful");
        Intent intent = new Intent(getApplicationContext(), AggregatorDashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFailed(Throwable throwable) {
        Alert.showFailed(getApplicationContext(),"Request not Successful, please Check Your Internet and try again");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        churno = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        productViewModel.dispose();
        client.dispatcher().cancelAll();


    }
}

