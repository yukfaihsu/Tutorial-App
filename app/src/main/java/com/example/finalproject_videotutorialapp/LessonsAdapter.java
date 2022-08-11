package com.example.finalproject_videotutorialapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.finalproject_videotutorialapp.databinding.CustomScreentwolistviewLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class LessonsAdapter extends ArrayAdapter<Lesson> {
    private static final String TAG = "LessonsAdapter";
    public LessonsAdapter(@NonNull Context context,  @NonNull ArrayList<Lesson> lessonsList) {
        super(context, 0, lessonsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CustomScreentwolistviewLayoutBinding binding;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_screentwolistview_layout, parent, false);
        }
        binding = CustomScreentwolistviewLayoutBinding.bind(convertView);

        Lesson currLesson = getItem(position);
        binding.tvS2Name.setText(currLesson.getName());
        binding.tvS2Length.setText("Length: " + currLesson.getLength());
        binding.tvS2Num.setText(String.valueOf(currLesson.getNumber()) + ".");
        if(currLesson.getIsComplete() == true){
            binding.ivComplete.setVisibility(View.VISIBLE);
            Log.d(TAG, "getView: Is complete: " + currLesson.getIsComplete());
        }
        else{
            binding.ivComplete.setVisibility(View.GONE);
            Log.d(TAG, "getView: Is complete: " + currLesson.getIsComplete());
        }

        return convertView;
    }

}
