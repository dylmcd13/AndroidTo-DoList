package com.example.to_dolist;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.HashMap;

/**
 * Main Activity Class
 * TODO: Make check box listener
 * TODO: Assign IDs to each text box (use View.generateViewId() probably)
 */
public class MainActivity extends AppCompatActivity {

  /**
   * HashMap for holding tasks
   * Integer is ID number of task (prolly when making tasks with addBtn)
   * Task is task object itself
   */
  private HashMap<Integer, Task> taskHashMap;

  /** Button for adding tasks */
  private Button addBtn;

  /** Parameters for text so the layout wraps the content and so the text is not all the way to left */
  RelativeLayout.LayoutParams textParams;

  /** Parameters for list to have list border match parent size and have enough height to be visible */
  RelativeLayout.LayoutParams listParams;


  RelativeLayout.LayoutParams checkBoxParams;

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
    listParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10);
    textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    textParams.leftMargin = 100;
    checkBoxParams = new RelativeLayout.LayoutParams(30,30);
    addBtn.setOnClickListener(addingTaskBox);
  }

  /**
   * When button is clicked, add checkbox, text and a border below the text to differentiate between tasks
   */
  private final View.OnClickListener addingTaskBox = v -> {
    RelativeLayout taskBox = (RelativeLayout) getLayoutInflater().inflate(R.layout.edit_text,null);

//    CheckBox box = new CheckBox(taskBox.getContext());
//    box.setText("");
    //box.setBackgroundColor(getResources().getColor(R.color.white));
    //box.layout(10,5,1,0);
    //box.layout(0,22,0,0);
    //box.setPadding(10,12,10,10);

//    taskBox.addView(box);


//    EditText test = new EditText(taskBox.getContext());
//    //test.setTextColor(getResources().getColor(R.color.white));
//    test.setTextSize(30);
//    test.setText("TextView");

    //test.setId(); //useful for later
//    taskBox.addView(test, textParams);


//    View dividerBottom = new View(taskBox.getContext());



//    taskBox.addView(dividerBottom,listParams);

    list.addView(taskBox);
  };

}