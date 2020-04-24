package com.example.npay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MONEY = "money";
    private static final String KEY_DATE = "date";


    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + " ( " +KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_NAME +
                " TEXT, " + KEY_MONEY + " TEXT, " + KEY_DATE + " TEXT " + " ) ";


        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }

    void insertUserDetails(String name , String money , String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME,name);
        cValues.put(KEY_MONEY,money);
        cValues.put(KEY_DATE,date);

        long newRowId = db.insert(TABLE_Users,null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, money, date FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("money",cursor.getString(cursor.getColumnIndex(KEY_MONEY)));
            user.put("date",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            userList.add(user);
        }
        return  userList;
    }

    public ArrayList<HashMap<String, String>> GetUserByUserId(long userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, money, date FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_NAME, KEY_MONEY, KEY_DATE}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("money",cursor.getString(cursor.getColumnIndex(KEY_MONEY)));
            user.put("date",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            userList.add(user);
        }
        return  userList;
    }
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }

    public int UpdateUserDetails(String money, String date, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_MONEY, money);
        cVals.put(KEY_DATE, date);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
    public int addAllValues(){

        int total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(" + (KEY_MONEY) + ") FROM " + TABLE_Users, null);
        if(c.moveToFirst()){
            total = c.getInt(0);
        }
        return total;
    }
    public ArrayList<HashMap<String, String>> history(String startdate,String enddate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<HashMap<String, String>> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_Users +
                " WHERE " + KEY_DATE +
                " BETWEEN '" + startdate + "' AND '" + enddate + "'", null);

        cursor.moveToFirst();

        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("money",cursor.getString(cursor.getColumnIndex(KEY_MONEY)));
            user.put("date",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            userList.add(user);
        }
        return userList;
    }

}
