package com.example.helmet;

import androidx.appcompat.app.AppCompatActivity;
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
    List<Privacy> privacyData = new ArrayList<>();

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
        privacy_list.setLayoutManager(new LinearLayoutManager(this));

        PrivacyAdapter privacyAdapter = new PrivacyAdapter(getSampleList());
        privacy_list.setAdapter(privacyAdapter);

        binding.addListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Privacy> getSampleList(){
        ArrayList<Privacy> lstPrivacyData = new ArrayList<>();
        for (int i = 0; i< 10; i++){
            Privacy Data = new Privacy();
            Data.setName("예제이름");
            Data.setRrn("예제주민번호");
            Data.setPhone("예제전화번호");
            lstPrivacyData.add(Data);
        }
        return lstPrivacyData;
    }
}
