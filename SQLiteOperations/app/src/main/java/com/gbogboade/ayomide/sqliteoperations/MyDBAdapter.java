package com.gbogboade.ayomide.sqliteoperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {
    MyDBHelper myDBHelper;

    public MyDBAdapter(Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public long insertData(String name, String password) {
        SQLiteDatabase dbb = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHelper.NAME, name);
        contentValues.put(MyDBHelper.PASSWORDS, password);
        long id = dbb.insert(MyDBHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] columns = {MyDBHelper.UID, MyDBHelper.NAME, MyDBHelper.PASSWORDS};
        Cursor cursor = db.query(MyDBHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(MyDBHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(MyDBHelper.NAME));
            String password = cursor.getString(cursor.getColumnIndex(MyDBHelper.PASSWORDS));
            buffer.append(cid + "  " + name + "  " + password + "\n");
        }
        return buffer.toString();
    }

    public int delete(String name) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] userId = {name};
        int count = db.delete(MyDBHelper.TABLE_NAME, MyDBHelper.NAME + " = ?", userId);
        return count;
    }

    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHelper.NAME, newName);
        String[] whereArgs = {oldName};
        int count = db.update(MyDBHelper.TABLE_NAME, contentValues,
                MyDBHelper.NAME + " = ?", whereArgs);
        return count;
    }

    static class MyDBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Users_Database.db";
        private static final String TABLE_NAME = "users";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORDS = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255)" +
                PASSWORDS + " VARCHAR(255))";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public MyDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }
}





























