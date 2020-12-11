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
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.DAO.maCode;
import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.example.app.ShowCode.webXemMa;

import static com.example.app.MainActivity.getCurrentTime;

public class taoQRVanBan extends AppCompatActivity {

    private EditText txtNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_q_r_van_ban);
        txtNoiDung = findViewById(R.id.editTextTextPersonName);

        Toolbar toolbar = findViewById(R.id.toolbar_taoQRvanBan);
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
        if (item.getItemId()==R.id.check){
            Toast.makeText(this, "1.0.0", Toast.LENGTH_SHORT).show();

            String noiDung = txtNoiDung.getText().toString();

            Intent intent = new Intent(this, webXemMa.class);
            Bundle bundle = new Bundle();
            bundle.putString("noiDung", noiDung);
            bundle.putString("theLoai", "vanBan");
            bundle.putString("thoiGian", getCurrentTime());
            intent.putExtras(bundle);
            SQLite sqLite = new SQLite(taoQRVanBan.this);
            maCode maCode = new maCode(noiDung,"vanBan",getCurrentTime(),"tao");
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