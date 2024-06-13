package com.example.taskmanager.mapper;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.taskmanager.beans.User;

public class UsuarioMapper implements RowMapper<User> {

	@Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
        user.setId(rs.getLong("id"));
        user.setNombreCompleto(rs.getString("full_name"));
        user.setNombreUsuario(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setContraseña(rs.getString("password"));
        return user;
    }
	
}
