package com.cookandroid.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cookandroid.myapplication.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    Fragment_admin_list fragment_admin_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    fragment_admin_list = new Fragment_admin_list();

        getSupportFragmentManager().beginTransaction().replace(R.id.container_admin, fragment_admin_list).commit();
        BottomNavigationView BottomNavigationView = findViewById(R.id.nav_view_admin);
        BottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment_admin_list).commit();

                        return true;
                    case R.id.navigation_dashboard:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment_admin_list).commit();

                        return true;
                }

                return false;
            }
        });
    }

}