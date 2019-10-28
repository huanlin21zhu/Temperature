package com.example.temperature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {
    private Button zh;
    private TextView ts,out;
    private EditText inp;
    String sheshi="";
    String title="",detail="";
    float huilv=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title","");
        detail = bundle.getString("detail","");
        zh=(Button)findViewById(R.id.zhuan2);
        ts=(TextView)findViewById(R.id.huatemp2);
        huilv=100/(Float.parseFloat(detail));
        ts.setText("人民币==>"+title+" = "+String.valueOf(huilv));
        out=(TextView)findViewById(R.id.outw);
        zh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inp=(EditText)findViewById(R.id.stemp2);
                sheshi=inp.getText().toString();
                if(sheshi!=null&&sheshi.matches("^[0.0-9.0]+$")){
                    float res=Float.parseFloat(sheshi)*huilv;
                    out.setText("转换结果为："+String.valueOf(res));
                }
                else{
                    out.setText("请正确输入！");
                    inp.setText("");
                }
            }
        });
    }
}
