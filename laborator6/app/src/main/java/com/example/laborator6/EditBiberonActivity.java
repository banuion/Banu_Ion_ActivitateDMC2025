package com.example.laborator6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.ToggleButton;
import android.widget.Toast;

public class EditBiberonActivity extends AppCompatActivity {

    private EditText editTextBrand;
    private CheckBox checkBoxAnticolici;
    private RadioGroup radioGroupMaterial;
    private RadioButton radioPlastic, radioSticla, radioSilicon;
    private Spinner spinnerCapacitate;
    private RatingBar ratingBar;
    private Switch switchTest;
    private ToggleButton toggleButtonTest;
    private Button buttonSalveaza;

    private int pozitie = -1;
    // Vector pentru opțiunile Spinner
    private Integer[] capacitati = new Integer[]{120, 150, 180};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_biberon);

        editTextBrand = findViewById(R.id.editTextBrand);
        checkBoxAnticolici = findViewById(R.id.checkBoxAnticolici);
        radioGroupMaterial = findViewById(R.id.radioGroupMaterial);
        radioPlastic = findViewById(R.id.radioPlastic);
        radioSticla = findViewById(R.id.radioSticla);
        radioSilicon = findViewById(R.id.radioSilicon);
        spinnerCapacitate = findViewById(R.id.spinnerCapacitate);
        ratingBar = findViewById(R.id.ratingBar);
        switchTest = findViewById(R.id.switchTest);
        toggleButtonTest = findViewById(R.id.toggleButtonTest);
        buttonSalveaza = findViewById(R.id.buttonCreeaza); // Butonul "Salvează"

        // Inițializăm spinner-ul
        ArrayAdapter<Integer> adapterCapacitate = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, capacitati);
        adapterCapacitate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCapacitate.setAdapter(adapterCapacitate);

        // Dacă activitatea a fost lansată cu un obiect, se preiau datele
        Intent intent = getIntent();
        if (intent.hasExtra("obiectBiberon")) {
            Biberon biberon = intent.getParcelableExtra("obiectBiberon");
            pozitie = intent.getIntExtra("pozitie", -1);
            if (biberon != null) {
                editTextBrand.setText(biberon.getBrand());
                checkBoxAnticolici.setChecked(biberon.isEsteAnticolici());
                switch (biberon.getMaterial()) {
                    case PLASTIC:
                        radioPlastic.setChecked(true);
                        break;
                    case STICLA:
                        radioSticla.setChecked(true);
                        break;
                    case SILICONE:
                        radioSilicon.setChecked(true);
                        break;
                }
                // Setăm Spinner-ul – selectăm poziția corespunzătoare capacității
                for (int i = 0; i < capacitati.length; i++) {
                    if (capacitati[i] == biberon.getCapacitate()) {
                        spinnerCapacitate.setSelection(i);
                        break;
                    }
                }
                ratingBar.setRating(biberon.getRating());
            }
        }

        buttonSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = editTextBrand.getText().toString();
                boolean esteAnticolici = checkBoxAnticolici.isChecked();
                int capacitate = (int) spinnerCapacitate.getSelectedItem();
                float rating = ratingBar.getRating();

                Biberon.TipMaterial tipMaterial;
                int selectedId = radioGroupMaterial.getCheckedRadioButtonId();
                if (selectedId == radioPlastic.getId()) {
                    tipMaterial = Biberon.TipMaterial.PLASTIC;
                } else if (selectedId == radioSticla.getId()) {
                    tipMaterial = Biberon.TipMaterial.STICLA;
                } else if (selectedId == radioSilicon.getId()) {
                    tipMaterial = Biberon.TipMaterial.SILICONE;
                } else {
                    tipMaterial = Biberon.TipMaterial.PLASTIC;
                }

                // (Opțional) afișare valori din Switch și ToggleButton
                boolean switchVal = switchTest.isChecked();
                boolean toggleVal = toggleButtonTest.isChecked();
                Toast.makeText(EditBiberonActivity.this,
                        "Switch: " + switchVal + " | Toggle: " + toggleVal,
                        Toast.LENGTH_SHORT).show();

                Biberon biberonModificat = new Biberon(brand, esteAnticolici, capacitate, tipMaterial, rating);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("obiectBiberon", biberonModificat);
                resultIntent.putExtra("pozitie", pozitie);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}