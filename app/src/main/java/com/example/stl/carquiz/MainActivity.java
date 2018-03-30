package com.example.stl.carquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//メイン画面
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void PlayButton(View v){
        Intent intent = new Intent(this,ChoiceActivity.class);
        startActivity(intent);
    }

    public void ReviewButton(View v){
        Intent intent = new Intent(this,MissChoiceActivity.class);
        startActivity(intent);
    }

    public void HelpButton(View v){
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);
    }
}
