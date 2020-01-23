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
import com.enyata.camdiary.data.model.NewResponse;
import com.enyata.camdiary.data.model.Post;

import com.enyata.camdiary.data.model.api.request.AggregationCollection;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.request.Aggregation;


import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.SavedAggregationResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.data.remote.ApiHeader;
import com.enyata.camdiary.data.remote.ApiUtils;
import com.enyata.camdiary.databinding.ActivityProductBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.utils.Alert;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        String id = productViewModel.getCollectorId();
        productViewModel.getCollectorCollection(id);
        activityProductBinding = getViewDataBinding();
        listView = activityProductBinding.listView;
//        str = productViewModel.getAggregationCollection();

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




                        Log.d("ARRAY_LIST ",String.valueOf(new ArrayList<>(productViewModel.getAggregationCollectionList())));
                        if (text.getText().toString().equals(getString(R.string.finishText))) {
//                            productViewModel.saveAggregation(collectorId, new ArrayList<>(productViewModel.getAggregationCollectionList()));
//                                str = productViewModel.getAggregationCollection();
//                            str = productViewModel.getAggregationCollectionList();


                        Post newAggregation = new Post(collectorId,productViewModel.getAggregationCollectionList());
                        Log.i("New RESPONSEE", String.valueOf(newAggregation));
//                            sendPost(newAggregation);


//                            str = String.valueOf(new ArrayList<>(productViewModel.getAggregationCollectionList()));
//                            Gson gson = new Gson();
//                            TypeToken<AggregationSavedCollection> token = new TypeToken<AggregationSavedCollection>() {};
//                            AggregationSavedCollection aggregationSavedCollection = gson.fromJson(str, token.getType());
//                           List <AggregationSavedCollection> pb = new AggregationSavedCollection("","","","","","","","","","","");
//                            pb.add(aggregationSavedCollection);
                           // Post newAggregation = new Post(collectorId,ArrayList<AggregationSavedCollection> aggregationSavedCollection);
//                            Post newAggregation = new Post(collectorId,aggregationSavedCollection);
//                            Log.i("MESSAGESSSS", newAggregation.toString());
//                            sendPost(newAggregation);


//                             productViewModel.sendPost(collectorId,pb);
//                                pb.add(new AggregationSavedCollection(collectionId,farmerId,collectionVolume,collectionStatus,testOne,testTwo,testThree,approvedContainer,collectionMessage,volume.getText().toString(),churno));


//                                List<AggregationSavedCollection>pb = new ArrayList<AggregationSavedCollection>();
//                                productViewModel.getAggregationCollection();
//                                JSONObject request = new JSONObject();
//
//                                JSONArray jArray = request.getJSONArray("aggregation_collections");
//                                for(int i = 0; i < jArray.length(); i++){
//                                    String  collectionIdd = jArray.getJSONObject(i).getString("collection_id");
//                                    String farmerIdd = jArray.getJSONObject(i).getString("farmer_id");
//                                    String collectionVolumee = jArray.getJSONObject(i).getString("collection_statuss");
//                                    String collectionStatuss = jArray.getJSONObject(i).getString("collection_status");
//                                    String testOnee = jArray.getJSONObject(i).getString("test_one");
//                                    String testTwoo = jArray.getJSONObject(i).getString("test_two");
//                                    String testThreee = jArray.getJSONObject(i).getString("test_three");
//                                    String approvedContainerr = jArray.getJSONObject(i).getString("approved_container");
//                                    String messagee = jArray.getJSONObject(i).getString("message");
//                                    String aggreagtionVolume = jArray.getJSONObject(i).getString("aggregation_volume");
//                                    String churnNo = jArray.getJSONObject(i).getString("aggregation_churno");
//                                }


//                                Gson gson = new Gson();
//                                TypeToken<ArrayList<AggregationCollection.Request>> token = new TypeToken<ArrayList<AggregationCollection.Request>>() {
////
//
//                                };
//
//                                ArrayList<AggregationCollection.Request> pb = gson.fromJson(str, token.getType());
////
//                                pb.add(new AggregationCollection.Request(collectionId,farmerId,collectionVolume,collectionStatus,testOne,testTwo,testThree,approvedContainer,collectionMessage,volume.getText().toString(),churno));
////
//                                String st = new Gson().toJson(pb);
////
//                                Log.i("MYRESPONSEEEEE", collectionId + pb.toString());
//
//                                productViewModel.saveAggregation(collectorId,pb);


//                                String str[] = productViewModel.getAggregationCollection().split(",");
//                                List<AggregationCollection.Request> al = new ArrayList<>();
//                                al = Arrays.asList(str);
//                                for(String s: al){
//                                    request.put("aggregation_collections", s);
//                                    ArrayList<AggregationCollection.Request>collection = new ArrayList<>();
//
//                                    Log.i("MYYYRESPONSEE",collection.toString());
//                                    productViewModel.saveAggregation(collectorId,s);


//                                }
//                                request.put("aggregation_collections", new JSONArray(productViewModel.getAggregationCollection()));

//                                productViewModel.saveAggregation(collectorId,);

                            // TODO
                            //  1. Pass appropriate value to third modal
                            //  2. Add view model to save aggregation and redirect to aggregation dashboard on success

//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }


//                        if (productViewModel.checkIfAggregationCollectionIsEmpty()) {
//                            List<AggregationCollection.Request> newList = new ArrayList<>();
//                            newList.add(request);
//                            productViewModel.setAggregationCollectionList(newList);
//                        } else {
//                            List<AggregationCollection.Request> arrayList = new ArrayList<>(productViewModel.getAggregationCollectionList());
//                            arrayList.add(request);
//                            productViewModel.setAggregationCollectionList(arrayList);
//                        }
//
//
//                        Log.d("ARRAY_LIST ",String.valueOf(new ArrayList<>(productViewModel.getAggregationCollectionList())));
//                        if (text.getText().toString().equals(getString(R.string.finishText)))
//                            productViewModel.saveAggregation(collectorId, new ArrayList<>(productViewModel.getAggregationCollectionList()));
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


    public void sendPost(Post post) {


        mAPIService.savePost(productViewModel.getAccessToken(),post).enqueue(new Callback<NewResponse>() {

            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {

                if (response.isSuccessful()) {
//                    Post object = response.body();
//                    String jsonString = object.toString();

//                    showResponse(response.body().toString());
                    Log.i("SUCCESS INFORMATION", "post submitted to API." + response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Log.e("failure", "Unable to submit post to API.");
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

    public void responseMessage(SavedAggregationResponse response) {
        Log.i("RESPONSEEEE", "HAS HIT THE ENDPOINT");
    }

    @Override
    public void aggregationCollection(Aggregation aggregate) {
    }

//
//    public void responseMessage(NewCollectionResponse response) {
//        Alert.showInfo(getApplicationContext(), response.getResponseMessage());
//        Log.i("ON RESPONSEEEE", "HAS HIT THE ENDPOINT");
//
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        churno = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

