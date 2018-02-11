package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Stores Team A goals.
    int teamAGoals = 0;

    //Stores Team B goals.
    int teamBGoals = 0;

    //Stores Team A number of yellow cards.
    int teamAYellowCards = 0;

    //Stores Team A number of red cards.
    int teamARedCards = 0;

    //Stores Team B number of yellow cards.
    int teamBYellowCards = 0;

    //Stores Team B number of red cards.
    int teamBRedCards = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method display Team A score.
     */
    private void displayTeamAScore(int score){
        TextView t = (TextView) findViewById(R.id.team_a_score);
        t.setText(score + "");
    }

    /**
     * This method display Team B score.
     */
    private void displayTeamBScore(int score){
        TextView t = (TextView) findViewById(R.id.team_b_score);
        t.setText(score + "");
    }

    /**
     * This method display Team A cards.
     */
    private void displayTeamACards(int yellowCard, int redCard){
        TextView t = (TextView) findViewById(R.id.team_a_cards);
        String cards = "Y " + yellowCard + "\t:\tR " + redCard;
        t.setText(cards);
    }

    /**
     * This method display Team B cards.
     */
    private void displayTeamBCards(int yellowCard, int redCard){
        TextView t = (TextView) findViewById(R.id.team_b_cards);
        String cards = "Y " + yellowCard + "\t:\tR " + redCard;
        t.setText(cards);
    }

    /**
     * Onclick method for Team A goal button.
     */
    public void teamAGoalScored(View view){
        teamAGoals = teamAGoals + 1;
        displayTeamAScore(teamAGoals);
    }

    /**
     * Onclick method for Team B goal button.
     */
    public void teamBGoalScored(View view){
        teamBGoals = teamBGoals + 1;
        displayTeamBScore(teamBGoals);
    }

    /**
     * Onclick for team a yellow card button.
     */
    public void teamAYellowCards(View view){
        teamAYellowCards = teamAYellowCards + 1;
        displayTeamACards(teamAYellowCards, teamARedCards);
    }

    /**
     * Onclick for team b yellow card button.
     */
    public void teamBYellowCards(View view){
        teamBYellowCards = teamBYellowCards + 1;
        displayTeamBCards(teamBYellowCards, teamBRedCards);
    }

    /**
     * Onclick for team a red card button.
     */
    public void teamARedCards(View view){
        teamARedCards = teamARedCards + 1;
        displayTeamACards(teamAYellowCards, teamARedCards);
    }

    /**
     * Onclick for team a red card button.
     */
    public void teamBRedCards(View view){
        teamBRedCards = teamBRedCards + 1;
        displayTeamBCards(teamBYellowCards, teamBRedCards);
    }

    /**
     * reset team a and team b values
     */
    public void reset(View view){
        teamAGoals = 0;
        teamBGoals = 0;
        teamAYellowCards = 0;
        teamARedCards = 0;
        teamBYellowCards = 0;
        teamBRedCards = 0;
        displayTeamAScore(teamAGoals);
        displayTeamBScore(teamBGoals);
        displayTeamACards(teamAYellowCards, teamARedCards);
        displayTeamBCards(teamBYellowCards, teamBRedCards);
    }
}
