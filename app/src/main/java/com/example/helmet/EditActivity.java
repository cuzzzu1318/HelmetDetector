package com.example.helmet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.helmet.databinding.ActivityEditBinding;
import com.example.helmet.databinding.ActivityListBinding;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;

    List<Privacy> privacyData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Appdatabase db = Room.databaseBuilder(getApplicationContext(), Appdatabase.class, "privacy-db")
                .allowMainThreadQueries() //입출력 가능하게
                .build();

        privacyData = db.privacyDao().getAll();

        RecyclerView privacy_list = binding.editList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        privacy_list.addItemDecoration(dividerItemDecoration);
        privacy_list.setLayoutManager(linearLayoutManager);

        PrivacyAdapter privacyAdapter = new PrivacyAdapter(privacyData);

        privacyAdapter.notifyDataSetChanged();
        privacy_list.setAdapter(privacyAdapter);
    }
}