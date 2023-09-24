package com.lexoffice.reactreleases.facade;

import com.lexoffice.reactreleases.dto.ReactReleaseDto;
import com.lexoffice.reactreleases.dto.ReactReleasesRequest;
import com.lexoffice.reactreleases.model.UserSearchHistory;
import com.lexoffice.reactreleases.service.ReactReleasesService;
import com.lexoffice.reactreleases.service.UserSearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactReleasesFacade {

    private final ReactReleasesService reactReleasesService;
    private final UserSearchHistoryService userSearchHistoryService;

    public List<ReactReleaseDto> getReactRelease(ReactReleasesRequest request){
        UserSearchHistory userSearchHistory = UserSearchHistory.builder()
                .loginUser(getLoggedInUser())
                .author(request.getAuthor())
                .startCreatedAt(request.getStartCreatedAt())
                .endCreatedAt(request.getEndCreatedAt())
                .build();

        userSearchHistoryService.saveUserSearchHistory(userSearchHistory);
        return reactReleasesService.getReactRelease(request);
    }

    public List<ReactReleaseDto> getLastSearchAsXML() {
        Optional<UserSearchHistory> userSearchHistory = userSearchHistoryService.findUserSearchHistory(getLoggedInUser());
        if(userSearchHistory.isPresent()) {
            return reactReleasesService.getReactRelease(userSearchHistory.get());
        }
        return new ArrayList<>();
    }

    private String getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
