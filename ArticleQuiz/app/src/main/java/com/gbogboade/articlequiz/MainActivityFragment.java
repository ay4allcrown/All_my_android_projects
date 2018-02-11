package com.gbogboade.articlequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {}

    private static int questID = 1;
    private final int totalQuest =10;

    private RadioButton[] radioButtons;
    private TextView questTextview;
    private TextView  questIDTextview;
    private Button prevButton;
    private Button nextButton;

    private ArrayList<String> correctAns;
    private ArrayList<AddQuestion> selectedQ;
    private ArrayList<String> userAnswer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        //radioGroup = (RadioGroup) view.findViewById(R.id.options_radioGroup);
        radioButtons = new RadioButton[3];
        radioButtons[0] = (RadioButton) view.findViewById(R.id.optA_radioButton);
        radioButtons[1] = (RadioButton) view.findViewById(R.id.optB_radioButton);
        radioButtons[2] = (RadioButton) view.findViewById(R.id.optC_radioButton);

        questIDTextview = (TextView) view.findViewById(R.id.questionID_textView);
        questTextview = (TextView) view.findViewById(R.id.question_textView);

        prevButton = (Button) view.findViewById(R.id.prev_button);
        nextButton = (Button) view.findViewById(R.id.next_button);

        prevButton.setOnClickListener(prevButtonClicked);
        nextButton.setOnClickListener(nextButtonClicked);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        selectQuestions();
        loadQuestion(questID);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getQuestID() == 0){
            selectQuestions();
            loadQuestion(1);
        }
    }

    private final View.OnClickListener prevButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (questID > 1){
                --questID;
                prevSelected(userAnswer.get(questID));
                loadQuestion(questID);
            }
        }
    };

    private final View.OnClickListener nextButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (questID < totalQuest){
                userAnswer.set(questID, getUserAnswer());
                ++questID;
                resetButtons();
                loadQuestion(questID);
            }
            else if (questID == totalQuest){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.confirm_submit);
                builder.setTitle(R.string.confirm_submit_title);
                builder.setPositiveButton(R.string.button_submit,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ScoreFragment score = new ScoreFragment();
                                score.setScore(getScore());
                                score.setTotalQ(getTotalQuest());
                                score.show(getFragmentManager(), "score display");

                                restartQuiz();
                            }
                        }
                );
                builder.setNegativeButton(R.string.button_cancel_submit, null);
                builder.show();
            }
        }
    };


    private void selectQuestions(){

        ArrayList<AddQuestion> actualQ = questionSet();
        selectedQ = new ArrayList<>();
        correctAns = new ArrayList<>();
        userAnswer = new ArrayList<>();
        try {
            Collections.shuffle(actualQ);
            for (int i = 0; i < totalQuest; i++){
                selectedQ.add(actualQ.get(i));
            }
            for (int i = 0; i < totalQuest; i++){
                correctAns.add(selectedQ.get(i).getAnswer());
                userAnswer.add("NOT SURE");
            }
        }
        catch (NullPointerException n){
            questTextview.setText(R.string.error_selecting_questions);
        }
    }

    private void loadQuestion(int qID){

        try{
            String que = selectedQ.get(qID - 1).getQuestion();

            questIDTextview.setText(getString(R.string.questionID, qID , totalQuest));
            questTextview.setText(que);

            if (qID == totalQuest){
                nextButton.setText(getText(R.string.submit_button));
            }
            else {
                nextButton.setText(getString(R.string.next));
            }
        }
        catch (NullPointerException e){
            questTextview.setText(R.string.error_loading_question);
        }
        catch (ArrayIndexOutOfBoundsException a){
            questTextview.setText(R.string.error_loading_question);
        }

    }

    public static ArrayList questionSet(){
        ArrayList<AddQuestion> questions = new ArrayList<>();
        questions.add(new AddQuestion("____ cup","A"));
        questions.add(new AddQuestion("___ egg", "AN"));
        questions.add(new AddQuestion("___ elephant", "AN"));
        questions.add(new AddQuestion("___ onion", "AN"));
        questions.add(new AddQuestion("___ ink", "AN"));
        questions.add(new AddQuestion("___ ant", "AN"));
        questions.add(new AddQuestion("___ hill", "A"));
        questions.add(new AddQuestion("___ tree", "A"));
        questions.add(new AddQuestion("___ computer", "A"));
        questions.add(new AddQuestion("___ bottle", "A"));
        questions.add(new AddQuestion("___ paper", "A"));
        questions.add(new AddQuestion("___ hen", "A"));
        questions.add(new AddQuestion("___ wool", "A"));
        questions.add(new AddQuestion("___ bag", "A"));
        questions.add(new AddQuestion("___ incubator", "AN"));
        questions.add(new AddQuestion("___ oil", "AN"));
        questions.add(new AddQuestion("___ ox", "AN"));

        return questions;
    }

    private void resetButtons(){
        radioButtons[0].setChecked(false);
        radioButtons[1].setChecked(false);
        radioButtons[2].setChecked(true);
    }

    private int calculateScore(){
        int score = 0;
        for (int i = 0; i < totalQuest;  i++){
            String c = correctAns.get(i);
            String u = userAnswer.get(i);
            if (c.equals(u)){
                score = score + 1;
            }
        }

        return score;
    }

    public int getScore(){
        return calculateScore();
    }

    public int getTotalQuest(){
        return  totalQuest;
    }

    private void prevSelected(String v){
        switch (v){
            case "A":
                radioButtons[0].setChecked(true);
                break;
            case "AN":
                radioButtons[1].setChecked(true);
                break;
            case "NOT SURE":
                radioButtons[2].setChecked(true);
                break;
        }
    }

    public static int getQuestID() {
        return questID;
    }

    private String getUserAnswer(){
        String answer = "";
        for (RadioButton r : radioButtons){
            if (r.isChecked()){
                answer = r.getText().toString();
            }
        }

        return answer;
    }

    public static void setQuestID(int q) {
        questID = q;
    }

    public void restartQuiz(){
        questID = 1;
        selectQuestions();
        loadQuestion(questID);
        questID++;
    }

}


















