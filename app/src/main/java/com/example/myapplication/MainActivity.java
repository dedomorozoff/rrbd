package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private View.OnClickListener btnStartListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener btnSettingsListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,Main3Activity.class);
        startActivity(intent);
        }
    };
    private View.OnClickListener btnExitListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = findViewById(R.id.btn1);
        Button btnSettings = findViewById(R.id.btn2);
        Button btnExit = findViewById(R.id.btn3);
        btnStart.setOnClickListener(btnStartListener);
        btnSettings.setOnClickListener(btnSettingsListener);
        btnExit.setOnClickListener(btnExitListener);

    }
}
