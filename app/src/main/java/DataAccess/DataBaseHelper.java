package DataAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ueno on 2018/03/26.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "CarQuiz", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable = "create table MissQuestion(id integer primary key,question,answer,grade)";
        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String droptable = "drop table if exists MissQuestion";
    }
}
