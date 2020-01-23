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
import com.enyata.camdiary.ui.collections.dashboard.DashboardSlideOneFragment;
import com.enyata.camdiary.ui.collections.dashboard.DashboardViewModel;

public class DeliveryDashboardSlideOneFragement extends Fragment {
    private DeliveryDashboardViewModel deliveryDashboardViewModel;

    public DeliveryDashboardSlideOneFragement(){
        // Required empty public constructor
    }

    public static DeliveryDashboardSlideOneFragement newInstance() {
        return new DeliveryDashboardSlideOneFragement();
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
        return inflater.inflate(R.layout.delivery_first_slide, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView completedDelivery = view.findViewById(R.id.litres_first);
       deliveryDashboardViewModel.getDeliveryCompleted().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               completedDelivery.setText(String.format(s));
            }
        });
    }
}
