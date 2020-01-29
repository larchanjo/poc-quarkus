package com.example.quarkus.infra.healthchecks;

import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness // the liveness check accessible at /health/live
@ApplicationScoped
public class MyLivenessHealthCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    // Name -> HealthCheckResponse.named()
    // Up -> HealthCheckResponse.up()
    // Down -> HealthCheckResponse.down()
    // With Data -> HealthCheckResponse.builder().withData().build()

    return HealthCheckResponse.named(MyLivenessHealthCheck.class.getSimpleName())
        .up()
        .withData("sample", UUID.randomUUID().toString())
        .build();
  }

}