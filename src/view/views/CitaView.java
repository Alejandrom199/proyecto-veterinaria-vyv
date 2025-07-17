package view.views;

import controller.CitaController;
import controller.ClienteController;
import controller.MascotaController;
import controller.VeterinarioController;
import model.entities.Cita;
import model.entities.Cliente;
import model.entities.Mascota;
import model.entities.Veterinario;
import utils.AppFactory;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Vista para la gestión de citas médicas veterinarias.
 * Permite listar, crear, editar y eliminar citas.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class CitaView extends JFrame {
    private JPanel contentPane;
    private JTable tblCitas;
    private JButton btnNuevaCita, btnEditarCita, btnEliminarCita;
    private CitaController citaController;
    private MascotaController mascotaController;
    private VeterinarioController veterinarioController;
    private ClienteController clienteController;

    /**
     * Constructor que inicializa la vista de gestión de citas.
     */
    public CitaView() {
        setTitle("Gestión de Citas");
        setBounds(100, 100, 900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        citaController = AppFactory.getCitaController();
        mascotaController = AppFactory.getMascotaController();
        veterinarioController = AppFactory.getVeterinarioController();
        clienteController = AppFactory.getClienteController();

        tblCitas = new JTable();
        contentPane.add(new JScrollPane(tblCitas), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnNuevaCita = new JButton("Nueva Cita");
        btnEditarCita = new JButton("Editar Cita");
        btnEliminarCita = new JButton("Eliminar Cita");

        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnNuevaCita, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.AZUL, btnEditarCita, true);
        SwingUtils.aplicarEstilo(SwingUtils.ColorTipo.ROJO, btnEliminarCita, true);

        panelBotones.add(btnNuevaCita);
        panelBotones.add(btnEditarCita);
        panelBotones.add(btnEliminarCita);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        agregarListeners();
        actualizarTabla();
        setVisible(true);
    }

    /**
     * Actualiza la tabla con los datos de las citas existentes.
     */
    private void actualizarTabla() {
        List<Cita> citas = citaController.obtenerTodasCitas();
        Object[][] data = new Object[citas.size()][5];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < citas.size(); i++) {
            data[i][0] = citas.get(i).getFechaHora().format(formatter);
            data[i][1] = citas.get(i).getMotivo();
            data[i][2] = citas.get(i).getEstado();
            data[i][3] = mascotaController.buscarMascotaPorId(citas.get(i).getIdMascota()).getNombre()+" - "+clienteController.buscarClientePorId(
                    mascotaController.buscarMascotaPorId(
                            citas.get(i).getIdMascota()
                    ).getIdCliente()
            ).getNombre();
            data[i][4] = veterinarioController.buscarVeterinarioPorId(citas.get(i).getIdVeterinario()).getNombre();
        }

        String[] columnas = {"Fecha y Hora", "Motivo", "Estado", "Mascota", "Veterinario"};
        tblCitas.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
    }

    /**
     * Muestra un diálogo para agregar una nueva cita.
     */
    private void agregarNuevaCita() {
        JTextField fechaHoraField = new JTextField(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        JTextField motivoField = new JTextField();
        JTextField estadoField = new JTextField("Pendiente");

        List<Mascota> mascotas = mascotaController.obtenerTodasMascotas();
        List<Veterinario> veterinarios = veterinarioController.obtenerTodosVeterinarios();

        JComboBox<String> comboMascotas = new JComboBox<>();


        for (Mascota m : mascotas){
            Cliente c = clienteController.buscarClientePorId(m.getIdCliente());
            comboMascotas.addItem(m.getIdMascota() + " - " + m.getNombre() + " - " +c.getNombre());
        }

        JComboBox<String> comboVeterinarios = new JComboBox<>();
        for (Veterinario v : veterinarios) comboVeterinarios.addItem(v.getIdVeterinario() + " - " + v.getNombre());

        Object[] message = {
                "Fecha y Hora (yyyy-MM-dd HH:mm):", fechaHoraField,
                "Motivo:", motivoField,
                "Estado:", estadoField,
                "Mascota:", comboMascotas,
                "Veterinario:", comboVeterinarios
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nueva Cita", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Cita c = new Cita();
            c.setFechaHora(LocalDateTime.parse(fechaHoraField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            c.setMotivo(motivoField.getText());
            c.setEstado(estadoField.getText());

            int selectedIndexMascota = comboMascotas.getSelectedIndex();
            c.setIdMascota(mascotas.get(selectedIndexMascota).getIdMascota());

            int selectedIndexVeterinario = comboVeterinarios.getSelectedIndex();
            c.setIdVeterinario(veterinarios.get(selectedIndexVeterinario).getIdVeterinario());

            citaController.guardarCita(c);
            actualizarTabla();
        }
    }

    /**
     * Muestra un diálogo para editar la cita seleccionada.
     */
    private void editarCitaSeleccionada() {
        int row = tblCitas.getSelectedRow();
        if (row != -1) {
            Cita cita = citaController.obtenerTodasCitas().get(row);

            JTextField fechaHoraField = new JTextField(cita.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            JTextField motivoField = new JTextField(cita.getMotivo());
            JTextField estadoField = new JTextField(cita.getEstado());

            List<Mascota> mascotas = mascotaController.obtenerTodasMascotas();
            JComboBox<String> comboMascotas = new JComboBox<>();

            for (Mascota m : mascotas){
                Cliente c = clienteController.buscarClientePorId(m.getIdCliente());
                comboMascotas.addItem(m.getIdMascota() + " - " + m.getNombre() + " - " +c.getNombre());
            }
            comboMascotas.setSelectedItem(cita.getIdCita()+" - "+mascotaController.buscarMascotaPorId(cita.getIdMascota()).getNombre());

            List<Veterinario> veterinarios = veterinarioController.obtenerTodosVeterinarios();
            JComboBox<String> comboVeterinarios = new JComboBox<>();
            for(Veterinario v : veterinarios)
                comboVeterinarios.addItem(v.getIdVeterinario()+" - "+v.getNombre());
            comboVeterinarios.setSelectedItem(cita.getIdCita()+" - "+veterinarioController.buscarVeterinarioPorId(cita.getIdVeterinario()).getNombre());

            Object[] message = {
                    "Fecha y Hora (yyyy-MM-dd HH:mm):", fechaHoraField,
                    "Motivo:", motivoField,
                    "Estado:", estadoField,
                    "Mascota:", comboMascotas,
                    "Veterinario:", comboVeterinarios
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Editar Cita", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                cita.setFechaHora(LocalDateTime.parse(fechaHoraField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                cita.setMotivo(motivoField.getText());
                cita.setEstado(estadoField.getText());
                cita.setIdMascota(mascotas.get(comboMascotas.getSelectedIndex()).getIdMascota());
                cita.setIdVeterinario(veterinarios.get(comboVeterinarios.getSelectedIndex()).getIdVeterinario());

                citaController.actualizarCita(cita);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una cita para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina la cita seleccionada después de confirmación.
     */
    private void eliminarCitaSeleccionada() {
        int row = tblCitas.getSelectedRow();
        if (row != -1) {
            Cita seleccionada = citaController.obtenerTodasCitas().get(row);
            int option = JOptionPane.showConfirmDialog(null, "¿Eliminar esta cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                citaController.eliminarCita(seleccionada.getIdCita());
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una cita para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Agrega los listeners a los botones de la interfaz.
     */
    private void agregarListeners() {
        btnNuevaCita.addActionListener(e -> agregarNuevaCita());
        btnEditarCita.addActionListener(e -> editarCitaSeleccionada());
        btnEliminarCita.addActionListener(e -> eliminarCitaSeleccionada());
    }
}
