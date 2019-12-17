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
import com.enyata.camdiary.ui.collections.dashboard.DashboardSlideTwoFragment;

public class AggregatorSlideTwoFragment extends Fragment {

    AggregatorDashboardViewModel aggregatorDashboardViewModel;

    public  AggregatorSlideTwoFragment(){
        //no constructor needed

    }

    public static AggregatorSlideTwoFragment newInstance() {
        return new AggregatorSlideTwoFragment();
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
        return inflater.inflate(R.layout.aggregator_second_slide, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView totalAggregationTextView= view.findViewById(R.id.total_aggregation);
        aggregatorDashboardViewModel.getTotalAggregation().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                totalAggregationTextView.setText(String.format(s));
            }
        });

    }
}
