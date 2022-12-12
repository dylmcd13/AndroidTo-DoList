package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CompletedTasks extends AppCompatActivity {
    String taskName="";

    LinearLayout list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_tasks);
        ImageButton home_button = (ImageButton) findViewById(R.id.home_button);
        home_button.setOnClickListener(homeListener);
        Log.i("ONCREATE CALLED","");
        Intent taskIntent = getIntent();
        taskName = taskIntent.getStringExtra("taskComplete");
        String[] tasks = taskName.split("\0");
        list=findViewById(R.id.addingTest);
        add(tasks);
    }

    private View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    };

    public void add(String [] tasks) {
        try {
            for (int i = 0; i < tasks.length;i++) {
                RelativeLayout taskBox = (RelativeLayout) getLayoutInflater().inflate(R.layout.completed_task, null);
                taskBox.setId(View.generateViewId());

                CheckBox complete = taskBox.findViewById(R.id.checkBox);
                complete.setChecked(true);
                EditText textBox = taskBox.findViewById(R.id.editTextBox2);
                textBox.setText(tasks[i]);
                list.addView(taskBox);
            }
        }catch(IllegalStateException e){
            Log.e("ILLEGAL STATE",e.toString());
        }
    }
}
