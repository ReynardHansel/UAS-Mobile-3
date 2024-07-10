package com.example.uas_3_sqlite;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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

        // Read students data from the database
        studentModalArrayList = dbHandler.readStudents();

        studentRVAdapter = new StudentRVAdapter(studentModalArrayList, ViewStudents.this, new StudentRVAdapter.OnStudentClickListener() {
            @Override
            public void onStudentClick(int position) {
                // Get the clicked student
                StudentModal clickedStudent = studentModalArrayList.get(position);

                new AlertDialog.Builder(ViewStudents.this)
                    .setTitle("Delete Student")
                    .setMessage("Are you sure you want to delete this student?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Delete student from the database
                            dbHandler.deleteStudent(clickedStudent.getId());
                            
                            // Reload student list abis delete
                            studentModalArrayList.clear();
                            studentModalArrayList.addAll(dbHandler.readStudents());
                            studentRVAdapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            }
        });

        studentsRV = findViewById(R.id.idRVStudents);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewStudents.this, RecyclerView.VERTICAL, false);
        studentsRV.setLayoutManager(linearLayoutManager);

        studentsRV.setAdapter(studentRVAdapter);
    }
}