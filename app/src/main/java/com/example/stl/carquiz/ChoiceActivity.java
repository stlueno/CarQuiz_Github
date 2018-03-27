package com.example.stl.carquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }

    public void onClickJunior(View v){
        Intent intent = new Intent(this,GradeJuniorActivity.class);
        startActivity(intent);
    }

    public void onClickGrade3(View v){
        Intent intent = new Intent(this,GradeThirdAcitivty.class);
        startActivity(intent);
    }

    public void onClickGrade2(View v){
        Intent intent = new Intent(this,GradeSecondActivity.class);
        startActivity(intent);
    }

    public void onClickGrade1(View v){
        Intent intent = new Intent(this,GradeFirstActivity.class);
        startActivity(intent);
    }
}
