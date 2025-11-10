## TP4 - Sistema de Gestión y Análisis de Ventas
Seminario de Práctica de Informática - Universidad Siglo 21 Alumno: Agustín Tiziano Greco Año: 2025

## 📘 Descripción general
PYME Ventas es un sistema desarrollado en Java que permite la gestión integral de una pequeña empresa, incluyendo la administración de usuarios, clientes, productos, ventas y reportes. Este proyecto forma parte del Trabajo Práctico N°4, incorporando la persistencia de datos en MySQL, el uso de POO, manejo de excepciones, y un enfoque modular.

## 🧩 Estructura del proyecto
El proyecto está organizado en paquetes:

src/ ├── app/ │ └── Main.java ├── modelo/ │ ├── Usuario.java │ ├── Cliente.java │ ├── Producto.java │ ├── Venta.java │ ├── DetalleVenta.java │ └── Reporte.java └── servicio/ ├── GestorUsuarios.java ├── GestorClientes.java ├── GestorProductos.java ├── GestorVentas.java └── GestorReportes.java └── ConexionBD.java

## 💾 Base de datos MySQL
El sistema se conecta a una base de datos remota mediante JDBC (Java Database Connectivity). La base fue creada en phpMyAdmin e incluye las tablas principales: Usuario, Cliente, Producto, Venta, Detalle_Venta, Reporte.

## ⚙️ Funcionalidades principales
Gestión de usuarios: alta, modificación y eliminación.

Gestión de clientes: registro y consulta.

Gestión de productos: registro, modificación, eliminación y listado.

Registro de ventas: con detalles de productos y cálculo automático del total.

Generación de reportes: resumen de ventas totales.

Persistencia real: todas las operaciones se reflejan directamente en la base de datos MySQL.

## 🧠 Conceptos aplicados
Encapsulamiento, herencia, polimorfismo y abstracción.

Estructuras de control y repetitivas (switch, do-while, for).

Uso de ArrayList para almacenar objetos en memoria.

Manejo de excepciones SQL con bloques try-catch.

Patrón de diseño: Modelo–Servicio–Controlador (MSC).

## 🧪 Ejemplo de uso
Ejecutar Main.java en Eclipse.

Desde el menú principal:

Registrar un nuevo producto/cliente, etc.

Listar productos y verificarlo en phpMyAdmin.

## 🧩 Video de presentación
🎥 Ver demostración completa: 👉 YouTube: https://www.youtube.com/watch?v=4RahFvioQIs

## 💻 Requisitos para ejecución
Java JDK 17 o superior.

Eclipse IDE.

Conexión a Internet (para acceder a la base de datos remota).

Driver JDBC para MySQL (mysql-connector-j.jar).

Observar los cambios reflejados en la base de datos remota.

