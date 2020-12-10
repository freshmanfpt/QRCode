package com.example.app.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.app.DAO.maCode;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(@Nullable Context context) {
        super(context, "QRCode", null, 1);
    }
    public final String TABLE_NAME = "QRCode";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQRCode  = "CREATE TABLE "+TABLE_NAME+" (maCode text primary key,theLoai text, ngayThang text,danhsach text);";
        sqLiteDatabase.execSQL(createTableQRCode);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public void addQRcode(maCode maCode){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();//xin quyền
        ContentValues contentValues = new ContentValues();//khai báo và ghép cặp giá trị với cột tương ứng
        contentValues.put("maCode",maCode.getMaCode());
        contentValues.put("theLoai",maCode.getTheLoai());
        contentValues.put("ngayThang",maCode.getNgayThang());
        contentValues.put("danhsach",maCode.getDanhsach());
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public List<maCode> getmaCode(){
        List<maCode> maCodeList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();//khi dùng constraint không dùng được cái này
        Cursor cursor = sqLiteDatabase.query("QRCode",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String maCode = cursor.getString(0);
            String theLoai = cursor.getString(1);
            String ngayThang = cursor.getString(2);
            String danhsach = cursor.getString(3);
            maCode maCode1 = new maCode(maCode,theLoai,ngayThang,danhsach);
            maCodeList.add(maCode1);
        }
        cursor.close();
        return maCodeList;
    }
    public List<maCode> getDanhSachQuet(){
        List<maCode> maCodeList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor;
        String danhsach1 = "quet";
        cursor= sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE danhsach LIKE '" +danhsach1+"%'",null);
        while (cursor.moveToNext()){
            String maCode = cursor.getString(0);
            String theLoai = cursor.getString(1);
            String ngayThang = cursor.getString(2);
            String danhsach = cursor.getString(3);
            maCode maCode1 = new maCode(maCode,theLoai,ngayThang,danhsach);
            maCodeList.add(maCode1);
        }
        cursor.close();
        return maCodeList;
    }
    public List<maCode> getDanhSachTao(){
        List<maCode> maCodeList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor;
        String danhsach1 = "tao";
        cursor= sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE danhsach LIKE '" +danhsach1+"%'",null);
        while (cursor.moveToNext()){
            String maCode = cursor.getString(0);
            String theLoai = cursor.getString(1);
            String ngayThang = cursor.getString(2);
            String danhsach = cursor.getString(3);
            maCode maCode1 = new maCode(maCode,theLoai,ngayThang,danhsach);
            maCodeList.add(maCode1);
        }
        cursor.close();
        return maCodeList;
    }
    public void deletemaCode(String maCode){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("QRCode","maCode = ?", new String[]{maCode});
    }
}
