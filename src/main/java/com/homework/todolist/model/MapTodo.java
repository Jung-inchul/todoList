/**
 * 
 */
package com.homework.todolist.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Incheol Jung
 */
@Entity
public class MapTodo {
	@Id
	private int mapId;
	private int taskId;
	private int referenceId;
	
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}
}
