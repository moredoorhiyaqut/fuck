package com.example.fuck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button GameStart;
    //宣告按鈕名稱

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameStart = findViewById(R.id.linkstart);
        GameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //當使用者按下按鈕時
                Intent ChangeToChooseProfession = new Intent(MainActivity.this, GameStart.class);
                //宣告
                startActivity(ChangeToChooseProfession);
                GameStart.setEnabled(false);
                //將按鍵無法使用，為了避免使用者在切換過程中再次因為按到按鈕導致出像多次頁面
                }
            });
        }
    @Override
    protected void onResume(){//當使用者回到該頁面時
        super.onResume();
        GameStart.setEnabled(true);
        //將按鈕重新啟用，避免使用者切換後按到返回鍵無法使用按鈕
    }
}
