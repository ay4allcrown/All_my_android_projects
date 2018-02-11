package com.gbogboade.ayomide.eventstracker;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ListView eventsListView;
    String eventsTitle[] = {"Russia 2018", "Sunday Service"};
    String eventsDate[] = {"2018-01-22", "2018-02-15"};
    int eventsIcons[] = {R.drawable.image1, R.drawable.image2};

    EventsDatabaseAdapter database;

    File file;
    FileOutputStream fileOutputStream = null;
    FileInputStream fileInputStream = null;
    String EVENTS_FILE = "tracked_events.txt";

    AddEventDialog addEventDialog;
    EventsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventsListView = (ListView) findViewById(R.id.events_listView);
        adapter = new EventsListAdapter(getApplicationContext(), eventsTitle, eventsDate, eventsIcons);
        eventsListView.setAdapter(adapter);
        eventsListView.setOnItemClickListener(listItemClicked);
        database = new EventsDatabaseAdapter(this);
        for (int i = 0; i < eventsTitle.length; i++ ){
            database.insertEvent(eventsTitle[i], eventsDate[i]);
        }
    }

    private AdapterView.OnItemClickListener listItemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            EventTrackedDialogFragment dialog = new EventTrackedDialogFragment();
            dialog.setEventTitle(eventsTitle[(int) id]);
            dialog.setEventDate(eventsDate[((int) id)]);
            dialog.show(getFragmentManager(), "Event Count Down");
        }
    };

    public void addEventAction(View v){
        addEventDialog = new AddEventDialog();
        addEventDialog.show(getFragmentManager(), "Add event");
        saveEventsFile();
        //readEventFile();
    }

    public void saveEventsFile() {
        File file = null;
        int eventId = 1;
        String eventInfo = addEventDialog.getEventTitle() + "*" +
                addEventDialog.getDate();

        try {
            file = getFilesDir();
            fileOutputStream = openFileOutput(EVENTS_FILE, Context.MODE_PRIVATE);
            fileOutputStream.write(eventInfo.getBytes());
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readEventFile(){
        try {
//            fileInputStream = openFileInput(EVENTS_FILE);
//            int read = -1;
//            StringBuffer buffer = new StringBuffer();
//
//            while((read = fileInputStream.read() - 1) != -1){
//                buffer.append(((char) read));
//            }
//
//            Log.i("EVENT-BUFFER",buffer.toString());
//            String eventTitle = buffer.substring(0, buffer.indexOf("*"));
//            String eventDate = buffer.substring(buffer.indexOf("*"));
//            Log.d("EVENT-TITLE", eventTitle);
//            Log.d("EVENT-DATE", eventDate);
            String[] e1 = {"Age"};
            String[] e2 = {"2019-10-12"};
            adapter = new EventsListAdapter(getApplicationContext(), e1, e2, eventsIcons);
            eventsListView.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

















