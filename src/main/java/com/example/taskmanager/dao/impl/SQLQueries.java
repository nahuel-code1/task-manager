package com.example.taskmanager.dao.impl;

public final class SQLQueries {

	private SQLQueries () {}
	
	// User Queries
	
	public static final String FIND_BY_USER = 
			"SELECT id, full_name, username, email, password FROM users WHERE username = ?";
	
	public static final String FIND_BY_EMAIL = 
			"SELECT id, full_name, username, email, password FROM users WHERE email = ?";

	public static final String REGISTER_USER = 
			"INSERT INTO users (full_name, username, email, password) VALUES (?, ?, ?, ?)";

	// Project Queries
	public static final String CREATE_PROJECT = 
			"INSERT INTO projects (name, user_id) VALUES (?, ?)";

	public static final String GET_PROJECTS_WITH_TASKS = 
			"SELECT p.id as project_id, p.name as project_name, p.user_id, " +
            "t.id as task_id, t.title as task_title, t.description as task_description, t.status as task_status " +
            "FROM projects p LEFT JOIN tasks t ON p.id = t.project_id where p.user_id = ?";
	
	// Task Queries
	public static final String CREATE_TASK = 
			"INSERT INTO tasks (title, description, status, project_id) VALUES (?, ?, ?, ?)";
	
	public static final String UPDATE_TASK = 
			"UPDATE tasks SET title = ?, description = ? WHERE id = ?";
	
	public static final String DELETE_TASK = 
			"DELETE FROM tasks WHERE id = ?";
	
	public static final String UPDATE_TASK_STATUS = 
			"UPDATE tasks SET status = ? WHERE id = ?";
	
	public static final String GET_TASK_STATUS_QUANTITY = 
			"SELECT status, COUNT(*) AS quantity FROM tasks WHERE project_id = ? GROUP BY status ORDER BY quantity desc;";
	
	public static final String ASSIGN_TASK_TO_USER = 
			"INSERT INTO assignments (task_id, user_id) VALUES (?, ?)";
	
	public static final String GET_TASKS_ASSIGNED_TO_USER = 
			"SELECT t.id, t.title, t.description, t.status "
			+ "FROM tasks t "
			+ "INNER JOIN assignments a ON t.id = a.task_id "
			+ "WHERE a.user_id = ?";
	

}
