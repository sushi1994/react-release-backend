package com.lexoffice.reactreleases.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@ParameterObject
@Builder
public class ReactReleasesRequest {
    @Parameter(description = "Start Datum für die Filterung des erstellten Release")
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startCreatedAt;
    @Parameter(description = "End Datum für die Filterung des erstellten Release")
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endCreatedAt;
    @Parameter(description = "Autor Filterung des erstellten Release")
    String author;
}
