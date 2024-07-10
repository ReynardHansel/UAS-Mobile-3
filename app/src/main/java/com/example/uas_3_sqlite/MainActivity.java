package com.example.uas_3_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText namaEdt, nimEdt, ipkEdt, matkulEdt;
    private Button addBtn, readBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing all our variables.
        namaEdt = findViewById(R.id.idEdtNama);
        nimEdt = findViewById(R.id.idEdtNim);
        ipkEdt = findViewById(R.id.idEdtIpk);
        matkulEdt = findViewById(R.id.idEdtMatkul);
        addBtn = findViewById(R.id.idBtnAdd);
        readBtn = findViewById(R.id.idBtnRead);

        // Creating a new DBHandler class and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is to get data from all edit text fields.
                String nama = namaEdt.getText().toString();
                String nimString = nimEdt.getText().toString();
                String ipkString = ipkEdt.getText().toString().replace(',', '.'); // Replace commas with dots
                String matkul = matkulEdt.getText().toString();
        
                // validating if the text fields are empty or not.
                if (nama.isEmpty() || nimString.isEmpty() || ipkString.isEmpty() || matkul.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                    return;
                }
        
                try {
                    long nim = Long.parseLong(nimString);
                    float ipk = Float.parseFloat(ipkString); // This might throw NumberFormatException
        
                    // on below line we are calling a method to add new
                    // student to sqlite data and pass all our values to it.
                    dbHandler.addNewStudent(nama, nim, ipk, matkul);
        
                    // after adding the data we are displaying a toast message.
                    Toast.makeText(MainActivity.this, "Student has been added.", Toast.LENGTH_SHORT).show();
                    namaEdt.setText("");
                    nimEdt.setText("");
                    ipkEdt.setText("");
                    matkulEdt.setText("");
                } catch (NumberFormatException e) {
                    // Handle the exception if the nim or ipk is not a valid number
                    Toast.makeText(MainActivity.this, "Use '.' instead of ',' for decimals & Make sure no whitespace", Toast.LENGTH_LONG).show();
                }
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening a new activity via an intent.
                Intent i = new Intent(MainActivity.this, ViewStudents.class);
                startActivity(i);
            }
        });
    }
}