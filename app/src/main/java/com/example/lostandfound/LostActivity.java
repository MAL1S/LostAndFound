package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.lost);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.found:
                        startActivity(new Intent(getApplicationContext() , FoundActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.account:
                        startActivity(new Intent(getApplicationContext() , AccountActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.lost:


                    case R.id.add:
                        startActivity(new Intent(getApplicationContext() , AddActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                }
                return false;
            }
        });
    }
}