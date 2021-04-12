package com.example.lostandfound;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoundActivity extends AppCompatActivity {

    private RecyclerView cardsList;
    private CardsAdapter cardsAdapter;
    TextView result; // получаем из firebase
    DatabaseReference dbRef; // firebase object
    HashMap<String, Record> list;

    private LocationManager locationManager;
    private Location location;
    ArrayList<Record> records ;

    ArrayList<Record> closest; //>лижайшие к пользователю объявления

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        result = findViewById(R.id.found_count);

        cardsList = findViewById(R.id.rv_found_cards);



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

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


        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("0").child("record").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<HashMap<String,Record>> r = new GenericTypeIndicator<HashMap<String,Record>>() {};
                list = snapshot.getValue(r);
                closest = new ArrayList<>();
//                for (Map.Entry<String, Record> entry : list.entrySet()) {
//                    System.out.println(entry.getKey() + " " + entry.getValue());
//
//                }

                if(list != null) {


                    records = new ArrayList<>(list.values());

                    MyAdapter myAdapter = new MyAdapter(FoundActivity.this, records);


                    cardsList.setAdapter(myAdapter);
                    cardsList.setLayoutManager(new LinearLayoutManager(FoundActivity.this));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); // следим за изменением данных


    }
}
