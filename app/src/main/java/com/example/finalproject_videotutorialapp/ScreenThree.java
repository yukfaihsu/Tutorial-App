package com.example.finalproject_videotutorialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;


import com.example.finalproject_videotutorialapp.databinding.ThreeScreenBinding;

public class ScreenThree extends AppCompatActivity {
    private ThreeScreenBinding binding;
    private static final String TAG = "ScreenThree";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ThreeScreenBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        int lessonNumFromScreen2 = 0;

        Intent currIntent = this.getIntent();
        if(currIntent == null){
            Log.d(TAG, "onCreate: This intent is null");
        }
        else{
            lessonNumFromScreen2 = currIntent.getIntExtra("LESSON_NUMBER",-1);
            Log.d(TAG, "Lesson position: " + lessonNumFromScreen2);
        }

        Lesson currLesson = LessonsDB.getInstance().getLessonByLessonNum(lessonNumFromScreen2);
        Log.d(TAG, "onCreate: From DataBase: " + currLesson.getNumber());

        this.binding.tvS3NameAndNum.setText(String.valueOf(currLesson.getNumber()) + ". " + currLesson.getName());
        this.binding.tvS3Length.setText("Length: " + currLesson.getLength());
        this.binding.tvDescription.setText(currLesson.getDescription());

        SharedPreferences sharePref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();
        this.binding.etNotes.setText(sharePref.getString("notes" + currLesson.getNumber(),""));

        this.binding.btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(currLesson.getLessonLink()));
                startActivity(openBrowser);
            }
        });

        this.binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "btnSaveNotes: Notes of Lesson " + currLesson.getNumber() + ". " + currLesson.getName() + " is saved");
                editor.putString("notes" + currLesson.getNumber(),binding.etNotes.getText().toString());
                editor.apply();

            }
        });

        Intent intent =getIntent();
        boolean forceSequentialProgression = intent.getBooleanExtra("forceSequentialProgression",false);

        this.binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currLesson.setComplete(true);
                Log.d(TAG, "onClick: Is " + currLesson.getNumber() + ". " + currLesson.getName() + " completed: " + currLesson.getIsComplete());
                Intent intent = new Intent(getApplicationContext(), ScreenTwo.class);
                intent.putExtra("forceSequentialProgressionState",forceSequentialProgression);
                startActivity(intent);


            }
        });
    }
}