package com.indev.calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setToolbar();
        setSlidingTabs();
        checkLoginProfile();
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

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void checkLoginProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            goLoginActivity();
        }
    }

    private void logOutFromProfile() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout: {
                logOutFromProfile();
                break;
            }
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

}