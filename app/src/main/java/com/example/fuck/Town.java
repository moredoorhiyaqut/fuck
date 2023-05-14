package com.example.fuck;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.w3c.dom.Text;

public class Town extends AppCompatActivity {
    Button Knife_Shop,Wrapon_Shop,Mission,Explore;
    Text UserValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        Knife_Shop = findViewById(R.id.Knife_Shop);
        Wrapon_Shop = findViewById(R.id.Weapon_Shop);
        Mission = findViewById(R.id.Mission);
        Explore = findViewById(R.id.Explore);

        //UserValue = findViewById(R.id.UserValue);

        Intent GetValue = getIntent();
        int[] Player = new int[8];
        String[] PlayerValue = {"HP", "STR", "DEF", "PHS", "SPD", "AGI", "ATC", "LUK"};

        for(int i = 0;i < PlayerValue.length ;i++){
            Player[i] = GetValue.getIntExtra(PlayerValue[i],0);
        }

        //DisplayValue();


    }

}