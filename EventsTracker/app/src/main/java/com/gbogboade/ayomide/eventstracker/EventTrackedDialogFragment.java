package com.gbogboade.ayomide.eventstracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventTrackedDialogFragment extends DialogFragment {
    private Handler handler;
    private Runnable runnable;
    public String EVENT_DATE;
    public String EVENT_TITLE;

    public TextView eventTitleTextView;
    private TextView daysTextView;
    private TextView hoursTextView;
    private TextView minutesTextView;
    private TextView secondsTextView;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View countdownLayout = getActivity().getLayoutInflater().inflate(
                R.layout.activity_event_count_down, null);
        eventTitleTextView = (TextView) countdownLayout.findViewById(R.id.event_title_textView);
        daysTextView = (TextView) countdownLayout.findViewById(R.id.days_textView);
        hoursTextView = (TextView) countdownLayout.findViewById(R.id.hours_textView);
        minutesTextView = (TextView) countdownLayout.findViewById(R.id.minutes_textView);
        secondsTextView = (TextView) countdownLayout.findViewById(R.id.seconds_textView);
        eventCountDownStart();
        eventTitleTextView.setText(EVENT_TITLE);
        builder.setView(countdownLayout);
        builder.setTitle(getString(R.string.event_countdown_dialog_title));
        builder.setPositiveButton("Got it!", null);
        return builder.create();
    }

    public void setEventDate(String EVENT_DATE) {
        this.EVENT_DATE = EVENT_DATE;
    }

    public void setEventTitle(String eventTitle) {
        this.EVENT_TITLE = eventTitle;
    }

    private void eventCountDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");
                    Date futureDate = dateFormat.parse(EVENT_DATE);
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;

                        daysTextView.setText(String.format("%s", String.format("%02d", days)));
                        minutesTextView.setText(String.format("%s", String.format("%02d", minutes)));
                        hoursTextView.setText("" + String.format("%02d", hours));
                        secondsTextView.setText("" + String.format("%02d", seconds));
                    } else {
                        //tvEventStart.setVisibility(View.VISIBLE);
                        //tvEventStart.setText("The event started!");
                        //textViewGone();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }
}