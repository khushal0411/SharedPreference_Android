package com.example.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<ListItems> listItems;
    public  static class MyViewHolder extends RecyclerView.ViewHolder {
     RelativeLayout relativeLayout;
     TextView name,stream,id;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            relativeLayout= itemView.findViewById(R.id.rl_list);
            name=itemView.findViewById(R.id.tv_name);
            stream=itemView.findViewById(R.id.tv_stream);
            id=itemView.findViewById(R.id.tv_id);
        }
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list,parent,false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
      ListItems list = listItems.get(position);
     holder.name.setText(list.getName());
     holder.stream.setText(list.getStream());
     holder.id.setText(list.getId());
     holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Bundle bundle = new Bundle();
             bundle.putString(Constants.ID, list.getId());
             bundle.putString(Constants.STREAM, list.getStream());
             bundle.putString(Constants.NAME, list.getName());
             bundle.putString("position",Integer.toString(position));
             UpdateDataFragment myFrag = new UpdateDataFragment();
             myFrag.setArguments(bundle);
             AppCompatActivity activity = (AppCompatActivity) v.getContext();
             FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
             transaction.replace(R.id.frame, myFrag);
             transaction.commit();

             Toast.makeText(v.getContext(), "You Have selected student "+list.getName(),Toast.LENGTH_SHORT).show();
         }
     });

    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }
    public RecyclerViewAdapter(ArrayList<ListItems> listItems)
    {
        this.listItems=listItems;
    }


}
