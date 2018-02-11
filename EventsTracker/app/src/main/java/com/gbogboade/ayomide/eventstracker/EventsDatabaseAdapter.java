package com.gbogboade.ayomide.eventstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class EventsDatabaseAdapter {
    EventsDBHelper eventsDBHelper;

    public EventsDatabaseAdapter(Context context) {
        eventsDBHelper = new EventsDBHelper(context);
    }

    public long insertEvent(String eventTitle, String eventDate) {
        SQLiteDatabase db = eventsDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventsDBHelper.EVENT_TITLE, eventTitle);
        contentValues.put(EventsDBHelper.EVENT_DATE, eventDate);
        long id = db.insert(EventsDBHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public int deleteEvent(String eventName) {
        SQLiteDatabase db = eventsDBHelper.getWritableDatabase();
        String[] event = {eventName};
        int count = db.delete(EventsDBHelper.TABLE_NAME,
                EventsDBHelper.EVENT_TITLE + " = ?", event);
        return count;
    }

    public List getEventsInDB() {
        SQLiteDatabase db = eventsDBHelper.getWritableDatabase();
        String[] column = {EventsDBHelper.EVENT_TITLE, EventsDBHelper.EVENT_DATE};
        Cursor cursor = db.query(EventsDBHelper.TABLE_NAME, column, null, null, null, null, null);
        List result = new ArrayList();
        while (cursor.moveToNext()) {
            String eventTitle = cursor.getString(cursor.getColumnIndex(EventsDBHelper.EVENT_TITLE));
            String eventDate = cursor.getString(cursor.getColumnIndex(EventsDBHelper.EVENT_DATE));
            result.add(eventTitle);
            result.add(eventDate);
        }
        return result;
    }


    static class EventsDBHelper extends SQLiteOpenHelper {

        private static final String EVENT_DATE = "Event-Date";
        private static final String EVENT_TITLE = "Event-Title";
        private static final int DATABASE_VERSION = 1;
        private static final String EID = "_id";
        private static final String DATABASE_NAME = "Events_Database";
        private static final String TABLE_NAME = "user events";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                EID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_TITLE +
                " VARCHAR(30) " + EVENT_DATE + " VARCHAR(30)";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public EventsDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}