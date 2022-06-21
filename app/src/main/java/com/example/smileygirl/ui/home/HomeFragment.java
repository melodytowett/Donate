package com.example.smileygirl.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smileygirl.Blood;
import com.example.smileygirl.Donate;
import com.example.smileygirl.Food;
import com.example.smileygirl.Money;
import com.example.smileygirl.R;
import com.example.smileygirl.Sanitaries;

public class HomeFragment extends Fragment {
    ImageView food,money,sanitaries,blood;


    private HomeViewModel homeViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.home_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        food = (ImageView) root.findViewById(R.id.food);
        money = (ImageView) root.findViewById(R.id.money);
        sanitaries = (ImageView) root.findViewById(R.id.sanitaries);
        blood = (ImageView) root.findViewById(R.id.blood);
       food.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getContext(),Donate.class);
               startActivity(intent);
           }
       });
       money.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getContext(), Donate.class);
               startActivity(intent);
           }
       });
       sanitaries.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getContext(), Donate.class);
               startActivity(intent);
           }
       });
       blood.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getContext(), Blood.class);
               startActivity(intent);
           }
       });


        return root;


            }




}