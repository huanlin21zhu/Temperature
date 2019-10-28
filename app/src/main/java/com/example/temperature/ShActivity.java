package com.example.temperature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ShActivity extends AppCompatActivity {
    List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh);

        SharedPreferences getBeforeTopic = getSharedPreferences("huilv", MODE_PRIVATE);
        ListView listView = (ListView) findViewById(R.id.mylist);
        data = new ArrayList<String>();
        String length = getBeforeTopic.getString("hv0","");
        for(int i=0;i<Integer.parseInt(length);i+=6) {
            data.add(getBeforeTopic.getString("hv"+String.valueOf(i+1),""));
        }
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

    }

}
