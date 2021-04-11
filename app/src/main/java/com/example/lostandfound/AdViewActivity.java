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
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_adview);

        reactButton = findViewById(R.id.reactButton);
        text = findViewById(R.id.adText);
        image = findViewById(R.id.adPicture);

        Intent i = getIntent();
        String record = i.getStringExtra("record");
        String[] arr = record.split(":");
        String text = arr[1].substring(0, arr[1].indexOf(','));
        String lat = arr[3].substring(0, arr[1].indexOf(','));
        String lon = arr[5].substring(0, arr[1].indexOf(','));
        String type = arr[7];

        reactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdViewActivity.this, ResponseActivity.class);

            }
        });
    }
}
