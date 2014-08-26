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
import android.widget.Toast;

public class taskManager extends Activity{
	private static final int MENU_ABOUT=Menu.FIRST;
	private static final int MENU_EXIT=Menu.FIRST+1;
	private static int selectedItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskmanager);
		
		//get selected item from intent
		
		Intent i = getIntent();
		selectedItem=i.getIntExtra("selectedItem",0);
		//Toast.makeText(getApplicationContext(), selectedItem+"",Toast.LENGTH_LONG ).show();
		
		
		
		//manage task code here
		//create reference to complete button
		Button completed=(Button)findViewById(R.id.buttonDeleteTASKMANAGER);
		
		completed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//read the list of tasks from tasks.txt
				BufferedReader reader=null;
				FileInputStream inputStream=null;
				BufferedWriter writer=null;
				FileOutputStream outputStream=null;
				String toBeDeleted="";
				try {
					inputStream=openFileInput("tasks.txt");
					reader=new BufferedReader(new InputStreamReader(inputStream));
					String line="";
					String taskList="";
					line=reader.readLine();
					int i=0;
					
					//int position=Integer.parseInt(selectedItem);
					
					while(line!=null) {
						if(i==selectedItem) {
							toBeDeleted=line;
							line=reader.readLine();
							i++;
						} else {
							taskList=taskList+line+"\n";
							line=reader.readLine();
							i++;
						}
					}
					
					reader.close();
					
					//write the new taskList to file tasks.txt
					
					outputStream=openFileOutput("tasks.txt",Context.MODE_PRIVATE);
					writer=new BufferedWriter(new OutputStreamWriter(outputStream));
					writer.write(taskList);
					
					writer.close();
					
					Toast.makeText(getApplicationContext(),"Task moved to Completed Successfully",Toast.LENGTH_SHORT).show();
					
					
					//check if completed.txt exists or not 
					
					File tasks=getBaseContext().getFileStreamPath("completed.txt");
					if(tasks.exists()) {
						
						//reading contents
						try {
							inputStream=openFileInput("completed.txt");
							
							reader=new BufferedReader(new InputStreamReader(inputStream));
							line=reader.readLine();
							String taskListdone="";
							while(line!=null) {
								taskListdone=taskListdone+line+"\n";
								line=reader.readLine();
							}
							reader.close();
						
							//writing contents
							
							//now we have read the contents of file into String taskList
							//append the new task to be added to the 
							taskListdone=toBeDeleted+"\n"+taskListdone;
							outputStream=openFileOutput("completed.txt",Context.MODE_PRIVATE);
							writer=new BufferedWriter(new OutputStreamWriter(outputStream));
							writer.write(taskListdone);
							writer.close();
							
						} catch(Exception e) {
							
						}
						
						
					} else {
						//create new file
						//write the current task to file
						 writer = null;
						try{
							FileOutputStream outputstream = openFileOutput("completed.txt",Context.MODE_PRIVATE);
							writer = new BufferedWriter(new OutputStreamWriter(outputstream));
							writer.write(toBeDeleted);
							
							writer.close();
							
						}
						catch(Exception e){
							
						}
					}
					
					
					
					finish();
				} catch(Exception e) {
					
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
