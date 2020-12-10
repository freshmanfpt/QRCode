package com.example.app.ShowCode;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.R;

public class webShowCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_show_code);

        Toolbar toolbar = findViewById(R.id.toolbar_webShow);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        String textName = bundle.getString("noiDung");
        String textName1 = bundle.getString("theLoai");
        String textName2 = bundle.getString("thoiGian");


        TextView textView = findViewById(R.id.tv_webnoidung_kq);
        TextView textView1 = findViewById(R.id.tv_webtheloai_kq);
        TextView textView2 = findViewById(R.id.tv_webtg_kq);

        textView.setText(textName);
        textView1.setText(textName1);
        textView2.setText(textName2);

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
        getMenuInflater().inflate(R.menu.menu3,menu);
        return true;
    }
    public void goToXemMa(View view){
        Bundle bundle = getIntent().getExtras();
        String textName = bundle.getString("noiDung");
        String textName1 = bundle.getString("theLoai");
        String textName2 = bundle.getString("thoiGian");

        Intent intent = new Intent(webShowCode.this, webXemMa.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("noiDung", textName);
        bundle1.putString("theLoai", textName1);
        bundle1.putString("thoiGian", textName2);
        intent.putExtras(bundle1);
        startActivity(intent);
    }
}