package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class viewAdapter extends RecyclerView.Adapter<viewAdapter.MyViewHolder> {

    Context context;
    ArrayList id,q1,q2,q3;

    viewAdapter(Context context, ArrayList id, ArrayList q1, ArrayList q2, ArrayList q3){
        this.context = context;
        this.id = id;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.q1.setText(String.valueOf(q1.get(position)));
        holder.q2.setText(String.valueOf(q2.get(position)));
        holder.q3.setText(String.valueOf(q3.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,q1,q2,q3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            q1 = itemView.findViewById(R.id.q1);
            q2 = itemView.findViewById(R.id.q2);
            q3 = itemView.findViewById(R.id.q3);
        }
    }
}
