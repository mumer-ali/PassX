package com.example.passx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ARS_1 extends AppCompatActivity {

    private TextView users_email;
    private TextView otp_field;
    private String otp;
    private int int_otp, rand;

    private String email, first_name, last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ars1);
        email = getIntent().getStringExtra("Email");
        first_name = getIntent().getStringExtra("First_Name");
        last_name = getIntent().getStringExtra("Last_Name");
        users_email = findViewById(R.id.users_email);
        users_email.setText(email);
        int min = 111111;
        int max = 999999;
        rand = (int)Math.floor(Math.random() * (max - min + 1) + min);
        send_email(email,rand);
        otp_field = findViewById(R.id.otp_field);
    }

    public void ok(View view){
        otp = otp_field.getText().toString().trim();
        int_otp = Integer.parseInt(otp);
        if (int_otp==rand){
            if (InternetCheck.isNetworkAvailable(getApplicationContext())) {
                Intent intent = new Intent(this, ARS_2.class);
                intent.putExtra("Email",email);
                intent.putExtra("First_Name",first_name);
                intent.putExtra("Last_Name",last_name);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Check your internet connection!", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Incorrect OTP!",Toast.LENGTH_LONG).show();
        }
    }

    private void send_email(String Receiver_Email, int rand_int)
    {
        try {
            String Sender_Email = "yourownpassx@gmail.com";
            String Sender_Password = "taxbhfzrpmdructz";

            String stringHost = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Sender_Email, Sender_Password);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Receiver_Email));
            mimeMessage.setSubject("Subject: OTP");
            mimeMessage.setText(rand_int + "");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            Toast.makeText(this, "OTP not sent!", Toast.LENGTH_LONG).show();
        } catch (MessagingException e) {
            Toast.makeText(this, "OTP not sent!", Toast.LENGTH_LONG).show();
        }
    }
}