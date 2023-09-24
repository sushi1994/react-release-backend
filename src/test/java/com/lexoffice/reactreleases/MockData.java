package com.lexoffice.reactreleases;

import com.lexoffice.reactreleases.dto.AuthorDto;
import com.lexoffice.reactreleases.dto.ReactReleaseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static List<ReactReleaseDto> getReactReleases() {
        List<ReactReleaseDto> reactReleaseDtos = new ArrayList<>();

        ReactReleaseDto reactReleaseDto1 = new ReactReleaseDto();
        reactReleaseDto1.setCreated_at(LocalDateTime.of(2022,12,11,12,12,12));
        reactReleaseDto1.setAuthor(new AuthorDto());
        reactReleaseDto1.getAuthor().setLogin("Otto");
        reactReleaseDtos.add(reactReleaseDto1);

        ReactReleaseDto reactReleaseDto2 = new ReactReleaseDto();
        reactReleaseDto2.setCreated_at(LocalDateTime.of(2019,12,11,12,12,12));
        reactReleaseDto2.setAuthor(new AuthorDto());
        reactReleaseDto2.getAuthor().setLogin("Sam");
        reactReleaseDtos.add(reactReleaseDto2);

        ReactReleaseDto reactReleaseDto3 = new ReactReleaseDto();
        reactReleaseDto3.setCreated_at(LocalDateTime.of(2023,12,11,12,12,12));
        reactReleaseDto3.setAuthor(new AuthorDto());
        reactReleaseDto3.getAuthor().setLogin("Samuel");
        reactReleaseDtos.add(reactReleaseDto3);

        return reactReleaseDtos;
    }
}
