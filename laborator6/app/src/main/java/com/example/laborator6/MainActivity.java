package com.example.laborator6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 101;
    private static final int REQUEST_CODE_EDIT = 102;
    private Button buttonAdaugaBiberon;
    private ListView listViewBiberoane;
    private ArrayList<Biberon> listaBiberoane;
    private BiberonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaBiberoane = new ArrayList<>();
        buttonAdaugaBiberon = findViewById(R.id.buttonAdaugaBiberon);
        listViewBiberoane = findViewById(R.id.listViewBiberoane);

        adapter = new BiberonAdapter(this, listaBiberoane);
        listViewBiberoane.setAdapter(adapter);

        // Butonul de adăugare – lansăm EditBiberonActivity fără obiect (mod adăugare)
        buttonAdaugaBiberon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditBiberonActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        // La click pe un element, lansăm EditBiberonActivity în modul editare
        listViewBiberoane.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Biberon biberonSelectat = listaBiberoane.get(position);
                Intent intent = new Intent(MainActivity.this, EditBiberonActivity.class);
                intent.putExtra("obiectBiberon", biberonSelectat);
                intent.putExtra("pozitie", position);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            Biberon biberon = data.getParcelableExtra("obiectBiberon");
            if(requestCode == REQUEST_CODE_ADD) {
                listaBiberoane.add(biberon);
                adapter.notifyDataSetChanged();
            } else if(requestCode == REQUEST_CODE_EDIT) {
                int pozitie = data.getIntExtra("pozitie", -1);
                if(pozitie != -1) {
                    listaBiberoane.set(pozitie, biberon);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}