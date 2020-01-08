package com.enyata.camdiary.ui.aggregations.product;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

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
        listView = findViewById(R.id.listView);

        String collectorId = productViewModel.getCollectorId();

        String aggregationCollection = productViewModel.getAggregationCollection();

        productViewModel.getCollectorCollection(collectorId);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            ProductList item = (ProductList) adapterView.getItemAtPosition(i);
            Alert.showInfo(getApplicationContext(),"FULL_NAME "+item.getFullName());
            Intent intent = new Intent(getApplicationContext(),VolumeActivity.class);
            intent.putExtra("collectionId",String.valueOf(item.getCollectionId()));
            intent.putExtra("fullName", item.getFullName());
            startActivity(intent);
            if(!TextUtils.isEmpty(aggregationCollection)){
                try {
                    JSONArray jsonArray = new JSONArray(aggregationCollection);
                    for (int k = 0; k < jsonArray.length(); k++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(k);
                        if(jsonObject.get("collection_id").equals(item.getCollectionId())){
                            productLists.remove(i);
                            productAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
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
