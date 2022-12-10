package com.example.to_dolist;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintSet;

/**
 * Class to hold task information
 */
public class Task {

  private EditText editTextView;

  /** Name of task */
  private String taskName;

  /** Constructor */
  Task(String taskName/**, EditText editTextView*/){
    this.taskName = taskName;
    //editTextView.setOnTouchListener(onTouchListener);
    //this.editTextView = editTextView;
  }

  /**
   * Change name of task
   * @param taskName Name of task to change to
   */
  public void changeTask(String taskName){
    this.taskName = taskName;
  }

  /**
   * @return Name of Task
   */
  public String getTaskName(){
    return taskName;
  }

  public EditText getEditTextView(){
    return editTextView;
  }

  private View.OnTouchListener onTouchListener = (v, event) -> {
    if(event.getAction() == MotionEvent.ACTION_DOWN) {
      v.performClick();
      Log.i("Task Name",getTaskName());
      return true;
    }
    return false;
  };


}
