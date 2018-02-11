package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int question_number = 0;
    private int TOTAL_QUESTIONS = 10;
    private String question = "";


    private int score = 0;

    private String[][] questions = {
            //1
            {"How many languages and dialects are spoken by people all over the world?",
                    "A. 6,000", "B. 9,000", "C. 4,000", "D. 1,000"},
            //2
            {"Approximately, how many people speak Chinese language?",
                    "A. 1 billion", "B. 1 million", "C. 1 lakh", "D. 1 thousand"},
            //3
            {"The language with the richest vocabulary is:",
                    "A. Hindi", "B. French", "C. English", "D. German"},
            //4
            {"English Language have more than ?? words:",
                    "A. 4,50,000", "B. 45,000", "C. 4,500", "D. 450"},
            //5
            {"The oldest Indian language is:",
                    "A. Telugu", "B. Hindu", "C. Tamil", "D. Punjabi"},
            //6
            {"Which book has been printed in the maximum number of languages and these scripts?",
                    "A. The Bible", "B. Hiraka Sutra", "C. The Super Book", "D. None of these"},
            //7
            {"The only religious book ever printed in a shorthand scripts is:",
                    "A. The Ramayana", "B. The Mahabharata", "C. The bible", "D. Guru Granth Sahib"},
            //8
            {"The oldest printed work in the world, which dates back to AD 868 is:",
                    "A. The Bible", "B. The Hirake Sutra", "C. The Ramayana", "D. The Mahabharata"},
            //9
            {"The largest book, the super book, is ?? and weight is ??",
                    "A. 270 cm, 300 cm, 252 kg.", "B. 100 cm, 110 cm, 100 kg.",
                    "C. 200 cm, 100 cm, 60 kg.", "D. None of these"},
            //10
            {"Les Hommes de bonne volonté is the:",
                    "A. Longest novel ever published", "B. Shortest novel every published",
                    "C. The oldest novel", "D. None of these"}
    };

    private String[] correctAnswers = {"B", "A", "C", "A", "C", "A", "C", "B", "A", "A"};
    private String[] userInput = new String[TOTAL_QUESTIONS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showQuestion(question_number + 1, questions[question_number][0],
                questions[question_number][1], questions[question_number][2],
                questions[question_number][3], questions[question_number][4]);

        ++question_number;
    }

    public void nextQuestion(View v) {
        if (question_number == TOTAL_QUESTIONS){
            calculateScore();
            return;
        }
        userInput[question_number] = userAnswer();
        showQuestion(question_number + 1, questions[question_number][0],
                questions[question_number][1], questions[question_number][2],
                questions[question_number][3], questions[question_number][4]);

        ++question_number;
    }

    public void prevQuestion(View v) {

    }

    private void showQuestion(int questionNumber, String question, String optA, String optB,
                              String optC, String optD) {
        TextView questionNumberTextView = (TextView) findViewById(R.id.question_number_textView);
        questionNumberTextView.setText(getString(R.string.question_number,
                questionNumber, TOTAL_QUESTIONS));

        TextView questionTextView = (TextView) findViewById(R.id.question_textView);
        questionTextView.setText(question);
        RadioButton[] radioButtons = new RadioButton[4];
        radioButtons[0] = (RadioButton) findViewById(R.id.option_a_radioButton);
        radioButtons[0].setText(optA);
        radioButtons[1] = (RadioButton) findViewById(R.id.option_b_radioButton);
        radioButtons[1].setText(optB);
        radioButtons[2] = (RadioButton) findViewById(R.id.option_c_radioButton);
        radioButtons[2].setText(optC);
        radioButtons[3] = (RadioButton) findViewById(R.id.option_d_radioButton);
        radioButtons[3].setText(optD);
    }

    private String userAnswer() {
        RadioButton[] radioButtons = new RadioButton[4];
        radioButtons[0] = (RadioButton) findViewById(R.id.option_a_radioButton);
        radioButtons[1] = (RadioButton) findViewById(R.id.option_b_radioButton);
        radioButtons[2] = (RadioButton) findViewById(R.id.option_c_radioButton);
        radioButtons[3] = (RadioButton) findViewById(R.id.option_d_radioButton);

        for (RadioButton r : radioButtons) {
            if (r.isChecked()) {
                r.setChecked(false);
                return r.getText().toString().substring(0, 1);
            }
        }
        return "";
    }

    private void calculateScore() {
        int score = 0;
        for (int i = 0; i < TOTAL_QUESTIONS; i++) {
            if (correctAnswers[i].equalsIgnoreCase(userInput[i])) {
                ++score;
            }
        }
        findViewById(R.id.options_radiogroup).setVisibility(View.INVISIBLE);
        findViewById(R.id.question_number_textView).setVisibility(View.INVISIBLE);
        findViewById(R.id.navigation_button_layout).setVisibility(View.INVISIBLE);
        TextView t = (TextView) findViewById(R.id.question_textView);
        t.setText(String.format(getString(R.string.score), score, TOTAL_QUESTIONS));
    }
}
