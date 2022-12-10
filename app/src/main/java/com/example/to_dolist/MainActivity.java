package com.example.to_dolist;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
  private HashMap<Integer, Task> tasks = new HashMap<>();

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
//    Log.i("TaskBox ID?",String.valueOf(taskBox.getId()));
    EditText editableText = taskBox.findViewById(R.id.editTextBox2);
    editableText.setOnFocusChangeListener(textBoxChangeListener);

    tasks.put(taskBox.getId(),new Task(""));
//    tasks.add(taskBox.getId(),new Task(""));//, taskBox.findViewById(R.id.nameOfTask)));
//    taskBox.requestFocus();
    list.addView(taskBox);
  }

  //TODO: change tasks(0) to get taskbox that is touched so we can update arraylist
  private View.OnFocusChangeListener textBoxChangeListener = new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
      if(hasFocus){
        RelativeLayout taskBox = v.findViewById(R.id.relativeLayoutBox);
        Log.i("TaskBox ID FOCUS CHANGE",String.valueOf(taskBox.getId()));
        Task taskTest = tasks.get(taskBox.getId());
//        tasks.get(0).changeTask(text.getText().toString());
        Log.i("Task at ?",taskTest.getTaskName());
      }
    }
  };

  private View.OnTouchListener textBoxTouchListener = new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
      if(event.getAction() == MotionEvent.ACTION_DOWN){
        v.performClick();


        return true;
      }
      return false;
    }
  };

}