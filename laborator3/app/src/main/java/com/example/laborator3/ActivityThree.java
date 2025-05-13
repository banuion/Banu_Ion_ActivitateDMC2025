package com.example.laborator3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityThree extends AppCompatActivity {

    private int val1, val2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        // 1. Preluam datele din intent
        Intent intentPrimire = getIntent();
        String mesaj = intentPrimire.getStringExtra("mesaj");
        val1 = intentPrimire.getIntExtra("valoare1", 0);
        val2 = intentPrimire.getIntExtra("valoare2", 0);

        // 2. Afisam intr-un Toast ce am primit
        Toast.makeText(this,
                "Am primit: " + mesaj + "\nValori: " + val1 + " si " + val2,
                Toast.LENGTH_LONG).show();

        // 3. Buton care trimite raspuns inapoi
        Button btnReturn = findViewById(R.id.buttonReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Facem suma
                int suma = val1 + val2;

                // Construim intentul de raspuns
                Intent intentReturn = new Intent();
                intentReturn.putExtra("returnMessage", "Salut din ActivityThree!");
                intentReturn.putExtra("returnSum", suma);

                // setResult -> trimite datele inapoi
                setResult(RESULT_OK, intentReturn);

                // Inchidem activitatea curenta
                finish();
            }
        });
    }
}