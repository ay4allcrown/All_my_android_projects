package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> words = new ArrayList<>(10);


        //WordAdapter<Word> itemsAdapter = new WordAdapterr<Word>();

        ListView listView = (ListView) findViewById(R.id.list);

        //listView.setAdapter(itemsAdapter);
    }
}
