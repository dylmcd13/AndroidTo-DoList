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
  LinearLayout.LayoutParams textParams;

  /** Parameters for list to have list border match parent size and have enough height to be visible */
  LinearLayout.LayoutParams listParams;


  LinearLayout.LayoutParams checkBoxParams;

  /** Layout for list */
  LinearLayout list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.initialize();
  }

  /**
   * Initializes View Components and parameters for for the text and list borders
   */
  private void initialize(){
    list = findViewById(R.id.addingTest);
    addBtn = findViewById(R.id.addTaskBtn);
    listParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5);
    textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    textParams.leftMargin = 100;
    checkBoxParams = new LinearLayout.LayoutParams(30,30);
    addBtn.setOnClickListener(addingTaskBox);
  }

  /**
   * When button is clicked, add checkbox, text and a border below the text to differentiate between tasks
   */
  private final View.OnClickListener addingTaskBox = v -> {
    RelativeLayout taskBox = new RelativeLayout(this);


    CheckBox box = new CheckBox(taskBox.getContext());
    box.setText("");
    //box.layout(0,22,0,0);
    //box.setPadding(10,12,10,10);

    taskBox.addView(box);


    TextView test = new TextView(taskBox.getContext());
    //test.setTextColor(getResources().getColor(R.color.white));
    test.setTextSize(30);
    test.setText("TextView");

    //test.setId(); //useful for later
    taskBox.addView(test, textParams);


    View dividerBottom = new View(taskBox.getContext());
    dividerBottom.setBackgroundColor(getResources().getColor(R.color.white));
    dividerBottom.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);


    taskBox.addView(dividerBottom,listParams);
    list.addView(taskBox);
  };

}