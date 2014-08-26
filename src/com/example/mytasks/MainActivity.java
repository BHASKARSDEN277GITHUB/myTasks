package com.example.mytasks;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
	
	private TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tabHost=getTabHost();
		
		//creating a tabhost.tabspec to specify the tab specifications
		TabHost.TabSpec specs;
		//creating intent object for reffering the activities
		Intent i;
		
		//adding the tab specifications now
		
		//add tasks tab 
		i=new Intent(this,addTask.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
;
		specs=tabHost.newTabSpec("addTasks")
				.setContent(i)
				.setIndicator("Add Tasks");
		tabHost.addTab(specs);
		
		//pending tasks tab
		i=new Intent(this,pendingTasks.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
;
		specs=tabHost.newTabSpec("pendingTasks")
				.setContent(i)
				.setIndicator("Pending Tasks");
		tabHost.addTab(specs);
		
		//done tasks
		i=new Intent(this,doneTasks.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
;
		specs=tabHost.newTabSpec("doneTasks")
				.setContent(i)
				.setIndicator("Swargeeya Tasks");
		tabHost.addTab(specs);
		
		//set default tab here
		tabHost.setCurrentTab(0);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
	
		return true;
	}
	
	

}
