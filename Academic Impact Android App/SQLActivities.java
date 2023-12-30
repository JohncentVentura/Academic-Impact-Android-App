package com.example.ccs322lacademicimpact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLActivities extends SQLiteOpenHelper {

    public static String DATABASE_NAME;
    public static final int DATABASE_VERSION = 1; //DATABASE_NAME is changing

    public SQLActivities(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBActivities.create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBActivities.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public static void setDatabaseName(String databaseName) {
        DATABASE_NAME = databaseName;
    }

    public long insertRecord(SQLActivities db, String...params){
        SQLiteDatabase myDB = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBActivities.FIELD_AID, params[0]);
        cv.put(DBActivities.FIELD_CID, params[1]);
        cv.put(DBActivities.FIELD_IMAGE, params[2]);
        cv.put(DBActivities.FIELD_NAME, params[3]);
        cv.put(DBActivities.FIELD_GRADE, params[4]);
        cv.put(DBActivities.FIELD_DESCRIPTION, params[5]);
        cv.put(DBActivities.FIELD_ANSWER, params[6]);

        Long insertRecord = myDB.insert(DBActivities.TABLE_NAME, null, cv);
        myDB.close();
        return insertRecord;
    }

    public ArrayList<DBActivities> findRecord(SQLActivities db, String RegID){
        SQLiteDatabase myDB = db.getWritableDatabase();
        Cursor cursor = myDB.query(DBActivities.TABLE_NAME, new String[] {DBActivities.FIELD_AID, DBActivities.FIELD_CID, DBActivities.FIELD_IMAGE, DBActivities.FIELD_NAME,
                        DBActivities.FIELD_GRADE, DBActivities.FIELD_DESCRIPTION, DBActivities.FIELD_ANSWER},
                DBActivities.FIELD_AID + "=?", new String[]{RegID}, null, null, null);
        DBActivities dbItems = new DBActivities();

        ArrayList<DBActivities>classesList = new ArrayList<DBActivities>();
        while(cursor.moveToNext()){
            dbItems = new DBActivities();
            dbItems.setAid(cursor.getInt(cursor.getColumnIndexOrThrow(DBActivities.FIELD_AID)));
            dbItems.setCid(cursor.getInt(cursor.getColumnIndexOrThrow(DBActivities.FIELD_CID)));
            dbItems.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(DBActivities.FIELD_IMAGE)));
            dbItems.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBActivities.FIELD_NAME)));
            dbItems.setGrade(cursor.getInt(cursor.getColumnIndexOrThrow(DBActivities.FIELD_GRADE)));
            dbItems.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBActivities.FIELD_DESCRIPTION)));
            dbItems.setAnswer(cursor.getString(cursor.getColumnIndexOrThrow(DBActivities.FIELD_ANSWER)));
            classesList.add(dbItems);
        }
        return classesList;
    }

    public ArrayList<DBActivities> getAllRecords(SQLActivities db){
        SQLiteDatabase myDB = db.getWritableDatabase();
        Cursor cursor = myDB.query(DBClasses.TABLE_NAME, new String[] {DBActivities.FIELD_AID, DBActivities.FIELD_CID, DBActivities.FIELD_IMAGE, DBActivities.FIELD_NAME,
                        DBActivities.FIELD_GRADE, DBActivities.FIELD_DESCRIPTION, DBActivities.FIELD_ANSWER}, null, null, null, null, null);
        DBActivities dbItems = new DBActivities();

        ArrayList<DBActivities>classesList = new ArrayList<DBActivities>();
        while (cursor.moveToNext()){
            dbItems = new DBActivities();
            dbItems.setAid(cursor.getInt(cursor.getColumnIndexOrThrow(DBActivities.FIELD_AID)));
            dbItems.setCid(cursor.getInt(cursor.getColumnIndexOrThrow(DBActivities.FIELD_CID)));
            dbItems.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(DBActivities.FIELD_IMAGE)));
            dbItems.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBActivities.FIELD_NAME)));
            dbItems.setGrade(cursor.getInt(cursor.getColumnIndexOrThrow(DBActivities.FIELD_GRADE)));
            dbItems.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBActivities.FIELD_DESCRIPTION)));
            dbItems.setAnswer(cursor.getString(cursor.getColumnIndexOrThrow(DBActivities.FIELD_ANSWER)));
            classesList.add(dbItems);
        }
        return classesList;
    }

    public int updateRecord(SQLActivities db, String...params){
        SQLiteDatabase myDB = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBActivities.FIELD_AID, params[0]);
        cv.put(DBActivities.FIELD_CID, params[1]);
        cv.put(DBActivities.FIELD_IMAGE, params[2]);
        cv.put(DBActivities.FIELD_NAME, params[3]);
        cv.put(DBActivities.FIELD_GRADE, params[4]);
        cv.put(DBActivities.FIELD_DESCRIPTION, params[5]);
        cv.put(DBActivities.FIELD_ANSWER, params[6]);

        int insertReturn = myDB.update(DBActivities.TABLE_NAME, cv, DBActivities.FIELD_AID + "=?", new String[]{params[0]});
        myDB.close();
        return insertReturn;
    }

    public int deleteRecord(SQLActivities db, String RegID){
        SQLiteDatabase myDb = db.getWritableDatabase();
        int insertReturn = myDb.delete(DBActivities.TABLE_NAME, DBActivities.FIELD_AID + "=?", new String[]{RegID});
        myDb.close();
        return insertReturn;
    }
}
