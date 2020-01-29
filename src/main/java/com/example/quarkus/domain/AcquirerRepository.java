package com.example.quarkus.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AcquirerRepository {

  private Map<String, Acquirer> acquirers = new HashMap<>();

  public Acquirer save(@NonNull Acquirer acquirer) {
    if (acquirer.getId() == null) {
      acquirer.setId(UUID.randomUUID().toString());
    }

    log.info("Saving {}", acquirer);
    acquirers.put(acquirer.getId(), acquirer);
    log.info("Saved {}", acquirer);

    return acquirer;
  }

  public Collection<Acquirer> findAll() {
    log.info("Finding all acquirers");
    Collection<Acquirer> acquirers = this.acquirers.values();
    log.info("Found all acquirers");

    return acquirers;
  }

}