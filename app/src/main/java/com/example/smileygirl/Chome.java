 package com.example.smileygirl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chome extends AppCompatActivity  {
    TextInputEditText Ccounty,hhome,llocation ;
    Button donate;
    String link_children = "http://192.168.43.156/try/children.php";


    @Override 
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chome);
        Ccounty =findViewById(R.id.Ccounty);
        hhome =findViewById(R.id.hhome);
        llocation =findViewById(R.id.llocation);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String County,home,location;
                County = Ccounty.getText().toString();
                home = hhome.getText().toString();
                location = llocation.getText().toString();
                if (!County.equals("") && !home.equals("") && !location.equals("")){
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] field = new String[3];
                                field[0] = "County";
                                field[1] = "home";
                                field[2] = "location";

                                String[] data = new String[3];
                                data[0] = County;
                                data[1] = home;
                                data[2] = location;
                                PutData putData = new PutData(link_children, "POST", field, data);

                                if (putData.startPut()){
                                    if (putData.onComplete()){
                                        String result = putData.getResult();
                                        if (result.equals("donation successful")){
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            Intent intent= new Intent(getApplicationContext(),Thankyou.class);
                                            startActivity(intent);
                                            finish();
                                        }else {
                                            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });
                }else {
                    Toast.makeText(getApplicationContext(),"fill in all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });

            }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Discard details and go back?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Donate.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}



