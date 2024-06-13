package com.example.taskmanager.beans;

import com.ezample.taskmanager.enums.TaskStatus;

import lombok.Data;

@Data
public class TaskStatusQuantityRes {

	TaskStatus status;
	
	int quantity;
}
