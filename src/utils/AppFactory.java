package utils;

import controller.*;
import model.dao.*;
import model.services.*;
import model.services.impl.*;

/**
 * Factory class for creating and managing all application components (DAOs, Services, and Controllers).
 * Implements the Factory pattern to centralize object creation and provide easy access to all components.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class AppFactory {
    //DAOs
    private static UsuarioDAO usuarioDAO;
    private static ClienteDAO clienteDAO;
    private static MascotaDAO mascotaDAO;
    private static VeterinarioDAO veterinarioDAO;
    private static CitaDAO citaDAO;
    private static MedicamentoDAO medicamentoDAO;
    private static ServicioDAO servicioDAO;
    private static FacturaDAO facturaDAO;
    private static DetalleFacturaDAO detalleFacturaDAO;

    //Services
    private static UsuarioService usuarioService;
    private static ClienteService clienteService;
    private static MascotaService mascotaService;
    private static VeterinarioService veterinarioService;
    private static CitaService citaService;
    private static MedicamentoService medicamentoService;
    private static ServicioService servicioService;
    private static FacturaService facturaService;
    private static DetalleFacturaService detalleFacturaService;
    private static AuthService authService;

    //Controllers
    private static UsuarioController usuarioController;
    private static ClienteController clienteController;
    private static MascotaController mascotaController;
    private static VeterinarioController veterinarioController;
    private static CitaController citaController;
    private static MedicamentoController medicamentoController;
    private static ServicioController servicioController;
    private static FacturaController facturaController;
    private static DetalleFacturaController detalleFacturaController;
    private static AuthController authController;

    static {
        initialize();
    }

    /**
     * Initializes all components with their default implementations.
     */
    private static void initialize() {
        usuarioDAO = new UsuarioDAO();
        clienteDAO = new ClienteDAO();
        mascotaDAO = new MascotaDAO();
        veterinarioDAO = new VeterinarioDAO();
        citaDAO = new CitaDAO();
        medicamentoDAO = new MedicamentoDAO();
        servicioDAO = new ServicioDAO();
        facturaDAO = new FacturaDAO();
        detalleFacturaDAO = new DetalleFacturaDAO();

        usuarioService = new UsuarioServiceImpl(usuarioDAO);
        clienteService = new ClienteServiceImpl(clienteDAO);
        mascotaService = new MascotaServiceImpl(mascotaDAO);
        veterinarioService = new VeterinarioServiceImpl(veterinarioDAO);
        citaService = new CitaServiceImpl(citaDAO);
        medicamentoService = new MedicamentoServiceImpl(medicamentoDAO);
        servicioService = new ServicioServiceImpl(servicioDAO);
        facturaService = new FacturaServiceImpl(facturaDAO);
        detalleFacturaService = new DetalleFacturaServiceImpl(detalleFacturaDAO);
        authService = new AuthServiceImpl(usuarioDAO);

        usuarioController = new UsuarioController(usuarioService);
        clienteController = new ClienteController(clienteService);
        mascotaController = new MascotaController(mascotaService);
        veterinarioController = new VeterinarioController(veterinarioService);
        citaController = new CitaController(citaService);
        medicamentoController = new MedicamentoController(medicamentoService);
        servicioController = new ServicioController(servicioService);
        facturaController = new FacturaController(facturaService);
        detalleFacturaController = new DetalleFacturaController(detalleFacturaService);
        authController = new AuthController(authService);
    }

    // DAO Getters

    /**
     * Gets the UsuarioDAO instance.
     *
     * @return the UsuarioDAO instance
     */
    public static UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    /**
     * Gets the ClienteDAO instance.
     *
     * @return the ClienteDAO instance
     */
    public static ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    /**
     * Gets the MascotaDAO instance.
     *
     * @return the MascotaDAO instance
     */
    public static MascotaDAO getMascotaDAO() {
        return mascotaDAO;
    }

    /**
     * Gets the VeterinarioDAO instance.
     *
     * @return the VeterinarioDAO instance
     */
    public static VeterinarioDAO getVeterinarioDAO() {
        return veterinarioDAO;
    }

    /**
     * Gets the CitaDAO instance.
     *
     * @return the CitaDAO instance
     */
    public static CitaDAO getCitaDAO() {
        return citaDAO;
    }

    /**
     * Gets the MedicamentoDAO instance.
     *
     * @return the MedicamentoDAO instance
     */
    public static MedicamentoDAO getMedicamentoDAO() {
        return medicamentoDAO;
    }

    /**
     * Gets the ServicioDAO instance.
     *
     * @return the ServicioDAO instance
     */
    public static ServicioDAO getServicioDAO() {
        return servicioDAO;
    }

    /**
     * Gets the FacturaDAO instance.
     *
     * @return the FacturaDAO instance
     */
    public static FacturaDAO getFacturaDAO() {
        return facturaDAO;
    }

    /**
     * Gets the DetalleFacturaDAO instance.
     *
     * @return the DetalleFacturaDAO instance
     */
    public static DetalleFacturaDAO getDetalleFacturaDAO() {
        return detalleFacturaDAO;
    }

    // Service Getters

    /**
     * Gets the UsuarioService instance.
     *
     * @return the UsuarioService instance
     */
    public static UsuarioService getUsuarioService() {
        return usuarioService;
    }

    /**
     * Gets the ClienteService instance.
     *
     * @return the ClienteService instance
     */
    public static ClienteService getClienteService() {
        return clienteService;
    }

    /**
     * Gets the MascotaService instance.
     *
     * @return the MascotaService instance
     */
    public static MascotaService getMascotaService() {
        return mascotaService;
    }

    /**
     * Gets the VeterinarioService instance.
     *
     * @return the VeterinarioService instance
     */
    public static VeterinarioService getVeterinarioService() {
        return veterinarioService;
    }

    /**
     * Gets the CitaService instance.
     *
     * @return the CitaService instance
     */
    public static CitaService getCitaService() {
        return citaService;
    }

    /**
     * Gets the MedicamentoService instance.
     *
     * @return the MedicamentoService instance
     */
    public static MedicamentoService getMedicamentoService() {
        return medicamentoService;
    }

    /**
     * Gets the ServicioService instance.
     *
     * @return the ServicioService instance
     */
    public static ServicioService getServicioService() {
        return servicioService;
    }

    /**
     * Gets the FacturaService instance.
     *
     * @return the FacturaService instance
     */
    public static FacturaService getFacturaService() {
        return facturaService;
    }

    /**
     * Gets the DetalleFacturaService instance.
     *
     * @return the DetalleFacturaService instance
     */
    public static DetalleFacturaService getDetalleFacturaService() {
        return detalleFacturaService;
    }

    // Controller Getters

    /**
     * Gets the UsuarioController instance.
     *
     * @return the UsuarioController instance
     */
    public static UsuarioController getUsuarioController() {
        return usuarioController;
    }

    /**
     * Gets the ClienteController instance.
     *
     * @return the ClienteController instance
     */
    public static ClienteController getClienteController() {
        return clienteController;
    }

    /**
     * Gets the MascotaController instance.
     *
     * @return the MascotaController instance
     */
    public static MascotaController getMascotaController() {
        return mascotaController;
    }

    /**
     * Gets the VeterinarioController instance.
     *
     * @return the VeterinarioController instance
     */
    public static VeterinarioController getVeterinarioController() {
        return veterinarioController;
    }

    /**
     * Gets the CitaController instance.
     *
     * @return the CitaController instance
     */
    public static CitaController getCitaController() {
        return citaController;
    }

    /**
     * Gets the MedicamentoController instance.
     *
     * @return the MedicamentoController instance
     */
    public static MedicamentoController getMedicamentoController() {
        return medicamentoController;
    }

    /**
     * Gets the ServicioController instance.
     *
     * @return the ServicioController instance
     */
    public static ServicioController getServicioController() {
        return servicioController;
    }

    /**
     * Gets the FacturaController instance.
     *
     * @return the FacturaController instance
     */
    public static FacturaController getFacturaController() {
        return facturaController;
    }

    /**
     * Gets the DetalleFacturaController instance.
     *
     * @return the DetalleFacturaController instance
     */
    public static DetalleFacturaController getDetalleFacturaController() {
        return detalleFacturaController;
    }

    /**
     * Gets the AuthController instance.
     *
     * @return the AuthController instance
     */
    public static AuthController getAuthController() {
        return authController;
    }

    /**
     * Injects a mock ClienteController for testing purposes.
     *
     * @param mock the mock ClienteController to inject
     */
    public static void injectMockClienteController(ClienteController mock) {
        clienteController = mock;
    }

    /**
     * Resets all components to their default implementations.
     */
    public static void reset() {
        initialize();
    }
}