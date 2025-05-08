package com.example.harmonix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NoteleMuzicaleSiDurata extends AppCompatActivity {
    Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notele_muzicale_si_durata);
        Next = findViewById(R.id.next_breaks);
        ImageView durata_sunete = findViewById(R.id.durata_sunete);
        ImageView durata_sunete_nume = findViewById(R.id.durata_sunete_nume);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteleMuzicaleSiDurata.this, Pauze.class);
                startActivity(intent);
            }
        });
    }

}