package com.example.app.Screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.SubScreen.Help;
import com.example.app.SubScreen.License;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmenet_caidat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmenet_caidat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SwitchCompat tggShowWeb;
    private SwitchCompat tggLuuQuet;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmenet_caidat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmenet_caidat.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmenet_caidat newInstance(String param1, String param2) {
        fragmenet_caidat fragment = new fragmenet_caidat();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmenet_caidat, container, false);
        tggLuuQuet = view.findViewById(R.id.tgg_luuQuet);
        tggShowWeb = view.findViewById(R.id.tgg_showWeb);
        SharedPreferences showWeb = fragmenet_caidat.this.getActivity().getSharedPreferences("showWeb", Context.MODE_PRIVATE);
        SharedPreferences luuQuet = fragmenet_caidat.this.getActivity().getSharedPreferences("luuQuet", Context.MODE_PRIVATE);
        tggShowWeb.setChecked(showWeb.getBoolean("showWeb", false));
        tggLuuQuet.setChecked(luuQuet.getBoolean("luuQuet", true));
        tggShowWeb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences showWeb = fragmenet_caidat.this.getActivity().getSharedPreferences("showWeb", MODE_PRIVATE);
                SharedPreferences.Editor editor = showWeb.edit();
                editor.putBoolean("showWeb", isChecked);
                editor.apply();
            }
        });
        tggLuuQuet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences luuQuet = fragmenet_caidat.this.getActivity().getSharedPreferences("luuQuet", MODE_PRIVATE);
                SharedPreferences.Editor editor = luuQuet.edit();
                editor.putBoolean("luuQuet", isChecked);
                editor.apply();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.st_help);
        Button button1 = view.findViewById(R.id.st_gioithieu);
        Button button2 = view.findViewById(R.id.st_giayphep);
        Button button3 = view.findViewById(R.id.st_app);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Help.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getActivity().getSharedPreferences("prefs",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstStart",true);
                editor.apply();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), License.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "1.0.0", Toast.LENGTH_SHORT).show();
            }
        });
    }
}