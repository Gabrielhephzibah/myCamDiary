package com.enyata.camdiary.ui.collections.entervolume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityEnterVolumeBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.rejection.reason.ReasonActivity;
import com.enyata.camdiary.ui.collections.successfulcollection.SuccessfulActivity;

import javax.inject.Inject;

public class EnterVolumeActivity extends BaseActivity<ActivityEnterVolumeBinding,EnterVolumeViewModel>implements EnterVolumeNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private EnterVolumeViewModel enterVolumeViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, FarmerDetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_volume;
    }

    @Override
    public EnterVolumeViewModel getViewModel() {
        enterVolumeViewModel = ViewModelProviders.of(this,factory).get(EnterVolumeViewModel.class);
        return enterVolumeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enterVolumeViewModel.setNavigator(this);
    }

    @Override
    public void accept() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(EnterVolumeActivity.this);
        LayoutInflater inflater = EnterVolumeActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
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
                Intent success = new Intent(getApplicationContext(), SuccessfulActivity.class);
                startActivity(success);

            }
        });

    }

    @Override
    public void reject() {
        Intent intent = new Intent(getApplicationContext(),ReasonActivity.class);
        startActivity(intent);

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(),FarmerDetailsActivity.class);
        startActivity(intent);
    }
}
