# 1. Base de Datos Relacional: MySQL
-- =============================================================================
-- Script de Creación de Base de Datos Relacional (MySQL 8.0+)
-- Proyecto: Sistema de Gestión de Clínicas SmartCare
-- =============================================================================

CREATE DATABASE IF NOT EXISTS smartcare_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE smartcare_db;

-- -----------------------------------------------------------------------------
-- 1. Tabla: admins (Mapeada desde Admin.java)
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS admins (
    id BIGINT AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_admins PRIMARY KEY (id),
    CONSTRAINT uk_admins_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------------------------------
-- 2. Tabla: doctors (Mapeada desde Doctor.java)
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS doctors (
    id BIGINT AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    specialty VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_doctors PRIMARY KEY (id),
    CONSTRAINT uk_doctors_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------------------------------
-- 3. Tabla: patients (Mapeada desde Patient.java)
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS patients (
    id BIGINT AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    date_of_birth DATE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_patients PRIMARY KEY (id),
    CONSTRAINT uk_patients_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------------------------------
-- 4. Tabla: appointments (Mapeada desde Appointment.java)
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS appointments (
    id BIGINT AUTO_INCREMENT,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED',
    reason TEXT NULL,
    
    CONSTRAINT pk_appointments PRIMARY KEY (id),
    
    -- Claves Foráneas
    CONSTRAINT fk_appointments_patient FOREIGN KEY (patient_id)
        REFERENCES patients(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
        
    CONSTRAINT fk_appointments_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctors(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
        
    -- Restricción de Dominio mapeada desde AppointmentStatus.java
    CONSTRAINT chk_appointments_status CHECK (status IN ('SCHEDULED', 'COMPLETED', 'CANCELLED'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------------------------------
-- 5. Índices de Rendimiento
-- -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS doctor_available_times (
    doctor_id BIGINT NOT NULL,
    available_times VARCHAR(20) NOT NULL,
    
    CONSTRAINT fk_doctor_available_times_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctors(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------------------------------
-- 6. Índices de Rendimiento
-- -----------------------------------------------------------------------------

-- Optimiza la verificación de solapamiento de horarios al agendar citas por médico
CREATE INDEX idx_doctor_schedule ON appointments (doctor_id, start_time, end_time);

-- Acelera la consulta del historial de citas agendadas por un paciente
CREATE INDEX idx_patient_appointments ON appointments (patient_id, start_time);

# 2. Base de Datos NoSQL: MongoDB

use smartcare_db;

db.createCollection("prescriptions", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: ["appointment_id", "patient_id", "doctor_id", "issued_at", "diagnosis", "medications"],
         properties: {
            appointment_id: {
               bsonType: "long",
               description: "ID relacional de la cita (obligatorio)"
            },
            patient_id: {
               bsonType: "long",
               description: "ID relacional del paciente (obligatorio)"
            },
            doctor_id: {
               bsonType: "long",
               description: "ID relacional del doctor (obligatorio)"
            },
            issued_at: {
               bsonType: "date",
               description: "Fecha y hora de emisión (obligatorio)"
            },
            diagnosis: {
               bsonType: "string",
               description: "Diagnóstico médico (obligatorio)"
            },
            notes: {
               bsonType: "string"
            },
            vital_signs: {
               bsonType: "object",
               properties: {
                  bloodPressure: { bsonType: "string" },
                  heartRateBpm: { bsonType: "int" },
                  temperatureCelsius: { bsonType: "double" },
                  weightKg: { bsonType: "double" }
               }
            },
            medications: {
               bsonType: "array",
               minItems: 1,
               description: "Debe incluir al menos un medicamento",
               items: {
                  bsonType: "object",
                  required: ["name", "dosage", "frequency", "duration"],
                  properties: {
                     name: { bsonType: "string" },
                     dosage: { bsonType: "string" },
                     frequency: { bsonType: "string" },
                     duration: { bsonType: "string" },
                     specialInstructions: { bsonType: "string" }
                  }
               }
            }
         }
      }
   }
});

// Índices para optimizar búsquedas frecuentes
db.prescriptions.createIndex({ "patient_id": 1, "issued_at": -1 });
db.prescriptions.createIndex({ "doctor_id": 1, "issued_at": -1 });
db.prescriptions.createIndex({ "appointment_id": 1 }, { unique: true });