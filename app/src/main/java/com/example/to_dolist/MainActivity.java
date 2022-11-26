package com.example.to_dolist;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.HashMap;

/**
 * Main Activity Class
 */
public class MainActivity extends AppCompatActivity {

  /** HashMap for holding tasks */
  private HashMap<String, Task> taskHashMap;

  private Button addBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    LinearLayout list = findViewById(R.id.addingTest);

    addBtn = findViewById(R.id.addTaskBtn);
    addBtn.setOnClickListener((View v) -> {
      TextView test = new TextView(this);
      test.setTextColor(getResources().getColor(R.color.white));
      test.setText("Hello@@!");
      list.addView(test);
    });

  }


}