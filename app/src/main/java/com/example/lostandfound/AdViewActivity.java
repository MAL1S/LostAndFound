package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdViewActivity extends AppCompatActivity {
    Button reactButton;
    TextView text;
    ImageView image;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adview);

        reactButton = findViewById(R.id.reactButton);
        text = findViewById(R.id.adText);
        image = findViewById(R.id.adPicture);


        Intent i = getIntent();
        String record = i.getStringExtra("record");


//      System.out.println(record);
//        String[] arr = record.split(":");
//        String info = arr[1].substring(0, arr[1].indexOf(','));
//        String lat = arr[2].substring(0, arr[1].indexOf(','));
//        String lon = arr[3].substring(0, arr[1].indexOf(','));
//        String type = arr[4].substring(0, arr[1].indexOf(','));
//        String email = arr[5];
//
//        text.setText(info + "\n" + lat + "\n" + lon + "\n" + type + "\n" + email);

        text.setText(record);

        reactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdViewActivity.this, ResponseActivity.class);

                startActivity(intent);
            }
        });
    }
}
