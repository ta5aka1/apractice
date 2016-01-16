package com.example.tasakai.quizgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RandomGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_game);
    }

    @Override
    protected void onResume(){
        super.onResume();

        ((TextView)findViewById(R.id.textNo)).setText("ランダム");
    }

    protected void onClick(View v) {

    }
}
