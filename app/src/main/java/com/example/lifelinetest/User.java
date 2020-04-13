package com.example.lifelinetest;

public class User {
    public String Id2,Name2,Ward2,Age2,Blood2,DeviceId2,Gender2;
    public User()
    {

    }
    public User(String Id2,String Name2, String Gender2,String Age2, String Ward2, String Blood2, String DeviceId2) {
        this.Id2 = Id2;
        this.Name2 = Name2;
        this.Gender2 = Gender2;
        this.Age2 = Age2;
        this.Ward2 = Ward2;
        this.Blood2 = Blood2;
        this.DeviceId2 = DeviceId2;
    }

    public String getId2() {
        return Id2;
    }

    public String getName2() {
        return Name2;
    }

    public String getGender2() {
        return Gender2;
    }

    public String getAge2() {
        return Age2;
    }

    public String getWard2() {
        return Ward2;
    }

    public String getBlood2() {
        return Blood2;
    }
    public String getDeviceId2() {
        return DeviceId2;
    }
}