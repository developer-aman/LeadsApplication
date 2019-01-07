package com.agtechnosolution.leadsapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by AnujPc on 07-01-2019.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "leadmanager";

        // table name
        public static final String TABLE_NAME = "leads";

        // Table Columns names
        public static final String KEY_ID = "id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String MOBILE = "mobile";
        public static final String COMMENT = "comment";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (id integer primary key, name text not null,email text not null,mobile text not null,comment text not null)";


        public DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS stock");
            //creating database
            onCreate(db);
        }

        public void addLead(Lead lead) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NAME, lead.getName());
            values.put(EMAIL, lead.getEmail());
            values.put(MOBILE, lead.getMobile());
            values.put(COMMENT, lead.getComment());
            // Inserting Row
            db.insert(TABLE_NAME, null, values);

        }



//    public ArrayList<String> getAllName()
//        {
//            ArrayList<String> array_list = new ArrayList<String>();
//            String selectQuery="select * from " + TABLE_NAME;
//            SQLiteDatabase db=this.getReadableDatabase();
//            Cursor cursor=db.rawQuery(selectQuery, null);
//            cursor.moveToFirst();
//            while(!cursor.isAfterLast()){
//                array_list.add(cursor.getString(cursor.getColumnIndex(NAME)));
//                cursor.moveToNext();
//            }
//            return array_list;
//        }

        public ArrayList<Lead> getAllLeads(){
            ArrayList<Lead> arrayList = new ArrayList<Lead>();
            String selectQuery="select * from " + TABLE_NAME;
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                String name=cursor.getString(cursor.getColumnIndex(NAME));
                String email=cursor.getString(cursor.getColumnIndex(EMAIL));
                String mobile=cursor.getString(cursor.getColumnIndex(MOBILE));
                String comment=cursor.getString(cursor.getColumnIndex(COMMENT));
                arrayList.add(new Lead(name,email,mobile,comment));
                cursor.moveToNext();
            }
            return arrayList;
        }
}
