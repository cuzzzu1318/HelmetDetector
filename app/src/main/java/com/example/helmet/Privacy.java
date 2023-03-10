package com.example.helmet;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Privacy {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private String rrn;



    public Privacy(String name, String phone, String rrn) {
        this.name = name;
        this.phone = phone;
        this.rrn = rrn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    @Override
    public String toString() {
        return "Privacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", rrn='" + rrn + '\'' +
                '}'+
                "\n";
    }
}
