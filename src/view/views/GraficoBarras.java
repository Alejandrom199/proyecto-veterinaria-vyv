package view.views;

import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Vista para mostrar un gráfico de barras con datos de medicamentos vendidos.
 * Muestra visualmente la comparación entre diferentes medicamentos.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class GraficoBarras extends JFrame {

    private final Map<String, Integer> datos;

    /**
     * Constructor que inicializa el gráfico de barras con los datos proporcionados.
     *
     * @param datos Mapa con los nombres de medicamentos como clave y cantidades vendidas como valor
     */
    public GraficoBarras(Map<String, Integer> datos) {
        this.datos = datos;
        setTitle("Medicamentos más vendidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 400);
        SwingUtils.centrarVentana(this);
        SwingUtils.bloquearRedimension(this, true);

        JPanel panelGrafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarGrafico((Graphics2D) g);
            }
        };

        add(panelGrafico);
    }

    private void dibujarGrafico(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();
        int padding = 70;
        int espacioBarra = 30; // Espacio entre barras
        int maxAltura = height - 2 * padding;

        int maxValor = datos.values().stream().max(Integer::compare).orElse(1);
        int anchoBarra = (width - 2 * padding) / datos.size();

        // Fuente más pequeña para etiquetas
        Font fuentePequena = new Font("SansSerif", Font.PLAIN, 10);
        g2.setFont(fuentePequena);
        FontMetrics metrics = g2.getFontMetrics();

        int x = padding;
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            String nombre = entry.getKey();
            int valor = entry.getValue();
            int altoBarra = (int) ((valor / (double) maxValor) * maxAltura);

            // Dibuja la barra
            g2.setColor(Color.BLUE);
            g2.fillRect(x, height - padding - altoBarra, anchoBarra - espacioBarra, altoBarra);

            // Dibuja valor encima de la barra
            g2.setColor(Color.BLACK);
            String valorTexto = String.valueOf(valor);
            int valorAncho = metrics.stringWidth(valorTexto);
            g2.drawString(valorTexto, x + (anchoBarra - espacioBarra - valorAncho) / 2, height - padding - altoBarra - 5);

            // Dibuja nombre debajo de la barra
            String nombreCorto = nombre.length() > 20 ? nombre.substring(0, 9) + "…" : nombre; // Opcional: truncar largo
            int nombreAncho = metrics.stringWidth(nombreCorto);
            g2.drawString(nombreCorto, x + (anchoBarra - espacioBarra - nombreAncho) / 2, height - padding + 15);

            x += anchoBarra;
        }
    }

}