package com.campusland.views;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.campusland.exceptiones.facturaexceptions.FacturaExceptionInsertDataBase;
import com.campusland.respository.models.*;
import com.campusland.utils.conexionpersistencia.conexionbdjson.JacksonConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ViewFactura extends ViewMain {

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearFactura();
                    break;
                case 2:
                    listarFactura();
                    break;
                case 3:
                    generarReporteDian();
                    break;
                case 4:
                    //  informeTotalVentas();
                    break;
                case 5:
                    mostrarClientesPorCompras();
                    break;
                case 6:
                    mostrarProductosMasVendidos();
                    break;

                case 7:
                    System.out.println("Fin");
                    break;


                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 8);

    }

    private static void generarReporteDian() {
        System.out.println("Generar reporte Dian por año");
        System.out.println("Ingrese el año");
        int year = leer.nextInt();
        List<Factura> facturas = serviceFactura.listar();
        filtarFacturaAnio(facturas, year);
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Factura----");
        System.out.println("1. Crear factura.");
        System.out.println("2. Listar factura.");
        System.out.println("3. Generar reporte Dian por año");
        System.out.println("4. Informe total de ventas , descuentos y impuestos");
        System.out.println("5. Lista desendente de clientes por compras");
        System.out.println("6. Lista desendente de productos mas vendidos");
        System.out.println("7. Salir ");
        return leer.nextInt();
    }

    public static void listarFactura() {
        System.out.println("Lista de Facturas");
        for (Factura factura : serviceFactura.listar()) {
            factura.display();
            System.out.println();
        }
    }

    public static void crearFactura() {
        System.out.println("-- Creación de Factura ---");

        Cliente cliente;
        do {
            cliente = ViewCliente.buscarGetCliente();
        } while (cliente == null);

        Factura factura = new Factura(LocalDateTime.now(), cliente);
        System.out.println("-- Se creó la factura -----------------");
        System.out.println("-- Seleccione los productos a comprar por código");


        do {
            Producto producto = ViewProducto.buscarGetProducto();

            if (producto != null) {
                System.out.print("Cantidad: ");
                int cantidad = leer.nextInt();
                ItemFactura item = new ItemFactura(cantidad, producto);
                factura.agregarItem(item);

                System.out.println("Agregar otro producto: si o no");
                String otroItem = leer.next();
                if (!otroItem.equalsIgnoreCase("si")) {
                    break;
                }
            }

        } while (true);
        LocalDate date = LocalDate.now();
        int anio = Integer.parseInt(String.valueOf(date.getYear()));
        Impuesto impuesto = serviceImpuesto.buscarGetImpuestoPorAnio(anio);
        factura.setImpuesto(impuesto);

        double totalImpuesto = factura.getTotalFactura() * factura.getImpuesto().getPorcentaje() / 100;
        factura.setTotalImpuesto(totalImpuesto);


        try {
            serviceFactura.crear(factura);
            System.out.println("Se creó la factura");
            factura.display();
        } catch (FacturaExceptionInsertDataBase e) {
            System.out.println(e.getMessage());
        }
    }


    public static List<Factura> filtarFacturAnio(List<Factura> facturas, int year) {
        List<Factura> filteredFacturas = new ArrayList<>();
        for (Factura factura : facturas) {
            if (factura.getFecha().getYear() == year) {
                filteredFacturas.add(factura);
            }
        }
        return filteredFacturas;
    }


    public static void filtarFacturaAnio(List<Factura> facturas, int year) {
        List<Map<String, Object>> filteredFacturas = new ArrayList<>();
        for (Factura factura : facturas) {
            if (factura.getFecha().getYear() == year) {
                Map<String, Object> facturaReporte = new HashMap<>();
                facturaReporte.put("nombreCliente", factura.getCliente().getNombre());
                facturaReporte.put("apellidoCliente", factura.getCliente().getApellido());
                facturaReporte.put("documentoCliente", factura.getCliente().getDocumento());
                facturaReporte.put("telefonoCliente", factura.getCliente().getCelular());
                facturaReporte.put("direccionCliente", factura.getCliente().getDireccion());
                facturaReporte.put("numeroFactura", factura.getNumeroFactura());
                facturaReporte.put("fechaVenta", factura.getFecha());
                facturaReporte.put("totalFactura", factura.getTotalFactura());
                facturaReporte.put("impuestoPagado", factura.getTotalImpuesto());
                filteredFacturas.add(facturaReporte);
            }
        }

        ObjectMapper objectMapper = JacksonConfig.getObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(filteredFacturas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter("impuesto".concat(String.valueOf(year)).concat(".json"))) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void mostrarClientesPorCompras() {
        try {
            List<Map<String, Object>> clientes = serviceFactura.listarClientesPorCompras();
            for (Map<String, Object> cliente : clientes) {
                System.out.println("ID: " + cliente.get("id") + ", Nombre: " + cliente.get("nombre") + ", Apellido: " + cliente.get("apellido") + ", Total compras: " + cliente.get("total_compras"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarProductosMasVendidos() {
        try {
            List<Map<String, Object>> productos = serviceFactura.listarProductosMasVendidos();
            for (Map<String, Object> producto : productos) {
                System.out.println("Código: " + producto.get("codigo") + ", Nombre: " + producto.get("nombre") + ", Total vendido: " + producto.get("total_vendido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
