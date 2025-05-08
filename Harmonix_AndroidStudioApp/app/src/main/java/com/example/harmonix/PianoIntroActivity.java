package com.example.harmonix;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class PianoIntroActivity extends AppCompatActivity {

    RelativeLayout pianoLayout;
    Handler handler = new Handler();
    MediaPlayer[] sounds = new MediaPlayer[5];
    View[] whiteKeys = new View[3];
    View[] blackKeys = new View[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creează layout-ul principal
        pianoLayout = new RelativeLayout(this);
        pianoLayout.setBackgroundColor(Color.parseColor("#F1C0EE"));

        // Container pentru clapele albe
        LinearLayout whiteKeyLayout = new LinearLayout(this);
        whiteKeyLayout.setOrientation(LinearLayout.HORIZONTAL);
        whiteKeyLayout.setPadding(0, 0, 0, 0);
        whiteKeyLayout.setGravity(android.view.Gravity.CENTER);

        // Parametrii pentru plasarea clapei
        RelativeLayout.LayoutParams whiteParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        whiteParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        // Mărimi pentru clapele albe
        int whiteWidth = 150;
        int whiteHeight = 500;

        // Creăm 3 clape albe
        for (int i = 0; i < 3; i++) {
            View key = new View(this);
            key.setBackgroundColor(Color.WHITE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(whiteWidth, whiteHeight);
            params.setMargins(10, 0, 10, 0); // Margini între clape
            key.setLayoutParams(params);
            whiteKeys[i] = key;
            whiteKeyLayout.addView(key);
        }

        pianoLayout.addView(whiteKeyLayout, whiteParams);

        // Clape negre (Do#, Re#)
        int blackWidth = 100;
        int blackHeight = 300;

        // DO# - Clapa neagră între Do și Re
        View doSharp = new View(this);
        doSharp.setBackgroundColor(Color.BLACK);
        RelativeLayout.LayoutParams doSharpParams = new RelativeLayout.LayoutParams(blackWidth, blackHeight);
        doSharpParams.addRule(RelativeLayout.ALIGN_TOP, whiteKeys[0].getId()); // Plasăm clapa neagră deasupra Do
        doSharpParams.addRule(RelativeLayout.ALIGN_BOTTOM, whiteKeys[0].getId());
        doSharpParams.setMargins(400, 950, 0, 0); // Poziționare între Do și Re
        blackKeys[0] = doSharp;
        pianoLayout.addView(doSharp, doSharpParams);

        // RE# - Clapa neagră între Re și Mi
        View reSharp = new View(this);
        reSharp.setBackgroundColor(Color.BLACK);
        RelativeLayout.LayoutParams reSharpParams = new RelativeLayout.LayoutParams(blackWidth, blackHeight);
        reSharpParams.addRule(RelativeLayout.ALIGN_TOP, whiteKeys[1].getId()); // Plasăm clapa neagră deasupra Re
        reSharpParams.addRule(RelativeLayout.ALIGN_BOTTOM, whiteKeys[1].getId());
        reSharpParams.setMargins(570, 950, 0, 0); // Poziționare între Re și Mi
        blackKeys[1] = reSharp;
        pianoLayout.addView(reSharp, reSharpParams);

        // Setăm layout-ul ca și content view
        setContentView(pianoLayout);

        // Încarcă sunetele
        sounds[0] = MediaPlayer.create(this, R.raw.do_note);
        sounds[1] = MediaPlayer.create(this, R.raw.do_sharp_note);
        sounds[2] = MediaPlayer.create(this, R.raw.re_note);
        sounds[3] = MediaPlayer.create(this, R.raw.re_sharp_note);
        sounds[4] = MediaPlayer.create(this, R.raw.mi_note);

        // Animația de apăsare a clapelelor
        animateKeys();
    }

    private void animateKeys() {
        final int[] order = {0, 1, 2, 3, 4}; // DO, DO#, RE, RE#, MI
        final int delay = 700;

        // Apăsăm clapele pe rând
        for (int i = 0; i < order.length; i++) {
            final int index = order[i];
            handler.postDelayed(() -> pressKey(index), i * delay);
        }

        // După ce animatia se încheie, ducem utilizatorul la MainActivity
        handler.postDelayed(() -> {
            Intent intent = new Intent(PianoIntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, order.length * delay + 1000);
    }

    private void pressKey(int index) {
        View keyView;
        if (index < 3) {
            keyView = whiteKeys[index];
        } else {
            keyView = blackKeys[index - 3];
        }

        // Apăsăm vizual clapele
        keyView.setAlpha(0.4f);
        sounds[index].start();

        // Restaurăm transparența după 300ms
        handler.postDelayed(() -> keyView.setAlpha(1f), 300);
    }
}
