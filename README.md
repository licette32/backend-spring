# 🧾 Proyecto: Sistema de Gestión de Artículos

Este proyecto implementa un sistema simple para la gestión de artículos (productos), con un backend desarrollado en **Spring Boot** y un frontend en **HTML, CSS y JavaScript puro**. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre una base de datos relacional.

---

## 📌 Descripción General

El sistema permite al usuario:

- Agregar nuevos artículos
- Visualizar el listado completo
- Modificar información existente
- Eliminar artículos

Este proyecto tiene como objetivo demostrar la integración entre una API RESTful y una interfaz web simple.

---

## 🧩 Características

### 🔙 Backend - API REST

- Listar todos los artículos
- Obtener un artículo por ID
- Crear un nuevo artículo
- Actualizar artículos existentes
- Eliminar artículos

### 🔝 Frontend - Interfaz Web

- Formulario para creación y edición
- Tabla para visualizar artículos
- Botones para editar y eliminar
- Mensajes de éxito y error

---

## 🧾 Modelo de Datos

Cada artículo posee los siguientes atributos:

| Atributo         | Descripción                          |
|------------------|--------------------------------------|
| `id`             | Identificador único (auto-generado)  |
| `nombre`         | Nombre del artículo                  |
| `categoria`      | Categoría del artículo               |
| `precio`         | Precio en moneda local               |
| `cantidadEnStock`| Stock disponible                     |
| `pathFoto`       | URL de la imagen del artículo        |

---

## 🛠️ Tecnologías Utilizadas

### Backend
- Java 17+
- Spring Boot
- Spring Data JPA + Hibernate
- Maven

### Base de Datos
- MySQL

### Frontend
- HTML5
- CSS3
- JavaScript (ES6+)

---

## 📎 Requisitos Previos

- JDK 17+
- Maven
- MySQL Server y cliente (Workbench, phpMyAdmin, DBeaver, etc.)
- (Opcional) Postman para probar la API

---

## ⚙️ Configuración del Proyecto

### 1. Crear Base de Datos en MySQL

```sql
CREATE DATABASE IF NOT EXISTS articulos_db;
USE articulos_db;
