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
import android.net.Uri;
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

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Main Activity Class
 * TODO: Make check box listener
 */
public class MainActivity extends AppCompatActivity implements Serializable {

  /**
   * HashMap for holding tasks
   */
  private final HashMap<Integer,Task> taskHashMap = new HashMap<>();

  /** Button for adding tasks */
  private Button addBtn;

  /** Layout for list */
  LinearLayout list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.i("ONCREATE CALLED","");
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
      RelativeLayout taskBox = (RelativeLayout) getLayoutInflater().inflate(R.layout.edit_text, null);
      taskBox.setId(View.generateViewId());
      Button deleteBtn = taskBox.findViewById(R.id.deleteBtn);


      EditText textBox = taskBox.findViewById(R.id.editTextBox2);
      textBox.setOnFocusChangeListener(textBoxChangeListener);

      deleteBtn.setOnClickListener(deleteListener);

      taskHashMap.put(taskBox.getId(),new Task(""));
      list.addView(taskBox);
    }catch(IllegalStateException e){
      Log.e("ILLEGAL STATE",e.toString());
    }
  }

  private final View.OnFocusChangeListener textBoxChangeListener = (v, hasFocus) -> {
    if(hasFocus){
      try {
        RelativeLayout taskBox = (RelativeLayout) v.getParent();
        Log.i("TaskBox id", String.valueOf(taskBox.getId()));
        EditText textBox = v.findViewById(R.id.editTextBox2);
        Log.i("textBox Contents",textBox.getText().toString());
        //TODO: make this update task obj in array
          taskHashMap.get(taskBox.getId()).changeTask(textBox.getText().toString());
      }catch(NullPointerException e){
          Log.e("NULL", e.toString());
      }
    }
  };

  /**
   * Listener for deleting tasks
   *
   * Grabs TextBox layout and retrieves textBox info from the child
   */
  private View.OnClickListener deleteListener = v -> {

      Log.i("HASHMAP BEFORE DELETING", taskHashMap.toString());

      RelativeLayout rel = (RelativeLayout) v.getParent();
      EditText textBox = (EditText) rel.getChildAt(1); //EditText is always at index 1?
      Intent deleteActivity = new Intent(this, Delete.class);
      deleteActivity.setAction(Intent.ACTION_SEND);
      deleteActivity.putExtra("taskBoxID",rel.getId());
      deleteActivity.putExtra("taskToDelete",textBox.getText().toString());
      Log.i("HashMap at"+rel.getId(),taskHashMap.get(rel.getId()).getTaskName());
      startActivityForResult(deleteActivity, 1);

//      finish();
  };

  private void resetList(){
    list.removeAllViews();
    Log.i("LIST CHILD COUNT", String.valueOf(list.getChildCount()));
    Log.i("HASH MAP SIZE", String.valueOf(taskHashMap.size()));
    Collection c = taskHashMap.values();
    for(Object obj : c){
      Task task = (Task) obj;
      RelativeLayout taskBox = (RelativeLayout) getLayoutInflater().inflate(R.layout.edit_text, null);
//      taskBox.setId(task.getTextBoxID());
      EditText textBox = taskBox.findViewById(R.id.editTextBox2);
      textBox.setText(task.getTaskName());
      list.addView(taskBox);
//      Log.i("Task FROM COLLECTION",task.getTaskName());
    }
    //Task[] taskArr = new Task[c.size()];
    //c.toArray(taskArr);
//    for(Task task : taskArr){
      Log.i("TASK COLLECTION", c.toString());
//    }
//    for(Task task : taskArrayList){
//
//
////      taskBox.setId(task.getTextBoxID()); //TODO: might need to do, idk
//      EditText textBox = taskBox.findViewById(R.id.editTextBox2);
//      textBox.setText(task.getTaskName());
//      list.addView(taskBox);
//    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1) {
      if(resultCode == Activity.RESULT_OK){
        String taskToDelete = data.getStringExtra("taskToDelete");
        int taskBoxID = data.getIntExtra("taskBoxID",-1);
        Log.i("TASK TO DELETE",taskToDelete);
        if(taskBoxID != -1)
          taskHashMap.remove(taskBoxID);
//        for(int i=0;i<taskArrayList.size();i++){
//          String taskName = taskArrayList.get(i).getTaskName();
//          if(taskName.equals(taskToDelete)){
//            taskArrayList.remove(i);
//            taskHashMap.remove(taskArrayList.get(i).getTextBoxID());
//          }
//        }
        resetList();


      }
      if (resultCode == Activity.RESULT_CANCELED) {
        Log.i("NO RESULT","");
      }
    }
  }


}