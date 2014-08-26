package com.example.mytasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class pendingTasks extends Activity {
	
	private static final int MENU_ABOUT=Menu.FIRST;
	private static final int MENU_EXIT=Menu.FIRST+1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pendingtask);
		
		//create reference to the list view
		final ListView list=(ListView)findViewById(R.id.listPendingTasks);
		
		//check if any tasks are there or not 
		File taskFile = getBaseContext().getFileStreamPath("tasks.txt");
		if(taskFile.exists()){
			
			//create bufferedReader reference 
			BufferedReader reader ;
			String line="";
			FileInputStream inputStream=null;
			
			try {
					inputStream=openFileInput("tasks.txt");
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
					
					/**
					 * setting on click listener on items of the list view
					 */
					
					list.setOnItemClickListener(new OnItemClickListener(){
						@Override
						public void onItemClick(AdapterView<?>parent,View view,int position,long id){
						
							//Toast.makeText(getApplicationContext(), position+"", Toast.LENGTH_SHORT).show();
							//create intent to lauch task Manager dialog activity
							Intent i=new Intent(getApplicationContext(),taskManager.class);
							//add the selectedItem to the intent for passing it to next activity
							int j=position;
							i.putExtra("selectedItem",j);
							startActivity(i);
							
							
							
						}
					});
					
				
			}catch(Exception e){
				
			}
			
		} else {
			Toast.makeText(getApplicationContext(),"NO TASKS ADDDED", Toast.LENGTH_LONG).show();
		}
			
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
