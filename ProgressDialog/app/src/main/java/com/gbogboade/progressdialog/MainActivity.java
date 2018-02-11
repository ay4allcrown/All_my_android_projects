package com.gbogboade.progressdialog;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = new ProgressDialog(this);
    }
    public void open(View view){
        progress.setMessage("Downloading book...");
        progress.setProgress(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        final int totalProgressTime = 100;

        final Thread t = new Thread(){
            @Override
            public void run() {
                int jumpTime = 0;
                while (jumpTime < totalProgressTime){
                    try{
                        sleep(200);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
























