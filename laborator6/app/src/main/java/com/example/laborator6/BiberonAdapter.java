package com.example.laborator6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class BiberonAdapter extends ArrayAdapter<Biberon> {

    public BiberonAdapter(Context context, List<Biberon> biberoane) {
        super(context, 0, biberoane);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Biberon biberon = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_biberon_adapter, parent, false);
        }

        TextView tvBrand = convertView.findViewById(R.id.textViewBrand);
        TextView tvDetails = convertView.findViewById(R.id.textViewDetails);

        tvBrand.setText(biberon.getBrand());
        String details = "Capacitate: " + biberon.getCapacitate() + " ml\n" +
                "Material: " + biberon.getMaterial() + "\n" +
                "Rating: " + biberon.getRating();
        tvDetails.setText(details);

        return convertView;
    }
}