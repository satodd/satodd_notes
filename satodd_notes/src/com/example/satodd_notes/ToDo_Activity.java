package com.example.satodd_notes;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
//import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ToDo_Activity extends Activity {
	
	private static final String FILENAME = "todo.txt";
	private static final String OUTFILE = "completed.txt";
	private ListView list_view;
	private int todoTotal;
	private int todoChecked;
	private int archivedTotal;
	private int archivedChecked;
	
	TaskList List = new TaskList();	
	TaskList Selected = new TaskList();
	TaskList Archived = new TaskList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_);
		
		//add/delete/archive/email/stats button function, view and textbox 
		Button nButton = (Button) findViewById(R.id.New_button);
		Button dButton = (Button) findViewById(R.id.delete_button);
		Button doneButton = (Button) findViewById(R.id.archive_button);
		Button eButton = (Button) findViewById(R.id.email_button);
		Button sButton = (Button) findViewById(R.id.Stats_button);
		final AutoCompleteTextView todo_view = (AutoCompleteTextView) findViewById(R.id.add_new);
		list_view = (ListView) findViewById(R.id.Listview);
		
		
		//Click the "new" button, saves the todo
		nButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//add to task list
				String text = todo_view.getText().toString();
				//saveInFile(text + "\n");
				List.add_new(text, List.length());
				//saveInFile(List.List_To_Array());
				makeAToast("Added!");
				onGoing();
			}
			});
		
		//Click Delete button, deletes selected tasks completely
		dButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//delete task list
				int x = 0;
				while (x < List.length()){
					if (List.get(x).selected == true){
						List.delete();
					}
					x++;
				}
				makeAToast("Deleted!");
				onGoing();
			}
			});
		
		//Click Archive button, appends string to completed/archived file
		doneButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//add to task list
				int x = 0;
				while (x < List.length()){
					if (List.get(x).selected == true){
						String[] array = List.List_To_Array();
						writeOutofFile(array);
						
						makeAToast("Archived!");
						
						List.delete();
						onGoing();
					}
					x++;
				}	
			}
			});
		
		//brings up statistics that updates with every new addition/deletion
		sButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onGoing();
				countFromFile();
				stats();
				
			}
		});
		
		//listens for email click, creates dialog and emails depending on the choice from the dialog
		eButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog alert = getAlert();
				alert.show();
			} 
		});

			
		//listens for SHORT clicking an item in the list view, once item is checked, looks for if it was selected already
		//SETS CHECK BOX/COMPLETED
		list_view.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				boolean isCompleted = List.isCompleted(position);
				if (isCompleted == false) {
					list_view.setItemChecked(position, true);	
					List.get(position).set_complete();
				}
				else {
					list_view.setItemChecked(position, false);
					List.get(position).complete = false ;
				}
				
				onGoing();
			}
		});
		
		//listens for LONG clicking an item in the list view, once item is checked, looks for if it was selected already
		//SETS SELECTED
		list_view.setOnItemLongClickListener(new OnItemLongClickListener(){
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				boolean isSelected = List.Select(position);
				if (isSelected == false) {	
					List.get(position).select();
					list_view.getChildAt(position).setBackgroundColor(Color.rgb(157, 222, 149));
					// in red value R = 222, G = 149, B = 149
					System.out.print("hold");
				}
				else {
					list_view.getChildAt(position).setBackgroundColor(Color.WHITE);
					List.get(position).deselect();
				}
				
				System.out.print("hold");
				//onGoing();
				return false;
			}
			
		});
		
	} //end of onCreate()
	
	
	//creates Dialog for emailing, calls email when a selection occurs
	private AlertDialog getAlert(){
		String []emailTypes = {"Email Todo", "Email Selected", "Email All"};

		AlertDialog.Builder builder = new AlertDialog.Builder(ToDo_Activity.this);
		builder.setTitle("What would you like to email?");
		builder.setItems(emailTypes, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
               	   email(which);
	}
		});
		AlertDialog question = builder.create();
		return question;
	}
	//dynamically changes listview to update for changes
	private void onGoing(){
	
	 String[] task = List.List_To_Array();
	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.abc_list_menu_item_checkbox, task);
	 list_view.setAdapter(adapter);
	 int x = 0;
	 saveInFile(task);
	 
	 while (x < List.length()){
		 boolean isCompleted = List.isCompleted(x);
			if (isCompleted == true) {
				list_view.setItemChecked(x, true);	
			}
			else {
				list_view.setItemChecked(x, false);
			}
			System.out.print("hold");
			x++;
		}//end of while loop
	 saveInFile(task);
}

	//Base code acquired from lonelytwitter: https://github.com/joshua2ua/lonelyTwitter. 09/16/2014

	protected void onStart() {
	super.onStart();
	loadFromFile();
	String[] todo = List.List_To_Array();
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.abc_list_menu_item_checkbox, todo);
	list_view.setAdapter(adapter);
	int x = 0;
	 
	 while (x < List.length()){
		 boolean isCompleted = List.isCompleted(x);
			if (isCompleted == true) {
				list_view.setItemChecked(x, true);	
			}
			else {
				list_view.setItemChecked(x, false);
			}
			System.out.print("hold");
			x++;
	 }//end of while loop
}

	private void loadFromFile() {
	ArrayList<String> todo = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			String lineText;
			int index = 0;
				while (line != null) {
					/// a line is decoded as selected (0/1), text
						lineText = line.substring(1);
						todo.add(lineText);
						List.add_new(lineText, index);
						todoTotal++;
						//char test = line.charAt(0);
						if (line.charAt(0) == '1') {
							List.get(index).complete = true;
							list_view.setItemChecked(index, true);
							todoChecked++;
					}
					index++;
					line = in.readLine();
				} //end of while 
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		onGoing();
}

	private void saveInFile(String[] array) {
	try {
		int y = 0;
		FileOutputStream fos =  openFileOutput(FILENAME, array.length);
		String[] output = new String[array.length];
		todoChecked = 0;
		todoTotal = 0;
		while (y < array.length)	{
			if (array[y] != null){
				todoTotal++;
				//appends code in front of string. selected/text
				if (List.get(y).complete == false){
					output[y] = "0" + array[y];
				}
				else {
					output[y] = "1"+ array[y];
					todoChecked++;
				}
				fos.write(new String(output[y]).getBytes());}
			y++;
		} //end of while
		
		fos.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	private void writeOutofFile(String[] array){
	try {
		int y = 0;
		FileOutputStream fos =  openFileOutput(OUTFILE, Context.MODE_APPEND);
		while (y < array.length)	{
			if (array[y] != null){
				if (List.get(y).selected == true){
					array[y] = "0" + array[y];
				}
				fos.write(new String(array[y]).getBytes());}
			y++;
		} //end of while loop
		fos.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	private void countFromFile(){
		try {
			archivedTotal = 0;
			archivedChecked = 0;
			saveInFile(List.List_To_Array());
			FileInputStream fis = openFileInput(OUTFILE);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			int j= 0;
				while (line != null) {
					archivedTotal++;
					if (line.charAt(0) == '1') {
						archivedChecked++;
						Archived.add_new(line.substring(1), j);
					}
					line = in.readLine();
					j++;
				}//end of while 
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	//END OF CODE FROM LONELYTWITTER
	
	//gets position of click from emailing Alert dialog and sends the appropriate string
	private void email(int position){
		if (position == 0){ //Email todo
			String[] tmp = List.List_To_Array();
			sending(tmp);
		}
		else if (position == 1){ //Email Selected
			int i = 0;
			//add to Selected List
			while (i < List.length()){
				if (List.get(i).selected == true){
					Selected.add_new(List.get(i).todo, position);
				}
				i++;
			}//end of while loop

			String[] tmp = Selected.List_To_Array();
			sending(tmp);
		}
		else if (position == 2){ //Email All
			int i = List.length() + Archived.length();
			int k = Archived.length();
			while (k < i){
				Archived.add_new(List.get(k).todo, k);
				k++;
			}//end of while loop
			String[] tmp = Archived.List_To_Array();
			sending(tmp);
		}
		
	}
	
	//Code/guide for emailing in android apps found here: http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application 09/22/2014
	private void sending(String[] message){
		Intent tosend = new Intent(Intent.ACTION_SEND);
		tosend.putExtra(Intent.EXTRA_SUBJECT, "To Do's");
		tosend.putExtra(Intent.EXTRA_TEXT, message);
		try {
		    startActivity(Intent.createChooser(tosend, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(ToDo_Activity.this, "There are no email clients installed.", Toast.LENGTH_LONG).show();
		}
	}
	
	private void stats(){
		//Shows Statistics in a dialog box
		AlertDialog.Builder builder = new AlertDialog.Builder(ToDo_Activity.this);
		int unchecked = todoTotal - todoChecked;
		int aUnchecked = archivedTotal - archivedChecked;
		builder.setMessage(" Total number of TODO items: " + todoTotal +
				"\n Total number of TODO items checked: " + todoChecked +
				"\n Total number of TODO items left unchecked: " + unchecked +
				"\n Total number of archived TODO items: " + archivedTotal +
				"\n Total number of checked archived TODO items: " + archivedChecked +
				"\n Total number of unchecked archived TODO items: " + aUnchecked);
		AlertDialog question = builder.create();
		question.show();
		builder.setTitle("Statistics");
		}
	
	private void makeAToast(String text){
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
	

