package com.example.helmet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.helmet.databinding.ActivityEditBinding;
import com.example.helmet.databinding.ActivityListBinding;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;

    List<Privacy> privacyData;
    List<Privacy> checkedData;
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

        PrivacyAdapter privacyAdapter = new PrivacyAdapter(privacyData, true);

        privacyAdapter.notifyDataSetChanged();
        privacy_list.setAdapter(privacyAdapter);

        binding.deleteButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("삭제하시겠습니까?");

            builder.setPositiveButton("확인", (dialog, which) -> {
                List<Privacy> checkedData = PrivacyAdapter.getCheckedData();
                db.privacyDao().delete(checkedData);
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            });

            builder.setNegativeButton("취소", (dialog, which) -> {
                // 취소 버튼을 클릭했을 때 처리할 내용
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
        binding.editEditButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            startActivity(intent);
        });
    }
}