package com.example.lab9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.*;
import android.widget.*;
import java.net.URL;
import java.util.List;
import java.util.concurrent.*;

public class CarImageAdapter extends ArrayAdapter<CarImageItem> {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public CarImageAdapter(Context ctx, List<CarImageItem> items) {
        super(ctx, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_car_image, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.rowImage);
        TextView txt = convertView.findViewById(R.id.rowText);
        CarImageItem item = getItem(position);

        txt.setText(item.title);
        img.setImageResource(android.R.color.darker_gray);

        // Încarcă imaginea în background
        executor.submit(() -> {
            try {
                URL url = new URL(item.imageUrl);
                Bitmap bmp = BitmapFactory.decodeStream(url.openStream());
                img.post(() -> img.setImageBitmap(bmp));
            } catch (Exception ignored) { }
        });

        return convertView;
    }
}