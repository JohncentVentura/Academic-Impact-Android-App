package com.example.ccs322lacademicimpact;

public class LayoutGrades {

    private int layoutGradesPrelim;
    private int layoutGradesMidterm;
    private int layoutGradesSemiFinal;
    private int layoutGradesFinal;
    private int layoutGradesAverage;

    public LayoutGrades(){
        super();
    }

    public LayoutGrades(int layoutGradesPrelim, int layoutGradesMidterm, int layoutGradesSemiFinal, int layoutGradesFinal, int layoutGradesAverage) {
        this.layoutGradesPrelim = layoutGradesPrelim;
        this.layoutGradesMidterm = layoutGradesMidterm;
        this.layoutGradesSemiFinal = layoutGradesSemiFinal;
        this.layoutGradesFinal = layoutGradesFinal;
        this.layoutGradesAverage = layoutGradesAverage;
    }

    public int getLayoutGradesPrelim() {
        return layoutGradesPrelim;
    }

    public void setLayoutGradesPrelim(int layoutGradesPrelim) {
        this.layoutGradesPrelim = layoutGradesPrelim;
    }

    public int getLayoutGradesMidterm() {
        return layoutGradesMidterm;
    }

    public void setLayoutGradesMidterm(int layoutGradesMidterm) {
        this.layoutGradesMidterm = layoutGradesMidterm;
    }

    public int getLayoutGradesSemiFinal() {
        return layoutGradesSemiFinal;
    }

    public void setLayoutGradesSemiFinal(int layoutGradesSemiFinal) {
        this.layoutGradesSemiFinal = layoutGradesSemiFinal;
    }

    public int getLayoutGradesFinal() {
        return layoutGradesFinal;
    }

    public void setLayoutGradesFinal(int layoutGradesFinal) {
        this.layoutGradesFinal = layoutGradesFinal;
    }

    public int getLayoutGradesAverage() {
        return layoutGradesAverage;
    }

    public void setLayoutGradesAverage(int layoutGradesAverage) {
        this.layoutGradesAverage = layoutGradesAverage;
    }
}
