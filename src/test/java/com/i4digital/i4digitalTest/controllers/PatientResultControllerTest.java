package com.i4digital.i4digitalTest.controllers;

import com.i4digital.i4digitalTest.costants.TypeRisk;
import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.services.PatientResultService;
import com.i4digital.i4digitalTest.util.ValidInfoPatient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.In;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class PatientResultControllerTest {

    @InjectMocks
    private PatientResultController patientResultController;

    @Mock
    private PatientResultService patientResultService;

    @Mock
    private ValidInfoPatient validInfoPatient;

    @Test
    void testShouldGetAllResults() {
        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setId(1L);
        patientResultDto.setDni(1234);
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);
        patientResultDto.setTypeRisk(TypeRisk.HIGH);

        Mockito.when(patientResultService.getAllResults()).thenReturn(Flux.fromIterable(Arrays.asList(patientResultDto)));


        StepVerifier
                .create(patientResultService.getAllResults().collectList())
                .consumeNextWith(
                        buf ->assertEquals(patientResultDto.getDni(),buf.get(0).getDni()))
                .expectComplete()
                .verify();

    }

    @Test
    void testShouldSaveAndGenerateTheResults() {

        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);
        ArrayList<PatientResultDto> patientResultDtos=new ArrayList<>();
        patientResultDtos.add(patientResultDto);

        Mockito.when(validInfoPatient.validInfoPercents(Mockito.any(ArrayList.class))).thenReturn(new HashMap());
        Mockito.when(patientResultService.saveResults(Mockito.any(ArrayList.class))).thenReturn(Flux.fromIterable(Arrays.asList(patientResultDto)));


        StepVerifier
                .create(patientResultController.saveResults(patientResultDtos))
                .consumeNextWith(
                        buf ->assertEquals(200,buf.getStatusCodeValue()))
                .expectComplete()
                .verify();

    }

    @Test
    void testNotShouldSaveTheResults() {

        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);
        ArrayList<PatientResultDto> patientResultDtos=new ArrayList<>();
        patientResultDtos.add(patientResultDto);

        HashMap<Integer,String> result=new HashMap();
        result.put(123,"Los valores suministrados no son correctos para los % deben ser [0-100]");

        Mockito.when(validInfoPatient.validInfoPercents(Mockito.any(ArrayList.class))).thenReturn(result);
        Mockito.when(patientResultService.saveResults(Mockito.any(ArrayList.class))).thenReturn(Flux.fromIterable(Arrays.asList(patientResultDto)));


        StepVerifier
                .create(patientResultController.saveResults(patientResultDtos))
                .consumeNextWith(
                        buf ->assertEquals(400,buf.getStatusCodeValue()))
                .expectComplete()
                .verify();

    }

    @Test
    void testShouldGetResultByDni() {
        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setId(1L);
        patientResultDto.setDni(1234);
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);
        patientResultDto.setTypeRisk(TypeRisk.HIGH);

        Mockito.when(patientResultService.getByDni(Mockito.any(Integer.class))).thenReturn(Mono.just(patientResultDto));

        StepVerifier
                .create(patientResultController.getByDni(1234))
                .consumeNextWith(
                        buf ->assertEquals(patientResultDto.getDni(),buf.getDni()))
                .expectComplete()
                .verify();
    }

}