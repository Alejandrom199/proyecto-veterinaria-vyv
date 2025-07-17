package utils;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class providing common Swing component styling and positioning methods.
 * Contains predefined color schemes and layout helpers for consistent UI.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class SwingUtils {

    private static final Color COLOR_AZUL = new Color(0, 120, 215);
    private static final Color COLOR_ROJO = new Color(205, 22, 0);
    private static final Color COLOR_VERDE = new Color(0, 184,6);
    private static final Color COLOR_TEXTO_BLANCO = Color.WHITE;

    /**
     * Enum representing available button color types.
     */
    public enum ColorTipo {
        /**
         * Azul color tipo.
         */
        AZUL,
        /**
         * Rojo color tipo.
         */
        ROJO,
        /**
         * Verde color tipo.
         */
        VERDE
    }

    /**
     * Centra una ventana en la pantalla, considerando la barra de tareas.
     *
     * @param window La ventana a centrar
     */
    public static void centrarVentana(Window window) {
        if (window == null) return;

        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        window.setLocation(
                bounds.x + (bounds.width - window.getWidth()) / 2,
                bounds.y + (bounds.height - window.getHeight()) / 2
        );
    }

    /**
     * Aplica estilo moderno a un botón según el color elegido.
     *
     * @param color          Tipo de color definido en el enum {@link ColorTipo}
     * @param boton          El JButton a estilizar
     * @param conEfectoHover Si es true, añade efecto hover (cambio de color al pasar el mouse)
     */
    public static void aplicarEstilo(ColorTipo color, JButton boton, boolean conEfectoHover) {
        if (boton == null || color == null) return;

        switch (color) {
            case AZUL:
                aplicarColor(COLOR_AZUL, boton, conEfectoHover);
                break;
            case ROJO:
                aplicarColor(COLOR_ROJO, boton, conEfectoHover);
                break;
            case VERDE:
                aplicarColor(COLOR_VERDE, boton, conEfectoHover);
                break;
            default:
                throw new IllegalArgumentException("ColorTipo no soportado: " + color);
        }
    }

    private static void aplicarColor(Color color, JButton boton, boolean conEfectoHover) {
        boton.setBackground(color);
        boton.setForeground(COLOR_TEXTO_BLANCO);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        if (conEfectoHover) {
            boton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    boton.setBackground(color.brighter());
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    boton.setBackground(color);
                }
            });
        }
    }

    /**
     * Bloquea el redimensionamiento de una ventana.
     *
     * @param frame      JFrame a configurar
     * @param tamanoFijo Si es true, también deshabilita maximizar
     */
    public static void bloquearRedimension(JFrame frame, boolean tamanoFijo) {
        if (frame == null) return;

        frame.setResizable(false);

        if (tamanoFijo) {
            frame.setMaximumSize(frame.getSize());
            frame.setExtendedState(JFrame.NORMAL);
        }
    }
}
