package com.enyata.camdiary.ui.aggregations.dashboard;

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
import com.enyata.camdiary.ui.aggregations.history.AggregatorHistoryViewModel;
import com.enyata.camdiary.ui.collections.dashboard.DashboardSlideOneFragment;
import com.enyata.camdiary.ui.collections.dashboard.DashboardViewModel;

public class AggregatorSlideOneFragment extends Fragment {
    AggregatorDashboardViewModel aggregatorDashboardViewModel;
      public  AggregatorSlideOneFragment(){
          //require empty constructor
      }

    public static AggregatorSlideOneFragment newInstance() {
        return new AggregatorSlideOneFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init ViewModel
        aggregatorDashboardViewModel = ViewModelProviders.of(requireActivity()).get(AggregatorDashboardViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.aggregator_first_slide, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView AggregatorVolumeTextView = view.findViewById(R.id.aggregator_litres);

        aggregatorDashboardViewModel.getAggregatorVolume().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                AggregatorVolumeTextView.setText(String.format("%s Litres",s));
            }
        });

    }


}
