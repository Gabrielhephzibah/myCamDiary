package com.enyata.camdiary.ui.collections.history;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.enyata.camdiary.R;
import com.enyata.camdiary.ViewModelProviderFactory;
import com.enyata.camdiary.data.model.Post;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionHistory;
import com.enyata.camdiary.data.model.api.response.CollectionHistoryResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.data.remote.APIService;

import com.enyata.camdiary.databinding.ActivityHistoryBinding;
import com.enyata.camdiary.ui.base.BaseActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends BaseActivity<ActivityHistoryBinding, HistoryViewModel> implements HistoryNavigator {

    @Inject
    Gson gson;
    @Inject
    ViewModelProviderFactory factory;
    private HistoryViewModel historyViewModel;
    ImageView collectorImage;
    CFAlertDialog alert;

    ListView listView;
    ArrayList<CollectorHistoryList> collectorHistoryLists = new ArrayList<>();
    ActivityHistoryBinding activityHistoryBinding;
    RelativeLayout data;
    private APIService mAPIService;
    ArrayList<CollectionItemInterface> collectionList = new ArrayList<CollectionItemInterface>();

    public static Intent newIntent(Context context) {
        return new Intent(context, HistoryActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return com.enyata.camdiary.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public HistoryViewModel getViewModel() {
        historyViewModel = ViewModelProviders.of(this, factory).get(HistoryViewModel.class);
        return historyViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyViewModel.setNavigator(this);
        activityHistoryBinding = getViewDataBinding();
        data = activityHistoryBinding.data;
        collectorImage = findViewById(R.id.collectorImage);
        String imageUrl = historyViewModel.getCollectorImage();

        Picasso.get().load(imageUrl).into(collectorImage);

        listView = activityHistoryBinding.listView;
        TextView collectorName = activityHistoryBinding.collectorName;
        collectorName.setText("Hey" + "," + historyViewModel.getCuurentUser());

        if (InternetConnection.getInstance(this).isOnline()) {
            historyViewModel.getCollectionHistory();
        } else {
            Alert.showFailed(getApplicationContext(), "Please Check Your Internet Connection and try again later");
        }


    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            VolumeResponse response = gson.fromJson(error.getErrorBody(), VolumeResponse.class);
            if (error.getErrorBody() != null) {
                Alert.showFailed(getApplicationContext(), response.getResponseMessage());
            } else {
                Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
            }

        }

    }

    @Override
    public void dataCollection() {
        Intent intent = new Intent(getApplicationContext(), DataCollectionActivity.class);
        startActivity(intent);

    }

    @Override
    public void scan() {
        Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
        startActivity(intent);

    }

    @Override
    public void back() {
        Intent back = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(back);

    }

    @Override
    public void logout() {
        CFAlertDialog.Builder alertDialog = new CFAlertDialog.Builder(this);
        LayoutInflater inflater = HistoryActivity.this.getLayoutInflater();
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
    public void getCollectionHistory(CollectionHistoryResponse response) {
        for (CollectionHistory history : response.getData()) {
            String date = history.getDate();
            collectionList.add(new CollectorHistoryHeader(date));
            List<Collection> collectionHistory = history.getCollectionHistory();
            for (int i = 0; i < collectionHistory.size(); i++) {
                Collection data = collectionHistory.get(i);
                String firstName = data.getFarmer().getFirstName();
                String lastName = data.getFarmer().getLastName();
                String litres = String.valueOf(data.getVolume());
                String statusOfCollection = data.getStatusOfCollection();
                String verificationNumber = data.getFarmer().getVerificationId();
                String companyName = data.getFarmer().getCooperativeName();
                collectionList.add(new CollectorHistory(firstName + " " + lastName, companyName, verificationNumber, statusOfCollection, litres + " litres"));
                CollectionHistoryCustomAdapter adapter = new CollectionHistoryCustomAdapter(HistoryActivity.this, collectionList);
                listView.setAdapter(adapter);

            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        historyViewModel.dispose();
    }

}
