package com.example.to_dolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.io.Serializable;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Delete extends AppCompatActivity {

    int taskBoxID;
    String taskName;
    TextView deleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent deleteIntent = getIntent();
        taskName = deleteIntent.getStringExtra("nameOfTask");
        taskBoxID = deleteIntent.getIntExtra("taskBoxID",-1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_task);

        deleteTextView = findViewById(R.id.nameOfTask);
        deleteTextView.setText(taskName);

        Button del_button = (Button) findViewById(R.id.delete_button);
        del_button.setOnClickListener(deleteListener);

        ImageButton home_button = (ImageButton) findViewById(R.id.home_button);
        home_button.setOnClickListener(homeListener);

        ImageButton tasks_button = (ImageButton) findViewById(R.id.tasks_button);
        tasks_button.setOnClickListener(taskListener);
    }

    private View.OnClickListener deleteListener = new View.OnClickListener() {
        Context context;
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(Delete.this)
                    .setTitle("Confirm")
                    .setMessage("Do you really want to delete this task?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            MediaPlayer ring= MediaPlayer.create(Delete.this,R.raw.garbage);
                            ring.start();

                            Toast.makeText(Delete.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                        }})
                    .setNegativeButton(android.R.string.no,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    }).show();
        }
    };

    private View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Delete.this, Delete.class));
        }
    };

    private View.OnClickListener taskListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Delete.this, Delete.class));
        }
    };
}
