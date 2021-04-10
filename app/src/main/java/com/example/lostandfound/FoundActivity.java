package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class FoundActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.found);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.found:

                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        finish();
                        overridePendingTransition(0 , 0);
                        return;
                    case R.id.lost:
                        startActivity(new Intent(getApplicationContext(), LostActivity.class));
                        finish();
                        overridePendingTransition(0 , 0);
                        return;
                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(), AddActivity.class));
                        finish();
                        overridePendingTransition(0 , 0);
                        return;


                }
            }
        });



    }
}
