package com.example.taskmanager.service.impl.test;

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

import com.example.taskmanager.beans.*;
import com.example.taskmanager.dao.TaskManagerDAO;
import com.example.taskmanager.mockUtil.MockUtil;
import com.example.taskmanager.service.impl.TaskManagerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskManagerServiceImplTest {

	@Mock
    private TaskManagerDAO taskManagerDAO;

    @InjectMocks
    private TaskManagerServiceImpl service;

    private User testUser;
    private Project testProject;
    private Task testTask;

    @BeforeEach
    public void setUp() {
        testUser = MockUtil.buildUser();
        testProject = MockUtil.buildProject();
        testTask = MockUtil.buildTask();
    }

    @Test
    public void testRegisterUser() {
        when(taskManagerDAO.findByUserName(anyString())).thenReturn(null);
        when(taskManagerDAO.findByEmail(anyString())).thenReturn(null);
        when(taskManagerDAO.saveUser(any(User.class))).thenReturn(1);

        StateResultRes response = service.registerUser(testUser);

        assertNotNull(response);
    }

    @Test
    public void testLogin() {
        when(taskManagerDAO.findByUserName(anyString())).thenReturn(testUser);
        LoginRes response = service.login(MockUtil.buildLoginFormReq());
        assertNotNull(response);
    }

    @Test
    public void testCreateProject() {
        when(taskManagerDAO.createProject(any(Project.class))).thenReturn(1);

        StateResultRes response = service.createProject(testProject);

        assertNotNull(response);
    }

    @Test
    public void testGetProjectsWithTasks() {
        List<Project> projects = new ArrayList<>();
        projects.add(testProject);

        when(taskManagerDAO.getProjectsWithTasks(anyLong())).thenReturn(projects);

        List<Project> response = service.getProjectsWithTasks(testUser);

        assertNotNull(response);
    }

    @Test
    public void testCreateTask() {
        when(taskManagerDAO.createTask(any(Task.class))).thenReturn(1);

        StateResultRes response = service.createTask(testTask);

        assertNotNull(response);
    }

    @Test
    public void testUpdateTask() {
        Long taskId = 1L;
        when(taskManagerDAO.updateTask(any(Task.class))).thenReturn(1);

        StateResultRes response = service.updateTask(taskId, testTask);

        assertNotNull(response);
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;
        when(taskManagerDAO.deleteTask(anyLong())).thenReturn(1);

        StateResultRes response = service.deleteTask(taskId);

        assertNotNull(response);

    }

    @Test
    public void testUpdateTaskStatus() {
        Long taskId = 1L;
        when(taskManagerDAO.updateTaskStatus(any(Task.class))).thenReturn(1);

        StateResultRes response = service.updateTaskStatus(taskId, testTask);

        assertNotNull(response);

    }

    @Test
    public void testGetTaskStatusQuantity() {
        List<TaskStatusQuantityRes> taskStatusList = MockUtil.buildTaskStatusQuantityResList();

        when(taskManagerDAO.getTaskStatusQuantity(anyLong())).thenReturn(taskStatusList);

        List<TaskStatusQuantityRes> response = service.getTaskStatusQuantity(testProject);

        assertNotNull(response);

    }

    @Test
    public void testAssignTaskToUser() {
        when(taskManagerDAO.assignTaskToUser(anyLong(), anyLong())).thenReturn(1);

        AssignTaskUserReq assignRequest = new AssignTaskUserReq();
        assignRequest.setTaskId(1L);
        assignRequest.setUserId(2L);
        StateResultRes response = service.assignTaskToUser(assignRequest);

        assertNotNull(response);

    }

    @Test
    public void testGetTasksAssignedToUser() {
        List<Task> assignedTasks = new ArrayList<>();
        assignedTasks.add(testTask);

        when(taskManagerDAO.getTasksAssignedToUser(anyLong())).thenReturn(assignedTasks);

        List<Task> response = service.getTasksAssignedToUser(testUser);

        assertNotNull(response);

    }
    
}
