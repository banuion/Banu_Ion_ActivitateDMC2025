package com.example.laborator11;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private List<String> data = new ArrayList<>();
    private DatabaseReference favRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        lv = findViewById(R.id.lvFavorites);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);

        favRef = FirebaseDatabase.getInstance()
                .getReference("favorites");
        favRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot snap) {
                data.clear();
                for (DataSnapshot c : snap.getChildren()) {
                    Item it = c.getValue(Item.class);
                    data.add(it.name + " – " + it.desc);
                }
                adapter.notifyDataSetChanged();
            }
            @Override public void onCancelled(DatabaseError err) { }
        });
    }
}
