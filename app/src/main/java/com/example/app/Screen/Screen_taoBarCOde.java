package com.example.app.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.app.CodeScreen.taoBarcode;
import com.example.app.R;

public class Screen_taoBarCOde extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_tao_bar_c_ode);
        Toolbar toolbar = findViewById(R.id.toolbar_barcode);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Strings
        SharedPreferences name = getSharedPreferences("barcodename",MODE_PRIVATE);
        String barcodename = name.getString("barcodename","something");
        SharedPreferences hint = getSharedPreferences("barcodenhint",MODE_PRIVATE);
        String barcodehint = hint.getString("barcodehint","something");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void gotoCreateBarcode(View v){
        SharedPreferences name = getSharedPreferences("barcodename",MODE_PRIVATE);
        SharedPreferences hint = getSharedPreferences("barcodenhint",MODE_PRIVATE);
        SharedPreferences.Editor editor = name.edit();
        SharedPreferences.Editor editor1 = hint.edit();
        if(v.getId() == R.id.btn_taoBarcode1){
            editor.putString("barcodename","Code_128");
            editor1.putString("barcodehint","Code_128");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode2){
            editor.putString("barcodename","AZTEC");
            editor1.putString("barcodehint","AZTEC");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode3){
            editor.putString("barcodename","CODABAR");
            editor1.putString("barcodehint","CODABAR");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode4){
            editor.putString("barcodename","CODE 39");
            editor1.putString("barcodehint","CODE 39");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode5){
            editor.putString("barcodename","CODE 93");
            editor1.putString("barcodehint","CODE 93");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode6){
            editor.putString("barcodename","DATA MATRIX");
            editor1.putString("barcodehint","DATA MATRIX");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode7){
            editor.putString("barcodename","EAN8");
            editor1.putString("barcodehint","EAN8");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode8){
            editor.putString("barcodename","EAN13");
            editor1.putString("barcodehint","EAN13");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode9){
            editor.putString("barcodename","ITF");
            editor1.putString("barcodehint","ITF");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode10){
            editor.putString("barcodename","MAXICODE");
            editor1.putString("barcodehint","MAXICODE");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode11){
            editor.putString("barcodename","PDF 417");
            editor1.putString("barcodehint","PDF 417");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode12){
            editor.putString("barcodename","RSS 14");
            editor1.putString("barcodehint","RSS 14");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode13){
            editor.putString("barcodename","RSS EXPANDED");
            editor1.putString("barcodehint","RSS ENXPANDED");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode14){
            editor.putString("barcodename","UPCA");
            editor1.putString("barcodehint","UPCA");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode15){
            editor.putString("barcodename","UPCE");
            editor1.putString("barcodehint","UPCE");
            editor.apply();
            editor1.apply();
        }
        if(v.getId() == R.id.btn_taoBarcode16){
            editor.putString("barcodename","UPCAN");
            editor1.putString("barcodehint","UPCAN");
            editor.apply();
            editor1.apply();
        }
        Intent intent = new Intent(getApplicationContext(), taoBarcode.class);
        startActivity(intent);
    }
}