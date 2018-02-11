package com.gbogboade.bluetoothstuff;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private Button btnTurnOnOff, btnDiscoverable, btnPairedDevices;
    private ListView pairedDevListView;
    private BluetoothAdapter ba;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ba = BluetoothAdapter.getDefaultAdapter();
        btnTurnOnOff = (Button) findViewById(R.id.turn_on_off_button);
        btnDiscoverable = (Button) findViewById(R.id.discoverable_button);
        btnPairedDevices = (Button) findViewById(R.id.paired_devices_button);
        pairedDevListView = (ListView) findViewById(R.id.paired_devices_listview);
        /*
        if (ba.isEnabled()){
            btnTurnOnOff.setText(getResources().getString(R.string.turn_off));
        }
        else {
            btnTurnOnOff.setText(getResources().getString(R.string.turn_on));
        }
        */

    }


    public void turnOnOff(View view){
        switch (btnTurnOnOff.getText().toString()){
            case "TURN ON":{
                if (!ba.isEnabled()){
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    Toast.makeText(getApplicationContext(), R.string.turned_on,
                            Toast.LENGTH_SHORT).show();
                    btnTurnOnOff.setText(getResources().getString(R.string.turn_off));
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.already_turned_on,
                            Toast.LENGTH_SHORT).show();
                    btnTurnOnOff.setText(getResources().getString(R.string.turn_off));
                }
            }
            case "TURN OFF":{
                ba.disable();
                Toast.makeText(getApplicationContext(), "Turned off",
                        Toast.LENGTH_SHORT).show();
                btnTurnOnOff.setText(getResources().getString(R.string.turn_on));
            }

        }




    }

    public void pairedDev(View view){
        try{
            pairedDevices = ba.getBondedDevices();
            ArrayList<String> list = new ArrayList<>();

            for (BluetoothDevice bd : pairedDevices)
                list.add(bd.getName());

            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            pairedDevListView.setAdapter(adapter);


        }catch (NullPointerException e){

        }

    }

    public void setDiscoverable(View view){
        Intent discoverable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(discoverable, 1);
        Toast.makeText(getApplicationContext(), "Now discoverable",
                Toast.LENGTH_SHORT).show();
    }

}
























