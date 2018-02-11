package com.gbogboade.articlequiz;

/**
 * Created by GBOGBOADE on 12/2/2017.
 */
public class AddQuestion {
    public String question;
    public String answer;


    public AddQuestion() {
        this.question = "";
        this.answer = "";
    }

    public AddQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
