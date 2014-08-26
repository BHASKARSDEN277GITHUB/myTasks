package com.example.mytasks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addTask extends Activity {
	
	private static final int MENU_ABOUT=Menu.FIRST;
	private static final int MENU_EXIT=Menu.FIRST+1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtask);
		
		//create reference to the button "ADD"
		Button addButton=(Button)findViewById(R.id.doneButtonADD);
		//create reference to EditText to get the task
		final EditText task=(EditText)findViewById(R.id.enterNameEditADD);
		
		//write code for addButton listener
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//read the task name
				String taskName=task.getText().toString();
				
				//check if it is valid task name 
				if(taskName.length()>0) {
					//check if tasks file exists or not 
					File tasks=getBaseContext().getFileStreamPath("tasks.txt");
					if(tasks.exists()) {
						
						//reading contents
						BufferedReader reader=null;
						FileInputStream inputStream=null;
						
						BufferedWriter writer=null;
						FileOutputStream outputStream=null;
						
						try {
							inputStream=openFileInput("tasks.txt");
							String line="";
							reader=new BufferedReader(new InputStreamReader(inputStream));
							line=reader.readLine();
							String taskList="";
							while(line!=null) {
								taskList=taskList+line+"\n";
								line=reader.readLine();
							}
							reader.close();
						
							//writing contents
							
							//now we have read the contents of file into String taskList
							//append the new task to be added to the 
							taskList=taskName+"\n"+taskList; //appending new task to the beginning of the task list 
							outputStream=openFileOutput("tasks.txt",Context.MODE_PRIVATE);
							writer=new BufferedWriter(new OutputStreamWriter(outputStream));
							writer.write(taskList);
							writer.close();
							task.setText("");
							Toast.makeText(getApplicationContext(), "Task Added Successfully",Toast.LENGTH_SHORT).show();
							
						} catch(Exception e) {
							
						}
						
						
					} else {
						//create new file
						//write the current task to file
						BufferedWriter writer = null;
						try{
							FileOutputStream outputstream = openFileOutput("tasks.txt",Context.MODE_PRIVATE);
							writer = new BufferedWriter(new OutputStreamWriter(outputstream));
							writer.write(taskName);
							//bunks.setText(str2+"done");
							writer.close();
							task.setText("");
							Toast.makeText(getApplicationContext(),"Task Added Successfully",Toast.LENGTH_SHORT).show();
							
						}
						catch(Exception e){
							
						}
					}
				} else {
					Toast.makeText(getApplicationContext(),"Please Enter Task Name",Toast.LENGTH_SHORT).show();
				}
				
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		menu.add(0,MENU_ABOUT,0,"About");
		menu.add(0,MENU_EXIT,0,"Quit");
		return true;
	}
	
	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case MENU_ABOUT:
	        Intent about = new Intent(this,about.class);
	        startActivity(about);
	        
	        return true;
	    case MENU_EXIT:
	        finish();
	        return true;
	        
	    }
	    return false;
	}

}
