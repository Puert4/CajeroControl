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
    public boolean verificarCredenciales(String correo, String passcode) {
        //Verifica si el correo y passcode existe y corresponde al mismo usuario
        String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE correo = ? AND passcode = ?";

        try (Connection conexionBD = conexion.obtenerConexion(); 
            PreparedStatement preparedStatement = conexionBD.prepareStatement(sql)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, passcode);

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
