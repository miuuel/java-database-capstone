# Smart Clinic Managemenrt System

Sección 1: Resumen de la arquitectura

Aplicación de Spring Boot utiliza controladores MVC como REST. Plantillas de Thymeleaf para los Dashboard de administración 
y de doctor, mientras que las API REST sirven a todos los demás módulos. Interactúa con dos DB: MySQL (para datos de pacientes,
doctores, citas y administración) y MongoDB (para recetas). Todos los controladores dirigen las solicitudes a través de una capa de servicio común, 
que a su vez delega en los repositorios apropiados. MySQL utiliza entidades JPA mientras que MongoDB utiliza modelos de documentos.

Sección 2: Flujo numerado de datos y control

Capa de Interfaz de Usuario (UI)
Paneles Web (Server-Side Rendering): Plantillas de Thymeleaf que renderizan dinámicamente las vistas HTML para los paneles de administración y de doctores.

Clientes API: Consumidores externos (aplicaciones móviles u otros servicios) que consumen las respuestas JSON expuestas por los endpoints REST.

Capa del Controlador
Controladores MVC (@Controller): Atienden las peticiones web de los paneles administrativos y de doctores, retornando vistas de Thymeleaf.

Controladores REST (@RestController): Gestionan las solicitudes HTTP para el resto de los módulos de la aplicación, devolviendo datos estructurados (JSON).

Responsabilidad: Recibir las solicitudes de los usuarios/clientes y redirigirlas hacia la capa de servicio común.

Capa de Servicio
Lógica de Negocio Centralizada: Servicios de Spring (@Service) comunes que procesan las reglas de negocio.

Orquestación: Recibe las peticiones desde los controladores (tanto MVC como REST) y delega las operaciones de persistencia a los repositorios correspondientes.

Capa de Repositorio
Spring Data JPA Repositories: Interfaces para el acceso a datos relacionales (CRUD y consultas personalizadas sobre MySQL).

Spring Data MongoDB Repositories: Interfaces para la manipulación y consulta de documentos NoSQL (MongoDB).

Acceso a la Base de Datos
MySQL (Base de Datos Relacional):

Modelo: Entidades JPA (@Entity).

Dominio: Datos de pacientes, doctores, citas y datos administrativos.

MongoDB (Base de Datos NoSQL):

Modelo: Modelos de documentos (@Document).

Dominio: Almacenamiento y gestión de recetas médicas.