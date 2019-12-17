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
import com.enyata.camdiary.databinding.FragmentAboutBinding;

public class DashboardSlideThreeFragment extends Fragment {

    DashboardSlideThreeFragment dashboardSlideThreeFragment;

    private DashboardViewModel dashboardViewModel;

    public DashboardSlideThreeFragment() {
        // Required empty public constructor
    }
    /**
     * Create a new instance of this fragment
     * @return A new instance of fragment FirstFragment.
     */
    public static DashboardSlideThreeFragment newInstance() {
        return new DashboardSlideThreeFragment();
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
        return inflater.inflate(R.layout.collection_third_slide, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView entries = view.findViewById(R.id.litres_third);
        dashboardViewModel.getEntries().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                entries.setText(s);
            }
        });
    }
}
