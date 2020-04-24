package com.example.npay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView totalIncome , totalExpense ;
    ImageView addMoney , subMoney ,addHistory , subHistory ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initilizers();

        DbHandler db = new DbHandler(this);
        DbHandler1 db1 = new DbHandler1(this);

        int sum = db.addAllValues();
        totalExpense.setText(Integer.toString(sum));

        int sum1 = db1.addAllValues();
        totalIncome.setText(Integer.toString(sum1));


        subMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,addExpense.class);
                startActivity(intent);
            }
        });
        subHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,expenseActivity.class);
                startActivity(intent);
            }
        });

        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,addIncome.class);
                startActivity(intent);
            }
        });
        addHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,incomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initilizers() {
        addMoney = (ImageView)findViewById(R.id.addMoney);
        addHistory = (ImageView)findViewById(R.id.addHistory);
        subMoney = (ImageView)findViewById(R.id.subMoney);
        subHistory = (ImageView)findViewById(R.id.subHistory);

        totalIncome = (TextView)findViewById(R.id.totalIncome);
        totalExpense = (TextView)findViewById(R.id.totalExpense);
    }
}
