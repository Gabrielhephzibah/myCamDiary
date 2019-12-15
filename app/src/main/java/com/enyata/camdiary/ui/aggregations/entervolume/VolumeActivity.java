package com.enyata.camdiary.ui.aggregations.entervolume;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityVolumeBinding;
import com.enyata.camdiary.ui.aggregations.collection.success.CollectionSuccessActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.product.ProductActivity;
import com.enyata.camdiary.ui.aggregations.product.ProductViewModel;
import com.enyata.camdiary.ui.base.BaseActivity;

import javax.inject.Inject;

public class VolumeActivity extends BaseActivity<ActivityVolumeBinding,VolumeViewModel>implements VolumeNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private VolumeViewModel volumeViewModel;
    Spinner spinner;
    String[] number = {"","1","2","3","4","5","6"};

    public static Intent newIntent(Context context) {
        return new Intent(context, AggregatorDashboardActivity.class);
    }



    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_volume;
    }

    @Override
    public VolumeViewModel getViewModel() {
        volumeViewModel = ViewModelProviders.of(this,factory).get(VolumeViewModel.class);
        return volumeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volumeViewModel.setNavigator(this);
        spinner = findViewById(R.id.spinner);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VolumeActivity.this, android.R.layout.simple_spinner_item,number);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void accept() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(VolumeActivity.this);
        LayoutInflater inflater = VolumeActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        TextView cancel =(TextView) dialogView.findViewById(R.id.cancel);
        TextView continuee = (TextView) dialogView.findViewById(R.id.continuee);
        TextView message = (TextView) dialogView.findViewById(R.id.message);
        message.setText("You have collected 40 litres of product \nfrom Adetoyin Gabriel.\nPlease tap continue to confirm \nCollection");
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
                Intent success = new Intent(getApplicationContext(), CollectionSuccessActivity.class);
                startActivity(success);

            }
        });


    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
        startActivity(intent);
    }
}
