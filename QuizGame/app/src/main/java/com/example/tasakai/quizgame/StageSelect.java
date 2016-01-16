package com.example.tasakai.quizgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StageSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_select);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setIcon();
    }

    private void setIcon() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT Clear FROM MyTable";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        int[] ClearFlg = new int[c.getCount()];

        for(int i = 0; i < c.getCount(); i++) {
            ClearFlg[i] = c.getInt(0);
            c.moveToNext();
        }
        c.close();
        db.close();

        for(int i = 1; i <= 10; i++) {
            String resViewName = "button" + i;
            int viewId = getResources().getIdentifier(resViewName, "id", getPackageName());
            Button button = (Button) findViewById(viewId);
            if(ClearFlg[i-1] == 1) {
                button.setText(String.valueOf(i));
                button.setTextColor(0xff58BE89);
                button.setEnabled(true);
            } else {
                button.setText(String.valueOf(i));
                button.setTextColor(0xffffff);
                button.setBackgroundColor(0xffB7B7B7);
                button.setEnabled(false);
            }
        }
    }

    public void onClick(View v) {
        // 画面遷移を定義
        Intent intent = new Intent(StageSelect.this, MainGame.class);
        // 遷移先の画面の問題番号をクリックした番号に変更
        intent.putExtra("QuestionNo", ((Button)v).getText());
        // 遷移開始
        startActivity(intent);
    }

}
