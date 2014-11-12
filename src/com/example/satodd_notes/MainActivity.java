//Sarah Todd
//ccid: satodd
//App: satodd_notes
//A Todo app where you can add, delete, save, email items as well as view saved/archived items. For more information, including for how to use and licencing see the README file included.
//How to do UI elements referenced here: http://developer.android.com/index.html 09-16-2014

package com.example.satodd_notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import java.util.Collection;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	/**
	 * @uml.property  name="toDo_Activity"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="mainActivity:com.example.satodd_notes.ToDo_Activity"
	 */
	private Collection<ToDo_Activity> toDo_Activity;

	/**
	 * Getter of the property <tt>toDo_Activity</tt>
	 * @return  Returns the toDo_Activity.
	 * @uml.property  name="toDo_Activity"
	 */
	public Collection<ToDo_Activity> getToDo_Activity() {
		return toDo_Activity;
	}

	/**
	 * Setter of the property <tt>toDo_Activity</tt>
	 * @param toDo_Activity  The toDo_Activity to set.
	 * @uml.property  name="toDo_Activity"
	 */
	public void setToDo_Activity(Collection<ToDo_Activity> toDo_Activity) {
		this.toDo_Activity = toDo_Activity;
	}

	/**
	 * @uml.property  name="toDo_Activity1"
	 * @uml.associationEnd  inverse="mainActivity1:com.example.satodd_notes.ToDo_Activity"
	 */
	private ToDo_Activity toDo_Activity1;

	/**
	 * Getter of the property <tt>toDo_Activity1</tt>
	 * @return  Returns the toDo_Activity1.
	 * @uml.property  name="toDo_Activity1"
	 */
	public ToDo_Activity getToDo_Activity1() {
		return toDo_Activity1;
	}

	/**
	 * Setter of the property <tt>toDo_Activity1</tt>
	 * @param toDo_Activity1  The toDo_Activity1 to set.
	 * @uml.property  name="toDo_Activity1"
	 */
	public void setToDo_Activity1(ToDo_Activity toDo_Activity1) {
		this.toDo_Activity1 = toDo_Activity1;
	}

	/** 
	 * @uml.property name="toDo_Activity2"
	 * @uml.associationEnd inverse="mainActivity1:com.example.satodd_notes.ToDo_Activity"
	 */
	private ToDo_Activity toDo_Activity2;

	/** 
	 * Getter of the property <tt>toDo_Activity2</tt>
	 * @return  Returns the toDo_Activity2.
	 * @uml.property  name="toDo_Activity2"
	 */
	public ToDo_Activity getToDo_Activity2() {
		return toDo_Activity2;
	}

	/** 
	 * Setter of the property <tt>toDo_Activity2</tt>
	 * @param toDo_Activity2  The toDo_Activity2 to set.
	 * @uml.property  name="toDo_Activity2"
	 */
	public void setToDo_Activity2(ToDo_Activity toDo_Activity2) {
		this.toDo_Activity2 = toDo_Activity2;
	}

	/** 
	 * @uml.property name="archived_Activity"
	 * @uml.associationEnd inverse="mainActivity:com.example.satodd_notes.Archived_Activity"
	 */
	private Archived_Activity archived_Activity;

	/** 
	 * Getter of the property <tt>archived_Activity</tt>
	 * @return  Returns the archived_Activity.
	 * @uml.property  name="archived_Activity"
	 */
	public Archived_Activity getArchived_Activity() {
		return archived_Activity;
	}

	/** 
	 * Setter of the property <tt>archived_Activity</tt>
	 * @param archived_Activity  The archived_Activity to set.
	 * @uml.property  name="archived_Activity"
	 */
	public void setArchived_Activity(Archived_Activity archived_Activity) {
		this.archived_Activity = archived_Activity;
	}

		
		/**
		 */
		public void onCreate(){
		}

}
