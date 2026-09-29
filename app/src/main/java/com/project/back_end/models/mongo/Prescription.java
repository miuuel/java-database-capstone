package com.project.back_end.models.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.back_end.models.mongo.Medication;
import com.project.back_end.models.mongo.VitalSigns;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;

    @NotNull(message = "El ID de la cita asociada es obligatorio")
    @Field("appointment_id")
    private Long appointmentId;

    @NotNull(message = "El ID del paciente es obligatorio")
    @Field("patient_id")
    private Long patientId;

    @NotNull(message = "El ID del doctor es obligatorio")
    @Field("doctor_id")
    private Long doctorId;

    @NotNull(message = "La fecha de emisión es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Field("issued_at")
    private LocalDateTime issuedAt = LocalDateTime.now();

    @NotBlank(message = "El diagnóstico es obligatorio")
    private String diagnosis;

    private String notes;

    @Valid
    @Field("vital_signs")
    private VitalSigns vitalSigns;

    @NotEmpty(message = "Debe registrar al menos un medicamento en la receta")
    @Valid
    private List<Medication> medications = new ArrayList<>();

    public Prescription() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public VitalSigns getVitalSigns() { return vitalSigns; }
    public void setVitalSigns(VitalSigns vitalSigns) { this.vitalSigns = vitalSigns; }

    public List<Medication> getMedications() { return medications; }
    public void setMedications(List<Medication> medications) { this.medications = medications; }
}