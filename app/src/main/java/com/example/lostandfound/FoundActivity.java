package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class FoundActivity extends AppCompatActivity{

    TextView result; // получаем из firebase
    DatabaseReference dbRef; // firebase object
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        result = findViewById(R.id.textView);

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
        dbRef.child("record").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<Record> r = new GenericTypeIndicator<Record>() {};
                if (r != null) {
                    Record rec =  snapshot.child("record").getValue(r);
                    Toast.makeText(FoundActivity.this, "Ку вы молодец часть 1", Toast.LENGTH_SHORT).show();
                    if (rec != null) {
                        result.setText(rec.toString());
                        Toast.makeText(FoundActivity.this, "Ку вы молодец", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); // следим за изменением данных

    }
}
