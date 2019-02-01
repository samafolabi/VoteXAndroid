package com.imperial.votex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    EditText userTag = null, systemTag = null;
    public static Button submitVote = null;
    public static TextView bigName = null;
    public static RadioGroup radioGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        userTag = (EditText) findViewById(R.id.userTag);
        systemTag = (EditText) findViewById(R.id.systemTag);
        bigName = (TextView) findViewById(R.id.bigName);
        radioGroup = (RadioGroup) findViewById(R.id.candGroup);
        submitVote = (Button) findViewById(R.id.submitVote);
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

        if (item.getItemId() == R.id.nav_pro) {
            SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            if (!prefs.getString("uname", null).equals(null)) {
                this.startActivity(new Intent(this, MainActivity.class));
            } else {
                this.startActivity(new Intent(this, LoginActivity.class));
            }
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

    public void searchSystem(View view) {
        String user = userTag.getText().toString(),
               system = systemTag.getText().toString();
        ArrayList list = new ArrayList(6);
        list.add(Callback.url+"/votex/"+user+"/"+system);
        list.add(null);
        list.add(5);
        list.add(VoteActivity.this);

        new ConnectRouteTask().execute(list);
    }

    public void submitVote(View view) {
        int rbID = radioGroup.getCheckedRadioButtonId();
        View rButton = radioGroup.findViewById(rbID);
        int idx = radioGroup.indexOfChild(rButton);
        RadioButton r = (RadioButton) radioGroup.getChildAt(idx);

        ArrayList list = new ArrayList(6);
        list.add(Callback.url+"/signup");
        list.add(r.getText().toString());
        list.add(6);
        list.add(VoteActivity.this);
        new ConnectRouteTask().execute(list);
    }

}
