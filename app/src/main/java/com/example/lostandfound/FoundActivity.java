package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.account:
                        Intent intent = new Intent(FoundActivity.this , AccountActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.lost:
                        Intent intent1 = new Intent(FoundActivity.this , LostActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.add:
                        Intent intent2 = new Intent(FoundActivity.this , AddActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });


    }
}
