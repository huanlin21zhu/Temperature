package com.example.temperature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    private Button zhbt,zhbt2,zhbt3,change;
    private TextView shuchu;
    private EditText shuru;
    float dollarRate = 0.1406f;
    float euroRate = 0.1276f;
    float wonRate = 167.8471f;
    String sheshi="",huashi="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zhbt=(Button)findViewById(R.id.zhuan);
        zhbt2=(Button)findViewById(R.id.zhuan2);
        zhbt3=(Button)findViewById(R.id.zhuan3);
        change=(Button)findViewById(R.id.xiugai);
        shuchu=(TextView)findViewById(R.id.huatemp);
        zhbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuru=(EditText)findViewById(R.id.stemp);
                sheshi=shuru.getText().toString();
                if(sheshi!=null&&sheshi.matches("^[0.0-9.0]+$")){
                    huashi=String.valueOf(Float.parseFloat(sheshi)*dollarRate)+"$";
                    shuchu.setText("汇率换算："+huashi);
                }
                else{
                    shuchu.setText("请正确输入!");
                    shuru.setText("");
                }
            }
        });

        zhbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuru=(EditText)findViewById(R.id.stemp);
                sheshi=shuru.getText().toString();
                if(sheshi!=null&&sheshi.matches("^[0.0-9.0]+$")){
                    huashi=String.valueOf(Float.parseFloat(sheshi)*euroRate)+"€";
                    shuchu.setText("汇率换算："+huashi);
                }
                else{
                    shuchu.setText("请正确输入!");
                    shuru.setText("");
                }
            }
        });

        zhbt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuru=(EditText)findViewById(R.id.stemp);
                sheshi=shuru.getText().toString();
                if(sheshi!=null&&sheshi.matches("^[0.0-9.0]+$")){
                    huashi=String.valueOf(Float.parseFloat(sheshi)*wonRate)+"₩";
                    shuchu.setText("汇率换算："+huashi);
                }
                else{
                    shuchu.setText("请正确输入!");
                    shuru.setText("");
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuru=(EditText)findViewById(R.id.stemp);
                shuru.setText("");
                shuchu.setText("");
                Intent intent = new Intent(MainActivity.this,GaiActivity.class);
                Bundle bdl = new Bundle();
                bdl.putFloat("dollar", dollarRate);
                bdl.putFloat("euro", euroRate);
                bdl.putFloat("won", wonRate);
                intent.putExtras(bdl);
                startActivityForResult(intent,1);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 2) {

            SharedPreferences getBeforeTopic = getSharedPreferences("fwrate", MODE_PRIVATE);
            float dollar = getBeforeTopic.getFloat("dollar_rate", 0.0f);
            float euro = getBeforeTopic.getFloat("euro_rate", 0.0f);
            float won = getBeforeTopic.getFloat("won_rate", 0.0f);
            Log.i("TAG", "onActivityResult: dollar=" + dollar);
            Log.i("TAG", "onActivityResult: euro=" + euro);
            Log.i("TAG", "onActivityResult: won=" + won);

            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("new_dollar", 0.0f);
            euroRate = bundle.getFloat("new_euro", 0.0f);
            wonRate = bundle.getFloat("new_won", 0.0f);
            //下面日志信息
            Log.i("TAG", "onActivityResult: dollarRate2=" + dollarRate);
            Log.i("TAG", "onActivityResult: euroRate2=" + euroRate);
            Log.i("TAG", "onActivityResult: wonRate2=" + wonRate);
        }
        super.onActivityResult( requestCode, resultCode, data);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.xianshi,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.Drate){
            // 事件处理代码
            String dollarRate2 = String.valueOf(dollarRate);
            Toast.makeText(this, "美元汇率为："+ dollarRate2, Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.Erate){
            // 事件处理代码
            String euroRate2 = String.valueOf(euroRate);
            Toast.makeText(this, "欧元汇率为："+ euroRate2, Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.Wrate){
            // 事件处理代码
            String wonRate2 = String.valueOf(wonRate);
            Toast.makeText(this, "韩元汇率为："+ wonRate2, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
