package com.example.to_dolist;

import android.content.res.Configuration;

public class Settings {
  boolean appDarkMode;

  Settings(int mode){
    switch(mode){
      case Configuration.UI_MODE_NIGHT_NO:
        appDarkMode = false;
      case Configuration.UI_MODE_NIGHT_YES:
        appDarkMode = true;
      default:
        appDarkMode = true; //default to dark mode bc it nice
    }
  }

  public void setDarkMode(boolean darkMode){
    this.appDarkMode = darkMode;
  }

  public boolean getAppDarkMode(){
    return appDarkMode;
  }
}
