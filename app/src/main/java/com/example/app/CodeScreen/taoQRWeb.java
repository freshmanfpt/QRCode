package com.example.app.CodeScreen;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.DAO.maCode;
import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.example.app.ShowCode.webXemMa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class taoQRWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_q_r_web);
        Toolbar toolbar = findViewById(R.id.toolbar_taoQRweb);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        if (item.getItemId() == R.id.check){
            EditText editText = findViewById(R.id.edt_webTao);
            Intent intent = new Intent(this, webXemMa.class);
            Bundle bundle = new Bundle();
            String tvKD = editText.getText().toString();
            if (tvKD.isEmpty()){
                Toast.makeText(this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            }
            bundle.putString("noiDung", tvKD);
            bundle.putString("theLoai", "web");
            bundle.putString("thoiGian", getCurrentTime());
            intent.putExtras(bundle);
            SQLite sqLite = new SQLite(taoQRWeb.this);
            maCode maCode = new maCode(tvKD,"web",getCurrentTime(),"tao");
            sqLite.addQRcode(maCode);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }
}