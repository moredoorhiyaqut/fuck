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

    private String PassWord = ""; //將密碼清空，避免使用者進入時因為前面曾輸入過導致使用者無法讓密碼正確
    private final String[] PassWordNum = {"1", "2", "3", "4", "5"}; //設定一個陣列，讓使用者按下按鈕密碼分別輸入密碼
    private final String TruePassWord = "114514"; //正確密碼為114514
    int HP,STR,DEF,PHS,SPD,AGI,ATC,LUK; //設定所有數值的簡稱

    private void PressSelect(String PressMessage){  //設定名為PressSlelect的函數，在後面撰寫程式碼會較為美觀
        AlertDialog.Builder Show = new AlertDialog.Builder(GameStart.this); //顯示小視窗在這個頁面(GameStart)
            Show.setTitle("確認要選擇" + PressMessage + "嗎？");   //顯示大標籤內容
            Show.setMessage( PressMessage + "的數值為：" +   //顯示小標籤內容
            "\n血量："+ HP + "\n攻擊：" + STR + "\n防禦：" + DEF + "\n體力：" + PHS + "\n速度：" + SPD +
            "\n敏捷：" + AGI +"\n技巧：" + ATC + "\n運氣：" + LUK + "\n\n按下旁邊畫面即可跳出"
            ).setPositiveButton("確認選擇", (dialogInterface, Confirm) -> {//做切換頁面以及傳送數值
                Intent ChangeToTalent = new Intent(GameStart.this,talent_plus.class);

                String[] PutString = {"HP", "STR", "DEF", "PHS", "SPD", "AGI", "ATC", "LUK"};
                int[] PutNum = {HP, STR, DEF, PHS, SPD, AGI, ATC, LUK};

                for (int i = 0;i < PutString.length; i++) {
                    ChangeToTalent.putExtra(PutString[i], PutNum[i]);
                }

                startActivity(ChangeToTalent);

            });
            Show.create().show();   //顯示小視窗
    }

    private void InPassWord(){ //設定輸入特殊密碼的函數，讓以後添加新內容更方便
        AlertDialog.Builder DualSword = new AlertDialog.Builder(GameStart.this);
        //顯示一個名為DualSword的小視窗在這個頁面開啟
        DualSword.setTitle("獲得技能:雙刀流");
        DualSword.setMessage("你的數值發生變化！" + "\n血量："+ HP + "\n攻擊：" + STR +
        "\n防禦：" + DEF + "\n體力：" + PHS + "\n速度：" + SPD + "\n敏捷：" + AGI +
        "\n技巧：" + ATC + "\n運氣：" + LUK + "\n\n按下旁邊空格即可跳出"
        );
        //設定顯示內容

        Sword.setText("選雙刀");   //將按鈕內的文字成選雙刀
        Sword.setBackgroundColor(Color.parseColor("#80FFFF"));  //將按鈕的顏色改為指定的藍色
        Sword.setTextColor(Color.parseColor("#80FFFF"));    //將按鈕內的文字改為黑色
        SwordText.setText("你在無意之中發現了自己能使用雙刀流" + //更改
                          "\n為了探索這個破格技能的解鎖方法" +
                          "\n而將自己這個破格能力隱藏不人知道");

        DualSword.setPositiveButton("確認", (dialogInterface, i) -> {
            Intent ChangeToTalent = new Intent(GameStart.this,talent_plus.class);
            startActivity(ChangeToTalent);
        });

        DualSword.show();
    }

    private void PlayerValue(int HP,int STR,int DEF, int PHS, int SPD,int AGI,int ATC,int LUK, int Key){
        //設定使用者按下按鈕後會觸發的方法，設定玩家數值
        this.HP  = HP;
        this.STR = STR;
        this.DEF = DEF;
        this.PHS = PHS;
        this.SPD = SPD;
        this.AGI = AGI;
        this.ATC = ATC;
        this.LUK = LUK;

        if (TruePassWord.equals(PassWord)){ //如果玩家已經輸入完成密碼，當使用者觸發方法時就只會設定數值
        } else if (TruePassWord.startsWith(PassWord)) {
            //當使用者沒有打完成密碼，就會判斷玩家所按的密碼是否有照順序輸入，沒有則清空
            PassWord += PassWordNum[Key];
            if (PassWord.equals(TruePassWord)){
                //當使用者觸發方法時，將數值更改為選雙刀的值，並呼叫出解鎖雙刀的小視窗
                PlayerValue(300,45,20,35,45,40,35,15,5);
                InPassWord();
            }
        } else {    //當使用者輸入的數值沒有依照順序，則將輸入的密碼清空
                PassWord = "";
        }

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
            PlayerValue(300,35,20,25,10,10,15,10,0);
            PressSelect("斧頭");
    
        });

        Rapier.setOnClickListener(view -> {
            PlayerValue(200,15,10,25,35,30,25,10,1);
            PressSelect("刺劍");

        });

        Shield.setOnClickListener(view -> {
            PlayerValue(300,10,40,40,5,5,10,10,2);
            PressSelect("大盾");
        });

        Sword.setOnClickListener(view -> {
            if ("選直劍".equals(Sword.getText().toString())) {
                //當使用者按下按鈕內容為選直劍時，輸入密碼，如果沒有輸入成功密碼則不會觸發隱藏技能
                PlayerValue(250,25,20,20,20,20,15,10,3);
                if (PassWord.equals(TruePassWord)){ //當使用者輸入完成密碼後將數值改為雙刀的數值
                    PlayerValue(300,45,20,35,45,40,35,15,5);
                }
                PressSelect("直劍");
            } else {
                //當輸入其他的範圍時判定為雙刀
                PlayerValue(300,45,20,35,45,40,35,15,5);
                PressSelect("雙刀");
            }

        });

        Stick.setOnClickListener(view -> {
                PlayerValue(250,30,20,30,10,10,20,10,4);
                PressSelect("棍");

        });

    }

}