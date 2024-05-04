package com.example.passx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ARS_3 extends AppCompatActivity {

    private EditText create_password;
    private EditText confirm_password;
    private String email, password, first_name, last_name;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ars3);
        auth = FirebaseAuth.getInstance();
        email = getIntent().getStringExtra("Email");
        password = getIntent().getStringExtra("Password");
        first_name = getIntent().getStringExtra("First_Name");
        last_name = getIntent().getStringExtra("Last_Name");
        confirm_password = findViewById(R.id.confirm_field);
        create_password = findViewById(R.id.create_field);
    }

    public void confirm_button(View view){
        String create = create_password.getText().toString().trim();
        String confirm = confirm_password.getText().toString().trim();
        if (create.equals(confirm)) {
            if (InternetCheck.isNetworkAvailable(getApplicationContext()) && create.length()>=8) {
                reg_function();
                HashMap<String,Object> data = new HashMap<String,Object>();
                data.put("email",email);
                data.put("password",password);
                data.put("master",create);
                data.put("first_name",first_name);
                data.put("last_name",last_name);
                FirebaseDatabase.getInstance().getReference().child("USERS").push().setValue(data);

                Intent intent = new Intent(ARS_3.this, Screen.class);
                intent.putExtra("Email",email);
                startActivity(intent);
            }
            else {
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

    private void reg_function()
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    // pass
                }
                else{
                    // pass
                }
            }
        });
    }
}