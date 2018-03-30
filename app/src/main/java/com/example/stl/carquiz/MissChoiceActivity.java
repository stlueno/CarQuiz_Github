package com.example.stl.carquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//復習画面
public class MissChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miss_choice);
    }
    public void onClickMissJunior(View v){
        Intent intent = new Intent(this,MissJuniorActivity.class);
        startActivity(intent);
    }

    public void onClickMissGrade3(View v){
        Intent intent = new Intent(this,MissThirdActivity.class);
        startActivity(intent);
    }

    public void onClickMissGrade2(View v){
        Intent intent = new Intent(this,MissSecondActivity.class);
        startActivity(intent);
    }

    public void onClickMissGrade1(View v){
        Intent intent = new Intent(this,MissFirstActivity.class);
        startActivity(intent);
    }
}
