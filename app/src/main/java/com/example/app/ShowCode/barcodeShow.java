package com.example.app.ShowCode;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.R;
import com.example.app.SQL.SQLite;

public class barcodeShow extends AppCompatActivity {

    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_show);
        Toolbar toolbar = findViewById(R.id.toolbar_barcodeShow);
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
        content = bundle.getString("noiDung");

        TextView textView = findViewById(R.id.textView2);
        TextView textView1 = findViewById(R.id.textView4);
        TextView textView2 = findViewById(R.id.textView5);

        textView.setText(textName);
        textView1.setText(textName1);
        textView2.setText(textName2);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }else if (item.getItemId()==R.id.share){
            Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,content);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        }else if(item.getItemId() == R.id.delete){
            SQLite sqlite = new SQLite(barcodeShow.this);
            sqlite.deletemaCode(content);
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3,menu);
        return true;
    }
    public void goToXemMa(View view){
        Intent intent = new Intent(getApplicationContext(),webXemMa.class);
        Bundle bundle = getIntent().getExtras();
        String textName = bundle.getString("noiDung");
        String textName1 = bundle.getString("theLoai");
        String textName2 = bundle.getString("thoiGian");

        Bundle bundle1 = new Bundle();
        bundle1.putString("noiDung", textName);
        bundle1.putString("theLoai", textName1);
        bundle1.putString("thoiGian", textName2);
        intent.putExtras(bundle1);
        startActivity(intent);
    }
    public void timKiemWeb(View view){
        try {
            Bundle bundle = getIntent().getExtras();
            String noiDung = bundle.getString("noiDung");
            Uri uri = Uri.parse("https://www.google.com/search?q="+noiDung+"&cad=h");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }
}