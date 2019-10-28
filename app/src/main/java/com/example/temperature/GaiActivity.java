package com.example.temperature;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class GaiActivity extends AppCompatActivity {
    private EditText mei,ou,han;
    private TextView tishi;
    private Button gai,cha,sel,lan;
    float dollarRate=0,euroRate=0,wonRate=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gai);
        Bundle bundle = getIntent().getExtras();
        dollarRate = bundle.getFloat("dollar",0.0f);
        euroRate = bundle.getFloat("euro",0.0f);
        wonRate = bundle.getFloat("won",0.0f);

        gai = (Button) findViewById(R.id.btn_Save2);
        cha = (Button) findViewById(R.id.Select);
        sel = (Button) findViewById(R.id.chakan);
        lan = (Button) findViewById(R.id.liulan);
        tishi = (TextView) findViewById(R.id.hint2);

        mei = findViewById(R.id.DollarRate2);
        ou = findViewById(R.id.EuroRate2);
        han = findViewById(R.id.WonRate2);
        mei.setText(String.valueOf(dollarRate));
        ou.setText(String.valueOf(euroRate));
        han.setText(String.valueOf(wonRate));
        sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SharedPreferences share=  getSharedPreferences("huilv", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = share.edit();

                            String url = "http://www.usd-cny.com/bankofchina.htm";
                            Document doc = Jsoup.connect(url).get();
                            Log.i("TAG", "run: " + doc.title());
                            Elements tables = doc.getElementsByTag("table");
                            Element table6 = tables.get(0);
                            //获取TD中的数据
                            Elements tds = table6.getElementsByTag("td");
                            editor2.putString("hv0",String.valueOf(tds.size()));
                            for(int i=0;i<tds.size();i+=6) {
                                Element td1 = tds.get(i);
                                Element td2 = tds.get(i + 5); //网页编码，每个5个table
                                String str1 = td1.text(); //货币种类
                                String val = td2.text();  //折算价
                                Log.i("TAG", "run: " + str1 + "==>" + val);
                                editor2.putString("hv"+String.valueOf(i+1), str1 + "==>" + val);
                            }
                            editor2.commit();
                            Intent intent = new Intent(GaiActivity.this,ShActivity.class);
                            startActivity(intent);
                        }
                        catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GaiActivity.this,SxActivity.class);
                startActivity(intent);
            }
        });

        gai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = mei.getText().toString();
                String str2 = ou.getText().toString();
                String str3 = han.getText().toString();
                int hint1=1,hint2=1,hint3=1;
                if(str1!=null&&str1.matches("^[0.0-9.0]+$")){
                    hint1=0;
                }
                if(str2!=null&&str2.matches("^[0.0-9.0]+$")){
                    hint2=0;
                }
                if(str3!=null&&str3.matches("^[0.0-9.0]+$")){
                    hint3=0;
                }
                if(hint1==0&&hint2==0&&hint3==0){
                    dollarRate = (Float.parseFloat(str1));
                    euroRate = (Float.parseFloat(str2));
                    wonRate = (Float.parseFloat(str3));

                    SharedPreferences sharedpreferences =  getSharedPreferences("fwrate", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putFloat("dollar_rate", dollarRate);
                    editor.putFloat("euro_rate", euroRate);
                    editor.putFloat("won_rate", wonRate);
                    editor.commit();
                    Log.i("TAG", "onActivityResult: 数据已保存");

                    Intent intent = getIntent();
                    Bundle bdl = new Bundle();
                    bdl.putFloat("new_dollar", dollarRate);
                    bdl.putFloat("new_euro", euroRate);
                    bdl.putFloat("new_won", wonRate);
                    intent.putExtras(bdl);
                    setResult(2,intent);//设置resultCode及带回的数据
                    finish();//返回到调用页面
                    //下面日志信息
                    Log.i("TAG", "onActivityResult: dollarRate=" + dollarRate);
                    Log.i("TAG", "onActivityResult: euroRate=" + euroRate);
                    Log.i("TAG", "onActivityResult: wonRate=" + wonRate);
                }
                else{
                    tishi.setText("请正确输入!");
                }
            }
        });

        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Message msg = handler.obtainMessage(5);
                            Message msg2 = handler.obtainMessage(5);
                            Message msg3 = handler.obtainMessage(5);

                            String url = "http://www.usd-cny.com/bankofchina.htm";
                            Document doc = Jsoup.connect(url).get();
                            Log.i("TAG", "run: " + doc.title());
                            Elements tables = doc.getElementsByTag("table");
                            Element table6 = tables.get(0);
                            //获取TD中的数据
                            Elements tds = table6.getElementsByTag("td");
                            for(int i=0;i<tds.size();i+=6){
                                Element td1 = tds.get(i);
                                Element td2 = tds.get(i+5); //网页编码，每个5个table
                                String str1 = td1.text(); //货币种类
                                String val = td2.text();  //折算价
                                Log.i("TAG", "run: " + str1 + "==>" + val);
                                float v = 100f / Float.parseFloat(val);  //实时变化的汇率
                                //获取数据并返回……
                                if(str1.equals("美元")){
                                    msg.obj = str1+String.valueOf(v);
                                }
                                if(str1.endsWith("欧元")){
                                    msg2.obj = str1+String.valueOf(v);
                                }
                                if(str1.equals("韩元")){
                                    msg3.obj = str1+String.valueOf(v);
                                }
                            }
                            //Log.i("TAG", "run: html=" + html);
                            //msg.what = 5;
                            handler.sendMessage(msg);
                            handler.sendMessage(msg2);
                            handler.sendMessage(msg3);
                        }
                        catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }

    private Handler handler =  new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==5){
                String str = (String) msg.obj;
                //Log.i("TAG", "handleMessage: getMessage msg = " + str);
                if(str.substring(0,2).equals("美元")){
                    mei.setText(str.substring(2,str.length()));
                }
                if(str.substring(0,2).equals("欧元")){
                    ou.setText(str.substring(2,str.length()));
                }
                if(str.substring(0,2).equals("韩元")){
                    han.setText(str.substring(2,str.length()));
                }
            }
            super.handleMessage(msg);
        }
    };
}
