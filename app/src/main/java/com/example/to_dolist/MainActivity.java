package com.example.to_dolist;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main Activity Class
 * TODO: Make check box listener
 */
public class MainActivity extends AppCompatActivity {

  /**
   * HashMap for holding tasks
   */
  private final ArrayList<TaskBoxLayout> tasks = new ArrayList<>();

  /** Button for adding tasks */
  private Button addBtn;


  /** Layout for list */
  LinearLayout list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.initialize();
  }

  /**
   * Initializes known View Components at compile time and sets parameters for the text and list borders
   */
  private void initialize(){
    list = findViewById(R.id.addingTest);
    addBtn = findViewById(R.id.addTaskBtn);
    addBtn.setOnClickListener(addingTaskBox);
  }

  /**
   * When button is clicked, add checkbox, text and a border below the text to differentiate between tasks
   */
  private final View.OnClickListener addingTaskBox = v -> {
    addTask();
    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(v.getRootView(),InputMethodManager.SHOW_IMPLICIT);
  };

  private void addTask(){
    try {
      Log.i("Linear Layout in MAIN?",list.toString());
      TaskBoxLayout task = new TaskBoxLayout(this,tasks.size());
      tasks.add(task);
      list.addView(task.getTaskBox());
    }catch(IllegalStateException e){
      Log.e("ILLEGAL STATE",e.toString());
    }
  }

  private final View.OnFocusChangeListener textBoxChangeListener = (v, hasFocus) -> {
    if(hasFocus){
      try{
        EditText textBoxTest = findViewById(v.getId());
        //TODO: make this update task obj in array
      }catch(NullPointerException e){
        Log.e("NULL",e.toString());
      }
    }
  };

  /**
   * Listener for deleting tasks
   *
   * Grabs TextBox layout and retrieves textBox info from the child
   */
  private View.OnClickListener deleteListener = v -> {
    try{
      RelativeLayout rel = (RelativeLayout) v.getParent();
      EditText textBox = (EditText) rel.getChildAt(1); //EditText is always at index 1?
      Intent deleteActivity = new Intent(this, Delete.class);
      deleteActivity.putExtra("taskBoxID",textBox.getId());
      deleteActivity.putExtra("tasks",tasks);
      deleteActivity.putExtra("nameOfTask",textBox.getText().toString());
      this.startActivity(deleteActivity);
    }catch(NullPointerException e){
      Log.e("ERROR",e.toString());
    }
  };

}