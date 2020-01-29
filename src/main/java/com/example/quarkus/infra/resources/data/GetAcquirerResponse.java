package com.example.quarkus.infra.resources.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAcquirerResponse {

  private String id;

  private String card;

  private Double value;

}
