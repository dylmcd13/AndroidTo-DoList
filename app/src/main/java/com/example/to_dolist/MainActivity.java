package com.example.to_dolist;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.android.material.appbar.MaterialToolbar;


public class MainActivity extends AppCompatActivity {

  SharedPreferences pref;
  SharedPreferences.Editor editor;
  ToggleButton darkModeButton;
  Bundle bundle;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
//    int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
    pref = getPreferences(Context.MODE_PRIVATE);
    editor = pref.edit();
    bundle = new Bundle();

//    setLightOrDarkMode(mode);
    if(savedInstanceState != null){ //after recreation
      if(setLightOrDarkMode(pref))
        savedInstanceState.putBoolean("darkMode",true);
      else
        savedInstanceState.putBoolean("darkMode",false);
      //set theme here when Activity is recreated
    }
    else { //first start
      bundle.putBoolean(String.valueOf(R.string.darkMode_key),true);
      setTheme(R.style.AppThemeDark);
    }
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    darkModeButton = findViewById(R.id.darkModeButton);
    Log.i("onCreate","Button Checked?: "+darkModeButton.isChecked());
//    setInitialButton(mode);
    darkModeButton.setOnCheckedChangeListener(checkListener);
    darkModeButton.setOnClickListener(v -> {
      recreate();
    });
  }

  private final CompoundButton.OnCheckedChangeListener checkListener = (buttonView, isChecked) -> {
    if(isChecked) {
      setTheme(R.style.AppThemeDark);
      editor.putBoolean(String.valueOf(R.string.darkMode_key),true);
    }
    else {
      setTheme(R.style.AppThemeLight);
      editor.putBoolean(String.valueOf(R.string.darkMode_key),false);
    }

  };

//  private View.OnClickListener darkModeListener = (View v) -> {
//    darkModeButton.setBackgroundResource(R.drawable.moon);
//  };

  /**
   *
   */
  private boolean setLightOrDarkMode(SharedPreferences pref){
    boolean dark = true;
    boolean dm = pref.getBoolean(String.valueOf(R.string.darkMode_key),dark);
    Log.i("setLightOrDarkMode","boolean: "+dm);
    if (dm) {
      setTheme(R.style.AppThemeDark);
      return true;
    } else {
      setTheme(R.style.AppThemeLight);
      return false;
    }
  }

  private void setInitialButton(int mode){
    switch(mode){
      case Configuration.UI_MODE_NIGHT_NO:
        darkModeButton.setChecked(false);
        break;
      case Configuration.UI_MODE_NIGHT_YES:
        darkModeButton.setChecked(true);
        break;
    }
  }

}