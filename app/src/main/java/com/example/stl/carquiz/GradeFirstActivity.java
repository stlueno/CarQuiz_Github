package com.example.stl.carquiz;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import DataAccess.DataBaseHelper;

public class GradeFirstActivity extends AppCompatActivity  implements View.OnClickListener{

    private int NumberOfQuestions = 10;
    private TextView questionText;
    private TextView questionsText;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button answerButton4;
    private int point;
    private String questions[][] = new String[NumberOfQuestions][5];
    private int count = 0;
    int counts=0;
    private int score = 0;
    private int percent = 0;
    private String answerStr;
    int[] order = null;
    private static Toast t;
    private int NumberOfQuestions2 = 10;
    private String questionsList[][];
    private String AnswerList[];
    private SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_junior);
        // ビューの初期化
        questionText  = (TextView) findViewById(R.id.question);
        questionsText = (TextView)findViewById(R.id.score);
        answerButton1 = (Button) findViewById(R.id.one);
        answerButton2 = (Button) findViewById(R.id.two);
        answerButton3 = (Button) findViewById(R.id.three);
        answerButton4 = (Button) findViewById(R.id.four);
        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);

        // 問題をセット
        try {
            AssetManager as = getResources().getAssets();
            InputStream is = as.open("testjunior.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(is), ',');
            for (int i = 0; i < NumberOfQuestions; i++) {
                questions[i] = reader.readNext();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        // 問題をシャッフル

        // 問題をボタンのラベルとして表示
        setNextText();
    }
    private int[] createRandomArray(int n, int offset) {
        int data[] = new int[n];
        Random random1 = new Random();
        Random random2 = new Random();
        int buf, i, rnd1, rnd2;
        for (i = 0; i < n; i++) {
            data[i] = i + offset;
        }
        for (i = 0; i < n * 10; i++) {
            rnd1 = random1.nextInt(n);
            rnd2 = random2.nextInt(n);
            buf = data[rnd1];
            data[rnd1] = data[rnd2];
            data[rnd2] = buf;
        }
        return data;
    }
    private void setNextText() {
        if (count >= NumberOfQuestions) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("point", point);
            intent.putExtra("percent",percent);
            startActivity(intent);
            count = 0;
        }
        answerStr = questions[count][1];
        int rndNum[] = createRandomArray(4, 1);
        questionText.setText(questions[count][0]);
        answerButton1.setText(questions[count][rndNum[0]]);
        answerButton2.setText(questions[count][rndNum[1]]);
        answerButton3.setText(questions[count][rndNum[2]]);
        answerButton4.setText(questions[count][rndNum[3]]);
        count++;
        score = score +1;
        questionsText.setText(score + "/" + "10");


    }

    @Override
    public void onClick(View v) {
        if (((Button)v).getText().equals(answerStr)) {
            point += 1;
            percent += 1;
            if(t != null){
                t.cancel();
            }
            t = Toast.makeText(this,"正解です",Toast.LENGTH_SHORT);
            t.show();
            setNextText();
        } else {

            // SQLiteOpenHelperをインスタンス化する
            DataBaseHelper db = new DataBaseHelper(getApplicationContext(),"CarQuiz.db",null,1);
            sql = db.getWritableDatabase();
            String answer = answerStr;
            String question = questionText.getText().toString();
            String insert = "INSERT INTO MissQuestion(question,answer) VALUES (" + "'" + question + "'" + "," + "'" + answer+ "'" + ")";
            sql.execSQL(insert);


                /*CSVWriter csvWriter = null;
                try {
                    csvWriter = new CSVWriter(new OutputStreamWriter( openFileOutput("test4output.csv", Context.MODE_PRIVATE)));
                    List<String[]> outStrList = new ArrayList<String[]>();
                    String[] outStr = new String[2];
                    outStr[0] = questionText.getText().toString();
                    outStr[1] = answerStr;
                    outStrList.add(outStr);
                    Log.d("D","quiz" + outStr[0]);
                    Log.d("D","quiz" + outStr[1]);

                    csvWriter.writeAll(outStrList);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (csvWriter != null) {
                        try {
                            csvWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }*/


            //csvWriter = new CSVWriter(new OutputStreamWriter( openFileOutput("test4output.csv", Context.MODE_PRIVATE)));

            if(t != null){
                t.cancel();
            }
            t = Toast.makeText(this,"不正解です",Toast.LENGTH_SHORT);
            t.show();
            Log.d("Quiz","point:" + point);
            Log.d("Quiz","percent:" + percent);

            setNextText();
        }
    }
}


