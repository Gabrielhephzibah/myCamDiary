package com.enyata.camdiary.ui.collections.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.R;

public class DashboardSlideTwoFragment  extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public DashboardSlideTwoFragment() {
        // Required empty public constructor
    }
    /**
     * Create a new instance of this fragment
     * @return A new instance of fragment FirstFragment.
     */
    public static DashboardSlideTwoFragment newInstance() {
        return new DashboardSlideTwoFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init ViewModel
        dashboardViewModel = ViewModelProviders.of(requireActivity()).get(DashboardViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.collection_second_slide, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView rejectedVolumeTextView = view.findViewById(R.id.litres_second);
        dashboardViewModel.getRejectedVolume().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                rejectedVolumeTextView.setText(String.format("%s Litres", s));
            }
        });
    }
}
