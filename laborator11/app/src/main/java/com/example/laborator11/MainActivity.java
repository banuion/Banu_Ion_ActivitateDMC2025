package com.example.laborator11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd, btnFavorites;
    private DatabaseReference favRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd       = findViewById(R.id.btnAdd);
        btnFavorites = findViewById(R.id.btnFavorites);

        // navighează la ecranul de adăugare
        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddItemActivity.class))
        );

        // navighează la lista favorite
        btnFavorites.setOnClickListener(v ->
                startActivity(new Intent(this, FavoritesActivity.class))
        );

        // ascultă modificări în RTDB la nodul "favorites"
        favRef = FirebaseDatabase.getInstance()
                .getReference("favorites");
        favRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot snap) {
                Toast.makeText(MainActivity.this,
                        "Baza online a fost actualizată",
                        Toast.LENGTH_SHORT).show();
            }
            @Override public void onCancelled(DatabaseError err) { }
        });
    }
}
