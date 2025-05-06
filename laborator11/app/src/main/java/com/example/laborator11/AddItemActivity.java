package com.example.laborator11;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends AppCompatActivity {
    private EditText etName, etDesc;
    private CheckBox cbOnline;
    private Button btnSave;
    private LocalDatabaseHelper localDb;
    private DatabaseReference favoritesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        etName   = findViewById(R.id.etName);
        etDesc   = findViewById(R.id.etDesc);
        cbOnline = findViewById(R.id.cbOnline);
        btnSave  = findViewById(R.id.btnSave);

        // 1) helper local (din laboratorul precedent)
        localDb = new LocalDatabaseHelper(this);

        // 2) RTDB reference la nodul "favorites"
        favoritesRef = FirebaseDatabase.getInstance()
                .getReference("favorites");

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            if (name.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this,
                        "Completează toate câmpurile",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Salvează local
            localDb.insertItem(name, desc);

            // Salvează online dacă checkbox e bifat
            if (cbOnline.isChecked()) {
                DatabaseReference newRef = favoritesRef.push();
                newRef.setValue(new Item(name, desc))
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.d("RTDB",">> Push OK, key=" + newRef.getKey());
                            } else {
                                Log.e("RTDB",">> Push FAILED", task.getException());
                            }
                        });
            }


            Toast.makeText(this,
                    "Obiect salvat",
                    Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
