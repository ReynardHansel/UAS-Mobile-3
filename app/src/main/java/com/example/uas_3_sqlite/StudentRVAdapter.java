package com.example.uas_3_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentRVAdapter extends RecyclerView.Adapter<StudentRVAdapter.ViewHolder> {
    private ArrayList<StudentModal> studentModalArrayList;
    private Context context;

    public StudentRVAdapter(ArrayList<StudentModal> studentModalArrayList, Context context) {
        this.studentModalArrayList = studentModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModal modal = studentModalArrayList.get(position);
        holder.studentNamaTV.setText(modal.getNama());
        holder.studentNimTV.setText(String.valueOf(modal.getNim()));
        holder.studentIpkTV.setText(String.valueOf(modal.getIpk()));
        holder.studentMatkulTV.setText(modal.getMatkul());
    }

    @Override
    public int getItemCount() {
        return studentModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView studentNamaTV, studentNimTV, studentIpkTV, studentMatkulTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNamaTV = itemView.findViewById(R.id.idTVStudentNama);
            studentNimTV = itemView.findViewById(R.id.idTVStudentNim);
            studentIpkTV = itemView.findViewById(R.id.idTVStudentIpk);
            studentMatkulTV = itemView.findViewById(R.id.idTVStudentMatkul);
        }
    }
}