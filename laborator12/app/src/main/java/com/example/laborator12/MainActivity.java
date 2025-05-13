package com.example.laborator12;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPins  = findViewById(R.id.btnMapPins);
        Spinner spin    = findViewById(R.id.spinnerShape);
        Button btnShape = findViewById(R.id.btnMapShape);

        btnPins.setOnClickListener(v ->
                startActivity(new Intent(this, MapPinsActivity.class))
        );

        btnShape.setOnClickListener(v -> {
            String choice = (String) spin.getSelectedItem();
            Intent i = new Intent(this, MapShapeActivity.class);
            i.putExtra("shapeType", choice);
            startActivity(i);
        });
    }
}
