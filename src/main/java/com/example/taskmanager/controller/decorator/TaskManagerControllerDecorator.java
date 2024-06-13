package com.example.taskmanager.controller.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
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

	/**
     * Registra un nuevo usuario en el sistema.
     * @param user El objeto usuario que contiene los detalles del usuario.
     * @return ResponseEntity con el resultado del proceso de registro.
     */
    @PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Registrar un nuevo usuario")
    @Override
    public ResponseEntity<StateResultRes> registerUser(@RequestBody User user) {
    	return taskManagerCtrlImpl.registerUser(user);
    }
    
    /**
     * Autentica a un usuario y le permite iniciar sesión.
     * @param usuarioLogin El formulario de inicio de sesión que contiene el nombre de usuario y la contraseña.
     * @return ResponseEntity con el resultado del proceso de inicio de sesión.
     */
    @PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Iniciar sesión de un usuario")
    @Override
    public ResponseEntity<StateResultRes> login(@RequestBody LoginFormReq usuarioLogin) {
    	return taskManagerCtrlImpl.login(usuarioLogin);
    }
    
    
    /**
     * Crea un nuevo proyecto.
     * @param project El objeto proyecto que contiene los detalles del proyecto.
     * @return ResponseEntity con el resultado del proceso de creación del proyecto.
     */
    @PostMapping(value = "/create/project", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Crear un nuevo proyecto")
    public ResponseEntity<StateResultRes> createProject(@RequestBody Project project) {
    	return taskManagerCtrlImpl.createProject(project);
    }
    
    /**
     * Recupera todos los proyectos junto con sus tareas para un usuario específico.
     * @param user El objeto usuario para filtrar los proyectos.
     * @return ResponseEntity con una lista de proyectos y sus tareas.
     */
    @PostMapping(value = "/project/task", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Obtener proyectos con tareas para un usuario")
    public ResponseEntity<List<Project>> getProjectsWithTasks(@RequestBody User user) {
    	return taskManagerCtrlImpl.getProjectsWithTasks(user);
    }
    
    /**
     * Crea una nueva tarea dentro de un proyecto.
     * @param task El objeto tarea que contiene los detalles de la tarea.
     * @return ResponseEntity con el resultado del proceso de creación de la tarea.
     */
    @PostMapping(value = "/create/task", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Crear una nueva tarea")
    public ResponseEntity<StateResultRes> createTask(@RequestBody Task task) {
    	return taskManagerCtrlImpl.createTask(task);
    }
    
    /**
     * Actualiza los detalles de una tarea existente.
     * @param taskId El ID de la tarea a actualizar.
     * @param task El objeto tarea que contiene los detalles actualizados de la tarea.
     * @return ResponseEntity con el resultado del proceso de actualización de la tarea.
     */
    @PutMapping(value = "/modify/{taskId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Actualizar una tarea existente")
    public ResponseEntity<StateResultRes> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
    	return taskManagerCtrlImpl.updateTask(taskId, task);
    }
    
    /**
     * Elimina una tarea por su ID.
     * @param taskId El ID de la tarea a eliminar.
     * @return ResponseEntity con el resultado del proceso de eliminación de la tarea.
     */
    @DeleteMapping(value = "/{taskId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Eliminar una tarea")
    public ResponseEntity<StateResultRes> deleteTask(@PathVariable Long taskId) {
    	return taskManagerCtrlImpl.deleteTask(taskId);
    }
	
    /**
     * Actualiza el estado de una tarea existente.
     * @param taskId El ID de la tarea cuyo estado se va a actualizar.
     * @param task El objeto tarea que contiene el estado actualizado.
     * @return ResponseEntity con el resultado del proceso de actualización del estado de la tarea.
     */
    @PutMapping(value = "/modify/status/{taskId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Actualizar el estado de una tarea")
    public ResponseEntity<StateResultRes> updateTaskStatus(@PathVariable Long taskId, @RequestBody Task task) {
    	return taskManagerCtrlImpl.updateTaskStatus(taskId, task);
    }
    
    /**
     * Recupera la cantidad de tareas en diferentes estados para un proyecto específico.
     * @param project El objeto proyecto para filtrar las tareas.
     * @return ResponseEntity con una lista de cantidades de estados de tareas.
     */
    @PostMapping(value = "/status/quantity", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Obtener cantidad de estados de tareas para un proyecto")
    public ResponseEntity<List<TaskStatusQuantityRes>> getTaskStatusQuantity(@RequestBody Project project) {
    	return taskManagerCtrlImpl.getTaskStatusQuantity(project);
    }
    
    /**
     * Asigna una tarea a un usuario.
     * @param request El objeto de solicitud que contiene el ID de la tarea y el ID del usuario.
     * @return ResponseEntity con el resultado del proceso de asignación de la tarea.
     */
    @PostMapping(value = "/assign/task", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Asignar una tarea a un usuario")
    public ResponseEntity<StateResultRes> assignTaskToUser(@RequestBody AssignTaskUserReq request) {
    	return taskManagerCtrlImpl.assignTaskToUser(request);
    }
    
    /**
     * Recupera todas las tareas asignadas a un usuario específico.
     * @param user El objeto usuario para filtrar las tareas asignadas.
     * @return ResponseEntity con una lista de tareas asignadas al usuario.
     */
    @PostMapping(value = "/assigned/task", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    @Operation(summary = "Obtener tareas asignadas a un usuario")
    public ResponseEntity<List<Task>> getTasksAssignedToUser(@RequestBody User user) {
    	return taskManagerCtrlImpl.getTasksAssignedToUser(user);
    }
    
}
