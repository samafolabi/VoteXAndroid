package com.imperial.votex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int NUM_PAGES = 2;
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    static EditText[] fields = new EditText[10];
    String[] keys = {"orgname", "fname", "lname", "email",
            "cemail", "uname", "pword", "cpword", "user", "pass"};
    String acctv = "org";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.logup_pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        if (!(prefs.getString("uname", "").equals(""))) {
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);

        if (item.getItemId() == R.id.nav_vote) {
            this.startActivity(new Intent(this, VoteActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new LogInSlidePageFragment();
            } else {
                return new SignUpSlidePageFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.org:
                if (checked) {
                    acctv = "org";
                    fields[0].setVisibility(View.VISIBLE);
                    fields[1].setVisibility(View.GONE);
                    fields[2].setVisibility(View.GONE);
                }
                break;
            case R.id.per:
                if (checked) {
                    acctv = "per";
                    fields[1].setVisibility(View.VISIBLE);
                    fields[2].setVisibility(View.VISIBLE);
                    fields[0].setVisibility(View.GONE);
                }
        }
    }

    public void signUp(View view) {
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("acctv", acctv);
        String key, val;
        for (int i = 0; i < 8; i++) {
            key = keys[i];
            val = fields[i].getText().toString();
            formData.put(key, val);
        }
        if (!(formData.get("email").equals(formData.get("cemail")))) {
            Toast.makeText(this,
                    "Please confirm your email", Toast.LENGTH_SHORT).show();
        } else {
            if (!(formData.get("pword").equals(formData.get("cpword")))) {
                Toast.makeText(this,
                        "Please confirm your password", Toast.LENGTH_SHORT).show();
            } else {
                ArrayList list = new ArrayList(6);
                list.add(Callback.url+"/signup");
                list.add(formData);
                list.add(1);
                list.add(LoginActivity.this);
                new ConnectRouteTask().execute(list);
            }
        }
    }

    public void logIn(View view) {
        Map<String, String> formData = new HashMap<String, String>();
        String key, val;
        for (int i = 8; i < 10; i++) {
            key = keys[i];
            val = fields[i].getText().toString();
            formData.put(key, val);
        }
        ArrayList list = new ArrayList(6);
        list.add(Callback.url+"/login");
        list.add(formData);
        list.add(2);
        list.add(LoginActivity.this);
        new ConnectRouteTask().execute(list);
    }

    /*public static String serializeMap(Map<String, String> map) {
        String result = "";
        for (String key: map.keySet()) {
            result += key + "=" + map.get(key) + "&";
        }
        return result.substring(0, result.length()-1);
    }*/

}