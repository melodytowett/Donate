package com.example.smileygirl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class reset_password extends AppCompatActivity {
    TextInputEditText email;
    Button btn_reset_password,back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email= findViewById(R.id.email);
        btn_reset_password = findViewById(R.id.btnreset_password);
        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reset_email = email.getText().toString();
                if (!reset_email.equals("")){
                    Toast.makeText(getApplicationContext(),"Link has been sent to"+reset_email,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(reset_password.this,reset_password_success.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"email cannot be empty",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        super.onBackPressed();
    }
}