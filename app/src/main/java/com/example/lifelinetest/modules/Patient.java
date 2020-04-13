package com.example.lifelinetest.modules;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Patient {

    public String id;
    public String name;
    public String wardNumber;
    public int age;
    public String bloodType;
    public String deviceId;

    public Patient() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Patient(String Id, String name, String wardNumber, int age, String bloodType, String deviceId) {
        this.id = Id;
        this.name = name;
        this.wardNumber = wardNumber;
        this.age = age;
        this.bloodType = bloodType;
        this.deviceId = deviceId;
    }



}
