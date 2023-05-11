package com.example.fuck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Story extends AppCompatActivity {
    Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Next = findViewById(R.id.button);
        Next.setOnClickListener(view -> {
            Intent GoToTown = new Intent(Story.this, Town.class);
            startActivity(GoToTown);
            Next.setEnabled(false);
        });
    }
}