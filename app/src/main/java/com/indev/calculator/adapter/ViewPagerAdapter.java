package com.indev.calculator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * To be used for adaptation view pager for activity with tabs
 *
 * @author E.Drozdyk
 * @version 1.0 27 Aug 2016
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence[] namesOfTabs;
    private int amountOfTabs;
    private Fragment[] fragmentsOfTabs;

    /**
     * Build a Constructor and assign the passed Values to appropriate values in the class
     */
    public ViewPagerAdapter(FragmentManager fragmentManager, CharSequence[] namesOfTabs,
                            int amountOfTabs, Fragment[] fragmentsOfTabs) {
        super(fragmentManager);
        this.namesOfTabs = namesOfTabs;
        this.amountOfTabs = amountOfTabs;
        this.fragmentsOfTabs = fragmentsOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        for (int i = 0; i < fragmentsOfTabs.length; i++) {
            //Check of the appropriate fragment which we return
            if (position == i) {
                fragment = fragmentsOfTabs[i];
                break;
            }
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return namesOfTabs[position];
    }

    @Override
    public int getCount() {
        return amountOfTabs;
    }

}
