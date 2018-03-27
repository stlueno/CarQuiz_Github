package com.example.stl.carquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import DataAccess.DataBaseHelper;

public class MissJuniorActivity extends AppCompatActivity {
    private SQLiteDatabase sql;
    private TextView questionText;
    private Button AnswerButton;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miss_junior);

        // SQLiteOpenHelperをインスタンス化する
        DataBaseHelper db = new DataBaseHelper(getApplicationContext(),"CarQuiz.db",null,1);
        sql = db.getWritableDatabase();
        String select = "select * from MissQuestion where grade='ジュニア級'";
        Cursor cursor = sql.rawQuery(select, null);
        startManagingCursor(cursor);

        String result_question = "";
        String result_answer = "";

        while( cursor.moveToNext() ){
            count++;
            int index_question    =   cursor.getColumnIndex("question");
            int index_answer  =   cursor.getColumnIndex("answer");
            String question          =   cursor.getString(index_question);
            String answer     =   cursor.getString(index_answer);
            result_question      += "問題 " + count + "\n"+ question + "\n"+ "\n" + "答え："+ answer + "\n" + "\n";
            result_answer      += " 答え:" +  answer + "\n";

        }

        TextView tv = (TextView)findViewById(R.id.MissQuiz);
        tv.setText( result_question );
        //Button bt = (Button)findViewById(R.id.Answer);
        //bt.setText(result_answer);
    }

    public void onClick(View v){
        // 確認ダイアログの生成
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("削除確認");
        alertDlg.setMessage("削除します。よろしいですか？");
        alertDlg.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // OK ボタンクリック処理
                        DataBaseHelper db = new DataBaseHelper(getApplicationContext(),"CarQuiz.db",null,1);
                        sql = db.getWritableDatabase();
                        String delete = "delete from MissQuestion";
                        sql.execSQL(delete);
                    }
                });
        alertDlg.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // 表示
        alertDlg.create().show();
        Toast.makeText(this,"削除後はすぐに反映されないため一旦戻り再度ご覧ください",Toast.LENGTH_LONG).show();
    }

    public void onClick2(View v){
        Intent intent = new Intent(this,MissChoiceActivity.class);
        startActivity(intent);

    }
}
