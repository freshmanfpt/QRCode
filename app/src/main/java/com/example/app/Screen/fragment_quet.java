package com.example.app.Screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.app.R;
import com.google.zxing.Result;

public class fragment_quet extends Fragment {

    private CodeScanner mCodeScanner;
    private static final int REQUEST_PERMISSION_CODE = 10;
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
                        resumePreview();
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

    public void resumePreview(){
        mCodeScanner.startPreview();
    }

}