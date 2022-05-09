package com.i4digital.i4digitalTest.repositories;
import com.i4digital.i4digitalTest.entities.PatientResult;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PatientResultRepository extends ReactiveCrudRepository<PatientResult, Long> {
  public Mono<PatientResult> findByDni(Integer dni);
}
