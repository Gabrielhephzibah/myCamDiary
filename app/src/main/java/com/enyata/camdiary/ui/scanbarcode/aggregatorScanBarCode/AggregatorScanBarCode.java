package com.enyata.camdiary.ui.scanbarcode.aggregatorScanBarCode;

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

import com.androidnetworking.error.ANError;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.api.response.DetailsErrorMessage;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.data.remote.ApiUtils;
import com.enyata.camdiary.databinding.ActivityAggregatorScanBarCodeBinding;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
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

public class AggregatorScanBarCode extends BaseActivity<ActivityAggregatorScanBarCodeBinding, AggregatorBarcodeViewModel> implements AggregatorBarcodeNavigator, ZXingScannerView.ResultHandler {
   @Inject
    Gson gson;

   @Inject
   ViewModelProviderFactory factory;
   AggregatorBarcodeViewModel aggregatorBarcodeViewModel;
     APIService mAPIService;
    ProgressDialog progressDialog;
    OkHttpClient client;


    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView mScannerView;

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_aggregator_scan_bar_code;
    }

    @Override
    public AggregatorBarcodeViewModel getViewModel() {
        aggregatorBarcodeViewModel = ViewModelProviders.of(this,factory).get(AggregatorBarcodeViewModel.class);
        return aggregatorBarcodeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregator_scan_bar_code);
        aggregatorBarcodeViewModel.setNavigator(this);
        mScannerView = new ZXingScannerView(this);
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
        new AlertDialog.Builder(AggregatorScanBarCode.this)
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
        aggregatorBarcodeViewModel.setCollectorVerificationId(rawResult.getText());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if (InternetConnection.getInstance(this).isOnline()){
           aggregatorBarcodeViewModel.scanCollectorBarcode(aggregatorBarcodeViewModel.getCollectorVerificationId());
            mScannerView.stopCamera();

        }else {
            progressDialog.dismiss();
            Alert.showFailed(getApplicationContext(),"please check your Internet Connection and try again");
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
        aggregatorBarcodeViewModel.onDispose();



    }


    @Override
    public void handleError(Throwable throwable) {
        progressDialog.dismiss();
        Log.i("ERRORRRR","FAILEEEEEDD");
        Log.i("ERRORR", "ERROR");
        if (throwable != null ) {
            ANError error = (ANError) throwable;
            DetailsErrorMessage response = gson.fromJson(error.getErrorBody(), DetailsErrorMessage.class);
            if (error.getErrorBody()!= null){
                Alert.showFailed(getApplicationContext(), response.getError() + " please scan correct barcode");
                onResume();
            }else {
                Alert.showFailed(getApplicationContext(),"Unable to connect to the internet");
                Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
                  startActivity(intent);
            }

        }
    }

    @Override
    public void onResponse(DetailsResponse response) {
        progressDialog.dismiss();
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
                      aggregatorBarcodeViewModel.setCollectorId(String.valueOf(response.getData().getId()));
                      aggregatorBarcodeViewModel.setCollectorName(response.getData().getFirstName() + " " + response.getData().getLastName());
                      intent.putExtra("first_name", response.getData().getFirstName());
                       intent.putExtra("last_name", response.getData().getLastName());
                       intent.putExtra("phone_number", response.getData().getContactNo());
                      intent.putExtra("email", response.getData().getEmail());
                       intent.putExtra("verification_id", response.getData().getVerificationId());
                       startActivity(intent);


    }
}
