package com.example.harmonix;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheiaFa extends AppCompatActivity {

    Button Next, playf, plays;
    MediaPlayer mediaPlayers, mediaPlayerf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cheia_fa);
        Next = findViewById(R.id.bemoldiez);
        ImageView cheia_fa_portativ = findViewById(R.id.cheia_fa_portativ);
        ImageView cheia_fa_vs_cheia_sol = findViewById(R.id.cheia_fa_vs_cheia_sol);
        playf = findViewById(R.id.listenf);
        plays = findViewById(R.id.listens);
        mediaPlayers = MediaPlayer.create(this, R.raw.notes_audio); //cheia sol
        mediaPlayerf = MediaPlayer.create(this, R.raw.cheia_fa); // cheia fa

        plays.setOnClickListener(v -> {
            if (mediaPlayerf != null && mediaPlayerf.isPlaying()) {
                mediaPlayerf.stop();
                mediaPlayerf.release();
                mediaPlayerf = null;
            }
            if (mediaPlayers != null) {
                mediaPlayers.start();
            }
        });
        playf.setOnClickListener(v -> {
            if (mediaPlayers != null && mediaPlayers.isPlaying()) {
                mediaPlayers.stop();
                mediaPlayers.release(); // eliberează resursele
                mediaPlayers = null;
            }
            if (mediaPlayerf != null) {
                mediaPlayerf.start();
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((mediaPlayers != null && mediaPlayers.isPlaying())||(mediaPlayerf != null && mediaPlayerf.isPlaying())) {
                    mediaPlayers.stop();
                    mediaPlayerf.stop();
                    mediaPlayers.release(); // eliberează resursele
                    mediaPlayerf.release();
                    mediaPlayers = null;
                    mediaPlayerf = null;
                }
                Intent intent = new Intent(CheiaFa.this,BemolDiez.class);
                startActivity(intent);
            }
        });
    }
}