package com.example.taskmanager.controller.decorator;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.taskmanager.beans.StateResultRes;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.example.taskmanager.beans.AssignTaskUserReq;
import com.example.taskmanager.beans.LoginFormReq;
import com.example.taskmanager.beans.LoginRes;
import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.User;

public interface TaskManagerController {

	ResponseEntity<StateResultRes> registerUser(User usuario);
	
	ResponseEntity<LoginRes> login(LoginFormReq usuarioLogin);

	ResponseEntity<StateResultRes> createProject(Project project);
	
	ResponseEntity<List<Project>> getProjectsWithTasks(User user);
	
	ResponseEntity<StateResultRes> createTask(Task task);
	
	ResponseEntity<StateResultRes> updateTask(Long taskId, Task task);

	ResponseEntity<StateResultRes> deleteTask(Long taskId);
	
	ResponseEntity<StateResultRes> updateTaskStatus(Long taskId, Task task);

	ResponseEntity<List<TaskStatusQuantityRes>> getTaskStatusQuantity(Project project);
	
	ResponseEntity<StateResultRes> assignTaskToUser(AssignTaskUserReq request);
	
	ResponseEntity<List<Task>> getTasksAssignedToUser(User user);

	ResponseEntity<List<Project>> getProjectsByUserId(User user);
	
	ResponseEntity<List<Task>> getTasksByUserId(User user);

}
