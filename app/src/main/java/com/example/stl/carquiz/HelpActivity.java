package com.example.stl.carquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//問題の説明をする画面
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
    public void onClick(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
