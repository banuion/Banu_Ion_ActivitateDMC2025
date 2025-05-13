package com.example.laborator4;
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

public class AddBiberonActivity extends AppCompatActivity {

    private EditText editTextBrand;
    private CheckBox checkBoxAnticolici;
    private RadioGroup radioGroupMaterial;
    private RadioButton radioPlastic, radioSticla, radioSilicon;
    private Spinner spinnerCapacitate;
    private RatingBar ratingBar;
    private Switch switchTest;
    private ToggleButton toggleButtonTest;
    private Button buttonCreeaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biberon);

        // Legarea componentelor din layout
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
        buttonCreeaza = findViewById(R.id.buttonCreeaza);

        // Inițializarea spinner-ului cu opțiuni de capacitate (ex: 120, 150, 180 ml)
        Integer[] capacitati = new Integer[]{120, 150, 180};
        ArrayAdapter<Integer> adapterCapacitate = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, capacitati);
        adapterCapacitate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCapacitate.setAdapter(adapterCapacitate);

        // Butonul de creare a obiectului Biberon
        buttonCreeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = editTextBrand.getText().toString();
                boolean esteAnticolici = checkBoxAnticolici.isChecked();
                int capacitate = (int) spinnerCapacitate.getSelectedItem();
                float rating = ratingBar.getRating();

                // Determinarea tipului de material din RadioGroup
                Biberon.TipMaterial tipMaterial;
                int selectedId = radioGroupMaterial.getCheckedRadioButtonId();
                if(selectedId == radioPlastic.getId()){
                    tipMaterial = Biberon.TipMaterial.PLASTIC;
                } else if(selectedId == radioSticla.getId()){
                    tipMaterial = Biberon.TipMaterial.STICLA;
                } else if(selectedId == radioSilicon.getId()){
                    tipMaterial = Biberon.TipMaterial.SILICONE;
                } else {
                    tipMaterial = Biberon.TipMaterial.PLASTIC;
                }

                // Opțional: afișăm valorile din Switch și ToggleButton
                boolean switchVal = switchTest.isChecked();
                boolean toggleVal = toggleButtonTest.isChecked();
                Toast.makeText(AddBiberonActivity.this,
                        "Switch: " + switchVal + " | Toggle: " + toggleVal,
                        Toast.LENGTH_SHORT).show();

                // Crearea instanței Biberon
                Biberon biberon = new Biberon(brand, esteAnticolici, capacitate, tipMaterial, rating);

                // Trimiterea obiectului înapoi către MainActivity prin Intent
                Intent intent = new Intent();
                intent.putExtra("obiectBiberon", biberon);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}