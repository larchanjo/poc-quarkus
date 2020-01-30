package com.example.quarkus.infra.resources;

import com.example.quarkus.domain.Acquirer;
import com.example.quarkus.domain.AcquirerService;
import com.example.quarkus.infra.resources.data.CreateAcquirerRequest;
import com.example.quarkus.infra.resources.data.CreateAcquirerResponse;
import com.example.quarkus.infra.resources.data.GetAcquirerResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/api/v1/acquirers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcquirerResource {

  private final AcquirerService service;

  public AcquirerResource(AcquirerService service) {
    this.service = service;
  }

  @POST
  @Counted(name = "create-acquirer-counter", description = "How many times this API have been called")
  @Timed(name = "create-acquirer-timer", description = "How long this API takes to perform the request", unit = MetricUnits.MILLISECONDS)
  public Response post(CreateAcquirerRequest request) {
    Acquirer acquirer = service.create(request.getCard(), request.getValue());
    CreateAcquirerResponse createAcquirerResponse = CreateAcquirerResponse.builder()
        .id(acquirer.getId())
        .card(acquirer.getCard())
        .value(acquirer.getValue())
        .build();

    return Response.status(Status.CREATED).entity(createAcquirerResponse).build();
  }

  @GET
  @Counted(name = "get-acquirers-counter", description = "How many times this API have been called")
  @Timed(name = "get-acquirers-timer", description = "How long this API takes to perform the request", unit = MetricUnits.MILLISECONDS)
  public Response getAll() {
    Collection<Acquirer> acquirers = service.get();

    Collection<GetAcquirerResponse> getAcquirerResponses = acquirers.stream()
        .map(acquirer -> GetAcquirerResponse.builder()
            .id(acquirer.getId())
            .card(acquirer.getCard())
            .value(acquirer.getValue())
            .build())
        .collect(Collectors.toCollection(ArrayList::new));

    return Response.ok(getAcquirerResponses).build();
  }

}