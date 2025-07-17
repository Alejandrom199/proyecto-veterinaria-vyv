import controller.AuthController;
import controller.UsuarioController;
import model.dto.UsuarioDTO;
import model.entities.Usuario;
import utils.AppFactory;
import utils.SessionManager;
import view.views.IndexView;
import view.views.LoginView;

/**
 * Clase principal que inicia la aplicación del sistema veterinario.
 * Gestiona el flujo inicial de autenticación y redirección a la vista principal.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class Main{
    /**
     * Punto de entrada principal de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Método que controla el flujo inicial de la aplicación.
     * Intenta cargar credenciales guardadas y autenticar al usuario automáticamente.
     * Si no hay credenciales guardadas o la autenticación falla, muestra la vista de login.
     */
    public static void run() {
        // Intentar carga automática
        String[] credenciales = SessionManager.cargarCredenciales();

        if (credenciales != null) {
            AuthController authController = AppFactory.getAuthController();
            if (authController.autenticarUsuario(credenciales[0], credenciales[1])) {
                // Obtener usuario como en tu lógica normal
                UsuarioController usuarioController = AppFactory.getUsuarioController();
                Usuario usuario = usuarioController.buscarUsuarioPorNombreUsuario(credenciales[0]);

                new IndexView(new UsuarioDTO(
                        usuario.getNombreUsuario(),
                        usuario.getRol()
                )).setVisible(true);

                return; // Salir del método
            }
        }

        // Si no hay sesión guardada, mostrar login
        new LoginView().setVisible(true);
    }
}
