package com.example.satodd_notes;

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
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//	
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
