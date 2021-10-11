package com.example.firebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivityR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_r);
    }

    public void Quran(View view) {
        Intent i = new Intent(MainActivityR.this,Quran.class);
        startActivity(i);

    }
}