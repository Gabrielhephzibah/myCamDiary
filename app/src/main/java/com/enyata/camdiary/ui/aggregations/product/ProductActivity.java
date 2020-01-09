package com.enyata.camdiary.ui.aggregations.product;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.databinding.ActivityProductBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class ProductActivity extends BaseActivity<ActivityProductBinding, ProductViewModel> implements ProductNavigator, AdapterView.OnItemSelectedListener {

    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private ProductViewModel productViewModel;
    ActivityProductBinding activityProductBinding;

    private AlertDialog firstModal, secondModal, thirdModal;
    private ProductAdapter productAdapter;
    private ListView listView;
    private ArrayList<ProductList> productLists = new ArrayList<>();
    private String aggregationCollections;
    private String churno;

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

        String id = productViewModel.getCollectorId();
        productViewModel.getCollectorCollection(id);
        activityProductBinding = getViewDataBinding();
        listView = activityProductBinding.listView;

        aggregationCollections = productViewModel.getAggregationCollection();

        if (!aggregationCollections.equals("nil"))
            productViewModel.setAggregationCollection("nil");

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
            String collectionMessage = product.getMesssage();
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

                if (TextUtils.isEmpty(volume.getText().toString())) {
                    Alert.showWarning(getApplicationContext(), "Please enter volume");
                    return;
                }

                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.put("collection_id", collectionId);
                    jsonObject.put("farmer_id", farmerId);
                    jsonObject.put("collection_volume", collectionVolume);
                    jsonObject.put("collection_status", collectionStatus);
                    jsonObject.put("test_one", testOne);
                    jsonObject.put("test_two", testTwo);
                    jsonObject.put("test_three", testThree);
                    jsonObject.put("approved_container", approvedContainer);
                    jsonObject.put("message", collectionMessage);
                    jsonObject.put("aggregation_volume", volume.getText().toString());
                    jsonObject.put("aggregation_churno", churno);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                LayoutInflater nextInflater = ProductActivity.this.getLayoutInflater();
                View nextDialogView = nextInflater.inflate(R.layout.confirm_entry_layout, null);
                TextView message = nextDialogView.findViewById(R.id.message);
                message.setText(String.format("You have collected %s litres of product \nfrom %s.\nPlease tap continue to confirm \nCollection", volume.getText().toString(), farmerName));
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

                    if (productLists.size() == 1) {
                        text.setText(getString(R.string.finishText));
                    } else {
                        text.setText(getString(R.string.nextText));
                    }
                    back1.setOnClickListener(view12121 -> thirdModal.dismiss());
                    text.setOnClickListener(view121212 -> {

                        productLists.remove(position);
                        firstModal.dismiss();
                        secondModal.dismiss();
                        thirdModal.dismiss();

                        try {
                            aggregationCollections = productViewModel.getAggregationCollection();
                            if (!aggregationCollections.equals("nil")) {
                                JSONArray jsonArray = new JSONArray(aggregationCollections);
                                jsonArray.put(jsonObject);
                                productViewModel.setAggregationCollection(String.valueOf(jsonArray));
                            } else {
                                JSONArray params = new JSONArray();
                                params.put(jsonObject);
                                productViewModel.setAggregationCollection(String.valueOf(params));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (text.getText().toString().equals(getString(R.string.finishText))) {

                            JSONObject request = new JSONObject();

                            try {

                                request.put("collector_id", collectorId);
                                request.put("aggregation_collections", new JSONArray(productViewModel.getAggregationCollection()));

                                // TODO
                                //  1. Pass appropriate value to third modal
                                //  2. Add viewmodel to save aggregation and redirect to aggregation dashsboard on success

                            } catch (JSONException e) {
                                e.printStackTrace();
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        churno = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
