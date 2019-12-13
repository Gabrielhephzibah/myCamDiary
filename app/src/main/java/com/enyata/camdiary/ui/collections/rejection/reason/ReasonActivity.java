package com.enyata.camdiary.ui.collections.rejection.reason;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityReasonBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.rejection.rejectsuccess.RejectsuccessActivity;

import javax.inject.Inject;

public class ReasonActivity extends BaseActivity<ActivityReasonBinding,ReasonViewModel>implements ReasonNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private ReasonViewModel reasonViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, ReasonActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reason;
    }

    @Override
    public ReasonViewModel getViewModel() {
        reasonViewModel = ViewModelProviders.of(this,factory).get(ReasonViewModel.class);
        return reasonViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reasonViewModel.setNavigator(this);

    }

    @Override
    public void submit() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(ReasonActivity.this);
        LayoutInflater inflater = ReasonActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        TextView entry = (TextView) dialogView.findViewById(R.id.entry);
        TextView message = (TextView) dialogView.findViewById(R.id.message);
        entry.setText("Rejection Accepted");
        message.setText("You have rejected 40 litres of product \nfrom Akin Solomon.\nPlease tap continue to confirm \nrejection");

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
                Intent intent = new Intent(getApplicationContext(), RejectsuccessActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), EnterVolumeActivity.class);
        startActivity(intent);
    }
}
