package com.example.laborator7;


import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private EditText editTextTextSize, editTextTextColor;
    private Button buttonSaveSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextTextSize = findViewById(R.id.editTextTextSize);
        editTextTextColor = findViewById(R.id.editTextTextColor);
        buttonSaveSettings = findViewById(R.id.buttonSaveSettings);

        // Încarcă setările existente
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        float textSize = prefs.getFloat("textSize", 16f);
        String textColor = prefs.getString("textColor", "#000000");
        editTextTextSize.setText(String.valueOf(textSize));
        editTextTextColor.setText(textColor);

        buttonSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sizeStr = editTextTextSize.getText().toString();
                String colorStr = editTextTextColor.getText().toString();
                if(sizeStr.isEmpty() || colorStr.isEmpty()){
                    Toast.makeText(SettingsActivity.this, "Toate câmpurile sunt obligatorii", Toast.LENGTH_SHORT).show();
                    return;
                }
                float newTextSize = Float.parseFloat(sizeStr);
                SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
                editor.putFloat("textSize", newTextSize);
                editor.putString("textColor", colorStr);
                editor.apply();
                Toast.makeText(SettingsActivity.this, "Setările au fost salvate", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}