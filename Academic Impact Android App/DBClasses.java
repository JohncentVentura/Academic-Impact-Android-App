package com.example.ccs322lacademicimpact;

public class DBClasses {

    public static final String FIELD_CID = "_id";
    public static final String FIELD_IMAGE = "classImage";
    public static final String FIELD_NAME = "className";
    public static final String FIELD_INSTRUCTOR = "classInstructor";
    public static final String FIELD_TIME = "classTime";
    public static final String FIELD_PRELIMGRADE = "classPrelimGrade";
    public static final String FIELD_MIDTERMGRADE = "classMidtermGrade";
    public static final String FIELD_SEMIFINALGRADE = "classSemiFinalGrade";
    public static final String FIELD_FINALGRADE = "classFinalGrade";

    public static final String TABLE_NAME = "tblRecords";
    public static final String create_query = "CREATE TABLE " + TABLE_NAME +
            " (" + FIELD_CID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " " + FIELD_IMAGE + " INTEGER," +
            " " + FIELD_NAME + " TEXT NOT NULL," +
            " " + FIELD_INSTRUCTOR + " TEXT NOT NULL," +
            " " + FIELD_TIME + " TEXT NOT NULL," +
            " " + FIELD_PRELIMGRADE + " INTEGER," +
            " " + FIELD_MIDTERMGRADE + " INTEGER," +
            " " + FIELD_SEMIFINALGRADE + " INTEGER," +
            " " + FIELD_FINALGRADE + " INTEGER" + ");";

    private int cid, image;
    private String name, instructor, time, prelimGrade, midtermGrade, semiFinalGrade, finalGrade;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrelimGrade() {
        return prelimGrade;
    }

    public void setPrelimGrade(String prelimGrade) {
        this.prelimGrade = prelimGrade;
    }

    public String getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(String midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public String getSemiFinalGrade() {
        return semiFinalGrade;
    }

    public void setSemiFinalGrade(String semiFinalGrade) {
        this.semiFinalGrade = semiFinalGrade;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }
}
