package com.example.taskmanager.controller.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.taskmanager.beans.StateResultRes;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.example.taskmanager.beans.AssignTaskUserReq;
import com.example.taskmanager.beans.LoginFormReq;
import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.User;
import com.example.taskmanager.controller.impl.TaskManagerControllerImpl;

@RestController
@RequestMapping("/taskManager")
@Tag(name = "manager task resources")
public class TaskManagerControllerDecorator implements TaskManagerController {

	@Autowired
    private TaskManagerControllerImpl taskManagerCtrlImpl;

    @PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<StateResultRes> registerUser(@RequestBody User user) {
    	return taskManagerCtrlImpl.registerUser(user);
    }
    
    @PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<StateResultRes> login(@RequestBody LoginFormReq usuarioLogin) {
    	return taskManagerCtrlImpl.login(usuarioLogin);
    }
    
    @PostMapping(value = "/create/project", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<StateResultRes> createProject(@RequestBody Project project) {
    	return taskManagerCtrlImpl.createProject(project);
    }
    
    @PostMapping(value = "/project/task", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<List<Project>> getProjectsWithTasks(@RequestBody User user) {
    	return taskManagerCtrlImpl.getProjectsWithTasks(user);
    }
    
    @PostMapping(value = "/create/task", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<StateResultRes> createTask(@RequestBody Task task) {
    	return taskManagerCtrlImpl.createTask(task);
    }
    
    @PutMapping(value = "/modify/{taskId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<StateResultRes> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
    	return taskManagerCtrlImpl.updateTask(taskId, task);
    }
    
    @DeleteMapping(value = "/{taskId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<StateResultRes> deleteTask(@PathVariable Long taskId) {
    	return taskManagerCtrlImpl.deleteTask(taskId);
    }
	
    @PutMapping(value = "/modify/status/{taskId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<StateResultRes> updateTaskStatus(@PathVariable Long taskId, @RequestBody Task task) {
    	return taskManagerCtrlImpl.updateTaskStatus(taskId, task);
    }
    
    @PostMapping(value = "/status/quantity", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<List<TaskStatusQuantityRes>> getTaskStatusQuantity(@RequestBody Project project) {
    	return taskManagerCtrlImpl.getTaskStatusQuantity(project);
    }
    
    @PostMapping(value = "/assign/task", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<StateResultRes> assignTaskToUser(@RequestBody AssignTaskUserReq request) {
    	return taskManagerCtrlImpl.assignTaskToUser(request);
    }
    
    @PostMapping(value = "/assigned/task", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<List<Task>> getTasksAssignedToUser(@RequestBody User user) {
    	return taskManagerCtrlImpl.getTasksAssignedToUser(user);
    }
    
}
