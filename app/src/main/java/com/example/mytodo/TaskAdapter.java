package com.example.mytodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodo.DDBB.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> taskList;
    private LayoutInflater mInflater;


    public TaskAdapter(Context mCtx, List<Task> taskList) {
        this.taskList = taskList;
        mInflater = LayoutInflater.from(mCtx);}

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_tasks,parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task t = taskList.get(position);
        holder.textViewTask.setText(t.getmTask());
        holder.textViewDesc.setText(t.getmDesc());
        if (t.getmFinished()){
            holder.textViewStatus.setText("Completed");
        }else{
            holder.textViewStatus.setText("Not Completed");
        }

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewStatus;
        private TextView textViewTask;
        private TextView textViewDesc;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
        }
    }
}
