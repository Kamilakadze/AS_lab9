package com.example.lab9.activities;

import com.example.lab9.R;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    return true; // Уже на Home
                case R.id.menu_search:
                    startActivity(new Intent(this, SearchActivity.class));
                    return true;
                case R.id.menu_library:
                    startActivity(new Intent(this, PlaylistActivity.class));
                    return true;
            }
            return false;
        });
    }
}
