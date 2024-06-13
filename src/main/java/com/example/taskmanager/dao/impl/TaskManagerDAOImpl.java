package com.example.taskmanager.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.example.taskmanager.beans.User;
import com.example.taskmanager.dao.TaskManagerDAO;
import com.example.taskmanager.mapper.AssignedTaskMapper;
import com.example.taskmanager.mapper.ProjectTaskMapper;
import com.example.taskmanager.mapper.TaskStatusQuantityResMapper;
import com.example.taskmanager.mapper.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class TaskManagerDAOImpl implements TaskManagerDAO {

	private final JdbcTemplate jdbcSQLServer;
	
	private static final Logger logger = LoggerFactory.getLogger(TaskManagerDAOImpl.class);
	
	public TaskManagerDAOImpl (@Qualifier("dsSqlServer") DataSource dsSQLServer) {
		jdbcSQLServer = new JdbcTemplate(dsSQLServer);
	}
	
	@Override
	public User findByUserName(String nombreUsuario) {
		try {
			return jdbcSQLServer.queryForObject(
				    SQLQueries.FIND_BY_USER,
				    new UsuarioMapper(),
				    nombreUsuario
				);
		} catch (DataAccessException e) {
		    logger.error("findByUserName: Error de acceso a datos al buscar un usuario por nombre de usuario", e);
			return null;
		}
	}
	
	@Override
	public User findByEmail(String email) {
		try {
			return jdbcSQLServer.queryForObject(
				    SQLQueries.FIND_BY_EMAIL,
				    new UsuarioMapper(),
				    email
				);
		} catch (DataAccessException e) {
		    logger.error("findByEmail: Error de acceso a datos al buscar un usuario por email", e);
			return null;
		}
	}
	
	@Override
	public int saveUser(User usuario) {
		try {
			return jdbcSQLServer.update(SQLQueries.REGISTER_USER, usuario.getNombreCompleto(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getContraseña());
		} catch (DataAccessException e) {
		    logger.error("saveUser: Error de acceso a datos al crear un usuario", e);
			return 0;
		}
    }
	
	@Override
	public int createProject(Project project) {
		try {
			return jdbcSQLServer.update(SQLQueries.CREATE_PROJECT, project.getName(), project.getUserId());
		} catch (DataAccessException e) {
		    logger.error("createProject: Error de acceso a datos al crear un proyecto", e);
			return 0;
		}
    }
	
	@Override
	public List<Project> getProjectsWithTasks(Long userId) {
		try {
			return jdbcSQLServer.query(
				    SQLQueries.GET_PROJECTS_WITH_TASKS,
				    new ProjectTaskMapper(),
				    userId);
		} catch (DataAccessException e) {
		    logger.error("getProjectsWithTasks: Error de acceso a datos al recuperar proyectos", e);
		    return null;
		}		
	}
	
	@Override
	public int createTask (Task task) {
		try {
			return jdbcSQLServer.update(SQLQueries.CREATE_TASK, task.getTitle(), task.getDescription(), 
					task.getStatus().toString(), task.getProjectId());
		} catch (DataAccessException e) {
		    logger.error("createTask: Error de acceso a datos al crear un proyecto", e);
			return 0;
		}
	}
	
	@Override
	public int updateTask (Task task) {
		try {
			return jdbcSQLServer.update(
					SQLQueries.UPDATE_TASK, 
					task.getTitle(), task.getDescription(), task.getId());
		} catch (DataAccessException e) {
		    logger.error("updateTask: Error de acceso a datos al actualizar la tarea", e);
			return 0;
		}
	}
	
	@Override
	public int deleteTask (Long taskId) {
		try {
			return jdbcSQLServer.update(SQLQueries.DELETE_TASK, taskId);
		} catch (DataAccessException e) {
		    logger.error("deleteTask: Error de acceso a datos al recuperar proyectos", e);
			return 0;
		}
	}
	
	@Override
	public int updateTaskStatus (Task task) {
		try {
			return jdbcSQLServer.update(
					SQLQueries.UPDATE_TASK_STATUS, 
					task.getStatus().toString(), task.getId());
		} catch (DataAccessException e) {
		    logger.error("updateTaskStatus: Error de acceso a datos al actualizar el estado de la tarea", e);
			return 0;
		}
	}
	
	@Override
	public List<TaskStatusQuantityRes> getTaskStatusQuantity(Long projectId) {
		try {
			return jdbcSQLServer.query(
				    SQLQueries.GET_TASK_STATUS_QUANTITY,
				    new TaskStatusQuantityResMapper(),
				    projectId);
		} catch (DataAccessException e) {
		    logger.error("getTaskStatusQuantity: Error de acceso a datos al recuperar la cantidad de estados de las tareas", e);
		    return null;
		}
	}
	
	@Override
    public int assignTaskToUser(Long taskId, Long userId) {
		try {
			return jdbcSQLServer.update(SQLQueries.ASSIGN_TASK_TO_USER, taskId, userId);
		} catch (DataAccessException e) {
		    logger.error("assignTaskToUser: Error de acceso a datos al asiganrle una tarea a un usuario", e);
		    return 0;
		}
    }
	
	@Override
    public List<Task> getTasksAssignedToUser(Long userId) {
		try {
			return jdbcSQLServer.query(SQLQueries.GET_TASKS_ASSIGNED_TO_USER, new AssignedTaskMapper(), userId);
		} catch (DataAccessException e) {
		    logger.error("getTasksAssignedToUser: Error de acceso a datos al recuperar las tareas asignadas a un usuario", e);
		    return null;
		}
    }
	
}
