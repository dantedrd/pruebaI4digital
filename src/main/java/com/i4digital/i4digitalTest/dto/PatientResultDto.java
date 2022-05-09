package com.i4digital.i4digitalTest.dto;

import com.i4digital.i4digitalTest.costants.TypeRisk;

public class PatientResultDto {
    private Long id;
    private Integer dni;
    private Double fat;
    private Double oxygen;
    private Double sugar;
    private TypeRisk typeRisk;

    public PatientResultDto() {
    }

    public PatientResultDto(Long id, Integer dni, Double fat, Double oxygen, Double sugar, TypeRisk typeRisk) {
        this.id = id;
        this.dni = dni;
        this.fat = fat;
        this.oxygen = oxygen;
        this.sugar = sugar;
        this.typeRisk = typeRisk;
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

    public TypeRisk getTypeRisk() {
        return typeRisk;
    }

    public void setTypeRisk(TypeRisk typeRisk) {
        this.typeRisk = typeRisk;
    }
}
