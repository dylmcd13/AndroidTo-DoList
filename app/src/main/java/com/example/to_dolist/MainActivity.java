package com.example.to_dolist;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.HashMap;

/**
 * Main Activity Class
 * TODO: Make check box listener
 * TODO: Assign IDs to each text box (use View.generateViewId() probably)
 */
public class MainActivity extends AppCompatActivity {

  /**
   * ArrayList for holding tasks
   */
  private final HashMap<Integer, Task> tasks = new HashMap<>();

  /** Button for adding tasks */
  private Button addBtn;


  /** Layout for list */
  LinearLayout list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.initialize();
//    File file = new File(this.getFilesDir(), "tasks");
//    file.
    String[] files = this.fileList();
//    Log.i("test",Arrays.toString(files));
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
    RelativeLayout taskBox = (RelativeLayout) getLayoutInflater().inflate(R.layout.edit_text,null);
    taskBox.setId(View.generateViewId());
    EditText editableText = taskBox.findViewById(R.id.editTextBox2);
    editableText.setOnFocusChangeListener(textBoxChangeListener);
    editableText.setId(View.generateViewId());
    tasks.put(editableText.getId(),new Task(""));

    Button deleteBtn = taskBox.findViewById(R.id.deleteBtn);
    deleteBtn.setId(View.generateViewId());
    deleteBtn.setOnClickListener(deleteListener);
    list.addView(taskBox);
  }

  //TODO: change tasks(0) to get taskbox that is touched so we can update arraylist
  private final View.OnFocusChangeListener textBoxChangeListener = (v, hasFocus) -> {
    if(hasFocus){
      try{
        EditText textBoxTest = findViewById(v.getId());
        Task taskTest = tasks.get(v.getId());
        taskTest.changeTask(textBoxTest.getText().toString());
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
      //TODO: get text info and send to delete activity
      Intent deleteActivity = new Intent(this, Delete.class);
      deleteActivity.putExtra("taskBoxID",textBox.getId());
      deleteActivity.putExtra("nameOfTask",textBox.getText().toString());
      this.startActivity(deleteActivity);
    }catch(NullPointerException e){
      Log.e("ERROR",e.toString());
    }
  };

}