package com.enyata.camdiary.ui.offlinecollection.offlineDashBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.bdsoffline.BdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.cdsoffline.CdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.pdsoffline.PdsOfflineActivity;

public class OfflineDataCollectionFragment extends Fragment {
    Button cds,bds,pds;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offline_data_collection_layout,container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cds = view.findViewById(R.id.cds);
        bds = view.findViewById(R.id.bds);
        pds = view.findViewById(R.id.pds);

        cds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CdsOfflineActivity.class);
                startActivity(intent);
            }
        });

        bds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BdsOfflineActivity.class);
                startActivity(intent);
            }
        });

        pds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PdsOfflineActivity.class);
                startActivity(intent);
            }
        });





    }
}
