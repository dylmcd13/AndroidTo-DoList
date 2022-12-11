package com.example.to_dolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Delete extends AppCompatActivity {

    int indexOfTasks;
    String taskName;
    TextView deleteTextView;
    ArrayList<TaskBoxLayout> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent deleteIntent = getIntent();
        String task_list;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_task);
        tasks = (ArrayList<TaskBoxLayout>) getIntent().getSerializableExtra("tasks");
        indexOfTasks = deleteIntent.getIntExtra("indexedAt",-1);
        deleteTextView = findViewById(R.id.nameOfTask);
        deleteTextView.setText(taskName);

        Button del_button = (Button) findViewById(R.id.delete_button);
        del_button.setOnClickListener(deleteListener);

        Button cancel_button = (Button) findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(cancelListener);

        ImageButton home_button = (ImageButton) findViewById(R.id.home_button);
        home_button.setOnClickListener(homeListener);

        ImageButton tasks_button = (ImageButton) findViewById(R.id.tasks_button);
        tasks_button.setOnClickListener(taskListener);
    }

    private View.OnClickListener deleteListener = new View.OnClickListener() {
        Context context;
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(Delete.this)
                    .setTitle("Confirm")
                    .setMessage("Do you really want to delete this task?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            MediaPlayer ring= MediaPlayer.create(Delete.this,R.raw.garbage);
                            ring.start();
                            tasks.remove(indexOfTasks);
                            startActivity(new Intent(Delete.this, MainActivity.class));
                            Toast.makeText(Delete.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                        }})
                    .setNegativeButton(android.R.string.no,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    }).show();
        }
    };

    private View.OnClickListener cancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Delete.this, MainActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    };

    private View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Delete.this, MainActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    };

    private View.OnClickListener taskListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Delete.this, MainActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    };
}
