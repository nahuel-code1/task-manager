package com.example.taskmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.beans.StateResultRes;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.example.taskmanager.beans.AssignTaskUserReq;
import com.example.taskmanager.beans.LoginFormReq;
import com.example.taskmanager.beans.LoginRes;
import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.User;
import com.example.taskmanager.dao.TaskManagerDAO;
import com.example.taskmanager.service.TaskManagerService;
import com.example.util.PasswordUtils;
import com.ezample.taskmanager.enums.TaskStatus;

import java.util.Base64;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

	@Autowired
	private TaskManagerDAO taskManagerDAO;
	
	
	@Override
	public StateResultRes registerUser (User usuario) {
		StateResultRes response = new StateResultRes();
		response.setEstado(1);
		response.setMensaje("Se registro ha sido exitoso");
		
		if (taskManagerDAO.findByUserName(usuario.getNombreUsuario()) != null) {
			response.setEstado(0);
			response.setMensaje("Nombre de usuario invalido. Modifiquelo e intente nuevamente.");
			return response;
		}
		
		if (taskManagerDAO.findByEmail(usuario.getEmail()) != null) {
			response.setEstado(0);
			response.setMensaje("Email invalido. Modifiquelo e intente nuevamente.");
			return response;
		}
		
		byte[] salt = PasswordUtils.generateSalt();
        String storedPassword = PasswordUtils.hashPassword(usuario.getContraseña(), salt);
		usuario.setContraseña(storedPassword);
		
		if (taskManagerDAO.saveUser(usuario) < 1) {
			response.setEstado(0);
			response.setMensaje("Ha ocurrido un error al intentar crear su usuario.");
		}
		
		return response;
	}
	
	@Override
	public LoginRes login(LoginFormReq usuarioLogin) {
		LoginRes response = new LoginRes();
		StateResultRes stateResult = new StateResultRes();
		stateResult.setEstado(1);
		stateResult.setMensaje("Login exitoso");
		response.setStateResult(stateResult);
		
		User user = taskManagerDAO.findByUserName(usuarioLogin.getNombreUsuario());
		if (user == null) {
			stateResult.setEstado(0);
			stateResult.setMensaje("No existe el nombre de usuario ingresado");
			return response;
		}
		
		if (!verifyPassword(usuarioLogin.getContraseña(), user.getContraseña())) {
			stateResult.setEstado(0);
			stateResult.setMensaje("Contraseña incorrecta");
			return response;
		}
		
		response.setUser(taskManagerDAO.findByUserName(usuarioLogin.getNombreUsuario()));
		
		return response;
		
	}

	private boolean verifyPassword(String inputPassword, String storedPassword) {
        String[] parts = storedPassword.split(":");
        String storedSalt = parts[0];

        byte[] salt = Base64.getDecoder().decode(storedSalt);
        String inputHashedPassword = PasswordUtils.hashPassword(inputPassword, salt);

        return inputHashedPassword.equals(storedPassword);
    }
	
	@Override
	public StateResultRes createProject(Project project) {
		StateResultRes response = new StateResultRes();
		response.setEstado(1);
		response.setMensaje("Proyecto creado exitosamente");
		
		if (taskManagerDAO.createProject(project) == 0) {
			response.setEstado(0);
			response.setMensaje("Ha ocurrido un error al intentar crear el proyecto");
		}
	
		return response;
	}
	
	@Override
	public List<Project> getProjectsWithTasks(User user) {
		return taskManagerDAO.getProjectsWithTasks(user.getId());
	}
	
	@Override
	public StateResultRes createTask (Task task) {
		StateResultRes response = new StateResultRes();
		response.setEstado(1);
		response.setMensaje("Tarea creado exitosamente");
		task.setStatus(TaskStatus.PENDING);
		if (taskManagerDAO.createTask(task) == 0) {
			response.setEstado(0);
			response.setMensaje("Ha ocurrido un error al intentar crear la tarea");
		}
	
		return response;
	}
	
	@Override
	public StateResultRes updateTask (Long taskId, Task task) {
		StateResultRes response = new StateResultRes();
		response.setEstado(1);
		response.setMensaje("Tarea actualizada exitosamente");
		
		task.setId(taskId);
		if (taskManagerDAO.updateTask(task) == 0) {
			response.setEstado(0);
			response.setMensaje("Ha ocurrido un error al intentar actualizar la tarea");
		}
	
		return response;
	
	}
	
	@Override
	public StateResultRes deleteTask (Long taskId) {
		StateResultRes response = new StateResultRes();
		response.setEstado(1);
		response.setMensaje("Se ha eliminado la tarea");
		
		if (taskManagerDAO.deleteTask(taskId) == 0) {
			response.setEstado(0);
			response.setMensaje("Ha ocurrido un error al intentar eliminar la tarea");
		}
	
		return response;
	}

	@Override
	public StateResultRes updateTaskStatus (Long taskId, Task task) {
		StateResultRes response = new StateResultRes();
		response.setEstado(1);
		response.setMensaje("Estado de la tarea actualizada exitosamente");
		
		task.setId(taskId);
		if (taskManagerDAO.updateTaskStatus(task) == 0) {
			response.setEstado(0);
			response.setMensaje("Ha ocurrido un error al intentar actualizar el estado de la tarea");
		}
	
		return response;
	}
	
	@Override
	public List<TaskStatusQuantityRes> getTaskStatusQuantity(Project project) {
		return taskManagerDAO.getTaskStatusQuantity(project.getId());
	}
	
	@Override
	public StateResultRes assignTaskToUser(AssignTaskUserReq request) {
		StateResultRes response = new StateResultRes();
		response.setEstado(1);
		response.setMensaje("Asignacion de la tarea exitosa");
		
		if (taskManagerDAO.assignTaskToUser(request.getTaskId(), request.getUserId()) == 0) {
			response.setEstado(0);
			response.setMensaje("Ha ocurrido un error al asignar la tarea");
		}
	
		return response;
	}
	
	@Override
	public List<Task> getTasksAssignedToUser(User user) {
		return taskManagerDAO.getTasksAssignedToUser(user.getId());
	}
	
	@Override
	public List<Project> getProjectsByUserId(User user) {
		return taskManagerDAO.getProjectsByUserId(user.getId());
	}
	
}
