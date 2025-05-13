package com.example.laborator4;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_BIBERON = 102;
    private Button buttonAdaugaBiberon;
    private TextView textViewRezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdaugaBiberon = findViewById(R.id.buttonAdaugaBiberon);
        textViewRezultat = findViewById(R.id.textViewRezultat);

        buttonAdaugaBiberon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBiberonActivity.class);
                startActivityForResult(intent, REQUEST_CODE_BIBERON);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_BIBERON && resultCode == RESULT_OK && data != null) {
            Biberon biberon = (Biberon) data.getSerializableExtra("obiectBiberon");
            if(biberon != null) {
                String info = "Date biberon:\n"
                        + "Brand: " + biberon.getBrand() + "\n"
                        + "Anticolici: " + biberon.isEsteAnticolici() + "\n"
                        + "Capacitate: " + biberon.getCapacitate() + " ml\n"
                        + "Material: " + biberon.getMaterial() + "\n"
                        + "Rating: " + biberon.getRating();
                textViewRezultat.setText(info);
            }
        }
    }
}
//. Creați o clasă pentru un obiect din realitate care conține inițialele numelui. Exemplu Alin Zamfiroiu – clasa Magazin (mAgaZin). Această clasă trebuie să conțină minim cinci atribute de tipuri diferite, dintre care minim unul să fie string, unul de tipul boolean, unul de tip întreg și un atribut sa aiba valori posibile într-o mulțime finită (enum).
//
//        3. Adăugați o nouă activitate în cadrul proiectului pentru preluarea de date pentru crearea de obiecte de tipul clasei create anterior. Activitatea trebuie să conțină view-uri de tipul: TextView, EditText, Button, CheckBox, RadioButton, Spinner, RattingBar, Switch, ToggleButton. Această activitate de introducere date este deschisă din prima activitate printr-un buton de adăugare printr-un intenet dependent.
//
//4. Datele introduse de utilizator sunt folosite pentru crearea unei instanțe a clasei. Această instanță este returnată către prima activitate, din cadrul căreia a fost deschisă activitatea de introducere date. Obiectul este transmis prin bundle (poate fi folosit si Parcelable sau trimiteți fiecare atribut in parte). În activitatea principală este afișat obiectul prin intermediul unor view-uri de tip TextView.