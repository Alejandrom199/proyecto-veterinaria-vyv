package view.views;

import controller.VeterinarioController;
import model.entities.Veterinario;
import utils.AppFactory;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Veterinario view.
 */
public class VeterinarioView extends JFrame {
    private JPanel contentPane;
    private JTable tblVeterinarios;
    private JButton btnNuevoVeterinario, btnEditarVeterinario, btnEliminarVeterinario;
    private VeterinarioController veterinarioController;

    /**
     * Instantiates a new Veterinario view.
     */
    public VeterinarioView() {
        setTitle("Gestión de Veterinarios");
        setBounds(100, 100, 900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        veterinarioController = AppFactory.getVeterinarioController();

        // Crear tabla
        tblVeterinarios = new JTable();
        contentPane.add(new JScrollPane(tblVeterinarios), BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnNuevoVeterinario = new JButton("Nuevo Veterinario");
        btnEditarVeterinario = new JButton("Editar Veterinario");
        btnEliminarVeterinario = new JButton("Eliminar Veterinario");

        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnNuevoVeterinario, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnEditarVeterinario, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.ROJO, btnEliminarVeterinario, true);

        panelBotones.add(btnNuevoVeterinario);
        panelBotones.add(btnEditarVeterinario);
        panelBotones.add(btnEliminarVeterinario);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        agregarListeners();

        actualizarTabla();
        setVisible(true);
    }

    private void actualizarTabla() {
        List<Veterinario> lista = veterinarioController.obtenerTodosVeterinarios();
        Object[][] data = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            data[i][0] = lista.get(i).getNombre();
            data[i][1] = lista.get(i).getEspecialidad();
            data[i][2] = lista.get(i).getTelefono();
            data[i][3] = lista.get(i).getEmail();
        }

        String[] columnas = {"Nombre", "Especialidad", "Teléfono", "Email"};
        tblVeterinarios.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
    }

    private void agregarNuevoVeterinario(){
        JTextField nombreField = new JTextField();
        JTextField especialidadField = new JTextField();
        JTextField telefonoField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] message = {
                "Nombre:", nombreField,
                "Especialidad:", especialidadField,
                "Teléfono:", telefonoField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nuevo Veterinario", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String especialidad = especialidadField.getText();
            String telefono = telefonoField.getText();
            String email = emailField.getText();

            Veterinario nuevo = new Veterinario(nombre, especialidad, telefono, email);
            veterinarioController.guardarVeterinario(nuevo);
            actualizarTabla();
        }
    }

    private void editarVeterinarioSeleccionado(){
        int row = tblVeterinarios.getSelectedRow();
        if (row != -1) {
            Veterinario seleccionado = veterinarioController.obtenerTodosVeterinarios().get(row);

            JTextField nombreField = new JTextField(seleccionado.getNombre());
            JTextField especialidadField = new JTextField(seleccionado.getEspecialidad());
            JTextField telefonoField = new JTextField(seleccionado.getTelefono());
            JTextField emailField = new JTextField(seleccionado.getEmail());

            Object[] message = {
                    "Nombre:", nombreField,
                    "Especialidad:", especialidadField,
                    "Teléfono:", telefonoField,
                    "Email:", emailField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Editar Veterinario", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                seleccionado.setNombre(nombreField.getText());
                seleccionado.setEspecialidad(especialidadField.getText());
                seleccionado.setTelefono(telefonoField.getText());
                seleccionado.setEmail(emailField.getText());

                veterinarioController.actualizarVeterinario(seleccionado);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un veterinario para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarVeterinarioSeleccionado(){
        int row = tblVeterinarios.getSelectedRow();
        if (row != -1) {
            Veterinario seleccionado = veterinarioController.obtenerTodosVeterinarios().get(row);
            int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este veterinario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                veterinarioController.eliminarVeterinario(seleccionado.getIdVeterinario());
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un veterinario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarListeners(){
        // Lógica para agregar veterinario
        btnNuevoVeterinario.addActionListener(e -> agregarNuevoVeterinario());

        // Lógica para editar veterinario
        btnEditarVeterinario.addActionListener(e -> editarVeterinarioSeleccionado());

        // Lógica para eliminar veterinario
        btnEliminarVeterinario.addActionListener(e -> eliminarVeterinarioSeleccionado());
    }
}
