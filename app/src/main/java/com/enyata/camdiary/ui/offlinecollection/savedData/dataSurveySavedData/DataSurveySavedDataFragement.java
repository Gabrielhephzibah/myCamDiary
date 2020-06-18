package com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveySavedData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.enyata.camdiary.R;


public class DataSurveySavedDataFragement extends Fragment {
    RelativeLayout openCds, openBds, openPds;
    LinearLayout cdsFragment, bdsFragment, pdsFragment;
    ImageView cdsToggleIcon, bdsToggleIcon, pdsToggleIcon;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_survey_saved_data_layout,container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        openCds = view.findViewById(R.id.onCdsOpen);
        openBds = view.findViewById(R.id.onBdsOpen);
        openPds = view.findViewById(R.id.onPdsOpen);
        cdsFragment = view.findViewById(R.id.cdsFragment);
        bdsFragment = view.findViewById(R.id.bdsFragment);
        pdsFragment = view.findViewById(R.id.pdsFragment);
        cdsToggleIcon = view.findViewById(R.id.cdsToggleIcon);
        bdsToggleIcon = view.findViewById(R.id.bdsToggleIcon);
        pdsToggleIcon = view.findViewById(R.id.pdsToggleIcon);
        openCds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cdsFragment.getVisibility() == View.GONE){
                    cdsFragment.setVisibility(View.VISIBLE);
                    cdsToggleIcon.setImageResource(R.drawable.ic_expand_less_icon);
                }else {
                    cdsFragment.setVisibility(View.GONE);
                    cdsToggleIcon.setImageResource(R.drawable.ic_expand_more_icon);
                }


            }
        });

        openBds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bdsFragment.getVisibility() == View.GONE){
                    bdsFragment.setVisibility(View.VISIBLE);
                    bdsToggleIcon.setImageResource(R.drawable.ic_expand_less_icon);
                }else {
                    bdsFragment.setVisibility(View.GONE);
                    bdsToggleIcon.setImageResource(R.drawable.ic_expand_more_icon);
                }

            }
        });

        openPds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdsFragment.getVisibility() == View.GONE){
                    pdsFragment.setVisibility(View.VISIBLE);
                    pdsToggleIcon.setImageResource(R.drawable.ic_expand_less_icon);
                }else {
                    pdsFragment.setVisibility(View.GONE);
                    pdsToggleIcon.setImageResource(R.drawable.ic_expand_more_icon);
                }

            }
        });

    }
}
