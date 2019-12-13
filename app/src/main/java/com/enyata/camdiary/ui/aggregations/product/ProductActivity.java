package com.enyata.camdiary.ui.aggregations.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityProductBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardViewModel;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailViewModel;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorAdapter;
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class ProductActivity extends BaseActivity<ActivityProductBinding,ProductViewModel>implements ProductNavigator {

    ProductAdapter productAdapter;
    ListView listView;
    ArrayList<ProductList> productLists = new ArrayList<>();


    @Inject
    ViewModelProviderFactory factory;
    private ProductViewModel productViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    public ProductViewModel getViewModel() {
        productViewModel = ViewModelProviders.of(this,factory).get(ProductViewModel.class);
        return productViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productViewModel.setNavigator(this);
        listView = findViewById(R.id.listView);


        JSONObject collector1 = new JSONObject();
        try {
            collector1.put("fullName", "Akin, Solomon");
            collector1.put("companyName", "Xamsatde");
            collector1.put("companyId", "X3478JND8992");
            collector1.put("myLitres", "40 litres");

        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject collector2 = new JSONObject();
        try {
            collector2.put("fullName", "Akin, Solomon");
            collector2.put("companyName", "Xamsatde");
            collector2.put("companyId", "X3478JND8992");
            collector2.put("myLitres", "40 litres");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject collector3 = new JSONObject();
        try {
            collector3.put("fullName", "Akin, Solomon");
            collector3.put("companyName", "Xamsatde");
            collector3.put("companyId", "X3478JND8992");
            collector3.put("myLitres", "40 litres");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray array = new JSONArray();
        array.put(collector1);
        array.put(collector2);
        array.put(collector3);
        array.put(collector2);
        array.put(collector1);
        array.put(collector3);


        for (int i = 0; i < array.length(); i++) {

            try {
                Log.i("message", array.toString());

                JSONObject object = array.getJSONObject(i);
                String fullName = object.getString("fullName");
                String companyName = object.getString("companyName");
                String companyId= object.getString("companyId");
                String myLitres = object.getString("myLitres");



                productLists.add(new ProductList(fullName,companyName,companyId,myLitres));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }


        productAdapter = new ProductAdapter(ProductActivity.this, productLists);
        listView.setAdapter(productAdapter);





    }

    @Override
    public void product() {
        Intent intent = new Intent(getApplicationContext(), VolumeActivity.class);
        startActivity(intent);
    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
        startActivity(intent);
    }
}
