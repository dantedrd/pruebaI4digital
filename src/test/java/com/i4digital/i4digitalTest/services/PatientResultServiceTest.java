package com.i4digital.i4digitalTest.services;

import com.i4digital.i4digitalTest.adapters.PatientResultRepositoryAdapter;
import com.i4digital.i4digitalTest.costants.TypeRisk;
import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.entities.PatientResult;
import com.i4digital.i4digitalTest.util.ValidInfoPatient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientResultServiceTest {

    @InjectMocks
    private PatientResultService patientResultService;

    @Mock
    private PatientResultRepositoryAdapter patientResultRepositoryAdapter;

    @Mock
    private ValidInfoPatient validInfoPatient;

    @Test
    void testShouldGetAllResults() {
        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);

        Mockito.when(patientResultRepositoryAdapter.getAll()).thenReturn(Flux.fromIterable(Arrays.asList(patientResultDto)));

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
        patientResultDto.setDni(1234);
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);


        Mockito.when(validInfoPatient.ValidTypeRisk(Mockito.anyDouble(),Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(TypeRisk.HIGH);
        Mockito.when(patientResultRepositoryAdapter.getByDni(Mockito.anyInt())).thenReturn(Mono.empty());
        Mockito.when(patientResultRepositoryAdapter.save(Mockito.any(PatientResultDto.class))).thenReturn(Mono.just(patientResultDto));


        ArrayList<PatientResultDto> patientResultDtos=new ArrayList<>();
        patientResultDtos.add(patientResultDto);



        StepVerifier
                .create(patientResultService.saveResults(patientResultDtos).collectList())
                .consumeNextWith(
                        buf ->assertEquals(patientResultDto.getDni(),buf.get(0).getDni()))
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


        Mockito.when(patientResultRepositoryAdapter.getByDni(Mockito.anyInt())).thenReturn(Mono.just(patientResultDto));


        StepVerifier
                .create(patientResultService.getByDni(1234))
                .consumeNextWith(
                        buf ->assertEquals(patientResultDto.getDni(),buf.getDni()))
                .expectComplete()
                .verify();

    }


}