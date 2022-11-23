package com.example.to_dolist;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    Log.i("Night Mode? ",": "+AppCompatDelegate.getDefaultNightMode());

    Button nightMode = findViewById(R.id.menu);
    nightMode.setOnClickListener(changeDarkMode);
  }

  private View.OnClickListener changeDarkMode = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
      Log.i("Night Mode? ",": "+AppCompatDelegate.getDefaultNightMode());
    }
  };
}