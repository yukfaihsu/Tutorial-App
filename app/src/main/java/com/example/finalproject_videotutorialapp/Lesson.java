package com.example.finalproject_videotutorialapp;

public class Lesson {
    private String name;
    private int number;
    private String length;
    private boolean isComplete;
    private String description;
    private String lessonLink;

    public Lesson(String name, int number, String length, String description, String lessonLink) {
        this.name = name;
        this.number = number;
        this.length = length;
        this.description = description;
        this.lessonLink = lessonLink;
        this.isComplete = false;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getLength() {
        return length;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getDescription() {
        return description;
    }

    public String getLessonLink() {
        return lessonLink;
    }

    public String toString(){
        return this.number + ". " + this.name;
    }
}
