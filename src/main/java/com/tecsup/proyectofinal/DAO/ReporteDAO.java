/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecsup.proyectofinal.DAO;

import com.tecsup.proyectofinal.DTO.ClienteDTO;
import com.tecsup.proyectofinal.DTO.ClienteFrecuenteDTO;
import com.tecsup.proyectofinal.DTO.HistorialCompraDTO;
import com.tecsup.proyectofinal.DTO.ProductoDTO;
import com.tecsup.proyectofinal.DTO.ProductoRecienteDTO;
import com.tecsup.proyectofinal.DTO.ProductoVendidoDTO;
import com.tecsup.proyectofinal.DTO.VentaRecienteDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Joao Higa
 */
public interface ReporteDAO {

    /**
     * Llama a sp_contar_productos_stock_menor_a.
     *
     * @param stockMinimo El umbral de stock para la alerta.
     * @return La cantidad de productos cuyo stock es menor al umbral.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    long contarProductosBajoStock(int stockMinimo) throws DAOException;

    /**
     * Llama a sp_contar_total_productos.
     *
     * @return El número total de productos distintos registrados.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    long contarTotalProductos() throws DAOException;

    /**
     * Llama a sp_total_ventas_dia.
     *
     * @return La suma total de las ventas realizadas en el día actual.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    double calcularTotalVentasHoy() throws DAOException;

    /**
     * Llama a sp_total_ventas_mes_actual.
     *
     * @return La suma total de las ventas realizadas en el mes actual.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    double calcularTotalVentasMesActual() throws DAOException;

    /**
     * Llama a sp_obtener_ultimas_n_ventas.
     *
     * @param limite El número máximo de ventas a devolver.
     * @return Una lista con las últimas ventas.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<VentaRecienteDTO>> obtenerUltimasVentas(int limite) throws DAOException;

    /**
     * Llama a sp_listar_n_prod.
     *
     * @param limite El número máximo de productos a devolver.
     * @return Una lista con los productos más recientemente creados.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<ProductoRecienteDTO>> listarProductosRecientes(int limite) throws DAOException;

    /**
     * Llama a sp_buscar_clientes.
     *
     * @param termino El texto a buscar en nombre, apellido o DNI.
     * @return Una lista de clientes que coinciden con el término de búsqueda.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<ClienteDTO>> buscarClientesPorTermino(String termino) throws DAOException;

    /**
     * Llama a sp_buscar_productos.
     *
     * @param termino El texto a buscar en el código o descripción del producto.
     * @return Una lista de productos que coinciden con el término de búsqueda.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<ProductoDTO>> buscarProductosPorTermino(String termino) throws DAOException;

    /**
     * Llama a sp_obtener_top_n_productos_vendidos_hoy.
     *
     * @param limite El número de productos a mostrar en el top.
     * @return Una lista de los productos más vendidos del día.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<ProductoVendidoDTO>> obtenerTopProductosVendidosHoy(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_top_n_productos_vendidos_mes_actual.
     *
     * @param limite El número de productos a mostrar en el top.
     * @return Una lista de los productos más vendidos del mes.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<ProductoVendidoDTO>> obtenerTopProductosVendidosMes(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_n_productos_menor_stock.
     *
     * @param limite El número de productos a listar.
     * @return Una lista de productos ordenados por su bajo stock.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<ProductoVendidoDTO>> obtenerProductosConMenorStock(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_top_n_productos_mas_vendidos.
     *
     * @param limite El número de productos a mostrar en el top histórico.
     * @return Una lista de los productos más vendidos históricamente.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<ProductoVendidoDTO>> obtenerTopProductosMasVendidos(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_top_clientes_frecuentes.
     *
     * @param limite El número de clientes a mostrar en el top.
     * @return Una lista de los clientes con más compras.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<ClienteFrecuenteDTO>> obtenerTopClientesFrecuentes(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_historial_compras_cliente.
     *
     * @param codigoCliente El código del cliente a consultar.
     * @return El historial de todos los productos que ha comprado el cliente.
     * @throws com.tecsup.proyectofinal.util.DAOException
     */
    Optional<List<HistorialCompraDTO>> obtenerHistorialComprasCliente(String codigoCliente) throws DAOException;
}
