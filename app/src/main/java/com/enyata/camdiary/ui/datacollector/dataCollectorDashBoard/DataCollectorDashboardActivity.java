package com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.enyata.camdiary.BR;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.databinding.ActivityDataCollectorBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.bdsData.BdsDataActivity;
import com.enyata.camdiary.ui.collections.data.cdsData.CdsDataActivity;
import com.enyata.camdiary.ui.collections.data.dataScanBarcode.DataScanCodeActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorEditProfile.DataCollectorChangePasswordFragment;
import com.enyata.camdiary.ui.datacollector.dataCollectorEditProfile.DataCollectorEditProfileActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class DataCollectorDashboardActivity extends BaseActivity<ActivityDataCollectorBinding,DataCollectorDashboardViewModel>implements DataCollectorDashBoardNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ActivityDataCollectorBinding activityDataCollectorBinding;
    DataCollectorDashboardViewModel dataCollectorDashboardViewModel;
    ImageView dataCollectorImage;
    TextView userName;
    CardView profilePicture;
    String currentUserImage,currentUserName;
    CFAlertDialog alert;
    int backButtonPressed = 0;

    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_collector;
    }

    @Override
    public DataCollectorDashboardViewModel getViewModel() {
        dataCollectorDashboardViewModel = ViewModelProviders.of(this,factory).get(DataCollectorDashboardViewModel.class);
        return dataCollectorDashboardViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataCollectorDashboardViewModel.setNavigator(this);
        activityDataCollectorBinding = getViewDataBinding();
        dataCollectorImage = activityDataCollectorBinding.dataCollectorImage;
        userName = activityDataCollectorBinding.username;
        profilePicture = activityDataCollectorBinding.profilePicture;
        currentUserImage = dataCollectorDashboardViewModel.getCurrentUserImage();
        currentUserName = dataCollectorDashboardViewModel.getCurrentUserName();
        Picasso.get().load(currentUserImage).into(dataCollectorImage);
        userName.setText(String.format("Hey, %s ", dataCollectorDashboardViewModel.getCurrentUserName()));
        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DataCollectorEditProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCds() {
        Intent intent = new Intent(getApplicationContext(), CdsDataActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBds() {
        Intent intent = new Intent(getApplicationContext(), BdsDataActivity.class);
        startActivity(intent);

    }

    @Override
    public void onPds() {
        Intent intent = new Intent(getApplicationContext(), DataScanCodeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onLogout() {
        CFAlertDialog.Builder alertDialog = new CFAlertDialog.Builder(this);
        LayoutInflater inflater = DataCollectorDashboardActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout_notification_sheet,null);
        alertDialog .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)
                .setCancelable(false)
                .setHeaderView(dialogView);
        Button yes = dialogView.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Button no = dialogView.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        alert = alertDialog.create();
        alert.show();
    }


    @Override
    public void onBackPressed() {
        if (backButtonPressed >= 2){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Press the back button twice to close the application.", Toast.LENGTH_SHORT).show();
            backButtonPressed++;
        }


    }
}
