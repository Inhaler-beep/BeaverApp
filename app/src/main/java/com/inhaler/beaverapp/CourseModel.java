package com.inhaler.beaverapp;

public class CourseModel {

    private String lessonnumber,lessontitle;

    public CourseModel(String lessonnumber, String lessontitle) {
        this.lessonnumber = lessonnumber;
        this.lessontitle = lessontitle;
    }

    public CourseModel() {
    }

    public String getLessonnumber() {
        return lessonnumber;
    }

    public void setLessonnumber(String lessonnumber) {
        this.lessonnumber = lessonnumber;
    }

    public String getLessontitle() {
        return lessontitle;
    }

    public void setLessontitle(String lessontitle) {
        this.lessontitle = lessontitle;
    }
}
