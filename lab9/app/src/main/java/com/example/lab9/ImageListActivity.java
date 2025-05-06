package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import java.util.*;

public class ImageListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        ListView listView = findViewById(R.id.listViewImages);
        List<CarImageItem> data = new ArrayList<>();
        data.add(new CarImageItem(
                "https://images.unsplash.com/photo-1525609004556-c46c7d6cf023?w=800",
                "BMW M3",
                "https://www.bmw.com/en/bmw-models/m3-sedan.html"
        ));

        data.add(new CarImageItem(
                "https://images.unsplash.com/photo-1502877338535-766e1452684a?w=800",
                "Porsche 911",
                "https://www.porsche.com/international/models/911/"
        ));
        // În ImageListActivity, metoda onCreate(), lista “data.add(...)”:


        data.add(new CarImageItem(
                "https://images.unsplash.com/photo-1588072432836-e10032774350?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
                "Audi R8 (Urban Edition)",
                "https://www.audi.com/en/models/r8.html"
        )); // :contentReference[oaicite:0]{index=0}

        data.add(new CarImageItem(
                "https://images.unsplash.com/photo-1549924231-f129b911e442?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
                "Tesla Model S (Silver)",
                "https://www.tesla.com/models"
        )); // :contentReference[oaicite:1]{index=1}

        data.add(new CarImageItem(
                "https://images.unsplash.com/photo-1511919884226-fd3cad34687c?w=800",
                "Lamborghini Aventador",
                "https://www.lamborghini.com/en-en/models/aventador"
        ));

        CarImageAdapter adapter = new CarImageAdapter(this, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, pos, id) -> {
            String url = data.get(pos).pageUrl;
            Intent i = new Intent(this, WebViewActivity.class);
            i.putExtra("url", url);
            startActivity(i);
        });
    }
}