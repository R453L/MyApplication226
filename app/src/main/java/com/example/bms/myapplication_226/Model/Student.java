package com.example.bms.myapplication_226.Model;

public class Student {
    String sId;
    String sName;
    String sImg;
    String sReg;

    public Student(){}

    public Student(String sId, String sName, String sImg, String sReg) {
        this.sId = sId;
        this.sName = sName;
        this.sImg = sImg;
        this.sReg = sReg;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsImg() {
        return sImg;
    }

    public void setsImg(String sImg) {
        this.sImg = sImg;
    }

    public String getsReg() {
        return sReg;
    }

    public void setsReg(String sReg) {
        this.sReg = sReg;
    }
}
