package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdViewActivity extends AppCompatActivity {
    Button reactButton;
    TextView text;
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adview);

        Toast toast = Toast.makeText(this , "ono" , Toast.LENGTH_LONG);
        Log.d("ONO" , "работает");
    }

    //    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.activity_adview);
//
//        reactButton = findViewById(R.id.reactButton);
//        text = findViewById(R.id.adText);
//        image = findViewById(R.id.adPicture);
//
//        Toast toast = Toast.makeText(this , "ono" , Toast.LENGTH_LONG);
//        Log.d("ONO" , "работает");
//        toast.show();
//
//        reactButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdViewActivity.this, ResponseActivity.class);
//
//            }
//        });
//    }
}
