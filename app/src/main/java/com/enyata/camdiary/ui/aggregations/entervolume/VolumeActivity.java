package com.enyata.camdiary.ui.aggregations.entervolume;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class VolumeActivity extends BaseActivity<ActivityVolumeBinding, VolumeViewModel> implements VolumeNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private VolumeViewModel volumeViewModel;
    Spinner spinner;

    TextView volume;
    String churno;
    String fullname;
    String collectionId;
    String[] number = {"","1","2","3","4","5","6"};
    ActivityVolumeBinding activityVolumeBinding;

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
        volumeViewModel = ViewModelProviders.of(this, factory).get(VolumeViewModel.class);
        return volumeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volumeViewModel.setNavigator(this);

        volume = findViewById(R.id.volume);
        collectionId = getIntent().getStringExtra("collectionId");
        fullname = getIntent().getStringExtra("fullName");
        activityVolumeBinding = getViewDataBinding();
        spinner = activityVolumeBinding.spinner;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VolumeActivity.this, android.R.layout.simple_spinner_item, number);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void accept() {
        churno = spinner.getSelectedItem().toString();
        if (TextUtils.isEmpty(volume.getText().toString()) || TextUtils.isEmpty(churno)) {
            Log.d("ACCEPT ","VOLUME "+volume.getText().toString() + " CHURNO_NO "+churno);
            return;
        }

        final AlertDialog.Builder dialog = new AlertDialog.Builder(VolumeActivity.this);
        LayoutInflater inflater = VolumeActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_entry_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        TextView cancel = dialogView.findViewById(R.id.cancel);
        TextView continuee = dialogView.findViewById(R.id.continuee);
        TextView message = dialogView.findViewById(R.id.message);
        message.setText(String.format("You have collected %s litres of product \n%s.\nPlease tap continue to confirm \nCollection", volume.getText().toString(), fullname));
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        cancel.setOnClickListener(v -> alertDialog.dismiss());

        continuee.setOnClickListener(v -> {

            JSONObject params = new JSONObject();
            try {
                params.put("collector_id", volumeViewModel.getCollectorId());
                params.put("collection_id", collectionId);
                params.put("volume", volume.getText().toString());
                params.put("churno", churno);

                Intent intent = new Intent(getApplicationContext(), CollectionSuccessActivity.class);
                intent.putExtra("farmer",volumeViewModel.getCollectorName());
                intent.putExtra("collector", fullname);
                intent.putExtra("volume",volume.getText().toString());
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
        startActivity(intent);
    }
}
