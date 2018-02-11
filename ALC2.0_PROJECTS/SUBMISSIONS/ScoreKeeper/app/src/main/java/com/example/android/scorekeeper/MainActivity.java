package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int teamAScore = 0;
    private int teamBScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method updated the points scored by Team A.
     *
     * @param currentScore
     */
    private void teamAScoreDisplay(int currentScore) {
        TextView t = (TextView) findViewById(R.id.team_a_score_textView);
        t.setText(currentScore);
    }

    /**
     * This method updated the points scored by Team B.
     *
     * @param currentScore
     */
    private void teamBScoreDisplay(int currentScore) {
        TextView t = (TextView) findViewById(R.id.team_b_score_textView);
        t.setText(currentScore);
    }

    /**
     * The scoring team gets 3 points.
     *
     * @param v
     */
    public void fieldGoalScored(View v) {
        switch (v.getId()) {
            case R.id.team_a_field_goal_button: {
                teamAScore += 3;
                teamAScoreDisplay(teamAScore);
            }
            break;
            case R.id.team_b_field_goal_button:
                teamBScore += 3;
                teamBScoreDisplay(teamBScore);
                break;
            default:
                break;
        }
    }

    /**
     * The scoring team gets 6 points.
     * @param v
     */
    public void touchDownScored(View v) {
        switch (v.getId()) {
            case R.id.team_a_touchdown_button:
                teamAScore += 6;
                teamAScoreDisplay(teamAScore);
                break;
            case R.id.team_b_touchdown_button:
                teamBScore += 6;
                teamBScoreDisplay(teamBScore);
                break;
            default:
                break;
        }
    }

    /**
     * The scoring team gets 2 points.
     * @param v
     */
    public void safetyScored(View v) {
        switch (v.getId()) {
            case R.id.team_a_safety_button:
                teamAScore += 2;
                teamAScoreDisplay(teamAScore);
                break;
            case R.id.team_b_safety_button:
                teamBScore += 2;
                teamBScoreDisplay(teamBScore);
                break;
            default:
                break;
        }
    }

    /**
     * The scoring team gets 1 if its a  kick and 2 if its a pass.
     * @param v
     */
    public void extraPointScored(View v) {
        switch (v.getId()) {
            case R.id.team_a_extra_point_kick_button:
                teamAScore += 1;
                teamAScoreDisplay(teamAScore);
                break;
            case R.id.team_b_extra_point_kick_button:
                teamBScore += 1;
                teamBScoreDisplay(teamBScore);
                break;
            case R.id.team_a_extra_point_pass_button:
                teamAScore += 2;
                teamAScoreDisplay(teamAScore);
                break;
            case R.id.team_b_extra_point_pass_button:
                teamBScore += 2;
                teamBScoreDisplay(teamBScore);
                break;
            default:
                break;
        }
    }

    /**
     *Scores reset
     * @param v
     */
    public void reset(View v){
        teamAScore = 0;
        teamBScore = 0;
        teamAScoreDisplay(teamAScore);
        teamBScoreDisplay(teamBScore);
    }

}








