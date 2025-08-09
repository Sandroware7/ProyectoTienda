/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.ClienteDTO;
import com.mycompany.proyectofinal.DTO.ClienteFrecuenteDTO;
import com.mycompany.proyectofinal.DTO.HistorialCompraDTO;
import com.mycompany.proyectofinal.DTO.ProductoDTO;
import com.mycompany.proyectofinal.DTO.ProductoRecienteDTO;
import com.mycompany.proyectofinal.DTO.ProductoVendidoDTO;
import com.mycompany.proyectofinal.DTO.VentaRecienteDTO;
import java.util.List;

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
     */
    long contarProductosBajoStock(int stockMinimo) throws DAOException;

    /**
     * Llama a sp_contar_total_productos.
     *
     * @return El número total de productos distintos registrados.
     */
    long contarTotalProductos() throws DAOException;

    /**
     * Llama a sp_total_ventas_dia.
     *
     * @return La suma total de las ventas realizadas en el día actual.
     */
    double calcularTotalVentasHoy() throws DAOException;

    /**
     * Llama a sp_total_ventas_mes_actual.
     *
     * @return La suma total de las ventas realizadas en el mes actual.
     */
    double calcularTotalVentasMesActual() throws DAOException;

    /**
     * Llama a sp_obtener_ultimas_n_ventas.
     *
     * @param limite El número máximo de ventas a devolver.
     * @return Una lista con las últimas ventas.
     */
    List<VentaRecienteDTO> obtenerUltimasVentas(int limite) throws DAOException;

    /**
     * Llama a sp_listar_n_prod.
     *
     * @param limite El número máximo de productos a devolver.
     * @return Una lista con los productos más recientemente creados.
     */
    List<ProductoRecienteDTO> listarProductosRecientes(int limite) throws DAOException;

    /**
     * Llama a sp_buscar_clientes.
     *
     * @param termino El texto a buscar en nombre, apellido o DNI.
     * @return Una lista de clientes que coinciden con el término de búsqueda.
     */
    List<ClienteDTO> buscarClientesPorTermino(String termino) throws DAOException;

    /**
     * Llama a sp_buscar_productos.
     *
     * @param termino El texto a buscar en el código o descripción del producto.
     * @return Una lista de productos que coinciden con el término de búsqueda.
     */
    List<ProductoDTO> buscarProductosPorTermino(String termino) throws DAOException;

    /**
     * Llama a sp_obtener_top_n_productos_vendidos_hoy.
     *
     * @param limite El número de productos a mostrar en el top.
     * @return Una lista de los productos más vendidos del día.
     */
    List<ProductoVendidoDTO> obtenerTopProductosVendidosHoy(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_top_n_productos_vendidos_mes_actual.
     *
     * @param limite El número de productos a mostrar en el top.
     * @return Una lista de los productos más vendidos del mes.
     */
    List<ProductoVendidoDTO> obtenerTopProductosVendidosMes(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_n_productos_menor_stock.
     *
     * @param limite El número de productos a listar.
     * @return Una lista de productos ordenados por su bajo stock.
     */
    List<ProductoVendidoDTO> obtenerProductosConMenorStock(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_top_n_productos_mas_vendidos.
     *
     * @param limite El número de productos a mostrar en el top histórico.
     * @return Una lista de los productos más vendidos históricamente.
     */
    List<ProductoVendidoDTO> obtenerTopProductosMasVendidos(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_top_clientes_frecuentes.
     *
     * @param limite El número de clientes a mostrar en el top.
     * @return Una lista de los clientes con más compras.
     */
    List<ClienteFrecuenteDTO> obtenerTopClientesFrecuentes(int limite) throws DAOException;

    /**
     * Llama a sp_obtener_historial_compras_cliente.
     *
     * @param codigoCliente El código del cliente a consultar.
     * @return El historial de todos los productos que ha comprado el cliente.
     */
    List<HistorialCompraDTO> obtenerHistorialComprasCliente(String codigoCliente) throws DAOException;
}
