package com.example.app.Screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.app.DAO.maCode;
import com.example.app.R;
import com.example.app.SQL.SQLite;
import com.example.app.ShowCode.barcodeShow;
import com.example.app.ShowCode.vanBanShow;
import com.example.app.ShowCode.webXemMa;
import com.example.app.ShowCode.wifiShowCode;
import com.google.zxing.BarcodeFormat;
import com.example.app.ShowCode.webShowCode;
import com.google.zxing.Result;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class fragment_quet extends Fragment {

    private CodeScanner mCodeScanner;
    private static final int REQUEST_PERMISSION_CODE = 10;
    private SQLite sqLite;
    CodeScannerView scannerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public fragment_quet() {}

    public static fragment_quet newInstance(String param1, String param2) {
        fragment_quet fragment = new fragment_quet();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sqLite = new SQLite(this.getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT>=23 &&
                this.getActivity().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            String[] permission = {Manifest.permission.CAMERA};
            requestPermissions(permission, REQUEST_PERMISSION_CODE);
        }else{
            resumePreview();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quet, container, false);
        scannerView = view.findViewById(R.id.view_scanner);
        mCodeScanner = new CodeScanner(fragment_quet.this.getActivity(), scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                fragment_quet.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(fragment_quet.this.getActivity(), result.getText(),Toast.LENGTH_LONG).show();

                        String theloai;
                        Class classToShow;

                        if (!(result.getBarcodeFormat().equals(BarcodeFormat.AZTEC) ||
                                result.getBarcodeFormat().equals(BarcodeFormat.QR_CODE) ||
                                result.getBarcodeFormat().equals(BarcodeFormat.DATA_MATRIX) ||
                                result.getBarcodeFormat().equals(BarcodeFormat.PDF_417))){
                            theloai = "barcode";
                            classToShow = barcodeShow.class;
                        }else if (result.getText().startsWith("WIFI:")){
                            theloai = "wifi";
                            classToShow = wifiShowCode.class;
                        }else if(result.getText().startsWith("http")){
                            theloai = "web";
                            classToShow = webXemMa.class;
                        }else{
                            theloai = "vanban";
                            classToShow = vanBanShow.class;
                        }

                        maCode maCode = new maCode(result.getText(), theloai, getCurrentTime(), "quet");

                        sqLite.addQRcode(maCode);


                        Intent intent = new Intent(fragment_quet.this.getContext(), webShowCode.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("noiDung", result.getText());
                        bundle.putString("theLoai", "");
                        bundle.putString("thoiGian", getCurrentTime());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        });
        if (Build.VERSION.SDK_INT>=23 &&
        this.getActivity().checkSelfPermission(Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED){
            String[] permission = {Manifest.permission.CAMERA};
            requestPermissions(permission, REQUEST_PERMISSION_CODE);
        }else{
            resumePreview();
        }
        return view;
    }

    private String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    public void resumePreview(){
        mCodeScanner.startPreview();
    }

}