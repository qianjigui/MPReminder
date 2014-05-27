package com.example.app.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wpc on 5/23/14.
 */
public class RemindItem {
    public static abstract class RemindEntry implements BaseColumns{
        public static final String TABLE_NAME ="reminder_entries";
        //_ID
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_NOTE = "note";
        public static final String COLUMN_NAME_CREATE_AT = "create_at";
        public static final String COLUMN_NAME_UPDATE_AT = "update_at";
        public static final String COLUMN_NAME_FINISH_AT = "finish_at";
    }

    private long _id;
    private String title;
    private String note;
    private long create_at,update_at,finish_at;

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public RemindItem(String title, String note){
        _id = -1;
        this.title = title;
        this.note = note;
        create_at = update_at = now();
        finish_at = Long.MAX_VALUE;
    }

    public void toFinish(){
        finish_at = now();
    }

    public boolean isFinish(){
        return finish_at<=now();
    }

    private static long now(){
        return System.currentTimeMillis();
    }

    public boolean save(){
       return insert(this);
    }

    public boolean update(){
        return update(this);
    }

    public long getId(){
        return this._id;
    }

    public static boolean insert(RemindItem item){
        SQLiteDatabase db = MPRemindDbHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RemindEntry.COLUMN_NAME_TITLE, item.title);
        values.put(RemindEntry.COLUMN_NAME_NOTE, item.note);
        values.put(RemindEntry.COLUMN_NAME_CREATE_AT, item.create_at);
        values.put(RemindEntry.COLUMN_NAME_UPDATE_AT, item.update_at);
        values.put(RemindEntry.COLUMN_NAME_FINISH_AT, item.finish_at);

        long newRowId;
        newRowId = db.insert(RemindEntry.TABLE_NAME, null, values);
        item._id = newRowId;
        return item._id!=-1;
    }

    public static List<RemindItem> all(){
        List<RemindItem> list = new LinkedList<RemindItem>();
        SQLiteDatabase db = MPRemindDbHelper.getInstance().getReadableDatabase();

        String[] projection = {
                RemindEntry._ID,
                RemindEntry.COLUMN_NAME_TITLE,
                RemindEntry.COLUMN_NAME_NOTE,
                RemindEntry.COLUMN_NAME_CREATE_AT,
                RemindEntry.COLUMN_NAME_UPDATE_AT,
                RemindEntry.COLUMN_NAME_FINISH_AT
        };

        Cursor c = db.query(RemindEntry.TABLE_NAME,projection,null,null,null,null,null);
        if(c.moveToFirst()){
            do{
                RemindItem item = new RemindItem(null,null);
                item._id = c.getLong(c.getColumnIndexOrThrow(RemindEntry._ID));
                item.title = c.getString(c.getColumnIndexOrThrow(RemindEntry.COLUMN_NAME_TITLE));
                item.note = c.getString(c.getColumnIndexOrThrow(RemindEntry.COLUMN_NAME_NOTE));
                item.create_at = c.getLong(c.getColumnIndexOrThrow(RemindEntry.COLUMN_NAME_CREATE_AT));
                item.update_at = c.getLong(c.getColumnIndexOrThrow(RemindEntry.COLUMN_NAME_UPDATE_AT));
                item.finish_at = c.getLong(c.getColumnIndexOrThrow(RemindEntry.COLUMN_NAME_FINISH_AT));
                list.add(item);
            }while(c.moveToNext());
        }
        c.close();

        return list;
    }

    public static boolean update(RemindItem item){
        item.update_at = now();
        SQLiteDatabase db = MPRemindDbHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(RemindEntry.COLUMN_NAME_TITLE, item.title);
        values.put(RemindEntry.COLUMN_NAME_NOTE, item.note);
        values.put(RemindEntry.COLUMN_NAME_CREATE_AT, item.create_at);
        values.put(RemindEntry.COLUMN_NAME_UPDATE_AT, item.update_at);
        values.put(RemindEntry.COLUMN_NAME_FINISH_AT, item.finish_at);

        String selection = RemindEntry._ID + " = ?";
        String[] selectArgs = {String.valueOf(item._id)};
        int count = db.update(RemindEntry.TABLE_NAME, values, selection, selectArgs);
        return count==1;
    }
}
