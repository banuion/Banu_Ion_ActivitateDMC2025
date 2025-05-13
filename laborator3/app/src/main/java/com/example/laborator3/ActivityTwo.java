package com.example.laborator3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ActivityTwo extends AppCompatActivity {

    private static final int REQUEST_CODE_THREE = 100; // cod pentru startActivityForResult

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // LOG in onCreate() (optional: Log.v)
        Log.v("ActivityTwo", "onCreate() - VERBOSE");

        Button btnOpenThird = findViewById(R.id.buttonOpenThird);
        btnOpenThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Construim un intent pentru ActivityThree
                Intent intent = new Intent(ActivityTwo.this, ActivityThree.class);

                // Punem in Intent datele: un mesaj si doua valori intregi
                intent.putExtra("mesaj", "Salut din ActivityTwo!");
                intent.putExtra("valoare1", 10);
                intent.putExtra("valoare2", 20);

                // Pornim ActivityThree si asteptam un raspuns (requestCode = 100)
                startActivityForResult(intent, REQUEST_CODE_THREE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("ActivityTwo", "onStart() - ERROR");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("ActivityTwo", "onResume() - WARNING");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ActivityTwo", "onPause() - DEBUG");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityTwo", "onStop() - INFO");
    }

    // onDestroy() daca vrei si un Log.v() (optional)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("ActivityTwo", "onDestroy() - VERBOSE");
    }

    // Metoda pentru cand se intoarce rezultatul din ActivityThree
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_THREE && resultCode == RESULT_OK && data != null){
            String msgPrimit = data.getStringExtra("returnMessage");
            int suma = data.getIntExtra("returnSum", 0);

            // Afisam intr-un Toast
            Toast.makeText(this,
                    "Mesaj primit: " + msgPrimit + "\nSuma: " + suma,
                    Toast.LENGTH_LONG).show();
        }
    }
}