package com.example.uas_3_sqlite;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ViewStudents extends AppCompatActivity {
    // Creating variables for our array list, dbhandler, adapter, and recycler view.
    private ArrayList<StudentModal> studentModalArrayList;
    private DBHandler dbHandler;
    private StudentRVAdapter studentRVAdapter;
    private RecyclerView studentsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        studentModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewStudents.this);

        studentModalArrayList = dbHandler.readStudents();

        studentRVAdapter = new StudentRVAdapter(studentModalArrayList, ViewStudents.this);
        studentsRV = findViewById(R.id.idRVStudents);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewStudents.this, RecyclerView.VERTICAL, false);
        studentsRV.setLayoutManager(linearLayoutManager);

        studentsRV.setAdapter(studentRVAdapter);
    }
}