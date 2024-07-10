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
    private OnStudentClickListener listener; // Dipake buat nge delete ntar

    public interface OnStudentClickListener {
        void onStudentClick(int position);
    }

    public StudentRVAdapter(ArrayList<StudentModal> studentModalArrayList, Context context, OnStudentClickListener listener) {
        this.studentModalArrayList = studentModalArrayList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_rv_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModal modal = studentModalArrayList.get(position);
        holder.studentIdTV.setText(String.valueOf(modal.getId()));
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
        private TextView studentIdTV, studentNamaTV, studentNimTV, studentIpkTV, studentMatkulTV;

        public ViewHolder(@NonNull View itemView, final OnStudentClickListener listener) {
            super(itemView);
            studentIdTV = itemView.findViewById(R.id.idTVStudentId);
            studentNamaTV = itemView.findViewById(R.id.idTVStudentNama);
            studentNimTV = itemView.findViewById(R.id.idTVStudentNim);
            studentIpkTV = itemView.findViewById(R.id.idTVStudentIpk);
            studentMatkulTV = itemView.findViewById(R.id.idTVStudentMatkul);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onStudentClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}