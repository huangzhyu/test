package com.damuzee.model;

import java.util.List;


public class ResultHolder {
	private List<Task> tasks;
	private Task task;
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
}
