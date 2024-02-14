-- Insertar clientes
INSERT INTO cliente (nombre, apellido, email, direccion, celular, documento) 
VALUES
('Juan', 'Perez', 'jperez@gmail.com', 'Calle 123', '31448975', '1001'),
('Maria', 'Lopez', 'mlopez@gmail.com', 'Avenida 456', '31567890', '1002'),
('Carlos', 'Gomez', 'cgomez@gmail.com', 'Carrera 789', '31789012', '1003'),
('Laura', 'Martinez', 'lmartinez@gmail.com', 'Calle 567', '31890123', '1004'),
('Pedro', 'Rodriguez', 'prodriguez@gmail.com', 'Calle 890', '31901234', '1005');

-- Insertar productos
INSERT INTO producto (nombre, descripcion, precioVenta, precioCompra) 
VALUES
('Producto1', 'Descripción del Producto 1', 50.00, 30.00),
('Producto2', 'Descripción del Producto 2', 40.00, 25.00),
('Producto3', 'Descripción del Producto 3', 60.00, 35.00),
('Producto4', 'Descripción del Producto 4', 70.00, 45.00),
('Producto5', 'Descripción del Producto 5', 55.00, 30.00),
-- ... Agrega más productos según sea necesario
('Producto18', 'Descripción del Producto 18', 65.00, 40.00),
('Producto19', 'Descripción del Producto 19', 80.00, 50.00),
('Producto20', 'Descripción del Producto 20', 75.00, 48.00);


-- insert de la tabla impuesto  
INSERT INTO dabase_factura.impuesto (id, anio, porcentaje) VALUES (1, 2023, 16);
INSERT INTO dabase_factura.impuesto (id, anio, porcentaje) VALUES (2, 2024, 18);


-- insert de la tabla Factura  -- la factura recibe de acuerdo al anio el descuento que se le va aplicar 

INSERT INTO dabase_factura.factura (numeroFactura, fecha, cliente_id, impuesto_id, totalImpuesto) VALUES (16, '2024-02-13 00:00:00', 1, 2, 108);
INSERT INTO dabase_factura.factura (numeroFactura, fecha, cliente_id, impuesto_id, totalImpuesto) VALUES (17, '2024-02-13 00:00:00', 1, 2, 25.2);
INSERT INTO dabase_factura.factura (numeroFactura, fecha, cliente_id, impuesto_id, totalImpuesto) VALUES (18, '2024-02-13 00:00:00', 3, 2, 99);
INSERT INTO dabase_factura.factura (numeroFactura, fecha, cliente_id, impuesto_id, totalImpuesto) VALUES (19, '2024-02-13 00:00:00', 4, 2, 62.1);
INSERT INTO dabase_factura.factura (numeroFactura, fecha, cliente_id, impuesto_id, totalImpuesto) VALUES (20, '2024-02-13 00:00:00', 5, 2, 900);



-- Insertar items de factura
INSERT INTO item_factura (factura_numeroFactura, producto_codigo, cantidad, importe) 
VALUES
(1, 1, 2, 100.00),
(1, 2, 3, 120.00),
(2, 3, 1, 60.00),
(2, 4, 2, 130.00),
(3, 5, 1, 55.00),
(8, 6, 2, 120.00),
(9, 7, 3, 195.00),
(10, 8, 1, 75.00);


-- 


