package com.gbogboade.articlequiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by DELL on 12/5/2017.
 */
public class ScoreFragment extends DialogFragment{

    public ScoreFragment() {
    }

    public int score;
    public int totalQ;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQ() {
        return totalQ;
    }

    public void setTotalQ(int totalQ) {
        this.totalQ = totalQ;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View scoreView = getActivity().getLayoutInflater().inflate(
                R.layout.score_fragment, null);
        builder.setView(scoreView);
        builder.setTitle(getString(R.string.your_score));

        builder.setPositiveButton(getString(R.string.retake_quiz_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivityFragment.setQuestID(0);
            }
        });

        TextView scoreDetail = (TextView) scoreView.findViewById(R.id.scoreTextView);
        scoreDetail.setText(getString(R.string.score_detail, score, totalQ));

        RatingBar scoreRating = (RatingBar) scoreView.findViewById(R.id.scoreRatingBar);
        scoreRating.setIsIndicator(true);
        double s = (score * 5) / totalQ ;
        scoreRating.setRating((float) s);

        return builder.show();
    }
}
