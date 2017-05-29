package com.example.bikramkoju.barberfinalapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 5/29/2017.
 */

public class ShowForUpdate extends AppCompatActivity {
    ListView lv;
    ArrayList<InfoModule> list = new ArrayList<InfoModule>();
    DatabaseHelper dp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);


        lv=(ListView)findViewById(R.id.listview);

        dp=new DatabaseHelper(ShowForUpdate.this);
        list=dp.getInfo();
        lv.setAdapter(new UpdateAdapter(ShowForUpdate.this,list));
    }
}
