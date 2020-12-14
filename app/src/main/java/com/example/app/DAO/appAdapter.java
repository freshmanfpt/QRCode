package com.example.app.DAO;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.app.R;

import java.util.List;

public class appAdapter extends BaseAdapter {
    private List<ApplicationInfo> appList = null;
    private Context  context;
    private PackageManager packageManager;
    public appAdapter(Context context,List<ApplicationInfo> applicationInfos){
        this.context = context;
    this.appList = applicationInfos;
    packageManager = context.getPackageManager();}
    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position , View view , ViewGroup parent) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_app,parent,false);
        TextView appName = view.findViewById(R.id.app_name);
        TextView packageName = view.findViewById(R.id.app_package);
        ImageView iconView = view.findViewById(R.id.app_icon);
        ApplicationInfo data = appList.get(position);
        appName.setText(data.loadLabel(packageManager));
        packageName.setText(data.packageName);
        iconView.setImageDrawable(data.loadIcon(packageManager));
        return view;
    }
}
