/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajerocontrol;

import com.mycompany.cajeropersistencia.conexion.IConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author TeLesheo
 */
public class Verificadores {

    private final IConexion conexion;

    public Verificadores(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Verifies if the email and passcode exist and correspond to the same user
     * in the database.
     *
     * @param correo The email to verify.
     * @param passcode The passcode to verify.
     * @return true if the email and passcode exist and correspond to the same
     * user, false otherwise.
     */
    public int verificarCredenciales(String correo, String passcode) {
        // SQL query to select the user ID from the usuarios table where the email and password match
        String sql = "SELECT id_usuario FROM usuarios WHERE email = ? AND passcode = ?";

        try (
                // Establishing a database connection and creating a prepared statement
                Connection conexionBD = conexion.obtenerConexion(); PreparedStatement preparedStatement = conexionBD.prepareStatement(sql)) {
            // Setting the email and password as parameters of the prepared statement
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, passcode);

            try (
                    // Executing the query and retrieving the result set
                    ResultSet result = preparedStatement.executeQuery()) {
                // Checking if the result set has at least one row
                if (result.next()) {
                    // Retrieving the user ID from the result set
                    return result.getInt("id_usuario");
                }
            }
        } catch (SQLException e) {
            // Handling SQLException by printing the stack trace for debugging
            e.printStackTrace();
            // Returning a value (-1) indicating failure in verifying credentials
        }
        // Returning -1 if an SQLException occurs or if no matching rows are found
        return -1;
    }

    /**
     * Verifies if an email already exists in the database.
     *
     * @param correo The email to verify.
     * @return true if the email already exists, false otherwise.
     */
    public boolean verificarEmailExistente(String correo) {
        String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE email = ?";
        try (Connection conexionBD = conexion.obtenerConexion(); PreparedStatement preparedStatement = conexionBD.prepareStatement(sql)) {
            preparedStatement.setString(1, correo);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    int total = result.getInt("total");
                    return total > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception properly
        }
        return false;
    }
}
