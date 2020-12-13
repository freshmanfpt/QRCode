package com.example.app.ShowCode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class webXemMa extends AppCompatActivity {
    ImageView img_hinhAnhMa;
    TextView tv_noiDung;
    TextView tv_theLoai;
    TextView tv_ngayThang;

    String textName;
    String maCode;
    private Bitmap bitmapShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_xem_ma);

        Toolbar toolbar = findViewById(R.id.toolbar_webXemMa);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        img_hinhAnhMa = findViewById(R.id.img_hinhAnhMa);
        tv_noiDung = findViewById(R.id.tv_noiDungMa);
        tv_theLoai = findViewById(R.id.tv_theLoaiMa);
        tv_ngayThang = findViewById(R.id.tv_ngayThangMa);

        Bundle bundle = getIntent().getExtras();
        String textName = bundle.getString("noiDung");
        maCode = textName;
        String textName1 = bundle.getString("theLoai");
        String textName2 = bundle.getString("thoiGian");
        String full = textName;

        MultiFormatWriter writer = new MultiFormatWriter();
        if (textName1.startsWith("barcode")){
            String typeBarCode = textName1.split("/")[1];
            BarcodeFormat barcodeFormat;
            if(typeBarCode.equals("Code_128")){
                barcodeFormat = BarcodeFormat.CODE_128;
            }else if(typeBarCode.equals("AZTEC")){
                barcodeFormat = BarcodeFormat.AZTEC;
            }else if(typeBarCode.equals("CODABAR")){
                barcodeFormat = BarcodeFormat.CODABAR;
            }else if(typeBarCode.equals("CODE 39")){
                barcodeFormat = BarcodeFormat.CODE_39;
            }else if(typeBarCode.equals("CODE 93")){
                barcodeFormat = BarcodeFormat.CODE_93;
            }else if(typeBarCode.equals("DATA MATRIX")){
                barcodeFormat = BarcodeFormat.DATA_MATRIX;
            }else if(typeBarCode.equals("EAN8")){
                barcodeFormat = BarcodeFormat.EAN_8;
            }else if(typeBarCode.equals("EAN13")){
                barcodeFormat = BarcodeFormat.EAN_13;
            }else if(typeBarCode.equals("ITF")){
                barcodeFormat = BarcodeFormat.ITF;
            }else if(typeBarCode.equals("MAXICODE")){
                barcodeFormat = BarcodeFormat.MAXICODE;
            }else if(typeBarCode.equals("PDF 417")){
                barcodeFormat = BarcodeFormat.PDF_417;
            }else if(typeBarCode.equals("RSS 14")){
                barcodeFormat = BarcodeFormat.RSS_14;
            }else if(typeBarCode.equals("RSS EXPANDED")){
                barcodeFormat = BarcodeFormat.RSS_EXPANDED;
            }else if(typeBarCode.equals("UPCA")){
                barcodeFormat = BarcodeFormat.UPC_A;
            }else if(typeBarCode.equals("UPCE")){
                barcodeFormat = BarcodeFormat.UPC_E;
            }else{
                barcodeFormat = BarcodeFormat.UPC_EAN_EXTENSION;
            }
            try {
                BitMatrix bitMatrix = writer.encode(full, barcodeFormat, 450, 150);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                bitmapShare = bitmap;
                img_hinhAnhMa.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(webXemMa.this, "Mã nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
            }
        }else{
            try {
                BitMatrix bitMatrix = writer.encode(full, BarcodeFormat.QR_CODE, 450, 450);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                img_hinhAnhMa.setImageBitmap(bitmap);
            } catch (Exception e){
                e.printStackTrace();
            }
        }


        tv_noiDung.setText(textName);
        tv_theLoai.setText(textName1);
        tv_ngayThang.setText(textName2);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }else if (item.getItemId()==R.id.share){
            Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
            String content = textName;
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,content);
            startActivity(Intent.createChooser(shareIntent, "Share via"));

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            Uri screenshotUri = Uri.parse(String.valueOf(MediaStore.Images.Media.insertImage(getContentResolver(), bitmapShare,"bitmapShare", null)));

            sharingIntent.setType("image/png");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
            startActivity(Intent.createChooser(sharingIntent, "Share image using"));
        }else if(item.getItemId() == R.id.delete){
            SQLite sqlite = new SQLite(webXemMa.this);
            sqlite.deletemaCode(maCode);
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3,menu);
        return true;
    }
}