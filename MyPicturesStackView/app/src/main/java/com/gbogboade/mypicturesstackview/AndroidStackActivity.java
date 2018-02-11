package com.gbogboade.mypicturesstackview;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.StackView;

import java.util.ArrayList;

/**
 * Created by DELL on 12/15/2017.
 */
public class AndroidStackActivity extends Activity {
    private static StackView stackView;
    private static ArrayList list;

    private static final Integer[] icons = {R.drawable.image1,
    R.drawable.image2, R.drawable.image3, R.drawable.image4,
    R.drawable.image5};

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        stackView = (StackView) findViewById(R.id.stack_view);
        list = new ArrayList();

        for (int i = 0; i < icons.length; i++){
            list.add(new StackView("Item" + i, icons[i]));
        }

        StackAdapter adapter = new StackAdapter(AndroidStackActivity.this, list);
        stackView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
















