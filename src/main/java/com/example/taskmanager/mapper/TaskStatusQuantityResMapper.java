package com.example.taskmanager.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.taskmanager.beans.TaskStatusQuantityRes;
import com.ezample.taskmanager.enums.TaskStatus;

public class TaskStatusQuantityResMapper implements RowMapper<TaskStatusQuantityRes> {

	@Override
    public TaskStatusQuantityRes mapRow(ResultSet rs, int rowNum) throws SQLException {
		TaskStatusQuantityRes res = new TaskStatusQuantityRes();
		res.setStatus(TaskStatus.valueOf(rs.getString("status")));
		res.setQuantity(rs.getInt("quantity"));
        return res;
    }
	
}
