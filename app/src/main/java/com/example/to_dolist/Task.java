package com.example.to_dolist;

/**
 * Class to hold task information
 */
public class Task {

  /** Name of task */
  private String taskName;

  /** Constructor */
  Task(String taskName){
    this.taskName = taskName;
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
  public String getTask(){
    return taskName;
  }

}
