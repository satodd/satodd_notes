package com.example.satodd_notes;

//Sarah Todd
//ccid: satodd
//App: satodd_notes
//This object creates an array Tasklists containing the object(s) Tasks, allowing for easy access to the items in the list and the ability to manipulatre the list itself.
//Operations performed on the array list are found here including size, changing to just text array, deletion and getting information.

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

/**
 * @uml.dependency   supplier="com.example.satodd_notes.Tasks"
 */
public class TaskList{

	public List<Tasks> Task_List = new ArrayList<Tasks>();
	
	public void add_new(String new_text, int position){
		//add new tasks to the list 
		Tasks NewTask = new Tasks();
		
		//initializes new task
		NewTask.set_text(new_text);
		if (Task_List.size() < position){
			while (position > Task_List.size()){
				Task_List.add(null);
			}
		}
		Task_List.add(position, NewTask);
		System.out.print("hold");
		//return Task_List;
	}
	
	public String[] List_To_Array(){
		String[] tmpArray = new String[Task_List.size()];
		
		for (int i =  0; i < Task_List.size(); i++) {
			tmpArray[i] = Task_List.get(i).getTodo() + "\n";
			
		}
		return tmpArray;
	}
	
	public int length(){
		return Task_List.size();
	}
	
	public boolean Select(int index){
		//looks in list, if item is selected, if it is, turns to false, if not turns to true
		if (Task_List.get(index).selected == true){
			//Task_List.get(index).selected = false;
			return true;
			}
		else {
			//Task_List.get(index).selected = true;
			return false;
		}
	}
	
	public boolean isCompleted(int index){
		//looks in list, if item is selected, if it is, turns to false, if not turns to true
		if (Task_List.get(index).complete == true){
			//Task_List.get(index).complete = false;
			return true;
			}
		else {
			//Task_List.get(index).complete = true;
			return false;
		}
	}
	
	public Tasks get(int index){
		//gets task from list at index
		Tasks task = Task_List.get(index);
		return task;
	}
	
	public void remove(int position){
		//remove single item from selected list
		Task_List.remove(position);
		System.out.print("hold");
	}
	
	public void delete(){
		//remove tasks selected from main list
		System.out.print("hold");
		int x = -1;

		while (x < Task_List.size()){
			if (x < 0) {
				x++; }
			Tasks string = Task_List.get(x);
			if (string.selected == true){
				//Task_List.get(x).selected = false;
				Task_List.remove(x);
				if (x != 0){
					x--;
				};
			}
			else {
				x++;
			}
			System.out.print("hold");
		}
	}
	

	public void clear(){
		Task_List.clear();
	}


	/** 
	 * @uml.property name="tasks"
	 * @uml.associationEnd aggregation="composite" inverse="taskList1:com.example.satodd_notes.Tasks"
	 */
	private Tasks tasks;

	/** 
	 * Getter of the property <tt>tasks</tt>
	 * @return  Returns the tasks.
	 * @uml.property  name="tasks"
	 */
	public Tasks getTasks() {
		return tasks;
	}

	/** 
	 * Setter of the property <tt>tasks</tt>
	 * @param tasks  The tasks to set.
	 * @uml.property  name="tasks"
	 */
	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}


}