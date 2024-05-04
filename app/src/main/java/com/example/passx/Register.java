package com.example.passx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private EditText first_name;
    private EditText last_name;
    private EditText email;
    String g_first_name,g_last_name,g_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        g_first_name = first_name.getText().toString();
        g_last_name = last_name.getText().toString();
        g_email = email.getText().toString();
    }

    public void submit(View view) {
        g_first_name = first_name.getText().toString().trim();
        g_last_name = last_name.getText().toString().trim();
        g_email = email.getText().toString().trim();
        if (g_first_name.isEmpty() || g_last_name.isEmpty() || g_email.isEmpty()) {
            Toast.makeText(this, "Fill the required fields!", Toast.LENGTH_LONG).show();
        } else {
            if (InternetCheck.isNetworkAvailable(getApplicationContext())) {
                Intent intent = new Intent(Register.this, ARS_1.class);
                intent.putExtra("Email", g_email);
                intent.putExtra("First_Name", g_first_name);
                intent.putExtra("Last_Name", g_last_name);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Check your internet connection!", Toast.LENGTH_LONG).show();
            }
        }
    }
}