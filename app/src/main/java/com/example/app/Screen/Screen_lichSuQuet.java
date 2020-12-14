package com.example.app.Screen;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.SearchView;

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

public class Screen_lichSuQuet extends AppCompatActivity {

    SQLite sqLite;
    ListView listViewQuet;
    maCodeAdapter maCodeAdapter;
    List<maCode> maCodeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_lich_su_quet);
        Toolbar toolbar = findViewById(R.id.toolbar_lsquet);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listViewQuet= findViewById(R.id.lv_lichsuquet);
        sqLite = new SQLite(Screen_lichSuQuet.this);
        maCodeList = sqLite.getDanhSachQuet();
        maCodeAdapter = new maCodeAdapter(maCodeList);
        listViewQuet.setAdapter(maCodeAdapter);

        listViewQuet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                maCode maCode = maCodeList.get(position);
                Class classToShow;
                if(maCode.getTheLoai().equalsIgnoreCase("web")){
                    classToShow = webShowCode.class;
                }else if(maCode.getTheLoai().equalsIgnoreCase("wifi")){
                    classToShow = wifiShowCode.class;
                }else if(maCode.getTheLoai().equalsIgnoreCase("barcode")){
                    classToShow = barcodeShow.class;
                }else if(maCode.getTheLoai().equalsIgnoreCase("vitri")){
                    classToShow = vitriShowCode.class;
                }else{
                    classToShow = vanBanShow.class;
                }
                Intent intent = new Intent(Screen_lichSuQuet.this, classToShow);
                Bundle bundle = new Bundle();
                bundle.putString("noiDung", maCode.getMaCode());
                bundle.putString("theLoai", maCode.getTheLoai());
                bundle.putString("thoiGian", maCode.getNgayThang());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadListView();
    }

    private void reloadListView(){
        maCodeList = sqLite.getDanhSachQuet();
        maCodeAdapter = new maCodeAdapter(maCodeList);
        listViewQuet.setAdapter(maCodeAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        if (item.getItemId() == R.id.delete){
            new AlertDialog.Builder(Screen_lichSuQuet.this).setTitle("Bạn muốn xóa tất lịch sử?")
                    .setMessage("")
                    .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sqLite.deleteQuet();
                            reloadListView();
                        }
                    })
                    .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
        if (item.getItemId()==R.id.search_view){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                maCodeList = sqLite.timKiemQuet(s);
                maCodeAdapter = new maCodeAdapter(maCodeList);
                listViewQuet.setAdapter(maCodeAdapter);
                return false;
            }
        });
        return true;
    }
}