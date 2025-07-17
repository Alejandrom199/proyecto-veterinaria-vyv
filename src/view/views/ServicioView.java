package view.views;

import controller.ServicioController;
import model.entities.Servicio;
import utils.AppFactory;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Servicio view.
 */
public class ServicioView extends JFrame {
    private JPanel contentPane;
    private JTable tblServicios;
    private JButton btnNuevoServicio, btnEditarServicio, btnEliminarServicio;
    private ServicioController servicioController;

    /**
     * Instantiates a new Servicio view.
     */
    public ServicioView() {
        setTitle("Gestión de Servicios");
        setBounds(100, 100, 900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        servicioController = AppFactory.getServicioController();

        tblServicios = new JTable();
        contentPane.add(new JScrollPane(tblServicios), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnNuevoServicio = new JButton("Nuevo Servicio");
        btnEditarServicio = new JButton("Editar Servicio");
        btnEliminarServicio = new JButton("Eliminar Servicio");

        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnNuevoServicio, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnEditarServicio, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.ROJO, btnEliminarServicio, true);

        panelBotones.add(btnNuevoServicio);
        panelBotones.add(btnEditarServicio);
        panelBotones.add(btnEliminarServicio);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        agregarListeners();
        actualizarTabla();
        setVisible(true);
    }

    private void actualizarTabla() {
        List<Servicio> lista = servicioController.obtenerTodosServicios();
        Object[][] data = new Object[lista.size()][3];

        for (int i = 0; i < lista.size(); i++) {
            data[i][0] = lista.get(i).getNombreServicio();
            data[i][1] = lista.get(i).getDescripcion();
            data[i][2] = lista.get(i).getPrecio();
        }

        String[] columnas = {"Nombre", "Descripción", "Precio"};
        tblServicios.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
    }

    private void agregarNuevoServicio() {
        JTextField nombreField = new JTextField();
        JTextField descripcionField = new JTextField();
        JTextField precioField = new JTextField();

        Object[] message = {
                "Nombre:", nombreField,
                "Descripción:", descripcionField,
                "Precio:", precioField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nuevo Servicio", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Servicio nuevo = new Servicio(
                    nombreField.getText(),
                    descripcionField.getText(),
                    Double.parseDouble(precioField.getText())
            );
            servicioController.guardarServicio(nuevo);
            actualizarTabla();
        }
    }

    private void editarServicioSeleccionado() {
        int row = tblServicios.getSelectedRow();
        if (row != -1) {
            Servicio seleccionado = servicioController.obtenerTodosServicios().get(row);

            JTextField nombreField = new JTextField(seleccionado.getNombreServicio());
            JTextField descripcionField = new JTextField(seleccionado.getDescripcion());
            JTextField precioField = new JTextField(String.valueOf(seleccionado.getPrecio()));

            Object[] message = {
                    "Nombre:", nombreField,
                    "Descripción:", descripcionField,
                    "Precio:", precioField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Editar Servicio", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                seleccionado.setNombreServicio(nombreField.getText());
                seleccionado.setDescripcion(descripcionField.getText());
                seleccionado.setPrecio(Double.parseDouble(precioField.getText()));

                servicioController.actualizarServicio(seleccionado);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un servicio para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarServicioSeleccionado() {
        int row = tblServicios.getSelectedRow();
        if (row != -1) {
            Servicio seleccionado = servicioController.obtenerTodosServicios().get(row);
            int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este servicio?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                servicioController.eliminarServicio(seleccionado.getIdServicio());
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un servicio para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarListeners() {
        btnNuevoServicio.addActionListener(e -> agregarNuevoServicio());
        btnEditarServicio.addActionListener(e -> editarServicioSeleccionado());
        btnEliminarServicio.addActionListener(e -> eliminarServicioSeleccionado());
    }
}
