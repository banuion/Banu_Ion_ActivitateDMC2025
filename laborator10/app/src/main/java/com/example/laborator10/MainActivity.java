package com.example.laborator10;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.*;
import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {
    private EditText editCity;
    private Spinner spinnerDays;
    private Button btnGetCode, btnGetForecast;
    private TextView textKey, textForecast;
    private String locationKey;
    private final String API_KEY = "oeD16eXCahXQTVBjqCuUf8LrcQCzThC0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCity = findViewById(R.id.editCity);
        spinnerDays = findViewById(R.id.spinnerDays);
        btnGetCode = findViewById(R.id.btnGetCode);
        btnGetForecast = findViewById(R.id.btnGetForecast);
        textKey = findViewById(R.id.textKey);
        textForecast = findViewById(R.id.textForecast);

        btnGetCode.setOnClickListener(v -> new CitySearchTask().execute());
        btnGetForecast.setOnClickListener(v -> new ForecastTask().execute());
    }

    private class CitySearchTask extends android.os.AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                String city = URLEncoder.encode(editCity.getText().toString(), "UTF-8");
                String urlStr = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey="
                        + API_KEY + "&q=" + city;
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) sb.append(line);
                JSONArray arr = new JSONArray(sb.toString());
                if (arr.length() > 0) {
                    JSONObject obj = arr.getJSONObject(0);
                    return obj.getString("Key");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String key) {
            if (key != null) {
                locationKey = key;
                textKey.setText("Location Key: " + key);
            } else {
                textKey.setText("Key not found");
            }
        }
    }

    private class ForecastTask extends android.os.AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            if (locationKey == null) return null;
            try {
                String daysOption = spinnerDays.getSelectedItem().toString();
                String path = daysOption.equals("1 zi") ? "daily/1day/" :
                        daysOption.equals("5 zile") ? "daily/5day/" : "daily/10day/";
                String urlStr = "https://dataservice.accuweather.com/forecasts/v1/" + path
                        + locationKey + "?apikey=" + API_KEY + "&metric=true";
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) sb.append(line);
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            if (json == null) {
                textForecast.setText("No forecast available");
                return;
            }
            try {
                JSONObject root = new JSONObject(json);
                JSONArray days = root.getJSONArray("DailyForecasts");
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < days.length(); i++) {
                    JSONObject day = days.getJSONObject(i);
                    JSONObject temp = day.getJSONObject("Temperature");
                    double min = temp.getJSONObject("Minimum").getDouble("Value");
                    double max = temp.getJSONObject("Maximum").getDouble("Value");
                    result.append(String.format("Ziua %d: min=%.1f°, max=%.1f°\n", i+1, min, max));
                }
                textForecast.setText(result.toString());
            } catch (JSONException e) {
                textForecast.setText("Parse error");
            }
        }
    }
}
