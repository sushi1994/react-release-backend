package com.lexoffice.reactreleases.facade;

import com.lexoffice.reactreleases.MockData;
import com.lexoffice.reactreleases.dto.ReactReleasesRequest;
import com.lexoffice.reactreleases.model.UserSearchHistory;
import com.lexoffice.reactreleases.service.ReactReleasesService;
import com.lexoffice.reactreleases.service.UserSearchHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReactReleasesFacadeTest {

    @InjectMocks
    ReactReleasesFacade cut;

    @Mock
    UserSearchHistoryService userSearchHistoryService;

    @Mock
    ReactReleasesService reactReleasesService;

    @Test
    void getReactRelease() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("admin");
        ReactReleasesRequest reactReleasesRequest = ReactReleasesRequest.builder().build();
        when(reactReleasesService.getReactRelease(reactReleasesRequest)).thenReturn(MockData.getReactReleases());

        assertThat(cut.getReactRelease(reactReleasesRequest)).isEqualTo(MockData.getReactReleases());
    }

    @Test
    void getLastSearchAsXML() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("admin");
        UserSearchHistory userSearchHistory = UserSearchHistory.builder().loginUser("admin").build();
        when(reactReleasesService.getReactRelease(userSearchHistory)).thenReturn(MockData.getReactReleases());
        when(userSearchHistoryService.findUserSearchHistory("admin")).thenReturn(Optional.ofNullable(userSearchHistory));

        assertThat(cut.getLastSearchAsXML()).isEqualTo(MockData.getReactReleases());
    }
}
