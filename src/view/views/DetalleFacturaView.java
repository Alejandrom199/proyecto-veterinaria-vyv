package view.views;

import controller.DetalleFacturaController;
import controller.FacturaController;
import controller.MedicamentoController;
import controller.ServicioController;
import model.entities.DetalleFactura;
import model.entities.Factura;
import model.entities.Medicamento;
import model.entities.Servicio;
import utils.AppFactory;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Vista para la gestión de detalles de factura.
 * Permite agregar, eliminar y actualizar detalles de factura.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class DetalleFacturaView extends JFrame {
    private JTable tblDetalles;
    private JButton btnAgregarDetalle, btnEliminarDetalle, btnActualizarTotal;
    private JPanel contentPane;
    private int idFactura;

    private DetalleFacturaController detalleFacturaController;
    private ServicioController servicioController;
    private MedicamentoController medicamentoController;
    private FacturaController facturaController;

    private FacturaView facturaView;

    /**
     * Constructor que inicializa la vista de detalles de factura.
     *
     * @param idFactura El ID de la factura a la que pertenecen los detalles
     */
    public DetalleFacturaView(int idFactura) {
        this.idFactura = idFactura;
        setTitle("Detalle de Factura #" + idFactura);
        setBounds(150, 150, 900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        detalleFacturaController = AppFactory.getDetalleFacturaController();
        servicioController = AppFactory.getServicioController();
        medicamentoController = AppFactory.getMedicamentoController();
        facturaController = AppFactory.getFacturaController();
        facturaView = new FacturaView();

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        tblDetalles = new JTable();
        contentPane.add(new JScrollPane(tblDetalles), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAgregarDetalle = new JButton("Agregar Detalle");
        btnEliminarDetalle = new JButton("Eliminar Detalle");
        btnActualizarTotal = new JButton("Actualizar Total");

        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnAgregarDetalle, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.VERDE, btnActualizarTotal, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.ROJO, btnEliminarDetalle, true);

        panelBotones.add(btnAgregarDetalle);
        panelBotones.add(btnActualizarTotal);
        panelBotones.add(btnEliminarDetalle);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        btnAgregarDetalle.addActionListener(e -> agregarDetalle());
        btnEliminarDetalle.addActionListener(e -> eliminarDetalle());
        btnActualizarTotal.addActionListener(e -> actualizarTotalFactura());

        actualizarTabla();
        setVisible(true);
    }

    /**
     * Muestra un diálogo para agregar un nuevo detalle a la factura.
     */
    private void agregarDetalle() {
        List<Servicio> servicios = servicioController.obtenerTodosServicios();
        List<Medicamento> medicamentos = medicamentoController.obtenerTodosMedicamentos();

        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Servicio", "Medicamento"});
        JComboBox<String> comboItems = new JComboBox<>();
        JTextField cantidadField = new JTextField("1");

        comboTipo.addActionListener(e -> {
            comboItems.removeAllItems();
            if (comboTipo.getSelectedItem().equals("Servicio")) {
                for (Servicio s : servicios) comboItems.addItem(s.getIdServicio() + " - " + s.getNombreServicio());
            } else {
                for (Medicamento m : medicamentos) comboItems.addItem(m.getIdMedicamento() + " - " + m.getNombre());
            }
        });

        comboTipo.setSelectedIndex(0); // dispara el evento para poblar comboItems

        Object[] message = {
                "Tipo:", comboTipo,
                "Item:", comboItems,
                "Cantidad:", cantidadField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Agregar Detalle", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            DetalleFactura detalle = new DetalleFactura();
            detalle.setIdFactura(idFactura);
            int cantidad = Integer.parseInt(cantidadField.getText());
            detalle.setCantidad(cantidad);

            String tipoSeleccionado = comboTipo.getSelectedItem().toString();
            detalle.setTipo(tipoSeleccionado); // Aquí se guarda el tipo

            if (tipoSeleccionado.equals("Servicio")) {
                Servicio servicio = servicios.get(comboItems.getSelectedIndex());
                detalle.setIdServicio(servicio.getIdServicio());
                detalle.setSubtotal(servicio.getPrecio());
                detalle.setIdMedicamento(null);
            } else {
                Medicamento medicamento = medicamentos.get(comboItems.getSelectedIndex());
                detalle.setIdMedicamento(medicamento.getIdMedicamento());
                detalle.setSubtotal(medicamento.getPrecio());
                detalle.setIdServicio(null);
            }

            detalleFacturaController.guardarDetalleFactura(detalle);
            actualizarTabla();
        }
    }

    /**
     * Elimina el detalle seleccionado después de confirmación.
     */
    private void eliminarDetalle() {
        int row = tblDetalles.getSelectedRow();
        if (row != -1) {
            DetalleFactura detalle = detalleFacturaController.obtenerDetallesPorFactura(idFactura).get(row);
            int option = JOptionPane.showConfirmDialog(null, "¿Eliminar este detalle?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                detalleFacturaController.eliminarDetalleFactura(detalle.getIdDetalle());
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un detalle para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Actualiza el total de la factura sumando todos los detalles.
     */
    private void actualizarTotalFactura() {
        List<DetalleFactura> detalles = detalleFacturaController.obtenerDetallesPorFactura(idFactura);
        double total = 0;

        for (DetalleFactura d : detalles) {
            total += d.getCantidad() * d.getSubtotal();
        }

        Factura factura = facturaController.buscarFacturaPorId(idFactura);
        factura.setTotal(total);
        facturaController.actualizarFactura(factura);

        JOptionPane.showMessageDialog(null, "Total actualizado: $" + total);
        actualizarTabla();
        facturaView.actualizarTabla();
    }

    /**
     * Actualiza la tabla con los detalles de la factura.
     */
    private void actualizarTabla() {
        List<DetalleFactura> detalles = detalleFacturaController.obtenerDetallesPorFactura(idFactura);
        Object[][] data = new Object[detalles.size()][5];

        for (int i = 0; i < detalles.size(); i++) {
            DetalleFactura d = detalles.get(i);
            String nombreItem = "";
            String tipo = d.getTipo();

            if ("Servicio".equalsIgnoreCase(tipo)) {
                Servicio servicio = servicioController.buscarServicioPorId(d.getIdServicio());
                nombreItem = (servicio != null) ? servicio.getNombreServicio() : "Servicio no encontrado";
            } else if ("Medicamento".equalsIgnoreCase(tipo)) {
                Medicamento medicamento = medicamentoController.buscarMedicamentoPorId(d.getIdMedicamento());
                nombreItem = (medicamento != null) ? medicamento.getNombre() : "Medicamento no encontrado";
            }

            data[i][0] = nombreItem;
            data[i][1] = tipo;
            data[i][2] = d.getCantidad();
            data[i][3] = d.getSubtotal();
            data[i][4] = d.getCantidad() * d.getSubtotal();
        }

        String[] columnas = {"Nombre", "Tipo", "Cantidad", "Precio Unitario", "Subtotal"};
        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
    }
}

