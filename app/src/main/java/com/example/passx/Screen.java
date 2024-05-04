package com.example.passx;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Screen extends AppCompatActivity {

    private String email, g_email;
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);


        email = getIntent().getStringExtra("Email");
        g_email = getIntent().getStringExtra("G_Email");
        bnv = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                {
                    if (item.getItemId() == R.id.home) {
                        HomeFragment home = new HomeFragment();
                        Bundle data = new Bundle();
                        data.putString("Email",email);
                        data.putString("G_Email",g_email);
                        home.setArguments(data);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
                        return true;
                    } else if (item.getItemId() == R.id.profile) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
                        return true;
                    } else if (item.getItemId() == R.id.settings) {
                        showDialog();
                        return true;
                    }
                }
                return false;
            }
        });

    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout security = dialog.findViewById(R.id.security);
        LinearLayout privacy = dialog.findViewById(R.id.privacy);
        LinearLayout log_out = dialog.findViewById(R.id.log_out);

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SecurityFragment()).commit();
                dialog.dismiss();
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new PrivacyFragment()).commit();
                dialog.dismiss();
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Screen.this,"Log Out is clicked",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}