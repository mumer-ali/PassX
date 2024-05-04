package com.example.passx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view){
        if (InternetCheck.isNetworkAvailable(getApplicationContext())) {
            startActivity(new Intent(this, Login.class));
        } else {
            Toast.makeText(this, "Check your internet connection!", Toast.LENGTH_LONG).show();
        }
    }

    public void register (View view){
        if (InternetCheck.isNetworkAvailable(getApplicationContext())) {
            startActivity(new Intent(this, Register.class));
        } else {
            Toast.makeText(this, "Check your internet connection!", Toast.LENGTH_LONG).show();
        }
    }
}