package app;

import java.util.Scanner;
import modelo.*;
import servicio.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorClientes gestorClientes = new GestorClientes();
        GestorProductos gestorProductos = new GestorProductos();
        GestorVentas gestorVentas = new GestorVentas();
        GestorReportes gestorReportes = new GestorReportes();

        int opcion;
        do {
            System.out.println("\n========= MENÚ PRINCIPAL =========");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Clientes");
            System.out.println("3. Gestionar Productos");
            System.out.println("4. Registrar Venta");
            System.out.println("5. Listar Ventas");
            System.out.println("6. Generar Reporte");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                // USUARIOS
                case 1 -> {
                    System.out.println("\n--- GESTIÓN DE USUARIOS ---");
                    System.out.println("1. Registrar usuario");
                    System.out.println("2. Modificar usuario");
                    System.out.println("3. Eliminar usuario");
                    System.out.println("4. Listar usuarios");
                    System.out.print("Opción: ");
                    int sub = sc.nextInt(); sc.nextLine();
                    switch (sub) {
                        case 1 -> {
                            System.out.print("Nombre: "); String nom = sc.nextLine();
                            System.out.print("Apellido: "); String ape = sc.nextLine();
                            System.out.print("Usuario: "); String usr = sc.nextLine();
                            System.out.print("Clave: "); String pass = sc.nextLine();
                            System.out.print("Rol: "); String rol = sc.nextLine();
                            gestorUsuarios.registrarUsuario(new Usuario(0, nom, ape, usr, pass, rol));
                        }
                        case 2 -> {
                            System.out.print("ID usuario a modificar: "); int id = sc.nextInt(); sc.nextLine();
                            System.out.print("Nuevo nombre: "); String nn = sc.nextLine();
                            System.out.print("Nuevo apellido: "); String na = sc.nextLine();
                            gestorUsuarios.modificarUsuario(id, nn, na);
                        }
                        case 3 -> {
                            System.out.print("ID usuario a eliminar: "); int id = sc.nextInt();
                            gestorUsuarios.eliminarUsuario(id);
                        }
                        case 4 -> {
                            List<Usuario> us = gestorUsuarios.listarUsuarios();
                            us.forEach(System.out::println);
                        }
                        default -> System.out.println("⚠️ Opción inválida.");
                    }
                }

                // CLIENTES
                case 2 -> {
                    System.out.println("\n--- GESTIÓN DE CLIENTES ---");
                    System.out.println("1. Registrar cliente");
                    System.out.println("2. Eliminar cliente");
                    System.out.println("3. Listar clientes");
                    System.out.print("Opción: ");
                    int sub = sc.nextInt(); sc.nextLine();
                    switch (sub) {
                        case 1 -> {
                            System.out.print("Nombre: "); String nom = sc.nextLine();
                            System.out.print("Apellido: "); String ape = sc.nextLine();
                            System.out.print("Email: "); String mail = sc.nextLine();
                            System.out.print("Teléfono: "); String tel = sc.nextLine();
                            gestorClientes.registrarCliente(new Cliente(0, nom, ape, mail, tel));
                        }
                        case 2 -> {
                            System.out.print("ID cliente a eliminar: "); int id = sc.nextInt();
                            gestorClientes.eliminarCliente(id);
                        }
                        case 3 -> gestorClientes.listarClientes().forEach(System.out::println);
                        default -> System.out.println("⚠️ Opción inválida.");
                    }
                }

                // PRODUCTOS
                case 3 -> {
                    System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
                    System.out.println("1. Registrar producto");
                    System.out.println("2. Modificar producto");
                    System.out.println("3. Eliminar producto");
                    System.out.println("4. Listar productos");
                    System.out.print("Opción: ");
                    int sub = sc.nextInt(); sc.nextLine();
                    switch (sub) {
                        case 1 -> {
                            System.out.print("Nombre: "); String nom = sc.nextLine();
                            System.out.print("Categoría: "); String cat = sc.nextLine();
                            System.out.print("Precio: "); double precio = sc.nextDouble();
                            System.out.print("Stock: "); int stock = sc.nextInt(); sc.nextLine();
                            gestorProductos.registrarProducto(new Producto(0, nom, cat, precio, stock));
                        }
                        case 2 -> {
                            System.out.print("ID producto: "); int idp = sc.nextInt(); sc.nextLine();
                            System.out.print("Nuevo nombre: "); String nn = sc.nextLine();
                            System.out.print("Nuevo stock: "); int ns = sc.nextInt(); sc.nextLine();
                            gestorProductos.modificarProducto(idp, nn, ns);
                        }
                        case 3 -> {
                            System.out.print("ID producto a eliminar: "); int idp = sc.nextInt();
                            gestorProductos.eliminarProducto(idp);
                        }
                        case 4 -> gestorProductos.listarProductos().forEach(System.out::println);
                        default -> System.out.println("⚠️ Opción inválida.");
                    }
                }

                // VENTAS
                case 4 -> {
                    System.out.println("\n--- REGISTRAR VENTA ---");
                    System.out.print("ID Cliente: "); int idC = sc.nextInt();
                    System.out.print("ID Usuario (vendedor): "); int idU = sc.nextInt();
                    Venta venta = new Venta(idC, idU);

                    String mas;
                    do {
                        System.out.print("ID Producto: "); int idP = sc.nextInt();
                        System.out.print("Cantidad: "); int cant = sc.nextInt();
                        Producto p = gestorProductos.buscarProducto(idP);
                        if (p == null) System.out.println("⚠️ Producto no encontrado.");
                        else {
                            DetalleVenta d = new DetalleVenta(p, cant);
                            venta.agregarDetalle(d);
                            System.out.println("🧾 Agregado: " + d);
                        }
                        System.out.print("Agregar otro producto? (s/n): "); mas = sc.next();
                    } while (mas.equalsIgnoreCase("s"));

                    gestorVentas.registrarVenta(venta);
                }

                case 5 -> {
                    System.out.println("\n--- LISTADO DE VENTAS ---");
                    List<Venta> ventas = gestorVentas.listarVentas();
                    ventas.forEach(v -> {
                        System.out.println(v);
                        v.getDetalles().forEach(d -> System.out.println("   " + d));
                    });
                }

                case 6 -> {
                    System.out.println("\n--- REPORTE DE VENTAS ---");
                    gestorReportes.generarReporteVentas(gestorVentas);
                }

                case 0 -> System.out.println("👋 Saliendo...");

                default -> System.out.println("⚠️ Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
