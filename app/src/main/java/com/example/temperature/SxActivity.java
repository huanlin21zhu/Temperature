package com.example.temperature;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class SxActivity extends ListActivity implements Runnable{
    private  static  String TAG ="Main";
    Elements tds;
    String j;
    ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sx);
        Thread t = new Thread(this);
        t.start();
        while (j == null) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//等待子线程完成

        for (int i = 0; i < tds.size(); i += 6) {
            HashMap<String, String> map = new HashMap<String, String>();
            Element td1 = tds.get(i);
            Element td2 = tds.get(i + 5);
            String str1 = td1.text();
            String val = td2.text();
            map.put("ItemTitle", str1); // 标题文字
            map.put("ItemDetail", val); // 详情描述
            Log.i(TAG, "run: " + str1 + "==>" + val);
            listItems.add(map);
        }
        // 生成适配器的Item和动态数组对应的元素
        //ListAdapter listItemAdapter = new SimpleAdapter(this, listItems, R.layout.activity_sx, new String[] { "ItemTitle", "ItemDetail" }, new int[] { R.id.itemTitle, R.id.itemDetail } );
        //setListAdapter(listItemAdapter);
        //非自定义的方法↑

        //自定义法↓
        MyAdapter myAdapter = new MyAdapter(this, R.layout.activity_sx, listItems);
        this.setListAdapter(myAdapter);
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.itemTitle);
                TextView detail = (TextView) view.findViewById(R.id.itemDetail);
                String title2 = String.valueOf(title.getText());
                String detail2 = String.valueOf(detail.getText());
                Log.i(TAG, "onItemClick: title2=" + title2);
                Log.i(TAG, "onItemClick: detail2=" + detail2);
                Intent intent = new Intent(SxActivity.this, TestActivity.class);
                Bundle bdl = new Bundle();
                bdl.putString("title", title2);
                bdl.putString("detail", detail2);
                intent.putExtras(bdl);
                startActivity(intent);
            }
        });

    }

    public void run() {
        Log.i(TAG, "run: run()......");
        try {
            String url = "http://www.usd-cny.com/bankofchina.htm";
            Document doc = Jsoup.connect(url).get();
            Log.i(TAG, "run: " + doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table6 = tables.get(0);
            // 获取 TD 中的数据
            tds = table6.getElementsByTag("td");
            j ="233666";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
