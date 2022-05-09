package com.i4digital.i4digitalTest.adapters;

import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.entities.PatientResult;
import com.i4digital.i4digitalTest.repositories.PatientResultRepository;
import com.i4digital.i4digitalTest.util.PatientResultMapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PatientResultRepositoryAdapter {
    private PatientResultRepository repository;

    private PatientResultMapping mapper = Mappers.getMapper(PatientResultMapping.class);

    @Autowired
    public PatientResultRepositoryAdapter(PatientResultRepository repository) {
        this.repository = repository;
    }

    public Flux<PatientResultDto> getAll() {
        return repository
                .findAll()
                .map(mapper::PatientResultToPatientResultDto);
    }

    public Mono<PatientResultDto> save(PatientResultDto patientResultDto) {
        return repository
                .save(mapper.PatientResultDtoToPatientResult(patientResultDto))
                .map(mapper::PatientResultToPatientResultDto);
    }
    public Mono<PatientResultDto> getByDni(Integer dni) {
        return repository
                .findByDni(dni)
                .map(mapper::PatientResultToPatientResultDto);
    }


}
