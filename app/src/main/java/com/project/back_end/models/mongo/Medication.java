package com.project.back_end.models.mongo;

import jakarta.validation.constraints.NotBlank;

public class Medication {

    @NotBlank(message = "El nombre del medicamento es obligatorio")
    private String name;

    @NotBlank(message = "La dosis es obligatoria")
    private String dosage;

    @NotBlank(message = "La frecuencia es obligatoria")
    private String frequency;

    @NotBlank(message = "La duración es obligatoria")
    private String duration;

    private String specialInstructions;

    public Medication() {}

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getSpecialInstructions() { return specialInstructions; }
    public void setSpecialInstructions(String specialInstructions) { this.specialInstructions = specialInstructions; }
}
