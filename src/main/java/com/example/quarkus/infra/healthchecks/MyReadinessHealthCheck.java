package com.example.quarkus.infra.healthchecks;

import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness // the readiness check accessible at /health/ready
@ApplicationScoped
public class MyReadinessHealthCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    // Name -> HealthCheckResponse.named()
    // Up -> HealthCheckResponse.up()
    // Down -> HealthCheckResponse.down()
    // With Data -> HealthCheckResponse.builder().withData().build()

    return HealthCheckResponse.named(MyReadinessHealthCheck.class.getSimpleName())
        .up()
        .withData("sample", UUID.randomUUID().toString())
        .build();
  }

}