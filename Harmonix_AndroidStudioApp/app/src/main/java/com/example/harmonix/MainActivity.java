package com.example.harmonix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnUsor, btnMediu, btnAvansat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUsor = findViewById(R.id.btn_usor);
        btnMediu = findViewById(R.id.btn_mediu);
        btnAvansat = findViewById(R.id.btn_avansat);

        btnUsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Usor.class);
                startActivity(intent);
            }
        });
        btnMediu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Mediu.class);
                startActivity(intent);
            }
        });
        btnAvansat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Avansat.class);
                startActivity(intent);
            }
        });
    }
}
