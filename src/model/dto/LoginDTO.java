package model.dto;

import controller.AuthController;

import javax.swing.*;

/**
 * DTO para transferencia de datos de login.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public record LoginDTO(
        JTextField txtUsername,
        JPasswordField txtPassword,
        AuthController authController,
        JCheckBox chkRecordar
) {}
