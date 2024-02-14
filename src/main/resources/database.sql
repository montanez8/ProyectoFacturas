create table if not exists cliente
(
    id int auto_increment
    primary key,
    nombre varchar(255) not null,
    apellido varchar(255) not null,
    email varchar(255) null,
    direccion varchar(255) null,
    celular varchar(20) null,
    documento varchar(20) not null
    );

create index if not exists idx_cliente_documento
    on cliente (documento);

create table if not exists factura
(
    numeroFactura int auto_increment
    primary key,
    fecha datetime not null,
    cliente_id int null
);

create index if not exists idx_factura_cliente
    on factura (cliente_id);

alter table factura
    add constraint factura_ibfk_1
        foreign key (cliente_id) references cliente (id);

create table if not exists item_factura
(
    id int auto_increment
    primary key,
    factura_numeroFactura int null,
    producto_codigo int null,
    cantidad int not null,
    importe decimal(10,2) not null
    );

create index if not exists idx_item_factura_factura
    on item_factura (factura_numeroFactura);

create index if not exists idx_item_factura_producto
    on item_factura (producto_codigo);

alter table item_factura
    add constraint item_factura_ibfk_1
        foreign key (factura_numeroFactura) references factura (numeroFactura);

create table if not exists producto
(
    codigo int auto_increment
    primary key,
    nombre varchar(255) not null,
    descripcion text null,
    precioVenta decimal(10,2) not null,
    precioCompra decimal(10,2) not null
    );

alter table item_factura
    add constraint item_factura_ibfk_2
        foreign key (producto_codigo) references producto (codigo);

create index if not exists idx_producto_nombre
    on producto (nombre);

# 1. Listar desc clientes por compras
SELECT DISTINCT c.id, c.nombre, c.apellido, SUM(ifa.importe) as total_compras
FROM cliente c
         JOIN factura f ON c.id = f.cliente_id
         JOIN item_factura ifa ON f.numeroFactura = ifa.factura_numeroFactura
GROUP BY c.id, c.nombre, c.apellido
ORDER BY total_compras DESC;


# Listar productos mas vendidos
SELECT DISTINCT p.codigo, p.nombre, SUM(ifa.cantidad) as total_vendido
FROM producto p
         JOIN item_factura ifa ON p.codigo = ifa.producto_codigo
GROUP BY p.codigo, p.nombre
ORDER BY total_vendido DESC;




