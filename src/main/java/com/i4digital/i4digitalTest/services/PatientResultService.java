package com.i4digital.i4digitalTest.services;

import com.i4digital.i4digitalTest.adapters.PatientResultRepositoryAdapter;
import com.i4digital.i4digitalTest.costants.TypeRisk;
import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.util.ValidInfoPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class PatientResultService {
    @Autowired
    private PatientResultRepositoryAdapter patientResultRepositoryAdapter;

    @Autowired
    private ValidInfoPatient validInfoPatient;

    public Flux<PatientResultDto> getAllResults(){
        return this.patientResultRepositoryAdapter.getAll();
    }

    public Flux<PatientResultDto> saveResults(ArrayList<PatientResultDto> patientResults){
         return Flux.fromIterable(patientResults)
                 .map(this::validAndAssingRisk)
                 .flatMap(this::saveResultPatient);

    }
    public Mono<PatientResultDto> getByDni(Integer dni){
        return this.patientResultRepositoryAdapter.getByDni(dni);
    }

    private PatientResultDto validAndAssingRisk(PatientResultDto patientResultDto){
        TypeRisk typeRisk=validInfoPatient.ValidTypeRisk(patientResultDto.getSugar(),patientResultDto.getFat(),patientResultDto.getOxygen());
        patientResultDto.setTypeRisk(typeRisk);
        return patientResultDto;
    }

    private Mono<PatientResultDto> saveResultPatient(PatientResultDto patientResultDto){
        return this.patientResultRepositoryAdapter.getByDni(patientResultDto.getDni())
                .defaultIfEmpty(new PatientResultDto())
                .flatMap(patientResult ->
                        Objects.isNull(patientResult.getDni())?
                                patientResultRepositoryAdapter.save(patientResultDto):
                                this.updateResultPatient(patientResult,patientResultDto));
    }

    private Mono<PatientResultDto> updateResultPatient(PatientResultDto patientResultDtoBefore,PatientResultDto patientResultDtoNew){
        patientResultDtoBefore.setFat(patientResultDtoNew.getFat());
        patientResultDtoBefore.setOxygen(patientResultDtoNew.getOxygen());
        patientResultDtoBefore.setSugar(patientResultDtoNew.getSugar());
        patientResultDtoBefore.setTypeRisk(patientResultDtoNew.getTypeRisk());
        return patientResultRepositoryAdapter.save(patientResultDtoBefore);
    }




}
