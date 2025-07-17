package view.views;

import controller.ClienteController;
import model.entities.Cliente;
import utils.AppFactory;
import utils.SwingUtils;
import utils.SwingUtils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Vista para la gestión de clientes de la veterinaria.
 * Permite listar, crear, editar y eliminar clientes.
 *
 * @author Andy Romero
 * @version 1.0
 * @since 2023
 */
public class ClienteView extends JFrame {
    private JPanel contentPane;
    private JTable tblClientes;
    private JButton btnNuevoCliente, btnEditarCliente, btnEliminarCliente;

    private final ClienteController clienteController;

    /**
     * Constructor que inicializa la vista de gestión de clientes.
     */
    public ClienteView() {
        setTitle("Gestión de Clientes");
        setBounds(100, 100, 900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        clienteController = AppFactory.getClienteController();

        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        // Crear tabla de clientes
        tblClientes = new JTable();
        contentPane.add(new JScrollPane(tblClientes), BorderLayout.CENTER);

        // Crear panel de botones
        JPanel panelBotones = new JPanel();
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        btnNuevoCliente = new JButton("Nuevo Cliente");
        btnEditarCliente = new JButton("Editar Cliente");
        btnEliminarCliente = new JButton("Eliminar Cliente");

        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnNuevoCliente, true);
        SwingUtils.aplicarEstilo(ColorTipo.AZUL, btnEditarCliente, true);
        SwingUtils.aplicarEstilo(ColorTipo.ROJO, btnEliminarCliente, true);

        panelBotones.add(btnNuevoCliente);
        panelBotones.add(btnEditarCliente);
        panelBotones.add(btnEliminarCliente);

        // Actualizar la tabla al iniciar la vista
        actualizarTabla();

        btnNuevoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoCliente();
            }
        });

        btnEditarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarClienteSeleccionado();
            }
        });

        btnEliminarCliente.addActionListener(e -> {
            eliminarClienteSeleccionado();
        });

        // Mostrar la ventana
        setVisible(true);

    }

    /**
     * Actualiza la tabla con los datos de los clientes existentes.
     */
    private void actualizarTabla() {
        List<Cliente> clientes = clienteController.obtenerTodosClientes();
        Object[][] data = new Object[clientes.size()][2];

        for (int i = 0; i < clientes.size(); i++) {
            data[i][0] = clientes.get(i).getNombre();
            data[i][1] = clientes.get(i).getTelefono();
        }

        String[] columnNames = {"Nombre", "Teléfono"};
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    /**
     * Muestra un diálogo para agregar un nuevo cliente.
     */
    private void agregarNuevoCliente() {
        JTextField nombreField = new JTextField();
        JTextField telefonoField = new JTextField();
        Object[] message = {
                "Nombre:", nombreField,
                "Teléfono:", telefonoField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nuevo Cliente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Crear el cliente
            String nombre = nombreField.getText();
            String telefono = telefonoField.getText();

            Cliente nuevoCliente = new Cliente(nombre, telefono);
            clienteController.guardarCliente(nuevoCliente);
            actualizarTabla();
        }
    }

    /**
     * Muestra un diálogo para editar el cliente seleccionado.
     */
    private void editarClienteSeleccionado() {
        int row = tblClientes.getSelectedRow();
        if (row != -1) {
            // Obtener el cliente seleccionado
            Cliente clienteSeleccionado = clienteController.obtenerTodosClientes().get(row);

            // Crear formulario de edición
            JTextField nombreField = new JTextField(clienteSeleccionado.getNombre());
            JTextField telefonoField = new JTextField(clienteSeleccionado.getTelefono());
            Object[] message = {
                    "Nombre:", nombreField,
                    "Teléfono:", telefonoField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Editar Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Actualizar el cliente
                clienteSeleccionado.setNombre(nombreField.getText());
                clienteSeleccionado.setTelefono(telefonoField.getText());

                clienteController.actualizarCliente(clienteSeleccionado);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un cliente para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina el cliente seleccionado después de confirmación.
     */
    private void eliminarClienteSeleccionado() {
        int row = tblClientes.getSelectedRow();
        if (row != -1) {
            // Obtener el cliente seleccionado
            Cliente clienteSeleccionado = clienteController.obtenerTodosClientes().get(row);

            int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                clienteController.eliminarCliente(clienteSeleccionado.getIdCliente());
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un cliente para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
