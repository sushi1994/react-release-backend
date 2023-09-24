package com.lexoffice.reactreleases.controller;

import com.lexoffice.reactreleases.dto.ReactReleaseDto;
import com.lexoffice.reactreleases.dto.ReactReleasesRequest;
import com.lexoffice.reactreleases.facade.ReactReleasesFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reactRelease")
@Validated
@SecurityRequirement(name = "basicAuth")
public class ReactReleaseController {

    private final ReactReleasesFacade reactReleasesFacade;

    @Operation(summary = "Liefert eine Liste von React Release Posts anhand der Suchparameter")
    @GetMapping(value ="/releases", produces = { "application/json"})
    public List<ReactReleaseDto> getReactReleases(@Validated ReactReleasesRequest request) {
        return reactReleasesFacade.getReactRelease(request);
    }

    @Operation(summary = "Liefert die letzte suche vom User als XML Format")
    @GetMapping(value = "/lastSearch", produces = { "application/xml"}, consumes = MediaType.ALL_VALUE)
    public List<ReactReleaseDto> getLastSearchAsXML() {
        return reactReleasesFacade.getLastSearchAsXML();
    }

}
