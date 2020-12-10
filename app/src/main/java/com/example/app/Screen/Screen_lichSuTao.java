package com.example.app.Screen;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.DAO.maCode;
import com.example.app.DAO.maCodeAdapter;
import com.example.app.R;
import com.example.app.SQL.SQLite;

import java.util.List;

public class Screen_lichSuTao extends AppCompatActivity {
    SQLite sqLite;
    ListView listViewTao;
    com.example.app.DAO.maCodeAdapter maCodeAdapter;
    List<maCode> maCodeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_lich_su_tao);
        Toolbar toolbar = findViewById(R.id.toolbar_lstao);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listViewTao= findViewById(R.id.lv_lichsutao);
        sqLite = new SQLite(Screen_lichSuTao.this);
        maCodeList = sqLite.getDanhSachTao();
        maCodeAdapter = new maCodeAdapter(maCodeList);
        listViewTao.setAdapter(maCodeAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}