package com.example.helmet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class PrivacyAdapter extends RecyclerView.Adapter<PrivacyAdapter.ViewHolder> {
    private List<Privacy> Data;

    public PrivacyAdapter(List<Privacy> data){
        Data = data;
    }
    @NonNull
    @Override
    public PrivacyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_privacy, parent,false);
        PrivacyAdapter.ViewHolder viewHolder = new PrivacyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PrivacyAdapter.ViewHolder holder, int position){
        holder.privacy_name.setText(Data.get(position).getName());
        holder.privacy_rrn.setText(Data.get(position).getRrn());
        holder.privacy_phone.setText(Data.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView privacy_name, privacy_rrn, privacy_phone;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            privacy_name = itemView.findViewById(R.id.privacy_name);
            privacy_rrn = itemView.findViewById(R.id.privacy_rrn);
            privacy_phone = itemView.findViewById(R.id.privacy_phone);
        }
    }
}
