package com.enyata.camdiary.ui.aggregations.dashboard;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.camdiary.ui.collections.dashboard.DashboardSlideTwoFragment;

public class AggregatorHomepageAdapter extends FragmentPagerAdapter {
    Context context;

    public AggregatorHomepageAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return AggregatorSlideOneFragment.newInstance();
        }
        else if(position == 1) {
                return AggregatorSlideThreeFragment.newInstance();
        }else {
            return AggregatorSlideTwoFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
