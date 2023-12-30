package com.example.ccs322lacademicimpact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLAccounts extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DatabaseAccounts";
    public static final int DATABASE_VERSION = 5; //If DATABASE_VERSION is changed, change the value of database name in classesDBName, activitiesDBName, notesDBName in SignupActivity

    public SQLAccounts(Context context) { //super parameters was changed, view original SQLiteMethods original parameters
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBAccounts.create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBAccounts.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertRecord(SQLAccounts db, String...params){
        SQLiteDatabase myDB = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBAccounts.FIELD_UID, params[0]);
        cv.put(DBAccounts.FIELD_IMAGE, params[1]);
        cv.put(DBAccounts.FIELD_LASTNAME, params[2]);
        cv.put(DBAccounts.FIELD_FIRSTNAME, params[3]);
        cv.put(DBAccounts.FIELD_YEARLEVEL, params[4]);
        cv.put(DBAccounts.FIELD_COURSE, params[5]);
        cv.put(DBAccounts.FIELD_CLASSESDB, params[6]);
        cv.put(DBAccounts.FIELD_ACTIVITIESDB, params[7]);
        cv.put(DBAccounts.FIELD_NOTESDB, params[8]);

        Long insertRecord = myDB.insert(DBAccounts.TABLE_NAME, null, cv);
        myDB.close();
        return insertRecord;
    }

    public ArrayList<DBAccounts> findRecord(SQLAccounts db, String RegID){
        SQLiteDatabase myDB = db.getWritableDatabase();
        Cursor cursor = myDB.query(DBAccounts.TABLE_NAME, new String[] {DBAccounts.FIELD_UID, DBAccounts.FIELD_IMAGE, DBAccounts.FIELD_LASTNAME, DBAccounts.FIELD_FIRSTNAME,
                DBAccounts.FIELD_YEARLEVEL, DBAccounts.FIELD_COURSE, DBAccounts.FIELD_CLASSESDB, DBAccounts.FIELD_ACTIVITIESDB, DBAccounts.FIELD_NOTESDB},
                DBAccounts.FIELD_UID + "=?", new String[]{RegID}, null, null, null);
        DBAccounts dbItems = new DBAccounts();

        ArrayList<DBAccounts>accountsList = new ArrayList<DBAccounts>();
        while(cursor.moveToNext()){
            dbItems = new DBAccounts();
            dbItems.setUid(cursor.getInt(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_UID)));
            dbItems.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_IMAGE)));
            dbItems.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_LASTNAME)));
            dbItems.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_FIRSTNAME)));
            dbItems.setYearLevel(cursor.getString(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_YEARLEVEL)));
            dbItems.setCourse(cursor.getString(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_COURSE)));
            dbItems.setClassesDB(cursor.getString(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_CLASSESDB)));
            dbItems.setActivitiesDB(cursor.getString(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_ACTIVITIESDB)));
            dbItems.setNotesDB(cursor.getString(cursor.getColumnIndexOrThrow(DBAccounts.FIELD_NOTESDB)));
            accountsList.add(dbItems);
        }
        return accountsList;
    }

    public int updateRecord(SQLAccounts db, String...params){
        SQLiteDatabase myDb = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBAccounts.FIELD_UID, params[0]);
        cv.put(DBAccounts.FIELD_IMAGE, params[1]);
        cv.put(DBAccounts.FIELD_LASTNAME, params[2]);
        cv.put(DBAccounts.FIELD_FIRSTNAME, params[3]);
        cv.put(DBAccounts.FIELD_YEARLEVEL, params[4]);
        cv.put(DBAccounts.FIELD_COURSE, params[5]);
        cv.put(DBAccounts.FIELD_CLASSESDB, params[6]);
        cv.put(DBAccounts.FIELD_ACTIVITIESDB, params[7]);
        cv.put(DBAccounts.FIELD_NOTESDB, params[8]);
        int insertReturn = myDb.update(DBClasses.TABLE_NAME, cv, DBAccounts.FIELD_UID + "=?", new String[]{params[0]});
        myDb.close();
        return insertReturn;
    }
}
