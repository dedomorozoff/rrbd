package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btnSettings;
    private Button btnExit;

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
            Toast toast= (Toast) Toast.makeText(getApplicationContext(),"тут скоро будут настройки",Toast.LENGTH_SHORT);
            toast.show();

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
        btnStart=findViewById(R.id.btn1);
        btnSettings=findViewById(R.id.btn2);
        btnExit=findViewById(R.id.btn3);
        btnStart.setOnClickListener(btnStartListener);
        btnSettings.setOnClickListener(btnSettingsListener);
        btnExit.setOnClickListener(btnExitListener);

    }
}
