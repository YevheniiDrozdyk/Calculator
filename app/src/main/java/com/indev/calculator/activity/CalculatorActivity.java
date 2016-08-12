package com.indev.calculator.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.indev.calculator.R;
import com.indev.calculator.adapter.ViewPagerAdapter;
import com.indev.calculator.fragment.FactorialFragment;
import com.indev.calculator.fragment.PairsFragment;
import com.indev.calculator.fragment.PalindromeFragment;
import com.indev.calculator.widget.SlidingTabLayout;

public class CalculatorActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;

    private final Fragment[] APP_FRAGMENT = {new PalindromeFragment(), new FactorialFragment(),
            new PairsFragment()};

    private final CharSequence[] NAME_OF_TAB = {"Palindrome", "Factorial", "Pairs"};
    private final int AMOUNT_OF_TABS = 3;

    private String userName;

    private static final String TAG="myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setToolbar();
        setSlidingTabs();
        Log.d(TAG, getUserName());
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void setSlidingTabs() {
        //Creating The ViewPagerAdapter and Passing Fragment Manager, Titles for the Tabs, Amount
        // of Tabs and Fragments
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), NAME_OF_TAB, AMOUNT_OF_TABS, APP_FRAGMENT);

        //Assigning ViewPager View and setting the adapter
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        //Assigning the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);

        // To make the Tabs Fixed set this true, This makes the tabs Evenly in Available width
        tabs.setDistributeEvenly(true);

        //Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        //Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(viewPager);
    }

    private String getUserName() {
        userName = getIntent().getStringExtra("userName");
        return userName;
    }
}
