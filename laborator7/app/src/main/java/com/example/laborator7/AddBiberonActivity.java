package com.example.laborator7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddBiberonActivity extends AppCompatActivity {

    private TextView textViewAddBiberonTitle;
    private EditText editTextBiberonBrand, editTextBiberonCapacitate;
    private RatingBar ratingBarBiberon;
    private RadioGroup radioGroupBiberonType;
    private RadioButton radioButtonBiberonAnticolici;
    private Button buttonSaveBiberon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biberon);

        // Legarea componentelor din layout
        textViewAddBiberonTitle = findViewById(R.id.textViewAddBiberonTitle);
        editTextBiberonBrand = findViewById(R.id.editTextBiberonBrand);
        editTextBiberonCapacitate = findViewById(R.id.editTextBiberonCapacitate);
        ratingBarBiberon = findViewById(R.id.ratingBarBiberon);
        radioGroupBiberonType = findViewById(R.id.radioGroupBiberonType);
        radioButtonBiberonAnticolici = findViewById(R.id.radioButtonBiberonAnticolici);
        buttonSaveBiberon = findViewById(R.id.buttonSaveBiberon);

        // Încărcarea setărilor din SharedPreferences și aplicarea lor
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        float textSize = prefs.getFloat("textSize", 16f);
        String textColor = prefs.getString("textColor", "#000000");
        textViewAddBiberonTitle.setTextSize(textSize);
        textViewAddBiberonTitle.setTextColor(Color.parseColor(textColor));
        // Se pot aplica și altor TextView-uri după preferință

        buttonSaveBiberon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Preluăm datele
                String brand = editTextBiberonBrand.getText().toString();
                String capacitateStr = editTextBiberonCapacitate.getText().toString();
                if (brand.isEmpty() || capacitateStr.isEmpty()) {
                    Toast.makeText(AddBiberonActivity.this, "Toate câmpurile sunt obligatorii", Toast.LENGTH_SHORT).show();
                    return;
                }
                int capacitate = Integer.parseInt(capacitateStr);
                float rating = ratingBarBiberon.getRating();
                // Determinăm dacă este anticolici (presupunem că dacă butonul "Da" este selectat, atunci e anticolici)
                boolean esteAnticolici = (radioGroupBiberonType.getCheckedRadioButtonId() == R.id.radioButtonBiberonAnticolici);

                Biberon biberon = new Biberon(brand, esteAnticolici, capacitate, rating);

                // Salvează obiectul în fișierul "all_biberoane.txt"
                saveBiberonToFile(biberon);

                // Trimitem obiectul către activitatea principală
                Intent intent = new Intent();
                intent.putExtra("obiectBiberon", biberon);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void saveBiberonToFile(Biberon biberon) {
        try {
            // Deschidem fișierul în modul APPEND
            FileOutputStream fos = openFileOutput("all_biberoane.txt", Context.MODE_APPEND);
            String data = biberon.toString() + "\n";
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}