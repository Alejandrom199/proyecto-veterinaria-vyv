package view.views;

import controller.MedicamentoController;
import model.dto.MedicamentoMasVendidoDTO;
import model.entities.Medicamento;
import utils.AppFactory;
import utils.SwingUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Medicamento view.
 */
public class MedicamentoView extends JFrame {
    private JPanel contentPane;
    private JTable tblMedicamentos;
    private JButton btnNuevoMedicamento, btnEditarMedicamento, btnEliminarMedicamento,btnGraficoBarras;
    private MedicamentoController medicamentoController;

    /**
     * Instantiates a new Medicamento view.
     */
    public MedicamentoView() {
        setTitle("Gestión de Medicamentos");
        setBounds(100, 100, 900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        medicamentoController = AppFactory.getMedicamentoController();

        // Crear tabla
        tblMedicamentos = new JTable();
        contentPane.add(new JScrollPane(tblMedicamentos), BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnNuevoMedicamento = new JButton("Nuevo Medicamento");
        btnEditarMedicamento = new JButton("Editar Medicamento");
        btnEliminarMedicamento = new JButton("Eliminar Medicamento");
        btnGraficoBarras = new JButton("Medicamentos Más Vendidos");

        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnNuevoMedicamento, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnEditarMedicamento, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.ROJO, btnEliminarMedicamento, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.VERDE, btnGraficoBarras, true);

        panelBotones.add(btnNuevoMedicamento);
        panelBotones.add(btnEditarMedicamento);
        panelBotones.add(btnEliminarMedicamento);
        panelBotones.add(btnGraficoBarras);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        agregarListeners();
        actualizarTabla();
        setVisible(true);
    }

    private void actualizarTabla() {
        List<Medicamento> lista = medicamentoController.obtenerTodosMedicamentos();
        Object[][] data = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            data[i][0] = lista.get(i).getNombre();
            data[i][1] = lista.get(i).getDescripcion();
            data[i][2] = lista.get(i).getPrecio();
        }

        String[] columnas = {"Nombre", "Descripción", "Precio"};
        tblMedicamentos.setModel(new DefaultTableModel(data, columnas));
    }

    private void agregarNuevoMedicamento() {
        JTextField nombreField = new JTextField();
        JTextField descripcionField = new JTextField();
        JTextField precioField = new JTextField();

        Object[] message = {
                "Nombre:", nombreField,
                "Descripción:", descripcionField,
                "Precio:", precioField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nuevo Medicamento", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            double precio = Double.parseDouble(precioField.getText());

            Medicamento nuevo = new Medicamento(nombre, descripcion, precio);
            medicamentoController.guardarMedicamento(nuevo);
            actualizarTabla();
        }
    }

    private void editarMedicamentoSeleccionado() {
        int row = tblMedicamentos.getSelectedRow();
        if (row != -1) {
            Medicamento seleccionado = medicamentoController.obtenerTodosMedicamentos().get(row);

            JTextField nombreField = new JTextField(seleccionado.getNombre());
            JTextField descripcionField = new JTextField(seleccionado.getDescripcion());
            JTextField precioField = new JTextField(String.valueOf(seleccionado.getPrecio()));

            Object[] message = {
                    "Nombre:", nombreField,
                    "Descripción:", descripcionField,
                    "Precio:", precioField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Editar Medicamento", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                seleccionado.setNombre(nombreField.getText());
                seleccionado.setDescripcion(descripcionField.getText());
                seleccionado.setPrecio(Double.parseDouble(precioField.getText()));

                medicamentoController.actualizarMedicamento(seleccionado);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un medicamento para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarMedicamentoSeleccionado() {
        int row = tblMedicamentos.getSelectedRow();
        if (row != -1) {
            Medicamento seleccionado = medicamentoController.obtenerTodosMedicamentos().get(row);
            int option = JOptionPane.showConfirmDialog(null, "¿Eliminar este medicamento?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                medicamentoController.eliminarMedicamento(seleccionado.getIdMedicamento());
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un medicamento para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Obtener medicamentos mas vendidos list.
     *
     * @return the list
     */
    public List<MedicamentoMasVendidoDTO> obtenerMedicamentosMasVendidos(){
        List<MedicamentoMasVendidoDTO> medicamentoMasVendidos = new ArrayList<MedicamentoMasVendidoDTO>();
        medicamentoMasVendidos = medicamentoController.obtenerMedicamentosMasVendidos();

        return medicamentoMasVendidos;
    }

    /**
     * Mostrar medicamentos mas vendidos.
     */
    public void mostrarMedicamentosMasVendidos() {
        // Mapa para almacenar el nombre del medicamento y la cantidad de ventas
        Map<String, Integer> datos = new LinkedHashMap<>();

        // Obtenemos la lista de medicamentos más vendidos
        List<MedicamentoMasVendidoDTO> medicamentosMasVendidos = obtenerMedicamentosMasVendidos();

        // Verificamos que la lista no esté vacía
        if (medicamentosMasVendidos != null && !medicamentosMasVendidos.isEmpty()) {
            // Recorremos la lista de medicamentos más vendidos
            for (MedicamentoMasVendidoDTO medicamento : medicamentosMasVendidos) {
                // Aquí asumimos que MedicamentoMasVendidoDTO tiene un método getNombre() y getVentas()
                datos.put(medicamento.nombre(), medicamento.totalVendido());
            }
            // Creamos el gráfico de barras con los datos obtenidos
            GraficoBarras graficoBarras = new GraficoBarras(datos);
            graficoBarras.setVisible(true);
        } else {
            // En caso de que no haya datos, mostrar un mensaje de advertencia
            JOptionPane.showMessageDialog(null, "No hay datos disponibles para mostrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void agregarListeners() {
        btnNuevoMedicamento.addActionListener(e -> agregarNuevoMedicamento());
        btnEditarMedicamento.addActionListener(e -> editarMedicamentoSeleccionado());
        btnEliminarMedicamento.addActionListener(e -> eliminarMedicamentoSeleccionado());
        btnGraficoBarras.addActionListener(e -> mostrarMedicamentosMasVendidos());
    }
}
