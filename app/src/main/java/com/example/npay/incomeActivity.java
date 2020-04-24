package com.example.npay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class incomeActivity extends AppCompatActivity {

    ListView incomeHistory ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        DbHandler1 db1 = new DbHandler1(this);
        incomeHistory = (ListView)findViewById(R.id.incomeHistory);

        ArrayList<HashMap<String, String>> userList = db1.GetUsers();

        ListAdapter adapter = new SimpleAdapter(incomeActivity.this, userList, R.layout.custom_listview,new String[]{"name","money","date"}, new int[]{R.id.name, R.id.money, R.id.date});
        incomeHistory.setAdapter(adapter);

    }
}
