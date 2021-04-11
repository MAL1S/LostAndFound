package com.example.lostandfound;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity implements ValueEventListener{
    Button createButton;
    Button pickCoordinatesButton;// Кнопка для выбора местоположения
    double lat, lon; // Получаем координаты маркера
    Intent i; //ну просто интент
    ImageButton pictureButton;
    Spinner spinner;
    EditText text;
    public static final int PICK_IMAGE = 1;
    Bitmap picture;
    DatabaseReference dbRef;
    static int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        i = getIntent();
        lat = getIntent().getDoubleExtra("lat", 43.8);
        lon = getIntent().getDoubleExtra("lon", 124.12);
        if (lat != 43.8) Toast.makeText(this, "Это с тост с карты", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Это с тост с менюхи", Toast.LENGTH_SHORT).show();

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

        createButton = findViewById(R.id.createButton);
        pickCoordinatesButton = findViewById(R.id.pickCoordinates);
        pictureButton = findViewById(R.id.createPictureButton);
        spinner = findViewById(R.id.spinnerTheme);
        text = findViewById(R.id.createText);

        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("record").addValueEventListener(this); // следим за изменением данных

        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String theme = spinner.getSelectedItem().toString();
                String info = text.getText().toString();
                addRecord(new Record(
                        info,
                        lon,
                        lat,
                        theme
                        ,MainActivity.getEmail()
                ), id);
//                Intent i = new Intent(AddActivity.this, FoundActivity.class);
//                startActivity(i);
            }
        });

        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(AddActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_IMAGE);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, PICK_IMAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pickCoordinatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, MapsActivity2.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PICK_IMAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_IMAGE);
                } else {
                    Toast.makeText(this, "У приложения нет права для просмотра фотографий", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedImage;

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                selectedImage = data.getData();
                InputStream in;
                try {
                    in = getContentResolver().openInputStream(selectedImage);
                    final Bitmap selected = BitmapFactory.decodeStream(in);
                    picture = selected;
                    pictureButton.setImageBitmap(selected);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    Toast.makeText(this, "Файл не найден", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void addRecord(Record r, int id) {
        Toast.makeText(this, "Мы попали addRecord", Toast.LENGTH_SHORT).show();
        dbRef.child(String.valueOf(id)).child("record").push().setValue(r);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Record record = snapshot.getValue(Record.class);
        //Log.d("our check", snapshot.getValue().getClass().toString());
        Log.d("record", "record: " + record);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}