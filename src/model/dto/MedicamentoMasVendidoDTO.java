package model.dto;

/**
 * DTO para medicamentos más vendidos.
 *
 * @author Jeremy Alvarado
 * @version 1.0
 */
public record MedicamentoMasVendidoDTO(
    String nombre,
    int totalVendido
) {}
