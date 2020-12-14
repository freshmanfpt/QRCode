package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){

        this.context = context;

    }

    // Arrays
    public int[] slide_images = {
            R.drawable.qrscan_1,
            R.drawable.save_2,
            R.drawable.share_1
    };
    public String[] slide_heading = {
            "Quét Và Tạo Mã",
            "Danh Sách Mã",
            "Chia Sẻ Mã"
    };
    public String[] slide_desc = {
            "Quét và tạo những kiểu mã QR và Barcode một cách nhanh chóng tiện lợi",
            "Lưu trữ các thể loại mã QR và Barcode trong thiết bị android của bạn",
            "Chia sẻ những đoạn mã cho người quen qua nhiều nền tản xã hội"
    };
    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImg = view.findViewById(R.id.slide_img);
        TextView slideHead = view.findViewById(R.id.slide_heading);
        TextView slideDesc = view.findViewById(R.id.slide_desc);

        slideImg.setImageResource(slide_images[position]);
        slideHead.setText(slide_heading[position]);
        slideDesc.setText(slide_desc[position]);

        container.addView(view);
        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
