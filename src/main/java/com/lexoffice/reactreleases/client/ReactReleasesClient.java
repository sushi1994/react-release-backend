package com.lexoffice.reactreleases.client;

import com.lexoffice.reactreleases.dto.ReactReleaseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "reactreleases", url = "https://api.github.com/repos/facebook/react")
public interface ReactReleasesClient {

    @GetMapping(value = "/releases", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ReactReleaseDto> getReactReleases();
}
