package com.example.ccs322lacademicimpact;

public class DBAccounts {

    public static final String FIELD_UID = "_id";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_LASTNAME = "lastname";
    public static final String FIELD_FIRSTNAME = "firstname";
    public static final String FIELD_YEARLEVEL = "yearLevel";
    public static final String FIELD_COURSE = "course";
    public static final String FIELD_CLASSESDB = "classesDB";
    public static final String FIELD_ACTIVITIESDB = "activitiesDB";
    public static final String FIELD_NOTESDB = "notesDB";

    public static final String TABLE_NAME = "tblRecords";
    public static final String create_query = "CREATE TABLE " + TABLE_NAME +
            " (" + FIELD_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " " + FIELD_IMAGE + " INTEGER," +
            " " + FIELD_LASTNAME + " TEXT NOT NULL," +
            " " + FIELD_FIRSTNAME + " TEXT NOT NULL," +
            " " + FIELD_YEARLEVEL + " TEXT NOT NULL," +
            " " + FIELD_COURSE + " TEXT NOT NULL," +
            " " + FIELD_CLASSESDB + " TEXT NOT NULL," +
            " " + FIELD_ACTIVITIESDB + " TEXT NOT NULL," +
            " " + FIELD_NOTESDB + " TEXT NOT NULL" + ");";

    private int uid, image;
    private String lastName, firstName, yearLevel, course, classesDB, activitiesDB, notesDB;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getClassesDB() {
        return classesDB;
    }

    public void setClassesDB(String classesDB) {
        this.classesDB = classesDB;
    }

    public String getActivitiesDB() {
        return activitiesDB;
    }

    public void setActivitiesDB(String activitiesDB) {
        this.activitiesDB = activitiesDB;
    }

    public String getNotesDB() {
        return notesDB;
    }

    public void setNotesDB(String notesDB) {
        this.notesDB = notesDB;
    }


}
