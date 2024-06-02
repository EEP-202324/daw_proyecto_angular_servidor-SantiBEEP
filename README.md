[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/6iGMrP35)

# Angular CRUD App

Esta es una aplicación Angular con un servidor backend que soporta métodos GET, POST, PUT y DELETE para operaciones CRUD.

## Descripción

La aplicación permite:
- **GET:** Obtener una lista de Unis.
- **GET(id):** Obtener una Uni por id para ver sus Detalles
- **POST:** Añadir una nuevo Unui.
- **PUT:** Actualizar una Uni existente.
- **DELETE:** Eliminar una Uni.

## Uso

1. Inicia el servidor backend:
    El servidor se ejecutará en `http://localhost:8080`.

2. Inicia la aplicación Angular:
    La aplicación estará en `http://localhost:4200`.

## Navegación por la Aplicación

La aplicación cuenta con 2 páginas principales y otra subpágina(Detalles):

1. **Página de "Inicio"** (`/dashboard`):
    - Muestra una lista de elementos obtenidos del servidor en formato de Dashboard.
    - Permite navegar a la página de detalles de un elemento específico del Dashboard.
    - Permite buscar universidades dentero de la base de datos.
    - Mensajes que aparecen todo el tiempo en cualquier página.

2. **Página de Universidades** (`/universidades`):
    - Muestra un listado de todas las universidades.
    - Permite editar o eliminar el elemento.
    - Mensajes que aparecen todo el tiempo en cualquier página.

3. **Página de Detalles** (`/detalles`):
    - Permite editar el elemento.
    - Con un boton para volver a la página anterior.
    - Mensajes que aparecen todo el tiempo en cualquier página.

## Explicación Detallada

[![Explicación Detallada](https://eepmad-my.sharepoint.com/:v:/g/personal/santiago-badajoz1_eep-igroup_com/EZJLwwT6N49JvOweE656MucB3Ma2VYGApYZCFgTVtqJfvA?e=qDmq9V&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D)