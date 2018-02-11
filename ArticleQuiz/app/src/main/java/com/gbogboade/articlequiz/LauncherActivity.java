package com.gbogboade.articlequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends AppCompatActivity {

    private Button btnTakeQuiz;
    private Button btnLearnArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        btnTakeQuiz = (Button) findViewById(R.id.take_quiz_button);
        btnLearnArticle = (Button) findViewById(R.id.learn_article_button);

        btnTakeQuiz.setOnClickListener(takeQuizClicked);
        btnLearnArticle.setOnClickListener(learnQuizClicked);
    }

    private final View.OnClickListener takeQuizClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.content_main);
        }
    };

    private final View.OnClickListener learnQuizClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
