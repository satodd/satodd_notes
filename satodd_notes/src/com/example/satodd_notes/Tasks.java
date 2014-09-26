package com.example.satodd_notes;

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
