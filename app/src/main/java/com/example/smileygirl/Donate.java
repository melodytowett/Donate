package com.example.smileygirl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Donate extends AppCompatActivity {
    Button Chome,School,Smothers,NGO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate);
       Chome = (Button)findViewById(R.id.Chome);
       School= (Button)findViewById(R.id.School);
        Smothers = (Button)findViewById(R.id.Smothers);
        NGO = (Button)findViewById(R.id.NGO);
        Chome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Donate.this,Chome.class);
                startActivity(intent);
            }
        });
        School.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Donate.this,School.class);
                startActivity(intent);
                finish();
            }
        });
        Smothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Donate.this,Smom.class);
                startActivity(intent);
                finish();
            }
        });
        NGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Donate.this,Ngo.class);
                startActivity(intent);
                finish();
            }
        });

  }
}
