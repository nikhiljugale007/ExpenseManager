package com.example.npay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addIncome extends AppCompatActivity {

    EditText expenseAmount , expenseNote ;
    Spinner spinner ;
    Button btnDate ,btnAddExpense;
    String formatedDate = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        initilizers();

        List<String> expenseCategories = new ArrayList<String>();
        expenseCategories.add("Select category");
        expenseCategories.add("POCKET MONEY");
        expenseCategories.add("FRIEND");
        expenseCategories.add("ONLINE EARNING");
        expenseCategories.add("RELATIVES");
        expenseCategories.add("OTHER");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,R.layout.spinner_with_space,expenseCategories);
        spinner.setAdapter(dataAdapter);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addIncome.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int   nDay  = view.getDayOfMonth();
                        int   nMonth= view.getMonth();
                        int   nYear = view.getYear();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(nYear, nMonth, nDay);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        formatedDate = "";
                        formatedDate = sdf.format(calendar.getTime());

                        Toast.makeText(getApplicationContext(),"DATE = "+ formatedDate + " ",Toast.LENGTH_SHORT).show();

                    }
                },year,month,dayOfMonth);

                datePickerDialog.show();
            }
        });

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Amount = expenseAmount.getText().toString();
                String expenseCategory = String.valueOf(spinner.getSelectedItem());
                String Note = expenseNote.getText().toString();

                if(TextUtils.isEmpty(Amount) || TextUtils.isEmpty(expenseCategory) || TextUtils.isEmpty(Note) || TextUtils.isEmpty(formatedDate)){
                    Toast.makeText(getApplicationContext(),"Enter All details correctly ",Toast.LENGTH_SHORT).show();
                }
                else{
                    DbHandler1 db1 = new DbHandler1(addIncome.this);
                    db1.insertUserDetails(expenseCategory,Amount,formatedDate);

                    Intent intent = new Intent(addIncome.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void initilizers() {
        expenseAmount = (EditText)findViewById(R.id.expenseAmount);
        expenseNote = (EditText)findViewById(R.id.expenseNote);
        spinner = (Spinner)findViewById(R.id.spiner);
        btnDate = (Button)findViewById(R.id.btnDate);
        btnAddExpense =(Button)findViewById(R.id.btnAddIncome);
    }
}
