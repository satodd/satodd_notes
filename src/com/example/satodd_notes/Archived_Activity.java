//Sarah Todd
//ccid: satodd
//App: satodd_notes

//The Archived Activity. Shows the list of archived objects and gives options to move/change their states.

package com.example.satodd_notes;

import android.app.Activity;
import android.os.Bundle;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Archived_Activity extends Activity {

	
	private static final String OUTFILE = "todo.txt";
	private static final String FILENAME = "completed.txt";
	private ListView arch_view;
	private int todoTotal;
	private int todoChecked;
	private int archivedTotal;
	private int archivedChecked;
	
	TaskList List = new TaskList();	
	TaskList Selected = new TaskList();
	TaskList Archived = new TaskList();
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archived_);

		//add/delete button function, view and textbox 
		Button dButton = (Button) findViewById(R.id.delete_Button);
		Button doneButton = (Button) findViewById(R.id.unarchive_Button);
		Button eButton = (Button) findViewById(R.id.email_Button);
		Button sButton = (Button) findViewById(R.id.stats_Button);
		arch_view = (ListView) findViewById(R.id.archlist);
		//oldList.setAdapter(list_adapter);
		

	//Click Done button, appends string to todo file
		doneButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//add to task list
				int x = 0;
				while (x < List.length()){
					if (List.get(x).selected == true){
						String array[] = List.List_To_Array();
						writeOutofFile(array);
						makeAToast("Unarchived!");
					}
					x++;
				}
				List.delete();
				onGoing();
			}
			});
		
	//listens for short clicking an item in the list view, once item is checked, looks for if it was selected already
		arch_view.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				boolean isCompleted = List.isCompleted(position);
				if (isCompleted == false) {
					arch_view.setItemChecked(position, true);	
					List.get(position).set_complete();
				}
				else {
					arch_view.setItemChecked(position, false);
					List.get(position).complete = false ;
				}
				onGoing();
			}
		});
		// lists for long clicking an item in the list view and highlights it
		arch_view.setOnItemLongClickListener(new OnItemLongClickListener(){
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				boolean isSelected = List.Select(position);
				if (isSelected == false) {	
					List.get(position).select();
					arch_view.getChildAt(position).setBackgroundColor(Color.rgb(157, 222, 149));
					// in red value R = 222, G = 149, B = 149
				}
				else {
					arch_view.getChildAt(position).setBackgroundColor(Color.WHITE);
					List.get(position).deselect();
				}
				return false;
			}
			
		});
		
		//listens for email click, creates dialog and emails depending on the choice from the dialog
		eButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog alert = getAlert();
				alert.show();
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
		
	}

	//dynamically changes listview. 
	private void onGoing(){

		String[] task = List.List_To_Array();
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.abc_list_menu_item_checkbox, task);
		 arch_view.setAdapter(adapter);
		 int x = 0;
		 saveInFile(task);
		 
		 while (x < List.length()){
			 boolean isCompleted = List.isCompleted(x);
				if (isCompleted == true) {
					arch_view.setItemChecked(x, true);	
				}
				else {
					arch_view.setItemChecked(x, false);
				}
				x++;
			}//end of while loop
		 saveInFile(task);
	}
	
	//Code referenced from lonelytwitter https://github.com/joshua2ua/lonelyTwitter 09/16/2014

	protected void onStart() {
		super.onStart();
		loadFromFile();
		String[] todo = List.List_To_Array();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.abc_list_menu_item_checkbox, todo);
		arch_view.setAdapter(adapter);
		int x = 0;
		 
		 while (x < List.length()){
			 boolean isCompleted = List.isCompleted(x);
				if (isCompleted == true) {
					arch_view.setItemChecked(x, true);	
				}
				else {
					arch_view.setItemChecked(x, false);
				}
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
						if (line.charAt(0) == '1') {
							List.get(index).complete = true;
							arch_view.setItemChecked(index, true);
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
			archivedChecked = 0;
			archivedTotal = 0;
			while (y < array.length)	{
				if (array[y] != null){
					archivedTotal++;
					//appends code in front of string. selected/text
					if (List.get(y).complete == false){
						output[y] = "0" + array[y];
					}
					else {
						output[y] = "1"+ array[y];
						archivedChecked++;
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
					else {
						array[y] = "1" + array[y];
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
			todoTotal = 0;
			todoChecked = 0;
			saveInFile(List.List_To_Array());
			FileInputStream fis = openFileInput(OUTFILE);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			int j= 0;
				while (line != null) {
					todoTotal++;
					if (line.charAt(0) == '1') {
						todoChecked++;
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
		
	// END OF CODE FROM LONELYTWITTER
	
	private void stats(){
		//Shows Statistics in a dialog box
		AlertDialog.Builder builder = new AlertDialog.Builder(Archived_Activity.this);
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
	
	//add in cite for andriod developer
	private void makeAToast(String text){
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
	private AlertDialog getAlert(){
		
		String []emailTypes = {"Email Selected"};

		AlertDialog.Builder builder = new AlertDialog.Builder(Archived_Activity.this);
		builder.setTitle("What would you like to email?");
		builder.setItems(emailTypes, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
               	   email(which);
	}
		});
		AlertDialog question = builder.create();
		return question;
	}
	
	//gets position of click from emailing Alert dialog and sends the appropriate string
	private void email(int position){
			if (position == 0){ //Email Selected
				int i = 0;
				//add to Selected List
				while (i < List.length()){
					if (List.get(i).selected == true){
						Selected.add_new(List.get(i).todo, position);
						
					}
				}//end of while loop

				String[] tmp = Selected.List_To_Array();
				if (Selected.length() > 0){
					sending(tmp);
				}
				else {
					makeAToast("No tasks selected");
				}
			}
		}
	

	//Code/guide for emailing in android apps found here:shish Pathak and madlymad,  http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application 09/22/2014
	private void sending(String[] message){
			Intent tosend = new Intent(Intent.ACTION_SEND);
			tosend.putExtra(Intent.EXTRA_SUBJECT, "To Do's");
			tosend.putExtra(Intent.EXTRA_TEXT, message);
			try {
			    startActivity(Intent.createChooser(tosend, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
			    makeAToast("There are no email clients installed.");
			}
		}
	
	
	}


