package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LostActivity extends AppCompatActivity {

    private RecyclerView cardsList;
    private CardsAdapter cardsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        cardsList = findViewById(R.id.rv_lost_cards);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cardsList.setLayoutManager(layoutManager);

        cardsList.setHasFixedSize(true);

        cardsAdapter = new CardsAdapter(30 , this , "Ключи" , "Потеряны ключи от квартиры");
        cardsList.setAdapter(cardsAdapter);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.found:
                        Intent intent = new Intent(LostActivity.this , FoundActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.account:
                        Intent intent1 = new Intent(LostActivity.this , AccountActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.add:
                        Intent intent2 = new Intent(LostActivity.this , AddActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });



    }
}