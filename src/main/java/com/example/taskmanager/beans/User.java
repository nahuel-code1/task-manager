package com.example.taskmanager.beans;

import lombok.Data;

@Data
public class User {

	private Long id;
    private String nombreUsuario;
    private String email;
    private String nombreCompleto;
    private String contraseña;
    
}
