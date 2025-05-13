package com.example.laborator2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inițializare componente UI
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextText2);
        button = findViewById(R.id.button);

        // Acțiune la apăsarea butonului
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = editText.getText().toString();
                if (!newText.isEmpty()) {
                    textView.setText(newText);
                } else {
                    textView.setText(getString(R.string.updated_text));
                }
            }
        });
    }
}
