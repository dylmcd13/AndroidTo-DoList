package com.example.to_dolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Delete extends AppCompatActivity {

  private MediaPlayer ring;

    /**
     * ID of task box in the HashMap
     */
    int taskBoxID;

    /**
     * Name of task
     */

    String taskName;

    /**
     * TextView for name of task
     */
    TextView deleteTextView;
   
   ArrayList<TaskBoxLayout> tasks;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent deleteIntent = getIntent();
        taskBoxID = deleteIntent.getIntExtra("taskBoxID",-1);
        taskName = deleteIntent.getStringExtra("taskToDelete");
        ring = MediaPlayer.create(this, R.raw.garbage);
//        ring.setOnPreparedListener();
//        ring.setOnCompletionListener();
        Log.i("taskToDelete IN DELETE",taskName);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_task);

//        Bundle arrayListBundle = deleteIntent.getBundleExtra("BUNDLE");
//        Log.i("tasks",tasks.get(indexOfTask).toString());
//        tasks = (ArrayList<TaskBoxLayout>) deleteIntent.getSerializableExtra("tasks");
        //tasks = deleteIntent.getExtra;
//        for(TaskBoxLayout taskLayout : tasks)
//          Log.i("task",taskLayout.getTask().getTaskName());
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
                            ring.start();
                            Intent list = new Intent();
                            list.putExtra("taskToDelete",taskName);
                            list.putExtra("taskBoxID",taskBoxID);
                            setResult(Activity.RESULT_OK,list);

                            Toast.makeText(Delete.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                            Log.i("TaskBoxID IN DELETE", String.valueOf(taskBoxID));
                            finish();
//                            startActivity(list);

                        }})
                    .setNegativeButton(android.R.string.no,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                            finish();
                        }
                    }).show();
        }
    };

    @Override
    protected void onStop() {
      super.onStop();
    }

    protected void onDestroy() {
      ring.release();
      super.onDestroy();
    }

    private View.OnClickListener cancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //startActivity(new Intent(Delete.this, MainActivity.class));
          finish();
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
