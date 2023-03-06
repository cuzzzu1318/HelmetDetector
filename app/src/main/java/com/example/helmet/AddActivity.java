package com.example.helmet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helmet.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Appdatabase db = Room.databaseBuilder(getApplicationContext(), Appdatabase.class, "privacy-db")
                .allowMainThreadQueries() //입출력 가능하게
                .build();

        binding.addButton.setOnClickListener(view -> {
            if (binding.name.getText().toString().length() == 0 |
                    binding.rrn.getText().toString().length() == 0 |
                    binding.phone.getText().toString().length() == 0) {
                Toast.makeText(AddActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            } else {
                db.privacyDao().insert(new Privacy(binding.name.getText().toString(), binding.rrn.getText().toString(), binding.phone.getText().toString()));

                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);

                Toast.makeText(AddActivity.this, "추가되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
