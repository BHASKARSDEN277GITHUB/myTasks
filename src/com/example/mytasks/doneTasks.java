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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class doneTasks extends Activity {
	
	private static final int MENU_ABOUT=Menu.FIRST;
	private static final int MENU_EXIT=Menu.FIRST+1;
	private static final int MENU_CLEAR=Menu.FIRST+2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.donetask);
		
		//create reference to the list view
				final ListView list=(ListView)findViewById(R.id.listViewDoneTasks);
				
				//check if any tasks are there or not 
				File taskFile = getBaseContext().getFileStreamPath("completed.txt");
				if(taskFile.exists()){
					
					//create bufferedReader reference 
					BufferedReader reader ;
					String line="";
					FileInputStream inputStream=null;
					
					try {
							inputStream=openFileInput("completed.txt");
							reader=new BufferedReader(new InputStreamReader(inputStream));
							line=reader.readLine();
							int i=0; //to get total number of lines
							String tasksList="";
							while(line!=null) {
								tasksList=tasksList+line+"\n";
								line=reader.readLine();
							}
							
							reader.close();
							
							//get the string array from tasksList string 
							String[] tasks=tasksList.split("\n");
							
							//populate the list view here
							//define new array adapter to populate the list view
							ArrayAdapter<String> adapter_for_this_list=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,tasks);  // i'll be an asshole if i remember these parameters .. lol
							//assign adapter to the list view
							list.setAdapter(adapter_for_this_list);
							
						
					}catch(Exception e){
						
					}
					
				} else {
					Toast.makeText(getApplicationContext(),"NO TASKS COMPLETED", Toast.LENGTH_SHORT).show();
				}
				
				
				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		menu.add(0,MENU_ABOUT,0,"About");
		menu.add(0,MENU_EXIT,0,"Quit");
		menu.add(0,MENU_CLEAR,0,"Clear");
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
	    case MENU_CLEAR:
	    	//check if completed.txt exits
	    	//if exits clear the compeleted.txt file 
	    	File file=getBaseContext().getFileStreamPath("completed.txt");
	        if(file.exists()) {
	        	FileOutputStream outputStream=null;
	        	BufferedWriter writer=null;
	        	try {
	        		outputStream=openFileOutput("completed.txt",Context.MODE_PRIVATE);
	        		writer=new BufferedWriter(new OutputStreamWriter(outputStream));
	        		writer.write("");
	        		writer.close();
	        		
	        	} catch (Exception e) {
	        		
	        	}
	        	
	        } else {
	        	Toast.makeText(getApplicationContext(),"No task In completed List",Toast.LENGTH_SHORT).show();
	        	
	        	
	        }
	        
	        //update the list :) hahahahhahahahaahhahaha crazy shit :):):)):):)):)
	        final ListView list=(ListView)findViewById(R.id.listViewDoneTasks);
			
	      //create bufferedReader reference 
			BufferedReader reader ;
			String line="";
			FileInputStream inputStream=null;
			
			try {
					inputStream=openFileInput("completed.txt");
					reader=new BufferedReader(new InputStreamReader(inputStream));
					line=reader.readLine();
					int i=0; //to get total number of lines
					String tasksList="";
					while(line!=null) {
						tasksList=tasksList+line+"\n";
						line=reader.readLine();
					}
					
					reader.close();
					
					//get the string array from tasksList string 
					String[] tasks=tasksList.split("\n");
					
					//populate the list view here
					//define new array adapter to populate the list view
					ArrayAdapter<String> adapter_for_this_list=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,tasks);  // i'll be an asshole if i remember these parameters .. lol
					//assign adapter to the list view
					list.setAdapter(adapter_for_this_list);
			}catch(Exception e) {
				
			}
	        
	        return true;
	    }
	       
	    	return false ;
	    
	    
	}

}
