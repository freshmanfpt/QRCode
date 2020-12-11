package com.example.app.CodeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.app.DAO.maCode;
import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.example.app.ShowCode.webXemMa;

import java.util.ArrayList;

import static com.example.app.MainActivity.getCurrentTime;

public class taoQRWifi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_q_r_wifi);
        Toolbar toolbar = findViewById(R.id.toolbar_taoQRwifi);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String[] strings = {"WPA/WPA2","WEP","-"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,strings);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        if (item.getItemId() == R.id.check){
            String[] types = new String[]{"WPA", "WEP", "nopass"};

            EditText txtTenMang = findViewById(R.id.editTextTextPersonName5);
            EditText txtMatKhau = findViewById(R.id.editTextTextPersonName7);
            Spinner spnLoaiMang = findViewById(R.id.spinner);

            String type = types[spnLoaiMang.getSelectedItemPosition()];
            String tenMang = txtTenMang.getText().toString();
            String matKhau = txtMatKhau.getText().toString();

            Intent intent = new Intent(this, webXemMa.class);
            Bundle bundle = new Bundle();
            String noiDung = "WIFI:T:"+type+";S:"+tenMang+";P:"+matKhau+";H:;";
            bundle.putString("noiDung", noiDung);
            bundle.putString("theLoai", "wifi");
            bundle.putString("thoiGian", getCurrentTime());
            intent.putExtras(bundle);
            SQLite sqLite = new SQLite(taoQRWifi.this);
            maCode maCode = new maCode(noiDung,"wifi",getCurrentTime(),"tao");
            sqLite.addQRcode(maCode);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }
}