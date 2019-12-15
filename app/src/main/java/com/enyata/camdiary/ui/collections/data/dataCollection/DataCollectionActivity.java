package com.enyata.camdiary.ui.collections.data.dataCollection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDataCollectionBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.dataSubmission.SubmissionActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;

import javax.inject.Inject;

public class DataCollectionActivity extends BaseActivity<ActivityDataCollectionBinding,DataCollectionViewModel>implements DataCollectionNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private DataCollectionViewModel dataCollectionViewModel;
    ImageView history;
    ImageView scanbarcode;

    public static Intent newIntent(Context context) {
        return new Intent(context, DataCollectionActivity.class);
    }


    @Override
    public int getBindingVariable() {

        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_collection;
    }

    @Override
    public DataCollectionViewModel getViewModel() {
        dataCollectionViewModel = ViewModelProviders.of(this,factory).get(DataCollectionViewModel.class);
        return dataCollectionViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataCollectionViewModel.setNavigator(this);
        history= findViewById(R.id.history);
        scanbarcode = findViewById(R.id.scanbarcode);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HistoryActivity.class);
                startActivity(intent);
            }
        });


        scanbarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scan = new Intent(getApplicationContext(), BarcodeActivity.class);
                startActivity(scan);
            }
        });

    }

    @Override
    public void submit() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(DataCollectionActivity.this);
        LayoutInflater inflater = DataCollectionActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_successful_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        TextView cancel =(TextView) dialogView.findViewById(R.id.cancel);
        TextView continuee = (TextView) dialogView.findViewById(R.id.continuee);
        final AlertDialog alertDialog =dialog.create();
        alertDialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        continuee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent success = new Intent(getApplicationContext(), SubmissionActivity.class);
                startActivity(success);

            }
        });



    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }


}
