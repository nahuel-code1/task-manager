package com.example.taskmanager.dao;

import java.util.List;

import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.example.taskmanager.beans.User;

public interface TaskManagerDAO {

	User findByUserName(String nombreUsuario);
	
	User findByEmail(String email);
	
	int saveUser(User usuario);
	
	int createProject(Project project);
	
	List<Project> getProjectsWithTasks(Long userId);
	
	int createTask (Task task);
	
	int updateTask (Task task);

	int deleteTask (Long taskId);

	int updateTaskStatus (Task task);
	
	List<TaskStatusQuantityRes> getTaskStatusQuantity(Long projectId);
	
	int assignTaskToUser(Long taskId, Long userId);
	
	List<Task> getTasksAssignedToUser(Long userId);

	List<Project> getProjectsByUserId(Long userId);
	
	List<Task> getTasksByUserId(Long userId);
	
}
