# Historia de Usuario 1: Iniciar Sesión en el Portal
Título:
Como Doctor, quiero iniciar sesión en el portal con mis credenciales, para gestionar mis citas y atender a los pacientes.

Criterios de Aceptación:

El portal presenta un formulario de ingreso solicitando correo electrónico/usuario y contraseña.

Al autenticarse con el rol DOCTOR, el sistema redirige al panel administrativo del médico (dashboard).

Si los datos son erróneos, el sistema despliega un mensaje de error sin especificar cuál credencial falló.

Prioridad: Alta

Story Points: 3

Notas:

Requiere autenticación mediante Spring Security y renderizado con Thymeleaf (@Controller).

# Historia de Usuario 2: Cerrar Sesión en el Portal
Título:
Como Doctor, quiero cerrar sesión en el portal, para proteger mis datos y la información confidencial de mis pacientes.

Criterios de Aceptación:

La opción de cerrar sesión está permanentemente visible en la barra superior de navegación.

Al ejecutar la acción, la sesión del médico queda invalidada de forma inmediata.

El sistema redirige automáticamente al usuario a la vista de inicio de sesión.

Prioridad: Alta

Story Points: 1

Notas:

Medida fundamental de privacidad para equipos compartidos en los consultorios de la clínica.

# Historia de Usuario 3: Ver Calendario de Citas
Título:
Como Doctor, quiero ver mi calendario de citas agendadas, para mantenerme organizado y planificar mi jornada de atención médica.

Criterios de Aceptación:

El portal ofrece una interfaz de agenda que muestra las citas agrupadas por día, semana o mes.

Cada cita exhibe la hora de inicio, duración (1 hora), nombre del paciente y estado (Programada, Completada, Cancelada).

El médico puede filtrar las citas por un rango de fechas específico.

Prioridad: Alta

Story Points: 5

Notas:

Realiza consultas en MySQL a través de Spring Data JPA filtrando las entidades de citas por el ID del doctor.

# Historia de Usuario 4: Marcar Indisponibilidad
Título:
Como Doctor, quiero marcar mis bloques de tiempo no disponibles, para que los pacientes solo puedan reservar en los horarios en los que realmente estoy atendiendo.

Criterios de Aceptación:

El doctor puede seleccionar fechas o franjas horarias específicas y marcarlas como "No disponible" (permisos, reuniones, descansos).

El sistema valida que no existan citas agendadas previamente en la franja horaria que se intenta bloquear; de haberlas, notifica al doctor para reprogramarlas primero.

Las franjas marcadas como no disponibles se bloquean automáticamente en el portal para que los pacientes no puedan seleccionarlas.

Prioridad: Media

Story Points: 5

Notas:

La capa de servicio debe aplicar validaciones de concurrencia al cruzar horarios de citas con la tabla/registro de indisponibilidad en MySQL.

# Historia de Usuario 5: Actualizar Perfil Profesional
Título:
Como Doctor, quiero actualizar mi información de contacto y especialización, para que los pacientes tengan mis datos actualizados en el portal público.

Criterios de Aceptación:

El doctor dispone de un formulario en su panel de configuración para modificar su teléfono, correo de contacto, especialidad y síntesis profesional.

El sistema valida la coherencia y formato de los datos ingresados antes de procesar el cambio.

Al guardar, la información del médico se actualiza en MySQL y se refleja inmediatamente en la lista pública de doctores.

Prioridad: Media

Story Points: 3

Notas:

Se implementa mediante una solicitud PUT/POST atendida por la capa de controlador que persiste sobre la entidad Doctor.

# Historia de Usuario 6: Ver Detalles del Paciente para Próximas Citas
Título:
Como Doctor, quiero consultar los detalles e historial del paciente asociado a una cita próxima, para prepararme adecuadamente antes de la consulta.

Criterios de Aceptación:

Al seleccionar una cita agendada, el médico puede ver la ficha del paciente (nombre, edad, teléfono de contacto y antecedentes registrados).

El médico puede consultar las recetas e historial clínico previo almacenado en MongoDB para dicho paciente.

El sistema restringe el acceso a la ficha clínica únicamente al doctor asignado a la cita correspondiente.

Prioridad: Alta

Story Points: 5

Notas:

Integra datos relacionales de MySQL (paciente y cita) con documentos flexibles de MongoDB (recetas e historial de observaciones).