package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.account:
                        Intent intent = new Intent(AddActivity.this , AccountActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.lost:
                        Intent intent1 = new Intent(AddActivity.this , LostActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.found:
                        Intent intent2 = new Intent(AddActivity.this ,FoundActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });





    }
}