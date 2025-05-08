package com.example.harmonix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class QuizzMediu extends AppCompatActivity {

    int score = 0;
    int questionIndex = 0;

    String[] questions = {
            "Ce notă este reprezentată în imaginea dată?",
            "Ce notă este reprezentată în imaginea dată?",
            "Ce notă este reprezentată în imaginea dată?",
            "Ce notă este reprezentată în imaginea dată?",
            "Ce este reprezentat în imaginea dată?",             // 5
            "Ce notă avem în imaginea dată?",                    // 6
            "Ce notă avem în imaginea dată?",                    // 7
            "Ce notă este cea marcată cu roșu în imaginea dată?"                     // 8
    };

    int[] images = {
            R.drawable.qmediu1,
            R.drawable.qmediu2,
            R.drawable.qmediu3,
            R.drawable.qmediu4,
            R.drawable.becar,  // 5 - simbol (diez/bemol)
            R.drawable.fa_diez,  // 6 - fa diez, fa bemol, mi
            R.drawable.si_bemol,
            R.drawable.bemolbecar
    };

    String[][] choices = {
            {"do", "re", "mi"},
            {"re", "si", "do"},
            {"la", "fa", "mi"},
            {"fa", "la", "sol"},
            {"diez", "bemol", "becar"},
            {"fa diez", "fa bemol", "mi diez"},
            {"si diez", "si bemol", "do major"},
            {"si bemol", "si diez", "si"}

    };

    String[] correctAnswers = {"re", "do", "fa", "sol", "becar", "fa diez", "si bemol", "si"};

    TextView questionText;
    ImageView questionImage;
    RadioGroup answersGroup;
    RadioButton answer1, answer2, answer3;
    Button nextBtn, backtomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_mediu);

        questionText = findViewById(R.id.question_text);
        questionImage = findViewById(R.id.question_image);
        answersGroup = findViewById(R.id.answers_group);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        nextBtn = findViewById(R.id.next_button);
        backtomain = findViewById(R.id.backtomainm);

        loadQuestion();

        nextBtn.setOnClickListener(v -> {
            int selectedId = answersGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Selectează un răspuns!", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedAnswer = findViewById(selectedId);
            if (selectedAnswer.getText().toString().equals(correctAnswers[questionIndex])) {
                score++;
            }

            questionIndex++;
            if (questionIndex < questions.length) {
                loadQuestion();
            } else {
                Toast.makeText(this, "Scor final: " + score + "/" + questions.length, Toast.LENGTH_LONG).show();
                // Poți redirecționa sau închide activitatea
                finish();
            }
        });
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QuizzMediu.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private void loadQuestion() {
        questionText.setText(questions[questionIndex]);
        questionImage.setImageResource(images[questionIndex]);
        answer1.setText(choices[questionIndex][0]);
        answer2.setText(choices[questionIndex][1]);
        answer3.setText(choices[questionIndex][2]);
        answersGroup.clearCheck();
    }
}
