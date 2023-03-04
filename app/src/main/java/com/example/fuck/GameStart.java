package com.example.fuck;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class GameStart extends AppCompatActivity {

    Button Ax,Rapier,Shield,Sword,Stick;    //設定按鈕變數
    TextView SwordText; //設定文字變數，因為只有直劍旁邊的文字會被更改故只設定文字

    String PassWord = ""; //將密碼清空，避免使用者進入時因為前面曾輸入過導致使用者無法讓密碼正確
    String [] PassWordNum = {"1","2","3","4","5"};//設定一個陣列，讓使用者按下按鈕密碼分別輸入密碼
    //正確密碼為114514
    int DualSwordToStart;

    int HP,ATK,DEF,PHS,SPD,AGI,ATC,LUK; //設定所有數值的簡稱

    void PlayerValue(int HP,int ATK,int DEF, int PHS, int SPD,int AGI,int ATC,int LUK){
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.PHS = PHS;
        this.SPD = SPD;
        this.AGI = AGI;
        this.ATC = ATC;
        this.LUK = LUK;

    }


    void PressSelect(String PressMessage){  //設定名為PressSlelect的函數，在後面撰寫程式碼會較為美觀
        AlertDialog.Builder Show = new AlertDialog.Builder(GameStart.this); //顯示小視窗在這個頁面(GameStart)
            Show.setTitle("確認要選擇" + PressMessage + "嗎？");   //顯示大標籤內容
            Show.setMessage( PressMessage + "的數值為：" +   //顯示小標籤內容
            "\n血量："+ HP + "\n攻擊：" + ATK + "\n防禦：" + DEF + "\n體力：" + PHS + "\n速度：" + SPD +
            "\n敏捷：" + AGI +"\n技巧：" + ATC + "\n運氣：" + LUK + "\n\n按下旁邊畫面即可跳出"
            ).setPositiveButton("確認選擇", (dialogInterface, Confirm) -> {//做切換頁面以及傳送數值
                Intent ChangeToTalent = new Intent(GameStart.this,talent_plus.class);
                ChangeToTalent.putExtra("HP", HP);
                ChangeToTalent.putExtra("ATK", ATK);
                ChangeToTalent.putExtra("DEF", DEF);
                ChangeToTalent.putExtra("PHS", PHS);
                ChangeToTalent.putExtra("SPD", SPD);
                ChangeToTalent.putExtra("AGI", AGI);
                ChangeToTalent.putExtra("ATC", ATC);
                ChangeToTalent.putExtra("LUK", LUK);

                startActivity(ChangeToTalent);

            });
            Show.create().show();   //顯示小視窗
    }

     void InPassWord(){ //設定輸入特殊密碼的函數，讓以後添加新內容更方便
        if(PassWord.equals("114514")){  //當密碼為114514的時候
            AlertDialog.Builder DualSword = new AlertDialog.Builder(GameStart.this);
            //顯示一個名為DualSword的小視窗在這個頁面開啟

            DualSword.setTitle("獲得技能:雙刀流");
            DualSword.setMessage("你的數值發生變化！" + "\n血量："+ HP + "\n攻擊：" + ATK +
            "\n防禦：" + DEF + "\n體力：" + PHS + "\n速度：" + SPD + "\n敏捷：" + AGI +
            "\n技巧：" + ATC + "\n運氣：" + LUK + "\n\n按下旁邊空格即可跳出"
            );
            //設定顯示內容

            Sword.setText("選雙刀");   //將按鈕內的文字成選雙刀
            Sword.setBackgroundColor(Color.parseColor("#80FFFF"));  //將按鈕的顏色改為指定的藍色
            Sword.setTextColor(Color.parseColor("#000000"));    //將按鈕內的文字改為黑色
            SwordText.setText("你在無意之中發現了自己能使用雙刀流" + //更改
            "\n為了隱藏這個破格技能的解鎖方法" +
            "\n而將自己一個技能換成切換雙刀的技能"
            );

            DualSword.setPositiveButton("確認", (dialogInterface, i) -> {
                Intent ChangeToTalent = new Intent(GameStart.this,talent_plus.class);
                startActivity(ChangeToTalent);
            });
            DualSword.show();
        }
    }

    void WatTen(){
        if (DualSwordToStart == 0) WatTen();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        Ax = findViewById(R.id.Ax);
        Rapier = findViewById(R.id.Rapier);
        Shield = findViewById(R.id.Shield);
        Sword = findViewById(R.id.Sword);
        Stick = findViewById(R.id.Stick);

        SwordText = findViewById(R.id.SwordText);



        Ax.setOnClickListener(view -> {
            PassWord += PassWordNum[0];
            PlayerValue(30,35,20,25,10,10,15,10);
            InPassWord();
            PressSelect("斧頭");

        });
        Rapier.setOnClickListener(view -> {
            PassWord += PassWordNum[1];
            PlayerValue(20,15,10,25,35,30,25,10);

            InPassWord();
            PressSelect("刺劍");

        });
        Shield.setOnClickListener(view -> {
            PassWord += PassWordNum[2];
            PlayerValue(30,10,40,40,5,5,10,10);

            InPassWord();
            PressSelect("大盾");

        });
            Sword.setOnClickListener(view -> {

                PassWord += PassWordNum[3];

                if (PassWord.equals("114514")) {
                    PlayerValue(30,35,20,30,40,35,25,15);

                    InPassWord();
                }
                else {
                    if ("選雙刀".equals(Sword.getText().toString())) {
                        PlayerValue(30,35,20,30,40,35,25,15);

                        InPassWord();
                        PressSelect("雙刀");
                    } else {
                        PlayerValue(25,25,20,20,20,20,15,10);

                        PressSelect("直劍");
                    }
                }

        });
            Stick.setOnClickListener(view -> {
                PassWord += PassWordNum[4];
                PlayerValue(25,30,20,30,10,10,20,10);


                InPassWord();
                PressSelect("棍");

        });

    }
}