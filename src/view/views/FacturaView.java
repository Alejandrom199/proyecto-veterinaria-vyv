package view.views;

import controller.ClienteController;
import controller.CitaController;
import controller.FacturaController;
import model.entities.Cliente;
import model.entities.Cita;
import model.entities.Factura;
import utils.AppFactory;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Vista para la gestión de facturas.
 * Permite listar, crear, editar y eliminar facturas.
 *
 * @author Anthony López
 * @version 1.0
 * @since 2023
 */
public class FacturaView extends JFrame {
    private JPanel contentPane;
    private JTable tblFacturas;
    private JButton btnNuevaFactura, btnEliminarFactura, btnVerDetalles;
    private FacturaController facturaController;
    private ClienteController clienteController;
    private CitaController citaController;

    /**
     * Constructor que inicializa la vista de gestión de facturas.
     */
    public FacturaView() {
        setTitle("Gestión de Facturas");
        setBounds(100, 100, 900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        facturaController = AppFactory.getFacturaController();
        clienteController = AppFactory.getClienteController();
        citaController = AppFactory.getCitaController();

        tblFacturas = new JTable();
        contentPane.add(new JScrollPane(tblFacturas), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnNuevaFactura = new JButton("Nueva Factura");
        btnEliminarFactura = new JButton("Eliminar Factura");
        btnVerDetalles = new JButton("Ver Detalles");

        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnNuevaFactura, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.VERDE, btnVerDetalles, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.ROJO, btnEliminarFactura, true);

        panelBotones.add(btnNuevaFactura);
        panelBotones.add(btnVerDetalles);
        panelBotones.add(btnEliminarFactura);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        btnNuevaFactura.addActionListener(e -> crearFactura());
        btnEliminarFactura.addActionListener(e -> eliminarFactura());
        btnVerDetalles.addActionListener(e -> verDetallesFactura());

        actualizarTabla();
        setVisible(true);
    }

    /**
     * Muestra un diálogo para crear una nueva factura.
     */
    private void crearFactura() {
        List<Cliente> clientes = clienteController.obtenerTodosClientes();
        List<Cita> citas = citaController.obtenerTodasCitas();

        JComboBox<String> comboClientes = new JComboBox<>();
        for (Cliente c : clientes) comboClientes.addItem(c.getIdCliente() + " - " + c.getNombre());

        JComboBox<String> comboCitas = new JComboBox<>();
        for (Cita c : citas) comboCitas.addItem(c.getIdCita() + " - " + c.getMotivo());

        JTextField totalField = new JTextField("0.0");

        Object[] message = {
                "Cliente:", comboClientes,
                "Cita:", comboCitas,
                "Total:", totalField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nueva Factura", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Factura f = new Factura();
            f.setFechaEmision(LocalDate.now());
            f.setTotal(Double.parseDouble(totalField.getText()));
            f.setIdCliente(clientes.get(comboClientes.getSelectedIndex()).getIdCliente());
            f.setIdCita(citas.get(comboCitas.getSelectedIndex()).getIdCita());

            facturaController.guardarFactura(f);
            actualizarTabla();
        }
    }

    /**
     * Muestra un diálogo para editar la factura seleccionada.
     */
    private void editarFactura() {
        int row = tblFacturas.getSelectedRow();
        if (row != -1) {
            Factura factura = facturaController.obtenerTodasFacturas().get(row);

            List<Cliente> clientes = clienteController.obtenerTodosClientes();
            List<Cita> citas = citaController.obtenerTodasCitas();

            JComboBox<String> comboClientes = new JComboBox<>();
            for (Cliente c : clientes) comboClientes.addItem(c.getIdCliente() + " - " + c.getNombre());

            JComboBox<String> comboCitas = new JComboBox<>();
            for (Cita c : citas) comboCitas.addItem(c.getIdCita() + " - " + c.getMotivo());

            comboClientes.setSelectedIndex(clientes.indexOf(clienteController.buscarClientePorId(factura.getIdCliente())));
            comboCitas.setSelectedIndex(citas.indexOf(citaController.buscarCitaPorId(factura.getIdCita())));

            JTextField totalField = new JTextField(String.valueOf(factura.getTotal()));

            Object[] message = {
                    "Cliente:", comboClientes,
                    "Cita:", comboCitas,
                    "Total:", totalField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Editar Factura", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                factura.setIdCliente(clientes.get(comboClientes.getSelectedIndex()).getIdCliente());
                factura.setIdCita(citas.get(comboCitas.getSelectedIndex()).getIdCita());
                factura.setTotal(Double.parseDouble(totalField.getText()));

                facturaController.actualizarFactura(factura);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una factura para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina la factura seleccionada después de confirmación.
     */
    private void eliminarFactura() {
        int row = tblFacturas.getSelectedRow();
        if (row != -1) {
            Factura factura = facturaController.obtenerTodasFacturas().get(row);
            int option = JOptionPane.showConfirmDialog(null, "¿Eliminar factura seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                facturaController.eliminarFactura(factura.getIdFactura());
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una factura para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Visualizar los detalles de las facturas
     */
    private void verDetallesFactura() {
        int row = tblFacturas.getSelectedRow();
        if (row != -1) {
            Factura factura = facturaController.obtenerTodasFacturas().get(row);
            new DetalleFacturaView(factura.getIdFactura()); // Vista detallada por factura
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una factura para ver detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Actualizar tabla.
     */
    public void actualizarTabla() {
        List<Factura> facturas = facturaController.obtenerTodasFacturas();
        Object[][] data = new Object[facturas.size()][4];

        for (int i = 0; i < facturas.size(); i++) {
            Factura f = facturas.get(i);
            data[i][0] = f.getIdFactura();
            data[i][1] = f.getFechaEmision();
            data[i][2] = clienteController.buscarClientePorId(f.getIdCliente()).getNombre();
            data[i][3] = f.getTotal();
        }

        String[] columnas = {"ID", "Fecha", "Cliente", "Total"};
        tblFacturas.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
    }
}
