package com.example.smileygirl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;

public class Sign extends AppCompatActivity {
    TextInputEditText fname, emaill, phoneno, passwordd, confirm_password;
    Button btbregist;
    TextView login;
    ProgressBar progressBar;
    String link_signup = "http://192.168.43.156/try/signup.php";
   // ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getSupportActionBar().hide();
        fname =  findViewById(R.id.fullnames);
        emaill =  findViewById(R.id.email);
        phoneno =  findViewById(R.id.phone_number);
        passwordd =  findViewById(R.id.password);
        confirm_password =  findViewById(R.id.confirmpassword);
        btbregist = findViewById(R.id.btnregister);
        login = findViewById(R.id.login);
        //progressBar = (ProgressBar) findViewById(R.id.progressbar);
       // progressBar.setVisibility(View.GONE);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });
        btbregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  password, name, email, cpassword,  phone;

                password = passwordd.getText().toString();
                name = fname.getText().toString();
                email = emaill.getText().toString();
                cpassword = confirm_password.getText().toString();

                phone = Objects.requireNonNull(phoneno.getText()).toString();

                if (!name.equals("")  && !email.equals("")  && !phone.equals("") && !password.equals("") && !cpassword.equals("")) {
                    if (password.equals(cpassword)) {
                       // progressBar.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] field = new String[4];
                                field[0] = "name";
                                field[1] = "email";
                                field[2] = "phone";
                                field[3] = "password";


                                String[] data = new String[4];
                                data[0] = name;
                                data[1] = email;
                                data[2] = phone;
                                data[3] = password;

                                PutData putData = new PutData(link_signup, "POST", field, data);

                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        progressBar.setVisibility(View.GONE);
                                        String result = putData.getResult();
                                        if (result.equals("Registration succeeded")) {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Login.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                }

                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "password does not match", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), " All Fields are required", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Discard details and go back?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}