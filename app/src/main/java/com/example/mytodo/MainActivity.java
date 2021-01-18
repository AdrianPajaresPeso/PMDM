package com.example.mytodo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodo.DDBB.AppDatabase;
import com.example.mytodo.DDBB.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private List<Task> taskList;
    private TaskAdapter mAdapter;
    private AppDatabase db;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {//To Do
            String newTask = data.getStringExtra("task");
            String newDesc = data.getStringExtra("desc");

            Task task = new Task(newTask, newDesc, false);
            db.taskDao().insert(task);
            taskList.add(task);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chargeVars();
    }

    private void chargeVars() {
        createFloatingButton();
        getRecyclerViewFromView();

        //charge database instance
        db = AppDatabase.getInstance(this);
        taskList = db.taskDao().getAll();
        mAdapter = new TaskAdapter(MainActivity.this, taskList);


        recyclerView.setAdapter(mAdapter);
    }

    private void getRecyclerViewFromView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createFloatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_delete_all:
                clearDB();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearDB() {
        db.taskDao().deleteAll();
        taskList.clear();
        mAdapter.notifyDataSetChanged();
    }
}