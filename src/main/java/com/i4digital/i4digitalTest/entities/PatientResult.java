package com.i4digital.i4digitalTest.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Patient_Results")
public class PatientResult {
    @Id
    private Long id;
    private Integer dni;
    private Double fat;
    private Double oxygen;
    private Double sugar;
    private String typeRisk;

    public PatientResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getOxygen() {
        return oxygen;
    }

    public void setOxygen(Double oxygen) {
        this.oxygen = oxygen;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public String getTypeRisk() {
        return typeRisk;
    }

    public void setTypeRisk(String typeRisk) {
        this.typeRisk = typeRisk;
    }
}
