package com.george.devil.Activities.Main.Common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapterFollowingFollowers extends FragmentStatePagerAdapter {

    private final List<Fragment> FragmentsList = new ArrayList<>();
    private final List<String> FragmentsTitleList = new ArrayList<>();

    TabAdapterFollowingFollowers(FragmentManager fm) {
        super(fm);
    }

    /**
     * Вызывается для отрисовки фрагметров
     * @param fragment xml для отрисовки экрана
     * @param title устанавливает текст в tab
     */
    public void addFragment(Fragment fragment, String title) {
        FragmentsList.add(fragment);
        FragmentsTitleList.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FragmentsList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentsTitleList.get(position);
    }

    @Override
    public int getCount() {
        return FragmentsList.size();
    }

}
