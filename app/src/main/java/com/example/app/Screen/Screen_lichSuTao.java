package com.example.app.Screen;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.DAO.maCode;
import com.example.app.DAO.maCodeAdapter;
import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.example.app.ShowCode.barcodeShow;
import com.example.app.ShowCode.vanBanShow;
import com.example.app.ShowCode.vitriShowCode;
import com.example.app.ShowCode.webShowCode;
import com.example.app.ShowCode.wifiShowCode;

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

        listViewTao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                maCode maCode = maCodeList.get(i);
                if(maCode.getTheLoai().equalsIgnoreCase("web")){
                    Intent intent = new Intent(Screen_lichSuTao.this, webShowCode.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("noiDung", maCode.getMaCode());
                    bundle.putString("theLoai", maCode.getTheLoai());
                    bundle.putString("thoiGian", maCode.getNgayThang());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(maCode.getTheLoai().equalsIgnoreCase("viTri")){
                    Intent intent = new Intent(Screen_lichSuTao.this, vitriShowCode.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("noiDung", maCode.getMaCode());
                    bundle.putString("theLoai", maCode.getTheLoai());
                    bundle.putString("thoiGian", maCode.getNgayThang());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(maCode.getTheLoai().equalsIgnoreCase("wifi")){
                    Intent intent = new Intent(Screen_lichSuTao.this, wifiShowCode.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("noiDung", maCode.getMaCode());
                    bundle.putString("theLoai", maCode.getTheLoai());
                    bundle.putString("thoiGian", maCode.getNgayThang());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(maCode.getTheLoai().equalsIgnoreCase("vanban")){
                    Intent intent = new Intent(Screen_lichSuTao.this, vanBanShow.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("noiDung", maCode.getMaCode());
                    bundle.putString("theLoai", maCode.getTheLoai());
                    bundle.putString("thoiGian", maCode.getNgayThang());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(maCode.getTheLoai().startsWith("barcode")){
                    Intent intent = new Intent(Screen_lichSuTao.this, barcodeShow.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("noiDung", maCode.getMaCode());
                    bundle.putString("theLoai", maCode.getTheLoai());
                    bundle.putString("thoiGian", maCode.getNgayThang());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
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