package com.example.tasakai.quizgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // ボタンがタッチされた時の処理
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button_normal:
                Toast.makeText(this, "ノーマルがタッチされた！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_random:
                Toast.makeText(this, "ランダムがタッチされた！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_time:
                Toast.makeText(this, "タイムアタックがタッチされた！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
