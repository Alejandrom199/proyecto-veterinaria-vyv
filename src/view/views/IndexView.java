package view.views;

import model.dto.UsuarioDTO;
import utils.SessionManager;
import utils.SwingUtils;
import utils.SwingUtils.ColorTipo;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Vista principal del sistema que muestra el panel de administración.
 * Contiene los botones de acceso a todas las funcionalidades del sistema.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class IndexView extends JFrame {
    private JPanel contentPane;
    private JPanel northPanel, centerPanel, southPanel;
    private JLabel lblUsername, lblRol;
    private JButton btnClientes, btnMascotas, btnCitas, btnFacturas, btnMedicamentos, btnServicios, btnVeterinarios, btnCerrarSesion;

    /**
     * Constructor que inicializa la vista principal del sistema.
     *
     * @param usuarioDTO Objeto con los datos del usuario que ha iniciado sesión
     */
    public IndexView(UsuarioDTO usuarioDTO) {
        setTitle("Panel de Administración");
        setBounds(100, 100, 400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        SwingUtils.centrarVentana(this);

        JPanel imagePanel = new JPanel();
        JLabel lblImagen = new JLabel();

        // Cargar la imagen
        ImageIcon icono = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/veterinariagpt.png"))
        );

        // Si falla, muestra un mensaje de error
        if (icono.getImage() == null) {
            JOptionPane.showMessageDialog(this, "Error: No se encontró la imagen");
        }

        Image imagenEscalada = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(imagenEscalada));

        imagePanel.setBackground(Color.WHITE);
        imagePanel.add(lblImagen);
        contentPane.add(imagePanel, BorderLayout.NORTH);

        centerPanel = new JPanel(new GridLayout(1, 1));
        centerPanel.setBackground(Color.WHITE);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        northPanel = new JPanel(new GridLayout(8, 1));
        northPanel.setBackground(Color.WHITE);
        centerPanel.add(northPanel);

        southPanel = new JPanel(new GridLayout(4, 1));
        southPanel.setBackground(Color.WHITE);
        centerPanel.add(southPanel);

        lblUsername = new JLabel("Username: " + usuarioDTO.nombreUsuario());
        lblRol = new JLabel("Rol: " + usuarioDTO.rol());

        btnClientes = new JButton("Clientes");
        btnMascotas = new JButton("Mascotas");
        btnVeterinarios = new JButton("Veterinarios");
        btnCitas = new JButton("Citas");
        btnMedicamentos = new JButton("Medicamentos");
        btnServicios = new JButton("Servicios Veterinarios");
        btnFacturas = new JButton("Facturas");
        btnCerrarSesion = new JButton("Cerrar sesión");

        northPanel.add(btnClientes);
        northPanel.add(btnMascotas);
        northPanel.add(btnVeterinarios);
        northPanel.add(btnCitas);
        northPanel.add(btnMedicamentos);
        northPanel.add(btnServicios);
        northPanel.add(btnFacturas);
        northPanel.add(btnCerrarSesion);

        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnClientes, true);
        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnMascotas, true);
        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnVeterinarios, true);
        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnCitas, true);
        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnMedicamentos, true);
        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnServicios, true);
        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnFacturas, true);
        SwingUtils.aplicarEstilo(ColorTipo.ROJO,btnCerrarSesion, true);

        southPanel.add(lblUsername);
        southPanel.add(lblRol);

        // Acciones de los botones
        btnClientes.addActionListener(e -> new ClienteView().setVisible(true));
        btnMascotas.addActionListener(e -> new MascotaView().setVisible(true));
        btnCitas.addActionListener(e -> new CitaView().setVisible(true));
        btnFacturas.addActionListener(e -> new FacturaView().setVisible(true));
        btnMedicamentos.addActionListener(e -> new MedicamentoView().setVisible(true));
        btnServicios.addActionListener(e -> new ServicioView().setVisible(true));
        btnVeterinarios.addActionListener(e -> new VeterinarioView().setVisible(true));
        btnCerrarSesion.addActionListener(e -> {
            SessionManager.eliminarSesion();
            this.dispose();
            new LoginView().setVisible(true);
        });

        setVisible(true);
    }
}

