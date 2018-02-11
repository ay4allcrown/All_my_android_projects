package com.gbogboade.twittersearchapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String SEARCHES = "searches";

    private EditText queryEditText;
    private EditText tagEditText;
    private FloatingActionButton saveFloatingActionButton;
    private SharedPreferences savedSearches;
    private List<String> tags;
    private SearchesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queryEditText = ((TextInputLayout)
                findViewById(R.id.queryTextInputLayout)).getEditText();
        queryEditText.addTextChangedListener(textwatcher);
        tagEditText = ((TextInputLayout)
                findViewById(R.id.tagTextInputLayout)).getEditText();
        tagEditText.addTextChangedListener(textwatcher);

        savedSearches = getSharedPreferences(SEARCHES, MODE_PRIVATE);

        tags = new ArrayList<>(savedSearches.getAll().keySet());
        Collections.sort(tags);

        RecyclerView recyclerView = (RecyclerView)
                findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchesAdapter(tags, itemClickListener, itemLongClickListener);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new ItemDivider(this));

        saveFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        saveFloatingActionButton.setOnClickListener(saveButtonListener);
        updateSaveFAB();
    }

    private final TextWatcher textwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            updateSaveFAB();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private void updateSaveFAB(){
     if (queryEditText.getText().toString().isEmpty() ||
         tagEditText.getText().toString().isEmpty()) {
         saveFloatingActionButton.hide();
     } else {
         saveFloatingActionButton.show();
     }
    }

    private final OnClickListener saveButtonListener = new
            OnClickListener() {
                @Override
                public void onClick(View v) {
                    String query = queryEditText.getText().toString();
                    String tag = tagEditText.getText().toString();

                    if (!query.isEmpty() && !tag.isEmpty()){
                        ((InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        v.getWindowToken(), 0);

                        addTaggedSearch(tag, query);
                        queryEditText.setText("");
                        tagEditText.setText("");
                        queryEditText.requestFocus();
                    }
            }
    };

    private final OnClickListener itemClickListener =
            new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tag = ((TextView) v).getText().toString();
                    String urlString = getString(R.string.search_URL) +
                            Uri.encode(savedSearches.getString(tag, ""), "UTF-8");
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(urlString));
                    startActivity(webIntent);
                }
    };

    private final OnLongClickListener itemLongClickListener =
            new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final String tag = ((TextView) v).getTag().toString();
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(
                            getString(R.string.share_edit_delete_title));
                    builder.setItems(R.array.dialog_items,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            shareSearch(tag);
                                            break;
                                        case 1:
                                            tagEditText.setText(tag);
                                            queryEditText.setText(
                                                    savedSearches.getString(tag, ""));
                                            break;
                                        case 2:
                                            deleteSearch(tag);
                                            break;
                                    }
                                }
                            });

                    builder.setNegativeButton(getString(R.string.cancel), null);
                    builder.create().show();
                    return true;
                }
            };

    private void addTaggedSearch(String tag, String query){
        SharedPreferences.Editor preferencesEditor = savedSearches.edit();
        preferencesEditor.putString(tag, query);
        preferencesEditor.apply();

        if (!tags.contains(tag)){
            tags.add(tag);
            Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);
            adapter.notifyDataSetChanged();
        }
    }

    private void shareSearch(String tag){
        String urlString = getString(R.string.search_URL) +
                Uri.encode(savedSearches.getString(tag, ""), "UTF-8");

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.share_subject));
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_message, urlString));
        shareIntent.setType("text/plain");

        startActivity(Intent.createChooser(shareIntent,
                getString(R.string.share_search)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


/**
 * page 313 fig 8.23
 */


















