package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.add);

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


                }
            }
        });



    }
}