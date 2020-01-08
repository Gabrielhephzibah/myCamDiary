package com.enyata.camdiary.ui.aggregations.product;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
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


import java.util.ArrayList;

import javax.inject.Inject;

public class ProductActivity extends BaseActivity<ActivityProductBinding, ProductViewModel> implements ProductNavigator {

    String id;

    ProductAdapter productAdapter;
    ListView listView;
    ArrayList<ProductList> productLists = new ArrayList<>();

    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    private ProductViewModel productViewModel;
    ActivityProductBinding activityProductBinding;

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

        String collectorId = productViewModel.getCollectorId();
        String aggregationCollection = productViewModel.getAggregationCollection();
        productViewModel.getCollectorCollection(collectorId);
        activityProductBinding = getViewDataBinding();
        listView = activityProductBinding.listView;

        productViewModel.getCollectorCollection(id);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(ProductActivity.this);
            LayoutInflater inflater = ProductActivity.this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.aggregator_enter_volume_layout,null);
            dialog.setView(dialogView);
            dialog.setCancelable(false);

            TextView back = dialogView.findViewById(R.id.back);
            TextView accept = dialogView.findViewById(R.id.accept);
            Spinner spinner = dialogView.findViewById(R.id.spinner);

            String[] number = {"0","1","2","3","4","5","6"};

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ProductActivity.this, android.R.layout.simple_spinner_item, number);
            spinner.setAdapter(arrayAdapter);

            final AlertDialog alert = dialog.create();
            alert.show();

            back.setOnClickListener(view1 -> alert.dismiss());

            accept.setOnClickListener(view12 -> {
               LayoutInflater nextInflater = ProductActivity.this.getLayoutInflater();
               View nextDialogView = nextInflater.inflate(R.layout.confirm_entry_layout,null);
               TextView message = nextDialogView.findViewById(R.id.message);
               message.setText("You have collected 40 litres of product \nfrom Adetoyin Gabriel.\nPlease tap continue to confirm \nCollection");
               dialog.setView(nextDialogView);
               dialog.setCancelable(false);

               final AlertDialog nextAlert = dialog.create();
               nextAlert.show();

               TextView cancel = nextDialogView.findViewById(R.id.cancel);
               TextView continuee = nextDialogView.findViewById(R.id.continuee);

               cancel.setOnClickListener(view121 -> nextAlert.dismiss());

               continuee.setOnClickListener(view1212 -> {
                   LayoutInflater thirdInflater = ProductActivity.this.getLayoutInflater();
                   View thirdDialogView = thirdInflater.inflate(R.layout.aggregator_confirm_successful_layout,null);
                   dialog.setView(thirdDialogView);
                   dialog.setCancelable(false);
                   AlertDialog thirdAlert = dialog.create();
                   thirdAlert.show();

                   TextView back1 = thirdDialogView.findViewById(R.id.back);
                   TextView next = thirdDialogView.findViewById(R.id.next);

                   back1.setOnClickListener(view12121 -> thirdAlert.dismiss());

                   next.setOnClickListener(view121212 -> {
                      Intent intent = new Intent(getApplicationContext(),ProductActivity.class);
                      startActivity(intent);
                       productViewModel.getCollectorCollection(id);
                   });

               });


            });

        });

        productViewModel.getCollections().observe(this, response -> {
            for (Collection data : response.getData()) {
                productLists.add(new ProductList(data.getFarmer().getFirstName() + "  " + data.getFarmer().getLastName(), data.getFarmer().getCooperativeName(), data.getFarmer().getVerificationId(), data.getVolume() + " litres", String.valueOf(data.getId())));
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
}
