package com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DetailsErrorMessage;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.FarmerIdResponse;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.data.remote.ApiUtils;
import com.enyata.camdiary.databinding.ActivityCollectorScanBarCodeBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.Result;

import java.io.IOException;

import javax.inject.Inject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;

public class CollectorScanBarCode extends BaseActivity<ActivityCollectorScanBarCodeBinding,CollectorBarcodeViewModel> implements CollectorBarcodeNavigator,ZXingScannerView.ResultHandler  {



    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;

    private static final int REQUEST_CAMERA = 1;

    private ZXingScannerView mScannerView;
    private APIService mAPIService;
    AlertDialog alert1;
    String farmerVerificationId;
    private  ProgressDialog progressDialog;
    OkHttpClient client;
    ProgressBar progressBar;

    private  CollectorBarcodeViewModel collectorBarcodeViewModel;

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collector_scan_bar_code;
    }

    @Override
    public CollectorBarcodeViewModel getViewModel() {
        collectorBarcodeViewModel = ViewModelProviders.of(this, factory).get(CollectorBarcodeViewModel.class);
        return collectorBarcodeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        collectorBarcodeViewModel.setNavigator(this);
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
         new AlertDialog.Builder(CollectorScanBarCode.this)
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
        collectorBarcodeViewModel.setfarmerVerificationId(rawResult.getText());
        Log.i("FarmerId", collectorBarcodeViewModel.getFarmerVerificationId());
        farmerVerificationId = collectorBarcodeViewModel.getFarmerVerificationId();
        Log.i("FARMERRRRRR", collectorBarcodeViewModel.getFarmerVerificationId());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if (InternetConnection.getInstance(this).isOnline()){

            collectorBarcodeViewModel.scanCollectorBarCode(collectorBarcodeViewModel.getFarmerVerificationId());

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
    protected void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
        collectorBarcodeViewModel.onDispose();



    }


    @Override
    public void handleError(Throwable throwable) {
        progressDialog.dismiss();
        Log.i("ERRORR", "ERROR");
        if (throwable != null ) {
            ANError error = (ANError) throwable;
            DetailsErrorMessage response = gson.fromJson(error.getErrorBody(), DetailsErrorMessage.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getError()+ " please scan correct barcode");
                onResume();
            }else {
                Alert.showFailed(getApplicationContext(),"Error Performing Operation, please try again later ");
                Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
                startActivity(intent);
            }

        }

    }

    @Override
    public void onResponse(DetailsResponse response) {
        progressDialog.dismiss();
        collectorBarcodeViewModel.setFarmerId(String.valueOf(response.getData().getId()));
                    Intent intent = new Intent(getApplicationContext(), FarmerDetailsActivity.class);
                    intent.putExtra("first_name", response.getData().getFirstName());
                    intent.putExtra("last_name", response.getData().getLastName());
                    intent.putExtra("phone_no", response.getData().getContactNo());
                    intent.putExtra("coperate_name", response.getData().getCooperativeName());
                    intent.putExtra("farmer_id", response.getData().getVerificationId());
                    startActivity(intent);

    }



}
