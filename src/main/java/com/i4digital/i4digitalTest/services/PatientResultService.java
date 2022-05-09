package com.i4digital.i4digitalTest.services;

import com.i4digital.i4digitalTest.adapters.PatientResultRepositoryAdapter;
import com.i4digital.i4digitalTest.costants.TypeRisk;
import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.entities.PatientResult;
import com.i4digital.i4digitalTest.util.ValidInfoPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

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
                .map(patientResultDto -> {
                    TypeRisk typeRisk=validInfoPatient.ValidTypeRisk(patientResultDto.getSugar(),patientResultDto.getFat(),patientResultDto.getOxygen());
                    patientResultDto.setTypeRisk(typeRisk);
                    return patientResultDto;
                })
                .flatMap(this.patientResultRepositoryAdapter::save);

    }
    public Mono<PatientResultDto> getByDni(Integer dni){
        return this.patientResultRepositoryAdapter.getByDni(dni);
    }




}
