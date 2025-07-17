package view.views;

import controller.MascotaController;
import controller.ClienteController;
import model.entities.Cliente;
import model.entities.Mascota;
import utils.AppFactory;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Mascota view.
 */
public class MascotaView extends JFrame {
    private JPanel contentPane;
    private JTable tblMascotas;
    private JButton btnNuevaMascota, btnEditarMascota, btnEliminarMascota;
    private MascotaController mascotaController = AppFactory.getMascotaController();
    private ClienteController clienteController = AppFactory.getClienteController();

    /**
     * Instantiates a new Mascota view.
     */
    public MascotaView() {
        setTitle("Gestión de Mascotas");
        setBounds(100, 100, 900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        // Tabla de mascotas
        tblMascotas = new JTable();
        contentPane.add(new JScrollPane(tblMascotas), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        btnNuevaMascota = new JButton("Nueva Mascota");
        btnEditarMascota = new JButton("Editar Mascota");
        btnEliminarMascota = new JButton("Eliminar Mascota");

        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnNuevaMascota, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnEditarMascota, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.ROJO, btnEliminarMascota, true);

        panelBotones.add(btnNuevaMascota);
        panelBotones.add(btnEditarMascota);
        panelBotones.add(btnEliminarMascota);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        // Listeners
        btnNuevaMascota.addActionListener(e -> crearMascota());
        btnEditarMascota.addActionListener(e -> editarMascota());
        btnEliminarMascota.addActionListener(e -> eliminarMascota());

        actualizarTabla();
        setVisible(true);
    }

    private void crearMascota() {
        JTextField nombreField = new JTextField();
        JTextField especieField = new JTextField();
        JTextField razaField = new JTextField();
        JTextField edadField = new JTextField();
        JTextField sexoField = new JTextField();

        List<Cliente> clientes = clienteController.obtenerTodosClientes();
        JComboBox<String> comboClientes = new JComboBox<>();
        for (Cliente c : clientes) comboClientes.addItem(c.getIdCliente() + " - " + c.getNombre());

        Object[] message = {
                "Nombre:", nombreField,
                "Especie:", especieField,
                "Raza:", razaField,
                "Edad:", edadField,
                "Sexo:", sexoField,
                "Cliente:", comboClientes
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nueva Mascota", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Mascota m = new Mascota();
            m.setNombre(nombreField.getText());
            m.setEspecie(especieField.getText());
            m.setRaza(razaField.getText());
            m.setEdad(Integer.parseInt(edadField.getText()));
            m.setSexo(sexoField.getText());

            int selectedIndex = comboClientes.getSelectedIndex();
            m.setIdCliente(clientes.get(selectedIndex).getIdCliente());

            mascotaController.guardarMascota(m);
            actualizarTabla();
        }
    }

    private void editarMascota() {
        int row = tblMascotas.getSelectedRow();
        if (row != -1) {
            Mascota mascota = mascotaController.obtenerTodasMascotas().get(row);

            JTextField nombreField = new JTextField(mascota.getNombre());
            JTextField especieField = new JTextField(mascota.getEspecie());
            JTextField razaField = new JTextField(mascota.getRaza());
            JTextField edadField = new JTextField(String.valueOf(mascota.getEdad()));
            JTextField sexoField = new JTextField(mascota.getSexo());

            List<Cliente> clientes = clienteController.obtenerTodosClientes();
            JComboBox<String> comboClientes = new JComboBox<>();
            for (Cliente c : clientes)
                comboClientes.addItem(c.getIdCliente() + " - " + c.getNombre());
            comboClientes.setSelectedItem(mascota.getIdCliente() + " - " + clienteController.buscarClientePorId(mascota.getIdCliente()).getNombre());

            Object[] message = {
                    "Nombre:", nombreField,
                    "Especie:", especieField,
                    "Raza:", razaField,
                    "Edad:", edadField,
                    "Sexo:", sexoField,
                    "Cliente:", comboClientes
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Editar Mascota", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                mascota.setNombre(nombreField.getText());
                mascota.setEspecie(especieField.getText());
                mascota.setRaza(razaField.getText());
                mascota.setEdad(Integer.parseInt(edadField.getText()));
                mascota.setSexo(sexoField.getText());
                mascota.setIdCliente(clientes.get(comboClientes.getSelectedIndex()).getIdCliente());

                mascotaController.actualizarMascota(mascota);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una mascota para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarMascota() {
        int row = tblMascotas.getSelectedRow();
        if (row != -1) {
            Mascota mascota = mascotaController.obtenerTodasMascotas().get(row);
            int option = JOptionPane.showConfirmDialog(null, "¿Eliminar mascota seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                mascotaController.eliminarMascota(mascota.getIdMascota());
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una mascota para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla() {
        List<Mascota> mascotas = mascotaController.obtenerTodasMascotas();
        Object[][] data = new Object[mascotas.size()][6];

        for (int i = 0; i < mascotas.size(); i++) {
            Mascota m = mascotas.get(i);
            data[i][0] = m.getNombre();
            data[i][1] = m.getEspecie();
            data[i][2] = m.getRaza();
            data[i][3] = m.getEdad();
            data[i][4] = m.getSexo();
            data[i][5] = clienteController.buscarClientePorId(m.getIdCliente()).getNombre();
        }

        String[] columnas = {"Nombre", "Especie", "Raza", "Edad", "Sexo", "Cliente"};
        tblMascotas.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
    }
}
