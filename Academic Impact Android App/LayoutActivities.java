package com.example.ccs322lacademicimpact;

public class LayoutActivities {

    private String layoutActID;
    private String layoutActClassID;
    private int layoutActImage;
    private String layoutActName;
    private int layoutActGrade;
    private String layoutActDesc;
    private String layoutActAnswer;

    public LayoutActivities(){super();}

    public LayoutActivities(String layoutActID, String layoutActClassID, int layoutActImage, String layoutActName, int layoutActGrade, String layoutActDesc, String layoutActAnswer) {
        this.layoutActID = layoutActID;
        this.layoutActClassID = layoutActClassID;
        this.layoutActImage = layoutActImage;
        this.layoutActName = layoutActName;
        this.layoutActGrade = layoutActGrade;
        this.layoutActDesc = layoutActDesc;
        this.layoutActAnswer = layoutActAnswer;
    }

    public int getLayoutActImage() {
        return layoutActImage;
    }

    public void setLayoutActImage(int layoutActImage) {
        this.layoutActImage = layoutActImage;
    }

    public String getLayoutActID() {
        return layoutActID;
    }

    public void setLayoutActID(String layoutActID) {
        this.layoutActID = layoutActID;
    }

    public String getLayoutActName() {
        return layoutActName;
    }

    public void setLayoutActName(String layoutActName) {
        this.layoutActName = layoutActName;
    }

    public String getLayoutActClassID() {
        return layoutActClassID;
    }

    public void setLayoutActClassID(String layoutActClassID) {
        this.layoutActClassID = layoutActClassID;
    }

    public int getLayoutActGrade() {
        return layoutActGrade;
    }

    public void setLayoutActGrade(int layoutActGrade) {
        this.layoutActGrade = layoutActGrade;
    }

    public String getLayoutActDesc() {
        return layoutActDesc;
    }

    public void setLayoutActDesc(String layoutActDesc) {
        this.layoutActDesc = layoutActDesc;
    }

    public String getLayoutActAnswer() {
        return layoutActAnswer;
    }

    public void setLayoutActAnswer(String layoutActAnswer) {
        this.layoutActAnswer = layoutActAnswer;
    }
}
