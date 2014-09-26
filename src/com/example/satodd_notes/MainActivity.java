package com.example.satodd_notes;

//Sarah Todd
//ccid: satodd
//App: satodd_notes
//A Todo app where you can add, delete, save, email items as well as view saved/archived items. For more information, including for how to use and licencing see the README file included.
//How to do UI elements referenced here: http://developer.android.com/index.html 09-16-2014

import com.example.satodd_notes.Archived_Activity;
import com.example.satodd_notes.ToDo_Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	//starts to do activity
	public void toDo_main(View view) {
		Intent intent = new Intent(this, ToDo_Activity.class);
		this.startActivity(intent);
	}
	
	//starts completed activity
	public void completed_main(View view) {
		Intent intent = new Intent(this, Archived_Activity.class);
		this.startActivity(intent);
	}
}
