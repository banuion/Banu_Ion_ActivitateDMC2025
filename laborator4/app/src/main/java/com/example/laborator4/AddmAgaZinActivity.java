package com.example.laborator4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.ToggleButton;
import android.widget.Toast;

public class AddmAgaZinActivity extends AppCompatActivity {

    private EditText editTextNume;
    private CheckBox checkBoxDeschis;
    private RadioGroup radioGroupTip;
    private RadioButton radioAlimentar, radioElectronice, radioHaine;
    private Spinner spinnerAn;
    private RatingBar ratingBar;
    private Switch switchTest;
    private ToggleButton toggleButtonTest;
    private Button buttonCreeaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addm_aga_zin);

        // 1. Legăm componentele din layout de cod
        editTextNume = findViewById(R.id.editTextNume);
        checkBoxDeschis = findViewById(R.id.checkBoxDeschis);
        radioGroupTip = findViewById(R.id.radioGroupTip);
        radioAlimentar = findViewById(R.id.radioAlimentar);
        radioElectronice = findViewById(R.id.radioElectronice);
        radioHaine = findViewById(R.id.radioHaine);
        spinnerAn = findViewById(R.id.spinnerAn);
        ratingBar = findViewById(R.id.ratingBar);
        switchTest = findViewById(R.id.switchTest);
        toggleButtonTest = findViewById(R.id.toggleButtonTest);
        buttonCreeaza = findViewById(R.id.buttonCreeaza);

        // 2. Inițializăm spinner-ul cu valori pentru an (ex: 2000..2023)
        Integer[] ani = new Integer[24]; // 2000..2023 => 24 valori
        for(int i=0; i<24; i++){
            ani[i] = 2000 + i;
        }
        ArrayAdapter<Integer> adapterAni = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ani);
        adapterAni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAn.setAdapter(adapterAni);

        // 3. Logica buton "Creează și returnează obiect"
        buttonCreeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Colectăm datele introduse
                String nume = editTextNume.getText().toString();
                boolean deschis = checkBoxDeschis.isChecked();
                int anSelectat = (int) spinnerAn.getSelectedItem();
                float rating = ratingBar.getRating();

                // Determinăm TipMagazin din radioGroup
                mAgaZin.TipMagazin tipMagazin;
                int selectedId = radioGroupTip.getCheckedRadioButtonId();

                if(selectedId == radioAlimentar.getId()){
                    tipMagazin = mAgaZin.TipMagazin.ALIMENTAR;
                } else if(selectedId == radioElectronice.getId()){
                    tipMagazin = mAgaZin.TipMagazin.ELECTRONICE;
                } else if(selectedId == radioHaine.getId()){
                    tipMagazin = mAgaZin.TipMagazin.HAINE;
                } else {
                    // dacă nu a selectat nimic, punem un default
                    tipMagazin = mAgaZin.TipMagazin.ALIMENTAR;
                }

                // Opțional: putem verifica Switch sau Toggle
                // exemplu de utilizare:
                boolean switchVal = switchTest.isChecked();
                boolean toggleVal = toggleButtonTest.isChecked();
                // pot fi folosite pentru logica suplimentară, dar
                // nu sunt neapărat parte din obiect.
                // Deocamdată doar le afișăm într-un Toast informativ
                Toast.makeText(AddmAgaZinActivity.this,
                        "Switch is " + switchVal + ", Toggle is " + toggleVal,
                        Toast.LENGTH_SHORT).show();

                // Creăm instanță mAgaZin
                mAgaZin magazin = new mAgaZin(nume, deschis, anSelectat, tipMagazin, rating);

                // Putem trimite obiectul prin intent -> extras
                //  - fie folosim putExtra("cheie", magazin) dacă implementează Serializable
                Intent intent = new Intent();
                intent.putExtra("obiectMagazin", magazin);

                // setResult + finish => trimite înapoi la MainActivity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}