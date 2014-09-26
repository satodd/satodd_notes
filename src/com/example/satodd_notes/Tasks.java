package com.example.satodd_notes;

//Sarah Todd
//ccid: satodd
//App: satodd_notes

//Object that stores information regarding todos: their text, completion status and selected status. Also includes functions to fetch and set data. This is the base class for the app

public class Tasks {

	public String todo;
	public boolean complete = false;
	public boolean selected = false;

	
	public void set_text(String text) {
		//set to-do text
		this.todo = text;
	}
	public void set_complete(){
		this.complete = true;
	}
	public void select(){
		this.selected = true;
	}
	public void deselect(){
		this.selected = false;
	}

	public void uncomplete(){
		this.complete = false;
	}	
	
	public String getTodo(){
		return todo;
	}
	
}
