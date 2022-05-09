package com.i4digital.i4digitalTest.controllers;

import com.i4digital.i4digitalTest.dto.PatientResultDto;
import com.i4digital.i4digitalTest.services.PatientResultService;
import com.i4digital.i4digitalTest.util.ValidInfoPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/Patient")
public class PatientResultController {
    @Autowired
    private PatientResultService patientResultService;

    @Autowired
    private ValidInfoPatient validInfoPatient;

    @GetMapping("/results")
    Flux<PatientResultDto> getAll() {
        return patientResultService.getAllResults();
    }

    @PostMapping("/results")
    Mono<ResponseEntity<?>> saveResults(@RequestBody ArrayList<PatientResultDto> patientResults) {
        HashMap<Integer,String> resultsValidation=validInfoPatient.validInfoPercents(patientResults);
        if(!resultsValidation.isEmpty()){
           return Mono.just(ResponseEntity.badRequest().body(resultsValidation));
        }
        return patientResultService.saveResults(patientResults)
                .collectList()
                .map(results ->ResponseEntity.ok().body(results));
    }

    @GetMapping("/results/{dni}")
    Mono<PatientResultDto> getByDni(@PathVariable Integer dni) {
        return patientResultService.getByDni(dni);
    }
}
