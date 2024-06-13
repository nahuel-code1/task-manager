package com.example.taskmanager.beans;

import com.ezample.taskmanager.enums.TaskStatus;

import lombok.Data;

@Data
public class Task {
	Long id;
    String title;
    String description;
    TaskStatus status;
    Long projectId;
}
