package com.example.helmet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.helmet.databinding.ActivityListBinding;
import com.example.helmet.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding;
    List<Privacy> privacyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Appdatabase db = Room.databaseBuilder(getApplicationContext(), Appdatabase.class, "privacy-db")
                .allowMainThreadQueries() //입출력 가능하게
                .build();

        privacyData = db.privacyDao().getAll();

        RecyclerView privacy_list = binding.privacyList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        privacy_list.addItemDecoration(dividerItemDecoration);
        privacy_list.setLayoutManager(linearLayoutManager);

        PrivacyAdapter privacyAdapter = new PrivacyAdapter(privacyData, false);

        privacyAdapter.notifyDataSetChanged();
        privacy_list.setAdapter(privacyAdapter);

        binding.addListButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(intent);
        });
        binding.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            startActivity(intent);
        });
    }
}
