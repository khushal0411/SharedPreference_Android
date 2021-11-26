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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddItemFragment extends Fragment {
 EditText name, stream,id;
 Button add;
    ArrayList<ListItems> listItems=new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    HomeFragment homeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_add_item, container, false);
        name=view.findViewById(R.id.et_upname);
        stream=view.findViewById(R.id.et_upstream);
        id=view.findViewById(R.id.et_upid);

        add=view.findViewById(R.id.btn_update);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              listItems.add(new ListItems(name.getText().toString(), stream.getText().toString(),id.getText().toString()));
                saveData();
                HomeFragment homepage = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction =fragmentManager.beginTransaction();
                transaction.replace(R.id.frame, homepage);
                transaction.commit();
            }
        });

        return view;
    }

    private void saveData() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MYPREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json1 = sharedPreferences.getString(Constants.STUDENTS, null);
        Type type = new TypeToken<ArrayList<ListItems>>() {}.getType();
        ArrayList olddata = gson.fromJson(json1, type);
        if(olddata==null)
        {
            String json = gson.toJson(listItems);
            editor.putString(Constants.STUDENTS, json);

            editor.apply();

            Toast.makeText(getContext(), "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();

        }
        else {
            olddata.addAll(listItems);
            String json = gson.toJson(olddata);
            editor.putString(Constants.STUDENTS, json);

            editor.apply();

            Toast.makeText(getContext(), "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();

        }


    }
}