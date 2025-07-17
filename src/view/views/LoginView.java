package view.views;

import controller.AuthController;
import model.dto.LoginDTO;
import utils.AppFactory;
import utils.SwingUtils;
import utils.SwingUtils.ColorTipo;
import view.listeners.LoginViewListener;

import javax.swing.*;
import java.awt.*;

/**
 * Vista para el inicio de sesión de usuarios.
 * Permite autenticar a los usuarios en el sistema.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class LoginView extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private AuthController authController;
    private JCheckBox chkRecordar;

    /**
     * Constructor que inicializa la vista de login.
     */
    public LoginView() {
        super("Login");
        setBounds(100, 100, 300, 200);

        authController = AppFactory.getAuthController();

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        contentPane = new JPanel(new GridLayout(6, 1));
        setContentPane(contentPane);

        // Crear los campos y botones
        JLabel lblUsername = new JLabel("Nombre de Usuario:");
        lblUsername.setBounds(30, 30, 120, 25);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 30, 120, 25);
        contentPane.add(txtUsername);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 70, 120, 25);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 70, 120, 25);
        contentPane.add(txtPassword);

        chkRecordar = new JCheckBox("Recordar usuario");
        chkRecordar.setBounds(150, 90, 120, 25);
        contentPane.add(chkRecordar);

        if(chkRecordar.isSelected()) {

        }

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(90, 120, 120, 30);
        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnLogin, true);
        contentPane.add(btnLogin);

        LoginDTO loginDTO = new LoginDTO(txtUsername, txtPassword, authController, chkRecordar);
        btnLogin.addActionListener(new LoginViewListener(loginDTO));

        setVisible(true);
    }

}
