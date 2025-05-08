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

import com.bumptech.glide.Glide;

public class Mediu extends AppCompatActivity {

    Button Next, playButton;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mediu);
        Next = findViewById(R.id.next_fa);
        ImageView cheia_sol = findViewById(R.id.cheia_sol);
        ImageView cheia_fa = findViewById(R.id.cheia_fa);
        playButton = findViewById(R.id.play_cheiafa);

        mediaPlayer = MediaPlayer.create(this, R.raw.cheia_fa);

        playButton.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release(); // eliberează resursele
                    mediaPlayer = null;
                }
                Intent intent = new Intent(Mediu.this,CheiaFa.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}