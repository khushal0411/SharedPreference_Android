package com.example.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class UpdateDataFragment extends Fragment {

EditText name,stream,id;
Button update;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_update_data, container, false);
        name=view.findViewById(R.id.et_upname);
        stream=view.findViewById(R.id.et_upstream);
        id=view.findViewById(R.id.et_upid);
        update=view.findViewById(R.id.btn_update);
        String s=getArguments().getString(Constants.STREAM);
        String n=getArguments().getString(Constants.NAME);
        String i=getArguments().getString(Constants.ID);
        String position=getArguments().getString("position");
        name.setText(n);
        stream.setText(s);
        id.setText(i);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MYPREF, Context.MODE_PRIVATE);
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String json1 = sharedPreferences.getString(Constants.STUDENTS, null);
                Type type = new TypeToken<ArrayList<ListItems>>() {}.getType();
                ArrayList updata = gson.fromJson(json1, type);
                updata.set(Integer.parseInt(position),new ListItems(name.getText().toString(),stream.getText().toString(),id.getText().toString()));
                String json = gson.toJson(updata);
                editor.putString(Constants.STUDENTS, json);
                editor.apply();
                HomeFragment homepage = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction =fragmentManager.beginTransaction();
                transaction.replace(R.id.frame, homepage);
                transaction.commit();
            }
        });



        return view;
    }
}