package com.enyata.camdiary.ui.scanbarcode.dataCollectorScanBarcode;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.Details;
import com.enyata.camdiary.data.model.api.response.DetailsErrorMessage;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.ResponseMessage;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.databinding.ActivityDataCollectorBarcodeBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.data.dataFarmerDetail.DataFarmerDetailActivity;
import com.enyata.camdiary.ui.collections.data.dataScanBarcode.DataScanCodeActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode.CollectorScanBarCode;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.Result;

import javax.inject.Inject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.OkHttpClient;

import static android.Manifest.permission.CAMERA;

public class DataCollectorBarcodeActivity extends BaseActivity<ActivityDataCollectorBarcodeBinding,DataCollectorBarcodeViewModel>implements DataCollectorBarcodeNavigator,ZXingScannerView.ResultHandler {
    @Inject
    ViewModelProviderFactory factory;

    @Inject
    Gson gson;
    private static final int REQUEST_CAMERA = 1;

    private ZXingScannerView mScannerView;
    private APIService mAPIService;
    AlertDialog alert1;
    String farmerVerificationId;
    private ProgressDialog progressDialog;
    OkHttpClient client;
    ProgressBar progressBar;


    DataCollectorBarcodeViewModel dataCollectorBarcodeViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_collector_barcode;
    }

    @Override
    public DataCollectorBarcodeViewModel getViewModel() {
        dataCollectorBarcodeViewModel = ViewModelProviders.of(this,factory).get(DataCollectorBarcodeViewModel.class);
        return dataCollectorBarcodeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        dataCollectorBarcodeViewModel.setNavigator(this);
        setContentView(mScannerView);



        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
            } else {
                requestPermission();
            }
        }
    }



    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);


    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Alert.showSuccess(getApplicationContext(), "Permission Granted, Now you can access camera");
                    }else {
                        Alert.showSuccess(getApplicationContext(), "Permission Denied, You cannot access camera");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }


                    }

                }
                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(DataCollectorBarcodeActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    public void handleResult(Result rawResult) {
        final String result = rawResult.getText();
        Log.d("QRCodeScanner", rawResult.getText());
        Log.d("QRCodeScanner", rawResult.getBarcodeFormat().toString());
        dataCollectorBarcodeViewModel.setfarmerVerificationId(rawResult.getText());
        Log.i("FarmerId", dataCollectorBarcodeViewModel.getFarmerVerificationId());
        farmerVerificationId = dataCollectorBarcodeViewModel.getFarmerVerificationId();
        Log.i("FARMERRRRRR", dataCollectorBarcodeViewModel.getFarmerVerificationId());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if (InternetConnection.getInstance(this).isOnline()){

            dataCollectorBarcodeViewModel.scanCollectorBarCode(dataCollectorBarcodeViewModel.getFarmerVerificationId());

        }else {
            progressDialog.dismiss();
            Alert.showFailed(getApplicationContext(),"Please check your internet connection and try again");
            onResume();

        }

    }


    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M){
            if (checkPermission()){
                if (mScannerView == null){
                    mScannerView = new ZXingScannerView(this);
                    setContentView(mScannerView);
                }
                mScannerView.setResultHandler(this);
                mScannerView.startCamera();
            }else {
                requestPermission();
            }
        }


    }


    @Override
    public void handleError(Throwable throwable) {
        progressDialog.dismiss();
        Log.i("ERRORR", "ERROR");
        try {


            if (throwable != null) {
                ANError error = (ANError) throwable;
                ResponseMessage response = gson.fromJson(error.getErrorBody(), ResponseMessage.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getApplicationContext(), response.getMessage() + ", " + "Please enter a valid Id");
                    onResume();
                } else {
                    Alert.showFailed(getApplicationContext(), "Error Performing Operation, please try again later ");
                    Intent intent = new Intent(getApplicationContext(), DataScanCodeActivity.class);
                    startActivity(intent);
                }

            }

        }catch (NullPointerException | ClassCastException | JsonSyntaxException | IllegalStateException exception){
            Alert.showFailed(getApplicationContext(),"An unknown error occurred");
        }

    }

    @Override
    public void onResponse(DetailsResponse response) {
        progressDialog.dismiss();
        dataCollectorBarcodeViewModel.setFarmerId(String.valueOf(response.getData().getId()));
        Intent intent = new Intent(getApplicationContext(), DataFarmerDetailActivity.class);
        dataCollectorBarcodeViewModel.setFarmerFullName(response.getData().getFirstName() + " " +response.getData().getLastName());
        dataCollectorBarcodeViewModel.setFarmerCoperative(response.getData().getCooperativeName());
        dataCollectorBarcodeViewModel.setFarmerPhoneNo(response.getData().getContactNo());
        dataCollectorBarcodeViewModel.setFarmerVerificationId(response.getData().getVerificationId());
        startActivity(intent);

    }

    }


