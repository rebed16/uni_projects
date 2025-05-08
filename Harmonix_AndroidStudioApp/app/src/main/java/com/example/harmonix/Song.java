// Avansat.java
package com.example.harmonix;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Song extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private final int[] whiteNotes = {
            R.raw.do_note, R.raw.re_note, R.raw.mi_note, R.raw.fa_note,
            R.raw.sol_note, R.raw.la_note, R.raw.si_note, R.raw.do_note_octave
    };

    private final int[] blackNotes = {
            R.raw.do_sharp_note, R.raw.re_sharp_note, R.raw.piano_demo1,
            R.raw.cheia_fa, R.raw.notes_audio
    };

    private final int[] blackPositions = {
            85, 210, 460, 585, 710 // pixels, adjust as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        LayoutInflater inflater = LayoutInflater.from(this);

        LinearLayout whiteLayout = findViewById(R.id.white_keys_layout);
        FrameLayout blackOverlay = findViewById(R.id.black_keys_overlay);

        // White keys
        for (int i = 0; i < whiteNotes.length; i++) {
            View key = inflater.inflate(R.layout.white_key, whiteLayout, false);
            final int note = whiteNotes[i];
            key.setOnClickListener(v -> playNote(note));
            whiteLayout.addView(key);
        }

        // Black keys
        for (int i = 0; i < blackNotes.length; i++) {
            View key = inflater.inflate(R.layout.black_key, blackOverlay, false);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(80, 300);
            params.leftMargin = blackPositions[i];
            key.setLayoutParams(params);
            final int note = blackNotes[i];
            key.setOnClickListener(v -> playNote(note));
            blackOverlay.addView(key);
        }

        findViewById(R.id.watch_video_btn).setOnClickListener(v -> showVideoDialog());
        findViewById(R.id.next_btn).setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.stop();
            Intent intent = new Intent(Song.this, twinkletwinkle.class);
            startActivity(intent);
        });
    }

    private void playNote(int resId) {
        if (mediaPlayer != null) mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(this, resId);
        mediaPlayer.start();
    }

    private void showVideoDialog() {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_video);

        VideoView videoView = dialog.findViewById(R.id.video_view);
        ImageView closeBtn = dialog.findViewById(R.id.close_button);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.piano_demo1);
        videoView.setVideoURI(videoUri);

        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(false);
            videoView.start();
        });

        closeBtn.setOnClickListener(v -> {
            videoView.stopPlayback();
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.release();
    }
}
