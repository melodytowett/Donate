package com.example.smileygirl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Smom extends AppCompatActivity {
    TextView womenGroup;
    boolean[] selectGroup;
    ArrayList<Integer> groupList = new ArrayList<>();
    String[] groupArray = {"Winning mums","Strong mums","Fit women","Joyfull women","Kenya women"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smom);
        womenGroup=findViewById(R.id.womenGroup);
        selectGroup = new boolean[groupArray.length];
        womenGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Smom.this);
                builder.setTitle("select food");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(groupArray, selectGroup, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b) {
                            groupList.add(i);
                            Collections.sort(groupList);
                        } else {
                           groupList.remove(i);
                        }

                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < groupList.size(); j++) {
                            stringBuilder.append(groupArray[groupList.get(j)]);
                            if (j != groupList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        womenGroup.setText(stringBuilder.toString());

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
                        for (int j = 0; j < selectGroup.length; j++) {
                            selectGroup[j] = false;
                            groupList.clear();
                           womenGroup.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

    }
}
