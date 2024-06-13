package com.example.taskmanager.dao.impl.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.taskmanager.beans.*;
import com.example.taskmanager.dao.impl.TaskManagerDAOImpl;
import com.example.taskmanager.mapper.ProjectTaskMapper;
import com.example.taskmanager.mapper.TaskStatusQuantityResMapper;
import com.example.taskmanager.mapper.UsuarioMapper;
import com.example.taskmanager.mockUtil.MockUtil;

@ExtendWith(MockitoExtension.class)
public class TaskManagerDAOImplTest {

	@Mock
    private JdbcTemplate jdbcSQLServer;
	
	@Mock
    private DataSource datasource;

    @InjectMocks
    private TaskManagerDAOImpl dao;

    private User testUser;
    private Project testProject;
    private Task testTask;

    @BeforeEach
    public void setUp() {
    	dao = new TaskManagerDAOImpl(datasource);
    	ReflectionTestUtils.setField(dao, "jdbcSQLServer", jdbcSQLServer);
        testUser = MockUtil.buildUser();
        testProject = MockUtil.buildProject();
        testTask = MockUtil.buildTask();
    }

    @Test
    public void testFindByUserName() {
        when(jdbcSQLServer.queryForObject(
        		anyString(),
        		any(UsuarioMapper.class),
        		anyString()
			)).thenReturn(testUser);
        User result = dao.findByUserName(testUser.getNombreUsuario());
        assertNotNull(result);
    }

    @Test
    public void testFindByUserName_Exception() {
        when(jdbcSQLServer.queryForObject(anyString(), any(UsuarioMapper.class), anyString()))
            .thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        User result = dao.findByUserName(testUser.getNombreUsuario());

        assertNull(result);
        verify(jdbcSQLServer, times(1)).queryForObject(anyString(), any(UsuarioMapper.class), anyString());
    }

    @Test
    public void testFindByEmail() {
        when(jdbcSQLServer.queryForObject(anyString(), any(UsuarioMapper.class), anyString()))
            .thenReturn(testUser);

        User result = dao.findByEmail(testUser.getEmail());

        assertNotNull(result);
        assertEquals(testUser.getEmail(), result.getEmail());
        verify(jdbcSQLServer, times(1)).queryForObject(anyString(), any(UsuarioMapper.class), anyString());
    }

    @Test
    public void testFindByEmail_Exception() {
        when(jdbcSQLServer.queryForObject(anyString(), any(UsuarioMapper.class), anyString()))
            .thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        User result = dao.findByEmail(testUser.getEmail());

        assertNull(result);
        verify(jdbcSQLServer, times(1)).queryForObject(anyString(), any(UsuarioMapper.class), anyString());
    }

    @Test
    public void testSaveUser() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(1);

        int result = dao.saveUser(testUser);

        assertEquals(1, result);
    }

    @Test
    public void testSaveUser_Exception() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyString(), anyString(), anyString())).thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        int result = dao.saveUser(testUser);

        assertEquals(0, result);
    }

    @Test
    public void testCreateProject() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyLong())).thenReturn(1);

        int result = dao.createProject(testProject);

        assertEquals(1, result);
    }

    @Test
    public void testCreateProject_Exception() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyLong())).thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        int result = dao.createProject(testProject);

        assertEquals(0, result);
    }

    @Test
    public void testGetProjectsWithTasks() {
        List<Project> projects = Collections.singletonList(testProject);
        when(jdbcSQLServer.query(anyString(), any(ProjectTaskMapper.class), anyLong())).thenReturn(projects);

        List<Project> result = dao.getProjectsWithTasks(testUser.getId());

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetProjectsWithTasks_Exception() {
        when(jdbcSQLServer.query(anyString(), any(ProjectTaskMapper.class), anyLong())).thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        List<Project> result = dao.getProjectsWithTasks(testUser.getId());

        assertNull(result);
        verify(jdbcSQLServer, times(1)).query(anyString(), any(ProjectTaskMapper.class), anyLong());
    }

    @Test
    public void testCreateTask() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyString(), anyString(), anyLong())).thenReturn(1);

        int result = dao.createTask(testTask);

        assertEquals(1, result);
    }

    @Test
    public void testCreateTask_Exception() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyString(), anyString(), anyLong())).thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        int result = dao.createTask(testTask);

        assertEquals(0, result);
    }

    @Test
    public void testUpdateTask() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyString(), anyLong())).thenReturn(1);

        int result = dao.updateTask(testTask);

        assertEquals(1, result);
    }

    @Test
    public void testUpdateTask_Exception() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyString(), anyLong())).thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        int result = dao.updateTask(testTask);

        assertEquals(0, result);
    }

    @Test
    public void testDeleteTask() {
        when(jdbcSQLServer.update(anyString(), anyLong())).thenReturn(1);

        int result = dao.deleteTask(testTask.getId());

        assertEquals(1, result);
    }

    @Test
    public void testDeleteTask_Exception() {
        when(jdbcSQLServer.update(anyString(), anyLong())).thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        int result = dao.deleteTask(testTask.getId());

        assertEquals(0, result);
    }

    @Test
    public void testUpdateTaskStatus() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyLong())).thenReturn(1);

        int result = dao.updateTaskStatus(testTask);

        assertEquals(1, result);
    }

    @Test
    public void testUpdateTaskStatus_Exception() {
        when(jdbcSQLServer.update(anyString(), anyString(), anyLong())).thenThrow(new DataAccessException("Error de acceso a datos") {private static final long serialVersionUID = 1L;});

        int result = dao.updateTaskStatus(testTask);

        assertEquals(0, result);
    }

    @Test
    public void testGetTaskStatusQuantity() {
        List<TaskStatusQuantityRes> statusList = MockUtil.buildTaskStatusQuantityResList();
        when(jdbcSQLServer.query(anyString(), any(TaskStatusQuantityResMapper.class), anyLong())).thenReturn(statusList);

        List<TaskStatusQuantityRes> result = dao.getTaskStatusQuantity(testProject.getId());

        assertNotNull(result);
        assertEquals(1, result.size());
    }

}
