package com.example.laborator7;
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
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.biberon_list_item, parent, false);
        }
        TextView tvBrand = convertView.findViewById(R.id.textViewBiberonBrand);
        TextView tvDetails = convertView.findViewById(R.id.textViewBiberonDetails);
        tvBrand.setText(biberon.getBrand());
        String details = "Capacitate: " + biberon.getCapacitate() + " ml, Rating: " + biberon.getRating();
        tvDetails.setText(details);
        return convertView;
    }
}