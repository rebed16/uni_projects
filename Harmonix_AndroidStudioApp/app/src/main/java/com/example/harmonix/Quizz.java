package com.example.harmonix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Quizz extends AppCompatActivity {

    private ImageView questionImage;
    private TextView questionText;
    private RadioGroup answersGroup;
    private RadioButton answerA, answerB, answerC;
    private Button nextButton, meniu;

    private ArrayList<QuizQuestion> questions;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        meniu = findViewById(R.id.backtomain);
        questionImage = findViewById(R.id.question_image);
        questionText = findViewById(R.id.question_text);
        answersGroup = findViewById(R.id.answers_group);
        answerA = findViewById(R.id.answer_a);
        answerB = findViewById(R.id.answer_b);
        answerC = findViewById(R.id.answer_c);
        nextButton = findViewById(R.id.next_question_btn);

        loadQuestions();
        displayQuestion();

        meniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Quizz.this,MainActivity.class);
                startActivity(intent);
            }
        });
        nextButton.setOnClickListener(v -> {
            int selectedId = answersGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Selectează un răspuns!", Toast.LENGTH_SHORT).show();
                return;
            }

            int userAnswer = -1;
            if (selectedId == answerA.getId()) userAnswer = 0;
            else if (selectedId == answerB.getId()) userAnswer = 1;
            else if (selectedId == answerC.getId()) userAnswer = 2;

            if (userAnswer == questions.get(currentIndex).correctIndex) {
                Toast.makeText(this, "Răspuns corect!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Răspuns greșit!", Toast.LENGTH_SHORT).show();
            }

            currentIndex++;
            if (currentIndex < questions.size()) {
                displayQuestion();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Quiz complet!")
                        .setMessage("Ai terminat quiz-ul!")
                        .setPositiveButton("OK", (dialog, which) -> finish())
                        .show();
            }
        });
    }

    private void loadQuestions() {
        questions = new ArrayList<QuizQuestion>();
        questions.add(new QuizQuestion(R.drawable.equestion1, "1. Ce notă este reprezentată în imaginea dată?",
                new String[]{"do", "mi", "si"}, 1));
        questions.add(new QuizQuestion(R.drawable.equestion2, "2. Ce notă reprezintă clapa marcată cu roșu?",
                new String[]{"la", "re", "sol"}, 0));
        questions.add(new QuizQuestion(R.drawable.equestion3, "3. Cum se numește nota din imagine?",
                new String[]{"notă întreagă", "doime", "optime"}, 2));
        questions.add(new QuizQuestion(R.drawable.equestion4, "4. Ce durată reprezintă pauza din imagine?",
                new String[]{"1 timp", "2 timpi", "o jumătate de timp"}, 0));
        questions.add(new QuizQuestion(R.drawable.equestion5, "5. Ce durată a sunetului reprezintă nota din imagine?",
                new String[]{"1 timp", "2 timpi", "4 timpi"}, 2));
        questions.add(new QuizQuestion(R.drawable.equestion6, "6. Ce notă este reprezentată în imaginea dată?",
                new String[]{"la", "fa", "re"}, 1));
        questions.add(new QuizQuestion(R.drawable.equestion7, "7. Ce notă reprezintă clapa marcată cu roșu?",
                new String[]{"si", "do", "sol"}, 2));
    }

    private void displayQuestion() {
        QuizQuestion current = questions.get(currentIndex);
        questionText.setText(current.question);
        questionImage.setImageResource(current.imageResId);
        answerA.setText("a. " + current.options[0]);
        answerB.setText("b. " + current.options[1]);
        answerC.setText("c. " + current.options[2]);
        answersGroup.clearCheck();
    }
}
