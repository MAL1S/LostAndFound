package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.account);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.found:
                        startActivity(new Intent(getApplicationContext(), FoundActivity.class));
                        finish();
                        overridePendingTransition(0 , 0);
                        return;

                    case R.id.account:

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