package com.example.npay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class expenseActivity extends AppCompatActivity {

    ListView expenseHistory ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        expenseHistory = (ListView)findViewById(R.id.expenseHistory);

        DbHandler db = new DbHandler(this);

        ArrayList<HashMap<String, String>> userList = db.GetUsers();

        ListAdapter adapter = new SimpleAdapter(expenseActivity.this, userList, R.layout.custom_listview,new String[]{"name","money","date"}, new int[]{R.id.name, R.id.money, R.id.date});
        expenseHistory.setAdapter(adapter);


    }
}
