package com.lexoffice.reactreleases.service;

import com.lexoffice.reactreleases.client.ReactReleasesClient;
import com.lexoffice.reactreleases.dto.ReactReleaseDto;
import com.lexoffice.reactreleases.dto.ReactReleasesRequest;
import com.lexoffice.reactreleases.model.UserSearchHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactReleasesService {

    private final ReactReleasesClient reactReleasesClient;

    public List<ReactReleaseDto> getReactRelease(ReactReleasesRequest request){
        return reactReleasesClient.getReactReleases().stream()
                .filter(reactReleaseDto -> isAfter(reactReleaseDto.getCreated_at(), Optional.ofNullable(request.getStartCreatedAt())))
                .filter(reactReleaseDto -> isBefore(reactReleaseDto.getCreated_at(), Optional.ofNullable(request.getEndCreatedAt())))
                .filter(reactReleaseDto -> getReactReleaseByAuthor(reactReleaseDto.getAuthor().getLogin(), Optional.ofNullable(request.getAuthor())))
                .toList();
    }

    public List<ReactReleaseDto> getReactRelease(UserSearchHistory userSearchHistory){
        return reactReleasesClient.getReactReleases().stream()
                .filter(reactReleaseDto -> isAfter(reactReleaseDto.getCreated_at(), Optional.ofNullable(userSearchHistory.getStartCreatedAt())))
                .filter(reactReleaseDto -> isBefore(reactReleaseDto.getCreated_at(), Optional.ofNullable(userSearchHistory.getEndCreatedAt())))
                .filter(reactReleaseDto -> getReactReleaseByAuthor(reactReleaseDto.getAuthor().getLogin(), Optional.ofNullable(userSearchHistory.getAuthor())))
                .toList();
    }

    private boolean isAfter(LocalDateTime createdDate, Optional<LocalDate> startDate){
        return startDate.map(localDate -> createdDate.isAfter(localDate.atStartOfDay())).orElse(true);
    }
    private boolean isBefore(LocalDateTime createdDate, Optional<LocalDate> endDate){
        return endDate.map(localDate -> createdDate.isBefore(localDate.atTime(LocalTime.MAX))).orElse(true);
    }

    private boolean getReactReleaseByAuthor(String author, Optional<String> requestAuthor) {
        return requestAuthor.map(s -> !author.isEmpty() && author.toLowerCase().contains(s.toLowerCase())).orElse(true);
    }
}
