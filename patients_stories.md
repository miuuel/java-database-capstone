# Historia de Usuario 3: Paciente
Título:Como Paciente, quiero agendar y consultar mis citas médicas desde el portal en línea, para seleccionar horarios disponibles de manera autónoma sin necesidad de llamadas telefónicas.

Criterios de Aceptación:

El paciente puede filtrar la lista de médicos disponibles por especialidad y visualizar sus horarios de atención.

El paciente puede seleccionar un horario libre y confirmar la reserva de la cita, la cual queda registrada en MySQL.

El sistema impide reservas duplicadas o solapamientos en la agenda del médico para el mismo bloque horario.

Prioridad: Alta

Story Points: 5

Notas:

La interfaz consumirá las API RESTful de gestión de citas desarrolladas en el backend.

Debe incluir la funcionalidad para que el paciente consulte o cancele sus citas agendadas dentro de un rango de tiempo permitido.