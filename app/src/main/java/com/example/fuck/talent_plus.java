package com.example.fuck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class talent_plus extends AppCompatActivity {

    SeekBar HPseekBar,STRseekBar,DEFseekBar,PHSseekBar,AGIseekBar,SPDseekBar,ATCseekBar,LUKseekBar; //宣告使用者可以使用的seekbar
    EditText HPNumber,STRNumber,DEFNumber,PHSNumber,AGINumber,SPDNumber,ATCNumber,LUKNumber;    //宣告使用者可以使用的text
    Button GameReadStart;   //宣告使用者可以宣告的按鈕
    int HP,STR,DEF,PHS,AGI,SPD,ATC,LUK,TotalValue = 80; //宣告使用者可以使用的數值



    private void setUpSeekBar(SeekBar seekBar, final TextView textView) {
        seekBar.setMax(20); //seekbar設定最高數值
        seekBar.setProgress(10);    //設定剛進入時的初始數值
        seekBar.setMin(0);  //設定最小數值
        textView.setText(String.valueOf(seekBar.getProgress()));
        //將seekBar的內容轉換成字串在Text上面顯示，讓使用者可以在進入頁面的時候看到初始設定的數值
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {    //拖動seekBar時
                textView.setText(String.valueOf(seekBar.getProgress()));    //讀取SeekBar上面的數值
                TotalValue = HPseekBar.getProgress() + STRseekBar.getProgress() +
                DEFseekBar.getProgress() + PHSseekBar.getProgress() + AGIseekBar.getProgress() +
                SPDseekBar.getProgress() + ATCseekBar.getProgress() + LUKseekBar.getProgress();
                //將所有數值加總在TotalValue，用來之後偵測是否超過數值
                if (TotalValue > 100) { //如果加總的數值超過100，將該移動的SeekBar的數鎖定在100內
                    seekBar.setProgress(seekBar.getProgress() - (TotalValue-100));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

            textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 0) {   //因為int的內容不可為空值，故設計成當使用者刪除完內容時將為0並跳出迴圈
                        textView.setText("0");
                        return;
                    }
                    int Player = Integer.parseInt(charSequence.toString());
                    if (Player > 20) {  //當使用者的數大於20時將數跳回20內並將總數確認是否在至100內
                        seekBar.setProgress(seekBar.getProgress() - (TotalValue - 100));
                        textView.setText("20");
                    } else {
                         seekBar.setProgress(Player);
                         TotalValue = TotalValue + (Player - Integer.parseInt(textView.getText().toString()));
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
        });
    }

    private void GameStart(){
        AlertDialog.Builder ConfirmToGO = new AlertDialog.Builder(talent_plus.this);
        ConfirmToGO.setTitle("確認要出發嗎？");
        ConfirmToGO.setMessage("經過加成後，您的數值為：" + "\n血量："+ (HP + (HPseekBar.getProgress() * 10)) +
        "\n力量：" + (STR + STRseekBar.getProgress()) + "\n防禦：" + (DEF + DEFseekBar.getProgress()) +
        "\n體力：" + (PHS + PHSseekBar.getProgress()) + "\n速度：" + (SPD + SPDseekBar.getProgress()) +
        "\n敏捷：" + (AGI + AGIseekBar.getProgress()) + "\n技巧：" + (ATC + ATCseekBar.getProgress()) +
        "\n運氣：" + (LUK + LUKseekBar.getProgress()) +
        (TotalValue < 100 ?"\n您的數值尚未使用完畢，您還有" + (100 - TotalValue) + "點數值，確認要出發嗎？":""));

        ConfirmToGO.setPositiveButton("確認", (dialogInterface, i) -> {
            HP  += ( HPseekBar.getProgress() * 10); //將數值更變為最終數值
            STR += STRseekBar.getProgress();
            DEF += DEFseekBar.getProgress();
            PHS += PHSseekBar.getProgress();
            SPD += SPDseekBar.getProgress();
            AGI += AGIseekBar.getProgress();
            ATC += ATCseekBar.getProgress();
            LUK += LUKseekBar.getProgress();

            SharedPreferences.Editor editor = getSharedPreferences("Combat", MODE_PRIVATE).edit();
            //創建一個SharedPreferences，來讓Town頁面來獲取玩家最終的數值
            int[]  PassValue = {HP, STR, DEF, PHS, SPD, AGI, ATC, LUK};
            String[] PlayerValueName = {"FinalHP", "FinalSTR", "FinalDEF", "FinalPHS", "FinalSPD", "FinalAGI", "FinalATC", "FinalLUK"};
            //設定陣列，使之後可以用for迴圈傳遞減少程式碼長度
            for (i = 0; i < PassValue.length; i++) {
                editor.putInt(PlayerValueName[i], PassValue[i]);
            }
            editor.apply();

            startActivity(new Intent(talent_plus.this, Story.class));   //轉換到下一頁
        });
        ConfirmToGO.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_plus);

        STRseekBar = findViewById(R.id.STRseekBar);
        HPseekBar = findViewById(R.id.HPseekBar);
        DEFseekBar = findViewById(R.id.DEFseekBar);
        PHSseekBar = findViewById(R.id.PHSseekBar);
        AGIseekBar = findViewById(R.id.AGIseekBar);
        SPDseekBar = findViewById(R.id.SPDseekBar);
        ATCseekBar = findViewById(R.id.ATCseekBar);
        LUKseekBar = findViewById(R.id.LUKseekBar);

        HPNumber = findViewById(R.id.HPNumber);
        STRNumber = findViewById(R.id.STRNumber);
        DEFNumber = findViewById(R.id.DEFNumber);
        PHSNumber = findViewById(R.id.PHSNumber);
        AGINumber = findViewById(R.id.AGINumber);
        SPDNumber = findViewById(R.id.SPDNumber);
        ATCNumber = findViewById(R.id.ATCNumber);
        LUKNumber = findViewById(R.id.LUKNumber);

        GameReadStart = findViewById(R.id.GameReadStart);

        setUpSeekBar(HPseekBar, HPNumber);
        setUpSeekBar(STRseekBar, STRNumber);
        setUpSeekBar(DEFseekBar, DEFNumber);
        setUpSeekBar(PHSseekBar, PHSNumber);
        setUpSeekBar(SPDseekBar, SPDNumber);
        setUpSeekBar(AGIseekBar, AGINumber);
        setUpSeekBar(ATCseekBar, ATCNumber);
        setUpSeekBar(LUKseekBar, LUKNumber);

        Intent ChangeToTalent = getIntent();
        HP = ChangeToTalent.getIntExtra("HP", 0);
        STR = ChangeToTalent.getIntExtra("STR", 0);
        DEF = ChangeToTalent.getIntExtra("DEF", 0);
        PHS = ChangeToTalent.getIntExtra("PHS", 0);
        SPD = ChangeToTalent.getIntExtra("SPD", 0);
        AGI = ChangeToTalent.getIntExtra("AGI", 0);
        ATC = ChangeToTalent.getIntExtra("ATC", 0);
        LUK = ChangeToTalent.getIntExtra("LUK", 0);

        GameReadStart.setOnClickListener(view -> GameStart());


    }
}