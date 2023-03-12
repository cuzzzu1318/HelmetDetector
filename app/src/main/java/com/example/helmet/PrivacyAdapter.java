package com.example.helmet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class PrivacyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static List<Privacy> Data;
    private boolean isChecked;

    public PrivacyAdapter(List<Privacy> data, boolean isChecked){
        Data = data;
        this.isChecked = isChecked;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        if(isChecked){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_privacy_checkbox, parent, false);
            ViewHolderCheckBox viewHolder = new ViewHolderCheckBox(view);
            return viewHolder;
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_privacy, parent, false);
            ViewHolderNoCheckBox viewHolder = new ViewHolderNoCheckBox(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position){
        if(isChecked){
            ViewHolderCheckBox viewholder = (ViewHolderCheckBox) holder;
            viewholder.privacy_name.setText(Data.get(position).getName());
            viewholder.privacy_rrn.setText(Data.get(position).getRrn());
            viewholder.privacy_phone.setText(Data.get(position).getPhone());
            viewholder.is_checked.setChecked(Data.get(position).isChecked());
            viewholder.is_checked.setOnCheckedChangeListener((buttonView, isChecked) -> Data.get(position).setChecked(isChecked));
        }
        else {
            ViewHolderNoCheckBox viewholder = (ViewHolderNoCheckBox) holder;
            viewholder.privacy_name.setText(Data.get(position).getName());
            viewholder.privacy_rrn.setText(Data.get(position).getRrn());
            viewholder.privacy_phone.setText(Data.get(position).getPhone());
        }
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public static class ViewHolderNoCheckBox extends RecyclerView.ViewHolder{
        private TextView privacy_name, privacy_rrn, privacy_phone;

        public ViewHolderNoCheckBox(@NonNull View itemView){
            super(itemView);

            privacy_name = itemView.findViewById(R.id.privacy_name);
            privacy_rrn = itemView.findViewById(R.id.privacy_rrn);
            privacy_phone = itemView.findViewById(R.id.privacy_phone);
        }
    }

    public static class ViewHolderCheckBox extends RecyclerView.ViewHolder{
        private TextView privacy_name, privacy_rrn, privacy_phone;
        private CheckBox is_checked;

        public ViewHolderCheckBox(@NonNull View itemView){
            super(itemView);

            privacy_name = itemView.findViewById(R.id.privacy_name);
            privacy_rrn = itemView.findViewById(R.id.privacy_rrn);
            privacy_phone = itemView.findViewById(R.id.privacy_phone);
            is_checked = itemView.findViewById((R.id.checkbox));
        }
    }

    public static List<Privacy> getCheckedData(){
        List<Privacy> checkedData = new ArrayList<>();
        for(Privacy data : Data){
            if(data.isChecked()){
                checkedData.add(data);
            }
        }
        return checkedData;
    }
}
