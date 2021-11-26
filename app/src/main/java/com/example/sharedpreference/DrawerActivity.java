package com.example.sharedpreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        HomeFragment homepage = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, homepage);
        transaction.commit();

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Constants.HOMEPAGETITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView Nav_view= findViewById(R.id.navigation);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open,R.string.drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawer_layout.addDrawerListener(drawerToggle);

        Nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Fragment frag = null;
                if(itemId==R.id.Home)
                { getSupportActionBar().setTitle(Constants.HOMEPAGETITLE);

                    frag =new HomeFragment();

                }
                else if(itemId==R.id.AddItem)
                {
                    getSupportActionBar().setTitle(Constants.ADDITEMTITLE);
                    frag =new AddItemFragment();


                }
                if (frag != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, frag);
                    transaction.commit();
                    drawer_layout.closeDrawers();
                    return true;
                }

                return false;
            }
        });
    }
}