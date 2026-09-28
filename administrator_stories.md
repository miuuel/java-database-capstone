# Historia de Usuario 1: Administrador
Título:Como Administrador, quiero registrar y gestionar perfiles de médicos y pacientes en el sistema, para mantener la información de la clínica actualizada y asignar los permisos de acceso correspondientes.

Criterios de Aceptación:

El administrador puede crear, editar, consultar y desactivar usuarios asignando explícitamente su rol (Administrador, Médico, Paciente).

Los datos personales y credenciales de los usuarios se persisten de manera estructurada en la base de datos MySQL.

El sistema valida que el correo electrónico sea único antes del registro y aplica cifrado a las contraseñas.

Prioridad: Alta

Story Points: 5

Notas:

Operación realizada mediante el panel de administración renderizado con Thymeleaf y Spring MVC.

Es requisito aplicar el control de acceso basado en roles (RBAC) para restringir este módulo solo al rol Administrador.