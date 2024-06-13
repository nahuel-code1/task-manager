package com.example.taskmanager.mockUtil;

import java.util.ArrayList;
import java.util.List;

import com.example.taskmanager.beans.LoginFormReq;
import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.StateResultRes;
import com.example.taskmanager.beans.Task;
import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.example.taskmanager.beans.User;
import com.ezample.taskmanager.enums.TaskStatus;

public class MockUtil {

	private MockUtil () {}
	
	public static User buildUser() {
		User testUser = new User();
        testUser.setId(1L);
        testUser.setNombreUsuario("usuario123");
        testUser.setEmail("usuario123@example.com");
        testUser.setNombreCompleto("Usuario de Prueba");
        testUser.setContraseña("password123");
        return testUser;
	}
	
	public static Project buildProject() {
		Project testProject = new Project();
        testProject.setId(1L);
        testProject.setName("Proyecto de Prueba");
        testProject.setUserId(1L);
        testProject.setTasks(new ArrayList<>());
        return testProject;
	}
	
	public static Task buildTask() {
		Task testTask = new Task();
        testTask.setId(1L);
        testTask.setTitle("Tarea de Prueba");
        testTask.setDescription("Descripción de la tarea de prueba");
        testTask.setStatus(TaskStatus.PENDING);
        testTask.setProjectId(1L);
        return testTask;
	}
	
	public static StateResultRes buildStateResultResOK() {
		StateResultRes expectedResult = new StateResultRes();
	    expectedResult.setEstado(1);
	    expectedResult.setMensaje("proceso finalizado con exito");
	    return expectedResult;
	}
	
	public static LoginFormReq buildLoginFormReq() {
		LoginFormReq loginRequest = new LoginFormReq();
	    loginRequest.setNombreUsuario("usuario123");
	    loginRequest.setContraseña("password123");
	    return loginRequest;
	}
	
	public static List<TaskStatusQuantityRes> buildTaskStatusQuantityResList() {
		List<TaskStatusQuantityRes> taskStatusList = new ArrayList<>();
	    TaskStatusQuantityRes statusRes = new TaskStatusQuantityRes();
	    statusRes.setQuantity(5);
	    statusRes.setStatus(TaskStatus.PENDING);
	    taskStatusList.add(statusRes);
	    return taskStatusList;
	}
	
}
