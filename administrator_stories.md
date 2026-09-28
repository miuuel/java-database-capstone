# Historia de Usuario 1: Iniciar Sesión en el Portal
Título:
Como Administrador, quiero iniciar sesión en el portal con mi nombre de usuario y contraseña, para gestionar la plataforma de manera segura.

Criterios de Aceptación:

El sistema presenta un formulario con campos para nombre de usuario (o correo electrónico) y contraseña.

Al ingresar credenciales válidas, el sistema autentica al usuario, inicia la sesión y redirige al panel principal de administración.

Si las credenciales son incorrectas, el sistema muestra un mensaje de error descriptivo sin revelar cuál de los datos falló.

Prioridad: Alta

Story Points: 3

Notas:

Requiere integración con Spring Security y cifrado de contraseñas mediante BCrypt.

# Historia de Usuario 2: Cerrar Sesión en el Portal
Título:
Como Administrador, quiero cerrar sesión en el portal, para proteger el acceso al sistema y evitar el uso no autorizado de mi cuenta.

Criterios de Aceptación:

Existe un botón u opción visible de "Cerrar sesión" en la barra de navegación del panel de administración.

Al hacer clic, la sesión actual se invalida inmediatamente y se eliminan las cookies o tokens de autenticación asociados.

El sistema redirige automáticamente al usuario a la pantalla de inicio de sesión.

Prioridad: Alta

Story Points: 1

Notas:

Garantiza la seguridad en equipos compartidos dentro de la clínica.

# Historia de Usuario 3: Agregar Médicos al Portal
Título:
Como Administrador, quiero agregar nuevos doctores al portal, para registrar al personal médico y habilitar su disponibilidad en la plataforma.

Criterios de Aceptación:

El formulario de registro solicita datos obligatorios como nombre completo, especialidad, correo electrónico, teléfono y credenciales iniciales.

El sistema valida que el correo electrónico no esté registrado previamente en MySQL antes de guardar la información.

Al guardar exitosamente, la nueva entidad de doctor se persiste en MySQL y el médico queda disponible para recibir asignaciones de citas.

Prioridad: Alta

Story Points: 5

Notas:

Se implementa mediante el controlador MVC con vistas Thymeleaf en la capa administrativa.

# Historia de Usuario 4: Eliminar Perfil de Doctor
Título:
Como Administrador, quiero eliminar o desactivar el perfil de un doctor del portal, para retirar del sistema al personal que ya no labora en la clínica.

Criterios de Aceptación:

El administrador puede seleccionar la opción de eliminar desde la lista o vista detallada del perfil del médico.

El sistema solicita una confirmación explícita mediante un diálogo emergente antes de procesar la acción.

Se aplica un borrado lógico (desactivación de estado) en la base de datos MySQL para preservar el historial de citas asociadas al médico.

Prioridad: Media

Story Points: 3

Notas:

Evitar borrado físico directo para no romper la integridad referencial con la tabla de citas pasadas.

# Historia de Usuario 5: Consultar Estadísticas de Citas vía MySQL CLI
Título:
Como Administrador, quiero ejecutar un procedimiento almacenado desde MySQL CLI, para obtener el número de citas por mes y analizar el volumen de uso de la plataforma.

Criterios de Aceptación:

Existe un procedimiento almacenado en MySQL (ej. sp_obtener_citas_por_mes) que agrupa y cuenta las citas por año y mes.

El procedimiento puede ser invocado directamente desde la línea de comandos de MySQL (CALL sp_obtener_citas_por_mes()).

El resultado devuelve una tabla con el mes, año y total de citas registradas en dicho periodo.

Prioridad: Media

Story Points: 3

Notas:

Tarea orientada a administración de base de datos para monitoreo y reporte sin sobrecargar la aplicación Spring Boot.