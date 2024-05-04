package com.example.passx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ARS_2 extends AppCompatActivity {

    private EditText create_password;
    private EditText confirm_password;
    private String email, first_name, last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ars2);
        email = getIntent().getStringExtra("Email");
        first_name = getIntent().getStringExtra("First_Name");
        last_name = getIntent().getStringExtra("Last_Name");
        create_password = findViewById(R.id.create_field);
        confirm_password = findViewById(R.id.confirm_field);
    }

    public void confirm(View view){
        String create = create_password.getText().toString().trim();
        String confirm = confirm_password.getText().toString().trim();
        if (create.equals(confirm) && create.length()>=8) {
            if (InternetCheck.isNetworkAvailable(getApplicationContext())) {
                Intent intent = new Intent(this, ARS_3.class);
                intent.putExtra("Email",email);
                intent.putExtra("First_Name",first_name);
                intent.putExtra("Last_Name",last_name);
                intent.putExtra("Password",create);
                startActivity(intent);
            } else {
                if (!(InternetCheck.isNetworkAvailable(getApplicationContext()))) {
                    Toast.makeText(this, "Check your internet connection!", Toast.LENGTH_LONG).show();
                }
                else if(!(create.length()>=8)){
                    Toast.makeText(this, "Password must be greater than 8 digits!", Toast.LENGTH_LONG).show();
                }
            }
        }
        else{
            Toast.makeText(this, "Incorrect Passwords!", Toast.LENGTH_LONG).show();
        }
    }
}