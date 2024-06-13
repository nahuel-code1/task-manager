package com.example.taskmanager.beans;

import java.util.List;

import lombok.Data;

@Data
public class Project {

	Long id;
    String name;
    Long userId;
    List<Task> tasks;
    
}
