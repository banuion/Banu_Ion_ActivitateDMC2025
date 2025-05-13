package com.example.laborator3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ActivityOne extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }
}
//1. Continuati in proiectul din laboratorul precedent sau creați unul nou în Android Studio cu API 23 sau API 24. Realizati o activitate noua pe care sa o setati ca activitate implicita.
//
//        2. Implementați metodele pentru prezentarea ciclului de viață al unei activități: onStart(), onResume(), onPause(), onStop().
//
//        3. In cadrul fiecărei metode salvați un log de tipul error, warning, debug, info: Log.e(), Log.w(), Log.d(), Log.i(), Log.v().
//
//        4. Filtrați si căutați logurile salvate în tab-ul Logat.
//
//        5. Adăugați o nouă activitate în cadrul proiectului. Setați din AndroidManifest ca noua activitate să fie lansată la deschiderea aplicației.
//
//        6. Adăugați o a treia activitate în cadrul proiectului.
//
//        7. În cea de a doua activitate adăugați un Button. La apăsarea butonului respectiv deschideți cea de a treia activitate printr-un Intent.
//
//        8. La deschiderea activității trimiteți un mesaj și două valori de tip întreg către activitatea deschisă printr-un Bundle.
//
//        9. În activitatea nou deschisă preluați informațiile trimise din activitatea precedentă și le afișați într-un Toast.
//
//        10. În activitatea nou deschisa trebuie sa aveți un buton care să trimită către activitatea precedentă un alt mesaj si suma celor două valori primite.
//
//        11. În activitatea inițială vor fi afișate printr-un Toast mesajul primit și valoarea calculată.