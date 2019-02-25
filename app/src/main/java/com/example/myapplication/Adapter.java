package com.example.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    private ArrayList<MyDataModel> mData;


    public Adapter(Main3Activity main3Activity, ArrayList<MyDataModel> data) {
        this.mData =data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        MyDataModel data;
        data=mData.get(0);
        String t1 =data.getId();
        myViewHolder.R_ID.setText(t1);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView R_ID,R_Firstname,R_Lastname,R_Group;

        public MyViewHolder(View itemView) {
            super(itemView);

            R_ID = (TextView) itemView.findViewById(R.id.t_c1);
            R_Firstname = (TextView) itemView.findViewById(R.id.t_c2);
            R_Lastname = (TextView) itemView.findViewById(R.id.t_c3);
            R_Group = (TextView) itemView.findViewById(R.id.t_c4);
        }
    }

}