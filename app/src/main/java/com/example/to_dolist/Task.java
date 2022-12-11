package com.example.to_dolist;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintSet;

import java.io.Serializable;

/**
 * Class to hold task information
 */
public class Task implements Parcelable {
  public static final Creator<Task> CREATOR = new Creator<Task>() {
    @Override
    public Task createFromParcel(Parcel in) {
      return new Task(in);
    }

    @Override
    public Task[] newArray(int size) {
      return new Task[size];
    }
  };


  /** Name of task */
  private String taskName;

  private Integer textBoxID;

  protected Task(Parcel in) {
    taskName = in.readString();
    if (in.readByte() == 0) {
      textBoxID = null;
    } else {
      textBoxID = in.readInt();
    }
  }


  public Integer getTextBoxID() {
    return textBoxID;
  }

  public void setTextBoxID(Integer textBoxID) {
    this.textBoxID = textBoxID;
  }



  /** Constructor */
  Task(String taskName){
    this.taskName = taskName;
    //editTextView.setOnTouchListener(onTouchListener);
    //this.editTextView = editTextView;
  }

  Task(String taskName, Integer textBoxID){
    this.taskName = taskName;
    this.textBoxID = textBoxID;
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

  private View.OnTouchListener onTouchListener = (v, event) -> {
    if(event.getAction() == MotionEvent.ACTION_DOWN) {
      v.performClick();
      Log.i("Task Name",getTaskName());
      return true;
    }
    return false;
  };


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(taskName);
    dest.writeInt(textBoxID);
  }
}
