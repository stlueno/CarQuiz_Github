package com.example.stl.carquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle = getIntent().getExtras();
        Bundle bundle2 = getIntent().getExtras();
        if (bundle != null) {
            int point = bundle.getInt("point");
            TextView scoreText = (TextView) findViewById(R.id.point);
            scoreText.setText(String.format("%d", point));
        }

        if(bundle2 != null){
            int percent = bundle2.getInt("percent");
            TextView percentText = (TextView)findViewById(R.id.percent);
            percent = percent * 10;
            percentText.setText(String.format("%d",percent));
            Log.d("RESULT","percent:" + percent);
        }

    }

    public void onClickChoice(View v){
        Intent intent = new Intent(this,ChoiceActivity.class);
        startActivity(intent);
    }

    public void onClickTop(View v){
        Intent intent2 = new Intent(this,MainActivity.class);
        startActivity(intent2);
    }
}