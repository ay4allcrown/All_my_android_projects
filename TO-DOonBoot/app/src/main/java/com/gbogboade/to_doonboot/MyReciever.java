package com.gbogboade.to_doonboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by DELL on 12/13/2017.
 */
public class MyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Todo on boot", Toast.LENGTH_SHORT).show();

    }
}
