package com.example.taskmanager.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.taskmanager.beans.StateResultRes;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.example.taskmanager.beans.AssignTaskUserReq;
import com.example.taskmanager.beans.LoginFormReq;
import com.example.taskmanager.beans.LoginRes;
import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.User;
import com.example.taskmanager.controller.decorator.TaskManagerController;
import com.example.taskmanager.service.TaskManagerService;

@Component
public class TaskManagerControllerImpl implements TaskManagerController {

	@Autowired
    private TaskManagerService taskManagerService;
	
	@Override
	public ResponseEntity<StateResultRes> registerUser(User usuario){
		StateResultRes response = taskManagerService.registerUser(usuario);
		
		if (response.getEstado().equals(0))
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<LoginRes> login(LoginFormReq usuarioLogin) {
		LoginRes response = taskManagerService.login(usuarioLogin);
		
		if (response.getStateResult().getEstado().equals(0))
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	
	@Override
	public ResponseEntity<StateResultRes> createProject(Project project) {
		StateResultRes response = taskManagerService.createProject(project);
		
		if (response.getEstado().equals(0))
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<List<Project>> getProjectsWithTasks(User user) {
		List<Project> response = taskManagerService.getProjectsWithTasks(user);
		
		if (response == null)
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<StateResultRes> createTask(Task task) {
		StateResultRes response = taskManagerService.createTask(task);
		
		if (response.getEstado().equals(0))
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<StateResultRes> updateTask(Long taskId, Task task) {
		StateResultRes response = taskManagerService.updateTask(taskId, task);
		
		if (response.getEstado().equals(0))
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<StateResultRes> deleteTask(Long taskId) {
		StateResultRes response = taskManagerService.deleteTask(taskId);
		
		if (response.getEstado().equals(0))
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<StateResultRes> updateTaskStatus(Long taskId, Task task) {
		StateResultRes response = taskManagerService.updateTaskStatus(taskId, task);
		
		if (response.getEstado().equals(0))
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<List<TaskStatusQuantityRes>> getTaskStatusQuantity(Project project) {
		List<TaskStatusQuantityRes> response = taskManagerService.getTaskStatusQuantity(project);
		
		if (response == null)
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<StateResultRes> assignTaskToUser(AssignTaskUserReq request) {
		StateResultRes response = taskManagerService.assignTaskToUser(request);
		
		if (response.getEstado().equals(0))
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<List<Task>> getTasksAssignedToUser(@RequestBody User user) {
		List<Task> response = taskManagerService.getTasksAssignedToUser(user);
		
		if (response == null)
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<List<Project>> getProjectsByUserId(User user){
		List<Project> response = taskManagerService.getProjectsByUserId(user);
		
		if (response == null)
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<List<Task>> getTasksByUserId(User user){
		List<Task> response = taskManagerService.getTasksByUserId(user);
		
		if (response == null)
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return ResponseEntity.ok(response);
	}
}
