package com.example.taskmanager.beans;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Project {

	Long id;
    String name;
    @NotNull
    Long userId;
    List<Task> tasks;
    
}
