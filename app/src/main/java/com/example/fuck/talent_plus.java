package com.example.fuck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class talent_plus extends AppCompatActivity {

    SeekBar HPseekBar,ATKseekBar,DEFseekBar,PHSseekBar,AGIseekBar,SPDseekBar,ATCseekBar,LUKseekBar;
    EditText HPNumber,ATKNumber,DEFNumber,PHSNumber,AGINumber,SPDNumber,ATCNumber,LUKNumber;
    Button GameReadStart;
    int HP,ATK,DEF,PHS,AGI,SPD,ATC,LUK;
    int TotalValue = 80;



    void setUpSeekBar(SeekBar seekBar, final TextView textView) {
        seekBar.setMax(20);
        seekBar.setProgress(10);
        seekBar.setMin(0);
        textView.setText(String.valueOf(seekBar.getProgress()));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(seekBar.getProgress()));
                TotalValue = HPseekBar.getProgress() + ATKseekBar.getProgress() +
                DEFseekBar.getProgress() + PHSseekBar.getProgress() + AGIseekBar.getProgress() +
                SPDseekBar.getProgress() + ATCseekBar.getProgress() + LUKseekBar.getProgress();

                if (TotalValue > 100) {
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
                if (charSequence.length() == 0) {   //因為int的內容不可為空值，故設計成當使用者刪除完內容時將為0
                    textView.setText("0");
                    return;
                }
                int Player = Integer.parseInt(charSequence.toString());
                if (Player > 20) {
                    textView.setText("20");
                    seekBar.setProgress(seekBar.getProgress() - (TotalValue - 100));

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

    void GameGo(){

        AlertDialog.Builder ConfirmToGO = new AlertDialog.Builder(talent_plus.this);
        ConfirmToGO.setTitle("確認要出發嗎？");
        ConfirmToGO.setMessage("經過加成後，您的數值為：" + "\n血量："+ (HP + HPseekBar.getProgress()) +
        "\n攻擊：" + (ATK + ATKseekBar.getProgress()) + "\n防禦：" + (DEF + DEFseekBar.getProgress()) +
        "\n體力：" + (PHS + PHSseekBar.getProgress()) + "\n速度：" + (SPD + SPDseekBar.getProgress()) +
        "\n敏捷：" + (AGI + AGIseekBar.getProgress()) + "\n技巧：" + (ATC + ATCseekBar.getProgress()) +
        "\n運氣：" + (LUK + LUKseekBar.getProgress()) +
        (TotalValue < 100 ?"\n您的數值尚未使用完畢，您還有" + (100 - TotalValue) + "點數值，確認要出發嗎？":""));

        ConfirmToGO.setPositiveButton("確認", (dialogInterface, i) -> {

            int MaxHP = HP + HPseekBar.getProgress();
            int MaxATK = ATK + ATKseekBar.getProgress();
            int MaxDEF = DEF + DEFseekBar.getProgress();
            int MaxPHS = PHS + PHSseekBar.getProgress();
            int MaxSPD = SPD + SPDseekBar.getProgress();
            int MaxAGI = AGI + AGIseekBar.getProgress();
            int MaxATC = ATC + ATCseekBar.getProgress();
            int MaxLUK = LUK + LUKseekBar.getProgress();

            Intent GoToStory = new Intent(talent_plus.this, Story.class);
            GoToStory.putExtra("InitialHP",MaxHP);
            GoToStory.putExtra("InitialATK",MaxATK);
            GoToStory.putExtra("InitialDEF",MaxDEF);
            GoToStory.putExtra("InitialPHS",MaxPHS);
            GoToStory.putExtra("InitialSPD",MaxSPD);
            GoToStory.putExtra("InitialAGI",MaxAGI);
            GoToStory.putExtra("InitialATC",MaxATC);
            GoToStory.putExtra("InitialLUK",MaxLUK);

            startActivity(GoToStory);
        });
        ConfirmToGO.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_plus);

        ATKseekBar = findViewById(R.id.ATKseekBar);
        HPseekBar = findViewById(R.id.HPseekBar);
        DEFseekBar = findViewById(R.id.DEFseekBar);
        PHSseekBar = findViewById(R.id.PHSseekBar);
        AGIseekBar = findViewById(R.id.AGIseekBar);
        SPDseekBar = findViewById(R.id.SPDseekBar);
        ATCseekBar = findViewById(R.id.ATCseekBar);
        LUKseekBar = findViewById(R.id.LUKseekBar);

        HPNumber = findViewById(R.id.HPNumber);
        ATKNumber = findViewById(R.id.ATKNumber);
        DEFNumber = findViewById(R.id.DEFNumber);
        PHSNumber = findViewById(R.id.PHSNumber);
        AGINumber = findViewById(R.id.AGINumber);
        SPDNumber = findViewById(R.id.SPDNumber);
        ATCNumber = findViewById(R.id.ATCNumber);
        LUKNumber = findViewById(R.id.LUKNumber);

        GameReadStart = findViewById(R.id.GameReadStart);

        setUpSeekBar(HPseekBar, HPNumber);
        setUpSeekBar(ATKseekBar, ATKNumber);
        setUpSeekBar(DEFseekBar, DEFNumber);
        setUpSeekBar(PHSseekBar, PHSNumber);
        setUpSeekBar(SPDseekBar, SPDNumber);
        setUpSeekBar(AGIseekBar, AGINumber);
        setUpSeekBar(ATCseekBar, ATCNumber);
        setUpSeekBar(LUKseekBar, LUKNumber);

        Intent ChangeToTalent = getIntent();
        HP = ChangeToTalent.getIntExtra("HP", 0);
        ATK = ChangeToTalent.getIntExtra("ATK", 0);
        DEF = ChangeToTalent.getIntExtra("DEF", 0);
        PHS = ChangeToTalent.getIntExtra("PHS", 0);
        SPD = ChangeToTalent.getIntExtra("SPD", 0);
        AGI = ChangeToTalent.getIntExtra("AGI", 0);
        ATC = ChangeToTalent.getIntExtra("ATC", 0);
        LUK = ChangeToTalent.getIntExtra("LUK", 0);

        GameReadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameGo();
            }
        });


    }
}