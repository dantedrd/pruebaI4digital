package com.i4digital.i4digitalTest.util;

import com.i4digital.i4digitalTest.costants.TypeRisk;
import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.entities.PatientResult;
import com.i4digital.i4digitalTest.util.ValidInfoPatient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ValidInfoPatientTest {

    @InjectMocks
    ValidInfoPatient validInfoPatient;

    @Test
    void testShouldValidRiskHigh() {
        TypeRisk risk=validInfoPatient.ValidTypeRisk(80.0,89.0,58.0);
        assertEquals(TypeRisk.HIGH,risk);
    }

    @Test
    void testShouldValidRiskMedium() {
        TypeRisk risk=validInfoPatient.ValidTypeRisk(65.0,63.0,62.0);
        assertEquals(TypeRisk.MEDIUM,risk);
    }

    @Test
    void testShouldValidRiskLow() {
        TypeRisk risk=validInfoPatient.ValidTypeRisk(48.1,50.0,72.0);
        assertEquals(TypeRisk.Low,risk);
    }

    @Test
    void testShouldValidPercents() {
        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setId(1L);
        patientResultDto.setDni(123);
        patientResultDto.setSugar(105.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);
        patientResultDto.setTypeRisk(TypeRisk.HIGH);
        ArrayList<PatientResultDto> patientResultDtos=new ArrayList<>();
        patientResultDtos.add(patientResultDto);
        HashMap<Integer,String> valid=validInfoPatient.validInfoPercents(patientResultDtos);
        assertEquals(valid.get(123),"Los valores suministrados no son correctos para los % deben ser [0-100]");
    }




}