package com.example.app.model;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app.MainActivity;

/**
 * Created by wpc on 5/23/14.
 */
public class MPRemindDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MPReminder.db";

    public static final String TEXT_TYPE = " TEXT ";
    public static final String INTEGER_TYPE = " INTEGER ";
    public static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_REMIND_ENTRIES =
            "CREATE TABLE " + RemindItem.RemindEntry.TABLE_NAME + " ( "+
                    RemindItem.RemindEntry._ID + INTEGER_TYPE+" PRIMARY KEY,"+
                    RemindItem.RemindEntry.COLUMN_NAME_TITLE+TEXT_TYPE+COMMA_SEP+
                    RemindItem.RemindEntry.COLUMN_NAME_NOTE+TEXT_TYPE+COMMA_SEP+
                    RemindItem.RemindEntry.COLUMN_NAME_CREATE_AT+INTEGER_TYPE+COMMA_SEP+
                    RemindItem.RemindEntry.COLUMN_NAME_UPDATE_AT+INTEGER_TYPE+COMMA_SEP+
                    RemindItem.RemindEntry.COLUMN_NAME_FINISH_AT+INTEGER_TYPE+")";

    public static final String SQL_DELETE_REMIND_ENTRIES = "DROP TABLE IF EXISTS " + RemindItem.RemindEntry.TABLE_NAME;

    public MPRemindDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_REMIND_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO
    }

    private static SQLiteOpenHelper helper;
    public static void init(Context context){
        helper = new MPRemindDbHelper(context);
    }

    public static SQLiteOpenHelper getInstance(){
        return helper;
    }
}
