package com.example.ccs322lacademicimpact;

public class DBActivities {

    public static final String FIELD_AID = "_id";
    public static final String FIELD_CID = "activityClassID";
    public static final String FIELD_IMAGE = "activityImage";
    public static final String FIELD_NAME = "activityName";
    public static final String FIELD_GRADE = "activityGrade";
    public static final String FIELD_DESCRIPTION = "activityDescription";
    public static final String FIELD_ANSWER = "activityAnswer";

    public static final String TABLE_NAME = "tblRecords";
    public static final String create_query = "CREATE TABLE " + TABLE_NAME +
            " (" + FIELD_AID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " " + FIELD_CID + " INTEGER NOT NULL," +
            " " + FIELD_IMAGE + " INTEGER," +
            " " + FIELD_NAME + " TEXT NOT NULL," +
            " " + FIELD_GRADE + " INTEGER," +
            " " + FIELD_DESCRIPTION + " TEXT NOT NULL," +
            " " + FIELD_ANSWER + " TEXT" + ");";

    private int aid, cid, image, grade;
    private String name, description, answer;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
