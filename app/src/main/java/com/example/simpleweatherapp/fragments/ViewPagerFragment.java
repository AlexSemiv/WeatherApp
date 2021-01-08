package com.example.simpleweatherapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.simpleweatherapp.MainActivity;
import com.example.simpleweatherapp.R;
import com.example.simpleweatherapp.myModel.DayForecast;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ViewPagerFragment extends Fragment {

    ArrayList<DayForecast> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        list = getArguments().getParcelableArrayList(MainActivity.DAY_FORECAST);

        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        if(list!=null) {
            ViewPager viewPager = view.findViewById(R.id.viewPager);
            TabLayout tab = view.findViewById(R.id.tab);

            PagerAdapter adapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);
            viewPager.setPageTransformer(true,new DeptPageTransformer());

            tab.setupWithViewPager(viewPager);
        }

        return view;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MainActivity.DAY_FORECAST,list.get(0).getThreeHoursList().get(position));

            CurrentForecastFragment forecastFragment = new CurrentForecastFragment();
            forecastFragment.setArguments(bundle);

            return forecastFragment;
        }

        @Override
        public int getCount() {
            return list.get(0).getThreeHoursList().size();
        }
    }
}
