package com.example.satodd_notes;

import java.util.Collection;

//Sarah Todd
//ccid: satodd
//App: satodd_notes

//Object that stores information regarding todos: their text, completion status and selected status. Also includes functions to fetch and set data. This is the base class for the app

/**
 * @author  satodd
 */
public class Tasks {

	/**
	 * @uml.property  name="todo"
	 */
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
	
	/**
	 * @return
	 * @uml.property  name="todo"
	 */
	public String getTodo(){
		return todo;
	}

	/** 
	 * @uml.property name="taskList"
	 * @uml.associationEnd inverse="tasks:com.example.satodd_notes.TaskList"
	 */
	private TaskList taskList;


	/** 
	 * Getter of the property <tt>taskList</tt>
	 * @return  Returns the taskList.
	 * @uml.property  name="taskList"
	 */
	public TaskList getTaskList() {
		return taskList;
	}
	/** 
	 * Setter of the property <tt>taskList</tt>
	 * @param taskList  The taskList to set.
	 * @uml.property  name="taskList"
	 */
	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}

	/** 
	 * @uml.property name="taskList1"
	 * @uml.associationEnd multiplicity="(0 2)" inverse="tasks:com.example.satodd_notes.TaskList"
	 */
	private Collection taskList1;


	/** 
	 * Getter of the property <tt>taskList1</tt>
	 * @return  Returns the taskList1.
	 * @uml.property  name="taskList1"
	 */
	public Collection getTaskList1() {
		return taskList1;
	}
	/** 
	 * Setter of the property <tt>taskList1</tt>
	 * @param taskList1  The taskList1 to set.
	 * @uml.property  name="taskList1"
	 */
	public void setTaskList1(Collection taskList1) {
		this.taskList1 = taskList1;
	}
	
}
