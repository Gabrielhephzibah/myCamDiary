package com.enyata.camdiary.ui.collections.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class DashboardAdapter extends FragmentPagerAdapter {

    Context context;

    public DashboardAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return DashboardSlideOneFragment.newInstance();
        } else if(position == 1) {
            return DashboardSlideTwoFragment.newInstance();
        }else{
            return DashboardSlideThreeFragment.newInstance();
        }
    }

}
