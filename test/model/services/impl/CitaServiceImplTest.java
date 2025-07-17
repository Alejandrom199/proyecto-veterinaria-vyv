package model.services.impl;

import model.dao.CitaDAO;
import model.entities.Cita;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The type Cita service impl test.
 */
@ExtendWith(MockitoExtension.class)
public class CitaServiceImplTest {

    @Mock
    private CitaDAO citaDao;

    @InjectMocks
    private CitaServiceImpl citaService;

    private Cita citaValida;
    private Cita citaInvalida;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        citaService = new CitaServiceImpl(citaDao);

        // Configurar cita válida
        citaValida = new Cita();
        citaValida.setIdCita(1);
        citaValida.setFechaHora(LocalDateTime.now().plusDays(1));
        citaValida.setMotivo("Consulta general");
        citaValida.setEstado("Programada");
        citaValida.setIdMascota(1);
        citaValida.setIdVeterinario(1);

        // Configurar cita inválida
        citaInvalida = new Cita();
        citaInvalida.setFechaHora(null);
        citaInvalida.setMotivo("");
    }

    /**
     * Guardar cita con cita valida deberia llamar al dao.
     *
     * @throws Exception the exception
     */
// Tests para guardarCita
    @Test
    public void guardarCita_ConCitaValida_DeberiaLlamarAlDao() throws Exception {
        // Arrange
        doNothing().when(citaDao).guardar(any(Cita.class));

        // Act
        citaService.guardarCita(citaValida);

        // Assert
        verify(citaDao, times(1)).guardar(citaValida);
    }

    /**
     * Guardar cita con fecha nula deberia lanzar excepcion.
     *
     * @throws PersistenceException the persistence exception
     */
    @Test
    public void guardarCita_ConFechaNula_DeberiaLanzarExcepcion() throws PersistenceException {
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.guardarCita(citaInvalida);
        });

        assertEquals("La fecha/hora es requerida", exception.getMessage());
        verify(citaDao, never()).guardar(any());
    }

    /**
     * Guardar cita con motivo vacio deberia lanzar excepcion.
     *
     * @throws PersistenceException the persistence exception
     */
    @Test
    public void guardarCita_ConMotivoVacio_DeberiaLanzarExcepcion() throws PersistenceException {
        // Arrange
        citaInvalida.setFechaHora(LocalDateTime.now());

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.guardarCita(citaInvalida);
        });

        assertEquals("El motivo es requerido", exception.getMessage());
        verify(citaDao, never()).guardar(any());
    }

    /**
     * Guardar cita con error en dao deberia lanzar excepcion.
     *
     * @throws Exception the exception
     */
    @Test
    public void guardarCita_ConErrorEnDao_DeberiaLanzarExcepcion() throws Exception {
        // Arrange
        doThrow(new PersistenceException("Error de base de datos")).when(citaDao).guardar(any());

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.guardarCita(citaValida);
        });

        assertTrue(exception.getMessage().contains("Error al guardar la cita"));
    }

    /**
     * Obtener todas citas deberia retornar lista de citas.
     *
     * @throws Exception the exception
     */
// Tests para obtenerTodasCitas
    @Test
    public void obtenerTodasCitas_DeberiaRetornarListaDeCitas() throws Exception {
        // Arrange
        List<Cita> citasMock = new ArrayList<>();
        citasMock.add(citaValida);
        when(citaDao.obtenerTodos()).thenReturn(citasMock);

        // Act
        List<Cita> resultado = citaService.obtenerTodasCitas();

        // Assert
        assertEquals(1, resultado.size());
        verify(citaDao, times(1)).obtenerTodos();
    }

    /**
     * Obtener todas citas con error en dao deberia lanzar excepcion.
     *
     * @throws Exception the exception
     */
    @Test
    public void obtenerTodasCitas_ConErrorEnDao_DeberiaLanzarExcepcion() throws Exception {
        // Arrange
        when(citaDao.obtenerTodos()).thenThrow(new PersistenceException("Error DB"));

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.obtenerTodasCitas();
        });

        assertTrue(exception.getMessage().contains("Error al obtener citas"));
    }

    /**
     * Buscar cita por id con id existente deberia retornar cita.
     *
     * @throws Exception the exception
     */
// Tests para buscarCitaPorId
    @Test
    public void buscarCitaPorId_ConIdExistente_DeberiaRetornarCita() throws Exception {
        // Arrange
        when(citaDao.buscarPorId(1)).thenReturn(citaValida);

        // Act
        Cita resultado = citaService.buscarCitaPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals("Consulta general", resultado.getMotivo());
        verify(citaDao, times(1)).buscarPorId(1);
    }

    /**
     * Buscar cita por id con error en dao deberia lanzar excepcion.
     *
     * @throws Exception the exception
     */
    @Test
    public void buscarCitaPorId_ConErrorEnDao_DeberiaLanzarExcepcion() throws Exception {
        // Arrange
        when(citaDao.buscarPorId(1)).thenThrow(new PersistenceException("Error DB"));

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.buscarCitaPorId(1);
        });

        assertTrue(exception.getMessage().contains("Error al buscar cita"));
    }

    /**
     * Actualizar cita con cita valida deberia llamar al dao.
     *
     * @throws Exception the exception
     */
// Tests para actualizarCita
    @Test
    public void actualizarCita_ConCitaValida_DeberiaLlamarAlDao() throws Exception {
        // Arrange
        doNothing().when(citaDao).actualizar(any(Cita.class));

        // Act
        citaService.actualizarCita(citaValida);

        // Assert
        verify(citaDao, times(1)).actualizar(citaValida);
    }

    /**
     * Actualizar cita con fecha nula deberia lanzar excepcion.
     *
     * @throws PersistenceException the persistence exception
     */
    @Test
    public void actualizarCita_ConFechaNula_DeberiaLanzarExcepcion() throws PersistenceException {
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.actualizarCita(citaInvalida);
        });

        assertEquals("La fecha/hora es requerida", exception.getMessage());
        verify(citaDao, never()).actualizar(any());
    }

    /**
     * Actualizar cita con error en dao deberia lanzar excepcion.
     *
     * @throws Exception the exception
     */
    @Test
    public void actualizarCita_ConErrorEnDao_DeberiaLanzarExcepcion() throws Exception {
        // Arrange
        doThrow(new PersistenceException("Error de base de datos")).when(citaDao).actualizar(any());

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.actualizarCita(citaValida);
        });

        assertTrue(exception.getMessage().contains("Error al actualizar cita"));
    }

    /**
     * Eliminar cita con id valido deberia llamar al dao.
     *
     * @throws Exception the exception
     */
// Tests para eliminarCita
    @Test
    public void eliminarCita_ConIdValido_DeberiaLlamarAlDao() throws Exception {
        // Arrange
        doNothing().when(citaDao).eliminar(anyInt());

        // Act
        citaService.eliminarCita(1);

        // Assert
        verify(citaDao, times(1)).eliminar(1);
    }

    /**
     * Eliminar cita con error en dao deberia lanzar excepcion.
     *
     * @throws Exception the exception
     */
    @Test
    public void eliminarCita_ConErrorEnDao_DeberiaLanzarExcepcion() throws Exception {
        // Arrange
        doThrow(new PersistenceException("Error de base de datos")).when(citaDao).eliminar(anyInt());

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.eliminarCita(1);
        });

        assertTrue(exception.getMessage().contains("Error al eliminar cita"));
    }

    /**
     * Buscar cita por id con id inexistente deberia lanzar excepcion.
     *
     * @throws Exception the exception
     */
    @Test
    public void buscarCitaPorId_ConIdInexistente_DeberiaLanzarExcepcion() throws Exception {
        // Arrange
        when(citaDao.buscarPorId(-1)).thenReturn(null); // Simula que no se encuentra la cita

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            citaService.buscarCitaPorId(-1);
        });

        // Verifica el mensaje de la excepción
        assertEquals("No se encontró una cita con el ID proporcionado", exception.getMessage());
        verify(citaDao, times(1)).buscarPorId(-1); // Verifica que se llamó al DAO con el ID correcto
    }
}