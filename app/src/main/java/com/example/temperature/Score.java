package com.example.temperature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends AppCompatActivity {
    private Button score1,score2,score3,score4,score5,score6,refresh;
    private TextView score11,score22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score6=(Button)findViewById(R.id.sc6);
        score5=(Button)findViewById(R.id.sc5);
        score4=(Button)findViewById(R.id.sc4);
        score3=(Button)findViewById(R.id.sc3);
        score2=(Button)findViewById(R.id.sc2);
        score1=(Button)findViewById(R.id.sc1);
        refresh=(Button)findViewById(R.id.clean);
        score11=(TextView)findViewById(R.id.sco1);
        score22=(TextView)findViewById(R.id.sco2);
        score1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score11.setText(String.valueOf(Integer.parseInt(score11.getText().toString())+1));
            }
        });

        score2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score11.setText(String.valueOf(Integer.parseInt(score11.getText().toString())+2));
            }
        });

        score3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score11.setText(String.valueOf(Integer.parseInt(score11.getText().toString())+3));
            }
        });

        score4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score22.setText(String.valueOf(Integer.parseInt(score22.getText().toString())+1));
            }
        });

        score5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score22.setText(String.valueOf(Integer.parseInt(score22.getText().toString())+2));
            }
        });

        score6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score22.setText(String.valueOf(Integer.parseInt(score22.getText().toString())+3));
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score11.setText("0");
                score22.setText("0");
            }
        });
    }
}
