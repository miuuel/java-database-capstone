# Historia de Usuario 1: Ver Lista de Doctores (Público)
Título:
Como Paciente no registrado, quiero ver la lista de doctores disponibles sin iniciar sesión, para explorar las opciones médicas antes de crear una cuenta.

Criterios de Aceptación:

El directorio de doctores es accesible públicamente sin solicitar credenciales o autenticación.

La vista muestra el nombre completo, especialidad, foto y horario general de atención de cada médico.

Si el usuario intenta agendar una cita directamente desde la lista, el sistema lo redirige al formulario de registro o inicio de sesión.

Prioridad: Media

Story Points: 3

Notas:

Vista o API pública que consulta las entidades de doctores en MySQL sin requerir token/sesión activa.

# Historia de Usuario 2: Registrarse en el Portal
Título:
Como Paciente, quiero registrarme en el portal usando mi correo electrónico y contraseña, para habilitar la opción de reservar citas médicas.

Criterios de Aceptación:

El formulario de registro solicita nombre completo, correo electrónico, teléfono y una contraseña segura.

El sistema valida que el correo electrónico no exista en la base de datos MySQL y que la contraseña cumpla con la longitud mínima requerida.

Tras un registro exitoso, el sistema guarda al usuario asignándole automáticamente el rol PACIENTE y permite iniciar sesión de inmediato.

Prioridad: Alta

Story Points: 5

Notas:

Las contraseñas deben cifrarse mediante BCrypt antes de almacenarse en MySQL.

# Historia de Usuario 3: Iniciar Sesión en el Portal
Título:
Como Paciente, quiero iniciar sesión en el portal con mi correo electrónico y contraseña, para gestionar mis reservas e información personal.

Criterios de Aceptación:

El usuario puede ingresar sus credenciales registradas en la pantalla de inicio de sesión.

El sistema verifica la autenticidad de las credenciales con el servicio de autenticación.

Si la autenticación es correcta, se redirige al paciente a su panel principal; si es incorrecta, se despliega un mensaje de alerta genérico.

Prioridad: Alta

Story Points: 3

Notas:

Integrado con la capa de seguridad de Spring Security para el manejo de sesiones o emisión de tokens.

# Historia de Usuario 4: Cerrar Sesión en el Portal
Título:
Como Paciente, quiero cerrar sesión en el portal, para proteger mi cuenta y mantener la privacidad de mi historial de salud.

Criterios de Aceptación:

El botón "Cerrar sesión" está visible y accesible en la barra superior de navegación del portal.

Al ejecutar la acción, la sesión del paciente se invalida y se limpian los datos de acceso temporal.

El sistema redirige automáticamente al portal público o a la vista de login.

Prioridad: Alta

Story Points: 1

Notas:

Impide que usuarios en dispositivos compartidos puedan navegar hacia atrás en el historial del navegador sin reautenticarse.

# Historia de Usuario 5: Reservar Cita de una Hora
Título:
Como Paciente autenticado, quiero seleccionar un doctor y reservar una cita de una hora de duración, para recibir la atención médica requerida.

Criterios de Aceptación:

El paciente selecciona un doctor, una fecha disponible y una franja horaria de 1 hora.

El sistema verifica en tiempo real que ni el doctor ni el paciente tengan otra cita solapada en ese bloque horario.

Al confirmar, el sistema registra la cita en la base de datos MySQL con estado "Programada" y muestra la confirmación en pantalla.

Prioridad: Alta

Story Points: 5

Notas:

Manejo de concurrencia en la capa de servicio para evitar doble reserva sobre el mismo horario. Consumido mediante API REST/controlador.

# Historia de Usuario 6: Ver Próximas Citas
Título:
Como Paciente, quiero consultar la lista de mis próximas citas programadas, para prepararme adecuadamente y asistir a tiempo a mis consultas.

Criterios de Aceptación:

El panel del paciente muestra una sección dedicada exclusivamente a las citas futuras (con fecha posterior a la actual).

Cada elemento del listado especifica fecha, hora, nombre del médico, especialidad y estado de la cita.

La lista se ordena cronológicamente, posicionando la cita más cercana en el primer lugar.

Prioridad: Alta

Story Points: 3

Notas:

Realiza una consulta filtrando las citas por el ID del paciente en MySQL ordenadas por fecha/hora ascendente.