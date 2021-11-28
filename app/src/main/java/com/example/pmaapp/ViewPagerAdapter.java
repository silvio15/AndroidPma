package com.example.pmaapp;

import static com.example.pmaapp.CreateNewRecordActivity.viewPager;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(@NonNull FragmentActivity fa) {
            super(fa);
        }

    private static int NUM_PAGES = 3;

        // Returns the fragment to display for that page
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return new PersonalInfoFragment();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new StudentInfoFragment();
                case 2: // Fragment # 1 - This will show SecondFragment
                    return new SummaryFragment();
                default:
                    return null;
            }
        }

    // Returns total number of pages
    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

}