package com.example.uas_3_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "studentdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String ID_COL = "id";
    private static final String NAMA_COL = "nama";
    private static final String NIM_COL = "nim";
    private static final String IPK_COL = "ipk";
    private static final String MATKUL_COL = "matkul";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAMA_COL + " TEXT,"
                + NIM_COL + " INTEGER,"
                + IPK_COL + " REAL,"
                + MATKUL_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewStudent(String nama, long nim, float ipk, String matkul) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAMA_COL, nama);
        values.put(NIM_COL, nim);
        values.put(IPK_COL, ipk);
        values.put(MATKUL_COL, matkul);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<StudentModal> readStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<StudentModal> studentModalArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(ID_COL); // Get the index of the ID column
                int namaIndex = cursor.getColumnIndex(NAMA_COL);
                int nimIndex = cursor.getColumnIndex(NIM_COL);
                int ipkIndex = cursor.getColumnIndex(IPK_COL);
                int matkulIndex = cursor.getColumnIndex(MATKUL_COL);
    
                if (idIndex != -1 && namaIndex != -1 && nimIndex != -1 && ipkIndex != -1 && matkulIndex != -1) {
                    studentModalArrayList.add(new StudentModal(
                        cursor.getInt(idIndex), // Fetch the ID
                        cursor.getString(namaIndex),
                        cursor.getLong(nimIndex),
                        cursor.getFloat(ipkIndex),
                        cursor.getString(matkulIndex)));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return studentModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}