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

public class Pauze extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button playButton, Quizz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pauze);
        ImageView pauze = findViewById(R.id.pauze);
        playButton = findViewById(R.id.play_simfonia5);
        Quizz = findViewById(R.id.quizz);
        mediaPlayer = MediaPlayer.create(this, R.raw.symphony5);

        playButton.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        });
        Quizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release(); // eliberează resursele
                    mediaPlayer = null;
                }
                Intent intent = new Intent(Pauze.this, Quizz.class);
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