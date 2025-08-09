-- ================================================================= --
-- BASE DE DATOS --
-- ================================================================= --
CREATE SCHEMA IF NOT EXISTS `BD_Proyecto_Final_G5_2.0`;
USE `BD_Proyecto_Final_G5_2.0`;

-- ================================================================= --
-- TABLAS --
-- ================================================================= --
CREATE TABLE IF NOT EXISTS `usuario`
(
    `cod_usuario`    INT AUTO_INCREMENT NOT NULL UNIQUE,
    `nombre_usuario` VARCHAR(50)        NOT NULL UNIQUE,
    `clave`          VARCHAR(255)       NOT NULL,
    `correo`         VARCHAR(100)       NOT NULL UNIQUE,
    `fecha_crea`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `fecha_modif`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`cod_usuario`)
);

CREATE TABLE IF NOT EXISTS `producto`
(
    `cod_prod`     VARCHAR(25)    NOT NULL UNIQUE,
    `descripcion`  VARCHAR(255)   NOT NULL,
    `precio_unit`  DECIMAL(10, 2) NOT NULL CHECK (precio_unit > 0),
    `stock_actual` INTEGER        NOT NULL CHECK (stock_actual >= 0),
    `ruta_imagen`  VARCHAR(255)   NOT NULL,
    `cod_usuario`  INT            NULL,
    `fecha_crea`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `fecha_modif`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`cod_prod`)
);

CREATE TABLE IF NOT EXISTS `cliente`
(
    `cod_cli`       VARCHAR(25)  NOT NULL UNIQUE,
    `nombre`        VARCHAR(255) NOT NULL,
    `apellido`      VARCHAR(255) NOT NULL,
    `dni`           VARCHAR(10)  NOT NULL,
    `direccion_cli` VARCHAR(255) NOT NULL,
    `telefono`      VARCHAR(15)  NOT NULL,
    `correo`        VARCHAR(100) NOT NULL,
    `cod_usuario`   INT          NULL,
    `fecha_crea`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `fecha_modif`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`cod_cli`)
);

CREATE TABLE IF NOT EXISTS `factura`
(
    `cod_fact`      VARCHAR(25)    NOT NULL UNIQUE,
    `cod_cli`       VARCHAR(25)    NOT NULL,
    `subtotal`      DECIMAL(10, 2) NOT NULL CHECK (subtotal >= 0),
    `igv`           DECIMAL(10, 2) NOT NULL CHECK (igv >= 0),
    `total`         DECIMAL(10, 2) NOT NULL CHECK (total >= 0),
    `fecha_emision` DATETIME       NOT NULL,
    `cod_usuario`   INT            NULL,
    PRIMARY KEY (`cod_fact`)
);

CREATE TABLE IF NOT EXISTS `detalle_factura`
(
    `cod_fact`    VARCHAR(25) NOT NULL,
    `cod_prod`    VARCHAR(25) NOT NULL,
    `cantidad`    INTEGER     NOT NULL CHECK (cantidad > 0),
    `cod_usuario` INT         NULL,
    `fecha_crea`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `fecha_modif` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`cod_fact`, `cod_prod`)
);

CREATE TABLE IF NOT EXISTS `movimiento_stock`
(
    `cod_mov`     INT         NOT NULL AUTO_INCREMENT UNIQUE,
    `cod_prod`    VARCHAR(25) NOT NULL,
    `tipo`        VARCHAR(20) NOT NULL CHECK (tipo IN ('INGRESO', 'SALIDA')),
    `cantidad`    INT         NOT NULL CHECK (cantidad > 0),
    `motivo`      VARCHAR(20) NOT NULL CHECK (motivo IN ('DEVOLUCION', 'VENTA', 'COMPRA')),
    `referencia`  VARCHAR(25) NOT NULL,
    `cod_usuario` INT         NULL,
    `fecha_crea`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `fecha_modif` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`cod_mov`)
);

-- ================================================================= --
-- RELACIONES --
-- ================================================================= --
ALTER TABLE `factura`
    ADD CONSTRAINT fk_factura__cliente_cod_cli
        FOREIGN KEY (cod_cli) REFERENCES cliente (cod_cli) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE `detalle_factura`
    ADD CONSTRAINT fk_detalle_factura__factura_cod_fact
        FOREIGN KEY (cod_fact) REFERENCES factura (cod_fact) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `detalle_factura`
    ADD CONSTRAINT fk_detalle_factura__producto_cod_prod
        FOREIGN KEY (cod_prod) REFERENCES producto (cod_prod) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE `movimiento_stock`
    ADD CONSTRAINT fk_movimiento_stock__producto
        FOREIGN KEY (cod_prod) REFERENCES producto (cod_prod) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE `factura`
    ADD CONSTRAINT fk_factura_usuario
        FOREIGN KEY (cod_usuario) REFERENCES usuario (cod_usuario) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE `cliente`
    ADD CONSTRAINT fk_cliente_usuario
        FOREIGN KEY (cod_usuario) REFERENCES usuario (cod_usuario) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE `producto`
    ADD CONSTRAINT fk_producto_usuario
        FOREIGN KEY (cod_usuario) REFERENCES usuario (cod_usuario) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE `movimiento_stock`
    ADD CONSTRAINT fk_movimiento_usuario
        FOREIGN KEY (cod_usuario) REFERENCES usuario (cod_usuario) ON UPDATE CASCADE ON DELETE SET NULL;

-- ================================================================= --
-- INDEX --
-- ================================================================= --
CREATE INDEX `idx_producto_desc` ON `producto` (`descripcion`);
CREATE INDEX `idx_producto_fechacrea` ON `producto` (`fecha_crea`);
CREATE INDEX `idx_producto_fechamod` ON `producto` (`fecha_modif`);
CREATE INDEX `idx_cliente_dni` ON `cliente` (`dni`);
CREATE INDEX `idx_cliente_nombre_apellido` ON `cliente` (`nombre`, `apellido`);
CREATE INDEX `idx_factura_codcli` ON `factura` (`cod_cli`);
CREATE INDEX `idx_detalle_factura_codfact` ON `detalle_factura` (`cod_fact`);
CREATE INDEX `idx_detalle_factura_codprod` ON `detalle_factura` (`cod_prod`);

-- ================================================================= --
-- STORED PROCEDURES PARA VALIDACIÓN --
-- ================================================================= --
DELIMITER $$

CREATE PROCEDURE `sp_priv_validar_cliente`(IN p_dni VARCHAR(10), IN p_telefono VARCHAR(15), IN p_correo VARCHAR(100))
BEGIN
    IF p_dni IS NOT NULL AND NOT p_dni REGEXP '^[0-9]{8,10}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DNI inválido. Debe tener entre 8 y 10 dígitos.';
    END IF;
    IF p_telefono IS NOT NULL AND NOT p_telefono REGEXP '^[0-9]{9}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Teléfono inválido. Debe tener 9 dígitos.';
    END IF;
    IF p_correo IS NOT NULL AND NOT p_correo REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Correo electrónico no válido.';
    END IF;
END$$

CREATE PROCEDURE `sp_priv_validar_factura`(IN p_subtotal DECIMAL(10, 2), IN p_igv DECIMAL(10, 2),
                                           IN p_total DECIMAL(10, 2))
BEGIN
    DECLARE igv_calculado DECIMAL(10, 2);
    SET igv_calculado = ROUND(p_subtotal * 0.18, 2);
    IF ROUND(p_igv, 2) <> igv_calculado THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El IGV debe ser el 18% del subtotal.';
    END IF;
    IF ROUND(p_subtotal + p_igv, 2) <> ROUND(p_total, 2) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El total debe ser igual a subtotal + IGV.';
    END IF;
END$$

CREATE PROCEDURE `sp_priv_validar_motivo_movimiento`(IN p_tipo VARCHAR(20), IN p_motivo VARCHAR(20))
BEGIN
    IF p_tipo = 'INGRESO' AND p_motivo NOT IN ('COMPRA', 'DEVOLUCION') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Motivo inválido para un INGRESO. Permitidos: COMPRA, DEVOLUCION.';
    END IF;
    IF p_tipo = 'SALIDA' AND p_motivo NOT IN ('VENTA') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Motivo inválido para una SALIDA. Permitido: VENTA.';
    END IF;
END$$

DELIMITER ;

-- ================================================================= --
-- TRIGGERS DE VALIDACIÓN--
-- ================================================================= --

-- Validar datos de cliente --
DELIMITER $$
CREATE TRIGGER IF NOT EXISTS validar_datos_al_crear_cliente
    BEFORE INSERT
    ON cliente
    FOR EACH ROW
BEGIN
    CALL sp_priv_validar_cliente(NEW.dni, NEW.telefono, NEW.correo);
END$$

CREATE TRIGGER IF NOT EXISTS validar_datos_al_modificar_cliente
    BEFORE UPDATE
    ON cliente
    FOR EACH ROW
BEGIN
    CALL sp_priv_validar_cliente(NEW.dni, NEW.telefono, NEW.correo);
END$$

-- Validar datos de factura --
CREATE TRIGGER IF NOT EXISTS validar_campos_al_insertar_factura
    BEFORE INSERT
    ON factura
    FOR EACH ROW
BEGIN
    CALL sp_priv_validar_factura(NEW.subtotal, NEW.igv, NEW.total);
END$$

CREATE TRIGGER IF NOT EXISTS validar_campos_al_actualizar_factura
    BEFORE UPDATE
    ON factura
    FOR EACH ROW
BEGIN
    CALL sp_priv_validar_factura(NEW.subtotal, NEW.igv, NEW.total);
END$$

-- Validar motivo y tipo de movimiento --
CREATE TRIGGER IF NOT EXISTS validar_motivo_al_registrar_movimiento
    BEFORE INSERT
    ON movimiento_stock
    FOR EACH ROW
BEGIN
    CALL sp_priv_validar_motivo_movimiento(NEW.tipo, NEW.motivo);
END$$

CREATE TRIGGER IF NOT EXISTS validar_motivo_al_actualizar_movimiento
    BEFORE UPDATE
    ON movimiento_stock
    FOR EACH ROW
BEGIN
    CALL sp_priv_validar_motivo_movimiento(NEW.tipo, NEW.motivo);
END$$

DELIMITER ;

-- ================================================================= --
-- TRIGGERS DE LÓGICA DE NEGOCIO--
-- ================================================================= --

DELIMITER $$
-- Registro de movimiento al crear un detalle de factura --
CREATE TRIGGER IF NOT EXISTS registrar_movimiento_al_registrar_detalle
    AFTER INSERT
    ON detalle_factura
    FOR EACH ROW
BEGIN
    INSERT INTO movimiento_stock (cod_prod, tipo, cantidad, motivo, referencia, cod_usuario)
    VALUES (NEW.cod_prod, 'SALIDA', NEW.cantidad, 'VENTA', NEW.cod_fact, NEW.cod_usuario);
END$$

-- Actualizar movimiento por actualización de detalle de factura --
CREATE TRIGGER IF NOT EXISTS actualizar_movimiento_al_modificar_detalle
    AFTER UPDATE
    ON detalle_factura
    FOR EACH ROW
BEGIN
    UPDATE movimiento_stock
    SET cantidad    = NEW.cantidad,
        fecha_modif = CURRENT_TIMESTAMP,
        cod_usuario = NEW.cod_usuario
    WHERE cod_prod = NEW.cod_prod
      AND referencia = NEW.cod_fact
      AND motivo = 'VENTA'
      AND tipo = 'SALIDA'
    LIMIT 1;
END$$

-- Crear movimiento compensatorio al eliminar un detalle de factura --
CREATE TRIGGER IF NOT EXISTS anular_movimiento_al_eliminar_detalle
    AFTER DELETE
    ON detalle_factura
    FOR EACH ROW
BEGIN
    INSERT INTO movimiento_stock(cod_prod, tipo, cantidad, motivo, referencia, cod_usuario)
    VALUES (OLD.cod_prod, 'INGRESO', OLD.cantidad, 'DEVOLUCION', OLD.cod_fact, OLD.cod_usuario);
END$$

-- ================================================================= --
-- TRIGGERS DE ACTUALIZACIÓN DE STOCK --
-- ================================================================= --
CREATE TRIGGER IF NOT EXISTS actualizar_stock_al_registrar_movimiento
    AFTER INSERT
    ON movimiento_stock
    FOR EACH ROW
BEGIN
    IF NEW.tipo = 'INGRESO' THEN
        UPDATE producto SET stock_actual = stock_actual + NEW.cantidad WHERE cod_prod = NEW.cod_prod;
    ELSEIF NEW.tipo = 'SALIDA' THEN
        IF (SELECT stock_actual FROM producto WHERE cod_prod = NEW.cod_prod) < NEW.cantidad THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay stock suficiente para completar la operación.';
        END IF;
        UPDATE producto SET stock_actual = stock_actual - NEW.cantidad WHERE cod_prod = NEW.cod_prod;
    END IF;
END$$

CREATE TRIGGER IF NOT EXISTS actualizar_stock_al_modificar_movimiento
    BEFORE UPDATE
    ON movimiento_stock
    FOR EACH ROW
BEGIN
    IF OLD.tipo = 'INGRESO' THEN
        UPDATE producto SET stock_actual = stock_actual - OLD.cantidad WHERE cod_prod = OLD.cod_prod;
    ELSEIF OLD.tipo = 'SALIDA' THEN
        UPDATE producto SET stock_actual = stock_actual + OLD.cantidad WHERE cod_prod = OLD.cod_prod;
    END IF;

    IF NEW.tipo = 'INGRESO' THEN
        UPDATE producto SET stock_actual = stock_actual + NEW.cantidad WHERE cod_prod = NEW.cod_prod;
    ELSEIF NEW.tipo = 'SALIDA' THEN
        IF (SELECT stock_actual FROM producto WHERE cod_prod = NEW.cod_prod) < NEW.cantidad THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Stock insuficiente para actualizar el movimiento.';
        END IF;
        UPDATE producto SET stock_actual = stock_actual - NEW.cantidad WHERE cod_prod = NEW.cod_prod;
    END IF;
END$$

CREATE TRIGGER IF NOT EXISTS actualizar_stock_al_eliminar_movimiento
    BEFORE DELETE
    ON movimiento_stock
    FOR EACH ROW
BEGIN
    IF OLD.tipo = 'INGRESO' THEN
        IF (SELECT stock_actual FROM producto WHERE cod_prod = OLD.cod_prod) < OLD.cantidad THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT =
                    'No se puede eliminar el ingreso, el stock resultante sería negativo.';
        END IF;
        UPDATE producto SET stock_actual = stock_actual - OLD.cantidad WHERE cod_prod = OLD.cod_prod;
    ELSEIF OLD.tipo = 'SALIDA' THEN
        UPDATE producto SET stock_actual = stock_actual + OLD.cantidad WHERE cod_prod = OLD.cod_prod;
    END IF;
END$$

DELIMITER ;

-- ================================================================= --
-- STORED PROCEDURES PARA CRUD --
-- ================================================================= --
DELIMITER $$

-- CREATE - Insertar nuevo producto --
CREATE PROCEDURE IF NOT EXISTS sp_insertar_producto(
    IN p_cod_prod VARCHAR(25), IN p_descripcion VARCHAR(255), IN p_precio_unit DECIMAL(10, 2),
    IN p_stock_actual INT, IN p_ruta_imagen VARCHAR(255), IN p_cod_usuario INT
)
BEGIN
    INSERT INTO producto (cod_prod, descripcion, precio_unit, stock_actual, ruta_imagen, cod_usuario)
    VALUES (p_cod_prod, p_descripcion, p_precio_unit, p_stock_actual, p_ruta_imagen, p_cod_usuario);
END$$

-- READ - Obtener producto por código --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_producto_por_codigo(IN p_cod_prod VARCHAR(25))
BEGIN
    SELECT cod_prod, descripcion, precio_unit, stock_actual, ruta_imagen, fecha_crea, fecha_modif
    FROM producto
    WHERE cod_prod = p_cod_prod;
END$$

-- UPDATE - Actualizar datos de un producto --
CREATE PROCEDURE IF NOT EXISTS sp_actualizar_producto(
    IN p_cod_prod VARCHAR(25), IN p_descripcion VARCHAR(255), IN p_precio_unit DECIMAL(10, 2),
    IN p_stock_actual INT, IN p_ruta_imagen VARCHAR(255), IN p_cod_usuario INT
)
BEGIN
    UPDATE producto
    SET descripcion  = p_descripcion,
        precio_unit  = p_precio_unit,
        stock_actual = p_stock_actual,
        ruta_imagen  = p_ruta_imagen,
        cod_usuario  = p_cod_usuario
    WHERE cod_prod = p_cod_prod;
END$$

-- DELETE - Eliminar producto --
CREATE PROCEDURE IF NOT EXISTS sp_eliminar_producto(IN p_cod_prod VARCHAR(25))
BEGIN
    IF EXISTS (SELECT 1 FROM detalle_factura WHERE cod_prod = p_cod_prod) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No se puede eliminar el producto porque está asociado a facturas.';
    END IF;
    IF EXISTS (SELECT 1 FROM movimiento_stock WHERE cod_prod = p_cod_prod) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT =
                'No se puede eliminar el producto porque tiene movimientos de stock registrados.';
    END IF;
    DELETE FROM producto WHERE cod_prod = p_cod_prod;
END$$

-- CREATE - Insertar nuevo cliente --
CREATE PROCEDURE IF NOT EXISTS sp_insertar_cliente(
    IN p_cod_cli VARCHAR(25), IN p_nombre VARCHAR(255), IN p_apellido VARCHAR(255), IN p_dni VARCHAR(10),
    IN p_direccion_cli VARCHAR(255), IN p_telefono VARCHAR(15), IN p_correo VARCHAR(100), IN p_cod_usuario INT
)
BEGIN
    INSERT INTO cliente (cod_cli, nombre, apellido, dni, direccion_cli, telefono, correo, cod_usuario)
    VALUES (p_cod_cli,
            p_nombre,
            p_apellido,
            p_dni,
            p_direccion_cli,
            p_telefono,
            p_correo,
            p_cod_usuario);
END$$

-- READ - Obtener cliente por código --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_cliente_por_codigo(IN p_cod_cli VARCHAR(25))
BEGIN
    SELECT cod_cli,
           nombre,
           apellido,
           dni,
           direccion_cli,
           telefono,
           correo,
           cod_usuario,
           fecha_crea,
           fecha_modif
    FROM cliente
    WHERE cod_cli = p_cod_cli;
END$$

-- UPDATE - Actualizar datos de un cliente --
CREATE PROCEDURE IF NOT EXISTS sp_actualizar_cliente(
    IN p_cod_cli VARCHAR(25), IN p_nombre VARCHAR(255), IN p_apellido VARCHAR(255), IN p_dni VARCHAR(10),
    IN p_direccion_cli VARCHAR(255), IN p_telefono VARCHAR(15), IN p_correo VARCHAR(100), IN p_cod_usuario INT
)
BEGIN
    UPDATE cliente
    SET nombre        = p_nombre,
        apellido      = p_apellido,
        dni           = p_dni,
        direccion_cli = p_direccion_cli,
        telefono      = p_telefono,
        correo        = p_correo,
        cod_usuario   = p_cod_usuario
    WHERE cod_cli = p_cod_cli;
END$$

-- DELETE - Eliminar cliente --
CREATE PROCEDURE IF NOT EXISTS sp_eliminar_cliente(IN p_cod_cli VARCHAR(25))
BEGIN
    IF EXISTS (SELECT 1 FROM factura WHERE cod_cli = p_cod_cli) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No se puede eliminar el cliente porque tiene facturas registradas.';
    END IF;
    DELETE FROM cliente WHERE cod_cli = p_cod_cli;
END$$

-- ================================================================= --
-- STORED PROCEDURES PARA REPORTERÍA --
-- ================================================================= --

-- Devuelve datos del usuario --
CREATE PROCEDURE sp_obtener_usuario(IN p_usuario VARCHAR(50))
BEGIN
    SELECT nombre_usuario, clave, correo FROM usuario WHERE nombre_usuario = p_usuario;
END$$

-- Devuelve la cantidad de productos con stock menor a un valor dado (p_stock) --
CREATE PROCEDURE IF NOT EXISTS sp_contar_productos_stock_menor_a(IN p_stock INT)
BEGIN
    SELECT COUNT(*) AS cantidad_productos_bajo_stock FROM producto WHERE stock_actual < p_stock;
END$$

-- Devuelve el total de productos registrados en la tabla "producto" --
CREATE PROCEDURE IF NOT EXISTS sp_contar_total_productos()
BEGIN
    SELECT COUNT(*) AS total_productos FROM producto;
END$$

-- Devuelve el monto total de ventas realizadas en el día actual --
CREATE PROCEDURE IF NOT EXISTS sp_total_ventas_dia()
BEGIN
    SELECT IFNULL(SUM(total), 0) AS total_ventas_dia FROM factura WHERE DATE(fecha_emision) = CURRENT_DATE();
END$$

-- Devuelve el monto total de ventas realizadas en el mes actual --
CREATE PROCEDURE IF NOT EXISTS sp_total_ventas_mes_actual()
BEGIN
    SELECT IFNULL(SUM(total), 0) AS total_ventas_mes
    FROM factura
    WHERE YEAR(fecha_emision) = YEAR(CURDATE())
      AND MONTH(fecha_emision) = MONTH(CURDATE());
END$$

-- Devuelve las últimas N ventas con la fecha, cliente y total --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_ultimas_n_ventas(IN p_limit INT)
BEGIN
    SELECT DATE(f.fecha_emision) AS fecha_emision, CONCAT(c.nombre, ' ', c.apellido) AS cliente, f.total AS total
    FROM factura f
             JOIN cliente c ON f.cod_cli = c.cod_cli
    ORDER BY f.fecha_emision DESC
    LIMIT p_limit;
END$$

-- Lista N productos más recientes con datos clave --
CREATE PROCEDURE IF NOT EXISTS sp_listar_n_prod(IN p_limit INT)
BEGIN
    SELECT cod_prod, descripcion, stock_actual, fecha_crea, fecha_modif
    FROM producto
    ORDER BY fecha_crea DESC
    LIMIT p_limit;
END$$

-- Busca clientes por nombre, apellido o DNI --
CREATE PROCEDURE IF NOT EXISTS sp_buscar_clientes(IN p_termino VARCHAR(255))
BEGIN
    SELECT nombre, apellido, dni, direccion_cli, telefono
    FROM cliente
    WHERE nombre LIKE CONCAT('%', p_termino, '%')
       OR apellido LIKE CONCAT('%', p_termino, '%')
       OR dni LIKE CONCAT('%', p_termino, '%');
END$$

-- Busca productos por código o descripción --
CREATE PROCEDURE IF NOT EXISTS sp_buscar_productos(IN p_termino VARCHAR(255))
BEGIN
    SELECT cod_prod, descripcion, precio_unit, stock_actual
    FROM producto
    WHERE cod_prod LIKE CONCAT('%', p_termino, '%')
       OR descripcion LIKE CONCAT('%', p_termino, '%');
END$$

-- Devuelve el detalle completo de una factura específica --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_factura_detalle(IN p_cod_fact VARCHAR(25))
BEGIN
    SELECT f.cod_fact                    AS codigo_factura,
           DATE(f.fecha_emision)         AS fecha_venta,
           c.nombre                      AS nombre_cliente,
           c.apellido                    AS apellido_cliente,
           p.descripcion                 AS nombre_producto,
           df.cantidad                   AS cantidad,
           p.precio_unit                 AS precio_unitario,
           (df.cantidad * p.precio_unit) AS subtotal_por_producto,
           f.subtotal                    AS subtotal_factura,
           f.igv                         AS igv_factura,
           f.total                       AS total_factura
    FROM factura f
             JOIN cliente c ON f.cod_cli = c.cod_cli
             JOIN detalle_factura df ON df.cod_fact = f.cod_fact
             JOIN producto p ON df.cod_prod = p.cod_prod
    WHERE f.cod_fact = p_cod_fact;
END$$

-- Muestra los N productos más vendidos en el día actual --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_top_n_productos_vendidos_hoy(IN p_limit INT)
BEGIN
    SELECT p.cod_prod                       AS codigo_producto,
           p.descripcion                    AS descripcion_producto,
           p.stock_actual                   AS stock_actual,
           SUM(df.cantidad)                 AS total_cantidad_vendida,
           SUM(df.cantidad * p.precio_unit) AS total_vendido
    FROM factura f
             JOIN detalle_factura df ON df.cod_fact = f.cod_fact
             JOIN producto p ON df.cod_prod = p.cod_prod
    WHERE DATE(f.fecha_emision) = CURRENT_DATE()
    GROUP BY p.cod_prod, p.descripcion, p.stock_actual
    ORDER BY total_cantidad_vendida DESC
    LIMIT p_limit;
END$$

-- Muestra los N productos más vendidos en el mes actual --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_top_n_productos_vendidos_mes_actual(IN p_limit INT)
BEGIN
    SELECT p.cod_prod                       AS codigo_producto,
           p.descripcion                    AS descripcion_producto,
           p.stock_actual                   AS stock_actual,
           SUM(df.cantidad)                 AS total_cantidad_vendida,
           SUM(df.cantidad * p.precio_unit) AS total_vendido
    FROM factura f
             JOIN detalle_factura df ON df.cod_fact = f.cod_fact
             JOIN producto p ON df.cod_prod = p.cod_prod
    WHERE YEAR(f.fecha_emision) = YEAR(CURDATE())
      AND MONTH(f.fecha_emision) = MONTH(CURDATE())
    GROUP BY p.cod_prod, p.descripcion, p.stock_actual
    ORDER BY total_cantidad_vendida DESC
    LIMIT p_limit;
END$$

-- Lista N productos con menor stock, incluyendo sus ventas totales --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_n_productos_menor_stock(IN p_limit INT)
BEGIN
    SELECT p.cod_prod                                    AS codigo_producto,
           p.descripcion                                 AS descripcion_producto,
           p.stock_actual                                AS stock_actual,
           COALESCE(SUM(df.cantidad), 0)                 AS total_cantidad_vendida,
           COALESCE(SUM(df.cantidad * p.precio_unit), 0) AS total_vendido
    FROM producto p
             LEFT JOIN detalle_factura df ON df.cod_prod = p.cod_prod
             LEFT JOIN factura f ON df.cod_fact = f.cod_fact
    GROUP BY p.cod_prod, p.descripcion, p.stock_actual
    ORDER BY p.stock_actual, total_cantidad_vendida DESC
    LIMIT p_limit;
END$$

-- Muestra los N productos más vendidos históricamente --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_top_n_productos_mas_vendidos(IN p_limit INT)
BEGIN
    SELECT p.cod_prod                       AS codigo_producto,
           p.descripcion                    AS descripcion_producto,
           p.stock_actual                   AS stock_actual,
           SUM(df.cantidad)                 AS total_cantidad_vendida,
           SUM(df.cantidad * p.precio_unit) AS total_vendido
    FROM detalle_factura df
             JOIN producto p ON df.cod_prod = p.cod_prod
             JOIN factura f ON df.cod_fact = f.cod_fact
    GROUP BY p.cod_prod, p.descripcion, p.stock_actual
    ORDER BY total_cantidad_vendida DESC
    LIMIT p_limit;
END$$

-- Busca N clientes por cualquier campo, mostrando todos sus datos --
CREATE PROCEDURE IF NOT EXISTS sp_buscar_n_clientes(IN p_termino VARCHAR(255), IN p_limit INT)
BEGIN
    SELECT cod_cli, nombre, apellido, dni, direccion_cli, telefono, correo
    FROM cliente
    WHERE cod_cli LIKE CONCAT('%', p_termino, '%')
       OR nombre LIKE CONCAT('%', p_termino, '%')
       OR apellido LIKE CONCAT('%', p_termino, '%')
       OR dni LIKE CONCAT('%', p_termino, '%')
       OR direccion_cli LIKE CONCAT('%', p_termino, '%')
       OR telefono LIKE CONCAT('%', p_termino, '%')
       OR correo LIKE CONCAT('%', p_termino, '%')
    ORDER BY fecha_crea DESC
    LIMIT p_limit;
END$$

-- Muestra los N clientes más frecuentes por número de facturas --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_top_clientes_frecuentes(IN p_limit INT)
BEGIN
    SELECT ROW_NUMBER() OVER (ORDER BY COUNT(f.cod_fact) DESC) AS posicion,
           c.cod_cli                                           AS codcliente,
           c.nombre,
           c.apellido
    FROM cliente c
             JOIN factura f ON c.cod_cli = f.cod_cli
    GROUP BY c.cod_cli, c.nombre, c.apellido
    ORDER BY COUNT(f.cod_fact) DESC
    LIMIT p_limit;
END$$

-- Muestra el historial de compra de un cliente dado su código --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_historial_compras_cliente(IN p_cod_cli VARCHAR(25))
BEGIN
    SELECT p.cod_prod            AS cod_producto,
           p.descripcion         AS descripcion,
           DATE(f.fecha_emision) AS fecha_compra,
           df.cantidad           AS cantidad
    FROM factura f
             JOIN detalle_factura df ON f.cod_fact = df.cod_fact
             JOIN producto p ON df.cod_prod = p.cod_prod
    WHERE f.cod_cli = p_cod_cli
    ORDER BY f.fecha_emision DESC;
END$$

DELIMITER ;