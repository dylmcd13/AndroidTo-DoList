package com.example.to_dolist;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;

/**
 * Main Activity Class
 * TODO: Make check box listener
 */
public class MainActivity extends AppCompatActivity implements Serializable {

  /**
   * HashMap for holding tasks
   */
  private HashMap<Integer,Task> taskHashMap = new HashMap<>();

  /** Button for adding tasks */
  private Button addBtn;

  public static String tasks="";

  /** Layout for list */
  LinearLayout list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.i("ONCREATE CALLED","");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.initialize();
    ImageButton home_button = (ImageButton) findViewById(R.id.home_button);
    home_button.setOnClickListener(homeListener);

    ImageButton completed_tasks_button = (ImageButton) findViewById(R.id.tasks_button);
    completed_tasks_button.setOnClickListener(taskButtonListener);
  }

  private View.OnClickListener homeListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      startActivity(new Intent(MainActivity.this, MainActivity.class));
      overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
  };

  private View.OnClickListener taskButtonListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent completeActivity = new Intent(MainActivity.this, CompletedTasks.class);
      completeActivity.setAction(Intent.ACTION_SEND);
      Log.i("tasks to complete:", tasks);
      completeActivity.putExtra("taskComplete",tasks);
      startActivity(completeActivity);
      overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
  };


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
      RelativeLayout taskBox = (RelativeLayout) getLayoutInflater().inflate(R.layout.edit_text, null);
      taskBox.setId(View.generateViewId());
      Button deleteBtn = taskBox.findViewById(R.id.deleteBtn);
      CheckBox complete = taskBox.findViewById(R.id.checkBox);
      complete.setOnClickListener(completedListener);
      EditText textBox = taskBox.findViewById(R.id.editTextBox2);
      textBox.addTextChangedListener(textWatcher);
      textBox.requestFocus();

      deleteBtn.setOnClickListener(deleteListener);

      taskHashMap.put(taskBox.getId(),new Task(""));
      list.addView(taskBox);
    }catch(IllegalStateException e){
      Log.e("ILLEGAL STATE",e.toString());
    }
  }

  private View.OnClickListener completedListener = v -> {
    RelativeLayout rel = (RelativeLayout) v.getParent();
    EditText textBox = (EditText) rel.getChildAt(1); //EditText is always at index 1?
    tasks += textBox.getText().toString() + "\0";
    Log.i("tasks:", tasks);
   // Intent completeActivity = new Intent(this, CompletedTasks.class);
    //completeActivity.setAction(Intent.ACTION_SEND);
    //completeActivity.putExtra("taskComplete",textBox.getText().toString());
    taskHashMap.remove(rel.getId());
    resetList();
   // startActivityForResult(completeActivity, 1);
  };


  private final TextWatcher textWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      View v = (View) getCurrentFocus().getParent();
      taskHashMap.get(v.getId()).changeTask(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
  };

  /**
   * Listener for deleting tasks
   *
   * Grabs TextBox layout and retrieves textBox info from the child
   */
  private View.OnClickListener deleteListener = v -> {


      RelativeLayout rel = (RelativeLayout) v.getParent();
      EditText textBox = (EditText) rel.getChildAt(1); //EditText is always at index 1?
      Intent deleteActivity = new Intent(this, Delete.class);
      deleteActivity.setAction(Intent.ACTION_SEND);
      deleteActivity.putExtra("taskBoxID",rel.getId());
      deleteActivity.putExtra("taskToDelete",textBox.getText().toString());
      startActivityForResult(deleteActivity, 1);
      overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

  };

  private void resetList(){
    list.removeAllViews();
    Collection c = taskHashMap.values();

    


    //mapKeySet.stream().min()
    for(Object obj : c){
   
      Task task = (Task) obj;
      HashMap<Integer, Task> copy = new HashMap<>();
      RelativeLayout taskBox = (RelativeLayout) getLayoutInflater().inflate(R.layout.edit_text, null);
      taskBox.setId(View.generateViewId());
      task.setTextBoxID(taskBox.getId());
      EditText textBox = taskBox.findViewById(R.id.editTextBox2);
      Button deleteBtn = taskBox.findViewById(R.id.deleteBtn);
      deleteBtn.setOnClickListener(deleteListener);
      textBox.setText(task.getTaskName());
      copy.put(taskBox.getId(),task);
      list.addView(taskBox);
    }

    taskHashMap = copy;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1) {
      if(resultCode == Activity.RESULT_OK){
        int taskBoxID = data.getIntExtra("taskBoxID",-1);
        taskHashMap.remove(taskBoxID);
        resetList();
      }
      if (resultCode == Activity.RESULT_CANCELED) {
        Log.i("NO RESULT","");
      }
    }
  }



}