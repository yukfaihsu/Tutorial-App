package com.example.finalproject_videotutorialapp;

import java.util.ArrayList;

public class LessonsDB {
    private ArrayList<Lesson> lessonsList;
    private static LessonsDB instance = null;

    public static LessonsDB getInstance(){
        if(instance == null){
            instance = new LessonsDB();
        }
        return instance;
    }

    private LessonsDB(){
        this.lessonsList = new ArrayList<Lesson>();

        lessonsList.add(new Lesson("Introduction to Android Studio", 1,"5 min", "This module gives you an introduction to Android Studio.", "https://www.youtube.com/watch?v=K2dodTXARqc"));
        lessonsList.add(new Lesson("Android User Interface Basics", 2,"24 min", "This module helps you understand the Android layout and how to work with them.", "https://www.youtube.com/watch?v=PJ3RdfJ4Np8"));
        lessonsList.add(new Lesson("Designing Multiscreen Apps", 3,"7 min", "This module teaches you how to create adaptive UI with fragment.", "https://www.youtube.com/watch?v=ZObgTM_xyIk"));
        lessonsList.add(new Lesson("Data Persistence", 4,"38 min", "This module provides you a tutorial of using SharedPreferences to persist data.", "https://www.youtube.com/watch?v=VJeZ40C6LYo"));
    }

    public ArrayList<Lesson> getLessonsList() {

        return this.lessonsList;
    }

    public Lesson getLessonByLessonNum(int lessonNum){
        for(Lesson l:lessonsList){
            if(lessonNum == l.getNumber()){
                return l;
            }
        }
        return null;
    }
}
