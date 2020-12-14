package com.example.app.CodeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app.DAO.appAdapter;
import com.example.app.DAO.maCode;
import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.example.app.ShowCode.webXemMa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.app.MainActivity.getCurrentTime;

public class taoQRApp extends AppCompatActivity {
    private PackageManager packageManager;
    private List<ApplicationInfo> appList;
    private appAdapter appAdapter;
    ListView listView;
    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_q_r_app);
        Toolbar toolbar = findViewById(R.id.toolbar_taoQRapp);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        packageManager = getPackageManager();
        appList = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
        appAdapter = new appAdapter(taoQRApp.this,appList);
        listView = findViewById(R.id.lv_appList);
        listView.setAdapter(appAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ApplicationInfo app = appList.get(i);
                Intent intent = new Intent(taoQRApp.this, webXemMa.class);
                Bundle bundle = new Bundle();
                String noiDung = "https://play.google.com/store/apps/details?id="+app.packageName;
                bundle.putString("noiDung", noiDung);
                bundle.putString("theLoai", "app");
                bundle.putString("thoiGian", getCurrentTime());
                intent.putExtras(bundle);
                SQLite sqLite = new SQLite(taoQRApp.this);
                maCode maCode = new maCode(noiDung,"app",getCurrentTime(),"tao");
                sqLite.addQRcode(maCode);
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
    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list){
        List<ApplicationInfo> applicationInfos = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list){
            applicationInfos.add(info);
        }
        return applicationInfos;
    }
}