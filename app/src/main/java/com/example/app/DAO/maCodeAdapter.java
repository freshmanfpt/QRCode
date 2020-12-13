package com.example.app.DAO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.R;

import java.util.List;

public class maCodeAdapter extends BaseAdapter {
    List<maCode> maCodeList;
    ImageView imageView_hinhAnh;
    TextView tv_noiDungCode;
    TextView tv_theLoaiCode;
    TextView tv_ngayThangCode;

    public maCodeAdapter(List<maCode> maCodes){
        this.maCodeList = maCodes;
    }
    @Override
    public int getCount() {
        return maCodeList.size();
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell,parent,false);
        tv_noiDungCode = convertView.findViewById(R.id.tv_noiDung);
        tv_theLoaiCode = convertView.findViewById(R.id.tv_theLoai);
        tv_ngayThangCode = convertView.findViewById(R.id.tv_ngayThang);
        imageView_hinhAnh =convertView.findViewById(R.id.img_anhTheLoai);
        maCode maCode =maCodeList.get(position);

        convertView.setTag(maCode);

        tv_noiDungCode.setText(maCode.getMaCode());
        tv_theLoaiCode.setText(maCode.getTheLoai());
        tv_ngayThangCode.setText(maCode.getNgayThang());

        if(maCode.getTheLoai().equalsIgnoreCase("barcode")){
            imageView_hinhAnh.setImageResource(R.drawable.barcode1);
        }else if (maCode.getTheLoai().equalsIgnoreCase("viTri")){
            imageView_hinhAnh.setImageResource(R.drawable.ic_baseline_location_on_24);
        }else if (maCode.getTheLoai().equalsIgnoreCase("web")){
            imageView_hinhAnh.setImageResource(R.drawable.ic_baseline_web_24);
        }else if (maCode.getTheLoai().equalsIgnoreCase("vanban")){
            imageView_hinhAnh.setImageResource(R.drawable.ic_baseline_web_24);
        }else {
            imageView_hinhAnh.setImageResource(R.drawable.ic_baseline_qr_code_24);
        }
        return convertView;
    }
}
