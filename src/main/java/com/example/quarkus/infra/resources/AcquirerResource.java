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

@Path("/api/v1/acquirers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcquirerResource {

  private final AcquirerService service;

  public AcquirerResource(AcquirerService service) {
    this.service = service;
  }

  @POST
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