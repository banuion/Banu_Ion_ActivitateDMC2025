package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.buttonGallery);
        btn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ImageListActivity.class))
        );
    }
}