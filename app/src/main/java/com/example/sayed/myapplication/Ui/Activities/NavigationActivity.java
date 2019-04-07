package com.example.sayed.myapplication.Ui.Activities;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Fragments.AboutappFragment;
import com.example.sayed.myapplication.Ui.Fragments.ContactusFragment;
import com.example.sayed.myapplication.Ui.Fragments.ContainerFragment;
import com.example.sayed.myapplication.Ui.Fragments.DonationRequestFragment;
import com.example.sayed.myapplication.Ui.Fragments.FavouritFragment;
import com.example.sayed.myapplication.Ui.Fragments.ModifyFragment;
import com.example.sayed.myapplication.Ui.Fragments.NotificationSettingFragment;
import com.example.sayed.myapplication.Ui.Helper.HelperMethod;
import com.example.sayed.myapplication.data.rest.ApiServices;

import static com.example.sayed.myapplication.data.rest.RetrofitClient.getClient;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        apiServices = getClient().create(ApiServices.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final DonationRequestFragment requestFragment = new DonationRequestFragment();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HelperMethod.FragmentManager(requestFragment, getSupportFragmentManager(), R.id.nav_frame, null, null);
            }
        });


        // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //     .setAction("Action", null).show();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ContainerFragment fragment = new ContainerFragment();
       /* FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fm.beginTransaction();

        fragmentTransaction.replace(R.id.nav_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
        HelperMethod.FragmentManager(fragment, getSupportFragmentManager(), R.id.nav_frame, null, null);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                item.getIcon().setTint(Color.WHITE);
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        ModifyFragment fragment = new ModifyFragment();
        NotificationSettingFragment fragment2 = new NotificationSettingFragment();
        FavouritFragment fragment3 = new FavouritFragment();
        AboutappFragment fragment4 = new AboutappFragment();
        ContactusFragment fragment5 = new ContactusFragment();


        if (id == R.id.nav_info) {


            HelperMethod.FragmentManager(fragment, getSupportFragmentManager(), R.id.nav_frame, null, null);

            // Handle the camera action
        } else if (id == R.id.nav_notification) {
            /*FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.nav_frame,fragment2);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/
            HelperMethod.FragmentManager(fragment2, getSupportFragmentManager(), R.id.nav_frame, null, null);


        } else if (id == R.id.nav_favourite) {
          /*  FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.nav_frame,fragment3);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/
            HelperMethod.FragmentManager(fragment3, getSupportFragmentManager(), R.id.nav_frame, null, null);

        } else if (id == R.id.nav_about) {
          /* FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.nav_frame,fragment4);
            fragmentTransaction.commit();*/
            HelperMethod.FragmentManager(fragment4, getSupportFragmentManager(), R.id.nav_frame, null, null);


        } else if (id == R.id.nav_instruction) {

        } else if (id == R.id.nav_contactus) {
          /* FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fm.beginTransaction();
            fragmentTransaction.replace(R.id.nav_frame,fragment5);
            fragmentTransaction.commit();*/
            HelperMethod.FragmentManager(fragment5, getSupportFragmentManager(), R.id.nav_frame, null, null);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
