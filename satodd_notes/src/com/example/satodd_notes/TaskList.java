package com.example.satodd_notes;

import java.util.ArrayList;
import java.util.List;

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
	public void email(){
		//email checked todo's
	}


}