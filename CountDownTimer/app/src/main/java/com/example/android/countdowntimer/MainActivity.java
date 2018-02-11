package com.example.android.countdowntimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private TextView txtDay, txtHour, txtMinute, txtSecond;
    private TextView tvEventStart;
    private Handler handler;
    private Runnable runnable;
    //format yyyy-MM-dd
    private String EVENT_DATE = "2018-1-20";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDay = (TextView) findViewById(R.id.txtDay);
        txtHour = (TextView) findViewById(R.id.txtHour);
        txtMinute = (TextView) findViewById(R.id.txtMinute);
        txtSecond = (TextView) findViewById(R.id.txtSecond);
        tvEventStart = (TextView) findViewById(R.id.tvEventStart);

        countDownStart();
    }

    private void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    Date futureDate = dateFormat.parse(EVENT_DATE);
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days *  (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtDay.setText("" + String.format("%02d", days));
                        txtMinute.setText("" + String.format("%02d", minutes));
                        txtHour.setText("" + String.format("%02d", hours));
                        txtSecond.setText("" + String.format("%02d", seconds));
                    } else{
                        tvEventStart.setVisibility(View.VISIBLE);
                        tvEventStart.setText("The event started!");
                        textViewGone();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    private void textViewGone() {
        findViewById(R.id.linearLayout1).setVisibility(View.GONE);
        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
        findViewById(R.id.linearLayout3).setVisibility(View.GONE);
        findViewById(R.id.linearLayout4).setVisibility(View.GONE);
        findViewById(R.id.textViewHeader1).setVisibility(View.GONE);
        findViewById(R.id.textViewHeader2).setVisibility(View.GONE);

    }
}
