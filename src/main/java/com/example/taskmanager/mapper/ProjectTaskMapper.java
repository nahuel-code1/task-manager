package com.example.taskmanager.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.example.taskmanager.beans.Project;
import com.example.taskmanager.beans.Task;
import com.ezample.taskmanager.enums.TaskStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectTaskMapper implements RowMapper<Project> {

    private static final Logger logger = LoggerFactory.getLogger(ProjectTaskMapper.class);

	@Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<Long, Project> projectMap = new HashMap<>();
        Project project = projectMap.computeIfAbsent(rs.getLong("project_id"), id -> {
            Project p = new Project();
            try {
                p.setId(id);
                p.setName(rs.getString("project_name"));
                p.setUserId(rs.getLong("user_id"));
                p.setTasks(new ArrayList<>());
            } catch (SQLException e) {
                logger.error("Error mapeando fila a proyecto", e);
                throw new RuntimeException("Error mapeando fila a proyecto", e);
            }
            return p;
        });

        if (rs.getLong("task_id") != 0) {
            Task task = new Task();
            task.setId(rs.getLong("task_id"));
            task.setTitle(rs.getString("task_title"));
            task.setDescription(rs.getString("task_description"));
            task.setStatus(TaskStatus.valueOf(rs.getString("task_status")));
            task.setProjectId(rs.getLong("project_id"));
            project.getTasks().add(task);
        }

        return project;
    }
	
}
