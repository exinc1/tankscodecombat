package com.example.tankscodecombat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_logout) {
                loadFragment(new LogoutFragment());
                return true;
            }
            else if (id == R.id.nav_main) {
                loadFragment(new MainActivityFragment());
                return true;
            }
            else if (id == R.id.nav_instructions) {
                loadFragment(new InstructionsFragment());
                return true;
            }

            return false;
        });
        loadFragment(new MainActivityFragment());

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}