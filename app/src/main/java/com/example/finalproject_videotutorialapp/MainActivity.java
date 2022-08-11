package com.example.finalproject_videotutorialapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject_videotutorialapp.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;

    String userName;
    String password;
    ArrayList<Credentials> credentialsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        credentialsList.add(new Credentials("abcd","1234"));

        Switch rememberMe = findViewById(R.id.swRememberMe);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        rememberMe.setChecked(sharedPref.getBoolean("saveLogin",false));


        Intent intent = getIntent();
        boolean logOut =intent.getBooleanExtra("logOut",false);

        if(logOut==true){
            rememberMe.setChecked(false);
            editor.putBoolean("saveLogin",false);
            editor.apply();
        }



        if (rememberMe.isChecked()==true ){
            binding.etName.setText(sharedPref.getString("userInput",""));
            binding.etPassword.setText(sharedPref.getString("passwordInput",""));
            Intent intend = new Intent(MainActivity.this,ScreenTwo.class);
            startActivity(intend);
        }


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean rightCredentials = false;
                boolean incorrectPassword=false;
                boolean userDoesnotExist = false;

                userName = binding.etName.getText().toString();
                password=binding.etPassword.getText().toString();



                for(int i=0;i<credentialsList.size();i++){
                    if(credentialsList.get(i).getUserName().contentEquals(userName) && credentialsList.get(i).getPassword().contentEquals(password)){
                        rightCredentials=true;
                    }
                    else if(credentialsList.get(i).getUserName().contentEquals(userName) && !credentialsList.get(i).getPassword().contentEquals(password)){
                        incorrectPassword=true;
                    }
                    else  if(!credentialsList.get(i).getUserName().contentEquals(userName)){
                        userDoesnotExist=true;
                    }
                }

                if  (userName.isEmpty() || password.isEmpty()){

                    binding.tvErrorMsg.setText("Please Enter Username and Password");

                }
                else if (rightCredentials==true){

                    Intent intent = new Intent(MainActivity.this,ScreenTwo.class);
                    editor.putBoolean("saveLogin",rememberMe.isChecked());
                    editor.putString("userInput",userName);
                    editor.putString("passwordInput",password);
                    editor.apply();
                    startActivity(intent);
                    binding.tvErrorMsg.setText("");
                }

                else if(incorrectPassword==true){

                    binding.tvErrorMsg.setText("Password Is Incorrect");
                    binding.etPassword.setText("");

                }

                else if(userDoesnotExist==true ){

                    binding.tvErrorMsg.setText("Username Does Not Exist");
                    binding.etName.setText("");
                    binding.etPassword.setText("");
                }

            }
        });


    }


}