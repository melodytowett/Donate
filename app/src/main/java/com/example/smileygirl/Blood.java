package com.example.smileygirl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Blood extends AppCompatActivity {
    Button button4,button5;
   TextView bldtype,Hospt;
    boolean[] selectBloodType,selectHospital;
    ArrayList<Integer> hospiList = new ArrayList<>();
    ArrayList<Integer> bloodList = new ArrayList<>();
    String[] bloodArray = {"O+","A+","AB+","B+","O-","A-","AB-","B-"};
    String[] hosptArray = {"Sogoo","weCare","WeTreat","LetsServe","thishopst","narok","medihill","Kenyata"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood);
        button4=(Button)findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        bldtype = findViewById(R.id.bldtype);
        selectBloodType = new boolean[bloodArray.length];
        bldtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Blood.this);
                builder.setTitle("Blood type");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(bloodArray, selectBloodType, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b) {
                            bloodList.add(i);
                            Collections.sort(bloodList);
                           // bloodList.add(i);
                            //Collections.sort(bloodList);
                        } else {
                            bloodList.remove(i);
                        }

                    }
                });
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < bloodList.size(); j++) {
                            stringBuilder.append(bloodArray[bloodList.get(j)]);
                            if (j != bloodList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        bldtype.setText(stringBuilder.toString());

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("delete all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < selectBloodType.length; j++) {
                            selectBloodType[j] = false;
                            bloodList.clear();
                            bldtype.setText("");
                        }
                    }
                });
                builder.show();
            }
        });
        Hospt=findViewById(R.id.Hopst);
        selectHospital = new boolean[hosptArray.length];
        Hospt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Blood.this);
                builder.setTitle("Hospital type");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(hosptArray, selectHospital, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b) {
                            hospiList.add(i);
                            Collections.sort(hospiList);
                        } else {
                            hospiList.remove(i);
                        }

                    }
                });
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < hospiList.size(); j++) {
                            stringBuilder.append(hosptArray[hospiList.get(j)]);
                            if (j != hospiList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        Hospt.setText(stringBuilder.toString());

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("delete all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < selectHospital.length; j++) {
                            selectHospital[j] = false;
                            hospiList.clear();
                            Hospt.setText("");
                        }
                    }
                });
                builder.show();
            }

        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                finish();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Location.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
