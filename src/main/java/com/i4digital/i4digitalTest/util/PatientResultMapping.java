package com.i4digital.i4digitalTest.util;

import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.entities.PatientResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientResultMapping {

    //@Mapping(target = "typeRisk", ignore = true)
    PatientResult PatientResultDtoToPatientResult(PatientResultDto patientResultDto);

    PatientResultDto PatientResultToPatientResultDto(PatientResult patientResult);
}
