# **API de Franquicias**

Esta es una API desarrollada en **Spring Boot** que gestiona franquicias, sucursales y productos. Está diseñada para persistir los datos en **MongoDB Atlas** y puede ser empaquetada en un contenedor Docker.

---

## **Características**
- Gestión de franquicias: creación, lectura y eliminación.
- Gestión de sucursales: vinculación a una franquicia, productos asociados.
- Gestión de productos: creación, modificación de stock y eliminación.
- Compatible con **MongoDB Atlas** para persistencia de datos.
- Implementación de **Docker** para despliegue y uso en contenedores.

---

## **Requisitos Previos**
1. Tener instalado:
   - [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
   - [Maven](https://maven.apache.org/install.html)
   - [Docker Desktop](https://www.docker.com/products/docker-desktop)
---

## **Configuración del Proyecto**

1. Clona el repositorio:
   
   git clone https://github.com/<tu-usuario>/<tu-repositorio>.git

   cd franquiciasApp


  ## Uso de Docker
  Construcción y Ejecución del Contenedor
  Construye la imagen Docker:
    docker build -t mi-api-springboot .

  Ejecuta el contenedor:
    docker run -p 8080:8080 mi-api-springboot

  La API estará disponible en:
    http://localhost:8080
