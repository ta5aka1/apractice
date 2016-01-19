package com.example.tasakai.quizgame;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainGame extends Activity {
    String QuestionNo;
    String Seikai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Intent intent = getIntent();
        QuestionNo = intent.getStringExtra("QuestionNo");
    }

    @Override
    protected void onResume(){
        super.onResume();

        ((TextView)findViewById(R.id.textNo)).setText("クイズNo" + QuestionNo);
        
        //問題セット
        setQuestion();
    }

    private void setQuestion() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT Pref, City0, City1, City2, City3, City4 FROM MyTable WHERE _id=" + QuestionNo;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        String Kenmei = c.getString(c.getColumnIndex("Pref"));
        String Choice1 = c.getString(c.getColumnIndex("City1"));
        String Choice2 = c.getString(c.getColumnIndex("City2"));
        String Choice3 = c.getString(c.getColumnIndex("City3"));
        String Choice4 = c.getString(c.getColumnIndex("City4"));

        Seikai = c.getString(c.getColumnIndex("City0"));

        c.close();
        db.close();

        ((TextView)findViewById(R.id.textQuestion)).setText(Kenmei);
        ((Button)findViewById(R.id.button1)).setText(Choice1);
        ((Button)findViewById(R.id.button2)).setText(Choice2);
        ((Button)findViewById(R.id.button3)).setText(Choice3);
        ((Button)findViewById(R.id.button4)).setText(Choice4);

    }

    public void onClick(View v){
        if(((Button)v).getText().equals(Seikai)) {
            Toast.makeText(this, "正解！", Toast.LENGTH_SHORT).show();

            ContentValues values = new ContentValues();
            values.put("Clear", 1);
            String whereClause = "_id = ?";

            DatabaseHelper dbHelpear = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelpear.getWritableDatabase();

            int ret;
            try{
                ret = db.update("MyTable", values, whereClause, new String[]{String.valueOf(Integer.parseInt(QuestionNo)+1)});
            }finally {
                db.close();
            }
            if(ret == -1){} else{}
        } else {
            Toast.makeText(this, "まちがい！", Toast.LENGTH_SHORT).show();
        }
    }
}
