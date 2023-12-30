package com.example.ccs322lacademicimpact;

public class LayoutClasses {

    private int layoutClassesImage;
    private String layoutClassesID;
    private String layoutClassesName;
    private String layoutClassesInstructor;
    private String layoutClassesTime;

    public LayoutClasses(){
        super();
    }

    public LayoutClasses(int layoutClassesImage, String layoutClassesID, String layoutClassesName, String layoutClassesInstructor, String layoutClassesTime) {
        this.layoutClassesImage = layoutClassesImage;
        this.layoutClassesID = layoutClassesID;
        this.layoutClassesName = layoutClassesName;
        this.layoutClassesInstructor = layoutClassesInstructor;
        this.layoutClassesTime = layoutClassesTime;
    }

    public int getLayoutClassesImage() {
        return layoutClassesImage;
    }

    public void setLayoutClassesImage(int layoutClassesImage) {
        this.layoutClassesImage = layoutClassesImage;
    }

    public String getLayoutClassesID() {
        return layoutClassesID;
    }

    public void setLayoutClassesID(String layoutClassesID) {
        this.layoutClassesID = layoutClassesID;
    }

    public String getLayoutClassesName() {
        return layoutClassesName;
    }

    public void setLayoutClassesName(String layoutClassesName) {
        this.layoutClassesName = layoutClassesName;
    }

    public String getLayoutClassesInstructor() {
        return layoutClassesInstructor;
    }

    public void setLayoutClassesInstructor(String layoutClassesInstructor) {
        this.layoutClassesInstructor = layoutClassesInstructor;
    }

    public String getLayoutClassesTime() {
        return layoutClassesTime;
    }

    public void setLayoutClassesTime(String layoutClassesTime) {
        this.layoutClassesTime = layoutClassesTime;
    }
}
