package com.i4digital.i4digitalTest.adapters;

import com.i4digital.i4digitalTest.costants.TypeRisk;
import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.entities.PatientResult;
import com.i4digital.i4digitalTest.repositories.PatientResultRepository;
import com.i4digital.i4digitalTest.util.PatientResultMapping;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PatientResultRepositoryAdapterTest {

    @InjectMocks
    PatientResultRepositoryAdapter patientResultRepositoryAdapter;

    @Mock
    public PatientResultRepository repository;

    @Mock
    private PatientResultMapping mapper;

    @Test
    void testShouldGetAllResults() {
        PatientResult patientResult = new PatientResult();
        patientResult.setId(1L);
        patientResult.setSugar(60.0);
        patientResult.setFat(30.0);
        patientResult.setOxygen(20.0);
        patientResult.setTypeRisk("HIGH");

        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setId(1L);
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);
        patientResultDto.setTypeRisk(TypeRisk.HIGH);

        Mockito.when(repository.findAll()).thenReturn(Flux.fromIterable(Arrays.asList(patientResult)));
        Mockito.when(mapper.PatientResultToPatientResultDto(Mockito.any(PatientResult.class))).thenReturn(patientResultDto);

        StepVerifier
                .create(patientResultRepositoryAdapter.getAll().collectList())
                .consumeNextWith(
                        buf ->assertEquals(patientResultDto.getDni(),buf.get(0).getDni()))
                .expectComplete()
                .verify();


    }

    @Test
    void testShouldSaveResult() {
        PatientResult patientResult = new PatientResult();
        patientResult.setId(1L);
        patientResult.setSugar(60.0);
        patientResult.setFat(30.0);
        patientResult.setOxygen(20.0);
        patientResult.setTypeRisk("HIGH");

        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setId(1L);
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);
        patientResultDto.setTypeRisk(TypeRisk.HIGH);

        Mockito.when(repository.save(Mockito.any(PatientResult.class))).thenReturn(Mono.just(patientResult));
        Mockito.when(mapper.PatientResultDtoToPatientResult(Mockito.any(PatientResultDto.class))).thenReturn(patientResult);
        Mockito.when(mapper.PatientResultToPatientResultDto(Mockito.any(PatientResult.class))).thenReturn(patientResultDto);

        StepVerifier
                .create(patientResultRepositoryAdapter.save(patientResultDto))
                .consumeNextWith(
                        buf ->assertEquals(patientResultDto.getDni(),buf.getDni()))
                .expectComplete()
                .verify();


    }

    @Test
    void testShouldGetResultByDni() {
        PatientResult patientResult = new PatientResult();
        patientResult.setId(1L);
        patientResult.setSugar(60.0);
        patientResult.setFat(30.0);
        patientResult.setOxygen(20.0);
        patientResult.setTypeRisk("HIGH");

        PatientResultDto patientResultDto = new PatientResultDto();
        patientResultDto.setId(1L);
        patientResultDto.setSugar(60.0);
        patientResultDto.setFat(30.0);
        patientResultDto.setOxygen(20.0);
        patientResultDto.setTypeRisk(TypeRisk.HIGH);

        Mockito.when(repository.findByDni(Mockito.anyInt())).thenReturn(Mono.just(patientResult));
        Mockito.when(mapper.PatientResultDtoToPatientResult(Mockito.any(PatientResultDto.class))).thenReturn(patientResult);
        Mockito.when(mapper.PatientResultToPatientResultDto(Mockito.any(PatientResult.class))).thenReturn(patientResultDto);

        StepVerifier
                .create(patientResultRepositoryAdapter.getByDni(123))
                .consumeNextWith(
                        buf ->assertEquals(patientResultDto.getDni(),buf.getDni()))
                .expectComplete()
                .verify();


    }

}
