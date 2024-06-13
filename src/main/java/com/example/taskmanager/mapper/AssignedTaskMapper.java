package com.example.taskmanager.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.taskmanager.beans.Task;
import com.ezample.taskmanager.enums.TaskStatus;

public class AssignedTaskMapper implements RowMapper<Task> {

	@Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setStatus(TaskStatus.valueOf(rs.getString("status")));
        return task;
    }
	
}
