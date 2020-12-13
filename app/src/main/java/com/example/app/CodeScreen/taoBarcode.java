package com.example.app.CodeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.app.DAO.maCode;
import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.example.app.ShowCode.webXemMa;

import static com.example.app.MainActivity.getCurrentTime;

public class taoBarcode extends AppCompatActivity {

    private EditText txtNoiDung;
    private String barcodeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_barcode);
        Toolbar toolbar = findViewById(R.id.toolbar_barcode);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //GetTitle and hint
        txtNoiDung = findViewById(R.id.edt_taobarCode);
        SharedPreferences name = getSharedPreferences("barcodename",MODE_PRIVATE);
        SharedPreferences hint = getSharedPreferences("barcodenhint",MODE_PRIVATE);

        barcodeName = name.getString("barcodename",null);
        String barcodehint = hint.getString("barcodehint",null);
        getSupportActionBar().setTitle(barcodehint);
        txtNoiDung.setHint(barcodeName);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        if (item.getItemId() == R.id.check){
            String noiDung = txtNoiDung.getText().toString();

            Intent intent = new Intent(this, webXemMa.class);
            Bundle bundle = new Bundle();
            bundle.putString("noiDung", noiDung);
            bundle.putString("theLoai", "barcode/"+barcodeName);
            bundle.putString("thoiGian", getCurrentTime());
            intent.putExtras(bundle);
            SQLite sqLite = new SQLite(taoBarcode.this);
            maCode maCode = new maCode(noiDung,"barcode",getCurrentTime(),"tao");
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