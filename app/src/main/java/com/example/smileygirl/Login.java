package com.example.smileygirl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;

public class Login extends AppCompatActivity {
    TextInputEditText emaill, passwordd;
    Button btnlogin;
    TextView forgot_password, register;
    //ProgressBar progressBar;
     SharedPreferences sp;


    String Url_login = "http://192.168.43.156/try/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        emaill = (TextInputEditText) findViewById(R.id.emaill);
        passwordd = (TextInputEditText) findViewById(R.id.passwordd);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        forgot_password = (TextView) findViewById(R.id.reset_password);
        register = (TextView) findViewById(R.id.register);
       // progressBar = (ProgressBar) findViewById(R.id.progressbar);
      //  progressBar.setVisibility(View.GONE);


        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (sp.getBoolean("logged", true)) {
            goToHome();
        }


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), reset_password.class);
                startActivity(intent);
                finish();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sign.class);
                startActivity(intent);
                finish();

            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected()) {
                   // if (internetIsConnected()) {

                        String email, password;

                        email = Objects.requireNonNull(emaill.getText()).toString();
                        password = Objects.requireNonNull(passwordd.getText()).toString();

                        if (!email.equals("") && !password.equals("")) {
                            if (email.equals("admin") && password.equals("admin")) {
                                Toast.makeText(getApplicationContext(), "logged in as admin", Toast.LENGTH_SHORT).show();
                                goToHome();
                                sp.edit().putString("userkey", email).apply();
                                sp.edit().putBoolean("logged", true).apply();


                            } else {

                               // progressBar.setVisibility(View.VISIBLE);
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        String[] field = new String[2];
                                        field[0] = "email";
                                        field[1] = "password";

                                        String[] data = new String[2];
                                        data[0] = email;
                                        data[1] = password;
                                        PutData putData = new PutData(Url_login, "POST", field, data);

                                        if (putData.startPut()) {
                                            if (putData.onComplete()) {
                                               // progressBar.setVisibility(View.GONE);
                                                String result = putData.getResult();
                                                if (result.equals("Login Success")) {

                                                    sp.edit().putBoolean("logged", true).apply();
                                                    sp.edit().putInt("userkey", 0).clear();
                                                    sp.edit().putString("user_key", email).apply();
                                                    goToHome();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }

                                    }

                                    private void goToHome() {
                                        Intent intent = new Intent(getApplicationContext(),Home.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }

                        } else {
                            Toast.makeText(getApplicationContext(), " All Fields are required", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast toast = Toast.makeText(Login.this, " Connected to Wi-Fi , No Internet", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                //} else {
                  //  Toast toast = Toast.makeText(Login.this, " Please Connect to a Network or turn on mobile data ", Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.CENTER, 0, 0);
                    //toast.show();
                //}

            }

            private void goToHome() {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                finish();
            }
        });
        // }
    }

    private void goToHome() {
    }

    private Boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) Login.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                android.os.Process.killProcess(Process.myPid());
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