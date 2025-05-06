package com.example.laborator7;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_BIBERON = 101;
    private Button buttonAddBiberon, buttonSettings;
    private ListView listViewBiberoane;
    private ArrayList<Biberon> listaBiberoane;
    private BiberonAdapter adapter; // Presupunem că ai creat și un adapter personalizat (vezi mai jos)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inițializare listă
        listaBiberoane = new ArrayList<>();
        listViewBiberoane = findViewById(R.id.listViewBiberoane);
        adapter = new BiberonAdapter(this, listaBiberoane);
        listViewBiberoane.setAdapter(adapter);

        buttonAddBiberon = findViewById(R.id.buttonAddBiberon);
        buttonSettings = findViewById(R.id.buttonSettings);

        buttonAddBiberon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBiberonActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_BIBERON);
            }
        });

        // La long click, salvăm Biberon-ul selectat în fișierul "favorites_biberoane.txt"
        listViewBiberoane.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Biberon biberonSelectat = listaBiberoane.get(position);
                saveFavoriteBiberon(biberonSelectat);
                Toast.makeText(MainActivity.this, "Biberon adăugat în favorite", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        // Butonul Settings: deschide SettingsActivity
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveFavoriteBiberon(Biberon biberon) {
        try {
            FileOutputStream fos = openFileOutput("favorites_biberoane.txt", Context.MODE_APPEND);
            String data = biberon.toString() + "\n";
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_BIBERON && resultCode == RESULT_OK && data != null) {
            Biberon biberon = data.getParcelableExtra("obiectBiberon");
            if(biberon != null) {
                listaBiberoane.add(biberon);
                adapter.notifyDataSetChanged();
            }
        }
    }
}