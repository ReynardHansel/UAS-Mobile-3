package com.example.uas_3_sqlite;

public class StudentModal {
    // Variables for our student data
    private String nama;
    private long nim;
    private float ipk;
    private String matkul;
    private int id;

    // Getter and setter methods
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getNim() {
        return nim;
    }

    public void setNim(long nim) {
        this.nim = nim;
    }

    public float getIpk() {
        return ipk;
    }

    public void setIpk(float ipk) {
        this.ipk = ipk;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Constructor
    public StudentModal(int id, String nama, long nim, float ipk, String matkul) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.ipk = ipk;
        this.matkul = matkul;
    }
}