package com.example.taskmanager.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.taskmanager.beans.Project;

public class ProjectMapper implements RowMapper<Project> {

	@Override
	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
		Project res = new Project();
		res.setId(rs.getLong("id"));
		res.setName(rs.getString("name"));
		res.setUserId(rs.getLong("user_id"));
		return res;
	}
}
