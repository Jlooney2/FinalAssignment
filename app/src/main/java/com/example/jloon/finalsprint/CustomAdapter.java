package com.example.jloon.finalsprint;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<GeoLocation> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewSummary;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            this.textViewSummary = (TextView) itemView.findViewById(R.id.textViewSummary);
        }
    }

    public CustomAdapter(ArrayList<GeoLocation> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewTitle = holder.textViewTitle;
        TextView textViewSummary = holder.textViewSummary;

        textViewTitle.setText(dataSet.get(listPosition).getTitle());
        textViewSummary.setText(dataSet.get(listPosition).getSummary());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
