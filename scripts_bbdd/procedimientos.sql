DELIMITER //
create procedure obtenerClientesEstandar()
begin
	select * from cliente inner join clienteestandar
    on cliente.idCliente = clienteestandar.idEstandar;
end //
DELIMITER ;

DELIMITER //
create procedure obtenerClientesPremium()
begin
	select * from cliente inner join clientepremium
    on cliente.idCliente = clientepremium.idPremium;
end //
DELIMITER ;

DELIMITER //

CREATE PROCEDURE insertarClienteEstandar(
	IN id INT,
    IN nombre VARCHAR(40),
    IN domicilio VARCHAR(40),
    IN nif VARCHAR(40),
    IN email VARCHAR(40)
)
BEGIN

    INSERT INTO cliente VALUES (id, nombre, domicilio, nif, email);

    INSERT INTO clienteestandar VALUES (id);
    
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE insertarClientePremium(
	IN id INT,
    IN nombre VARCHAR(40),
    IN domicilio VARCHAR(40),
    IN nif VARCHAR(40),
    IN email VARCHAR(40),
    IN descuento FLOAT
)
BEGIN

    INSERT INTO cliente VALUES (id, nombre, domicilio, nif, email);

    INSERT INTO clientepremium VALUES (id, descuento);
    
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ObtenerClienteEstandarPorID(IN cliente_id INT, OUT encontrado INT)
BEGIN
    -- Declarar una variable para contar las filas encontradas
    DECLARE num_rows INT;

    -- Contar las filas que coinciden con el ID proporcionado
    SELECT COUNT(*) INTO num_rows
    FROM clienteestandar
    WHERE idEstandar = cliente_id;

    -- Verificar si se encontró algún cliente
    IF num_rows > 0 THEN
        -- Seleccionar el cliente por su ID
        SELECT *
        FROM cliente
        WHERE idCliente = cliente_id;
        set encontrado = num_rows;
    END IF;
END //

DELIMITER ;
