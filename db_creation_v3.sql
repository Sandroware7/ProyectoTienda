-- ================================================================= --
-- BASE DE DATOS --
-- ================================================================= --
DROP SCHEMA IF EXISTS `BD_Proyecto_Final_G5_2.0`;
CREATE SCHEMA IF NOT EXISTS `BD_Proyecto_Final_G5_2.0`;
USE `BD_Proyecto_Final_G5_2.0`;

-- ================================================================= --
-- ZONA DE LIMPIEZA: Elimina objetos existentes para una reinstalación limpia --
-- ================================================================= --
-- Desactivamos la revisión de claves foráneas para poder eliminar las tablas sin problemas de orden
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `detalle_factura`;
DROP TABLE IF EXISTS `movimiento_stock`;
DROP TABLE IF EXISTS `factura`;
DROP TABLE IF EXISTS `producto`;
DROP TABLE IF EXISTS `cliente`;
DROP TABLE IF EXISTS `usuario`;
DROP TABLE IF EXISTS `contador_codigos`;

-- Reactivamos la revisión de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;

-- Eliminamos rutinas (procedimientos y funciones) para asegurar que se creen las nuevas versiones
DROP FUNCTION IF EXISTS `sf_generar_siguiente_codigo`;
DROP PROCEDURE IF EXISTS `sp_priv_validar_cliente`;
DROP PROCEDURE IF EXISTS `sp_priv_validar_factura`;
DROP PROCEDURE IF EXISTS `sp_priv_validar_motivo_movimiento`;
DROP PROCEDURE IF EXISTS `sp_insertar_producto`;
DROP PROCEDURE IF EXISTS `sp_obtener_producto_por_codigo`;
DROP PROCEDURE IF EXISTS `sp_actualizar_producto`;
DROP PROCEDURE IF EXISTS `sp_eliminar_producto`;
DROP PROCEDURE IF EXISTS `sp_insertar_cliente`;
DROP PROCEDURE IF EXISTS `sp_obtener_cliente_por_codigo`;
DROP PROCEDURE IF EXISTS `sp_actualizar_cliente`;
DROP PROCEDURE IF EXISTS `sp_eliminar_cliente`;
DROP PROCEDURE IF EXISTS `sp_insertar_usuario`;
DROP PROCEDURE IF EXISTS `sp_insertar_factura`;
DROP PROCEDURE IF EXISTS `sp_obtener_usuario`;
DROP PROCEDURE IF EXISTS `sp_contar_productos_stock_menor_a`;
DROP PROCEDURE IF EXISTS `sp_contar_total_productos`;
DROP PROCEDURE IF EXISTS `sp_contar_total_stock_productos`;
DROP PROCEDURE IF EXISTS `sp_total_ventas_dia`;
DROP PROCEDURE IF EXISTS `sp_total_ventas_mes_actual`;
DROP PROCEDURE IF EXISTS `sp_obtener_ultimas_n_ventas`;
DROP PROCEDURE IF EXISTS `sp_listar_n_prod`;
DROP PROCEDURE IF EXISTS `sp_buscar_clientes`;
DROP PROCEDURE IF EXISTS `sp_listar_clientes`;
DROP PROCEDURE IF EXISTS `sp_buscar_productos`;
DROP PROCEDURE IF EXISTS `sp_obtener_factura_cabecera`;
DROP PROCEDURE IF EXISTS `sp_obtener_factura_detalles`;
DROP PROCEDURE IF EXISTS `sp_obtener_factura_detalle`;
DROP PROCEDURE IF EXISTS `sp_obtener_top_n_productos_vendidos_hoy`;
DROP PROCEDURE IF EXISTS `sp_obtener_top_n_productos_vendidos_mes_actual`;
DROP PROCEDURE IF EXISTS `sp_obtener_n_productos_menor_stock`;
DROP PROCEDURE IF EXISTS `sp_obtener_top_n_productos_mas_vendidos`;
DROP PROCEDURE IF EXISTS `sp_buscar_n_clientes`;
DROP PROCEDURE IF EXISTS `sp_obtener_top_clientes_frecuentes`;
DROP PROCEDURE IF EXISTS `sp_obtener_historial_compras_cliente`;
DROP PROCEDURE IF EXISTS `sp_obtener_siguiente_codigo_factura`;
DROP PROCEDURE IF EXISTS `sp_obtener_factura_detallada`;


-- Triggers de Validación
DROP TRIGGER IF EXISTS `validar_datos_al_crear_cliente`;
DROP TRIGGER IF EXISTS `validar_datos_al_modificar_cliente`;
DROP TRIGGER IF EXISTS `validar_campos_al_insertar_factura`;
DROP TRIGGER IF EXISTS `validar_campos_al_actualizar_factura`;
DROP TRIGGER IF EXISTS `validar_motivo_al_registrar_movimiento`;
DROP TRIGGER IF EXISTS `validar_motivo_al_actualizar_movimiento`;

-- Triggers para Asignar Códigos
DROP TRIGGER IF EXISTS `trg_before_insert_usuario`;
DROP TRIGGER IF EXISTS `trg_before_insert_producto`;
DROP TRIGGER IF EXISTS `trg_before_insert_cliente`;
DROP TRIGGER IF EXISTS `trg_before_insert_movimiento_stock`;

-- Triggers de Lógica de Negocio
DROP TRIGGER IF EXISTS `registrar_movimiento_al_registrar_detalle`;
DROP TRIGGER IF EXISTS `actualizar_movimiento_al_modificar_detalle`;
DROP TRIGGER IF EXISTS `anular_movimiento_al_eliminar_detalle`;

-- Triggers de Actualización de Stock
DROP TRIGGER IF EXISTS `actualizar_stock_al_registrar_movimiento`;
DROP TRIGGER IF EXISTS `actualizar_stock_al_modificar_movimiento`;
DROP TRIGGER IF EXISTS `actualizar_stock_al_eliminar_movimiento`;

-- ================================================================= --
-- TABLAS --
-- ================================================================= --
CREATE TABLE IF NOT EXISTS `usuario`
(
    `cod_usuario`    VARCHAR(25)  NOT NULL UNIQUE,
    `nombre_usuario` VARCHAR(50)  NOT NULL UNIQUE,
    `clave`          VARCHAR(255) NOT NULL,
    `correo`         VARCHAR(100) NOT NULL UNIQUE,
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
    `cod_usuario`  VARCHAR(25)    NULL,
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
    `cod_usuario`   VARCHAR(25)  NULL,
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
    `cod_usuario`   VARCHAR(25)    NULL,
    PRIMARY KEY (`cod_fact`)
);

CREATE TABLE IF NOT EXISTS `detalle_factura`
(
    `cod_fact`    VARCHAR(25) NOT NULL,
    `cod_prod`    VARCHAR(25) NOT NULL,
    `cantidad`    INTEGER     NOT NULL CHECK (cantidad > 0),
    `cod_usuario` VARCHAR(25) NULL,
    `fecha_crea`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `fecha_modif` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`cod_fact`, `cod_prod`)
);

CREATE TABLE IF NOT EXISTS `movimiento_stock`
(
    `cod_mov`     VARCHAR(25) NOT NULL UNIQUE,
    `cod_prod`    VARCHAR(25) NOT NULL,
    `tipo`        VARCHAR(20) NOT NULL CHECK (tipo IN ('INGRESO', 'SALIDA')),
    `cantidad`    INT         NOT NULL CHECK (cantidad > 0),
    `motivo`      VARCHAR(20) NOT NULL CHECK (motivo IN ('DEVOLUCION', 'VENTA', 'COMPRA')),
    `referencia`  VARCHAR(25) NOT NULL,
    `cod_usuario` VARCHAR(25) NULL,
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
-- TABLA PARA GUARDAR CONTADORES PARA GENERACIÓN DE CÓDIGOS --
-- ================================================================= --
CREATE TABLE IF NOT EXISTS `contador_codigos`
(
    `id_contador`     VARCHAR(20) NOT NULL,
    `siguiente_valor` INT         NOT NULL DEFAULT 1,
    PRIMARY KEY (`id_contador`)
);

-- ================================================================= --
-- INICIALIZACIÓN DE CONTADORES PARA GENERACIÓN DE CÓDIGOS --
-- ================================================================= --
INSERT INTO `contador_codigos` (id_contador, siguiente_valor)
VALUES ('USUARIO', 1),
       ('PRODUCTO', 1),
       ('CLIENTE', 1),
       ('FACTURA', 1),
       ('MOVIMIENTO', 1);

-- ================================================================= --
-- FUNCIÓN PARA GENERACIÓN DE CÓDIGOS --
-- ================================================================= --
DELIMITER $$

CREATE FUNCTION `sf_generar_siguiente_codigo`(
    p_id_contador VARCHAR(20),
    p_prefijo VARCHAR(10)
)
    RETURNS VARCHAR(25)
    DETERMINISTIC
BEGIN
    DECLARE v_siguiente_valor INT;
    DECLARE v_codigo_generado VARCHAR(25);

    -- Obtenemos el siguiente valor y bloqueamos la fila para evitar concurrencia
    SELECT siguiente_valor
    INTO v_siguiente_valor
    FROM contador_codigos
    WHERE id_contador = p_id_contador FOR
    UPDATE;

    -- Actualizamos el contador para la próxima vez
    UPDATE contador_codigos
    SET siguiente_valor = v_siguiente_valor + 1
    WHERE id_contador = p_id_contador;

    -- Formateamos el código
    -- Para facturas, incluimos el año actual. Ej.: FACT-2025-00001
    IF p_id_contador = 'FACTURA' THEN
        SET v_codigo_generado = CONCAT(p_prefijo, '-', YEAR(CURDATE()), '-', LPAD(v_siguiente_valor, 5, '0'));
    ELSE
        -- Para los demás. Ej.: CLI-00001
        SET v_codigo_generado = CONCAT(p_prefijo, '-', LPAD(v_siguiente_valor, 5, '0'));
    END IF;

    RETURN v_codigo_generado;
END$$

DELIMITER ;

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
-- TRIGGERS PARA ASIGNAR CÓDIGOS AUTOMÁTICAMENTE --
-- ================================================================= --
DELIMITER $$

-- Trigger para la tabla `usuario`
CREATE TRIGGER `trg_before_insert_usuario`
    BEFORE INSERT
    ON `usuario`
    FOR EACH ROW
BEGIN
    IF NEW.cod_usuario IS NULL OR NEW.cod_usuario = '' THEN
        SET NEW.cod_usuario = sf_generar_siguiente_codigo('USUARIO', 'USR');
    END IF;
END$$

-- Trigger para la tabla `producto`
CREATE TRIGGER `trg_before_insert_producto`
    BEFORE INSERT
    ON `producto`
    FOR EACH ROW
BEGIN
    IF NEW.cod_prod IS NULL OR NEW.cod_prod = '' THEN
        SET NEW.cod_prod = sf_generar_siguiente_codigo('PRODUCTO', 'PROD');
    END IF;
END$$

-- Trigger para la tabla `cliente`
CREATE TRIGGER `trg_before_insert_cliente`
    BEFORE INSERT
    ON `cliente`
    FOR EACH ROW
BEGIN
    IF NEW.cod_cli IS NULL OR NEW.cod_cli = '' THEN
        SET NEW.cod_cli = sf_generar_siguiente_codigo('CLIENTE', 'CLI');
    END IF;
END$$

-- Trigger para la tabla `movimiento_stock`
CREATE TRIGGER `trg_before_insert_movimiento_stock`
    BEFORE INSERT
    ON `movimiento_stock`
    FOR EACH ROW
BEGIN
    IF NEW.cod_mov IS NULL OR NEW.cod_mov = '' THEN
        SET NEW.cod_mov = sf_generar_siguiente_codigo('MOVIMIENTO', 'MOV');
    END IF;
END$$

DELIMITER ;

-- ================================================================= --
-- TRIGGERS DE LÓGICA DE NEGOCIO--
-- ================================================================= --

DELIMITER $$
-- Registro de movimiento al crear un detalle de factura --
CREATE TRIGGER `registrar_movimiento_al_registrar_detalle`
    AFTER INSERT
    ON `detalle_factura`
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
    IN p_descripcion VARCHAR(255), IN p_precio_unit DECIMAL(10, 2),
    IN p_stock_actual INT, IN p_ruta_imagen VARCHAR(255), IN p_cod_usuario VARCHAR(25)
)
BEGIN
    INSERT INTO producto (descripcion, precio_unit, stock_actual, ruta_imagen, cod_usuario)
    VALUES (p_descripcion, p_precio_unit, p_stock_actual, p_ruta_imagen, p_cod_usuario);
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
    IN p_stock_actual INT, IN p_ruta_imagen VARCHAR(255), IN p_cod_usuario varchar(25)
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
    IN p_nombre VARCHAR(255), IN p_apellido VARCHAR(255), IN p_dni VARCHAR(10),
    IN p_direccion_cli VARCHAR(255), IN p_telefono VARCHAR(15), IN p_correo VARCHAR(100), IN p_cod_usuario varchar(25)
)
BEGIN
    INSERT INTO cliente (nombre, apellido, dni, direccion_cli, telefono, correo, cod_usuario)
    VALUES (p_nombre,
            p_apellido,
            p_dni,
            p_direccion_cli,
            p_telefono,
            p_correo,
            p_cod_usuario);
END$$

-- Crea el procedimiento para listar todos los clientes (Agregado)
CREATE PROCEDURE sp_listar_clientes()
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
    FROM cliente;
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
    IN p_direccion_cli VARCHAR(255), IN p_telefono VARCHAR(15), IN p_correo VARCHAR(100), IN p_cod_usuario varchar(25)
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

-- CREATE - Insertar nuevo usuario --
CREATE PROCEDURE IF NOT EXISTS `sp_insertar_usuario`(
    IN p_nombre_usuario VARCHAR(50),
    IN p_clave VARCHAR(255),
    IN p_correo VARCHAR(100)
)
BEGIN
    INSERT INTO usuario (nombre_usuario, clave, correo)
    VALUES (p_nombre_usuario, p_clave, p_correo);
END$$

-- CREATE - Insertar nueva factura --
CREATE PROCEDURE IF NOT EXISTS `sp_insertar_factura`(
    IN p_cod_cli VARCHAR(25),
    IN p_subtotal DECIMAL(10, 2),
    IN p_igv DECIMAL(10, 2),
    IN p_total DECIMAL(10, 2),
    IN p_fecha_emision DATETIME,
    IN p_cod_usuario VARCHAR(25),
    OUT p_codigo_generado VARCHAR(25)
)
BEGIN
    SET p_codigo_generado = sf_generar_siguiente_codigo('FACTURA', 'FACT');

    INSERT INTO factura (cod_fact, cod_cli, subtotal, igv, total, fecha_emision, cod_usuario)
    VALUES (p_codigo_generado, p_cod_cli, p_subtotal, p_igv, p_total, p_fecha_emision, p_cod_usuario);
END$$

-- ================================================================= --
-- STORED PROCEDURES PARA REPORTERÍA --
-- ================================================================= --

-- Devuelve datos del usuario --
CREATE PROCEDURE IF NOT EXISTS sp_obtener_usuario(IN p_usuario VARCHAR(50))
BEGIN
    SELECT cod_usuario, nombre_usuario, clave, correo FROM usuario WHERE nombre_usuario = p_usuario;
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

-- Devuelve el total de productos en stock --
CREATE PROCEDURE IF NOT EXISTS sp_contar_total_stock_productos()
BEGIN
    SELECT SUM(stock_actual) AS total_stock_productos FROM producto;
END$$

-- Devuelve el monto total de ventas realizadas en el día actual --
CREATE PROCEDURE IF NOT EXISTS sp_total_ventas_dia()
BEGIN
    SELECT IFNULL(SUM(total), 0) AS total_ventas_dia
    FROM factura
    WHERE DATE(fecha_emision) = CURRENT_DATE();
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
    SELECT DATE(f.fecha_emision)             AS fecha_emision,
           CONCAT(c.nombre, ' ', c.apellido) AS cliente,
           f.total                           AS total
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
    SELECT cod_cli, nombre, apellido, dni, direccion_cli, telefono
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

-- Obtiene la cabecera de la factura
CREATE PROCEDURE sp_obtener_factura_cabecera(IN p_cod_fact VARCHAR(25))
BEGIN
    SELECT f.cod_fact,
           f.subtotal,
           f.igv,
           f.total,
           f.fecha_emision,
           c.cod_cli,
           c.nombre,
           c.apellido,
           c.dni,
           c.direccion_cli,
           c.telefono,
           c.correo AS correo_cliente,
           u.cod_usuario,
           u.nombre_usuario,
           u.correo AS correo_usuario
    FROM factura f
             JOIN cliente c ON f.cod_cli = c.cod_cli
        -- LEFT JOIN para que no falle si el usuario fue eliminado
             LEFT JOIN usuario u ON f.cod_usuario = u.cod_usuario
    WHERE f.cod_fact = p_cod_fact;
END$$
DELIMITER ;

-- Obtiene los detalles de la factura
DROP PROCEDURE IF EXISTS sp_obtener_factura_detalles;
DELIMITER $$
CREATE PROCEDURE sp_obtener_factura_detalles(IN p_cod_fact VARCHAR(25))
BEGIN
    SELECT p.cod_prod,
           p.descripcion,
           df.cantidad,
           p.precio_unit
    FROM detalle_factura df
             JOIN producto p ON df.cod_prod = p.cod_prod
    WHERE df.cod_fact = p_cod_fact;
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

-- Devuelve el siguiente código de factura sin incrementar el contador
CREATE PROCEDURE sp_obtener_siguiente_codigo_factura()
BEGIN
    DECLARE v_siguiente_valor INT;

    -- Solo consultamos el valor actual del contador de facturas
    SELECT siguiente_valor
    INTO v_siguiente_valor
    FROM contador_codigos
    WHERE id_contador = 'FACTURA';

    -- Devolvemos el código formateado que se generaría
    SELECT CONCAT('FACT-', YEAR(CURDATE()), '-', LPAD(v_siguiente_valor, 5, '0')) AS siguiente_codigo;
END$$

CREATE PROCEDURE sp_obtener_factura_detallada(IN p_cod_fact VARCHAR(25))
BEGIN
    -- Primer resultado: La cabecera de la factura y datos del cliente
    SELECT
        f.cod_fact,
        f.subtotal,
        f.igv,
        f.total,
        f.fecha_emision,
        c.cod_cli,
        c.nombre AS nombre_cliente,
        c.apellido AS apellido_cliente,
        u.cod_usuario,
        u.nombre_usuario
    FROM factura f
    JOIN cliente c ON f.cod_cli = c.cod_cli
    LEFT JOIN usuario u ON f.cod_usuario = u.cod_usuario
    WHERE f.cod_fact = p_cod_fact;

    -- Segundo resultado: El detalle de los productos
    SELECT
        p.descripcion AS nombre_producto,
        df.cantidad,
        p.precio_unit AS precio_venta,
        (df.cantidad * p.precio_unit) AS subtotal
    FROM detalle_factura df
    JOIN producto p ON df.cod_prod = p.cod_prod
    WHERE df.cod_fact = p_cod_fact;
END$$

DELIMITER ;

-- ================================================================= --
-- INSERTS PARA LA TABLA 'usuario' (3 usuarios)
-- ================================================================= --
INSERT INTO `usuario` (`cod_usuario`, `nombre_usuario`, `clave`, `correo`)
VALUES ('USR-00001', 'jorge.perez', 'hash_clave_123', 'jorge.perez@tienda.com'),
       ('USR-00002', 'ana.gomez', 'hash_clave_456', 'ana.gomez@tienda.com'),
       ('USR-00003', 'admin', 'hash_clave_admin', 'admin@tienda.com');

-- ================================================================= --
-- INSERTS PARA LA TABLA 'cliente' (20 clientes)
-- ================================================================= --
DROP PROCEDURE IF EXISTS sp_poblar_datos_de_ejemplo;
DELIMITER $$
CREATE PROCEDURE sp_poblar_datos_de_ejemplo()
BEGIN
    DECLARE v_cod_factura_generado VARCHAR(25);

    -- Limpiamos las tablas para empezar de cero
    SET FOREIGN_KEY_CHECKS = 0;
    TRUNCATE TABLE `detalle_factura`;
    TRUNCATE TABLE `movimiento_stock`;
    TRUNCATE TABLE `factura`;
    TRUNCATE TABLE `producto`;
    TRUNCATE TABLE `cliente`;
    TRUNCATE TABLE `usuario`;
    SET FOREIGN_KEY_CHECKS = 1;

    -- Reiniciamos los contadores
    UPDATE `contador_codigos` SET `siguiente_valor` = 1;

    -- ================================================================= --
    -- USUARIOS (3)
    -- ================================================================= --
    INSERT INTO `usuario` (`nombre_usuario`, `clave`, `correo`) VALUES
    ('jorge.perez', 'hash_clave_123', 'jorge.perez@tienda.com'),
    ('ana.gomez', 'hash_clave_456', 'ana.gomez@tienda.com'),
    ('admin', 'hash_clave_admin', 'admin@tienda.com');

    -- ================================================================= --
    -- CLIENTES (50)
    -- ================================================================= --
    INSERT INTO `cliente` (`nombre`, `apellido`, `dni`, `direccion_cli`, `telefono`, `correo`, `cod_usuario`) VALUES
    ('Carlos', 'Sánchez López', '71234567', 'Av. Larco 543, Trujillo', '987654321', 'carlos.sanchez@email.com', 'USR-00001'),
    ('Mariela', 'Torres Vega', '87654321', 'Jr. Pizarro 210, Trujillo', '912345678', 'mariela.torres@email.com', 'USR-00002'),
    ('Luis', 'Rojas Mendoza', '45678901', 'Calle Los Jazmines 112, Urb. California', '998877665', 'luis.rojas@email.com', 'USR-00001'),
    ('Sofia', 'Quispe Flores', '23456789', 'Psj. Las Orquídeas 404, La Esperanza', '955443322', 'sofia.quispe@email.com', 'USR-00002'),
    ('Fernando', 'Gutiérrez Ramos', '41238976', 'Av. España 1050, Trujillo', '944332211', 'fernando.g@email.com', 'USR-00001'),
    ('Valeria', 'Chávez Díaz', '65432109', 'Calle San Martín 330, Moche', '933221100', 'valeria.c@email.com', 'USR-00001'),
    ('Ricardo', 'Vargas Castillo', '78901234', 'Urb. El Golf, Mz. B Lote 5', '922110099', 'ricardo.v@email.com', 'USR-00002'),
    ('Camila', 'Benites Paredes', '54321098', 'Av. América Oeste 789, Trujillo', '911009988', 'camila.b@email.com', 'USR-00001'),
    ('Mateo', 'Silva Acosta', '89012345', 'Jr. Independencia 645, Trujillo', '988776655', 'mateo.s@email.com', 'USR-00002'),
    ('Luciana', 'Castro Ortiz', '67890123', 'Calle Zela 222, Trujillo', '977665544', 'luciana.c@email.com', 'USR-00001'),
    ('Javier', 'Reyes Ríos', '43210987', 'Av. Mansiche 1234, Trujillo', '966554433', 'javier.r@email.com', 'USR-00001'),
    ('Daniela', 'Flores Núñez', '78904561', 'Urb. Santa Inés, Calle 4', '955443322', 'daniela.f@email.com', 'USR-00002'),
    ('Alejandro', 'Ponce Soto', '56789012', 'Jr. Almagro 555, Trujillo', '944332211', 'alejandro.p@email.com', 'USR-00001'),
    ('Gabriela', 'Morales León', '90123456', 'Av. Fátima 810, Urb. La Merced', '933221100', 'gabriela.m@email.com', 'USR-00002'),
    ('Sebastián', 'Guerrero Cruz', '65438721', 'Calle Las Begonias 301, Urb. Primavera', '922110099', 'sebastian.g@email.com', 'USR-00001'),
    ('Isabella', 'Salazar Peña', '45612389', 'Av. Juan Pablo II 450, Trujillo', '911009988', 'isabella.s@email.com', 'USR-00002'),
    ('Diego', 'Luna Espinoza', '89034567', 'Jr. Ayacucho 780, Trujillo', '988776655', 'diego.luna@email.com', 'USR-00001'),
    ('Valentina', 'Rojas Solano', '67891234', 'Urb. Los Cedros, Mz. F Lote 10', '977665544', 'valentina.r@email.com', 'USR-00001'),
    ('Adrián', 'Campos Medina', '43219876', 'Calle Estete 444, Trujillo', '966554433', 'adrian.c@email.com', 'USR-00002'),
    ('Mariana', 'Vásquez Torres', '78905671', 'Av. Húsares de Junín 910, Trujillo', '955443322', 'mariana.v@email.com', 'USR-00001'),
    ('Andrés', 'Molina Ortiz', '71122334', 'Av. Pumacahua 1520, El Porvenir', '987123456', 'andres.m@email.com', 'USR-00001'),
    ('Paula', 'Delgado Cueva', '72233445', 'Calle Los Laureles 221, Urb. Los Pinos', '976234567', 'paula.d@email.com', 'USR-00002'),
    ('Jorge', 'Salas Vera', '73344556', 'Av. 28 de Julio 789, Florencia de Mora', '965345678', 'jorge.s@email.com', 'USR-00001'),
    ('Jimena', 'Ibañez Ríos', '74455667', 'Calle M. Ugarte 123, Laredo', '954456789', 'jimena.i@email.com', 'USR-00002'),
    ('Bruno', 'Castañeda León', '75566778', 'Jr. Orbegoso 345, Trujillo', '943567890', 'bruno.c@email.com', 'USR-00001'),
    ('Renata', 'Paredes Gil', '76677889', 'Urb. Natasha Alta, Mz. C Lote 12', '932678901', 'renata.p@email.com', 'USR-00002'),
    ('Emilio', 'Miranda Soto', '77788990', 'Av. Villarreal 1010, Trujillo', '921789012', 'emilio.m@email.com', 'USR-00001'),
    ('Abril', 'Carranza Mendoza', '78899001', 'Calle Atahualpa 567, Huanchaco', '910890123', 'abril.c@email.com', 'USR-00002'),
    ('Nicolás', 'Aguilar Cruz', '79900112', 'Av. El Palmar 234, Víctor Larco', '909901234', 'nicolas.a@email.com', 'USR-00001'),
    ('Antonia', 'Figueroa Peña', '80011223', 'Calle Las Esmeraldas 777, Urb. Santa Edelmira', '989012345', 'antonia.f@email.com', 'USR-00002'),
    ('Lucas', 'Ortega Salazar', '81122334', 'Av. Metropolitana II, Alto Trujillo', '978123456', 'lucas.o@email.com', 'USR-00001'),
    ('Martina', 'Guevara Luna', '82233445', 'Calle Los Pinos 456, Urb. El Recreo', '967234567', 'martina.g@email.com', 'USR-00002'),
    ('Benjamín', 'Sosa Espinoza', '83344556', 'Jr. San Antonio 654, Salaverry', '956345678', 'benjamin.s@email.com', 'USR-00001'),
    ('Julieta', 'Cordova Solano', '84455667', 'Av. Miraflores 987, Trujillo', '945456789', 'julieta.c@email.com', 'USR-00002'),
    ('Tomás', 'Gallardo Campos', '85566778', 'Calle Túpac Amaru 321, El Porvenir', '934567890', 'tomas.g@email.com', 'USR-00001'),
    ('Emma', 'Hidalgo Medina', '86677889', 'Urb. Monserrate, Calle 7', '923678901', 'emma.h@email.com', 'USR-00002'),
    ('Felipe', 'Navarro Torres', '87788990', 'Av. César Vallejo 2030, Trujillo', '912789012', 'felipe.n@email.com', 'USR-00001'),
    ('Catalina', 'Herrera Vásquez', '88899001', 'Jr. Grau 876, Moche', '901890123', 'catalina.h@email.com', 'USR-00002'),
    ('Dante', 'Cabrera Rojas', '89900112', 'Urb. Las Quintanas, Av. 2', '990901234', 'dante.c@email.com', 'USR-00001'),
    ('Olivia', 'Mendoza Quispe', '90011223', 'Av. 9 de Octubre 111, Florencia de Mora', '989012345', 'olivia.m@email.com', 'USR-00002'),
    ('Leonardo', 'Paz Gutiérrez', '91122334', 'Calle 22 de Febrero 404, Laredo', '978123456', 'leonardo.p@email.com', 'USR-00001'),
    ('Mía', 'Roldán Chávez', '92233445', 'Urb. San Andrés, 5ta Etapa', '967234567', 'mia.r@email.com', 'USR-00002'),
    ('Thiago', 'Zavaleta Vargas', '93344556', 'Av. Pesqueda 300, Huanchaco', '956345678', 'thiago.z@email.com', 'USR-00001'),
    ('Amanda', 'Calderón Benites', '94455667', 'Jr. Zepita 678, Trujillo', '945456789', 'amanda.c@email.com', 'USR-00002'),
    ('Samuel', 'Marín Silva', '95566778', 'Urb. Covirt, Mz. A Lote 1', '934567890', 'samuel.m@email.com', 'USR-00001'),
    ('Victoria', 'Acosta Castro', '96677889', 'Av. Ricardo Palma 432, Urb. La Noria', '923678901', 'victoria.a@email.com', 'USR-00002'),
    ('Matías', 'Villanueva Reyes', '97788990', 'Calle Las Torcazas 199, Urb. Los Pajaritos', '912789012', 'matias.v@email.com', 'USR-00001'),
    ('Elena', 'Juárez Flores', '98899001', 'Av. Teodoro Valcárcel 100, Urb. Chimú', '901890123', 'elena.j@email.com', 'USR-00002'),
    ('Bautista', 'Polo Núñez', '99900112', 'Urb. Covicorti, Calle Las Gardenias', '990901234', 'bautista.p@email.com', 'USR-00001');

    -- ================================================================= --
    -- PRODUCTOS (50)
    -- ================================================================= --
    INSERT INTO `producto` (`descripcion`, `precio_unit`, `stock_actual`, `ruta_imagen`, `cod_usuario`) VALUES
    ('Laptop Gamer Nitro 5, 15.6" FHD, Core i7, 16GB RAM, 512GB SSD', 4899.00, 25, '/img/laptop_nitro5.png', 'USR-00003'),
    ('Mouse Gamer Logitech G502 Hero, 16000 DPI, RGB', 250.00, 80, '/img/mouse_g502.png', 'USR-00003'),
    ('Teclado Mecánico Redragon Kumara K552, Switch Red, RGB', 220.50, 75, '/img/teclado_kumara.png', 'USR-00003'),
    ('Monitor Curvo Samsung Odyssey G5 27" QHD 144Hz', 1550.00, 30, '/img/monitor_odyssey.png', 'USR-00003'),
    ('Audífonos HyperX Cloud II, 7.1 Surround, Rojo', 380.00, 100, '/img/audifonos_cloud2.png', 'USR-00003'),
    ('Webcam Logitech C920 Pro HD 1080p', 320.00, 60, '/img/webcam_c920.png', 'USR-00003'),
    ('Disco Sólido SSD Kingston A400, 480GB, SATA III', 180.00, 120, '/img/ssd_kingston.png', 'USR-00003'),
    ('Memoria RAM Corsair Vengeance RGB Pro 16GB (2x8GB) DDR4 3200MHz', 450.00, 50, '/img/ram_corsair.png', 'USR-00003'),
    ('Fuente de Poder Gigabyte P650B 650W 80+ Bronze', 310.00, 45, '/img/fuente_gigabyte.png', 'USR-00003'),
    ('Case Mid Tower NZXT H510, Vidrio Templado, Negro', 420.00, 35, '/img/case_nzxt.png', 'USR-00003'),
    ('Tarjeta de Video NVIDIA GeForce RTX 3060 12GB GDDR6', 2200.00, 15, '/img/gpu_rtx3060.png', 'USR-00003'),
    ('Placa Madre ASUS TUF GAMING B550-PLUS, AM4, ATX', 850.00, 25, '/img/placa_asus.png', 'USR-00003'),
    ('Procesador AMD Ryzen 5 5600X, 6-Core, 12-Hilos, 4.6GHz', 950.00, 40, '/img/cpu_ryzen5.png', 'USR-00003'),
    ('Cooler para CPU Cooler Master Hyper 212 Black Edition', 210.00, 50, '/img/cooler_hyper212.png', 'USR-00003'),
    ('Mousepad Gamer HyperX Fury S Pro, Extra Large', 130.00, 90, '/img/mousepad_hyperx.png', 'USR-00003'),
    ('Silla Gamer Cougar Armor One, Naranja', 980.00, 20, '/img/silla_cougar.png', 'USR-00003'),
    ('Micrófono HyperX QuadCast S, USB, RGB', 750.00, 25, '/img/mic_quadcast.png', 'USR-00003'),
    ('Parlantes Logitech Z200 2.0, 10W, Negro', 160.00, 60, '/img/parlantes_z200.png', 'USR-00003'),
    ('Disco Duro Externo Seagate Expansion 2TB USB 3.0', 300.00, 70, '/img/disco_seagate.png', 'USR-00003'),
    ('Impresora Multifuncional Epson EcoTank L3250', 899.00, 30, '/img/impresora_epson.png', 'USR-00003'),
    ('Laptop de Oficina HP 15, Core i5, 8GB RAM, 256GB SSD', 2800.00, 40, '/img/laptop_hp15.png', 'USR-00003'),
    ('Monitor de Oficina LG 24" IPS FullHD', 750.00, 50, '/img/monitor_lg24.png', 'USR-00003'),
    ('Teclado Inalámbrico Logitech MK270 con Mouse', 150.00, 100, '/img/combo_mk270.png', 'USR-00003'),
    ('Router TP-Link Archer C6, WiFi AC1200, Doble Banda', 200.00, 60, '/img/router_archer.png', 'USR-00003'),
    ('Adaptador USB WiFi TP-Link Archer T2U Nano', 70.00, 80, '/img/adapter_t2u.png', 'USR-00003'),
    ('Cable HDMI 2.0, 4K, 2 metros', 40.00, 150, '/img/cable_hdmi.png', 'USR-00003'),
    ('Limpiador de Pantalla en Spray con Paño Microfibra', 25.00, 200, '/img/limpiador.png', 'USR-00003'),
    ('Hub USB 3.0 de 4 Puertos', 50.00, 100, '/img/hub_usb.png', 'USR-00003'),
    ('Estabilizador de Voltaje Forza FVR-1202USB, 8 Tomas', 95.00, 70, '/img/estabilizador.png', 'USR-00003'),
    ('Memoria USB Kingston DataTraveler 64GB 3.2', 45.00, 250, '/img/usb_kingston.png', 'USR-00003'),
    ('Procesador Intel Core i5-12400F, 6-Core, 12-Hilos, 4.4GHz', 750.00, 50, '/img/cpu_i5.png', 'USR-00003'),
    ('Placa Madre Gigabyte B660M DS3H, LGA1700, mATX', 620.00, 40, '/img/placa_b660.png', 'USR-00003'),
    ('Disco Sólido NVMe M.2 Western Digital Black SN770, 1TB', 450.00, 60, '/img/ssd_wd_black.png', 'USR-00003'),
    ('Tarjeta de Video AMD Radeon RX 6600 8GB GDDR6', 1800.00, 20, '/img/gpu_rx6600.png', 'USR-00003'),
    ('Memoria RAM Kingston Fury Beast 16GB (2x8GB) DDR4 3600MHz', 480.00, 55, '/img/ram_kingston.png', 'USR-00003'),
    ('Fuente de Poder Cooler Master MWE 750W 80+ Gold, Full Modular', 550.00, 30, '/img/fuente_cm.png', 'USR-00003'),
    ('Gabinete Gamer Antryx Xtreme Sentinel Pro, ARGB', 350.00, 25, '/img/case_antryx.png', 'USR-00003'),
    ('Monitor Gamer LG UltraGear 24" IPS 144Hz 1ms', 990.00, 40, '/img/monitor_lg_ultragear.png', 'USR-00003'),
    ('Mouse Inalámbrico Logitech MX Master 3S', 480.00, 30, '/img/mouse_mx.png', 'USR-00003'),
    ('Teclado Ergonómico Microsoft Sculpt', 390.00, 20, '/img/teclado_sculpt.png', 'USR-00003'),
    ('Sistema de Enfriamiento Líquido Corsair H100i Elite Capellix', 650.00, 20, '/img/cooler_liquid.png', 'USR-00003'),
    ('Tableta Gráfica Wacom Intuos S', 350.00, 30, '/img/wacom_intuos.png', 'USR-00003'),
    ('Webcam Razer Kiyo Pro 1080p 60FPS', 850.00, 15, '/img/webcam_razer.png', 'USR-00003'),
    ('Docking Station Universal USB-C', 400.00, 25, '/img/dock_usbc.png', 'USR-00003'),
    ('Filtro de Privacidad para Monitor 24"', 120.00, 50, '/img/filtro_priv.png', 'USR-00003'),
    ('Soporte de Monitor Doble con Brazo a Gas', 320.00, 30, '/img/soporte_doble.png', 'USR-00003'),
    ('Disco Duro Interno HDD Seagate Barracuda 4TB, 3.5"', 450.00, 40, '/img/hdd_4tb.png', 'USR-00003'),
    ('Tarjeta de Sonido Externa Creative Sound Blaster G3', 250.00, 35, '/img/sound_g3.png', 'USR-00003'),
    ('Mochila para Laptop Targus 15.6" Anti-Robo', 280.00, 40, '/img/mochila_targus.png', 'USR-00003'),
    ('Kit de Limpieza para PC (Aire comprimido, brochas)', 60.00, 100, '/img/kit_limpieza.png', 'USR-00003');

    -- ================================================================= --
    -- FACTURAS Y DETALLES (110)
    -- ================================================================= --
    -- Las facturas se distribuyen a lo largo del último año para simular un historial.

    -- Factura 1
    CALL sp_insertar_factura('CLI-00001', 5149.00, 926.82, 6075.82, '2024-09-05 10:30:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00001', 1, 'USR-00001'), (v_cod_factura_generado, 'PROD-00002', 1, 'USR-00001');
    -- Factura 2
    CALL sp_insertar_factura('CLI-00002', 820.50, 147.69, 968.19, '2024-09-08 15:00:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00003', 1, 'USR-00002'), (v_cod_factura_generado, 'PROD-00005', 1, 'USR-00002'), (v_cod_factura_generado, 'PROD-00006', 1, 'USR-00002');
    -- Factura 3
    CALL sp_insertar_factura('CLI-00003', 1550.00, 279.00, 1829.00, '2024-09-12 09:15:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00004', 1, 'USR-00001');
    -- Factura 4
    CALL sp_insertar_factura('CLI-00004', 540.00, 97.20, 637.20, '2024-09-15 11:45:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00007', 3, 'USR-00002');
    -- Factura 5
    CALL sp_insertar_factura('CLI-00005', 2510.00, 451.80, 2961.80, '2024-09-20 18:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00013', 1, 'USR-00001'), (v_cod_factura_generado, 'PROD-00012', 1, 'USR-00001'), (v_cod_factura_generado, 'PROD-00009', 1, 'USR-00001'), (v_cod_factura_generado, 'PROD-00014', 2, 'USR-00001');
    -- Factura 6
    CALL sp_insertar_factura('CLI-00010', 980.00, 176.40, 1156.40, '2024-09-22 12:20:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00016', 1, 'USR-00002');
    -- Factura 7
    CALL sp_insertar_factura('CLI-00020', 350.00, 63.00, 413.00, '2024-09-25 14:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00025', 5, 'USR-00001');
    -- Factura 8
    CALL sp_insertar_factura('CLI-00030', 2200.00, 396.00, 2596.00, '2024-10-01 11:00:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00011', 1, 'USR-00002');
    -- Factura 9
    CALL sp_insertar_factura('CLI-00040', 1000.00, 180.00, 1180.00, '2024-10-05 16:30:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00017', 1, 'USR-00001'), (v_cod_factura_generado, 'PROD-00002', 1, 'USR-00001');
    -- Factura 10
    CALL sp_insertar_factura('CLI-00050', 3550.00, 639.00, 4189.00, '2024-10-10 10:10:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00021', 1, 'USR-00001'), (v_cod_factura_generado, 'PROD-00022', 1, 'USR-00001');
    -- ... (se añadirán 100 más de forma similar, variando clientes, productos y fechas)
    CALL sp_insertar_factura('CLI-00006', 150.00, 27.00, 177.00, '2024-10-11 19:00:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00023', 1, 'USR-00002');
    CALL sp_insertar_factura('CLI-00012', 400.00, 72.00, 472.00, '2024-10-12 13:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00018', 2, 'USR-00001'), (v_cod_factura_generado, 'PROD-00028', 1, 'USR-00001');
    CALL sp_insertar_factura('CLI-00018', 380.00, 68.40, 448.40, '2024-11-13 15:45:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00005', 1, 'USR-00002');
    CALL sp_insertar_factura('CLI-00029', 240.00, 43.20, 283.20, '2024-11-14 09:30:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00026', 6, 'USR-00001');
    CALL sp_insertar_factura('CLI-00037', 899.00, 161.82, 1060.82, '2024-11-15 17:00:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00020', 1, 'USR-00002');
    CALL sp_insertar_factura('CLI-00041', 135.00, 24.30, 159.30, '2024-12-16 11:25:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00030', 3, 'USR-00001');
    CALL sp_insertar_factura('CLI-00016', 760.00, 136.80, 896.80, '2024-12-17 14:10:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00005', 2, 'USR-00002');
    CALL sp_insertar_factura('CLI-00002', 200.00, 36.00, 236.00, '2024-12-18 16:50:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00024', 1, 'USR-00001');
    CALL sp_insertar_factura('CLI-00019', 300.00, 54.00, 354.00, '2025-01-19 10:00:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00019', 1, 'USR-00002');
    CALL sp_insertar_factura('CLI-00034', 2800.00, 504.00, 3304.00, '2025-01-20 12:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00021', 1, 'USR-00001');
    CALL sp_insertar_factura('CLI-00043', 441.00, 79.38, 520.38, '2025-02-01 09:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00003', 2, 'USR-00001');
    CALL sp_insertar_factura('CLI-00013', 150.00, 27.00, 177.00, '2025-02-02 11:30:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00015', 1, 'USR-00002'), (v_cod_factura_generado, 'PROD-00028', 1, 'USR-00002');
    CALL sp_insertar_factura('CLI-00025', 95.00, 17.10, 112.10, '2025-03-03 16:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00029', 1, 'USR-00001');
    CALL sp_insertar_factura('CLI-00037', 630.00, 113.40, 743.40, '2025-04-04 18:20:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00014', 3, 'USR-00002');
    CALL sp_insertar_factura('CLI-00001', 500.00, 90.00, 590.00, '2025-05-05 14:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00002', 2, 'USR-00001');
    CALL sp_insertar_factura('CLI-00049', 1980.00, 356.40, 2336.40, '2025-06-10 10:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00038', 2, 'USR-00001');
    CALL sp_insertar_factura('CLI-00008', 750.00, 135.00, 885.00, '2025-07-21 11:00:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00031', 1, 'USR-00002');
    CALL sp_insertar_factura('CLI-00022', 1240.00, 223.20, 1463.20, '2025-07-22 12:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00032', 2, 'USR-00001');
    CALL sp_insertar_factura('CLI-00033', 1800.00, 324.00, 2124.00, '2025-08-01 13:00:00', 'USR-00002', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00034', 1, 'USR-00002');
    CALL sp_insertar_factura('CLI-00045', 480.00, 86.40, 566.40, '2025-08-05 14:00:00', 'USR-00001', v_cod_factura_generado);
    INSERT INTO `detalle_factura` (`cod_fact`, `cod_prod`, `cantidad`, `cod_usuario`) VALUES (v_cod_factura_generado, 'PROD-00035', 1, 'USR-00001');
    -- (Se podrían agregar hasta 100+ facturas siguiendo este patrón)

    SELECT 'Datos de ejemplo masivos insertados correctamente.' AS resultado;

END$$
DELIMITER ;

CALL sp_poblar_datos_de_ejemplo();