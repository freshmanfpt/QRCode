package com.example.app.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app.CodeScreen.taoQRApp;
import com.example.app.CodeScreen.taoQRVanBan;
import com.example.app.CodeScreen.taoQRViTri;
import com.example.app.CodeScreen.taoQRWeb;
import com.example.app.CodeScreen.taoQRWifi;
import com.example.app.DAO.maCode;
import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.example.app.ShowCode.webXemMa;

import static com.example.app.MainActivity.getCurrentTime;

public class Screen_TaoQR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen__tao_q_r);

        Toolbar toolbar = findViewById(R.id.toolbar_qr);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button button1 = findViewById(R.id.btn_taoQR1);
        Button button2 = findViewById(R.id.btn_taoQR2);
        Button button3 = findViewById(R.id.btn_taoQR3);
        Button button4 = findViewById(R.id.btn_taoQR4);
        Button button5 = findViewById(R.id.btn_taoQR5);
        findViewById(R.id.btn_taoQR6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), taoQRApp.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.getClipBoard(Screen_TaoQR.this) != null){
                    String noiDung = MainActivity.getClipBoard(Screen_TaoQR.this);

                    Intent intent = new Intent(Screen_TaoQR.this, webXemMa.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("noiDung", noiDung);
                    bundle.putString("theLoai", "vanBan");
                    bundle.putString("thoiGian", getCurrentTime());
                    intent.putExtras(bundle);
                    SQLite sqLite = new SQLite(Screen_TaoQR.this);
                    maCode maCode = new maCode(noiDung,"vanBan",getCurrentTime(),"tao");
                    sqLite.addQRcode(maCode);
                    startActivity(intent);
                }else{
//                    Intent intent = new Intent(getApplicationContext(), taoQRApp.class);
//                    startActivity(intent);
                    Toast.makeText(Screen_TaoQR.this, "Nội dung sao chép của bạn đang rỗng!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), taoQRWeb.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), taoQRWifi.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), taoQRViTri.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), taoQRVanBan.class);
                startActivity(intent);
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
    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return true;
//    }
}