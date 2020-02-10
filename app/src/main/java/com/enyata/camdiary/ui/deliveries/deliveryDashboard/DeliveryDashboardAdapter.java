package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.enyata.camdiary.ui.collections.dashboard.DashboardSlideOneFragment;
import com.enyata.camdiary.ui.collections.dashboard.DashboardSlideThreeFragment;
import com.enyata.camdiary.ui.collections.dashboard.DashboardSlideTwoFragment;

public class DeliveryDashboardAdapter extends FragmentPagerAdapter {
    Context context;

    public DeliveryDashboardAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return DeliveryDashboardSlideOneFragement.newInstance();

        } else {
            return DeliveryDashboardSlideTwoFragment.newInstance();
        }
    }

}
