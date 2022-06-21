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

public class Ngo extends AppCompatActivity {
    Button donate,locate;
    TextView RNgo;
    boolean[] selectNGO;
    ArrayList<Integer> ngoList = new ArrayList<>();
    String[] ngoArray = {"Adventure in mission","Save the Child","red cross","Thelady's heart","Acres of mercy"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngo);
        donate=findViewById(R.id.donate);
        locate=findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Location.class);
                startActivity(intent);
                finish();
            }
        });
        RNgo=findViewById(R.id.RNgo);
        selectNGO = new boolean[ngoArray.length];
        RNgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ngo.this);
                builder.setTitle("select NGO");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(ngoArray, selectNGO, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b) {
                            ngoList.add(i);
                            Collections.sort(ngoList);
                        } else {
                            ngoList.remove(i);
                        }

                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < ngoList.size(); j++) {
                            stringBuilder.append(ngoArray[ngoList.get(j)]);
                            if (j != ngoList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        RNgo.setText(stringBuilder.toString());

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < selectNGO.length; j++) {
                            selectNGO[j] = false;
                            ngoList.clear();
                            RNgo.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

    }
}
