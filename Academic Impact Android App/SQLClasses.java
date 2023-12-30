package com.example.ccs322lacademicimpact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLClasses extends SQLiteOpenHelper {

    public static String DATABASE_NAME;
    public static final int DATABASE_VERSION = 1; //DATABASE_NAME is changing

    public SQLClasses(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBClasses.create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBClasses.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public static void setDatabaseName(String databaseName) {
        DATABASE_NAME = databaseName;
    }

    public long insertRecord(SQLClasses db, String...params){
        SQLiteDatabase myDB = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBClasses.FIELD_CID, params[0]);
        cv.put(DBClasses.FIELD_IMAGE, params[1]);
        cv.put(DBClasses.FIELD_NAME, params[2]);
        cv.put(DBClasses.FIELD_INSTRUCTOR, params[3]);
        cv.put(DBClasses.FIELD_TIME, params[4]);
        cv.put(DBClasses.FIELD_PRELIMGRADE, params[5]);
        cv.put(DBClasses.FIELD_MIDTERMGRADE, params[6]);
        cv.put(DBClasses.FIELD_SEMIFINALGRADE, params[7]);
        cv.put(DBClasses.FIELD_FINALGRADE, params[8]);

        Long insertRecord = myDB.insert(DBClasses.TABLE_NAME, null, cv);
        myDB.close();
        return insertRecord;
    }

    public ArrayList<DBClasses> findRecord(SQLClasses db, String RegID){
        SQLiteDatabase myDB = db.getWritableDatabase();
        Cursor cursor = myDB.query(DBClasses.TABLE_NAME, new String[] {DBClasses.FIELD_CID, DBClasses.FIELD_IMAGE, DBClasses.FIELD_NAME, DBClasses.FIELD_INSTRUCTOR,
                        DBClasses.FIELD_TIME, DBClasses.FIELD_PRELIMGRADE, DBClasses.FIELD_MIDTERMGRADE, DBClasses.FIELD_SEMIFINALGRADE, DBClasses.FIELD_FINALGRADE},
                DBClasses.FIELD_CID + "=?", new String[]{RegID}, null, null, null);
        DBClasses dbItems = new DBClasses();

        ArrayList<DBClasses>classesList = new ArrayList<DBClasses>();
        while(cursor.moveToNext()){
            dbItems = new DBClasses();
            dbItems.setCid(cursor.getInt(cursor.getColumnIndexOrThrow(DBClasses.FIELD_CID)));
            dbItems.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(DBClasses.FIELD_IMAGE)));
            dbItems.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_NAME)));
            dbItems.setInstructor(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_INSTRUCTOR)));
            dbItems.setTime(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_TIME)));
            dbItems.setPrelimGrade(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_PRELIMGRADE)));
            dbItems.setMidtermGrade(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_MIDTERMGRADE)));
            dbItems.setSemiFinalGrade(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_SEMIFINALGRADE)));
            dbItems.setFinalGrade(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_FINALGRADE)));
            classesList.add(dbItems);
        }
        return classesList;
    }

    public ArrayList<DBClasses> getAllRecords(SQLClasses db){
        SQLiteDatabase myDB = db.getWritableDatabase();
        Cursor cursor = myDB.query(DBClasses.TABLE_NAME, new String[] {DBClasses.FIELD_CID, DBClasses.FIELD_IMAGE, DBClasses.FIELD_NAME, DBClasses.FIELD_INSTRUCTOR,
                        DBClasses.FIELD_TIME, DBClasses.FIELD_PRELIMGRADE, DBClasses.FIELD_MIDTERMGRADE, DBClasses.FIELD_SEMIFINALGRADE, DBClasses.FIELD_FINALGRADE},
                        null, null, null, null, null);
        DBClasses dbItems = new DBClasses();

        ArrayList<DBClasses>classesList = new ArrayList<DBClasses>();
        while (cursor.moveToNext()){
            dbItems = new DBClasses();
            dbItems.setCid(cursor.getInt(cursor.getColumnIndexOrThrow(DBClasses.FIELD_CID)));
            dbItems.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(DBClasses.FIELD_IMAGE)));
            dbItems.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_NAME)));
            dbItems.setInstructor(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_INSTRUCTOR)));
            dbItems.setTime(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_TIME)));
            dbItems.setPrelimGrade(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_PRELIMGRADE)));
            dbItems.setMidtermGrade(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_MIDTERMGRADE)));
            dbItems.setSemiFinalGrade(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_SEMIFINALGRADE)));
            dbItems.setFinalGrade(cursor.getString(cursor.getColumnIndexOrThrow(DBClasses.FIELD_FINALGRADE)));
            classesList.add(dbItems);
        }
        return classesList;
    }

    public int updateRecord(SQLClasses db, String...params){
        SQLiteDatabase myDb = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBClasses.FIELD_CID, params[0]);
        cv.put(DBClasses.FIELD_IMAGE, params[1]);
        cv.put(DBClasses.FIELD_NAME, params[2]);
        cv.put(DBClasses.FIELD_INSTRUCTOR, params[3]);
        cv.put(DBClasses.FIELD_TIME, params[4]);
        cv.put(DBClasses.FIELD_PRELIMGRADE, params[5]);
        cv.put(DBClasses.FIELD_MIDTERMGRADE, params[6]);
        cv.put(DBClasses.FIELD_SEMIFINALGRADE, params[7]);
        cv.put(DBClasses.FIELD_FINALGRADE, params[8]);
        int insertReturn = myDb.update(DBClasses.TABLE_NAME, cv, DBClasses.FIELD_CID + "=?", new String[]{params[0]});
        myDb.close();
        return insertReturn;
    }

    public int deleteRecord(SQLClasses db, String RegID){
        SQLiteDatabase myDb = db.getWritableDatabase();
        int insertReturn = myDb.delete(DBClasses.TABLE_NAME, DBClasses.FIELD_CID + "=?", new String[]{RegID});
        myDb.close();
        return insertReturn;
    }

}
