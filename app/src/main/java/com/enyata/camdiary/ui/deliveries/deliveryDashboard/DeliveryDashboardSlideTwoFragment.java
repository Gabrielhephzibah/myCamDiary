package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

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

public class DeliveryDashboardSlideTwoFragment extends Fragment {

    private DeliveryDashboardViewModel deliveryDashboardViewModel;

    public DeliveryDashboardSlideTwoFragment(){
        // Required empty public constructor
    }

    public static DeliveryDashboardSlideTwoFragment newInstance() {
        return new DeliveryDashboardSlideTwoFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init ViewModel
        deliveryDashboardViewModel = ViewModelProviders.of(requireActivity()).get(DeliveryDashboardViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.delivery_second_slide, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView bottleCount = view.findViewById(R.id.litres_second);
        deliveryDashboardViewModel.getInventoryCompleted().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                bottleCount.setText(String.format(s));
            }
        });
    }
}
