# Historia de Usuario 2: Médico
Título:Como Médico, quiero emitir y adjuntar recetas médicas digitales al finalizar una consulta, para guardar un registro flexible e historial clínico detallado de mis pacientes.

Criterios de Aceptación:

El médico puede redactar una receta incluyendo medicamentos, dosis, instrucciones de consumo y observaciones adicionales asociadas al ID de la cita.

La receta se almacena en la base de datos no relacional MongoDB utilizando un esquema de documento flexible que soporte observaciones variables.

El médico puede visualizar el historial previo de recetas emitidas para el paciente seleccionado.

Prioridad: Alta

Story Points: 8

Notas:

Requiere interactuar con la capa de repositorio de Spring Data MongoDB.

Se debe validar que únicamente el médico asignado a la cita tenga permisos para emitir la receta.