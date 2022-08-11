package com.example.finalproject_videotutorialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Switch;

import com.example.finalproject_videotutorialapp.databinding.ScreenTwoBinding;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;

public class ScreenTwo extends AppCompatActivity {
    private ScreenTwoBinding binding;
    private ArrayList<Lesson> lessonsList;
    private static final String TAG = "ScreenTwo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ScreenTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=getIntent();
        Switch SequentialProgression = findViewById(R.id.swSequential);
        SequentialProgression.setChecked(intent.getBooleanExtra("forceSequentialProgressionState",false));



        lessonsList = LessonsDB.getInstance().getLessonsList();
        Log.d(TAG, lessonsList.toString());

        LessonsAdapter lesAdapter = new LessonsAdapter(this,lessonsList);
        this.binding.lvLessons.setAdapter(lesAdapter);


        this.binding.lvLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if (SequentialProgression.isChecked()==true) {

                if (i == 0) {
                    Intent intent = new Intent(getApplicationContext(), ScreenThree.class);
                    int lessonNum = i + 1;
                    intent.putExtra("LESSON_NUMBER", lessonNum);
                    intent.putExtra("forceSequentialProgression",true);
                    Log.d(TAG, "Lesson " + lessonNum + " is being clicked");
                    startActivity(intent);
                }
                else {
                    if (lessonsList.get(i-1).getIsComplete() == false) {


                    }
                     else if (lessonsList.get(i-1).getIsComplete() == true) {
                        Intent intent = new Intent(getApplicationContext(), ScreenThree.class);
                        int lessonNum = i + 1;
                        intent.putExtra("LESSON_NUMBER", lessonNum);
                        intent.putExtra("forceSequentialProgression",true);
                        Log.d(TAG, "Lesson " + lessonNum + " is being clicked");
                        startActivity(intent);
                    }
                }
            }
             else if(SequentialProgression.isChecked()==false) {

                    Intent intent = new Intent(getApplicationContext(), ScreenThree.class);
                    int lessonNum = i + 1;
                    intent.putExtra("LESSON_NUMBER", lessonNum);
                    Log.d(TAG, "Lesson " + lessonNum + " is being clicked");
                    startActivity(intent);
                }
            }

        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("logOut",true);
                startActivity(intent);

            }
        });
    }


}