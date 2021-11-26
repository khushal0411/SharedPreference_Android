package com.example.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
 RecyclerView recyclerView;
 ArrayList<ListItems> listItems=new ArrayList<>();
 RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        loadData();
        recyclerView=view.findViewById(R.id.rv_homefrag);

        recyclerViewAdapter= new RecyclerViewAdapter(listItems);
        recyclerViewAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                listItems.remove(viewHolder.getAdapterPosition());

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MYPREF,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Gson gson = new Gson();
                    String json = gson.toJson(listItems);
                    editor.putString(Constants.STUDENTS, json);
                    editor.apply();
                    Toast.makeText(getContext(), "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
                recyclerViewAdapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        //ListItems listitems=new  ListItems("Khushal Goyal","CSE","19124021");
        //listItems.add(listitems);

        return view;
    }

   public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MYPREF,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Constants.STUDENTS, null);
        Type type = new TypeToken<ArrayList<ListItems>>() {}.getType();
        listItems = gson.fromJson(json, type);
        if (listItems == null) {
            Toast.makeText(getContext(),"No data in storage",Toast.LENGTH_SHORT).show();
            listItems = new ArrayList<>();

        }
    }

}