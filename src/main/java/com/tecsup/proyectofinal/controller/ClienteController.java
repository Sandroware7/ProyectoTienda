/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecsup.proyectofinal.controller;

import com.tecsup.proyectofinal.model.dao.ClienteDAO;
import com.tecsup.proyectofinal.model.dao.ReporteDAO;
import com.tecsup.proyectofinal.model.dao.daoimpl.ClienteDAOImpl;
import com.tecsup.proyectofinal.model.dao.daoimpl.ReporteDAOImpl;
import com.tecsup.proyectofinal.model.dto.ClienteDTO;
import com.tecsup.proyectofinal.model.dto.ClienteFrecuenteDTO; // NUEVO: Importar DTO
import com.tecsup.proyectofinal.model.dto.HistorialCompraDTO; // NUEVO
import com.tecsup.proyectofinal.view.Clientes;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para la gestión de clientes. Maneja la lógica de negocio y la
 * interacción entre la vista (Clientes.java) y el modelo de datos (ClienteDAO).
 */
public class ClienteController {

    // Referencias a la Vista y al Modelo
    private final Clientes vista;
    private final ClienteDAO clienteDAO;
    private final ReporteDAO reporteDAO;

    /**
     * Constructor que inicializa el controlador.
     *
     * @param vista La instancia del formulario Clientes que este controlador
     * manejará.
     */
    public ClienteController(Clientes vista) {
        this.vista = vista;
        this.clienteDAO = new ClienteDAOImpl();
        this.reporteDAO = new ReporteDAOImpl();

        // Configura los listeners para los botones del formulario
        initListeners();

        // Carga los clientes existentes en la tabla al iniciar la ventana
        cargarTodosLosClientes();
        cargarClientesFrecuentes(); // NUEVO: Cargar clientes frecuentes al iniciar

    }

    /**
     * Configura los ActionListeners para los botones de la vista.
     */
    private void initListeners() {
        // Cuando se haga clic en el botón "Guardar", se ejecutará el método guardarCliente()
        vista.getGuardarClienteButton().addActionListener(e -> guardarCliente());
        vista.getBuscarClienteButton().addActionListener(e -> buscarCliente()); // NUEVO: Listener para el botón 
        vista.getActualizarClienteButton().addActionListener(e -> actualizarCliente()); // NUEVO
        vista.getEliminarClienteButton().addActionListener(e -> eliminarCliente()); // NUEVO

        // Aquí se añadirán los listeners para los otros botones (Actualizar, Eliminar, Buscar).
    }

    /**
     * Obtiene todos los clientes de la base de datos y los muestra en la
     * JTable.
     */
    private void cargarTodosLosClientes() {
        try {
            // 1. Pide al DAO la lista completa de clientes
            List<ClienteDTO> clientes = clienteDAO.listar();

            // 2. Obtiene el modelo de la tabla desde la vista para poder manipularla
            DefaultTableModel model = (DefaultTableModel) vista.getTablaClientes().getModel();

            // 3. Limpia la tabla por si tenía datos anteriores
            model.setRowCount(0);

            // 4. Recorre la lista de clientes y añade cada uno como una nueva fila
            for (ClienteDTO cliente : clientes) {
                Object[] row = {
                    cliente.codCli(),
                    cliente.nombre(),
                    cliente.apellido(),
                    cliente.dni(),
                    cliente.direccionCli(),
                    cliente.telefono(),
                    cliente.correo()
                };
                model.addRow(row);
            }
        } catch (DAOException e) {
            vista.mostrarMensaje("Error al cargar los clientes: " + e.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Recoge los datos del formulario, los valida y los guarda en la base de
     * datos. Después de guardar, refresca la tabla.
     */
    private void guardarCliente() {
        // 1. Obtener los datos del formulario
        String nombre = vista.getNombreClienteText().trim();
        String apellido = vista.getApellidoClienteText().trim();
        String dni = vista.getDniClienteText().trim();
        String direccion = vista.getDireccionClienteText().trim();
        String telefono = vista.getTelefonoClienteText().trim();
        String correo = vista.getCorreoClienteText().trim();

        // 2. Validar que los campos obligatorios no estén vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
            vista.mostrarMensaje("Los campos Nombre, Apellido y DNI son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return; // Detiene la ejecución si la validación falla
        }

        // 3. Crear el objeto DTO con los datos del formulario
        ClienteDTO nuevoCliente = new ClienteDTO(
                null, // codCli es autogenerado por la BD
                nombre,
                apellido,
                dni,
                telefono,
                correo,
                direccion,
                null, // codUsuario se obtiene de la sesión en el DAO
                null, // fechaCrea es autogenerada por la BD
                null // fechaModif es autogenerada por la BD
        );

        // 4. Intentar guardar en la base de datos
        try {
            clienteDAO.guardar(nuevoCliente);
            vista.mostrarMensaje("Cliente guardado exitosamente.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarCampos();

            // 5. ¡Paso clave! Refrescar la tabla para mostrar el nuevo cliente
            cargarTodosLosClientes();

        } catch (DAOException e) {
            // Si el DAO lanza un error, se lo mostramos al usuario
            vista.mostrarMensaje("Error al guardar el cliente: " + e.getMessage(), "Error en la Operación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarCliente() {
        // 1. Pedimos al usuario el código a buscar
        String codigoBuscado = JOptionPane.showInputDialog(
                vista,
                "Ingrese el código del cliente a buscar:",
                "Buscar Cliente",
                JOptionPane.QUESTION_MESSAGE
        );

        // 2. Si el usuario no cancela o deja el campo vacío
        if (codigoBuscado != null && !codigoBuscado.trim().isEmpty()) {
            try {
                // 3. Llamamos al DAO
                Optional<ClienteDTO> clienteOpt = clienteDAO.buscarPorCodigo(codigoBuscado.trim());

                // 4. Verificamos si el Optional contiene un cliente
                if (clienteOpt.isPresent()) {
                    ClienteDTO clienteEncontrado = clienteOpt.get();
                    // 5. Llenamos el formulario con los datos encontrados
                    vista.setCodigoClienteText(clienteEncontrado.codCli());
                    vista.setNombreClienteText(clienteEncontrado.nombre());
                    vista.setApellidoClienteText(clienteEncontrado.apellido());
                    vista.setDniClienteText(clienteEncontrado.dni());
                    vista.setDireccionClienteText(clienteEncontrado.direccionCli());
                    vista.setTelefonoClienteText(clienteEncontrado.telefono());
                    vista.setCorreoClienteText(clienteEncontrado.correo());
                    // NUEVO: Cargar historial del cliente encontrado
                    cargarHistorialCompras(clienteEncontrado.codCli());

                } else {
                    // Si el Optional está vacío, el cliente no fue encontrado
                    vista.mostrarMensaje("Cliente con código '" + codigoBuscado + "' no encontrado.", "Búsqueda Fallida", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DAOException e) {
                vista.mostrarMensaje("Error al buscar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarCliente() {
        // 1. Obtener el código del cliente del campo de texto (que no es editable)
        String codigoCliente = vista.getCodigoClienteText().trim();

        // 2. Validar que se ha seleccionado un cliente para actualizar
        if (codigoCliente.isEmpty()) {
            vista.mostrarMensaje("Por favor, busque o seleccione un cliente de la tabla antes de actualizar.", "Ningún Cliente Seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Recoger los datos (posiblemente modificados) de los campos
        String nombre = vista.getNombreClienteText().trim();
        String apellido = vista.getApellidoClienteText().trim();
        String dni = vista.getDniClienteText().trim();
        String direccion = vista.getDireccionClienteText().trim();
        String telefono = vista.getTelefonoClienteText().trim();
        String correo = vista.getCorreoClienteText().trim();

        // 4. Validar que los campos obligatorios no estén vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
            vista.mostrarMensaje("Los campos Nombre, Apellido y DNI son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 5. Crear el DTO con todos los datos, incluyendo el código
        ClienteDTO clienteActualizado = new ClienteDTO(
                codigoCliente, nombre, apellido, dni, telefono, correo, direccion,
                null, null, null // El DAO se encarga del usuario y las fechas
        );

        // 6. Llamar al DAO para actualizar
        try {
            clienteDAO.actualizar(clienteActualizado);
            vista.mostrarMensaje("Cliente actualizado exitosamente.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarCampos();
            cargarTodosLosClientes(); // Refrescar la tabla
            cargarClientesFrecuentes(); // Refrescar por si se cambió el nombre de un cliente frecuente

        } catch (DAOException e) {
            vista.mostrarMensaje("Error al actualizar el cliente: " + e.getMessage(), "Error en la Operación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCliente() {
        // 1. Obtener el código del cliente del campo de texto
        String codigoCliente = vista.getCodigoClienteText().trim();

        // 2. Validar que se ha seleccionado un cliente
        if (codigoCliente.isEmpty()) {
            vista.mostrarMensaje("Por favor, busque o seleccione un cliente antes de eliminar.", "Ningún Cliente Seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Pedir confirmación al usuario
        int confirmacion = JOptionPane.showConfirmDialog(
                vista,
                "¿Está seguro de que desea eliminar al cliente con código " + codigoCliente + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // 4. Si el usuario confirma (presiona "Sí")
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 5. Llamar al DAO para eliminar
                clienteDAO.eliminar(codigoCliente);
                vista.mostrarMensaje("Cliente eliminado exitosamente.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                vista.limpiarCampos();
                cargarTodosLosClientes(); // Refrescar la tabla
                cargarClientesFrecuentes(); // Refrescar por si el eliminado era frecuente

            } catch (DAOException e) {
                vista.mostrarMensaje("Error al eliminar el cliente: " + e.getMessage(), "Error en la Operación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // NUEVO: Método para cargar los clientes frecuentes
    private void cargarClientesFrecuentes() {
        try {
            // Puedes cambiar este número para mostrar más o menos clientes
            int topLimit = 5;
            Optional<List<ClienteFrecuenteDTO>> clientes = reporteDAO.obtenerTopClientesFrecuentes(topLimit);

            DefaultTableModel model = (DefaultTableModel) vista.getTablaClientesFrecuentes().getModel();
            model.setRowCount(0); // Limpiar la tabla

            // Llenar la tabla con los datos de los clientes frecuentes
            if (clientes.isPresent()) {
                for (ClienteFrecuenteDTO cliente : clientes.get()) {
                    Object[] row = {
                        cliente.posicion(),
                        cliente.codCliente(),
                        cliente.nombre(),
                        cliente.apellido()
                    };
                    model.addRow(row);
                }
            }

        } catch (DAOException e) {
            vista.mostrarMensaje("Error al cargar clientes frecuentes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // NUEVO: Método para cargar el historial de compras de un cliente
    private void cargarHistorialCompras(String codCli) {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaHistorial().getModel();
        model.setRowCount(0); // Limpiar la tabla de historial

        try {
            Optional<List<HistorialCompraDTO>> historial = reporteDAO.obtenerHistorialComprasCliente(codCli);
            for (HistorialCompraDTO compra : historial.orElse(Collections.emptyList())) {
                Object[] row = {
                    compra.codProducto(),
                    compra.descripcion(),
                    compra.fechaCompra(),
                    compra.cantidad()
                };
                model.addRow(row);
            }
        } catch (DAOException e) {
            vista.mostrarMensaje("Error al cargar el historial de compras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
