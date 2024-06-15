package com.example.taskmanager.service;

import com.example.taskmanager.beans.StateResultRes;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;

import java.util.List;

import com.example.taskmanager.beans.AssignTaskUserReq;
import com.example.taskmanager.beans.LoginFormReq;
import com.example.taskmanager.beans.LoginRes;
import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.User;

public interface TaskManagerService {

	StateResultRes registerUser(User usuario);
	
	LoginRes login(LoginFormReq usuarioLogin);

	StateResultRes createProject(Project project);
	
	List<Project> getProjectsWithTasks(User user);
	
	StateResultRes createTask (Task task);
	
	StateResultRes updateTask (Long taskId, Task task);

	StateResultRes deleteTask (Long taskId);

	StateResultRes updateTaskStatus (Long taskId, Task task);

	List<TaskStatusQuantityRes> getTaskStatusQuantity(Project project);

	StateResultRes assignTaskToUser(AssignTaskUserReq request);
	
	List<Task> getTasksAssignedToUser(User user);

	List<Project> getProjectsByUserId(User user);
	
}
