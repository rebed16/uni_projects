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

public class BemolDiez extends AppCompatActivity {

    Button Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bemol_diez);
        Next = findViewById(R.id.qmediu);
        ImageView bemol = findViewById(R.id.bemol);
        ImageView diez = findViewById(R.id.diez);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BemolDiez.this,QuizzMediu.class);
                startActivity(intent);
            }
        });

    }
}