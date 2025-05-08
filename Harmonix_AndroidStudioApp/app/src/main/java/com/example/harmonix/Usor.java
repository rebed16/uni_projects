package com.example.harmonix;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import android.widget.Toast;

public class Usor extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button playButton, Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usor);

        Next=findViewById(R.id.next_break);
        ImageView gifView = findViewById(R.id.keyboard_image);
        Glide.with(this).asGif().load(R.drawable.piano_keys).into(gifView);
        ImageView notesImage = findViewById(R.id.notes_image);

        playButton = findViewById(R.id.play_audio_btn);

        mediaPlayer = MediaPlayer.create(this, R.raw.notes_audio);

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
                //Toast.makeText(Usor.this, "Se apasa butonul!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Usor.this, NoteleMuzicaleSiDurata.class);
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
