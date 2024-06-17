package com.example.taskmanager.controller.decorator.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.taskmanager.beans.*;
import com.example.taskmanager.controller.impl.TaskManagerControllerImpl;
import com.example.taskmanager.mockUtil.MockUtil;
import com.example.taskmanager.service.TaskManagerService;

@ExtendWith(MockitoExtension.class)
public class TaskManagerControllerImplTest {

	@Mock
    private TaskManagerService taskManagerService;

    @InjectMocks
    private TaskManagerControllerImpl controller;

    @BeforeEach
    public void setUp() {}

    @Test
    public void testRegisterUser() {
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerService.registerUser(any(User.class))).thenReturn(expectedResult);

        ResponseEntity<StateResultRes> response = controller.registerUser(MockUtil.buildUser());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testLogin() {
		LoginRes expectedResult = MockUtil.buildLoginRes();

        when(taskManagerService.login(any(LoginFormReq.class))).thenReturn(expectedResult);

        ResponseEntity<LoginRes> response = controller.login(MockUtil.buildLoginFormReq());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testCreateProject() {
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerService.createProject(any(Project.class))).thenReturn(expectedResult);

        ResponseEntity<StateResultRes> response = controller.createProject(MockUtil.buildProject());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testGetProjectsWithTasks() {
        List<Project> projects = new ArrayList<>();
        projects.add(MockUtil.buildProject());

        when(taskManagerService.getProjectsWithTasks(any(User.class))).thenReturn(projects);

        ResponseEntity<List<Project>> response = controller.getProjectsWithTasks(MockUtil.buildUser());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projects, response.getBody());
    }

    @Test
    public void testCreateTask() {
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerService.createTask(any(Task.class))).thenReturn(expectedResult);

        ResponseEntity<StateResultRes> response = controller.createTask(MockUtil.buildTask());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testUpdateTask() {
        Long taskId = 1L;
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerService.updateTask(eq(taskId), any(Task.class))).thenReturn(expectedResult);

        ResponseEntity<StateResultRes> response = controller.updateTask(taskId, MockUtil.buildTask());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerService.deleteTask(eq(taskId))).thenReturn(expectedResult);

        ResponseEntity<StateResultRes> response = controller.deleteTask(taskId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testUpdateTaskStatus() {
        Long taskId = 1L;
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerService.updateTaskStatus(eq(taskId), any(Task.class))).thenReturn(expectedResult);

        ResponseEntity<StateResultRes> response = controller.updateTaskStatus(taskId, MockUtil.buildTask());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testGetTaskStatusQuantity() {
    	List<TaskStatusQuantityRes> taskStatusList = MockUtil.buildTaskStatusQuantityResList();

        when(taskManagerService.getTaskStatusQuantity(any(Project.class))).thenReturn(taskStatusList);

        ResponseEntity<List<TaskStatusQuantityRes>> response = controller.getTaskStatusQuantity(MockUtil.buildProject());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskStatusList, response.getBody());
    }

    @Test
    public void testAssignTaskToUser() {
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();
        AssignTaskUserReq assignRequest = new AssignTaskUserReq();
        assignRequest.setTaskId(1L);
        assignRequest.setUserId(2L);
        
        when(taskManagerService.assignTaskToUser(any(AssignTaskUserReq.class))).thenReturn(expectedResult);

        ResponseEntity<StateResultRes> response = controller.assignTaskToUser(assignRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testGetTasksAssignedToUser() {
    	List<Task> assignedTasks = new ArrayList<>();
        assignedTasks.add(MockUtil.buildTask());

        when(taskManagerService.getTasksAssignedToUser(any(User.class))).thenReturn(assignedTasks);

        ResponseEntity<List<Task>> response = controller.getTasksAssignedToUser(MockUtil.buildUser());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignedTasks, response.getBody());
    }
    
}
