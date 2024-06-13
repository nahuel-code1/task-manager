package com.example.taskmanager.controller.decorator.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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

import com.example.taskmanager.beans.AssignTaskUserReq;
import com.example.taskmanager.beans.LoginFormReq;
import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.StateResultRes;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.example.taskmanager.beans.User;
import com.example.taskmanager.controller.decorator.TaskManagerControllerDecorator;
import com.example.taskmanager.controller.impl.TaskManagerControllerImpl;
import com.example.taskmanager.mockUtil.MockUtil;

@ExtendWith(MockitoExtension.class)
public class TaskManagerControllerDecoratorTest {

	@Mock
    private TaskManagerControllerImpl taskManagerCtrlImpl;
	
	@InjectMocks
    private TaskManagerControllerDecorator controller;

	@BeforeEach
    public void setUp() {
		
	}
	
	@Test
    public void testRegisterUser() {
        when(taskManagerCtrlImpl.registerUser(any(User.class))).thenReturn(ResponseEntity.ok(MockUtil.buildStateResultResOK()));

        ResponseEntity<StateResultRes> response = controller.registerUser(MockUtil.buildUser());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MockUtil.buildStateResultResOK(), response.getBody());
    }

	@Test
    public void testLogin() {
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerCtrlImpl.login(any(LoginFormReq.class))).thenReturn(ResponseEntity.ok(expectedResult));

        ResponseEntity<StateResultRes> response = controller.login(MockUtil.buildLoginFormReq());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }
	
	@Test
    public void testCreateProject() {
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerCtrlImpl.createProject(any(Project.class))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(expectedResult));

        ResponseEntity<StateResultRes> response = controller.createProject(MockUtil.buildProject());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }
	
	@Test
    public void testGetProjectsWithTasks() {
        List<Project> projects = new ArrayList<>();
        projects.add(MockUtil.buildProject());
        ResponseEntity<List<Project>> expectedResponse = ResponseEntity.ok(projects);

        when(taskManagerCtrlImpl.getProjectsWithTasks(any(User.class))).thenReturn(expectedResponse);

        ResponseEntity<List<Project>> response = controller.getProjectsWithTasks(MockUtil.buildUser());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projects, response.getBody());
    }

    @Test
    public void testCreateTask() {
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerCtrlImpl.createTask(any(Task.class))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(expectedResult));

        ResponseEntity<StateResultRes> response = controller.createTask(MockUtil.buildTask());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testUpdateTask() {
        Long taskId = 1L;
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerCtrlImpl.updateTask(eq(taskId), any(Task.class))).thenReturn(ResponseEntity.ok(expectedResult));

        ResponseEntity<StateResultRes> response = controller.updateTask(taskId, MockUtil.buildTask());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerCtrlImpl.deleteTask(eq(taskId))).thenReturn(ResponseEntity.ok(expectedResult));

        ResponseEntity<StateResultRes> response = controller.deleteTask(taskId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testUpdateTaskStatus() {
        Long taskId = 1L;
        StateResultRes expectedResult = MockUtil.buildStateResultResOK();

        when(taskManagerCtrlImpl.updateTaskStatus(eq(taskId), any(Task.class))).thenReturn(ResponseEntity.ok(expectedResult));

        ResponseEntity<StateResultRes> response = controller.updateTaskStatus(taskId, MockUtil.buildTask());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testGetTaskStatusQuantity() {
        List<TaskStatusQuantityRes> taskStatusList = MockUtil.buildTaskStatusQuantityResList();
        ResponseEntity<List<TaskStatusQuantityRes>> expectedResponse = ResponseEntity.ok(taskStatusList);

        when(taskManagerCtrlImpl.getTaskStatusQuantity(any(Project.class))).thenReturn(expectedResponse);

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

        when(taskManagerCtrlImpl.assignTaskToUser(any(AssignTaskUserReq.class))).thenReturn(ResponseEntity.ok(expectedResult));

        ResponseEntity<StateResultRes> response = controller.assignTaskToUser(assignRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testGetTasksAssignedToUser() {
        List<Task> assignedTasks = new ArrayList<>();
        assignedTasks.add(MockUtil.buildTask());
        ResponseEntity<List<Task>> expectedResponse = ResponseEntity.ok(assignedTasks);

        when(taskManagerCtrlImpl.getTasksAssignedToUser(any(User.class))).thenReturn(expectedResponse);

        ResponseEntity<List<Task>> response = controller.getTasksAssignedToUser(MockUtil.buildUser());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignedTasks, response.getBody());
    }


}
