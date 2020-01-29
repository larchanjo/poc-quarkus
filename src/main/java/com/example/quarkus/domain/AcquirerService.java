package com.example.quarkus.domain;

import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AcquirerService {

  private final AcquirerRepository repository;

  public AcquirerService(@NonNull AcquirerRepository repository) {
    this.repository = repository;
  }

  public Acquirer create(@NonNull String card, @NonNull Double value) {
    Acquirer acquirer = Acquirer.builder()
        .card(card)
        .value(value)
        .build();
    log.info("Creating {}", acquirer);
    Acquirer savedAcquirer = repository.save(acquirer);
    log.info("Created {}", acquirer);

    return savedAcquirer;
  }

  public Collection<Acquirer> get() {
    log.info("Getting all acquirers");
    Collection<Acquirer> acquirers = repository.findAll();
    log.info("Got all acquirers");

    return acquirers;
  }

}