package com.example.passx;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button submit;
    private FirebaseAuth auth;
    private String g_email, g_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        auth = FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g_email = email.getText().toString().trim();
                g_password = password.getText().toString().trim();
                if(TextUtils.isEmpty(g_email)||TextUtils.isEmpty(g_password))
                {
                    Toast.makeText(Login.this,"Fill the required fields!",Toast.LENGTH_LONG);
                }
                else{
                    if (InternetCheck.isNetworkAvailable(getApplicationContext())) {
                        log_function();
                    } else {
                        Toast.makeText(Login.this, "Check your internet connection!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void log_function(){
        auth.signInWithEmailAndPassword(g_email,g_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(Login.this, Screen.class);
                    intent.putExtra("G_Email",g_email);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Incorrect Username or Password!", Toast.LENGTH_LONG);
                }
            }
        });
    }

}