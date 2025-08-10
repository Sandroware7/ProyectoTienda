/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecsup.proyectofinal.util;

/**
 *
 * @author Joao Higa
 */
/**
 * Excepción personalizada para la capa de Acceso a Datos (DAO).
 * Se utiliza para encapsular cualquier excepción específica de la tecnología 
 * de persistencia (como SQLException) y presentar un error consistente
 * a las capas superiores de la aplicación.
 */
public class DAOException extends Exception {

    /**
     * Constructor por defecto.
     */
    public DAOException() {
        super();
    }

    /**
     * Constructor que acepta un mensaje de error detallado.
     *
     * @param message El mensaje que describe el error.
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje y la causa original del error.
     * 
     * Este es el constructor más importante, ya que permite "envolver" la
     * excepción original (ej. SQLException), conservando toda la información
     * de la traza de la pila para una depuración efectiva.
     *
     * @param message El mensaje que describe el error.
     * @param cause   La excepción original que causó este error.
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que solo acepta la causa original del error.
     *
     * @param cause La excepción original que causó este error.
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}