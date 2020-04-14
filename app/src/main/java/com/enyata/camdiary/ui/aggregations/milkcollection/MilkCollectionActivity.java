package com.enyata.camdiary.ui.aggregations.milkcollection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityMilkCollectionBinding;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.utils.Alert;

import java.util.zip.Inflater;

import javax.inject.Inject;

public class MilkCollectionActivity extends BaseActivity<ActivityMilkCollectionBinding,MilkCollectionViewModel>implements MilkCollectionNavigator {

    @Inject
    ViewModelProviderFactory factory;
    MilkCollectionViewModel milkCollectionViewModel;
    AlertDialog.Builder dialog;
    AlertDialog alert1, alert2, alert3;
    View view1, view2, view3;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_milk_collection;
    }

    @Override
    public MilkCollectionViewModel getViewModel() {

        milkCollectionViewModel = ViewModelProviders.of(this,factory).get(MilkCollectionViewModel.class);
        return milkCollectionViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        milkCollectionViewModel.setNavigator(this);
    }



    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), CollectorDetailActivity.class);
        startActivity(intent);

    }

    @Override
    public void onProceed() {

        dialog = new AlertDialog.Builder(MilkCollectionActivity.this);
        LayoutInflater inflater =  MilkCollectionActivity.this.getLayoutInflater();
        view1 = inflater.inflate(R.layout.aggregator_volume_layout,null);
        dialog.setView(view1);
        dialog.setCancelable(false);
        alert1 = dialog.create();
        alert1.show();

        TextView back = view1.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert1.dismiss();
            }
        });
        TextView accept = view1.findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater secondInflater =  MilkCollectionActivity.this.getLayoutInflater();
                view2 = secondInflater.inflate(R.layout.confirm_entry_layout,null);
                dialog.setView(view2);
                TextView cancel = view2.findViewById(R.id.cancel);
                TextView continuee = view2.findViewById(R.id.continuee);
                alert2 = dialog.create();
                alert2.show();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                    }
                });

                continuee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutInflater thirdInflater = MilkCollectionActivity.this.getLayoutInflater();
                        view3 = thirdInflater.inflate(R.layout.aggregator_confirm_successful_layout,null);
                        dialog.setView(view3);
                        TextView back1 = view3.findViewById(R.id.back);
                        TextView text = view3.findViewById(R.id.next);
                        back1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alert3.dismiss();
                            }
                        });

                        text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dismissAllmodal();
                                Intent intent = new Intent( getApplicationContext(), AggregatorDashboardActivity.class);
                                startActivity(intent);
                            }
                        });
                        alert3 = dialog.create();
                        alert3.show();
                    }
                });



            }
        });


    }

    public void dismissAllmodal(){
        alert1.dismiss();
        alert2.dismiss();
        alert3.dismiss();
    }
}
