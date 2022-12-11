package com.example.to_dolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class TaskBoxLayout implements Serializable {
  int index;

  transient Activity context;

  transient RelativeLayout taskBox;

  transient EditText textBox;

  transient Button deleteBtn;

  Task task;


  /**
   * Constructor
   * @param context Activity Context
   */
  TaskBoxLayout(Activity context,int index){
    super();
    this.context = context;
    this.index = index;
    LayoutInflater inflater = context.getLayoutInflater();
    taskBox = (RelativeLayout) inflater.inflate(R.layout.edit_text,null,true);
    task = new Task("");
    textBox = taskBox.findViewById(R.id.editTextBox2);
    deleteBtn = taskBox.findViewById(R.id.deleteBtn);

  }



  RelativeLayout getTaskBox(){
    return taskBox;
  }

  Task getTask(){
    return task;
  }

  Button getDeleteBtn(){
    return deleteBtn;
  }

  void changeTaskName(String taskName){
    task.changeTask(taskName);
  }



//  /**
//   * Listener for deleting tasks
//   *
//   * Grabs TextBox layout and retrieves textBox info from the child
//   */
//  private View.OnClickListener deleteListener = v -> {
//    try{
//      RelativeLayout rel = (RelativeLayout) v.getParent();
//      EditText textBox = (EditText) rel.getChildAt(1); //EditText is always at index 1?
//      Intent deleteActivity = new Intent(context, Delete.class);
//      deleteActivity.putExtra("indexedAt",index);
//      deleteActivity.putExtra("nameOfTask",textBox.getText().toString());
//      context.startActivity(deleteActivity);
//    }catch(NullPointerException e){
////      Log.e("ERROR",e.toString());
//      e.printStackTrace();
//    }
//  };
}